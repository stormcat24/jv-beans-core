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
package org.stormcat.jvbeans.common.io;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.stormcat.jvbeans.common.constants.Charset;
import org.stormcat.jvbeans.common.exception.IORuntimeException;

/**
 * @author a.yamada
 *
 */
public class OutputStreamWriterUtil {

    protected OutputStreamWriterUtil() {
        
    }
    
    public static OutputStreamWriter getWriter(OutputStream os, Charset charset) {
        try {
            return new OutputStreamWriter(os, charset.getValue());
        } catch (UnsupportedEncodingException e) {
            throw new IORuntimeException(e);
        }
    }
    
}
