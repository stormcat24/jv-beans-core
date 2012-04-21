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

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.stormcat.jvbeans.common.constants.Charset;
import org.stormcat.jvbeans.common.constants.FileExtension;
import org.stormcat.jvbeans.common.lang.StringUtil;
import org.stormcat.jvbeans.jvlink.analyze.JvBeansContainer;
import org.stormcat.jvbeans.jvlink.analyze.JvRecordMeta;
import org.stormcat.jvbeans.util.JvStringUtil;

/**
 * JV-Dataを任意のセパレータ文字列で区切って変換するための抽象コンバーターです。
 * @author a.yamada
 * @since 0.3
 *
 */
public abstract class JvIncludeSeparatorDataConvertor extends JvDataConverter {

    /**
     * コンストラクタ
     * @param outputType 出力形式
     */
    protected JvIncludeSeparatorDataConvertor(FileExtension outputType) {
        super(outputType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(JvBeansContainer container, String data) {
        if (StringUtils.isBlank(data)) {
            return "";
        }
        
        if (data.length() < 2) {
            return data;
        }
        
        List<JvRecordMeta> metaItems = container.getRecordMetaItems(data.substring(0, 2));
        
        StringBuilder builder = new StringBuilder();
        appendString(builder, data, metaItems);
        builder.deleteCharAt(builder.length() - 1); // 最後のセパレーター文字を除去する
        return builder.toString();
    }
    
    private void appendString(StringBuilder builder, String data, List<JvRecordMeta> metaItems) {
        int position = 0;
        byte[] bytes = StringUtil.getBytes(data, Charset.MS932);
        for (JvRecordMeta meta : metaItems) {
            int byteLength = meta.getByteLength();
            int repeatCount = meta.getRepeatCount();
            for (int i = 1; i <= repeatCount; i++) {
                byte[] parts = ArrayUtils.subarray(bytes, position, position + byteLength);
                String rawData = StringUtil.getString(parts, Charset.MS932);
                List<JvRecordMeta> joinItems = meta.getJoinMetaItems();
                if (joinItems != null && !joinItems.isEmpty()) {
                    appendString(builder, rawData, joinItems);
                } else {
                    builder.append("\"");
                    builder.append(JvStringUtil.trim(rawData));
                    builder.append("\"");
                    builder.append(getSeparator());
                }
                position += byteLength;
            }
        }
    }

    /**
     * セパレーター文字列を取得します。
     * <p>このセパレーター文字列によってJV-Dataの各レコードは区切られます。
     * @return セパレーター文字列
     */
    protected abstract String getSeparator();

}
