package com.haiwai.housekeeper.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * 
 * @author abner
 * 
 */
public class VerifyRegular {

	public static boolean isMobileNO(String mobiles) {

		Pattern p = Pattern

				.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");

		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

	/**
	 * 6-16为数字和字母组合
	 * 
	 * @param pwd
	 * @return
	 */
	public static boolean isPwd(String pwd) {
		Pattern pat = Pattern.compile("[\\da-zA-Z]{6,16}");
		Pattern patno = Pattern.compile(".*\\d.*");
		Pattern paten = Pattern.compile(".*[a-zA-Z].*");
		Matcher mat = pat.matcher(pwd);
		Matcher matno = patno.matcher(pwd);
		Matcher maten = paten.matcher(pwd);

		// Pattern p =
		// Pattern.compile("/(?!^\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{4,23}/")
		// // (?!^\d+$) 排除全数字
		// // (?!^[a-zA-Z]+$) 排除全字母
		// // [0-9a-zA-Z]{4,23} 字符或字母4-23位，不考虑全为数字和全为字符情况。
		// Matcher m = p.matcher(pwd);

		return matno.matches() && maten.matches() && mat.matches();
	}

	public static boolean isEmail(String email) {
		Pattern p = Pattern
				.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 转化Html中的代替符
	 * @param notice
	 * @return
	 */
	public static String convertHtml(String notice){

		notice = notice.replaceAll("&quot;", "\"");
		notice = notice.replaceAll("&lt;", "<");
		notice = notice.replaceAll("&gt;", ">");
		notice = notice.replaceAll("&amp;", "&");
		notice = notice.replaceAll("&apos;", "'");

//		LogUtils.e("HTML:"+notice);
		return notice;
	}
}
