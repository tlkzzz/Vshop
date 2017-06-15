package com.Vshop.core.entity.apibean;

import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

/**
 * 余额支付api实体
 * @author liukai
 */
@Data
@ToString
public class PredepositRechargeApiBean {
	
	//自增编号
	private Integer pdrId;
	
	//记录唯一标示
	private String pdrSn;
	
	//会员编号
	private Integer pdrMemberId;
	
	//会员名称
	private String pdrMemberName;
	
	//充值金额
	private BigDecimal pdrAmount;
}
