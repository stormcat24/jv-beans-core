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

import org.apache.commons.lang.ArrayUtils;
import org.stormcat.jvbeans.common.constants.Charset;
import org.stormcat.jvbeans.common.lang.StringUtil;
import org.stormcat.jvbeans.common.reflect.ClassUtil;
import org.stormcat.jvbeans.common.reflect.MethodUtil;
import org.stormcat.jvbeans.exception.JvBeansRuntimeException;
import org.stormcat.jvbeans.jvlink.analyze.JvBindingObjectConverter;
import org.stormcat.jvbeans.jvlink.analyze.JvRecordMeta;
import org.stormcat.jvbeans.util.JvStringUtil;

/**
 * 通常のJV-DataをDTOに変換するためのBinderです。
 * @author a.yamada
 * @since 0.3
 *
 */
public class JvStandardDataBinder extends JvDataBinder {

    /**
     * コンストラクタ
     */
    public JvStandardDataBinder() {
        super(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void bindProperty(List<JvRecordMeta> metaItems, T dto, String data) {
        byte[] bt = StringUtil.getBytes(data, Charset.MS932);
        bindProperty(metaItems, dto, bt);
    }
    
    private <T> void bindProperty(List<JvRecordMeta> metaItems, T dto, byte[] bt) {
        for (JvRecordMeta meta : metaItems) {
            byte[] parts = null;
            List<JvRecordMeta> joinItems = meta.getJoinMetaItems();
            if (meta.getRepeatCount() == 1) {
                // List以外
                parts = subarray(bt, meta.getPosition(), meta.getByteLength());
                setProperty(parts, meta, dto);
            } else {
                List<Object> list = new ArrayList<Object>();
                for (int i = 0; i < meta.getRepeatCount(); i++) {
                    Object child = null;
                    parts = subarray(bt, i * meta.getByteLength() + meta.getPosition(), meta.getByteLength());
                    if (joinItems == null) {
                        // 単純List
                        child = convert(parts, meta);
                    } else {
                        // Join Dto List
                        child = ClassUtil.newInstance(meta.getType());
                        // 再帰処理
                        bindProperty(joinItems, child, parts);
                    }
                    list.add(child);
                }
                MethodUtil.invoke(meta.getMutator(), dto, null, list);
            }
        }

    }
    
    private Object convert(byte[] data, JvRecordMeta meta) {
        String rawData = JvStringUtil.trim(StringUtil.getString(data, Charset.MS932));
        return JvBindingObjectConverter.convert(rawData, meta);
    }
    
    private void setProperty(byte[] data, JvRecordMeta meta, Object target) {
        Object value = convert(data, meta);
        Method mutator = meta.getMutator();
        if (mutator == null) {
            throw new JvBeansRuntimeException("セッターメソッドがありません。");
        }
        MethodUtil.invoke(mutator, target, null, value);        
    }
    
    private byte[] subarray(byte[] data, int position, int byteLength) {
        return ArrayUtils.subarray(data, position - 1, position + byteLength - 1);
    }

}
