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

import java.util.Date;

import org.stormcat.jvbeans.config.DataOption;
import org.stormcat.jvbeans.config.condition.RealTimeOpenCondition;
import org.stormcat.jvbeans.config.condition.StoredOpenCondition;
import org.stormcat.jvbeans.config.key.CourseFileKey;
import org.stormcat.jvbeans.config.key.RtOpenKey;
import org.stormcat.jvbeans.exception.JvIllegalFormatKeyException;
import org.stormcat.jvbeans.response.JvCourseFile;
import org.stormcat.jvbeans.response.JvResult;


/**
 * JVLinkにアクセスするための基本的なAPIを提供します。
 * <p>{@link JvLinkWrapper}層はJV-Link APIの純粋なラッパーであることに対し、
 * {@link JvLinkManager}層においては、JV-LinkをJavaで利用する開発者にとって使いやすく堅牢にするための実装が施される必要があります。
 * @author a.yamada
 * @since 0.1
 *
 */
public interface JvLinkManager {

    /**
     * JVLinkからJRA-VANサーバにアクセスする際のUserAgent HTTPヘッダを指定します。
     * <p>UserAgentとして指定できる文字列は、最大64バイトで以下の文字で構成されます。
     * <ul>
     *   <li>半角文字</li>
     *   <li>アルファベット</li>
     *   <li>数字</li>
     *   <li>スペース</li>
     *   <li>ピリオド</li>
     *   <li>スラッシュ</li>
     * </ul>
     * 上述の条件が満たされていない文字列（{@code null}, 空文字を含む）が渡された場合は{@link IllegalArgumentException}がthrowされます。
     * <p>
     * また、このメソッドを使用しなかった場合はUserAgentとして<b>UNKNOWN</b>が指定されます。
     * <p>
     * 設定されたUserAgentは{@link JvLinkManager#init()}内で指定されます。
     * @param userAgent UserAgent文字列
     */
    void setUserAgent(String userAgent);

    /**
     * 設定されているUserAgentを返します。
     * <p>{@link JvLinkManager#setUserAgent(String)}で設定されていなければ<b>UNKNOWN</b>を返します。
     * @return UserAgent
     */
    String getUserAgent();

    /**
     * JVLinkの初期化を行います。
     * <p>JVLinkの一連の処理を行う際に、最初に呼び出す必要があるメソッドです。
     * <p>初期化処理の際に、JRA-VANサーバにアクセスする際のUserAgentを設定します。
     * ここで使用するUserAgentは{@link JvLinkManager#setUserAgent(String)}でセットされた文字列が使用されますが、
     * セットしなかった場合は<b>UNKNOWN</b>が使用されます。
     * @return 結果オブジェクト
     */
    JvResult init();

    /**
     * JV-Linkの設定変更を行うためのダイアログを表示します。
     * <p>設定可能なプロパティは以下の通りです。
     * <ul>
     *   <li>m_saveflag（保存フラグ）</li>
     *   <li>m_savepath（保存パス）</li>
     *   <li>m_servicekey（サービスキー）</li>
     * </ul>
     * <p>ただし、サービスキーが既に設定されている場合は変更できません。
     * @return 結果オブジェクト
     */
    JvResult setUIProperties();

    /**
     * JV-Linkのサービスキーを設定し、レジストリに保存します。
     * <p>サービスキーが設定済みの場合は、サービスキーは変更できません。
     * @param serviceKey サービスキー
     * @return 結果オブジェクト
     * @throws IllegalArgumentException 保存パスが{@code null}、もしくは存在しないディレクトリの場合
     */
    JvResult setServiceKey(String serviceKey);

    /**
     * JV-Linkのデータを設定したパスに保存するかどうかを設定します。
     * @param saveFlag {@code true}:保存する, {@code false}:保存しない
     * @return 結果オブジェクト
     */
    JvResult setSaveFlag(boolean saveFlag);

    /**
     * JV-Linkのデータの保存パスを設定し、レジストリに保存します。
     * <p>保存パスは実際に存在するディレクトリを指定する必要があります。
     * @param savePath 保存パス
     * @return 結果オブジェクト
     * @throws IllegalArgumentException 保存パスが{@code null}、もしくは存在しないディレクトリの場合
     */
    JvResult setSavePath(String savePath);

    /**
     * 蓄積系データの取得要求を行います。
     * <p>データの読み出しは{@link JvReader}をイテレートすることで1レコードずつ読み出されます。
     * @param <T> {@link JvBindingDto}型
     * @param condition データ取得条件
     * @param fromTime データ読み出し開始ポイント(YYYYMMDDhhmmss形式)
     * @param dataOption データオプション
     * <p>{@code null}の場合は{@link DataOption#STANDARD}で取得要求が行われます。
     * @return {@link JvReaderImpl}
     * @throws IllegalArgumentException 引数に{@code null}がある場合
     */
    <T extends JvBindingDto> JvReader<T> open(StoredOpenCondition<T> condition, Date fromTime, DataOption dataOption);

    /**
     * 蓄積系データの取得要求を行います。
     * <p>データの読み出しは{@link JvReader}をイテレートすることで1レコードずつ読み出されます。
     * @param <T> {@link JvBindingDto}型
     * @param condition データ取得条件
     * @param fromTime データ読み出し開始ポイント(YYYYMMDDhhmmss形式)
     * @param dataOption データオプション
     * <p>{@code null}の場合は{@link DataOption#STANDARD}で取得要求が行われます。
     * @return {@link JvReader}
     * @throws IllegalArgumentException 引数に{@code null}がある場合
     */
    <T extends JvBindingDto> JvReader<T> open(StoredOpenCondition<T> condition, String fromTime, DataOption dataOption);

    /**
     * 蓄積系データの取得要求を行います。
     * <p>データの読み出しは{@link JvReader}をイテレートすることで1レコードずつ読み出されます。
     * <p>DTOへのバインディングは行いません。
     * @param <T> {@link JvBindingDto}型
     * @param condition データ取得条件
     * @param fromTime データ読み出し開始ポイント(YYYYMMDDhhmmss形式)
     * @param dataOption データオプション
     * <p>{@code null}の場合は{@link DataOption#STANDARD}で取得要求が行われます。
     * @return {@link JvReader}
     * @since 0.2
     */
    public <T extends JvBindingDto> JvReader<String> simpleOpen(StoredOpenCondition<T> condition, Date fromTime,
            DataOption dataOption);

    /**
     * 蓄積系データの取得要求を行います。
     * <p>データの読み出しは{@link JvReader}をイテレートすることで1レコードずつ読み出されます。
     * <p>DTOへのバインディングは行いません。
     * @param <T> {@link JvBindingDto}型
     * @param condition データ取得条件
     * @param fromTime データ読み出し開始ポイント(YYYYMMDDhhmmss形式)
     * @param dataOption データオプション
     * <p>{@code null}の場合は{@link DataOption#STANDARD}で取得要求が行われます。
     * @return {@link JvReader}
     * @since 0.2
     */
    public <T extends JvBindingDto> JvReader<String> simpleOpen(StoredOpenCondition<T> condition, String fromTime,
            DataOption dataOption);

    /**
     * 速報系のデータ取得要求を行います。
     * <p>データの読み出しは{@link JvReader}をイテレートすることで1レコードずつ読み出されます。
     * @param condition データ種別
     * @param rtOpenKey 速報系要求キー
     * @return {@link JvReader}
     * @throws IllegalArgumentException 引数に{@code null}がある場合
     * @throws JvIllegalFormatKeyException データ種別が{@link RtOpenKey}の形式をサポートしていない場合
     */
     <T extends JvBindingDto> JvReader<T> rtOpen(RealTimeOpenCondition<T> condition, RtOpenKey rtOpenKey);

     /**
      * 速報系のデータ取得要求を行います。
      * <p>データの読み出しは{@link JvReader}をイテレートすることで1レコードずつ読み出されます。
      * <p>DTOへのバインディングは行いません。
      * @param condition データ種別
      * @param rtOpenKey 速報系要求キー
      * @return {@link JvReader}
      * @throws IllegalArgumentException 引数に{@code null}がある場合
      * @throws JvIllegalFormatKeyException データ種別が{@link RtOpenKey}の形式をサポートしていない場合
      */
      <T extends JvBindingDto> JvReader<String> rtSimpleOpen(RealTimeOpenCondition<T> condition, RtOpenKey rtOpenKey);

    /**
     * JV-Dataのダウンロード完了ファイル数を返します。
     * <p>ダウンロード失敗時は例外が発生します。
     * @return ダウンロード完了ファイル数
     */
    long status();

    /**
     * {@link JvLinkManager#open}で準備した不要レコードを読み飛ばします。
     * <p>このメソッドはデータ読み込み中にレコード種別IDを判断して使用するものですが、
     * JV-Beansではバイト単位ではなく、レコード単位で読みだすため意味はありません。
     */
    void skip();

    /**
     * {@link JvLinkManager#open}によって起動された、JVデータファイルのダウンロード・コピーを安全に中止します。
     * <p>キャンセル状態において、{@link JvReaderImpl}による読み込み処理を行うことはできません。
     */
    void cancel();

    /**
     * JV-Dataの読み込み処理を正常に終了させます。
     * <p>開いているファイルを全てクローズさせ、実行中のダウンロードスレッドを安全に終了させます。
     * <p>データ読み込み後は確実に呼び出す必要があります。
     * @return 結果オブジェクト
     */
    JvResult close();

    /**
     * ダウンロードしたJV-Dataを削除します。
     * <p>ファイル名はXXXXX.jvdのように、ファイル名だけを指定します（フルパスを指定する必要はありません）。
     * <p>何らかの原因により、ファイルが破損してデータがうまく読みだせなくなった際に使用します。
     * @param fileName 削除対象ファイル名
     * @return 結果オブジェクト
     * @throws IllegalArgumentException ファイル名が{@code null}の場合
     */
    JvResult fileDelete(String fileName);

    /**
     * 任意の勝負服画像情報を要求し、指定のパスに保存します。
     * @param pattern 服色標示文字列
     * <p>服色標示はJRAレーシングプログラムに記載されているものを指定します。
     * <p>(例)水色，赤山形一本輪，水色袖
     * @param filePath 取得画像保存フルパス
     * @return 結果オブジェクト
     * @throws IllegalArgumentException 服色標示、保存パスが{@code null}の場合、もしくは保存ディレクトリが存在しない場合
     */
    JvResult fukuFile(String pattern, String filePath);

    /**
     * JV-Linkからコースgif画像を要求し、{JRA-VANインストールディレクトリ}/Data Lab/pictures配下に保存します。
     * <p>keyは{@link CourseFileKeyFactory}で生成したものを指定します。
     * <p>コース説明文を{@link JvCourseFile#getExplanation()}で取得することが可能です。
     * <p>指定したkeyに該当するコースが存在しない場合は、「No Image」画像が保存されます。
     * @param courseFileKey コースを指定するkey
     * @return コース図用結果オブジェクト
     * @throws IllegalArgumentException mvRaceKeyが{@code null}の場合
     */
    JvCourseFile courseFile(CourseFileKey courseFileKey);

    /**
     * JV-Linkからコースgif画像を要求し、任意の場所に保存します。
     * <p>keyは{@link CourseFileKeyFactory}で生成したものを指定します。
     * <p>{@link JvLinkManager#courseFile(CourseFileKey)}とは違い、
     * {@link JvCourseFile#getExplanation()}にてコース説明文を取得することはできません（{@code null}を返します）。
     * <p>指定したkeyに該当するコースが存在しない場合は、「No Image」画像が保存されます。
     * @param courseFileKey コースを指定するkey
     * @param filePath 取得画像保存フルパス
     * @return コース図用結果オブジェクト
     * @throws IllegalArgumentException mvRaceKeyが{@code null}の場合
     */
    JvCourseFile courseFile2(CourseFileKey courseFileKey, String filePath);

}
