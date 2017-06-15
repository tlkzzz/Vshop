package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.GoodsType;
import com.Vshop.core.entity.vo.GoodsTypeVO;
import com.Vshop.service.module.goods.dao.GoodsTypeDao;
import com.Vshop.service.module.goods.dao.mapper.GoodsTypeMapper;
import com.Vshop.service.module.goods.vo.GoodsTypeVo;
import com.Vshop.service.utils.page.Pager;

@Repository
public class GoodsTypeDaoImpl implements GoodsTypeDao{

	@Resource
	private GoodsTypeMapper goodsTypeMapper;
	
	@Override
	public void save(GoodsType goodsType) {
		goodsTypeMapper.save(goodsType);
	}

	@Override
	public void update(GoodsType goodsType) {
		goodsTypeMapper.update(goodsType);
	}

	@Override
	public void delete(Integer typeId) {
		goodsTypeMapper.delete(typeId);
	}

	@Override
	public GoodsType findById(Integer typeId) {
		return goodsTypeMapper.findById(typeId);
	}

	@Override
	public List<GoodsType> findList() {
		return goodsTypeMapper.findList();
	}

	@Override
	public GoodsTypeVO selectTypeFetchOther(Integer typeId) {
		return goodsTypeMapper.selectTypeFetchOther(typeId);
	}

	@Override
	public int findCount(Pager pager) {
		return goodsTypeMapper.findCount(pager);
	}

	@Override
	public List<GoodsType> findPageList(Pager pager) {
		return goodsTypeMapper.findPageList(pager);
	}

	/*public List<GoodsType> findTypeByClassId() {
		return goodsTypeMapper.findTypeByClassId();
	}*/

	@Override
	public void saveGoodsType(GoodsTypeVo vo) {
		goodsTypeMapper.saveGoodsType(vo);
	}

	public void updateGoodsType(GoodsTypeVo vo) {
		goodsTypeMapper.updateGoodsType(vo);
	}

	public void updateType(GoodsType type) {
		goodsTypeMapper.updateType(type);
	}
	
	
	
	

}
