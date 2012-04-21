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
package org.stormcat.jvbeans.jvlink;

import org.stormcat.jvbeans.config.DataDiv;

/**
 * JV-LinkのデータをバインディングするDtoの基底クラスです。
 * <p>{@link JvBean}アノテーションが付与されるバインディングDtoはこのDtoを継承する必要があります。
 * <p>JOIN用の子Dtoはこのクラスを継承する必要はありません。
 * @author a.yamada
 * @since 0.1
 *
 */
public abstract class JvBindingDto {

    /** JV-Data文字列 */
    private String line;

    /** データ保存ファイル名 */
    private String fileName;

    /**
     * レコード種別IDを返します。
     * <p>このDtoがどのレコード種別のバインディングであるかを示します。
     * @return レコード種別ID
     */
    public abstract org.stormcat.jvbeans.config.RecordTypeId getRecordTypeId();

    /**
     * データ区分を返します。
     * @return データ区分
     */
    public abstract DataDiv getDataDiv();

    /**
     * データ作成年月日を返します。
     * @return データ作成年月日
     */
    public abstract java.util.Date getDataCreateDate();

    /**
     * このDTOのレコードの文字列表現を返します。
     * @return レコード文字列
     */
    public String toString() {
        return line;
    }

    /**
     * JV-Data文字列をセットします。
     * @param line JV-Data文字列
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     * データ保存ファイル名を返します。
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * データ保存ファイル名をセットします。
     * @param fileName データ保存ファイル名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


}
