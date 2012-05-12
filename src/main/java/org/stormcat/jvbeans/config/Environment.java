package org.stormcat.jvbeans.config;

import org.stormcat.jvbeans.common.constants.Charset;

/**
 * 環境定義用singletonです。
 * @author a.yamada
 *
 */
public enum Environment {

	INSTANCE;
	
	private Charset defaultCharset = Charset.WINDOWS31J;

	public Charset getDefaultCharset() {
		synchronized (defaultCharset) {
			return defaultCharset;	
		}
	}

	public void setDefaultCharset(Charset defaultCharset) {
		synchronized (defaultCharset) {
			this.defaultCharset = defaultCharset;	
		}
	}
	
}
