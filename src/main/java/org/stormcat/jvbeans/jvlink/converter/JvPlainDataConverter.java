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

import org.stormcat.jvbeans.jvlink.analyze.JvBeansContainer;


/**
 * JV-Dataを何も変換せずに返す未処理用コンバーターです。
 * <p>{@link JvDataConverterFactory}において、サポート外の出力形式が指定された場合に
 * 未処理用コンバーターとしてこのクラスのインスタンスを返すために便宜的に用意しています。
 * @author a.yamada
 * @since 0.3
 *
 */
public class JvPlainDataConverter extends JvDataConverter {

    /**
     * コンストラクタ
     */
    protected JvPlainDataConverter() {
        super(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(JvBeansContainer container, String data) {
        return data;
    }


}
