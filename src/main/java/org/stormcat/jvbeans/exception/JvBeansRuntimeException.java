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

/**
 * JVBeansの基本例外クラスです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvBeansRuntimeException extends RuntimeException {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 2520476345534199159L;

    /**
     * コンストラクタ
     */
    public JvBeansRuntimeException() {
        super();
    }

    /**
     * コンストラクタ
     * @param message エラーメッセージ
     * @param cause 例外
     */
    public JvBeansRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     * @param message エラーメッセージ
     */
    public JvBeansRuntimeException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * @param cause 例外
     */
    public JvBeansRuntimeException(Throwable cause) {
        super(cause);
    }

    
}
