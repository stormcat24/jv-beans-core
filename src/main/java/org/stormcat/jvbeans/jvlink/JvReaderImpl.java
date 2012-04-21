/*
 * Copyright 2009-2011 the Stormcat Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.stormcat.jvbeans.jvlink;

import java.util.Iterator;

import org.stormcat.jvbeans.common.constants.FileExtension;
import org.stormcat.jvbeans.common.lang.ThreadUtil;
import org.stormcat.jvbeans.common.reflect.ClassUtil;
import org.stormcat.jvbeans.config.condition.OpenCondition;
import org.stormcat.jvbeans.config.condition.StoredOpenCondition;
import org.stormcat.jvbeans.exception.JvBeansRuntimeException;
import org.stormcat.jvbeans.jvlink.analyze.JvBeansContainer;
import org.stormcat.jvbeans.response.JvContents;

/**
 * JV-Dataをイテレートするための{@link JvReader}実装クラスです。
 *
 * @deprecated 文字列イテレートを{@link JvSimpleReader}に、
 * バインディングDTOイテレートを{@link JvBindingDtoReader}に実装を分離
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvReaderImpl <T extends JvBindingDto> implements JvReader<T> {

    /** {@link JvLinkManager}実装クラス */
    private JvLinkManagerImpl jvLinkManager;

    /** データ取得条件 */
    private OpenCondition<T> openCondition;

    /**
     *
     * コンストラクタ
     * @param jvLinkManager {@link JvLinkManager}
     * @param openCondition データ取得条件
     */
    public JvReaderImpl(JvLinkManager jvLinkManager, OpenCondition<T> openCondition) {
        this.jvLinkManager = (JvLinkManagerImpl) jvLinkManager;
        this.openCondition = openCondition;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int returnCode = -1;

            /**
             *
             * {@inheritDoc}
             */
            public boolean hasNext() {
                if (returnCode < -1) {
                    throw new JvBeansRuntimeException(String.format("データの読み込みに失敗しました。: return code = %d", returnCode));
                }
                return returnCode != 0;
            }

            /**
             *
             * {@inheritDoc}
             */
            public T next() {
                JvContents<T> contents = readLine();
                returnCode = contents.getReturnCode();
                T dto = contents.getBindingDto();
                if (dto == null) {
                    dto = ClassUtil.newInstance(openCondition.getType());
                }
                dto.setFileName(contents.getFileName());
                return dto;
            }

            /**
             *
             * {@inheritDoc}
             */
            public void remove() {
                JvContents<T> jvContents = readLine();
                returnCode = jvContents.getReturnCode();
            }

            private JvContents<T> readLine() {
                if (openCondition == null) {
                    throw new JvBeansRuntimeException("JVOpenがされていません。");
                }

                return readContents(openCondition);
            }

            private JvContents<T> readContents(OpenCondition<T> condition) {
                JvContents<T> contents = null;

                JvBeansContainer container = jvLinkManager.getJvBeansContainer();

                int result = -1;
                while (result != 0) {
                    contents = read(container.getRecordByteLength(condition.getRecordTypeId()));
                    result = contents.getReturnCode();
                    if (result < -1) {
                        if (result == -3) {
                            // -3のときは少し間隔を空けて、リトライする。とりあえず1000msで十分
                            ThreadUtil.sleep(1000);
                            continue;
                        }
                        JvLinkExceptionHandler.handleException(contents);
                    }

                    if (contents.startWith(condition.getRecordTypeId()) && !contents.isEmpty()) {
                        contents.setBindingDto(
                                jvLinkManager.getJvBindingDtoFactory().create(contents.toString(), openCondition));
                        break;
                    } else {
                        // rtOpenはskip不要
                        if (condition instanceof StoredOpenCondition) {
                            jvLinkManager.skip();
                        }
                    }
                }
                return contents;
            }

            @SuppressWarnings("unchecked")
            private JvContents<T> read(int length) {
                return (JvContents<T>) jvLinkManager.getJvLinkWrapper().jvRead(length);
            }

        };
    }

    /**
     * {@inheritDoc}
     */
    public void setOutputType(FileExtension outputType) {
    }

    /**
     * {@inheritDoc}
     */
    public FileExtension getOutputType() {
        return null;
    }

}
