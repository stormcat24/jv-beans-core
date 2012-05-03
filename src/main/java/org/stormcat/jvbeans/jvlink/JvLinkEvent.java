package org.stormcat.jvbeans.jvlink;

import com.jacob.com.Variant;

/**
 * JV-Linkが通知するイベントハンドリングのJava実装です。
 * @author a.yamada
 *
 */
public class JvLinkEvent {

	public void JVEvtPay(Variant[] variants) {
		// TODO handling
		System.out.println("JVEvtPay");
	}
	
	public void JVEvtJockeyChange(Variant[] variants) {
		// TODO handling
		System.out.println("JVEvtJockeyChange");
	}
	
	public void JVEvtWeather(Variant[] variants) {
		// TODO handling
		System.out.println("JVEvtWeather");
	}
	
	public void JVEvtCourseChange(Variant[] variants) {
		// TODO handling
		System.out.println("JVEvtCourseChange(Variant[] variants) {");
	}
	
	public void JVEvtAvoid(Variant[] variants) {
		// TODO handling
		System.out.println("JVEvtAvoid");
	}
	
	public void JVEvtTimeChange(Variant[] variants) {
		// TODO handling
		System.out.println("JVEvtTimeChange");
	}
	
	public void JVEvtWeight(Variant[] variants) {
		// TODO handling
		System.out.println("JVEvtWeight");
	}
}
