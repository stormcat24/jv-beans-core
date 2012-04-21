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

import java.lang.reflect.Method;
import java.util.List;

/**
 * レコードをメタ情報を保持するためのクラスです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvRecordMeta {

    /** 項目の位置 */
    private int position;
    
    /** バイト長 */
    private int byteLength;
    
    /** 繰り返し回数 */
    private int repeatCount;
    
    /** バインディング型 */
    private Class<?> type;
    
    /** 値をセットするためのsetterメソッド */
    private Method mutator;
    
    /** JOINレコードのメタ情報 */
    private List<JvRecordMeta> joinMetaItems;
    
    /** 値補正 */
    private float correction;
    
    /**
     * 
     * コンストラクタ
     * @param position 項目の位置
     * @param byteLength バイト長
     * @param repeatCount 繰り返し回数
     * @param type バインディング型
     * @param mutator 値をセットするためのsetterメソッド
     * @param correction 値補正
     */
    JvRecordMeta(int position, int byteLength, int repeatCount, 
            Class<?> type, Method mutator, Float correction) {
        this.position = position;
        this.byteLength = byteLength;
        this.repeatCount = repeatCount;
        this.type = type;
        this.mutator = mutator;
        this.correction = correction;
    }
    
    /**
     * 
     * コンストラクタ
     * @param position 項目の位置
     * @param byteLength バイト長
     * @param repeatCount 繰り返し回数
     * @param type バインディング型
     * @param mutator 値をセットするためのsetterメソッド
     * @param correction 値補正
     * @param joinMetaItems JOINレコードのメタ情報
     */
    JvRecordMeta(int position, int byteLength, int repeatCount, 
            Class<?> type, Method mutator, Float correction, List<JvRecordMeta> joinMetaItems) {
        this(position, byteLength, repeatCount, type, mutator, correction);
        this.joinMetaItems = joinMetaItems;
    }

    /**
     * 項目の位置を返します。
     * @return position 項目の位置
     */
    public int getPosition() {
        return position;
    }

    /**
     * バイト長を返します。
     * @return byteLength バイト長
     */
    public int getByteLength() {
        return byteLength;
    }

    /**
     * 繰り返し回数を返します。
     * @return repeatCount 繰り返し回数
     */
    public int getRepeatCount() {
        return repeatCount;
    }

    /**
     * バインディング型を返します。
     * @return type バインディング型
     */
    public Class<?> getType() {
        return type;
    }

    /**
     * 値をセットするためのsetterメソッドを返します。
     * @return mutator 値をセットするためのsetterメソッド
     */
    public Method getMutator() {
        return mutator;
    }
    
    /**
     * 値補正を返します。
     * @return correction 値補正
     */
    public float getCorrection() {
        return correction;
    }

    /**
     * JOINレコードのメタ情報を返します。
     * @return joinMetaItems JOINレコードのメタ情報
     */
    public List<JvRecordMeta> getJoinMetaItems() {
        return joinMetaItems;
    }


}
