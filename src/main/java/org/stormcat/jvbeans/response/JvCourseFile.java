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
package org.stormcat.jvbeans.response;

/**
 * コース図取得時の結果を格納するクラスです。
 * @author a.yamada
 * @since 0.1
 *
 */
public class JvCourseFile extends JvResult {
    
    /** コース図画像の保存パス */
    private String filePath;

    /** コースの説明 */
    private String explanation;

    
    /**
     * コース図画像の保存パスを返します。
     * @return filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * コース図画像の保存パスをセットします。
     * @param filePath コース図画像の保存パス
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * コースの説明を返します。
     * @return explanation コースの説明
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * コースの説明をセットします。
     * @param explanation コースの説明
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    
    
}
