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
package org.stormcat.jvbeans.response;

/**
 * JV-Linkの各APIの実行結果を格納するための基底クラスです。
 * <p>戻り値が{@code void}のAPIを除き、{@code returnCode}には値が格納されます。
 * @author a.yamada
 * @since 0.1
 *
 */
public abstract class JvResult {

    /** JV-Link APIの戻り値 */
    private int returnCode;
    
    /**
     * JV-Link APIの戻り値を返します。
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * JV-Link APIの戻り値をセットします。
     * @param returnCode JV-Link APIの戻り値
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
    
    
}
