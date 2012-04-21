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
package org.stormcat.jvbeans.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JRA-VANのレコードフォーマットの各プロパティを表現するアノテーションです。
 * <p>
 * @author a.yamada
 * @since 0.1
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface JvProperty {

    /**
     * レコードにおける対象のプロパティの開始位置を返します。
     * @return 開始位置
     */
    int position();
    
    /**
     * レコードにおける対象のプロパティのバイト長を返します。
     * @return プロパティのバイト長
     */
    int byteLength();
    
    /**
     * レコードにおいて対象のプロパティが何回繰り返されるかを返します。
     * @return プロパティの個数
     */
    int repeatCount() default 1;
    
    /**
     * 単位を補正し、正しい値にバインディングするための値です。
     * <p>floatの精度で十分です。
     * @return 補正値
     */
    float correction() default 1f;
    
}
