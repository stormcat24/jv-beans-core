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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.stormcat.jvbeans.common.exception.ParseRuntimeException;

/**
 * @author a.yamada
 *
 */
public class DateUtil {

    protected DateUtil() {
        
    }
    
    public static String formatDate(Date target, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(target);
    }
    
    public static Date parseDate(String target, String format) {
        if (StringUtil.isBlank(target)) {
            return null;
        }
        try {
            return DateUtils.parseDate(target, new String[]{format});
        } catch (ParseException e) {
            throw new ParseRuntimeException(e);
        }
    }
}
