package com.Vshop.service.module.goods.dao;

import com.Vshop.core.entity.base.GoodsImages;

/**
 * 
 *    
 * 项目名称：Vshop-seller   
 * 类名称：GoodsImagesDao   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月20日 下午10:13:15   
 * 修改人：liuhao   
 * 修改时间：2014年12月20日 下午10:13:15   
 * 修改备注：   
 * @version    
 *
 */
public interface GoodsImagesDao {

	void saveGoodsImages(GoodsImages goodsImages);
	
	void deleteGoodsImage(GoodsImages goodsImages);
}
