/**
 * 
 */
package com.Vshop.front.module.tag.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>Title: ParamsUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月2日
 * @version 1.0
 */
public class ParamsUtils {
	
	/**
	 * 返回字符串
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj){
		String ob = "";
		if(obj != null){ 
			ob = obj.toString();
		}
		if(ob == "null" || ob==""){
			return "";
		}
		return ob;
	}
	
	/**
	 * 返回 int
	 * @param obj
	 * @return
	 */
	public static int getInt(Object obj){
		String str = "";
		if(obj != null) str = obj.toString();
		
		if(str!="null" && StringUtils.isNotEmpty(str)) {
			int ob =  Integer.valueOf(str);
			return ob;
		}
		return 0;
	}

	/**
	 * 返回long
	 * @param obj
	 * @return
	 */
	public static long getLong(Object obj){
		String str = "";
		if(obj != null) str = obj.toString();
		
		if(str!="null" && StringUtils.isNotEmpty(str)) {
			long ob = Long.valueOf(str);
			return ob;
		}
		return 0;
	}
}
