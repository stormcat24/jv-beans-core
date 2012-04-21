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

import org.stormcat.jvbeans.common.reflect.ClassUtil;

import com.jacob.com.Variant;

/**
 * Jacob関連のユーティリティです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JacobUtil {

    /**
     * コンストラクタ
     * <p>ユーティリティのためインスタンス生成不可
     */
    private JacobUtil() {
        throw new AssertionError();
    }
    
    /**
     * ネイティヴメソッドに参照渡しするためのオブジェクトを生成します。
     * @param type 型
     * @param parameters コンストラクタ用可変長引数
     * @return {@link Variant}
     */
    public static Variant getRefVariant(Class<?> type, Object ... parameters) {
        return new Variant(ClassUtil.newInstance(type, parameters), true);
    }
    
}
