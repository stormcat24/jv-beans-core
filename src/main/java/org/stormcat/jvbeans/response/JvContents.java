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
package org.stormcat.jvbeans.response;

import org.stormcat.jvbeans.common.lang.StringUtil;
import org.stormcat.jvbeans.config.RecordTypeId;
import org.stormcat.jvbeans.jvlink.JvBindingDto;


/**
 * JV-Dataの取得結果を格納するためのクラスです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvContents<T extends JvBindingDto> extends JvResult {

    /** JV-Data文字列 */
    private String line;
    
    /** データ保存ファイル名 */
    private String fileName;
    
    /** JV-DataバインディングDTO */
    private T bindingDto;

    /**
     * JV-Data文字列を返します。
     * @return line
     */
    public String getLine() {
        return line;
    }

    /**
     * JV-Data文字列をセットします。
     * @param line JV-Data文字列
     */
    public void setLine(String line) {
        this.line = line;
        if (bindingDto != null) {
            bindingDto.setLine(line);
        }
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
    
    /**
     * JV-Dataが対象のレコード種別ID{@link RecordTypeId}のレコードであるかどうかを判定します。
     * @param recordTypeId レコード種別ID
     * @return 判定結果
     */
    public boolean startWith(RecordTypeId recordTypeId) {
        if (line == null) {
            return false;
        }
        return line.startsWith(recordTypeId.getValue());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
         return getLine();
    }

    /**
     * JV-Data文字列が空であるかどうかを判定します。
     * @return 判定結果
     */
    public boolean isEmpty() {
        return StringUtil.isBlank(line);
    }

    /**
     * JV-DataバインディングDTOを返します。
     * @return mappedDto JV-DataバインディングDTO
     */
    public T getBindingDto() {
        return bindingDto;
    }

    /**
     * JV-DataバインディングDTOをセットします。
     * @param bindingDto JV-DataバインディングDTO
     */
    public void setBindingDto(T bindingDto) {
        this.bindingDto = bindingDto;
    }
    
}
