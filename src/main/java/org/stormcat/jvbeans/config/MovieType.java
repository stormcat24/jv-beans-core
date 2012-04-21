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
package org.stormcat.jvbeans.config;

import org.stormcat.jvbeans.common.constants.PairValueEnum;

/**
 * 映像の種類(movietype)のEnumによる表現です。
 * @author a.yamada
 * @since 0.1
 *
 */
public enum MovieType implements PairValueEnum<String> {
    
    /** レース映像 */
    RACE("レース映像", "00"),
    
    /** 調教映像指定週全馬 */
    TRAINING_SPECIFIED_WEEK_ALL_HORSE("調教映像指定週全馬", "11"),
    
    /** 調教映像指定週指定馬 */
    TRAINING_SPECIFIED_WEEK_SPECIFIED_HORSE("調教映像指定週指定馬", "12"),
    
    /** 調教映像指定馬全調教 */
    TRAINING_SPECIFIED_HORSE_ALL_TRAINING("調教映像指定馬全調教", "13"),
    ;

    /** 名称 */
    private String name;
    
    /** 値 */
    private String value;
    
    /**
     * 
     * コンストラクタ
     * @param name 名称
     * @param value 値
     */
    private MovieType(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public String getValue() {
        return value;
    }

    
}
