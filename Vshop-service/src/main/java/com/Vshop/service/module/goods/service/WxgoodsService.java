package com.Vshop.service.module.goods.service;

import java.util.List;
import java.util.Map;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Wxgoods;
import com.Vshop.service.utils.page.Pager;

/**
 * @项目名称：Vshop-seller
 * @类名称：BrandService
 * @类描述：
 * @修改备注：
 * @version
 */
public interface WxgoodsService {
	
	/**
	 * 保存品牌
	 * @param brand
	 */
	void save(Wxgoods brand);
	
	/**
	 * 删除品牌
	 * @param id
	 */
	void delete(String id);
	/**
	 * 删除品牌
	 * @param id
	 */
	void updelete(String id);
	/**
	 * 更新品牌
	 * @param brand
	 */
	void update(Wxgoods brand);
	
	/**
	 * 获取分页数据
	 * @param pager
	 * @return
	 */
	List<Wxgoods> findPageList(Pager pager);



	/**
	 * 根据id获取品牌
	 * @param id
	 * @return
	 */
	Wxgoods findById(String id);
	
	
    /**
     * 获取品牌数量
     * @param brand
     * @return
     */
    int countBrand(Wxgoods brand);
    List<Wxgoods> findList(Wxgoods brand);
}
