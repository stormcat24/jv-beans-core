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

import java.util.List;

import org.stormcat.jvbeans.annotation.JvBean;

/**
 * JV-DataをバインディングするDTOに付与されたアノテーションのメタ情報を保持するクラスです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvBeanMeta {

    /** バインディングするDTOの型 */
    private Class<?> type;
    
    /** 各レコードのメタ情報 */
    private List<JvRecordMeta> recordMetaItems;
    
    /** レコード長 */
    private int byteLength;
    
    /**
     * 
     * コンストラクタ
     * @param type バインディングするDTOの型
     * @param recordMetaItems 各レコードのメタ情報
     */
    public JvBeanMeta(Class<?> type, List<JvRecordMeta> recordMetaItems) {
        this.type = type;
        this.recordMetaItems = recordMetaItems;
        JvBean annotation = type.getAnnotation(JvBean.class);
        if (annotation != null) {
            byteLength = annotation.byteLength();
        }
    }

    /**
     * 各レコードのメタ情報を返します。
     * @return type 各レコードのメタ情報
     */
    public Class<?> getType() {
        return type;
    }

    /**
     * 各レコードのメタ情報を返します。
     * @return recordMetaItems 各レコードのメタ情報
     */
    public List<JvRecordMeta> getRecordMetaItems() {
        return recordMetaItems;
    }

    /**
     * レコード長を返します。
     * @return byteLength レコード長
     */
    public int getByteLength() {
        return byteLength;
    }
    
    
    
}
