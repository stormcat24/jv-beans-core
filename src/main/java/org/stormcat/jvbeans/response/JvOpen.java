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

import org.stormcat.jvbeans.jvlink.JvLinkWrapper;

/**
 * {@link JvLinkWrapper#jvOpen(String, String, long)}で、
 * 蓄積系データを要求した際に取得できる情報を格納するためのクラスです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvOpen extends JvResult {

    /** 指定した条件に該当する全ファイル数 */
    private Integer readCount;
    
    /** {@code readCount}のうち、ダウンロードが必要なファイル数 */
    private Integer downloadCount;
    
    /** 全ファイルのうち最も新しいファイルのタイムスタンプ */
    private String lastFileTimeStamp;

    /**
     * 指定した条件に該当する全ファイル数を返します。
     * @return readCount 指定した条件に該当する全ファイル数
     */
    public Integer getReadCount() {
        return readCount;
    }

    /**
     * 指定した条件に該当する全ファイル数をセットします。
     * @param readCount 指定した条件に該当する全ファイル数
     */
    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    /**
     * ダウンロードが必要なファイル数を返します。
     * @return downloadCount ダウンロードが必要なファイル数
     */
    public Integer getDownloadCount() {
        return downloadCount;
    }

    /**
     * ダウンロードが必要なファイル数をセットします。
     * @param downloadCount ダウンロードが必要なファイル数
     */
    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    /**
     * 最も新しいファイルのタイムスタンプを返します。
     * @return lastFileTimeStamp 最も新しいファイルのタイムスタンプ
     */
    public String getLastFileTimeStamp() {
        return lastFileTimeStamp;
    }

    /**
     * 最も新しいファイルのタイムスタンプをセットします。
     * @param lastFileTimeStamp 最も新しいファイルのタイムスタンプ
     */
    public void setLastFileTimeStamp(String lastFileTimeStamp) {
        this.lastFileTimeStamp = lastFileTimeStamp;
    }
    
    
}
