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
package org.stormcat.jvbeans.jvlink.util;

import org.junit.Test;
import org.stormcat.jvbeans.util.JvStringUtil;
import static org.junit.Assert.*;

/**
 * @author a.yamada
 *
 */
public class JvStringUtilTest {

    @Test
    public void trimNull() {
        assertNull(JvStringUtil.trim(null));
    }
    
    @Test
    public void trimHalfSpace1() {
        assertEquals("hoge", JvStringUtil.trim("hoge "));
    }
    
    @Test
    public void trimHalfSpace2() {
        assertEquals("hoge", JvStringUtil.trim(" hoge "));
    }
    
    @Test
    public void trimHalfSpace3() {
        assertEquals("hoge", JvStringUtil.trim(" hoge"));
    }
    
    @Test
    public void trimFullSpace1() {
        assertEquals("hoge", JvStringUtil.trim("hoge　"));
    }
    
    @Test
    public void trimFullSpace2() {
        assertEquals("hoge", JvStringUtil.trim("　hoge　"));
    }
    
    @Test
    public void trimFullSpace3() {
        assertEquals("hoge", JvStringUtil.trim("hoge　"));
    }
}
