package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.GoodsCombination;
import com.Vshop.service.module.goods.dao.GoodsCombinationDao;
import com.Vshop.service.module.goods.service.GoodsCombinationService;

@Service
public class GoodsCombinationServiceImpl implements GoodsCombinationService{

	@Autowired
	private GoodsCombinationDao goodsCombinationDao;
	
	/**
	 * @author chen
	 * 通过条件获取组合商品
	 * 参数说明:在GoodsCombination实体类中设置条件,则返回相应的list
	 * 比如:设置goodsid=1,则返回goodsid=1的组合商品
	 */
	@Override
	public List<GoodsCombination> selectByCondition(GoodsCombination goodsCombination) {
		
		return goodsCombinationDao.selectByCondition(goodsCombination);
		
	}

	/**
	 * @author chen
	 * 修改组合商品
	 * 参数说明:
	 * 在这个实体类中需要设置2个参数:goodsId商品的id,
	 * 以及allCombinationGoodsIdStr所有被组合的商品id,以逗号分隔
	 * 2015年08月14日11:03:24
	 */
	@Override
	public void updateGoodsCombination(GoodsCombination goodsCombination) {
		
		if(goodsCombination != null){
			
			/**每一次修改,先删除再增加*/
			goodsCombinationDao.deleteByGoodsId(goodsCombination.getGoodsId());
			
			/**判断*/
			if(StringUtils.isNotEmpty(goodsCombination.getAllCombinationGoodsIdStr())){
				
				/**将所有组合商品的字符串,解析为数字,循环插入*/
				for(String combinationId : goodsCombination.getAllCombinationGoodsIdStr().split(",")){
					
					goodsCombination.setCombinationGoodsId(Integer.parseInt(combinationId));
					
					goodsCombinationDao.saveGoodsCombination(goodsCombination);
					
				}
			}
		}
	}
    
    

}
