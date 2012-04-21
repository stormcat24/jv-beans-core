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
package org.stormcat.jvbeans.config.condition;

import org.stormcat.jvbeans.config.DataSpec;
import org.stormcat.jvbeans.config.RecordTypeId;

/**
 * データ取得要求(open)時に指定する条件を表現するためのクラスです。
 * @author a.yamada
 *
 */
public abstract class OpenCondition <T> {

    /** データ種別 */
    private DataSpec dataSpec;

    /** レコード種別ID */
    private RecordTypeId recordTypeId;

    /** バインディング型 */
    private Class<T> type;

    /**
     * コンストラクタ
     * @param dataSpec データ種別
     * @param recordTypeId レコード種別ID
     * @param type バインディング型
     */
    protected OpenCondition(DataSpec dataSpec, RecordTypeId recordTypeId, Class<T> type) {
        this.dataSpec = dataSpec;
        this.recordTypeId = recordTypeId;
        this.type = type;
    }

    /**
     * データ種別を返します。
     * @return dataSpec データ種別
     */
    public DataSpec getDataSpec() {
        return dataSpec;
    }

    /**
     * レコード種別IDを返します。
     * @return recordTypeId レコード種別ID
     */
    public RecordTypeId getRecordTypeId() {
        return recordTypeId;
    }

    /**
     * バインディング型を返します。
     * @return type バインディング型
     */
    public Class<T> getType() {
        return type;
    }


}
