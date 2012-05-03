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
package org.stormcat.jvbeans.jvlink;

import org.stormcat.jvbeans.response.JvContents;
import org.stormcat.jvbeans.response.JvCourseFile;
import org.stormcat.jvbeans.response.JvMvContents;
import org.stormcat.jvbeans.response.JvOpen;
import org.stormcat.jvbeans.response.JvResult;

/**
 * JV-Link APIをJavaで使用できるようにするWrapperを提供するためのインタフェースです。
 * <p>これらのメソッドは入力チェック、型変換等の処理を内封した{@link JvLinkManager}経由で使用されます。
 * @author a.yamada
 * @since 0.1
 */
public interface JvLinkWrapper {

    /**
     * {@code Long JVInit(String sid)}のJavaによるWrapperです。
     * @param sid UserAgent文字列
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -101}<td>SIDが設定されていない
     *   <tr><td>{@code -102}<td>SIDが{@code 64byte}を超えている
     *   <tr><td>{@code -103}<td>SIDが不正（SIDの1桁目がスペース）
     * </table>
     */
    JvResult jvInit(String sid);
    
    /**
     * {@code Long JVSetUIProperties()}のJavaによるWrapperです。
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常（キャンセルボタンが押された場合も含む）
     *   <tr><td>{@code -100}<td>SIDが設定されていない
     * </table>
     */
    JvResult jvSetUIProperties();
    
    /**
     * {@code Long JVSetServiceKey(String servicekey)}のJavaによるWrapperです。
     * @param serviceKey 認証に使用するサービスキー文字列
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -100}<td>パラメータが不正あるいはレジストリへの保存に失敗
     *   <tr><td>{@code -101}<td>既にサービスキーが登録されている
     * </table>
     */
    JvResult jvSetServiceKey(String serviceKey);
    
    /**
     * {@code Long JVSetSaveFlag(Long saveflag)}のJavaによるWrapperです。
     * @param saveFlag 1:保存する / 0:保存しない
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -100}<td>{@code saveFlag}の値が不正、またはエラー発生
     * </table>
     */
    JvResult jvSetSaveFlag(long saveFlag);
    
    /**
     * {@code Long JVSetSavePath(String savepath)}のJavaによるWrapperです。
     * @param savePath ダウンロードするファイルを保存するパス
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -100}<td>{@code savePath}の値が不正、またはエラー発生
     * </table>
     */
    JvResult jvSetSavePath(String savePath);
    
    /**
     * {@code Long JVOpen(String dataspec, String fromtime, Long option, Long readcount, Long downloadcount, String lastfiletimestamp)}の
     * JavaによるWrapperです。
     * @param dataSpec データ種別ID
     * @param fromTime データ読み出し開始時間({@code yyyyMMddHHmmss}形式
     * @param option データオプション
     * <table>
     *   <th>option<th>種別
     *   <tr><td>{@code 1}<td>通常データ
     *   <tr><td>{@code 2}<td>今週データ
     *   <tr><td>{@code 3}<td>セットアップデータ
     *   <tr><td>{@code 4}<td>ダイアログ無しセットアップデータ
     *   <tr><td>上記以外<td>エラー
     * </table>
     * @return JVOpen結果オブジェクト
     * <p>リターンコード（{@link JvOpen#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -1}<td>該当データ無し
     *   <tr><td>{@code -2}<td>セットアップダイアログでキャンセルが押された
     *   <tr><td>{@code -111}<td>{@code dataspec}パラメータが不正
     *   <tr><td>{@code -112}<td>{@code fromtime}パラメータが不正
     *   <tr><td>{@code -114}<td>{@code key}パラメータが不正
     *   <tr><td>{@code -115}<td>{@code option}パラメータが不正
     *   <tr><td>{@code -116}<td>{@code dataspec}と{@code option}の組み合わせが不正
     *   <tr><td>{@code -201}<td>JVInitが行なわれていない
     *   <tr><td>{@code -202}<td>前回のJVOpen/JVRTOpen/JVMVOpenに対してJVCloseが呼ばれていない（オープン中）
     *   <tr><td>{@code -211}<td>レジストリ内容が不正（レジストリ内容が不正に変更された）
     *   <tr><td>{@code -301}<td>認証エラー
     *   <tr><td>{@code -302}<td>サービスキーの有効期限切れ
     *   <tr><td>{@code -303}<td>サービスキーが設定されていない（サービスキーが空値）
     *   <tr><td>{@code -401}<td>JV-Link内部エラー
     *   <tr><td>{@code -411}<td>サーバーエラー（HTTPステータス404 NotFound）
     *   <tr><td>{@code -412}<td>サーバーエラー（HTTPステータス403 Forbidden）
     *   <tr><td>{@code -413}<td>サーバーエラー（HTTPステータス200,403,404以外）
     *   <tr><td>{@code -421}<td>サーバーエラー（サーバーの応答が不正）
     *   <tr><td>{@code -501}<td>セットアップ処理においてCD-ROMが無効
     *   <tr><td>{@code -504}<td>サーバーメンテナンス中
     * </table>
     */
    JvOpen jvOpen(String dataSpec, String fromTime, long option);
    
    /**
     * {@code Long JVRTOpen(String dataspec, String key)}のJavaによるWrapperです。
     * @param dataSpec データ種別ID
     * @param key 該当データを取得するための要求キー
     * <p>
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>提供単位<th>key<th>説明
     *   <tr><td>レース毎<td>{@code YYYYMMDDJJKKHHRR}<br>または<br>{@code YYYYMMDDJJRR}<td>{@code MM}:開催月<br>
     *   {@code DD}:開催日<br>{@code JJ}:場コード<br>{@code KK}:回次<br>{@code HH}:日次<br>{@code RR}:レース番号
     *   <tr><td>開催日単位<td>{@code YYYYMMDD}<td>{@code YYYY}:開催年<br>{@code MM}:開催月<br>{@code DD}:開催日
     * </table>
     * @return JVOpen結果オブジェクト
     * <p>リターンコード（{@link JvOpen#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -1}<td>該当データ無し
     *   <tr><td>{@code -2}<td>セットアップダイアログでキャンセルが押された
     *   <tr><td>{@code -111}<td>{@code dataspec}パラメータが不正
     *   <tr><td>{@code -112}<td>{@code fromtime}パラメータが不正
     *   <tr><td>{@code -114}<td>{@code key}パラメータが不正
     *   <tr><td>{@code -115}<td>{@code option}パラメータが不正
     *   <tr><td>{@code -116}<td>{@code dataspec}と{@code option}の組み合わせが不正
     *   <tr><td>{@code -201}<td>{@code JVInit}が行なわれていない
     *   <tr><td>{@code -202}<td>前回のJVOpen/JVRTOpen/JVMVOpenに対してJVCloseが呼ばれていない（オープン中）
     *   <tr><td>{@code -211}<td>レジストリ内容が不正（レジストリ内容が不正に変更された）
     *   <tr><td>{@code -301}<td>認証エラー
     *   <tr><td>{@code -302}<td>サービスキーの有効期限切れ
     *   <tr><td>{@code -303}<td>サービスキーが設定されていない（サービスキーが空値）
     *   <tr><td>{@code -401}<td>JV-Link内部エラー
     *   <tr><td>{@code -411}<td>サーバーエラー（HTTPステータス404 NotFound）
     *   <tr><td>{@code -412}<td>サーバーエラー（HTTPステータス403 Forbidden）
     *   <tr><td>{@code -413}<td>サーバーエラー（HTTPステータス200,403,404以外）
     *   <tr><td>{@code -421}<td>サーバーエラー（サーバーの応答が不正）
     *   <tr><td>{@code -501}<td>セットアップ処理においてCD-ROMが無効
     *   <tr><td>{@code -504}<td>サーバーメンテナンス中
     * </table>
     */
    JvOpen jvRtOpen(String dataSpec, String key);
    
    /**
     * {@code Long JVStatus()}のJavaによるWrapperです。
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}以上<td>正常（ダウンロード済みファイル数）
     *   <tr><td>{@code -201}<td>JVInitが行なわれていない
     *   <tr><td>{@code -203}<td>JVOpenが行なわれていない
     *   <tr><td>{@code -502}<td>ダウンロード失敗（通信エラーやディスクエラーなど）
     * </table>
     */
    JvResult jvStatus();
    
    /**
     * {@code Long JVRead(String buff, Long size, String filename)}のJavaによるWrapperです。
     * @param size 文字列バッファにコピーするデータの長さ
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvContents#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}以上<td>正常（バッファにセットしたデータのサイズ）
     *   <tr><td>{@code 0}<td>全ファイル読み込み終了({@code EOF})
     *   <tr><td>{@code -1}<td>ファイル切り替わり
     *   <tr><td>{@code -3}<td>ファイルダウンロード中
     *   <tr><td>{@code -201}<td>JVInitが行なわれていない
     *   <tr><td>{@code -202}<td>前回のJVOpen/JVRTOpen/JVMVOpenに対してJVCloseが呼ばれていない（オープン中）
     *   <tr><td>{@code -203}<td>JVOpenが行なわれていない
     *   <tr><td>{@code -402}<td>ダウンロードしたファイルが異常（ファイルサイズ={@code 0}）
     *   <tr><td>{@code -403}<td>ダウンロードしたファイルが異常（データ内容）
     *   <tr><td>{@code -502}<td>ダウンロード失敗（通信エラーやディスクエラーなど）
     *   <tr><td>{@code -503}<td>ファイルが見つからない
     * </table>
     */
    JvContents<? extends JvBindingDto> jvRead(long size);
    
    /**
     * {@code void JVSkip()}のJavaによるWrapperです。
     */
    void jvSkip();
    
    /**
     * {@code void JVCancel()}のJavaによるWrapperです。
     */
    void jvCancel();
    
    /**
     * {@code Long JVClose()}のJavaによるWrapperです。
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -1}<td>エラー発生
     * </table>
     */
    JvResult jvClose();
    
    /**
     * {@code Long JVFileDelete(String filename)}のJavaによるWrapperです。
     * @param fileName 削除対象のファイル名
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -1}<td>エラー発生
     * </table>
     */
    JvResult jvFileDelete(String fileName);
    
    /**
     * {@code Long JVFukuFile(String pattern, String filepath)}のJavaによるWrapperです。
     * @param pattern 勝負服の色・模様を示す服色標示
     * @param filePath 勝負服ファイルの出力ファイル名（フルパス指定）
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -1}<td>該当データ無し
     *   <tr><td>{@code -118}<td>{@code filepath}パラメータが不正
     *   <tr><td>{@code -201}<td>JVInitが行なわれていない
     *   <tr><td>{@code -211}<td>レジストリ内容が不正（レジストリ内容が不正に変更された）
     *   <tr><td>{@code -301}<td>認証エラー
     *   <tr><td>{@code -302}<td>サービスキーの有効期限切れ
     *   <tr><td>{@code -303}<td>サービスキーが設定されていない（サービスキーが空値）
     *   <tr><td>{@code -401}<td>JV-Link内部エラー
     *   <tr><td>{@code -411}<td>サーバーエラー（HTTPステータス404 NotFound）
     *   <tr><td>{@code -412}<td>サーバーエラー（HTTPステータス403 Forbidden）
     *   <tr><td>{@code -413}<td>サーバーエラー（HTTPステータス200,403,404以外）
     *   <tr><td>{@code -421}<td>サーバーエラー（サーバーの応答が不正）
     *   <tr><td>{@code -431}<td>サーバーエラー（サーバーアプリケーション内部エラー）
     *   <tr><td>{@code -504}<td>サーバーメンテナンス中
     * </table>
     */
    JvResult jvFukuFile(String pattern, String filePath);
    
    /**
     * {@code Long JVMVCheck(String key)}のJavaによるWrapperです。
     * <p>JRA-VAN登録ソフト以外ではこの機能は利用できません。
     * @param key 該当データを取得するための要求キー
     * <p>
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>提供単位<th>key<th>説明
     *   <tr><td>レース毎<td>{@code YYYYMMDDJJKKHHRR}<br>または<br>{@code YYYYMMDDJJRR}<td>{@code MM}:開催月<br>
     *   {@code DD}:開催日<br>{@code JJ}:場コード<br>{@code KK}:回次<br>{@code HH}:日次<br>{@code RR}:レース番号
     * </table>
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常(公開動画なし)
     *   <tr><td>{@code 1}<td>正常(公開動画あり)
     *   <tr><td>{@code -1}<td>該当データ無し
     *   <tr><td>{@code -114}<td>{@code key}パラメータが不正
     *   <tr><td>{@code -201}<td>JVInitが行なわれていない
     *   <tr><td>{@code -202}<td>前回のJVOpen/JVRTOpen/JVMVOpenに対してJVCloseが呼ばれていない（オープン中）
     *   <tr><td>{@code -211}<td>レジストリ内容が不正（レジストリ内容が不正に変更された）
     *   <tr><td>{@code -301}<td>認証エラー
     *   <tr><td>{@code -302}<td>サービスキーの有効期限切れ
     *   <tr><td>{@code -303}<td>サービスキーが設定されていない（サービスキーが空値）
     *   <tr><td>{@code -401}<td>JV-Link内部エラー
     *   <tr><td>{@code -411}<td>サーバーエラー（HTTPステータス404 NotFound）
     *   <tr><td>{@code -412}<td>サーバーエラー（HTTPステータス403 Forbidden）
     *   <tr><td>{@code -413}<td>サーバーエラー（HTTPステータス200,403,404以外）同上
     *   <tr><td>{@code -421}<td>サーバーエラー（サーバーの応答が不正）
     *   <tr><td>{@code -431}<td>サーバーエラー（サーバーアプリケーション内部エラー）
     *   <tr><td>{@code -504}<td>サーバーメンテナンス中
     * </table>
     * @deprecated JRA-VANレーシングビュアー連携機能の申請が必要（公開するソフトウェア前提）で、JRA-VAN登録ソフト以外では利用できません。
     * JV-BeansはあくまでJV-Link開発支援フレームワークであるため、ソフトウェア登録はできず、テストができないためこのメソッドの動作は保証できません。
     */
    JvResult jvMvCheck(String key);
    
    /**
     * {@code Long JVMVPlay(String key)}のJavaによるWrapperです。
     * <p>JRA-VAN登録ソフト以外ではこの機能は利用できません。
     * @param key 再生するレース映像キー
     * <p>
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>種類<th>movietype<th>指定するキー(key)<th>説明
     *   <tr><td>レース映像<td>{@code 00}<td>{@code YYYYMMDDJJKKHHRRTT}<br>または<br>{@code YYYYMMDDJJRRTT}<br>または<br>
     *   YYYYMMDDJJKKHHRR<br>または<br>YYYYMMDDJJRR<td>{@code YYYY}:開催年<br>{@code MM}:開催月<br>{@code DD}:開催日<br>
     *   {@code JJ}:場コード<br>{@code KK}:回次<br>{@code HH}:日次<br>{@code RR}:レース番号<br>{@code TT}:動画種別
     *   <tr><td>調教映像<td>{@code 11}<br>または<br>{@code 12}<br>または<br>{@code 13}<br><td>{@code YYYYMMDDNNNNNNNNNN}
     *   <td>{@code YYYY}:調教実施年<br>{@code MM}:調教実施月<br>{@code DD}:調教実施日<br>{@code NNNNNNNNNN}:血統登録番号
     * </table>
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -1}<td>該当データ無し
     *   <tr><td>{@code -100}<td>パラメータが不正あるいはレジストリへの保存に失敗
     *   <tr><td>{@code -111}<td>{@code movietype}パラメータが不正  
     *   <tr><td>{@code -114}<td>{@code key}パラメータが不正
     *   <tr><td>{@code -201}<td>JVInitが行なわれていない
     *   <tr><td>{@code -202}<td>前回のJVOpen/JVRTOpen/JVMVOpenに対してJVCloseが呼ばれていない（オープン中）
     *   <tr><td>{@code -211}<td>レジストリ内容が不正（レジストリ内容が不正に変更された）
     *   <tr><td>{@code -301}<td>認証エラー
     *   <tr><td>{@code -302}<td>サービスキーの有効期限切れ
     *   <tr><td>{@code -303}<td>サービスキーが設定されていない（サービスキーが空値）
     *   <tr><td>{@code -304}<td>JRAレーシングビュアー連携機能認証エラー
     *   <tr><td>{@code -401}<td>JV-Link内部エラー
     *   <tr><td>{@code -411}<td>サーバーエラー（HTTPステータス404 NotFound）
     *   <tr><td>{@code -412}<td>サーバーエラー（HTTPステータス403 Forbidden）
     *   <tr><td>{@code -413}<td>サーバーエラー（HTTPステータス200,403,404以外）
     *   <tr><td>{@code -421}<td>サーバーエラー（サーバーの応答が不正）
     *   <tr><td>{@code -431}<td>サーバーエラー（サーバーアプリケーション内部エラー）
     *   <tr><td>{@code -504}<td>サーバーメンテナンス中 
     * </table>
     * @deprecated JRA-VANレーシングビュアー連携機能の申請が必要（公開するソフトウェア前提）で、JRA-VAN登録ソフト以外では利用できません。
     * JV-BeansはあくまでJV-Link開発支援フレームワークであるため、ソフトウェア登録はできず、テストができないためこのメソッドの動作は保証できません。
     */
    JvResult jvMvPlay(String key);
    
    /**
     * {@code Long JVMVPlayWithType(String movietype , String key)}のJavaによるWrapperです。
     * <p>JRA-VAN登録ソフト以外ではこの機能は利用できません。
     * @param movieType レース映像:00, 調教映像:11, 12, 13
     * @param key 再生するレース映像キー
     * <p>
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>種類<th>movietype<th>指定するキー(key)<th>説明
     *   <tr><td>レース映像<td>{@code 00}<td>{@code YYYYMMDDJJKKHHRRTT}<br>または<br>{@code YYYYMMDDJJRRTT}<br>または<br>
     *   YYYYMMDDJJKKHHRR<br>または<br>YYYYMMDDJJRR<td>{@code YYYY}:開催年<br>{@code MM}:開催月<br>{@code DD}:開催日<br>
     *   {@code JJ}:場コード<br>{@code KK}:回次<br>{@code HH}:日次<br>{@code RR}:レース番号<br>{@code TT}:動画種別
     *   <tr><td>調教映像<td>{@code 11}<br>または<br>{@code 12}<br>または<br>{@code 13}<br><td>{@code YYYYMMDDNNNNNNNNNN}
     *   <td>{@code YYYY}:調教実施年<br>{@code MM}:調教実施月<br>{@code DD}:調教実施日<br>{@code NNNNNNNNNN}:血統登録番号
     * </table>
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -1}<td>該当データ無し
     *   <tr><td>{@code -100}<td>パラメータが不正あるいはレジストリへの保存に失敗
     *   <tr><td>{@code -111}<td>{@code movietype}パラメータが不正  
     *   <tr><td>{@code -114}<td>{@code key}パラメータが不正
     *   <tr><td>{@code -201}<td>JVInitが行なわれていない
     *   <tr><td>{@code -202}<td>前回のJVOpen/JVRTOpen/JVMVOpenに対してJVCloseが呼ばれていない（オープン中）
     *   <tr><td>{@code -211}<td>レジストリ内容が不正（レジストリ内容が不正に変更された）
     *   <tr><td>{@code -301}<td>認証エラー
     *   <tr><td>{@code -302}<td>サービスキーの有効期限切れ
     *   <tr><td>{@code -303}<td>サービスキーが設定されていない（サービスキーが空値）
     *   <tr><td>{@code -304}<td>JRAレーシングビュアー連携機能認証エラー
     *   <tr><td>{@code -401}<td>JV-Link内部エラー
     *   <tr><td>{@code -411}<td>サーバーエラー（HTTPステータス404 NotFound）
     *   <tr><td>{@code -412}<td>サーバーエラー（HTTPステータス403 Forbidden）
     *   <tr><td>{@code -413}<td>サーバーエラー（HTTPステータス200,403,404以外）
     *   <tr><td>{@code -421}<td>サーバーエラー（サーバーの応答が不正）
     *   <tr><td>{@code -431}<td>サーバーエラー（サーバーアプリケーション内部エラー）
     *   <tr><td>{@code -504}<td>サーバーメンテナンス中 
     * </table>
     * @deprecated JRA-VANレーシングビュアー連携機能の申請が必要（公開するソフトウェア前提）で、JRA-VAN登録ソフト以外では利用できません。
     * JV-BeansはあくまでJV-Link開発支援フレームワークであるため、ソフトウェア登録はできず、テストができないためこのメソッドの動作は保証できません。
     */
    JvResult jvMvPlayWithTime(String movieType, String key);
    
    /**
     * {@code Long JVMVOpen(String movietype, String searchkey)}のJavaによるWrapperです。
     * <p>JRA-VAN登録ソフト以外ではこの機能は利用できません。
     * @param movieType 調教映像指定週全馬:11, 調教映像指定週指定馬:12, 調教映像指定馬全調教:13
     * @param searchKey 該当データを要求するためのキー
     * <p>
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>種類<th>movietype<th>指定するキー(searchKey)<th>説明
     *   <tr><td>調教映像指定週全馬<td>{@code 11}<td>{@code YYYYMMDD}<td>{@code YYYY}:開催年<br>
     *   {@code MM}:開催月<br>{@code DD}:開催日
     *   <tr><td>調教映像指定週指定馬<td>{@code 12}<td>{@code YYYYMMDDNNNNNNNNNN}<td>{@code YYYY}:開催年<br>
     *   {@code MM}:開催月<br>{@code DD}:開催日<br>{@code NNNNNNNNNN}:血統登録番号
     *   <tr><td>調教映像指定馬全調教<td>{@code 13}<td>{@code NNNNNNNNNN”}<td>{@code NNNNNNNNNN}:血統登録番号
     * </table>
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvResult#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -1}<td>該当データ無し
     *   <tr><td>{@code -111}<td>{@code movietype}パラメータが不正  
     *   <tr><td>{@code -114}<td>{@code key}パラメータが不正
     *   <tr><td>{@code -201}<td>JVInitが行なわれていない
     *   <tr><td>{@code -202}<td>前回のJVOpen/JVRTOpen/JVMVOpenに対してJVCloseが呼ばれていない（オープン中）
     *   <tr><td>{@code -211}<td>レジストリ内容が不正（レジストリ内容が不正に変更された）
     *   <tr><td>{@code -301}<td>認証エラー
     *   <tr><td>{@code -302}<td>サービスキーの有効期限切れ
     *   <tr><td>{@code -303}<td>サービスキーが設定されていない（サービスキーが空値）
     *   <tr><td>{@code -304}<td>JRAレーシングビュアー連携機能認証エラー
     *   <tr><td>{@code -401}<td>JV-Link内部エラー
     *   <tr><td>{@code -411}<td>サーバーエラー（HTTPステータス404 NotFound）
     *   <tr><td>{@code -412}<td>サーバーエラー（HTTPステータス403 Forbidden）
     *   <tr><td>{@code -413}<td>サーバーエラー（HTTPステータス200,403,404以外）
     *   <tr><td>{@code -421}<td>サーバーエラー（サーバーの応答が不正）
     *   <tr><td>{@code -431}<td>サーバーエラー（サーバーアプリケーション内部エラー）
     *   <tr><td>{@code -504}<td>サーバーメンテナンス中 
     * </table>
     * @deprecated JRA-VANレーシングビュアー連携機能の申請が必要（公開するソフトウェア前提）で、JRA-VAN登録ソフト以外では利用できません。
     * JV-BeansはあくまでJV-Link開発支援フレームワークであるため、ソフトウェア登録はできず、テストができないためこのメソッドの動作は保証できません。
     */
    JvResult jvMvOpen(String movieType, String searchKey);
    
    /**
     * {@code Long JVMVRead(String buff, Long size)}のJavaによるWrapperです。
     * <p>JRA-VAN登録ソフト以外ではこの機能は利用できません。
     * @param size {@code byte}配列にコピーするデータの長さ
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvMvContents#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}以上<td>正常（バッファにセットしたデータのサイズ）
     *   <tr><td>{@code 0}<td>全ファイル読み込み終了({@code EOF})
     *   <tr><td>{@code -3}<td>データダウンロード中
     *   <tr><td>{@code -201}<td>JVInitが行なわれていない
     *   <tr><td>{@code -203}<td>JVMVOpenが行なわれていない
     *   <tr><td>{@code -402}<td>ダウンロードしたデータが異常（サイズ={@code 0}）
     *   <tr><td>{@code -403}<td>ダウンロードしたデータが異常（データ内容）
     *   <tr><td>{@code -502}<td>ダウンロード失敗（通信エラーやディスクエラーなど）
     *   <tr><td>{@code -503}<td>一時ファイルが見つからない
     * </table>
     * @deprecated JRA-VANレーシングビュアー連携機能の申請が必要（公開するソフトウェア前提）で、JRA-VAN登録ソフト以外では利用できません。
     * JV-BeansはあくまでJV-Link開発支援フレームワークであるため、ソフトウェア登録はできず、テストができないためこのメソッドの動作は保証できません。
     */
    JvMvContents jvMvRead(long size);
    
    /**
     * {@code Long JVCourseFile(String key, String filepath, String explanation)}のJavaによるWrapperです。
     * @param key コース図を取得するための要求キー
     * <table>
     *   <th>指定するキー<th>説明
     *   <tr><td>{@code YYYYMMDDJJKKKKTT}<td>{@code YYYY}:開催年<br>{@code MM}:開催月<br>{@code DD}:開催日<br>
     *   {@code JJ}:場コード<br>{@code KKKK}:距離<br>{@code TT}:トラックコード
     * </table>
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvCourseFile#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -1}<td>該当データ無し
     *   <tr><td>{@code -114}<td>{@code key}パラメータが不正
     *   <tr><td>{@code -201}<td>JVInitが行なわれていない
     *   <tr><td>{@code -211}<td>レジストリ内容が不正（レジストリ内容が不正に変更された）
     *   <tr><td>{@code -301}<td>認証エラー
     *   <tr><td>{@code -302}<td>サービスキーの有効期限切れ
     *   <tr><td>{@code -303}<td>サービスキーが設定されていない（サービスキーが空値）
     *   <tr><td>{@code -401}<td>JV-Link内部エラー
     *   <tr><td>{@code -411}<td>サーバーエラー（HTTPステータス404 NotFound）
     *   <tr><td>{@code -412}<td>サーバーエラー（HTTPステータス403 Forbidden）
     *   <tr><td>{@code -413}<td>サーバーエラー（HTTPステータス200,403,404以外）
     *   <tr><td>{@code -421}<td>サーバーエラー（サーバーの応答が不正）
     *   <tr><td>{@code -431}<td>サーバーエラー（サーバーアプリケーション内部エラー）
     *   <tr><td>{@code -502}<td>ダウンロード失敗（通信エラーやディスクエラーなど）
     *   <tr><td>{@code -504}<td>サーバーメンテナンス中
     * </table>
     */
    JvCourseFile jvCourseFile(String key);
    
    /**
     * {@code Long JVCourseFile2(String key, String filepath)}のJavaによるWrapperです。
     * @param key コース図を取得するための要求キー
     * @param filePath コース図ファイルの出力ファイル名（フルパス）
     * <table>
     *   <th>指定するキー<th>説明
     *   <tr><td>{@code YYYYMMDDJJKKKKTT}<td>{@code YYYY}:開催年<br>{@code MM}:開催月<br>{@code DD}:開催日<br>
     *   {@code JJ}:場コード<br>{@code KKKK}:距離<br>{@code TT}:トラックコード
     * </table>
     * @return 結果オブジェクト
     * <p>リターンコード（{@link JvCourseFile#getReturnCode()}参照）
     * <table border="0" cellspacing="3" cellpadding="0">
     *   <th>リターンコード<th>内容
     *   <tr><td>{@code 0}<td>正常
     *   <tr><td>{@code -1}<td>該当データ無し
     *   <tr><td>{@code -114}<td>{@code key}パラメータが不正
     *   <tr><td>{@code -201}<td>JVInitが行なわれていない
     *   <tr><td>{@code -211}<td>レジストリ内容が不正（レジストリ内容が不正に変更された）
     *   <tr><td>{@code -301}<td>認証エラー
     *   <tr><td>{@code -302}<td>サービスキーの有効期限切れ
     *   <tr><td>{@code -303}<td>サービスキーが設定されていない（サービスキーが空値）
     *   <tr><td>{@code -401}<td>JV-Link内部エラー
     *   <tr><td>{@code -411}<td>サーバーエラー（HTTPステータス404 NotFound）
     *   <tr><td>{@code -412}<td>サーバーエラー（HTTPステータス403 Forbidden）
     *   <tr><td>{@code -413}<td>サーバーエラー（HTTPステータス200,403,404以外）
     *   <tr><td>{@code -421}<td>サーバーエラー（サーバーの応答が不正）
     *   <tr><td>{@code -431}<td>サーバーエラー（サーバーアプリケーション内部エラー）
     *   <tr><td>{@code -502}<td>ダウンロード失敗（通信エラーやディスクエラーなど）
     *   <tr><td>{@code -504}<td>サーバーメンテナンス中
     * </table>
     */
    JvCourseFile jvCourceFile2(String key, String filePath);
    
    /**
     * {@code Long JVWatchEvent()}のJavaによるWrapperです。
     * @return
     */
    void jvWatchEvent();
    
    
    /**
     * {@code Long JVWatchEventClose()}のJavaによるWrapperです。
     * @return
     */
    JvResult jvWatchEventClose();
    
    
    
}
