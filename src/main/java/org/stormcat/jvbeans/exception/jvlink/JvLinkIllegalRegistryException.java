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
 * サービスキー等の設定値をレジストリから読み出して使用する時に、
 * 値が不正な形式に書き換えられた場合に投げられる例外です。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvLinkIllegalRegistryException extends JvLinkException {

    /**
     * SerialVerisonUID
     */
    private static final long serialVersionUID = 6313555720428042149L;

    /**
     * コンストラクタ
     * @param jvLinkErrorCode エラーコード
     */
    public JvLinkIllegalRegistryException(JvLinkErrorCode jvLinkErrorCode) {
        super(jvLinkErrorCode);
    }
}
