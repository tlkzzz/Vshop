package com.Vshop.core.state.goods;

/**
 * 商品的各种状态
 * @author cgl
 * 2015年08月06日10:30:05
 */
public final class GoodsState {
	/**
	 * 商品推荐:是
	 */
	public final static int GOODS_COMMEND_YES = 1;
	
	/**
	 * 商品推荐:否
	 */
	public final static int GOODS_COMMEND_NO = 0;
	
	/**
	 * 商品上架状态
	 */
	public final static int GOODS_ON_SHOW = 1;
	
	/**
	 * 商品下架状态
	 */
	public final static int GOODS_OFF_SHOW = 0;
	
	/**
	 * 商品定时商家状态
	 */
	public final static int GOODS_PARPER_SHOW = 2;
	
	/**
	 * 商品状态开启
	 */
	public final static int GOODS_OPEN_STATE = 0;
	
	/**
	 * 商品状态关闭(违规下架)
	 */
	public final static int GOODS_CLOSE_STATE = 1;
	

	
	/**
	 * 商品状态审核未通过
	 */
	public final static int GOODS_APPLY_OFF = 50;
	
	/**
	 * 商品状态待审核
	 */
	public final static int GOODS_APPLY_PREPARE = 60;
	
	
	/**
	 * 商品所在店铺状态 开启 
	 */
	public final static int GOODS_STORE_OPEN = 0;
	
	/**
	 * 商品所在店铺状态 关闭
	 */
	public final static int GOODS_STORE_CLOSE = 1;
	
	/**
	 * 未删除
	 */
	public final static int GOODS_NOT_DELETE = 0;
	
	/**
	 * 已删除
	 */
	public final static int GOODS_HAS_DELETE = 1;
	
}
