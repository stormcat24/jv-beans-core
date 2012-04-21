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
package org.stormcat.jvbeans.common.exception;

/**
 * {@link NoSuchMethodException}をラップするRuntime例外です。
 * @author a.yamada
 *
 */
public class NoSuchMethodRuntimeException extends StormcatRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -7346648334184906671L;

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param cause {@link NoSuchMethodException}
     */
    public NoSuchMethodRuntimeException(String message, NoSuchMethodException cause) {
        super(message, cause);
    }

    /**
     * コンストラクタ
     * @param cause {@link NoSuchMethodException}
     */
    public NoSuchMethodRuntimeException(NoSuchMethodException cause) {
        super(cause);
    }

    
}
