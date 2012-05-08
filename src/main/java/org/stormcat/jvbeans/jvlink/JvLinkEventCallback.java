package org.stormcat.jvbeans.jvlink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jacob.com.Variant;

/**
 * JV-Linkが通知するイベントハンドリングのJava実装です。
 * @author a.yamada
 * @since 1.1.0
 */
public class JvLinkEventCallback {
	
	private final Logger logger = LoggerFactory.getLogger(JvLinkEventCallback.class);
	
	private final JvLinkEventHandler handler;
	
	/**
	 * コンストラクタ
	 * @param handler {@link JvLinkEventCallback}
	 */
	JvLinkEventCallback(JvLinkEventHandler handler) {
		this.handler = handler;
	}

	/**
	 * JVEvtPayをキャッチし、ハンドリングメソッドへ転送します。
	 * @param variants コールバック引数
	 */
	public void JVEvtPay(Variant[] variants) {
		logger.info("caught JV-Link event 'JVEvtPay'!");
		handler.handlePay(getParameter(variants));
	}
	
	/**
	 * JVEvtJockeyChangeをキャッチし、ハンドリングメソッドへ転送します。
	 * @param variants コールバック引数
	 */
	public void JVEvtJockeyChange(Variant[] variants) {
		logger.info("caught JV-Link event 'JVEvtJockeyChange'!");
		handler.handleJockeyChange(getParameter(variants));
	}

	/**
	 * JVEvtWeatherをキャッチし、ハンドリングメソッドへ転送します。
	 * @param variants コールバック引数
	 */
	public void JVEvtWeather(Variant[] variants) {
		logger.info("caught JV-Link event 'JVEvtWeather'!");
		handler.handleWeather(getParameter(variants));
	}
	
	/**
	 * JVEvtCourseChangeをキャッチし、ハンドリングメソッドへ転送します。
	 * @param variants コールバック引数
	 */
	public void JVEvtCourseChange(Variant[] variants) {
		logger.info("caught JV-Link event 'JVEvtCourseChange'!");
		handler.handleCourseChange(getParameter(variants));
	}
	
	/**
	 * JVEvtTimeChangeをキャッチし、ハンドリングメソッドへ転送します。
	 * @param variants コールバック引数
	 */
	public void JVEvtAvoid(Variant[] variants) {
		logger.info("caught JV-Link event 'JVEvtAvoid'!");
		handler.handleAvoid (getParameter(variants));
	}
	
	/**
	 * JVEvtTimeChangeをキャッチし、ハンドリングメソッドへ転送します。
	 * @param variants コールバック引数
	 */
	public void JVEvtTimeChange(Variant[] variants) {
		logger.info("caught JV-Link event 'JVEvtTimeChange'!");
		handler.handleTimeChange(getParameter(variants));
	}
	
	/**
	 * JVEvtWeightをキャッチし、ハンドリングメソッドへ転送します。
	 * @param variants コールバック引数
	 */
	public void JVEvtWeight(Variant[] variants) {
		logger.info("caught JV-Link event 'JVEvtWeight'!");
		handler.handleWeight(getParameter(variants));
	}
	
	private String getParameter(Variant[] variants) {
		return variants[0].getString();
	}
}
