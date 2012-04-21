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

import org.stormcat.jvbeans.common.reflect.ClassUtil;
import org.stormcat.jvbeans.response.JvResult;
import org.stormcat.jvbeans.response.JvSimpleResult;

import com.jacob.com.Variant;

/**
 * {@link JvResult}を生成するためのstaticファクトリです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvResultFactory {

    /**
     * {@link Variant}から{@link JvResult}を生成します。
     * @param variant {@link Variant}
     * @return {@link JvResult}
     */
    public static JvResult createJvResult(Variant variant) {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(variant.getInt());
        variant.safeRelease();
        return result;
    }
    
    /**
     * {@link Variant}から指定した型の{@link JvResult}を生成します。
     * @param <T> 型パラメータ
     * @param variant {@link Variant}
     * @param type {@link Class}
     * @return {@link JvResult}
     */
    public static <T extends JvResult> T createJvResult(Variant variant, Class<T> type) {
        T t = ClassUtil.newInstance(type);
        t.setReturnCode(variant.getInt());
        variant.safeRelease();
        return t;
    }
}
