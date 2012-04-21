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
package org.stormcat.jvbeans.exception;

import org.stormcat.jvbeans.jvlink.JvLinkManager;

/**
 * {@link JvLinkManager}の引数として渡されるkeyが、適切な形式ではない場合に投げられる例外です。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvIllegalFormatKeyException extends JvBeansRuntimeException {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = -2724262791406422843L;

    /**
     * コンストラクタ
     */
    public JvIllegalFormatKeyException() {
        super();
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param cause 例外
     */
    public JvIllegalFormatKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     * @param message メッセージ
     */
    public JvIllegalFormatKeyException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * @param cause 例外
     */
    public JvIllegalFormatKeyException(Throwable cause) {
        super(cause);
    }

    
}
