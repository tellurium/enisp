package cn.yestar;

public class Snippet {
	public static final int MAX_INFO_LENGH = 18;
	
	// �����ڳ����ַ�������ΪС��18���ַ��ĳ���
	public static String getShortenString(String str) {
		if (str.length() > MAX_INFO_LENGH) {
			StringBuilder sb = new StringBuilder();
			sb.append(str.substring(0, MAX_INFO_LENGH));
			sb.append("...");
			return sb.toString();
		} else {
			return str;
		}
	}
}
