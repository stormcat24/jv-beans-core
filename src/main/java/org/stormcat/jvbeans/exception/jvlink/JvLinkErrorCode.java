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
package org.stormcat.jvbeans.exception.jvlink;

import org.stormcat.jvbeans.common.constants.PairValueEnum;
import org.stormcat.jvbeans.response.JvResult;


/**
 * JVLinkが返すエラーコードの定義です。
 * @author a.yamada
 * @since 0.1
 *
 */
public enum JvLinkErrorCode implements PairValueEnum<Integer> {

    /** -100:パラメータが不正あるいはレジストリへの保存に失敗 */
    _100("パラメータが不正あるいはレジストリへの保存に失敗", -100),
    /** -101:sidが設定されていない */
    _101("sidが設定されていない", -101),
    /** -102:sidが64byteを超えている */
    _102("sidが64byteを超えている", -102),
    /** -103:sidが不正（sidの１桁目がスペース） */
    _103("sidが不正（sidの１桁目がスペース）", -103),
    
    /** -111:dataspec もしくは movietypeパラメータが不正 */
    _111("dataspec もしくは movietypeパラメータが不正", -111),
    /** -112:fromtimeパラメータが不正 */
    _112("fromtimeパラメータが不正", -112),
    /** -114:key もしくは searchkeyパラメータが不正 */
    _114("key もしくは searchkeyパラメータが不正", -114),
    /** -115:optionパラメータが不正 */
    _115("optionパラメータが不正", -115),
    /** -116:dataspecとoptionの組み合わせが不正 */
    _116("dataspecとoptionの組み合わせが不正", -116),
    /** -118:filepathパラメータが不正 */
    _118("filepathパラメータが不正", -118),
    
    /** -201:JVInitなわれていない */
    _201("JVInitなわれていない", -201),
    /** -202:前回のJVOpen/JVRTOpen/JVMVOpenに対してJVCloseが呼ばれていない（オープン中） */
    _202("前回のJVOpen/JVRTOpen/JVMVOpenに対してJVCloseが呼ばれていない（オープン中）", -202),
    /** -203:JVOpen/JVMVOpenが行なわれていない */
    _203("JVOpen/JVMVOpenが行なわれていない", -203),
    /** -211:レジストリ内容が不正（レジストリ内容が不正に変更された） */
    _211("レジストリ内容が不正（レジストリ内容が不正に変更された）", -211),
    /** -212:レジストリ内容が不正（レジストリ内容が不正に変更された） */
    _212("レジストリ内容が不正（レジストリ内容が不正に変更された）", -212),
    
    /** -301:認証エラー */
    _301("認証エラー", -301),
    /** -302:サービスキーの有効期限切れ */
    _302("サービスキーの有効期限切れ", -302),
    /** -303:サービスキーが設定されていない（サービスキーが空値） */
    _303("サービスキーが設定されていない（サービスキーが空値）", -303),
    /** 304):JRAレーシングビュアー連携機能認証エラー" */
    _304("JRAレーシングビュアー連携機能認証エラー", -304), 
    
    /** -401:JV-Link内部エラー */
    _401("JV-Link内部エラー", -401),
    /** -402:ダウンロードしたファイルが異常（ファイルサイズ=0） */
    _402("ダウンロードしたファイルが異常（ファイルサイズ=0）", -402),
    /** -403:ダウンロードしたファイルが異常（データ内容） */
    _403("ダウンロードしたファイルが異常（データ内容）", -403),
    /** -411:サーバーエラー（HTTPステータス404 NotFound） */
    _411("サーバーエラー（HTTPステータス404 NotFound）", -411),
    /** -412:サーバーエラー（HTTPステータス403 Forbidden） */
    _412("サーバーエラー（HTTPステータス403 Forbidden）", -412),
    /** -413:サーバーエラー（HTTPステータス200,403,404以外） */
    _413("サーバーエラー（HTTPステータス200,403,404以外）", -413),
    /** -421:サーバーエラー（サーバーの応答が不正） */
    _421("サーバーエラー（サーバーの応答が不正）", -421),
    /** -431:サーバーエラー（サーバーアプリケーション内部エラー） */
    _431("サーバーエラー（サーバーアプリケーション内部エラー）", -431),
    
    /** -501:セットアップ処理においてCD-ROMが無効 */
    _501("セットアップ処理においてCD-ROMが無効", -501),
    /** -502:ダウンロード失敗（通信エラーやディスクエラーなど） */
    _502("ダウンロード失敗（通信エラーやディスクエラーなど）", -502),
    /** -503:ファイルが見つからない */
    _503("ファイルが見つからない", -503),
    /** -504:サーバーメンテナンス中 */
    _504("サーバーメンテナンス中", -504),
    
    ;
    
    /** メッセージ */
    private String message;
    
    /** エラーコード */
    private Integer errorCode;
    
    /**
     * コンストラクタ
     * @param message メッセージ
     * @param errorCode エラーコード
     */
    private JvLinkErrorCode(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return message;
    }

    /**
     * {@inheritDoc}
     */
    public Integer getValue() {
        return errorCode;
    }
    
    /**
     * {@link JvResult}が同じエラーコードを保持しているかを判定します。
     * @param result 結果オブジェクト
     * @return 同じエラーコードの有無
     */
    public boolean isSameError(JvResult result) {
        return errorCode.intValue() == result.getReturnCode();
    }
}
