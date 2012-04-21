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
package org.stormcat.jvbeans.util;

/**
 * JVBeans用の文字列操作ユーティリティです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvStringUtil {

    /**
     * 
     * コンストラクタ
     */
    private JvStringUtil() {
        throw new AssertionError();
    }
    
    /**
     * JV-Data用の空白除去メソッドです。
     * <p>対象文字列が{@code null}の場合は{@code null}を返します。
     * @param target 対象文字列
     * @return 空白除去結果文字列
     */
    public static String trim(String target) {
        if (target == null) {
            return null;
        }
        return target.trim().replace("　", "");
    }
}
