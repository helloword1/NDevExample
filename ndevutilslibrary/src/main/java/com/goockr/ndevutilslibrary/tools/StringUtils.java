package com.goockr.ndevutilslibrary.tools;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/**
	 * is null or its length is 0 or it is made by space
	 * 
	 * <pre>
	 * isBlank(null) = true;
	 * isBlank(&quot;&quot;) = true;
	 * isBlank(&quot;  &quot;) = true;
	 * isBlank(&quot;a&quot;) = false;
	 * isBlank(&quot;a &quot;) = false;
	 * isBlank(&quot; a&quot;) = false;
	 * isBlank(&quot;a b&quot;) = false;
	 * </pre>
	 * 
	 * @param str
	 * @return if string is null or its size is 0 or it is made by space, return
	 *         true, else return false.
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * Returns true if the string is null or 0-length.
	 * 
	 * @param str
	 *            the string to be examined
	 * @return true if str is null or zero length
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	/**
	 * ���ַ����������ӿհ׷�����Ҫ�����ֻ�����ķָ�
	 * 
	 * @param str
	 *            ��:"138 0013 8000"
	 * @return ����:"13800138000"
	 */
	public static String removeBlank(String str) {
		String tempStr = str.replace(" ", "");
		return tempStr;
	}

	public static int trulyLength(String content) {
		int length = 0;
		String chinese = "[\\u4e00-\\u9fa5]";
		for (int i = 0; i < content.length(); i++) {
			String temp = content.substring(i, i + 1);
			if (temp.matches(chinese)) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length;
	}

	/**
	 * encoded in utf-8
	 * 
	 * <pre>
	 * utf8Encode(null)        =   null
	 * utf8Encode("")          =   "";
	 * utf8Encode("aa")        =   "aa";
	 * utf8Encode("啊啊啊啊")   = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
	 * </pre>
	 * 
	 * @param str
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 *             if an error occurs
	 */
	public static String utf8Encode(String str) {
		if (!isEmpty(str) && str.getBytes().length != str.length()) {
			try {
				return URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(
						"UnsupportedEncodingException occurred. ", e);
			}
		}
		return str;
	}

	/**
	 * �ж��ǲ���һ���Ϸ����ֻ�����
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isPhone(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|14[0-9])\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.find();
	}
	/**
	 * �ж��ǲ���һ���Ϸ��Ĺ̻�����
	 * 
	 * @param tel
	 * @return
	 */
	public static boolean isTel(String tel) {
		Pattern p = Pattern
				.compile("^(010|02\\d|0[3-9]\\d{2})?\\d{6,8}$");
		Matcher m = p.matcher(tel);
		return m.find();
	}

	/**
	 * �ж��ǲ���һ���Ϸ����ֻ����к���,�������Ժ�Ҫɾ��
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobilePhone(String mobiles) {
		Pattern p = Pattern
				.compile("^((11[0-9])|12[0-9]|(13[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|14[0-9])\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.find();
	}

	private final static Pattern emailer = Pattern
			.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

	/**
	 * �ж��ǲ���һ���Ϸ��ĵ����ʼ���ַ
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * �ж��Ƿ�Ϊ�����˺�
	 * 
	 * @param accountNo
	 * @return
	 */
	public static boolean isAccountNo(String accountNo) {
		if (isWholeDigit(accountNo)
				&& (16 == accountNo.length() || 19 == accountNo.length() || 20 == accountNo
						.length())) {
			return true;
		}
		return false;
	}

	/**
	 * �ж��ǲ���һ���Ϸ������֤��
	 * 
	 * @param IdNumber
	 * @return
	 */
	public static boolean isIdNumber(String IdNumber) {
		boolean m = IdNumber
				.matches("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)");
		return m;
	}

	/**
	 * �Ƿ�����ȫΪ����
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isWholeDigit(String text) {
		boolean isWholeDigit = true;
		for (int i = 0; i < text.length(); i++) {
			boolean isDigit = Character.isDigit(text.charAt(i));
			if (!isDigit) {
				isWholeDigit = false;
				break;
			}
		}
		return isWholeDigit;
	}

	/**
	 * �����֤�ŵ��м��滻��*
	 * 
	 * @param idNumber
	 *            ��������֤��:"441702198808084321"
	 * @return ����"4417****4321"
	 */
	public static String replaceIdNumberMiddle2Asterisk(String idNumber) {
		char[] idNumberCharArray = idNumber.toCharArray();
		int startIndex = 4, endIndex = idNumberCharArray.length - 4;
		for (int i = startIndex; i < endIndex; i++) {
			idNumberCharArray[i] = '*';
		}
		return new String(idNumberCharArray);
	}

	/**
	 * ��email���м��滻��*�������û���ǰ��λ�����һλ
	 * 
	 * @param email
	 *            �����email:"abcdef@com.ccb.shop.com"
	 * @return ����"ab***f@com.ccb.shop.com"
	 */
	public static String replaceEmailMiddle2Asterisk(String email) {
		char[] emailCharArray = email.toCharArray();
		int place = email.indexOf("@");

		// @ǰ��������û�������2����ȥ����*�ţ����򲻼�
		if (place > 2) {
			int startIndex = 2, endIndex = place - 1;
			for (int i = startIndex; i < endIndex; i++) {
				emailCharArray[i] = '*';
			}
		}
		return new String(emailCharArray);
	}

	/**
	 * ��userName���м��滻��***�������û���ǰ��λ�������λ
	 * 
	 * @param userName
	 *            �����userName:"abcdefghi"
	 * @return ����"ab***hi"
	 */
	public static String replaceUserNameMiddle2Asterisk(String userName) {
		int length = userName.length();

		if (length < 4) {
			return userName;
		}

		String result = userName.substring(0, 2) + "***"
				+ userName.substring(length - 2, length);
		return result;
	}

	/**
	 * ���ֻ��ŵ��м��滻��*
	 * 
	 * @param phoneNumber
	 *            ������ֻ���:"18664548654"
	 * @return ����"186****8654"
	 */
	public static String replacePhoneMiddle2Asterisk(String phoneNumber) {
		char[] phoneCharArray = phoneNumber.toCharArray();
		int startIndex = 3, endIndex = startIndex + 4;
		for (int i = startIndex; i < endIndex; i++) {
			phoneCharArray[i] = '*';
		}
		return new String(phoneCharArray);
	}

	/**
	 * �ѽ����˺ŵ��м��滻��*
	 *
	 * @return 6227003328*****1070
	 */
	public static String replaceAccountNoMiddle2Asterisk(String accountNo) {
		char[] accountNoCharArray = accountNo.toCharArray();
		int startIndex = 10, endIndex = accountNoCharArray.length - 4;
		for (int i = startIndex; i < endIndex; i++) {
			accountNoCharArray[i] = '*';
		}
		return new String(accountNoCharArray);
	}

	public static boolean isHaveValue(String input) {
		if (isEmpty(input)) {
			return false;
		}
		if (input.equalsIgnoreCase("null"))
			return false;
		else
			return true;
	}

	/**
	 * ��ȡ��Ӧ��ֵ
	 * 
	 * @param key
	 * @param json
	 * @return
	 */
	public static String getValue(String key, JSONObject json) {
		if (json == null)
			return null;
		if (json.isNull(key))
			return null;
		try {
			return json.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �ַ���ת����
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * ����ת����
	 * 
	 * @param obj
	 * @return ת���쳣���� 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * �ж���������6������ĸ���������
	 * 
	 * @param str
	 * @return ��
	 */
	public static String stringFilter(String str) {
		// ֻ������ĸ������
		String regEx = "^[A-Za-z0-9]{6,16}$";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * ��ȡƴ��������ĸ����д��
	 * 
	 * @param pinyin
	 * @return
	 */
	public static String getFirstLetter(final String pinyin) {
		if (TextUtils.isEmpty(pinyin))
			return "��λ";
		String c = pinyin.substring(0, 1);
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c).matches()) {
			return c.toUpperCase();
		} else if ("0".equals(c)) {
			return "��λ";
		} else if ("1".equals(c)) {
			return "����";
		}
		return "��λ";
	}

	/**
	 * ��ȡƴ��������ĸ����д��
	 * 
	 * @param pinyin
	 * @return
	 */
	public static String getFirstLetterCard(final String pinyin) {
		if (TextUtils.isEmpty(pinyin))
			return "";
		String c = pinyin.substring(0, 1);
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c).matches()) {
			return c.toUpperCase();
		} else {
			return "#";
		}

	}

	/**
	 * ��ȡƴ��������ĸ����д��
	 * 
	 * @param pinyin
	 * @return
	 */
	public static String getCardFirstLetter(final String pinyin) {
		if (TextUtils.isEmpty(pinyin))
			return "��";
		String c = pinyin.substring(0, 1);
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c).matches()) {
			return c.toUpperCase();
		}
		return "��";
	}

	/**
	 * �ж��Ƿ��ǺϷ��ֻ���
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isPassWord(String pw) {
		// Pattern p = Pattern
		// .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(18[1,0-1]))\\d{8}$");
		Pattern p = Pattern.compile("^([A-Za-z]|[0-9])+$");
		Matcher m = p.matcher(pw);
		return m.matches();
	}

	/**
	 * �ж��Ƿ��ǺϷ��ֻ���
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isUrl(String url) {
		Pattern pattern = Pattern
				.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)"
						+ "(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+$");
		Matcher m = pattern.matcher(url);
		return m.matches();
	}
}
