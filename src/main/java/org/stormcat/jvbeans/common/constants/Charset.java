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
package org.stormcat.jvbeans.common.constants;

/**
 * 文字コードを定義するEnumです。
 * @author a.yamada
 *
 */
public enum Charset implements SingleValueEnum<String> {

    /** 文字コードUTF-8を表現します。 */
    UTF8("UTF-8"),
    
    /** 文字コードEUC_JPを表現します。 */
    EUCJP("EUC_JP"),

    /** 文字コードShift_JISを表現します。 */
    SHIFTJIS("Shift_JIS"),

    /** 文字コードMS932を表現します。 */
    MS932("MS932"),

    /** 文字コードWindows-31Jを表現します。 */
    WINDOWS31J("Windows-31J"),

    /** 文字コードISO-8859-1を表現します。 */
    ISO8859_1("ISO-8859-1")
    ;
    
    /** 値 */
    private String value;
    
    /**
     * 
     * コンストラクタ
     * @param value 値
     */
    private Charset(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    public String getValue() {
        return value;
    }
    
}
