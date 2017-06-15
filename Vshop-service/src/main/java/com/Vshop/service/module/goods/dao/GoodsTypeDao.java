package com.Vshop.service.module.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.GoodsType;
import com.Vshop.core.entity.vo.GoodsTypeVO;
import com.Vshop.service.module.goods.vo.GoodsTypeVo;
import com.Vshop.service.utils.page.Pager;

public interface GoodsTypeDao {
	void save(GoodsType goodsType);

    void update(GoodsType goodsType);

    /**
     * 删除
     * @param
     */
    void delete(@Param("typeId") Integer typeId);

    GoodsType findById(@Param("typeId") Integer typeId);
    
    /**
     * 查询所有的商品类型
     * @return
     */
    List<GoodsType> findList();
	
	/**
	 * 关联查询
	 * @param typeId
	 * @return
	 */
    GoodsTypeVO selectTypeFetchOther(@Param("typeId") Integer typeId);
	
	 /**
     * 查询条数
     * @param pager
     * @return
     */
    int findCount(Pager pager);

    /**
     * 列表
     * @param pager
     * @return
     */
    List<GoodsType> findPageList(Pager pager);
    
    //List<GoodsType> findTypeByClassId();
    
    /**
     * 保存商品类型
     * @param vo
     */
    void saveGoodsType(GoodsTypeVo vo);
    /**
     * 修改商品类型
     * @param vo
     */
    void updateGoodsType(GoodsTypeVo vo);
    
    /**
     * 只修改type
     * @param type
     */
    void updateType(GoodsType type);
}
