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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.stormcat.jvbeans.common.constants.Charset;
import org.stormcat.jvbeans.common.exception.IORuntimeException;

/**
 * @author a.yamada
 *
 */
public class FileUtil {

    protected FileUtil() {
        
    }
    
    public static void writeStringToFile(File writeFile, String data, Charset charset) {
        String fullPath = writeFile.getAbsolutePath().replace("\\", "/");
        File dir = new File(fullPath.substring(0, fullPath.lastIndexOf("/")));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            FileUtils.writeStringToFile(writeFile, data, charset.getValue());
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }
    
}
