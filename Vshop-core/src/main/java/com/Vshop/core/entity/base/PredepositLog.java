package com.Vshop.core.entity.base;

import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.BaseEntity;

/**
 * 预存款变更日志表
 * @author liukai
 */
@Data
@ToString
public class PredepositLog extends BaseEntity{
	
	//自增编号
	private Integer lgId;
	
	//会员编号
	private Integer lgMemberId;
	
	//会员名称
	private String lgMemberName;
	
	//管理员名称
	private String lgAdminName;
	
	//order_pay下单支付预存款,order_freeze下单冻结预存款,order_cancel取消订单解冻预存款,order_comb_pay下单支付被冻结的预存款,recharge充值,cash_apply申请提现冻结预存款,cash_pay提现成功,cash_del取消提现申请，解冻预存款,refund退款
	private String lgType;
	
	//可用金额变更0表示未变更
	private BigDecimal lgAvAmount;
	
	//冻结金额变更0表示未变更
	private BigDecimal lgFreezeAmount;
	
	//描述
	private String lgDesc;
}
