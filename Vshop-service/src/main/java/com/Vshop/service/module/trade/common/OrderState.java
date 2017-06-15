package com.Vshop.service.module.trade.common;

public final class OrderState {
	
	/**
	 * 订单状态已取消
	 */
	public final static int ORDER_STATE_CANCLE = 0;
	
	/**
	 * 订单状态待付款
	 */
	public final static int ORDER_STATE_NO_PATMENT = 10;
	
	/**
	 * 订单状态待发货
	 */
	public final static int ORDER_STATE_UNFILLED = 20;
	
	/**
	 * 订单状态待收货
	 */
	public final static int ORDER_STATE_NOT_RECEIVING = 30;
	
	/**
	 * 订单状态交易完成
	 */
	public final static int ORDER_STATE_FINISH = 40;
	
	/**
	 * 订单状态已提交(货到付款)
	 */
	public final static int ORDER_STATE_SUBMIT = 50;
	
	/**
	 * 订单状态已确认(货到付款)
	 */
	public final static int ORDER_STATE_CONFIRM = 60;
	
	/**
	 * 订单付款状态未付款
	 */
	public final static int PAYMENT_STATE_NO = 0;
	
	/**
	 * 订单付款状态已付款
	 */
	public final static int PAYMENT_STATE_YES = 1;
	
	/**
	 * 订单退款状态无退款
	 */
	public final static int REFUND_STATE_NO = 0;
	
	/**
	 * 订单退款状态部分退款
	 */
	public final static int REFUND_STATE_SOM = 1;
		
	/**
	 * 订单退款状态全部退款
	 */
	public final static int REFUND_STATE_ALL = 2;
	
	/**
	 * 订单退货状态无退货
	 */
	public final static int RETURN_STATE_NO = 0;
	
	/**
	 * 订单退货状态部分退货 
	 */
	public final static int RETURN_STATE_SOM = 1;
	
	/**
	 * 订单退货状态全部退货
	 */
	public final static int RETURN_STATE_ALL = 2;
	
	/**
	 * 订单类型普通
	 */
	public final static int ORDER_TYPE_ORDINARY = 0;
	
	/**
	 * 订单类型团购
	 */
	public final static int ORDER_TYPE_GROUP = 1;
	
	/**
	 * 订单评论状态-未评论
	 */
	public final static int ORDER_EVALUATION_NO = 0;
	
	/**
	 * 订单评论状态-已评论
	 */
	public final static int ORDER_EVALUATION_YES = 1;
	
	/**
	 * 订单结算状态-未结算
	 */
	public final static int ORDER_BALANCE_NO = 0;
	
	/**
	 * 订单结算状态-已结算
	 */
	public final static int ORDER_BALANCE_YES = 1;
	
	/**
	 * 根据订单状态返回对应的订单值
	 * @param status
	 * @return
	 */
	public static String orderStatus(int status){
		String info = "";
		switch (status) {
		case 0:
			info = "已取消";
			break;
		case 10:
			info = "待付款";
			break;
		case 20:
			info = "待发货";
			break;
		case 30:
			info = "待收货";
			break;
		case 40:
			info = "已完成";
			break;
		default:
			info = "已取消";
			break;
		}
		return info;
	}
	
	
	public static void main(String[] args) {
		System.out.println(orderStatus(0));
	}
	
}
