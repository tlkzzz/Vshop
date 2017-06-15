package com.Vshop.service.module.search.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.GoodsWords;
import com.Vshop.service.module.search.dao.GoodsWordsDao;
import com.Vshop.service.module.search.dao.mapper.GoodsWordsMapper;
/**
 * 商品关键词
 * @author cgl
 * 2015年08月18日10:05:53
 */
@Component
public class GoodsWordsDaoImpl implements GoodsWordsDao{

	@Autowired
	GoodsWordsMapper goodsWordsMapper;
	
	@Override
	public void saveGoodsWords(GoodsWords goodsWords) {
		// TODO Auto-generated method stub
		goodsWordsMapper.saveGoodsWords(goodsWords);
	}

	@Override
	public void delete(Integer wordsId) {
		// TODO Auto-generated method stub
		goodsWordsMapper.delete(wordsId);
	}

	@Override
	public void update(GoodsWords goodsWords) {
		// TODO Auto-generated method stub
		goodsWordsMapper.update(goodsWords);
	}

	@Override
	public Integer isExist(String keyword) {
		// TODO Auto-generated method stub
		return goodsWordsMapper.isExist(keyword);
	}

	@Override
	public void updateWordsNum(GoodsWords goodsWords) {
		// TODO Auto-generated method stub
		goodsWordsMapper.updateWordsNum(goodsWords);
	}

	/**
	 * 关键词匹配
	 */
	public List<GoodsWords> keywordMatch(GoodsWords goodsWords){
		return goodsWordsMapper.keywordMatch(goodsWords);
	}
	
	/**
	 * 删除所有的记录,初始化id
	 */
	public void deleteAll(){
		goodsWordsMapper.deleteAll();
	}
}
