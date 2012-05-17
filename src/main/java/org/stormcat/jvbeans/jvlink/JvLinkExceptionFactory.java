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

import org.stormcat.jvbeans.exception.jvlink.JvLinkAuthException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkDownloadFailedException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkErrorCode;
import org.stormcat.jvbeans.exception.jvlink.JvLinkException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkFileNotFoundException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkIllegalArgumentException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkIllegalDownloadFileException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkIllegalRegistryException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkInternalException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkNotClosedException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkNotInitializedException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkNotOpenedException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkParameterException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkServerException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkSetupFailedException;
import org.stormcat.jvbeans.response.JvContents;
import org.stormcat.jvbeans.response.JvResult;


/**
 * {@link JvLinkException}を生成するためのstatic factoryです。
 * @author a.yamada
 * @since 0.1
 *
 */
class JvLinkExceptionFactory {
    
    /**
     * 
     * コンストラクタ
     */
    private JvLinkExceptionFactory() { }
    
    /**
     * {@link JvResult}に適合する{@link JvLinkException}を生成します。
     * <p>{@link JvResult}がJVLinkがサポートしていないエラーコードを持つ場合は、{@code null}を返します。
     * @param result {@link JvResult}
     * @return JVLinkによって引き起こされた例外
     * @throws IllegalArgumentException {@link JvResult}が{@code null}の場合
     */
    static JvLinkException create(JvResult result) {
        if (result == null) {
            throw new IllegalArgumentException("結果オブジェクト(JVResult)がnullではいけません。");
        }
        
        if (JvLinkErrorCode._100.isSameError(result)) {
            return new JvLinkParameterException();
        } else if (JvLinkErrorCode._101.isSameError(result)) {
            return new JvLinkIllegalArgumentException(JvLinkErrorCode._101);
        } else if (JvLinkErrorCode._102.isSameError(result)) {
            return new JvLinkIllegalArgumentException(JvLinkErrorCode._102);
        } else if (JvLinkErrorCode._103.isSameError(result)) {
            return new JvLinkIllegalArgumentException(JvLinkErrorCode._103);
        } else if (JvLinkErrorCode._111.isSameError(result)) {
            return new JvLinkIllegalArgumentException(JvLinkErrorCode._111);
        } else if (JvLinkErrorCode._112.isSameError(result)) {
            return new JvLinkIllegalArgumentException(JvLinkErrorCode._112);
        } else if (JvLinkErrorCode._114.isSameError(result)) {
            return new JvLinkIllegalArgumentException(JvLinkErrorCode._114);
        } else if (JvLinkErrorCode._115.isSameError(result)) {
            return new JvLinkIllegalArgumentException(JvLinkErrorCode._115);
        } else if (JvLinkErrorCode._116.isSameError(result)) {
            return new JvLinkIllegalArgumentException(JvLinkErrorCode._116);
        } else if (JvLinkErrorCode._118.isSameError(result)) {
            return new JvLinkIllegalArgumentException(JvLinkErrorCode._118);
        } else if (JvLinkErrorCode._201.isSameError(result)) {
            return new JvLinkNotInitializedException();
        } else if (JvLinkErrorCode._202.isSameError(result)) {
            return new JvLinkNotClosedException();
        } else if (JvLinkErrorCode._203.isSameError(result)) {
            return new JvLinkNotOpenedException();
        } else if (JvLinkErrorCode._211.isSameError(result)) {
            return new JvLinkIllegalRegistryException(JvLinkErrorCode._211);
        } else if (JvLinkErrorCode._212.isSameError(result)) {
            return new JvLinkIllegalRegistryException(JvLinkErrorCode._212);
        } else if (JvLinkErrorCode._301.isSameError(result)) {
            return new JvLinkAuthException(JvLinkErrorCode._301);
        } else if (JvLinkErrorCode._302.isSameError(result)) {
            return new JvLinkAuthException(JvLinkErrorCode._302);
        } else if (JvLinkErrorCode._303.isSameError(result)) {
            return new JvLinkIllegalArgumentException(JvLinkErrorCode._303);
        } else if (JvLinkErrorCode._304.isSameError(result)) {
            return new JvLinkAuthException(JvLinkErrorCode._304); 
        } else if (JvLinkErrorCode._401.isSameError(result)) {
            return new JvLinkInternalException();
        } else if (JvLinkErrorCode._402.isSameError(result)) {
            return new JvLinkIllegalDownloadFileException(JvLinkErrorCode._402, ((JvContents<?>) result).getFileName());
        } else if (JvLinkErrorCode._403.isSameError(result)) {
            return new JvLinkIllegalDownloadFileException(JvLinkErrorCode._403, ((JvContents<?>) result).getFileName());
        } else if (JvLinkErrorCode._411.isSameError(result)) {
            return new JvLinkServerException(JvLinkErrorCode._411);
        } else if (JvLinkErrorCode._412.isSameError(result)) {
            return new JvLinkServerException(JvLinkErrorCode._412);
        } else if (JvLinkErrorCode._413.isSameError(result)) {
            return new JvLinkServerException(JvLinkErrorCode._413);
        } else if (JvLinkErrorCode._421.isSameError(result)) {
            return new JvLinkServerException(JvLinkErrorCode._421);
        } else if (JvLinkErrorCode._431.isSameError(result)) {
            return new JvLinkServerException(JvLinkErrorCode._431);
        } else if (JvLinkErrorCode._501.isSameError(result)) {
            return new JvLinkSetupFailedException();
        } else if (JvLinkErrorCode._502.isSameError(result)) {
            return new JvLinkDownloadFailedException();
        } else if (JvLinkErrorCode._503.isSameError(result)) {
            return new JvLinkFileNotFoundException();
        } else if (JvLinkErrorCode._504.isSameError(result)) {
            return new JvLinkServerException(JvLinkErrorCode._504);
        }
        
        return null;
    }
}
