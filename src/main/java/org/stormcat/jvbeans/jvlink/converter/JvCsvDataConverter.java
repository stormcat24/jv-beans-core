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
package org.stormcat.jvbeans.jvlink.converter;

import org.stormcat.jvbeans.common.constants.FileExtension;

/**
 * JV-DataをCSV形式に変換するためのコンバーターです。
 * @author a.yamada
 * @since 0.3
 * 
 */
public class JvCsvDataConverter extends JvIncludeSeparatorDataConvertor {

    /**
     * コンストラクタ
     */
    public JvCsvDataConverter() {
        super(FileExtension.CSV);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getSeparator() {
        return ",";
    }


}
