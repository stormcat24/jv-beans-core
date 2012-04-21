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
 * 動画リストの1行分を格納するためのクラスです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvMvContents extends JvResult {

    /** 動画リスト1行分 */
    private String line;

    /**
     * 動画リストの1行分を返します。
     * @return line 動画リストの1行分
     */
    public String getLine() {
        return line;
    }

    /**
     * 動画リストの1行分をセットします。
     * @param line 動画リストの1行分
     */
    public void setLine(String line) {
        this.line = line;
    }
    
    
}
