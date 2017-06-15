package com.Vshop.core.entity.base;

import java.sql.Timestamp;

import com.Vshop.core.common.DateUtils;

public class BaseEntity {
	

	/**
	 * 创建时间-数据库字段
	 */
	private Long createTime;
	
	/**
	 * 修改时间-数据库字段
	 */
	private Long updateTime;
	
	/**
	 * 开始时间-数据库字段
	 */
	private Long  startTime;
	
	/**
	 * 结束时间-数据库字段
	 */
	private Long endTime;
	
	
	
	/**
	 * 创建时间－页面字段
	 */
	private Timestamp createTimeStr;
	
	/**
	 * 修改时间－页面字段
	 */
	private Timestamp updateTimeStr;
	
	/**
	 * 开始时间－页面字段
	 */
	private Timestamp startTimeStr;
	
	/**
	 * 结束时间－页面字段
	 */
	private Timestamp endTimeStr;

	 /**
     * 0:未删除;1.已删除
     */
    private int isDel;
    
    /**
   	 * 上架时间
   	 */
   	 private Long upForTime;
   	 
   	 /**
   	 * 日期格式上架时间
   	 */
   	private Timestamp upForTimeStr;
	
   	/**
   	 * 下架时间
   	 */
   	 private Long downForTime;
   	 
   	 /**
   	 * 日期格式下架时间
   	 */
   	private Timestamp downForTimeStr;
   	
   	
   	 
   	
    
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
		if(null != createTime){
			createTimeStr = DateUtils.getTimestampByLong(createTime);
			this.createTimeStr = createTimeStr;
		}
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
		if(null != updateTime){
			updateTimeStr = DateUtils.getTimestampByLong(updateTime);
			this.updateTimeStr = updateTimeStr;
		}
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public Timestamp getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(Timestamp createTimeStr) {
		if(null!=createTime){
			createTimeStr = DateUtils.getTimestampByLong(createTime);
		}
		this.createTimeStr = createTimeStr;
	}

	public Timestamp getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(Timestamp updateTimeStr) {
		if(null!=updateTime){
			updateTimeStr = DateUtils.getTimestampByLong(updateTime);
		}
		this.updateTimeStr = updateTimeStr;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
		if(null != startTime){
			this.startTimeStr = DateUtils.getTimestampByLong(startTime);
			//this.startTimeStr = startTimeStr;
		}
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
		if(null != endTime){
			this.endTimeStr = DateUtils.getTimestampByLong(endTime);
			//this.endTimeStr = endTimeStr;
		}
	}

	public Timestamp getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(Timestamp startTimeStr) {
		if(startTime!=null)
		startTimeStr = DateUtils.getTimestampByLong(startTime);
		this.startTimeStr = startTimeStr;
	}

	public Timestamp getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(Timestamp endTimeStr) {
		if(endTime!=null)
		endTimeStr = DateUtils.getTimestampByLong(endTime);
		this.endTimeStr = endTimeStr;
	}
	
	public Long getUpForTime(){
   		return upForTime;
   	}
   	
   	public void setUpForTime(Long upForTime){
   		this.upForTime = upForTime;
   		if(upForTime != null){
   			upForTimeStr = DateUtils.getTimestampByLong(upForTime);
   		}
   		this.upForTimeStr = upForTimeStr;
   	}
   	
   	public Timestamp getUpForTimeStr(){
   		return upForTimeStr;
   	}
   	
   	public void setUpForTimeStr(Timestamp upForTimeStr){
   		if(upForTimeStr != null){
   			upForTimeStr = DateUtils.getTimestampByLong(upForTime);
   		}
   		this.upForTimeStr = upForTimeStr;
   	}
   	
   	public Long getDownForTime(){
   		return downForTime;
   	}
   	
   	public void setDownForTime(Long downForTime){
   		this.downForTime = downForTime;
   		if(downForTime != null){
   			downForTimeStr = DateUtils.getTimestampByLong(downForTime);
   		}
   		this.downForTimeStr = downForTimeStr;
   	}
   	
   	public Timestamp getDownForTimeStr(){
   		return downForTimeStr;
   	}
   	
   	public void setDownForTimeStr(Timestamp downForTimeStr){
   		if(downForTimeStr != null){
   			downForTimeStr = DateUtils.getTimestampByLong(downForTime);
   		}
   		this.downForTimeStr = downForTimeStr;
   	}
	
	
}
