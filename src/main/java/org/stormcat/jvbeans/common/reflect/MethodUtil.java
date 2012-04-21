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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.stormcat.jvbeans.common.exception.IllegalAccessRuntimeException;
import org.stormcat.jvbeans.common.exception.InvocationTargetRuntimeException;
import org.stormcat.jvbeans.common.exception.NoSuchMethodRuntimeException;

/**
 * @author a.yamada
 *
 */
public class MethodUtil {

    protected MethodUtil() {
        
    }
    
    public static Method getMethod(Class<?> target, String methodName, Class<?> ... parameters) {
        try {
            return target.getMethod(methodName, parameters);
        } catch (SecurityException e) {
            throw e;
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodRuntimeException(e);
        }
    }
    
    public static Method getDeclareMethod(Class<?> target, String methodName, Class<?> ... parameters) {
        try {
            return target.getDeclaredMethod(methodName, parameters);
        } catch (SecurityException e) {
            throw e;
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodRuntimeException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T invoke(Method method, Object target, Class<T> returnType, Object ... args) {
        try {
            return (T) method.invoke(target, args);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("methodName = %s", method.getName()), e);
        } catch (IllegalAccessException e) {
            throw new IllegalAccessRuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new InvocationTargetRuntimeException(e);
        }
    }
}
