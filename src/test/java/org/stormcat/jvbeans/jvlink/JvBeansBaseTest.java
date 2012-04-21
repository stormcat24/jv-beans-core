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
package org.stormcat.jvbeans.jvlink;

import java.io.BufferedReader;
import java.io.File;

import org.stormcat.jvbeans.common.constants.Charset;
import org.stormcat.jvbeans.common.io.BufferedReaderUtil;

/**
 * @author a.yamada
 *
 */
public abstract class JvBeansBaseTest {

    public BufferedReader readTestRecource(String resourceFileName) {
        
        String packageName = this.getClass().getPackage().getName();
        
        File file = new File(String.format("src/test/resources/%s/%s", packageName.replace(".", "/"), resourceFileName));
        if (!file.exists()) {
            throw new RuntimeException(String.format("%s が存在しません。", file.getAbsolutePath()));
        }
        
        return BufferedReaderUtil.getReader(file, Charset.MS932);
    }
    
}
