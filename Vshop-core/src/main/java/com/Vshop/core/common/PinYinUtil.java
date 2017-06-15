package com.Vshop.core.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {
	/**
	 * 将字符串转换成拼音数组
	 * 
	 * @param src
	 * @param isPolyphone
	 *            是否查出多音字的所有拼音
	 * @param separator
	 *            多音字拼音之间的分隔符
	 * @return
	 */
	public static String[] stringToPinyin(String src, boolean isPolyphone,
			String separator) {
		// 判断字符串是否为空
		if ("".equals(src) || null == src) {
			return null;
		} else {
			src = src.replaceAll(" ", "");
		}
		char[] srcChar = src.toCharArray();
		int srcCount = srcChar.length;
		String[] srcStr = new String[srcCount];

		for (int i = 0; i < srcCount; i++) {
			srcStr[i] = charToPinyin(srcChar[i], isPolyphone, separator);
		}
		return srcStr;
	}

	/**
	 * 将单个字符转换成拼音
	 * 
	 * @param src
	 * @return
	 */
	public static String charToPinyin(char src, boolean isPolyphone,
			String separator) {
		// 创建汉语拼音处理类
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		// 输出设置，大小写，音标方式
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		StringBuffer tempPinying = new StringBuffer();

		// 如果是中文
		if (src > 128) {
			try {
				// 转换得出结果
				String[] strs = PinyinHelper.toHanyuPinyinStringArray(src,
						defaultFormat);

				// 是否查出多音字，默认是查出多音字的第一个字符
				if (isPolyphone && null != separator) {
					for (int i = 0; i < strs.length; i++) {
						tempPinying.append(strs[i]);
						if (strs.length != (i + 1)) {
							// 多音字之间用特殊符号间隔起来
							tempPinying.append(separator);
						}
					}
				} else {
					tempPinying.append(strs[0]);
				}

			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}
		} else {
			tempPinying.append(src);
		}
		return tempPinying.toString();
	}

	/**
	 * 汉字转拼音
	 * 
	 * @param name
	 * @return
	 */
	public static String getPingYin(String name) {
		String[] str = stringToPinyin(name, false, null);
		String pinyin = "";
		for (int i = 0; i < str.length; i++) {
			pinyin += str[i];
		}
		return pinyin;
	}

	// 简体中文的编码范围从B0A1（45217）一直到F7FE（63486）
	private static int BEGIN = 45217;
	private static int END = 63486;

	// 按照声 母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字。
	// i, u, v都不做声母, 自定规则跟随前面的字母
	private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈',
			'哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌',
			'塌', '挖', '昔', '压', '匝', };

	// 二十六个字母区间对应二十七个端点
	// GB2312码汉字区间十进制表示
	private static int[] table = new int[27];

	// 对应首字母区间表
	private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			't', 't', 'w', 'x', 'y', 'z', };

	// 初始化
	static {
		for (int i = 0; i < 26; i++) {
			table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
		}
		table[26] = END;// 区间表结尾
	}

	// ------------------------public方法区------------------------
	// 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出

	public static String cn2py(String SourceStr) {
		String Result = "";
		int StrLength = SourceStr.length();
		int i;
		try {
			for (i = 0; i < StrLength; i++) {
				Result += Char2Initial(SourceStr.charAt(i));
			}
		} catch (Exception e) {
			Result = "";
			e.printStackTrace();
		}
		return Result;
	}

	// ------------------------private方法区------------------------
	/**
	 * 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0' 　　* 　　
	 */
	private static char Char2Initial(char ch) {
		// 对英文字母的处理：小写字母转换为大写，大写的直接返回
		if (ch >= 'a' && ch <= 'z') {
			return (char) (ch - 'a' + 'A');
		}
		if (ch >= 'A' && ch <= 'Z') {
			return ch;
		}
		// 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，
		// 若不是，则直接返回。
		// 若是，则在码表内的进行判断。
		int gb = gbValue(ch);// 汉字转换首字母
		if ((gb < BEGIN) || (gb > END))// 在码表区间之前，直接返回
		{
			return ch;
		}
		int i;
		for (i = 0; i < 26; i++) {// 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
			if ((gb >= table[i]) && (gb < table[i + 1])) {
				break;
			}
		}
		if (gb == END) {// 补上GB2312区间最右端
			i = 25;
		}
		return initialtable[i]; // 在码表区间中，返回首字母
	}

	/**
	 * 取出汉字的编码 cn 汉字 　　
	 */
	private static int gbValue(char ch) {// 将一个汉字（GB2312）转换为十进制表示。
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2) {
				return 0;
			}
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取汉字串拼音首字母，英文字符不变
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音首字母
	 */
	public static String getFirstSpell(String chinese) {
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(
							arr[i], defaultFormat);
					if (temp != null) {
						pybf.append(temp[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}

	/**
	 * 获取汉字串拼音，英文字符不变
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音
	 */
	public static String getFullSpell(String chinese) {
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i],
							defaultFormat)[0]);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString();
	}

	public static void main(String[] args) {
		System.out.println(getPingYin("中文测试变首字母"));
		// System.out.println(TestName.stringArrayToString(getHeadByString("我的心肝爱上"),"-"));
		/*
		 * String[] str = null; String name = "你是      我的"; String pinyin = "";
		 * str = name.split(""); str = stringToPinyin(name,false,null);
		 * //System.out.println(TestName.stringToPinyin(name)); for(int i=0; i <
		 * str.length; i++){ pinyin += str[i]; } System.out.println(pinyin);
		 */
		// stringToPinyin
	}
}
