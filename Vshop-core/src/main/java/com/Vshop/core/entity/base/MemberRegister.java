package com.Vshop.core.entity.base;



import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberRegister {

	/**
	 * 会员id
	 */
	private Integer memberId;
	
	/**
	 * 会员名称
	 */
	private String memberName;
	
	/**
	 * 注册时间
	 */
	private String finnshedTime;
	
	/**
	 * 注册数量
	 */
	private Integer memberCount;
	
	/**
	 * 时间条件
	 * 按这周查:week
	 * 按这个月查:month
	 * 按今年年查:year
	 */
	private String condition;
	
	/**
	 * 开始时间
	 */
	private Long startTime;
	
	/**
	 * 结束时间
	 */
	private Long endTime;
}
