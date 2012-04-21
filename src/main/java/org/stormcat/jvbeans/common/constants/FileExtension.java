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
package org.stormcat.jvbeans.common.constants;

/**
 * ファイル拡張子を定義するEnumです。
 * @author a.yamada
 *
 */
public enum FileExtension implements SingleValueEnum<String> {

    TXT("txt"),

    CSV("csv"),

    TSV("tsv");

    private String extension;

    private String regularExpression;

    private FileExtension(String extension) {
        this.extension = extension;
        this.regularExpression = String.format("^.*\\.(%s|%s)$", extension.toUpperCase(), extension);
    }

    /**
     * {@inheritDoc}
     */
    public String getValue() {
        return extension;
    }

    /**
     * 拡張子の正規表現を返します。
     * @return 正規表現
     */
    public String getRegularExpression() {
        return regularExpression;
    }

    /**
     * 任意の文字列が対象の拡張子に適合しているかを判定します。
     * <p>文字列が{@code null}の場合は{@code false}を返します。
     * @param target 拡張子検査用文字列
     * @return 拡張子適合結果
     */
    public boolean matchesExtension(String target) {
        if (target == null) {
            return false;
        }
        return target.matches(regularExpression);
    }
}
