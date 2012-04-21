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

import java.io.IOException;

/**
 * {@link IOException}をラップするRuntime例外です。
 * @author a.yamada
 *
 */
public class IORuntimeException extends StormcatRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 5877504330032406033L;

    /**
     * コンストラクタ
     * @param message メッセージ
     * @param cause {@link IOException}
     */
    public IORuntimeException(String message, IOException cause) {
        super(message, cause);
    }
    
    /**
     * コンストラクタ
     * @param message メッセージ
     */
    public IORuntimeException(String message) {
        super(message);
    }    

    /**
     * コンストラクタ
     * @param cause {@link IOException}
     */
    public IORuntimeException(IOException cause) {
        super(cause);
    }

    
}
