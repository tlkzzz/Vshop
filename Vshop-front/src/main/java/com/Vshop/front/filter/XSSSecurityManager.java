package com.Vshop.front.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

/**
 * @author winnie
 * @date
 * @describe 安全过滤配置管理类，由XSSSecurityManger修改
 */
public class XSSSecurityManager {

	private static Logger logger = Logger.getLogger(XSSSecurityManager.class);

	/**
	 * REGEX：校验正则表达式
	 */
	public static String REGEX;

	public static String[] REGEXS = new String[15];

	/**
	 * 特殊字符匹配
	 */
	public static Pattern XSS_PATTERN;

	private XSSSecurityManager() {
		// 不可被实例化
	}

	/**
	 * 读取安全审核配置文件xss_security_config.xml 设置XSSSecurityConfig配置信息
	 * 
	 * @param path
	 *            配置文件地址 eg C:/apache-tomcat-6.0.33/webapps/security_filter/WebRoot/config/xss/xss_security_config.xml
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static void init() {
		logger.info("XSSSecurityManager.initConfig(String path) begin");

		REGEXS[0] = ".*[A|a][L|l][E|e][R|r][T|t]\\s*\\(.*\\).*";
		// <!-- 匹配含有字符： window.location = -->
		REGEXS[1] = ".*[W|w][I|i][N|n][D|d][O|o][W|w]\\.[L|l][O|o][C|c][A|a][T|t][I|i][O|o][N|n]\\s*=.*";
		// <!-- 匹配含有字符：style = x:ex pression ( ) -->
		REGEXS[2] = ".*[S|s][T|t][Y|y][L|l][E|e]\\s*=.*[X|x]:[E|e][X|x].*[P|p][R|r][E|e][S|s]{1,2}[I|i][O|o][N|n]\\s*\\(.*\\).*";
		// <!-- 匹配含有字符： document.cookie -->
		REGEXS[3] = ".*[D|d][O|o][C|c][U|u][M|m][E|e][N|n][T|t]\\.[C|c][O|o]{2}[K|k][I|i][E|e].*";
		// <!-- 匹配含有字符： eval( ) -->
		REGEXS[4] = ".*[E|e][V|v][A|a][L|l]\\s*\\(.*\\).*";
		// <!-- 匹配含有字符： unescape() -->
		REGEXS[5] = ".*[U|u][N|n][E|e][S|s][C|c][A|a][P|p][E|e]\\s*\\(.*\\).*";
		// <!-- 匹配含有字符： execscript( ) -->
		REGEXS[6] = ".*[E|e][X|x][E|e][C|c][S|s][C|c][R|r][I|i][P|p][T|t]\\s*\\(.*\\).*";
		// <!-- 匹配含有字符： msgbox( ) -->
		REGEXS[7] = ".*[M|m][S|s][G|g][B|b][O|o][X|x]\\s*\\(.*\\).*";
		// <!-- 匹配含有字符： confirm( ) -->
		REGEXS[8] = ".*[C|c][O|o][N|n][F|f][I|i][R|r][M|m]\\s*\\(.*\\).*";
		// <!-- 匹配含有字符： prompt( ) -->
		REGEXS[9] = ".*[P|p][R|r][O|o][M|m][P|p][T|t]\\s*\\(.*\\).*";
		// <!-- 匹配含有字符： <script> </script> -->
		REGEXS[10] = ".*<[S|s][C|c][R|r][I|i][P|p][T|t]>.*</[S|s][C|c][R|r][I|i][P|p][T|t]>.*";
		// <!-- 匹配含有字符： 含有一个符号： " -->
		REGEXS[11] = "[.&[^\"]]*\"[.&[^\"]]*";
		// <!-- 匹配含有字符： 含有一个符号： ' -->
		REGEXS[12] = "[.&[^']]*'[.&[^']]*";
		// <!-- 匹配含有字符： 含有回车换行 和 <script> </script> -->
		REGEXS[13] = ".&[^a]]|[|a|\n|\r\n|\r|\u0085|\u2028|\u2029]]*<[S|s][C|c][R|r][I|i][P|p][T|t]>.*</[S|s][C|c][R|r][I|i][P|p][T|t]>[[.&[^a]]|[|a|\n|\r\n|\r|\u0085|\u2028|\u2029]]*";
		// <!-- 匹配特殊sql字符 -->
		REGEXS[14] = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" + "(.*\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b.*)";

		
		StringBuffer tempStr = new StringBuffer("^");
		for (String tmp : REGEXS) {
			tmp = tmp.replaceAll("\\\\\\\\", "\\\\");
			tempStr.append(tmp);
			tempStr.append("|");
		}
		if (tempStr.charAt(tempStr.length() - 1) == '|') {
			REGEX = tempStr.substring(0, tempStr.length() - 1) + "$";
			logger.info("安全匹配规则" + REGEX);
		} else {
			logger.error("安全过滤配置文件加载失败:正则表达式异常 " + tempStr.toString());
		}
		// 生成匹配器   
		XSS_PATTERN = Pattern.compile(REGEX);  

		logger.info("XSSSecurityManager.initConfig(String path) end");
	}

	/**
	 * 对非法字符进行替换
	 * 
	 * @param text
	 * @return
	 */
	public static String securityReplace(String text) {
		if (isNullStr(text)) {
			return text;
		} else {
			return text.replaceAll(REGEX, XSSSecurityConfig.REPLACEMENT);
		}
	}

	/**
	 * 匹配字符是否含特殊字符
	 * 
	 * @param text
	 * @return
	 */
	public static boolean matches(String text) {
		if (text == null) {
			return false;
		}
		return XSS_PATTERN.matcher(text).matches();
	}

	/**
	 * 判断是否为空串，建议放到某个工具类中
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNullStr(String value) {
		return value == null || value.trim().equals("");
	}
	
	
	private static Pattern PATH_PATTERN = Pattern.compile("^[A-Za-z0-9_-|\\/|\\.]*$");  
	/**
	 * 地址必须是字母数字下划线减号
	 * @param str
	 * @return
	 */
	public static boolean checkPageLink(String path){
		if (path == null) {
			return false;
		}
		return PATH_PATTERN.matcher(path).matches();
	}
	
	public static String getBasePath(HttpServletRequest request){
		String path = request.getContextPath();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	}
	
	public static void main(String[] args) {
		try {

			try {
//				init();
//				XSS_PATTERN = Pattern.compile("(.*\\bselect\\b.*)");  
				System.out.println(matches("%3Cscript+%3E%5Bwindow%5B%27location%27%5D%3D%27%5Cx6a%5Cx61%5Cx76%5Cx61%5Cx73%5Cx63%5Cx72%5Cx69%5Cx70%5Cx74%5Cx3a%5Cx61%5Cx6c%5Cx65%5Cx72%5Cx74%5Cx2833%5Cx29%27%5D%3C%2Fscript%3E"));
				
//				System.out.println(checkPageLink("/manager/CoreUser/list.do"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}