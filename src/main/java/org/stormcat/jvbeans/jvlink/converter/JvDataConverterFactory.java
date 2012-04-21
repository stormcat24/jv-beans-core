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

import java.util.HashMap;
import java.util.Map;

import org.stormcat.jvbeans.common.constants.FileExtension;

/**
 * {@link JvDataConverter}を取得するためのstaticファクトリクラスです。
 * @author a.yamada
 * @since 0.3
 * 
 */
public class JvDataConverterFactory {

    /** {@link JvDataConverter}保持Map */
    private static final Map<FileExtension, JvDataConverter> convertorMap = new HashMap<FileExtension, JvDataConverter>();

    /** 未処理用コンバーター */
    private static final JvPlainDataConverter plainDataConvertor = new JvPlainDataConverter();
    
    static {
        convertorMap.put(FileExtension.CSV, new JvCsvDataConverter());
        convertorMap.put(FileExtension.TSV, new JvTsvDataConverter());
    }
    
    /**
     * 指定の{@link FileExtension}に対応する{@link JvDataConverter}を返します。
     * @param outputType 出力形式
     * @return 変換コンバーター
     */
    public static JvDataConverter createConvertor(FileExtension outputType) {
        return convertorMap.containsKey(outputType) ? convertorMap.get(outputType) : plainDataConvertor;
    }
    
}
