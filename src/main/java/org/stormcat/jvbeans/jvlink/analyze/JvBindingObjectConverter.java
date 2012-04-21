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
package org.stormcat.jvbeans.jvlink.analyze;

import java.util.Date;

import org.stormcat.jvbeans.common.constants.PairValueEnum;
import org.stormcat.jvbeans.common.lang.DateUtil;
import org.stormcat.jvbeans.common.lang.DoubleUtil;
import org.stormcat.jvbeans.common.lang.FloatUtil;
import org.stormcat.jvbeans.common.lang.IntegerUtil;
import org.stormcat.jvbeans.common.lang.LongUtil;
import org.stormcat.jvbeans.util.PairValueEnumUtil;


/**
 * JV-Dataの文字列からオブジェクトへの変換を行うためのコンバーターです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvBindingObjectConverter {
    
    /**
     * 
     * コンストラクタ
     */
    private JvBindingObjectConverter() { }

    /**
     * {@link JvRecordMeta}のメタ情報を元に、文字列を適するオブジェクトへ変換して返します。
     * <p>バインディング型が{@link PairValueEnum}実装型や基本データ型、{@link java.util.Date}型でない場合には
     * {@code null}を返します。
     * @param target レコード文字列
     * @param meta メタ情報
     * @return 変換後オブジェクト
     */
    @SuppressWarnings("unchecked")
    public static Object convert(String target, JvRecordMeta meta) {
        Class<?> type = meta.getType();
        float correction = meta.getCorrection();
        
        if (PairValueEnumUtil.isImplementsPairValueEnum(type)) {
           return PairValueEnumUtil.getPairValueEnum((Class<? extends PairValueEnum<?>>) type, target);
        } else if (String.class == type) {
            return target;
        } else if (Integer.class == type) {
            Integer i = IntegerUtil.convert(target);
            if (i == null) {
                return i;
            }
            return (int)(i * correction);
        } else if (Float.class  == type) {
            Float f = FloatUtil.convert(target);
            if (f == null) {
                return f;
            }
            return  f * correction;
        } else if (Long.class == type) {
            Long l = LongUtil.convert(target);
            if (l == null) {
                return l;
            }
            return (long)(l * correction);
        } else if (Double.class == type) {
            Double d = DoubleUtil.convert(target);
            if (d == null) {
                return d;
            }
            return (double)(d * correction);
        } else if (Date.class == type) {
            return DateUtil.parseDate(target, "yyyyMMdd");
        }
        return null;
    }
    
}
