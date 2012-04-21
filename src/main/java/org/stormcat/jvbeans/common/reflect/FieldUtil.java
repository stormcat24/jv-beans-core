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

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.stormcat.jvbeans.common.exception.IllegalAccessRuntimeException;
import org.stormcat.jvbeans.common.exception.NoSuchFieldRuntimeException;


/**
 * @author a.yamada
 *
 */
public class FieldUtil {

    protected FieldUtil() {
        
    }
    
    public static Field getField(String fieldName, Class<?> target) {
        try {
            return target.getField(fieldName);
        } catch (SecurityException e) {
            throw e;
        } catch (NoSuchFieldException e) {
            throw new NoSuchFieldRuntimeException(e);
        }
    }
    
    public static Object getValue(Field field, Object obj) {
        try {
            field.setAccessible(true);
            return field.get(obj);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw new IllegalAccessRuntimeException(e);
        }
    }
    
    public static Method getGetterMethod(Field field) {
        String fieldName = field.getName();
        StringBuilder builder = new StringBuilder("get");
        builder.append(fieldName.substring(0, 1).toUpperCase());
        builder.append(fieldName.subSequence(1, fieldName.length()));
        return MethodUtil.getDeclareMethod(field.getDeclaringClass(), builder.toString());
    }
    
    public static Method getSetterMethod(Field field) {
        String fieldName = field.getName();
        StringBuilder builder = new StringBuilder("set");
        builder.append(fieldName.substring(0, 1).toUpperCase());
        builder.append(fieldName.subSequence(1, fieldName.length()));
        return MethodUtil.getDeclareMethod(field.getDeclaringClass(), builder.toString(), field.getType());
    }
    
}
