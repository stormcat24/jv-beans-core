package org.stormcat.jvbeans.exception;

import org.stormcat.jvbeans.jvlink.JvLastSuccess;

import com.jacob.com.JacobException;

/**
 * COM実行時の例外をラップする例外です。
 * @author a.yamada
 *
 */
public class JvComException extends JvBeansRuntimeException {
	
	private final JvLastSuccess lastSuccess;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1112855024054395761L;
	
	public JvComException(JacobException e, JvLastSuccess lastSuccess) {
		super("JV-Link側で例外が発生しました。", e);
		this.lastSuccess = lastSuccess;
	}

	public JvLastSuccess getLastSuccess() {
		return lastSuccess;
	}

}
