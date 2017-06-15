package com.Vshop.service.module.goods.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.service.module.goods.dao.GoodsSpecDao;
import com.Vshop.service.module.goods.service.GoodsSpecService;
import com.Vshop.service.utils.goods.GoodsUtils;
import com.Vshop.service.utils.page.Pager;

@Service
public class GoodsSpecServiceImpl implements GoodsSpecService{

    @Resource
    private GoodsSpecDao goodsSpecDao;

	@Override
	public void saveGoodsSpec(GoodsSpec goodsSpec) {
		// TODO Auto-generated method stub
		goodsSpecDao.saveGoodsSpec(goodsSpec);
	}

	@Override
	public void deleteGoodsSpecByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		goodsSpecDao.deleteGoodsSpecByGoodsId(goodsId);
	}

	@Override
	public List<GoodsSpec> findListByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsSpecDao.findListByGoodsId(goodsId);
	}

	@Override
	public GoodsSpec findByGoodsSpecId(Integer goodsSpecId) {
		// TODO Auto-generated method stub
		return goodsSpecDao.findByGoodsSpecId(goodsSpecId);
	}

	@Override
	public List<GoodsSpec> findAllList() {
		// TODO Auto-generated method stub
		return goodsSpecDao.findAllList();
	}

	@Override
	public List<GoodsSpec> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecDao.findPageList(pager);
	}

	@Override
	public Integer findPageListCount(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecDao.findPageListCount(pager);
	}

	@Override
	public void updateGoodsSpecStorage(GoodsSpec goodsSpec) {
		// TODO Auto-generated method stub
		goodsSpecDao.updateGoodsSpecStorage(goodsSpec);
	}

	/**
	 * 通过已选择的规格值id(以逗号分隔)得到对应的规格商品
	 * @return String
	 */
	@Override
	public GoodsSpec getGoodsSpecBySpecValueId(String specValuesStr, Goods goods) {
		
		if(goods != null){
			/**通过商品id获得商品下所有对应的规格*/
			List<GoodsSpec> goodsSpecs = findListByGoodsId(goods.getGoodsId());
			if(StringUtils.isNotEmpty(specValuesStr)){
				/**将传入的规格值id,以逗号分隔转成String数组*/
				String [] strArr01 = specValuesStr.split(",");
				/**转成int数组*/
				int [] intArr01 = new int[strArr01.length];
				for(int i = 0; i < strArr01.length; i ++){
					intArr01[i] = Integer.parseInt(strArr01[i]);
				}
				/**排序*/
				Arrays.sort(intArr01);
				
				for(GoodsSpec goodsSpec : goodsSpecs){
					/**得到以逗号分隔的规格值id*/
					String str = GoodsUtils.getThisGoodsAllSpecValueId(goodsSpec.getSpecGoodsSpec());
					
					/**和传入的进行比较,如果匹配则返回,否则继续匹配*/
					String [] strArr02 = str.split(",");
					/**转成int数组*/
					int [] intArr02 = new int[strArr02.length];
					for(int i = 0; i < strArr02.length; i ++){
						intArr02[i] = Integer.parseInt(strArr02[i]);
					}
					/**排序*/
					Arrays.sort(intArr02);
					
					/**比较两个数组是否相等*/
					/**匹配*/
					if(Arrays.equals(intArr01, intArr02)){
						return goodsSpec;
					}
				}
			}else{
				/**如果只有一个规格那么直接返回*/
				if(goodsSpecs.size() == 1){
					return goodsSpecs.get(0);
				}
			}
			
		}else{
			return null;
		}
		return null;
	}
}
