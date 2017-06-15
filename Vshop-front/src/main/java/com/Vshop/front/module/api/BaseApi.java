/**
 * 
 */
package com.Vshop.front.module.api;

import net.sf.json.JSONObject;

/**
 * <p>Title: BaseApi.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月10日
 * @version 1.0
 */
public class BaseApi {
	
//	public String msg = "获取失败";
//	
//	public String data = "";
//	
//	private int result = 0;
	
	public JSONObject jsonObj ;

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}


}
