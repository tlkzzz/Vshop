package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.service.module.goods.dao.GoodsSpecDao;
import com.Vshop.service.module.goods.dao.mapper.GoodsSpecMapper;
import com.Vshop.service.utils.page.Pager;

@Repository
public class GoodsSpecDaoImpl implements GoodsSpecDao {

    @Resource
    private GoodsSpecMapper goodsSpecMapper;

	@Override
	public void saveGoodsSpec(GoodsSpec goodsSpec) {
		// TODO Auto-generated method stub
		goodsSpecMapper.saveGoodsSpec(goodsSpec);
	}

	@Override
	public void deleteGoodsSpecByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		goodsSpecMapper.deleteGoodsSpecByGoodsId(goodsId);
	}

	@Override
	public List<GoodsSpec> findListByGoodsId(Integer goodsId) {
		// TODO Auto-generated method stub
		return goodsSpecMapper.findListByGoodsId(goodsId);
	}

	@Override
	public GoodsSpec findByGoodsSpecId(Integer goodsSpecId) {
		// TODO Auto-generated method stub
		return goodsSpecMapper.findByGoodsSpecId(goodsSpecId);
	}

	@Override
	public List<GoodsSpec> findAllList() {
		// TODO Auto-generated method stub
		return goodsSpecMapper.findAllList();
	}

	@Override
	public List<GoodsSpec> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecMapper.findPageList(pager);
	}

	@Override
	public Integer findPageListCount(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecMapper.findPageListCount(pager);
	}

	@Override
	public void updateGoodsSpecStorage(GoodsSpec goodsSpec) {
		// TODO Auto-generated method stub
		goodsSpecMapper.updateGoodsSpecStorage(goodsSpec);
	}

}
