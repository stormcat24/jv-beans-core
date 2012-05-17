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

import org.junit.Test;
import org.stormcat.jvbeans.exception.jvlink.JvLinkAuthException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkDownloadFailedException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkFileNotFoundException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkIllegalArgumentException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkIllegalDownloadFileException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkIllegalRegistryException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkInternalException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkNotClosedException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkNotInitializedException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkNotOpenedException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkParameterException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkServerException;
import org.stormcat.jvbeans.exception.jvlink.JvLinkSetupFailedException;
import org.stormcat.jvbeans.response.JvContents;
import org.stormcat.jvbeans.response.JvResult;
import org.stormcat.jvbeans.response.JvSimpleResult;

import static org.junit.Assert.*;
/**
 * @author a.yamada
 *
 */
public class JvLinkExceptionFactoryTest extends JvBeansBaseTest {

    @Test
    public void createNull() {
        try {
            JvLinkExceptionFactory.create(null);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }
    
    @Test
    public void createOk() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(100);
        
        assertNull(JvLinkExceptionFactory.create(result));
    }
    
    @Test
    public void create100() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-100);
        
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkParameterException);
    }

    @Test
    public void create101() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-101);
        
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalArgumentException);
    }
    
    @Test
    public void create102() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-102);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalArgumentException);
    }
    
    @Test
    public void create103() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-103);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalArgumentException);
    }
    
    @Test
    public void create111() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-111);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalArgumentException);
    }
    
    @Test
    public void create112() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-112);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalArgumentException);
    }
    
    @Test
    public void create114() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-114);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalArgumentException);
    }
    
    @Test
    public void create115() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-115);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalArgumentException);
    }
    
    @Test
    public void create116() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-116);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalArgumentException);
    }
    
    @Test
    public void create118() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-118);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalArgumentException);
    }
    
    @Test
    public void create201() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-201);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkNotInitializedException);
    }
    
    @Test
    public void create202() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-202);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkNotClosedException);
    }
    
    @Test
    public void create203() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-203);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkNotOpenedException);
    }
    
    @Test
    public void create211() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-211);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalRegistryException);
    }
    
    @Test
    public void create212() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-212);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalRegistryException);
    }
    
    @Test
    public void create301() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-301);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkAuthException);
    }
    
    @Test
    public void create302() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-302);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkAuthException);
    }
    
    @Test
    public void create303() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-303);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalArgumentException);
    }
    
    @Test
    public void create304() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-304);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkAuthException);
    }
    
    @Test
    public void create401() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-401);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkInternalException);
    }
    
    @Test
    public void create402() {
    	JvContents<JvBindingDto> result = new JvContents<JvBindingDto>();
        result.setReturnCode(-402);
        result.setFileName("hoge.jvd");
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalDownloadFileException);
    }
    
    @Test
    public void create403() {
    	JvContents<JvBindingDto> result = new JvContents<JvBindingDto>();
        result.setReturnCode(-403);
        result.setFileName("hoge.jvd");
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkIllegalDownloadFileException);
    }
    
    @Test
    public void create411() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-411);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkServerException);
    }
    
    @Test
    public void create412() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-412);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkServerException);
    }
    
    @Test
    public void create413() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-413);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkServerException);
    }
    
    @Test
    public void create421() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-421);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkServerException);
    }
    
    @Test
    public void create431() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-431);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkServerException);
    }
    
    @Test
    public void create501() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-501);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkSetupFailedException);
    }
    
    @Test
    public void create502() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-502);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkDownloadFailedException);
    }
    
    @Test
    public void create503() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-503);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkFileNotFoundException);
    }
    
    @Test
    public void create504() {
        JvResult result = new JvSimpleResult();
        result.setReturnCode(-504);
        JvLinkException e = JvLinkExceptionFactory.create(result);
        assertTrue(e instanceof JvLinkServerException);
    }
    
}
