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
 * JVLinkによって返されたエラーをラップし、例外として扱うための抽象クラスです。
 * <p>各エラーコードに対応する例外クラスはこのクラスを継承する必要があります。
 * @author a.yamada
 * @since 0.1
 *
 */
public abstract class JvLinkException extends RuntimeException {

    /** SerialVersionUID */
    private static final long serialVersionUID = 7740288253675025557L;
    
    /** JV-Linkのメソッドが返す戻り値です。 */
    private int errorCode;

    /**
     * 
     * コンストラクタ
     */
    public JvLinkException() {
        super();
    }
    
    /**
     * 
     * コンストラクタ
     * @param jvLinkErrorCode エラーコード
     */
    public JvLinkException(JvLinkErrorCode jvLinkErrorCode) {
        this(jvLinkErrorCode.getValue(), jvLinkErrorCode.getName());
    }
    
    /**
     * コンストラクタ
     * @param errorCode エラーコード
     * @param message エラーメッセージ
     */
    public JvLinkException(int errorCode, String message) {
        super(message);
        this.errorCode  = errorCode;
    }

    /**
     * エラーコードを返します。
     * @return errorCode エラーコード
     */
    public final int getErrorCode() {
        return errorCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getMessage() {
        return String.format("[JV-Link Error Code : %d] %s", errorCode, super.getMessage());
    }
    
}
