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
import org.stormcat.jvbeans.common.reflect.ClassUtil;
import org.stormcat.jvbeans.config.condition.OpenCondition;
import org.stormcat.jvbeans.jvlink.converter.JvDataConverter;
import org.stormcat.jvbeans.jvlink.converter.JvDataConverterFactory;
import org.stormcat.jvbeans.response.JvContents;

/**
 * JV-Dataをイテレートするための{@link JvReader}実装クラスです。
 * <p>JV-DataをバインディングDTOでイテレーションします。
 * @author a.yamada
 * @since 0.2
 *
 */
public class JvBindingDtoReader<T extends JvBindingDto> implements JvReader<T> {

    private JvDataIterator<T> iterator;

    private JvLinkManagerImpl jvLinkManager;

    private FileExtension outputType;

    /**
     *
     * コンストラクタ
     * @param jvLinkManager {@link JvLinkManagerImpl}
     * @param openCondition データ種別
     */
    public JvBindingDtoReader(JvLinkManagerImpl jvLinkManager, OpenCondition<T> openCondition) {
        iterator = new JvDataIterator<T>(jvLinkManager, openCondition);
        this.jvLinkManager = jvLinkManager;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            /**
             *
             * {@inheritDoc}
             */
            public boolean hasNext() {
                return iterator.hasNext();
            }

            /**
             *
             * {@inheritDoc}
             */
            public T next() {
                JvContents<T> contents =  iterator.next();
                // ここのキャストは許容される
                Class<T> clazz = (Class<T>) iterator.getOpenCondition().getType();
                OpenCondition<T> condition = iterator.getOpenCondition();
                if (contents != null && contents.startWith(condition.getRecordTypeId()) && !contents.isEmpty()) {
                    T dto = jvLinkManager.getJvBindingDtoFactory().create(contents.toString(), condition);
                    contents.setBindingDto(dto);
                    JvDataConverter convertor = JvDataConverterFactory.createConvertor(outputType);
                    contents.setLine(convertor.convert(jvLinkManager.getJvBeansContainer(), contents.getLine()));
                    dto.setFileName(contents.getFileName());
                    return dto;
                } else {
                    return ClassUtil.newInstance(clazz);
                }
            }

            /**
             *
             * {@inheritDoc}
             */
            public void remove() {
                iterator.remove();
            }

        };
    }

    /**
     * {@inheritDoc}
     */
    public void setOutputType(FileExtension outputType) {
        this.outputType = outputType;
    }

    /**
     * {@inheritDoc}
     */
    public FileExtension getOutputType() {
        return outputType;
    }

}
