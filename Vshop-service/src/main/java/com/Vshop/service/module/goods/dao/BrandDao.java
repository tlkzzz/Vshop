package com.Vshop.service.module.goods.dao;

import java.util.List;
import java.util.Map;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * @项目名称：Vshop-seller
 * @类名称：BrandDao
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
public interface BrandDao {
	/**
	 * 保存品牌
	 * @param brand
	 */
	void save(Brand brand);
	
	/**
	 * 删除品牌
	 * @param id
	 */
	void delete(int id);
	
	/**
	 * 更新品牌
	 * @param brand
	 */
	void update(Brand brand);
	
	/**
	 * 获取分页数据
	 * @param pager
	 * @return
	 */
	List<Brand> findPageList(Pager pager);

	/**
	 * 获取全部数据
	 * @return
	 */
	List<Brand> findList();

	/**
	 * 根据id获取品牌
	 * @param id
	 * @return
	 */
	Brand findById(long id);
	
    /**
     * 根据classId查询品牌
     * @return
     */
	List<Brand> findByClassId(int classId);
	
	/**
     * 获取品牌的分类
     * @return
     */
    List<Brand> findBrandGroupByClassId();
    
    /**
     * 根据店铺id获取店铺的品牌
     * @param storeId
     * @return
     */
    List<Brand> getBrandListByStoreId(int storeId);
    
    /**
     * 根据typeid获取品牌
     * @param storeId
     * @return
     */
    List<Brand> getBrandListByTypeId(int typeId);
    
    /**
     * 根据店铺typeId获取店铺的品牌
     * @param storeId
     * @return
     */
    List<Brand> findListByType(int typeId);
    
    /**
     * 获取品牌数量
     * @param brand
     * @return
     */
    int countBrand(Brand brand);
    
    List<Map<String, Object>> queryBrandUseList(List<Integer> brandIds);
}
