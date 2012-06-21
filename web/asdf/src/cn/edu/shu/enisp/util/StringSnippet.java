package cn.edu.shu.enisp.util;

public class StringSnippet {
	public static String makeNotNull(String sourceStr) {
		return (sourceStr == null || sourceStr.length() == 0) ? "" : sourceStr;
	}
}
