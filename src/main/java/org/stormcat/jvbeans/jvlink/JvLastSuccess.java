package org.stormcat.jvbeans.jvlink;

/**
 * JV-Linkで最後の読み込み成功時の情報を保持するクラスです。
 * @author a.yamada
 *
 */
public class JvLastSuccess {
	
	private final String fileName;
	
	private final String line;

	public JvLastSuccess(String fileName, String line) {
		this.fileName = fileName;
		this.line = line;
	}

	public String getFileName() {
		return fileName;
	}

	public String getLine() {
		return line;
	}

	@Override
	public String toString() {
		return String.format("filename=%s\nline=%s", fileName, line);
	}
	
}
