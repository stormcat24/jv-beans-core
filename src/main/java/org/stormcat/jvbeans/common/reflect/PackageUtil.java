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

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.stormcat.jvbeans.common.lang.StringUtil;

/**
 * @author a.yamada
 *
 */
public class PackageUtil {

    protected PackageUtil() {
        
    }
    
    /**
     * 対象のパッケージ配下の{@link Class}のリストを取得します。
     * @param packageName パッケージ名称
     * @return Classのリスト
     */
    public static List<Class<?>> getClassList(String packageName) {
        if (StringUtil.isBlank(packageName)) {
            throw new IllegalArgumentException("指定パッケージ名が空です。");
        }
        
        String dirName = packageName.replace(".", "/");
        
        List<Class<?>> classList = new ArrayList<Class<?>>();
        URL url = Thread.currentThread().getContextClassLoader().getResource(dirName);
        if (url == null) {
            return classList;
        }
        
        File packageDir = new File(url.getFile());
        if (packageDir.exists()) {
            for (File f : packageDir.listFiles()) {
                if (f.isDirectory()) {
                    classList.addAll(getClassList(String.format("%s/%s", dirName, f.getName())));
                } else {
                    if (f.getName().endsWith(".class") && !f.getName().contains("$")) {
                        classList.add(createClass(dirName, f.getName()));
                    }
                }
            }
        }
        return classList;
    }
    
    public static List<Class<?>> getClassList(JarFile jarFile, String packageName) {
        Enumeration<JarEntry> e = jarFile.entries();
        
        String rootDir = packageName.replace(".", "/");
        
        List<Class<?>> classList = new ArrayList<Class<?>>();
        while (e.hasMoreElements()) {
            JarEntry entry = e.nextElement();
            String fullName = entry.getName();
            if (fullName.startsWith(rootDir) && fullName.endsWith(".class")) {
                classList.add(ClassUtil.forName(fullName.substring(0, fullName.length() - 6).replace("/", ".")));
            }
        }
        
        return classList;
    }
    
    private static Class<?> createClass(String dirName, String className) {
        String fqdn = String.format("%s.%s", dirName.replace("/", "."), className.substring(0, className.length() - 6));
        return ClassUtil.forName(fqdn);
    }
}
