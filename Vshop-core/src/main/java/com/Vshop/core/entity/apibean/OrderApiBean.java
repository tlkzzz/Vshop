package com.Vshop.core.entity.apibean;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.OrderGoods;

/**
 * 订单接口实体
 * @author liukai
 */
@Data
@ToString
public class OrderApiBean {
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
	 * 订单生成时间
	 */
	private Long addTime;
	
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
	 * 订单编号，外部支付时使用，有些外部支付系统要求特定的订单编号
	 */
	private String outSn;
	
	/**
	 * 支付留言
	 */
	private String payMessage;
	
	/**
	 * 配送时间
	 */
	private Long shippingTime;
	
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
	 * 发票信息
	 */
	private String invoice;
	
	/**
	 * 商品总价格
	 */
	private BigDecimal goodsAmount;
	
	/**
	 * 订单总价格
	 */
	private BigDecimal orderAmount;
	
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
	 * 订单留言
	 */
	private String orderMessage;
	
	/**
	 * 订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
	 */
	private Integer orderState;
	
	/**
	 * 代金券id
	 */
	private Integer voucherId;
	
	/**
	 * 退款状态:0是无退款,1是部分退款,2是全部退款
	 */
	private Integer refundState;
	
	/**
	 * 退货状态:0是无退货,1是部分退货,2是全部退货
	 */
	private Integer returnState;
	
	/**
	 * 退货数量
	 */
	private Integer returnNum;
	
	/**
	 * 订单商品(订单项)
	 */
	private List<OrderGoods> orderGoodsList;
    
    /**
     * 订单支付表id
     */
    private Integer payId;
    
    /**
     * 订单支付表编号
     */
    private String paySn;
    
}
