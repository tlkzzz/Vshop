package com.Vshop.core.entity.base;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * admin log 日志表
 * 项目名称：Vshop-entity   
 * 类名称：AdminLog   
 * 类描述：   
 * 修改备注：   
 * @version    
 */
@Data
@ToString
public class AdminLog extends BaseEntity{

	/**
	 * 主键ID
	 */
	private Integer id;

	/**
	 * 记录操作内容
	 */
	private String content;
	
	/**
	 * 时间
	 */
	private Date createtime;
	
	/**
	 * 用户名称
	 */
	private String adminName;
	
	/**
	 * 用户ID
	 */
	private String adminId;
	
	/**
	 * ip
	 */
	private String ip;
	
	/**
	 * 操作URL
	 */
	private String url;
	
	
	/**
	 * 页面查询条件开始时间
	 */
	private String starttime;
	/**
	 * 页面查询条件结束时间
	 */
	private String endtime;
	
	
//	 /**
//     * 0:未删除;1.已删除
//     */
//    private int isDel;
//    /**
//     * 创建时间
//     */
//    private Long createdTime;
//    /**
//     * 更新时间
//     */
//    private Long updatedTime;
	
	
}
