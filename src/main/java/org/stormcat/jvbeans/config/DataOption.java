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
import org.stormcat.jvbeans.jvlink.JvLinkManager;

/**
 * {@link JvLinkManager#open}で指定するoptionのEnumによる表現です。
 * @author a.yamada
 * @since 0.1
 *
 */
public enum DataOption implements PairValueEnum<Integer> {
    
    /** 通常データ */
    STANDARD("通常データ", 1),
    
    /** 今週データ */
    THIS_WEEK("今週データ", 2),
    
    /** ダイアログ有りセットアップデータ */
    SETUP_WITH_DIALOG("ダイアログ有りセットアップデータ", 3),
    
    /** ダイアログ無しセットアップ */
    SETUP_WITHOUT_DIALOG("ダイアログ無しセットアップ", 4);
    
    /** 名称 */
    private String name;
    
    /** 値 */
    private Integer value;
    
    /**
     * 
     * コンストラクタ
     * @param name 名称
     * @param value 値
     */
    private DataOption(String name, Integer value) {
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
    public Integer getValue() {
        return value;
    }

    
}
