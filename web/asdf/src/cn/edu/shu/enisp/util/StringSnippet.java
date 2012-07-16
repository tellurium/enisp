package cn.edu.shu.enisp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringSnippet {

	public static String makeNotNull(String sourceStr) {
		return (sourceStr == null || sourceStr.length() == 0) ? "" : sourceStr;
	}

    public static boolean checkIsDate(String dateStr) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = df.parse(dateStr);
            dateStr = df.format(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
