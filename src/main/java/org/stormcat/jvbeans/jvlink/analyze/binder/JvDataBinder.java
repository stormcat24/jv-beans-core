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

import java.util.List;

import org.stormcat.jvbeans.common.constants.FileExtension;
import org.stormcat.jvbeans.jvlink.analyze.JvRecordMeta;


/**
 * JV-Data文字列をDTOにバインドするためのBinderです。
 * @author a.yamada
 * @since 0.3
 *
 */
public abstract class JvDataBinder {

    /** 入力形式 */
    protected FileExtension inputType;
    
    /**
     * 
     * コンストラクタ
     * @param inputType 入力形式
     */
    protected JvDataBinder(FileExtension inputType) {
        this.inputType = inputType;
    }
    
    /**
     * Jv-Dataをメタ情報を基にDTOにバインディングします。
     * @param <T> DTO型パラメータ
     * @param metaItems メタ情報リスト
     * @param dto データバインディング対象DTO
     * @param data バインディング用文字列
     */
    public abstract <T> void bindProperty(List<JvRecordMeta> metaItems, T dto, String data);
    
}
