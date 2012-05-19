package org.stormcat.jvbeans.jvlink;

import org.stormcat.jvbeans.common.lang.StringUtil;

/**
 * 最後にJV-Linkから最後に読み込みに成功した情報を保持するためのクラスです。
 * @author a.yamada
 *
 */
public class JvLastSuccessRepository {

	private static final ThreadLocal<JvLastSuccess> local = new ThreadLocal<JvLastSuccess>();
	
	/**
	 * {@link JvLastSuccess} を設定します。
	 * @param success {@link JvLastSuccess}
	 */
	public static void setLastSuccess(JvLastSuccess success) {
		if (StringUtil.isNotBlank(success.getLine())) {
			local.set(success);	
		}
	}
	
	/**
	 * {@link JvLastSuccess} を取得します。
	 * @return {@link JvLastSuccess}
	 */
	public static JvLastSuccess getLastFileName() {
		return local.get();
	}
}
