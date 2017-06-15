package com.Vshop.union.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * 接口返回值
 * 
 * @author liuzhen
 * @version 2015-9-24
 */
@Data
@ToString
public class APIResult implements Serializable {

	private static final long serialVersionUID = 1L;

	public APIResult(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public APIResult() {
	}

	public String code;
	public String msg;
}
