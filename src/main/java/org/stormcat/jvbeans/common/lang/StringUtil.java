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

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.stormcat.jvbeans.common.constants.Charset;
import org.stormcat.jvbeans.common.exception.IORuntimeException;

/**
 * @author a.yamada
 *
 */
public class StringUtil extends StringUtils {

    protected StringUtil() {
        
    }
    
    public static int getBytesLength(String target) {
        if (target == null) {
            return 0;
        }

        return target.getBytes().length;
    }
    
    public static int getBytesLength(String target, Charset charset) {
        if (target == null) {
            return 0;
        }
        try {
            return target.getBytes(charset.getValue()).length;
        } catch (UnsupportedEncodingException e) {
            throw new IORuntimeException(e);
        }
    }
    
    public static byte[] getBytes(String target, Charset charset) {
        try {
            return target.getBytes(charset.getValue());
        } catch (UnsupportedEncodingException e) {
            throw new IORuntimeException(e);
        }
    }
    
    public static String getString(byte[] target, Charset charset) {
        try {
            return new String(target, charset.getValue());
        } catch (UnsupportedEncodingException e) {
            throw new IORuntimeException(e);
        }
    }
    
    public static String toZenkuakuNum(int target) {
        StringBuilder builder = new StringBuilder();
        String s = String.valueOf(target);
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            builder.append((char)(array[i] - '0' + '０'));
        }
        
        return builder.toString();
    }
}
