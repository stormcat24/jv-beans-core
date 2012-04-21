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
package org.stormcat.jvbeans.common.lang;

import java.lang.reflect.Field;

import org.stormcat.jvbeans.common.reflect.FieldUtil;

/**
 * @author a.yamada
 *
 */
public class EnumUtil {

    protected EnumUtil() {
        
    }
    
    // <E extends Enum<E>>ではないのは、enumがinterfaceを実装した場合不都合なので
    @SuppressWarnings("unchecked")
    public static <E> E getEnum(Class<E> enumType, String enumKey) {
        Field field = FieldUtil.getField(enumKey, enumType);
        return (E) FieldUtil.getValue(field, null);
    }
    
}
