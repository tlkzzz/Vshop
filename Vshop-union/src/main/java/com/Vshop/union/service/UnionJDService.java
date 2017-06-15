package com.Vshop.union.service;

import com.Vshop.union.entity.APIResult;
import com.Vshop.union.entity.UnionGoods;

/**
 * 京东联盟service
 * 
 * @author liuzhen
 * @version 2015-10-11
 */
public interface UnionJDService {

	/**
	 * 获取token
	 * 
	 * @return
	 */
	public APIResult getAccessToken();

	/**
	 * 刷新token
	 * 
	 * @return
	 */
	public APIResult getRefreshAccessToken();

	/**
	 * 获取京东联盟推广商品信息
	 * 
	 * @param goods
	 * @return
	 */
	public APIResult setgoodsinfo(UnionGoods goods);

	/**
	 * 获取单条推广商品链接
	 * 
	 * @param materialId
	 * @param subUnionId
	 * @return
	 */
	public APIResult getOnePromotionCode(String materialId, String subUnionId);
}
