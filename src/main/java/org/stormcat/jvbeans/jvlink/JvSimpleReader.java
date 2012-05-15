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
import org.stormcat.jvbeans.config.condition.OpenCondition;
import org.stormcat.jvbeans.jvlink.analyze.JvBeansContainer;
import org.stormcat.jvbeans.jvlink.converter.JvDataConverter;
import org.stormcat.jvbeans.jvlink.converter.JvDataConverterFactory;
import org.stormcat.jvbeans.response.JvContents;

/**
 * JV-Dataをイテレートするための{@link JvReader}実装クラスです。
 * <p>JV-DataをStringでイテレーションする{@link JvReader}で、DTOへのデータバインディングは行いません。
 * @author a.yamada
 * @since 0.2
 *
 */
public class JvSimpleReader<T extends JvBindingDto> implements JvReader<String> {

    private JvDataIterator<T> iterator;

    private FileExtension outputType;

    private JvBeansContainer jvBeansContainer;

    /**
     * コンストラクタ
     * @param jvLinkManager {@link JvLinkManagerImpl}
     * @param openCondition データ種別
     */
    public JvSimpleReader(JvLinkManagerImpl jvLinkManager, OpenCondition<T> condition) {
        iterator = new JvDataIterator<T>(jvLinkManager, condition);
        jvBeansContainer = jvLinkManager.getJvBeansContainer();
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            /**
             * {@inheritDoc}
             */
            public boolean hasNext() {
                return iterator.hasNext();
            }

            /**
             * {@inheritDoc}
             */
            public String next() {
                JvContents<T> contents = iterator.next();
                System.out.println("RAW:" + contents.toString());
                JvDataConverter convertor = JvDataConverterFactory.createConvertor(outputType);
                contents.setLine(convertor.convert(jvBeansContainer, contents.getLine()));
                return contents.getLine();
            }

            /**
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
