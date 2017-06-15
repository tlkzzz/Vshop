package com.Vshop.service.module.goods.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Wxgoods;
import com.Vshop.service.module.goods.dao.BrandDao;
import com.Vshop.service.module.goods.dao.WxgoodsDao;
import com.Vshop.service.module.goods.dao.mapper.WxgoodsMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：BrandDaoImpl
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@Repository
public class WxgoodsDaoImpl implements WxgoodsDao {
	@Resource
	private WxgoodsMapper wxgoodsMapper;

	@Override
	public void save(Wxgoods brand) {
		wxgoodsMapper.save(brand);
	}

	@Override
	public void delete(String id) {
		wxgoodsMapper.delete(id);
	}
	@Override
	public void updelete(String id) {
		wxgoodsMapper.updelete(id);
	}

	@Override
	public void update(Wxgoods brand) {
		wxgoodsMapper.update(brand);
	}

	@Override
	public List<Wxgoods> findPageList(Pager pager) {
		return wxgoodsMapper.findPageList(pager);
	}


	@Override
	public Wxgoods findById(String id) {
		return wxgoodsMapper.findById(id);
	}

	
	 /**
     * 获取品牌数量
     * @param brand
     * @return
     */

	@Override
	public int countBrand(Wxgoods brand) {
		return wxgoodsMapper.countBrand(brand);
	}

	@Override
	public List<Wxgoods> findList(Wxgoods brand) {
		// TODO 自动生成的方法存根
		return wxgoodsMapper.findList(brand);
	}

	

}
