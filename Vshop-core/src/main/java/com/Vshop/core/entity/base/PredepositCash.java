package com.Vshop.core.entity.base;

import java.math.BigDecimal;


import lombok.Data;
import lombok.ToString;

/**
 * 预存款提现记录表
 * @author liukai
 */
@Data
@ToString
public class PredepositCash {
	
	//自增编号
	private Integer pdcId;
	
	//记录唯一标示
	private String pdcSn;
	
	//会员编号
	private Integer pdcMemberId;
	
	//会员名称
	private String pdcMemberName;
	
	//金额
	private BigDecimal pdcAmount;
	
	//收款银行
	private String pdcBankName;
	
	//收款账号
	private String pdcBankNo;
	
	//开户人姓名
	private String pdcBankUser;
	
	//添加时间
	private Long pdcAddTime;
	
	//付款时间
	private Long pdcPaymentTime;
	
	//提现支付状态 0默认1支付完成
	private String pdcPaymentState;
	
	//支付管理员
	private String pdcPaymentAdmin;

}
