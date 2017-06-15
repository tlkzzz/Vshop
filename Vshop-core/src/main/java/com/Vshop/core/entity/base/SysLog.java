package com.Vshop.core.entity.base;


import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * 系统日志表
 * 项目名称：Vshop-entity   
 * 类名称：SysLog   
 * 类描述：平台日志  
 * 修改备注：   
 * @version    
 */
@Data
@ToString
public class SysLog extends BaseEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7274802539154924826L;

	/**
	 * 主键ID
	 */
	private Integer id;
	
	/**
	 * 类型
	 */
	private String type;

	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 操作人
	 */
	private String createBy;
	
	/**
	 * 操作人ip地址
	 */
	private String remoteAddr;
	
	/**
	 * 操作URL
	 */
	private String userAgent;
	
	/**
	 * 操作uri
	 */
	private String requestUri;
	
	/**
	 * 方法
	 */
	private String method;
	
	/**
	 * 参数
	 */
	private String params;
	
	/**
	 * 异常信息
	 */
	private String exception;
	
	// 日志类型（1：接入日志；2：错误日志）
	public static final String TYPE_ACCESS = "1";
	public static final String TYPE_EXCEPTION = "2";
	
	
}
