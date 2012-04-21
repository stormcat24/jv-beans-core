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
package org.stormcat.jvbeans.jvlink.analyze.binder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.stormcat.jvbeans.common.constants.FileExtension;
import org.stormcat.jvbeans.common.reflect.ClassUtil;
import org.stormcat.jvbeans.common.reflect.MethodUtil;
import org.stormcat.jvbeans.exception.JvBeansRuntimeException;
import org.stormcat.jvbeans.jvlink.analyze.JvBindingObjectConverter;
import org.stormcat.jvbeans.jvlink.analyze.JvRecordMeta;

/**
 * 任意のセパレータ文字列で区切られたJV-Dataをバインドするための抽象Binderです。
 * @author a.yamada
 * @since 0.3
 *
 */
public abstract class JvIncludeSeparatorDataBinder extends JvDataBinder {

    /**
     * コンストラクタ
     * @param inputType 入力形式
     */
    protected JvIncludeSeparatorDataBinder(FileExtension inputType) {
        super(inputType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void bindProperty(List<JvRecordMeta> metaItems, T dto, String data) {

        String[] records = data.split(getSeparator());
        
        int index = 0;
        for (JvRecordMeta meta : metaItems) {
            List<JvRecordMeta> joinItems = meta.getJoinMetaItems();
            if (meta.getRepeatCount() == 1) {
                setProperty(removeQuote(records[index++]), meta, dto);
            } else {
                List<Object> list = new ArrayList<Object>();
                for (int i = 0; i < meta.getRepeatCount(); i++) {
                    Object child = null;
                    if (joinItems == null) {
                        child = JvBindingObjectConverter.convert(removeQuote(records[index++]), meta);
                    } else {
                        // Join Dto List
                        child = ClassUtil.newInstance(meta.getType());
                        // 再帰処理
                        int length = getChildRecordLength(joinItems);
                        bindProperty(joinItems, child, construct(records, index, length));
                        index += length;
                    }
                    list.add(child);
                }
                MethodUtil.invoke(meta.getMutator(), dto, null, list);
            }
        }
    }
    
    private int getChildRecordLength(List<JvRecordMeta> joinItems) {
        int length = 0;
        for (JvRecordMeta meta : joinItems) {
            length += meta.getRepeatCount();
        }
        return length;
    }
    
    private String construct(String[] data, int index, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = index; i < index + length; i++) {
            builder.append(data[i]);
            if (i < index + length - 1) {
                builder.append(getSeparator());    
            }
        }
        return builder.toString();
    }
    
    private void setProperty(String data, JvRecordMeta meta, Object target) {
        Object value = JvBindingObjectConverter.convert(data, meta);
        Method mutator = meta.getMutator();
        if (mutator == null) {
            throw new JvBeansRuntimeException("セッターメソッドがありません。");
        }
        MethodUtil.invoke(mutator, target, null, value);        
    }
    
    private String removeQuote(String data) {
        return data.substring(1, data.length() - 1);
    }
    
    /**
     * セパレーター文字列を取得します。
     * <p>このセパレーター文字列によってJV-Dataの各レコードは区切られます。
     * @return セパレーター文字列
     */
    protected abstract String getSeparator();
    

}
