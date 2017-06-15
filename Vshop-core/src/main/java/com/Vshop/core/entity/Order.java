package com.Vshop.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.BaseEntity;
import com.Vshop.core.entity.base.OrderAddress;
import com.Vshop.core.entity.base.OrderGoods;
import com.Vshop.core.entity.base.OrderLog;
import com.Vshop.core.entity.base.RefundLog;
import com.Vshop.core.entity.base.ReturnOrder;

import lombok.Data;
import lombok.ToString;
import lombok.extern.java.Log;

/**
 * ShopOrder entity. @author MyEclipse Persistence Tools
 */
@Data
@ToString
@Log
public class Order extends BaseEntity implements Serializable{
	/**
	 *  实体序列化id
	 */
	private static final long serialVersionUID = -227066370980514932L;

	/**
	 * 订单索引id
	 */
	private Integer orderId;
	
	/**
	 * 订单编号，商城内部使用
	 */
	private String orderSn;
	
	/**
	 * 卖家店铺id
	 */
	private Integer storeId;
	
	
	/**
	 * 供应商 id
	 */
	private Integer supplierId;
	
	/**
	 * 卖家店铺名称
	 */
	private String storeName;
	
	/**
	 * 买家id
	 */
	private Integer buyerId;
	
	/**
	 * 买家姓名
	 */
	private String buyerName;
	
	/**
	 * 买家电子邮箱
	 */
	private String buyerEmail;
	
	/**
	 * 订单类型 0.普通 1.团购
	 */
	private Integer orderType;
	
	/**
	 * 支付方式id
	 */
	private Integer paymentId;
	
	/**
	 * 支付方式名称
	 */
	private String paymentName;
	
	/**
	 * 支付方式名称代码
	 */
	private String paymentCode;
	
	/**
	 * 支付类型:1是即时到帐,2是担保交易
	 */
	private String paymentDirect;
	
	/**
	 * 付款状态:0:未付款;1:已付款
	 */
	private Integer paymentState;
	
	/**
	 * 支付(付款)时间
	 */
	private Long paymentTime;
	
	/**
	 * 支付(付款)时间-页面字段
	 */
	private Timestamp paymentTimeStr;
	
	/**
	 * 订单编号，外部支付时使用，有些外部支付系统要求特定的订单编号
	 */
	private String outSn;
	
	/**
	 * 外部交易平台单独使用的标识字符串
	 */
	private String outPaymentCode;
	
	/**
	 * 支付留言
	 */
	private String payMessage;
	
	/**
	 * 配送时间
	 */
	private Long shippingTime;
	
	/**
	 * 配送时间-页面字段
	 */
	private Timestamp shippingTimeStr;
	
	/**
	 * 配送公司ID
	 */
	private Integer shippingExpressId;
	
	/**
	 * 配送公司编号
	 */
	private String shippingExpressCode;
	
	/**
	 * 物流单号
	 */
	private String shippingCode;
	
	/**
	 * 订单完成时间
	 */
	private Long finnshedTime;
	
	/**
	 * 订单完成时间-页面字段
	 */
	private Timestamp finnshedTimeStr;
	
	/**
	 * 发票信息
	 */
	private String invoice;
	
	/**
	 * 商品总价格
	 */
	private BigDecimal goodsAmount;
	
	/**
	 * 折扣价格
	 */
	private BigDecimal discount;
	
	/**
	 * 订单应付金额
	 */
	private BigDecimal orderAmount;
	
	/**
	 * 订单总价格
	 */
	private BigDecimal orderTotalPrice;
	
	/**
	 * 余额支付金额
	 */
	private BigDecimal predepositAmount;
	
	/**
	 * 运费价格
	 */
	private BigDecimal shippingFee;
	
	/**
	 * 配送方式
	 */
	private String shippingName;
	
	/**
	 * 评价状态 0为评价，1已评价
	 */
	private Integer evaluationStatus;
	
	/**
	 * 评价时间
	 */
	private Long evaluationTime;
	
	/**
	 * 评价时间-页面字段
	 */
	private Timestamp evaluationTimeStr;
	
	/**
	 * 卖家是否已评价买家
	 */
	private Integer evalsellerStatus;
	
	/**
	 * 卖家评价买家的时间
	 */
	private Long evalsellerTime;
	
	/**
	 * 卖家评价买家的时间-页面字段
	 */
	private Timestamp evalsellerTimeStr;
	
	/**
	 * 订单留言
	 */
	private String orderMessage;
	
	/**
	 * 订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
	 */
	private Integer orderState;
	
	/**
	 * 订单赠送积分
	 */
	private Integer orderPointscount;
	
	/**
	 * 代金券id
	 */
	private Integer voucherId;
	
	/**
	 * 代金券面额
	 */
	private BigDecimal voucherPrice;
	
	/**
	 * 代金券编码
	 */
	private String voucherCode;
	
	/**
	 * 退款状态:0是无退款,1是部分退款,2是全部退款
	 */
	private Integer refundState;
	
	/**
	 * 退货状态:0是无退货,1是部分退货,2是全部退货
	 */
	private Integer returnState;
	
	/**
	 * 退款金额
	 */
	private BigDecimal refundAmount;
	
	/**
	 * 退货数量
	 */
	private Integer returnNum;
	
	/**
	 * 团购编号(非团购订单为0)
	 */
	private Integer groupId;
	
	/**
	 * 团购数量
	 */
	private Integer groupCount;
	
	/**
	 *  限时折扣编号
	 */
	private Integer xianshiId;
	
	/**
	 * 限时折扣说明
	 */
	private String xianshiExplain;
	
	/**
	 * 满就送编号
	 */
	private Integer mansongId;
	
	/**
	 * 满就送说明
	 */
	private String mansongExplain;
	
	/**
	 * 搭配套餐id
	 */
	private Integer bundlingId;
	
	/**
	 * 搭配套餐说明
	 */
	private String bundlingExplain;
	
	/**
	 * 1PC2手机端
	 */
	private String orderFrom;
	
	/**
	 * 订单取消原因
	 */
	private String cancelCause;
	
	/**
	 * 发货备注
	 */
	private String deliverExplain;
	
	/**
	 * 发货地址ID
	 */
	private Integer daddressId;
	
	/**
	 * 订单商品(订单项)
	 */
	private List<OrderGoods> orderGoodsList;
	
	/**
	 * 订单日志
	 */
	private List<OrderLog> orderLogList;
	
	/**
	 * 订单收货地址
	 */
	private OrderAddress address;
	
	/**
	 * 订单退款信息
	 */
	private RefundLog refundLog; 
	
	/**
	 * 订单退货信息
	 */
	private ReturnOrder returnOrder;
	
    /**
     * 收货地址id
     */
    private Integer addressId;
    
    /**
     * 订单支付表id
     */
    private Integer payId;
    
    /**
     * 订单支付表编号
     */
    private String paySn;
    
    /**
     * 结算状态:0未结算,1已结算
     */
    private Integer balanceState;
    
    /**
     * 结算时间
     */
    private Long balanceTime;
    
    /**
     * 结算时间-页面字段
     */
    private Timestamp balanceTimeStr;
    
    /**
	 * 订单总佣金
	 */
	private BigDecimal totalCommission;
    
    /**
     * 多个订单状态查询字段,查询时若与orderState字段同时使用,那么orderStates数组中必须包含与orderState相同元素
     * 建议:若查询时不与orderState字段同时使用
     */
    private int[] orderStates;
    
    public Long getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(Long shippingTime) {
		this.shippingTime = shippingTime;
		shippingTimeStr = DateUtils.getTimestampByLong(shippingTime);
		this.shippingTimeStr = shippingTimeStr;
	}

	public Timestamp getShippingTimeStr() {
		return shippingTimeStr;
	}

	public void setShippingTimeStr(Timestamp shippingTimeStr) {
		shippingTimeStr = DateUtils.getTimestampByLong(shippingTime);
		this.shippingTimeStr = shippingTimeStr;
	}

	public Long getFinnshedTime() {
		return finnshedTime;
	}

	public void setFinnshedTime(Long finnshedTime) {
		this.finnshedTime = finnshedTime;
		finnshedTimeStr = DateUtils.getTimestampByLong(finnshedTime);
		this.finnshedTimeStr = finnshedTimeStr;
	}

	public Timestamp getFinnshedTimeStr() {
		return finnshedTimeStr;
	}

	public void setFinnshedTimeStr(Timestamp finnshedTimeStr) {
		finnshedTimeStr = DateUtils.getTimestampByLong(finnshedTime);
		this.finnshedTimeStr = finnshedTimeStr;
	}

	public Long getEvaluationTime() {
		return evaluationTime;
	}

	public void setEvaluationTime(Long evaluationTime) {
		this.evaluationTime = evaluationTime;
		evaluationTimeStr = DateUtils.getTimestampByLong(evaluationTime);
		this.evaluationTimeStr = evaluationTimeStr;
	}

	public Timestamp getEvaluationTimeStr() {
		return evaluationTimeStr;
	}

	public void setEvaluationTimeStr(Timestamp evaluationTimeStr) {
		evaluationTimeStr = DateUtils.getTimestampByLong(evaluationTime);
		this.evaluationTimeStr = evaluationTimeStr;
	}

	public Long getEvalsellerTime() {
		return evalsellerTime;
	}

	public void setEvalsellerTime(Long evalsellerTime) {
		this.evalsellerTime = evalsellerTime;
		evalsellerTimeStr = DateUtils.getTimestampByLong(evalsellerTime);
		this.evalsellerTimeStr = evalsellerTimeStr;
	}

	public Timestamp getEvalsellerTimeStr() {
		return evalsellerTimeStr;
	}

	public void setEvalsellerTimeStr(Timestamp evalsellerTimeStr) {
		evalsellerTimeStr = DateUtils.getTimestampByLong(evalsellerTime);
		this.evalsellerTimeStr = evalsellerTimeStr;
	}

	public Long getBalanceTime() {
		return balanceTime;
	}

	public void setBalanceTime(Long balanceTime) {
		this.balanceTime = balanceTime;
		balanceTimeStr = DateUtils.getTimestampByLong(balanceTime);
		this.balanceTimeStr = balanceTimeStr;
	}

	public Timestamp getBalanceTimeStr() {
		return balanceTimeStr;
	}

	public void setBalanceTimeStr(Timestamp balanceTimeStr) {
		balanceTimeStr = DateUtils.getTimestampByLong(balanceTime);
		this.balanceTimeStr = balanceTimeStr;
	}
	
	public Long getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Long paymentTime) {
		this.paymentTime = paymentTime;
		paymentTimeStr = DateUtils.getTimestampByLong(paymentTime);
		this.paymentTimeStr = paymentTimeStr;
	}

	public Timestamp getPaymentTimeStr() {
		return paymentTimeStr;
	}

	public void setPaymentTimeStr(Timestamp paymentTimeStr) {
		paymentTimeStr = DateUtils.getTimestampByLong(paymentTime);
		this.paymentTimeStr = paymentTimeStr;
	}

	public BigDecimal getTotalCommission() {
		if (orderGoodsList != null && !orderGoodsList.isEmpty()) {
			if (totalCommission == null || totalCommission.intValue() == 0) {
				totalCommission = new BigDecimal(0);
				for (OrderGoods orderGoods : orderGoodsList) {
					if (orderGoods != null) {
						try {
							totalCommission = (orderGoods.getGoodsCommission()
									.multiply(new BigDecimal(orderGoods.getGoodsNum()))).add(totalCommission);
						} catch (Exception ex) {
							log.info(ex.toString());
						}
					}
				}
				return totalCommission;
			}
		}
		
		totalCommission = new BigDecimal(0);
		
		return totalCommission;
	}

	public void setTotalCommission(BigDecimal totalCommission) {
		this.totalCommission = totalCommission;
	}
	
}