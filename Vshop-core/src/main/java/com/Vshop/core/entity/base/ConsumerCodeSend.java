package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConsumerCodeSend extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5097798739533795906L;

	/**
	 * 自增ID
	 */
	private Integer consumerCodeId; // int(11) 主键
	
	/**
	 * 消费码
	 */
	private String consumerCodeBunch; // varchar(20)
	
	/**
	 * 码来源，1:自身，3：第三方直接提供
	 */
	private Integer codeSource; // int(1) 
	
	/**
	 * 发码人
	 */
	private Integer sendUser; // int(11) 发码人
	
	/**
	 * 订单Id
	 */
	private Integer orderId; // int(11) 
	
	/**
	 * 商品Id
	 */
	private Integer goodsId; // int(11) 
	
	/**
	 * 商品价格
	 */
	private Double goodsAmount; // decimal(10,2)
	
	/**
	 * 0,有有效期;1,永久有效
	 */
	private Integer validityStatus; // int(1)
	
	/**
	 * 有效期(天)
	 */
	private Integer validityTime; // int(11) 
	
	private Integer validityTimeStart;
	
	private Integer validityTimeEnd;
	
	/**
	 * 有效期至
	 */
	private Long validityEndTime; // bigint(13) 
	
	private Long validityEndTimeStart;
	
	private Long validityEndTimeEnd;
	
	/**
	 * 发码时间
	 */
	private Long sendTime; // bigint(13) 
	
	private Long sendTimeStart;
	
	private Long sendTimeEnd;
	
	/**
	 * 收码/撤回/作废时间
	 */
	private Long receiveTime; // bigint(13)
	
	private Long receiveTimeStart;
	
	private Long receiveTimeEnd;
	
	/**
	 * 1:已发,2:已收,3:过期作废,4撤回作废
	 */
	private Integer codeStatus; // int(1) 
	
	private String orderSn;  // 订单号

}
