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
package org.stormcat.jvbeans.jvlink;

import org.stormcat.jvbeans.exception.jvlink.JvLinkException;
import org.stormcat.jvbeans.response.JvResult;


/**
 * JVLinkによって引き起こされる例外をハンドリングするためのハンドラです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvLinkExceptionHandler {

    /**
     * 
     * コンストラクタ
     */
    private JvLinkExceptionHandler() { }
    
    /**
     * {@link JvResult}の内容を見て例外ハンドリングを行います。
     * <p>JVResultが問題となるエラーコードを保持していない場合は何も行いません。
     * @param result 結果オブジェクト
     * @throws IllegalArgumentException JVResultが{@code null}の場合
     */
    public static void handleException(JvResult result) {
        JvLinkException ex = JvLinkExceptionFactory.create(result);
        if (ex != null) {
            throw ex;
        }
    }
    
}
