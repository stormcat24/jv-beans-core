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
package org.stormcat.jvbeans.jvlink.converter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.stormcat.jvbeans.common.constants.FileExtension;

/**
 * @author a.yamada
 *
 */
public class JvDataConverterFactoryTest {

    @Test
    public void createConvertor1() {
        assertTrue(JvDataConverterFactory.createConvertor(FileExtension.CSV) instanceof JvCsvDataConverter);
    }

    @Test
    public void createConvertor2() {
        assertTrue(JvDataConverterFactory.createConvertor(FileExtension.TSV) instanceof JvTsvDataConverter);
    }

    @Test
    public void createConvertor3() {
        assertTrue(JvDataConverterFactory.createConvertor(FileExtension.TXT) instanceof JvPlainDataConverter);
    }

    @Test
    public void createConvertor4() {
        assertTrue(JvDataConverterFactory.createConvertor(null) instanceof JvPlainDataConverter);
    }

}
