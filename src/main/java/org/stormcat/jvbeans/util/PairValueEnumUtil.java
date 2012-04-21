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
package org.stormcat.jvbeans.util;

import org.stormcat.jvbeans.common.constants.PairValueEnum;

/**
 * {@link PairValueEnum}を操作するためのユーティリティです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class PairValueEnumUtil {

    /**
     * 
     * コンストラクタ
     */
    private PairValueEnumUtil() { }
    
    /**
     * 対象の{@link Class}が{@link PairValueEnum}を実装しているかどうかを判定します。
     * @param clazz クラス
     * @return 判定結果
     */
    public static boolean isImplementsPairValueEnum(Class<?> clazz) {
        for (Class<?> i : clazz.getInterfaces()) {
            if (i == PairValueEnum.class) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 文字列から{@link PairValueEnum}を実装したEnumのインスタンスを返します。
     * <p>該当するEnum定数が存在しなかった場合は、{@code null}を返します。
     * @param clazz {@link PairValueEnum}実装クラス
     * @param value 対象文字列
     * @return {@link PairValueEnum}
     */
    public static PairValueEnum<?> getPairValueEnum(Class<? extends PairValueEnum<?>> clazz, String value) {
        for (PairValueEnum<?> e : clazz.getEnumConstants()) {
            if (e.getValue().toString().equals(value)) {
                return e;
            }
        }
        
        return null;
    }
}
