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
 * JRAレーシングビュアーの動画種別(TT)のEnumによる表現です。
 * @author a.yamada
 * @since 0.1
 *
 */
public enum MovieQuality implements PairValueEnum<String>{

    /** 高解像度版 */
    HIGH_RESOLUTION("高解像度版", "01"),
    
    /** 通常版 */
    STANDARD("通常版", "02");
    
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
    private MovieQuality(String name, String value) {
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
