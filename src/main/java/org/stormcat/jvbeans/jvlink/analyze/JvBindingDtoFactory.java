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
package org.stormcat.jvbeans.jvlink.analyze;

import org.stormcat.jvbeans.common.constants.FileExtension;
import org.stormcat.jvbeans.common.lang.EnumUtil;
import org.stormcat.jvbeans.common.lang.StringUtil;
import org.stormcat.jvbeans.common.reflect.ClassUtil;
import org.stormcat.jvbeans.config.RecordTypeId;
import org.stormcat.jvbeans.config.condition.OpenCondition;
import org.stormcat.jvbeans.exception.JvBeansRuntimeException;
import org.stormcat.jvbeans.jvlink.JvBindingDto;
import org.stormcat.jvbeans.jvlink.analyze.binder.JvDataBinder;
import org.stormcat.jvbeans.jvlink.analyze.binder.JvDataBinderFactory;


/**
 * JV-Dataの文字列からJavaBeansへのバインディングを行うファクトリです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvBindingDtoFactory {
    
    private JvBeansContainer jvBeansContainer;
    
    private FileExtension inputType;
    
    /**
     * 
     * コンストラクタ
     */
    public JvBindingDtoFactory() { }
    
    /**
     * JV-Dataの1レコードをDTOにバインディングしたものを返します。
     * <p>取得した{@link JvBindingDto}は利用側でキャストする必要があります。
     * @param str JV-Data文字列
     * @return バインディングDTO
     * @throws IllegalArgumentException レコードが{@code null}の場合
     * @throws JvBeansRuntimeException レコードからレコード種別IDが取得できなかった場合
     * @throws IllegalStateException JvBeansContainerがプロパティとして設定されていない場合
     * @deprecated
     */
    public JvBindingDto create(String str) {
        if (StringUtil.isBlank(str)) {
            throw new IllegalArgumentException("文字列が空です");
        }

        RecordTypeId recordTypeId = EnumUtil.getEnum(jvBeansContainer.getRecordTypeIdClass(), str.substring(0, 2));
        if (recordTypeId == null) {
            throw new JvBeansRuntimeException("レコード種別IDを取得できません。");
        }
        
        if (jvBeansContainer == null) {
            throw new IllegalStateException("JVBindingDtoFactoryのプロパティ(jvBeansContainer)がnullになっています。");
        }
        
        JvBindingDto dto = JvBindingDto.class.cast(ClassUtil.newInstance(jvBeansContainer.getMappedType(recordTypeId)));
        return createDto(str, recordTypeId, dto);
    }

    /**
     * JV-Dataの1レコードを指定したDTOにバインディングしたものを返します。
     * @param str JV-Data文字列
     * @param recordTypeId バインディング型
     * @return バインディングDTO
     * @throws IllegalArgumentException レコードが{@code null}の場合
     * @throws JvBeansRuntimeException レコードからレコード種別IDが取得できなかった場合
     * @throws IllegalStateException JvBeansContainerがプロパティとして設定されていない場合
     */
    public <T extends JvBindingDto> T create(String str, OpenCondition<T> condition) {
        if (StringUtil.isBlank(str)) {
            throw new IllegalArgumentException("文字列が空です");
        }

        if (condition == null) {
            throw new JvBeansRuntimeException("レコード種別IDを取得できません。");
        }
        
        
        if (jvBeansContainer == null) {
            throw new IllegalStateException("JVBindingDtoFactoryのプロパティ(jvBeansContainer)がnullになっています。");
        }
        
        RecordTypeId recordTypeId = condition.getRecordTypeId();
        
        T dto = ClassUtil.newInstance(condition.getType());
        return createDto(str, recordTypeId, dto);
    }
    
    private <T extends JvBindingDto> T createDto(String str, RecordTypeId recordTypeId, T dto) {
        JvDataBinder binder = JvDataBinderFactory.createBinder(inputType);
        binder.bindProperty(jvBeansContainer.getRecordMetaItems(recordTypeId.toString()), dto, str);
        dto.setLine(str);
        return dto;
    }
    
    /**
     * {@link JvBeansContainer}をセットします。
     * @param jvBeansContainer {@link JvBeansContainer}
     */
    public void setJvBeansContainer(JvBeansContainer jvBeansContainer) {
        this.jvBeansContainer = jvBeansContainer;
    }
    
    /**
     * 入力形式
     * @param inputType 入力形式
     */
    public void setInputType(FileExtension inputType) {
        this.inputType = inputType;
    }

}
