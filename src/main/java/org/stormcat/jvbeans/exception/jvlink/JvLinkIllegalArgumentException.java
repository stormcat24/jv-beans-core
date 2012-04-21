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
 * JVLinkのメソッドに対して、不正な値が与えられた際に投げられる例外です。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvLinkIllegalArgumentException extends JvLinkException {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -2563333872512927580L;

    /**
     * 
     * コンストラクタ
     * @param jvLinkErrorCode エラーコード
     */
    public JvLinkIllegalArgumentException(JvLinkErrorCode jvLinkErrorCode) {
        super(jvLinkErrorCode);
    }
}
