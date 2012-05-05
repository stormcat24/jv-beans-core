package org.stormcat.jvbeans.jvlink;

/**
 * JV-Linkの通知イベントハンドラのインタフェースです。
 * @author a.yamada
 * @since 1.1.0
 *
 */
public interface JvLinkEventHandler {

	/**
	 * 払戻確定イベントをハンドリングします。
	 * @param yyyymmddjjrr パラメータ
	 */
	public void handlePay(String yyyymmddjjrr);
	
	/**
	 * 馬体重発表イベントをハンドリングします。
	 * @param yyyymmddjjrr パラメータ
	 */
	public void handleWeight(String yyyymmddjjrr);
	
	/**
	 * 騎手変更イベントをハンドリングします。
	 * @param ttyyyymmddjjrrnnnnnnnnnnnnnn パラメータ
	 */
	public void handleJockeyChange(String ttyyyymmddjjrrnnnnnnnnnnnnnn);
	
	/**
	 * 天候馬場状態変更イベントをハンドリングします。
	 * @param ttyyyymmddjjrrnnnnnnnnnnnnnn パラメータ
	 */
	public void handleWeather(String ttyyyymmddjjrrnnnnnnnnnnnnnn);
	
	/**
	 * コース変更イベントをハンドリングします。
	 * @param ttyyyymmddjjrrnnnnnnnnnnnnnn パラメータ
	 */
	public void handleCourseChange(String ttyyyymmddjjrrnnnnnnnnnnnnnn);

	/**
	 * 出走取消・競走除外イベントをハンドリングします。
	 * @param ttyyyymmddjjrrnnnnnnnnnnnnnn パラメータ
	 */
	public void handleAvoid(String ttyyyymmddjjrrnnnnnnnnnnnnnn);
	
	/**
	 * 発想時刻変更イベントをハンドリングします。
	 * @param ttyyyymmddjjrrnnnnnnnnnnnnnn パラメータ
	 */
	public void handleTimeChange(String ttyyyymmddjjrrnnnnnnnnnnnnnn);
	
}
