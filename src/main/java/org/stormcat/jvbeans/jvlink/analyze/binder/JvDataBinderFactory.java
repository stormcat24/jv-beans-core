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
package org.stormcat.jvbeans.jvlink.analyze.binder;

import java.util.HashMap;
import java.util.Map;

import org.stormcat.jvbeans.common.constants.FileExtension;

/**
 * {@link JvDataBinder}を取得するためのstaticファクトリクラスです。
 * @author a.yamada
 * @since 0.3
 *
 */
public class JvDataBinderFactory {

    /** {@link JvDataBinder}保持Map */
    private static final Map<FileExtension, JvDataBinder> binderMap = new HashMap<FileExtension, JvDataBinder>();

    /** 通常のBinder */
    private static final JvStandardDataBinder standardDataBinder = new JvStandardDataBinder();
    
    static {
        binderMap.put(FileExtension.CSV, new JvCsvDataBinder());
        binderMap.put(FileExtension.TSV, new JvTsvDataBinder());
    }
    
    /**
     * 指定の{@link FileExtension}に対応する{@link JvDataBinder}を返します。
     * @param inputType 入力形式
     * @return 対応Binder
     */
    public static JvDataBinder createBinder(FileExtension inputType) {
        return binderMap.containsKey(inputType) ? binderMap.get(inputType) : standardDataBinder;
    }
    
}
