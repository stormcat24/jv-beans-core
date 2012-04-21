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
package org.stormcat.jvbeans.exception.jvlink;

/**
 * 何らかの問題によってダウンロードしたファイルに破損等の異常が発生し、
 * 正しくデータを読み出せなくなった場合に投げられる例外です。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvLinkIllegalDownloadFileException extends JvLinkException {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -2035509523759808338L;

    /**
     * 
     * コンストラクタ
     * @param jvLinkErrorCode エラーコード
     */
    public JvLinkIllegalDownloadFileException(JvLinkErrorCode jvLinkErrorCode) {
        super(jvLinkErrorCode);
    }
}
