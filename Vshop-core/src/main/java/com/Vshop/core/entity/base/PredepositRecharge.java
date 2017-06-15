package com.Vshop.core.entity.base;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.BaseEntity;

/**
 * 预存款充值表
 * @author liukai
 */
@Data
@ToString
public class PredepositRecharge extends BaseEntity{
	
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
	
	//支付方式
	private String pdrPaymentCode;
	
	//支付方式名称
	private String pdrPaymentName;
	
	//第三方支付接口交易号
	private String pdrTradeSn;
	
	//支付状态 0未支付1支付
	private String pdrPaymentState;
	
	//支付时间
	private Long pdrPaymentTime;
	
	//支付时间-页面字段
	private Timestamp pdrPaymentTimeStr;
	
	//管理员名
	private String pdrAdmin;

	public Long getPdrPaymentTime() {
		return pdrPaymentTime;
	}

	public void setPdrPaymentTime(Long pdrPaymentTime) {
		this.pdrPaymentTime = pdrPaymentTime;
		pdrPaymentTimeStr = DateUtils.getTimestampByLong(pdrPaymentTime);
		this.pdrPaymentTimeStr = pdrPaymentTimeStr;
	}

	public Timestamp getPdrPaymentTimeStr() {
		return pdrPaymentTimeStr;
	}

	public void setPdrPaymentTimeStr(Timestamp pdrPaymentTimeStr) {
		pdrPaymentTimeStr = DateUtils.getTimestampByLong(pdrPaymentTime);
		this.pdrPaymentTimeStr = pdrPaymentTimeStr;
	}
	
}
