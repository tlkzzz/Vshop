/**
 * 
 */
package com.Vshop.core.entity.base;

import java.io.Serializable;
import java.sql.Timestamp;

import com.Vshop.core.common.DateUtils;

import lombok.Data;
import lombok.ToString;

/**
 * <p>Title: Adv.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
@Data
@ToString
public class Adv implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2133307607172848881L;

	/**
	 * 广告自增标识编号
	 */
	private Integer advId;
	
	/**
	 * 广告位id
	 */
	private Integer apId;
	
	/**
	 * 广告标题
	 */
	private String advTitle;
	
	/**
	 * 广告url
	 */
	private String advUrl;
	
	/**
	 * 结束时间
	 */
	private Long endDate;
	
	/**
	 * 开始时间
	 */
	private Long startDate;
	
	/**
	 * 点击次数
	 */
	private Integer clickNum;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 资源路径
	 */
	private String resUrl;
	
	/**
	 * 广告背景颜色
	 */
	private String advBgcolor;
	
	/**
	 * 会员购买的广告是否通过审核0未审核1审核已通过2审核未通过
	 */
	private Integer isAllow;
	
	/**
	 * 开始时间－页面字段
	 */
	private Timestamp startDateStr;
	
	/**
	 * 结束时间－页面字段
	 */
	private Timestamp endDateStr;

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
		if(null != startDate){
			startDateStr = DateUtils.getTimestampByLong(startDate);
			this.startDateStr = startDateStr;
		}
	}

	public Timestamp getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(Timestamp startDateStr) {
		startDateStr = DateUtils.getTimestampByLong(startDate);
		this.startDateStr = startDateStr;
	}
	
	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
		if(null != endDate){
			endDateStr = DateUtils.getTimestampByLong(endDate);
			this.endDateStr = endDateStr;
		}
	}

	public Timestamp getEndtDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(Timestamp endDateStr) {
		endDateStr = DateUtils.getTimestampByLong(endDate);
		this.endDateStr = endDateStr;
	}
	
}
