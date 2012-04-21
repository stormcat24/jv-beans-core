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
import org.stormcat.jvbeans.jvlink.JvBindingDto;

/**
 * 速報系データのデータ取得条件を表現するクラスです。
 * @author a.yamada
 *
 */
public class RealTimeOpenCondition <T extends JvBindingDto> extends OpenCondition <T> {

    /**
     * コンストラクタ
     * @param dataSpec データ種別
     * @param recordTypeId レコード種別ID
     * @param type バインディング型
     */
    public RealTimeOpenCondition(DataSpec dataSpec, RecordTypeId recordTypeId, Class<T> type) {
        super(dataSpec, recordTypeId, type);
    }

}
