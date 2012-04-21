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
 * JRA-VANの各レコードをバインディングするBeanであることを示すアノテーションです。
 * <p>Join対象の子Dtoに付与する必要はありません。
 * @author a.yamada
 * @since 0.1
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface JvBean {

    /**
     * どのレコード種別IDに対応しているかを返します。
     * @return レコード種別ID
     */
    String recordTypeId();

    /**
     * 対象のレコードフォーマットのレコード長({@code byte})を返します。
     * @return レコード長
     */
    int byteLength();

}
