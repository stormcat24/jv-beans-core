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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.stormcat.jvbeans.common.exception.ClassNotFoundRuntimeException;
import org.stormcat.jvbeans.common.exception.IllegalAccessRuntimeException;
import org.stormcat.jvbeans.common.exception.InstantiationRuntimeException;
import org.stormcat.jvbeans.common.exception.InvocationTargetRuntimeException;
import org.stormcat.jvbeans.common.exception.NoSuchMethodRuntimeException;
import org.stormcat.jvbeans.common.lang.StringUtil;

/**
 * @author a.yamada
 *
 */
public class ClassUtil {

    /**
     * 
     * コンストラクタ
     */
    protected ClassUtil() {
        
    }
    
    public static Class<?> forName(String fqdn) {
        if (StringUtil.isBlank(fqdn)) {
            return null;
        }
        try {
            return Class.forName(fqdn);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundRuntimeException(e);
        }
    }
    
    public static <T> Constructor<T> getConstructor(Class<T> target, Class<?> ... parameterTypes) {
        try {
            return target.getConstructor(parameterTypes);
        } catch (SecurityException e) {
            throw e;
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodRuntimeException(e);
        }
    }
    
    public static <T> Constructor<T> getConstructor(Class<T> target) {
        return getConstructor(target, new Class<?>[]{});
    }
    
    public static <T> T newInstance(Class<T> target, Object ... parameters) {
        Constructor<T> constructor = getConstructor(target, convertTypeArray(parameters));
        try {
            return constructor.newInstance(parameters);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (InstantiationException e) {
            throw new InstantiationRuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalAccessRuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new InvocationTargetRuntimeException(e);
        }
    }
    
    public static <T> T newInstance(Class<T> target) {
        return newInstance(target, new Object[]{});
    }
    
    private static Class<?>[] convertTypeArray(Object[] parameters) {
        Class<?>[] classArray = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            classArray[i] = parameters[i].getClass();
        }
        return classArray;
    }
    
    public static String getPackageName(String fqdn) {
        return fqdn.substring(0, fqdn.lastIndexOf("."));
    }
    
    public static String getClassName(String fqdn) {
        return fqdn.substring(fqdn.lastIndexOf(".") + 1, fqdn.length());
    }
}
