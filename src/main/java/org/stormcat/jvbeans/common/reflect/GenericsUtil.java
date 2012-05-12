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
package org.stormcat.jvbeans.common.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author a.yamada
 *
 */
public class GenericsUtil {

    protected GenericsUtil() {
        
    }
    
    public static Class<?> getRowClass(Type type) {
        if (type == null) {
            throw new IllegalArgumentException("typeがnullではいけません。");
        }
        if (Class.class.isInstance(type)) {
            return Class.class.cast(type);
        }
        
        if (ParameterizedType.class.isInstance(type)) {
            return getRowClass(ParameterizedType.class.cast(type).getRawType());
        }
        // TODO WildcardType
        // TODO GenericArrayType
        
        return null;
    }
    
}
