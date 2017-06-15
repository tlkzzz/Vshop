package com.Vshop.core.entity.base;


import lombok.Data;
import lombok.ToString;

/**
 * Seller log 日志表
 * 项目名称：gt-entity   
 * 类名称：SellerLog   
 * 类描述：   
 * 修改备注：   
 * @version    
 */
@Data
@ToString
public class SellerLog {

	/**
	 * 日志编号
	 */
	private Integer logId;

	/**
	 * 日志内容
	 */
	private String logContent;
	
	/**
	 * 日志时间
	 */
	private Long logTime;
	
	/**
	 * 卖家编号
	 */
	private int logSellerId;
	
	/**
	 * 卖家帐号
	 */
	private String logSellerName;
	
	/**
	 * 店铺编号
	 */
	private int logStoreId;
	
	/**
	 * 卖家ip
	 */
	private String logSellerIp;
	/**
	 * 日志url
	 */
	private String logUrl;
	
	 /**
     * 日志状态(0-失败 1-成功)
     */
    private int logState;

	/**
	 * 页面查询条件开始时间
	 */
	private Long startTime;
	/**
	 * 页面查询条件结束时间
	 */
	private Long endTime;
	
	
}
