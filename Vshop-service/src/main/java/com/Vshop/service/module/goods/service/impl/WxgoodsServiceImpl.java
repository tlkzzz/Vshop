package com.Vshop.service.module.goods.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Wxgoods;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.goods.dao.BrandDao;
import com.Vshop.service.module.goods.dao.WxgoodsDao;
import com.Vshop.service.module.goods.service.BrandService;
import com.Vshop.service.module.goods.service.WxgoodsService;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：BrandServiceImpl
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@Slf4j
@Service
public class WxgoodsServiceImpl implements WxgoodsService {

	@Resource
	private WxgoodsDao brandDao;

	public void save(Wxgoods brand) {
//		brand.setCjsj(System.currentTimeMillis());
		brandDao.save(brand);
	}

	public void delete(String id) {
		brandDao.delete(id);
	}
	public void updelete(String id) {
		brandDao.delete(id);
	}
	public void update(Wxgoods brand) {
//		brand.setUpdateTime(System.currentTimeMillis());
		brandDao.update(brand);
	}

	public List<Wxgoods> findPageList(Pager pager) {
		return brandDao.findPageList(pager);
	}


	public Wxgoods findById(String id) {
		return brandDao.findById(id);
	}

	

	@Override
	public int countBrand(Wxgoods brand) {
		return brandDao.countBrand(brand);
	}

	@Override
	public List<Wxgoods> findList(Wxgoods brand) {
		// TODO 自动生成的方法存根
		return brandDao.findList(brand);
	}




}
