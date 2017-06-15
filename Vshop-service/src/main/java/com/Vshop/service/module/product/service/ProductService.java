package com.Vshop.service.module.product.service;

import java.io.IOException;

import javax.servlet.ServletException;

import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsStore;

import freemarker.template.TemplateException;

public interface ProductService {

	/**
	 * 保存goods
	 * cgl
	 * 2015年06月17日11:50:08
	 * 返回 0 则保存失败
	 * 否则返回goodsId
	 */
	public Integer saveGoods(Goods goods, String goodsSpecJson);
	
	
	/**
	 * 修改goods
	 * @param goods
	 * @param goodsSpecJson
	 * 返回 0 则保存失败
	 * 返回 1 则保存成功
	 */
	public Integer updateGoods(Goods goods, String goodsSpecJson)  throws IOException, TemplateException, ServletException;
	
    
    /**
     * 修改库存
     * @parm GoodsSpec 需要2个参数 specId 以及出售数量 specSalenum(这个出售数量是本次的出售数量)
     * 返回 0 则保存失败
	 * 返回 1 则保存成功
     */
    public Integer updateStorage(GoodsSpec goodsSpec)  throws IOException, TemplateException, ServletException;
    
    public Integer savePreGoods(Goods goods, String goodsSpecJson);
    
    
    public Integer saveStoreGoods(GoodsStore goodsStore) ;
	
}
