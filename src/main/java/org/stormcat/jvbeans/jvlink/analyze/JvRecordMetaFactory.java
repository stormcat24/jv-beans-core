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

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.stormcat.jvbeans.annotation.JvProperty;
import org.stormcat.jvbeans.common.reflect.FieldUtil;
import org.stormcat.jvbeans.common.reflect.GenericsUtil;
import org.stormcat.jvbeans.config.RecordTypeId;


/**
 * レコードメタ情報{@link JvRecordMeta}を生成するためのファクトリです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvRecordMetaFactory {

    private Class<? extends RecordTypeId> recordTypeIdClass;

    /**
     * 
     * コンストラクタ
     * @param recordTypeIdClass {@link RecordTypeId} 実装Enum
     */
    public JvRecordMetaFactory(Class<? extends RecordTypeId> recordTypeIdClass) { 
        this.recordTypeIdClass = recordTypeIdClass;
    }
    
    /**
     * フィールドに付与された{@link JvProperty}からメタ情報を構築したものを返します。
     * <p>{@link JvProperty}が付与されていないフィールドの場合{@code null}を返します。
     * @param field フィールド
     * @return レコードメタ情報
     * @throws IllegalArgumentException fieldが{@code null}の場合
     */
    public JvRecordMeta create(Field field) {
        if (field == null) {
            throw new IllegalArgumentException("フィールドがnullになっています。");
        }
        
        JvProperty jvProperty = field.getAnnotation(JvProperty.class);
        if (jvProperty == null) {
            return null;
        }
        
        Class<?> type = field.getType();
        if (type == List.class) {
            Class<?> genericClass = GenericsUtil.getRowClass(ParameterizedType.class.cast(field.getGenericType()).getActualTypeArguments()[0]);
            List<JvRecordMeta> metaItems = null;
            String fqdn = genericClass.getName();
            // TODO JOINクラスのチェック方法
            if (fqdn.startsWith("org.stormcat.jvbeans.jvlink.definitions.dto.child.") && fqdn.endsWith("Dto")) {
                metaItems = new ArrayList<JvRecordMeta>();
                for (Field f : genericClass.getDeclaredFields()) {
                    JvRecordMeta childMeta = create(f);
                    if (childMeta != null) {
                        metaItems.add(childMeta);    
                    }
                }    
            } 
            return new JvRecordMeta(jvProperty.position(), jvProperty.byteLength(), jvProperty.repeatCount(), 
                    genericClass, FieldUtil.getSetterMethod(field), jvProperty.correction(), metaItems);
        } else {
            return new JvRecordMeta(jvProperty.position(), jvProperty.byteLength(), jvProperty.repeatCount(), 
                    type == RecordTypeId.class ? recordTypeIdClass : field.getType(), FieldUtil.getSetterMethod(field), jvProperty.correction());  
        }

    }
}
