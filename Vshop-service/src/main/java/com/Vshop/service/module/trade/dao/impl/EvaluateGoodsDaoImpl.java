package com.Vshop.service.module.trade.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.service.module.trade.dao.EvaluateGoodsDao;
import com.Vshop.service.module.trade.dao.mapper.EvaluateGoodsMapper;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.admin.module.trade.dao.impl
 * @Description:
 * @date 2014/11/12 13:07
 */

@Repository
public class EvaluateGoodsDaoImpl implements EvaluateGoodsDao{

    @Autowired
    private EvaluateGoodsMapper evaluateGoodsMapper;

	@Override
	public List<EvaluateGoods> findPageList(Pager pager) {
		return evaluateGoodsMapper.findPageList(pager);
	}

    /**
     * 总条数
     *
     * @param evaluateGoods
     * @return
     */
    @Override
    public int findCount(EvaluateGoods evaluateGoods) {
        return evaluateGoodsMapper.findCount(evaluateGoods);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        evaluateGoodsMapper.delete(id);
    }
    
    @Override
    public void updateById(EvaluateGoods evaluateGoods) {
    	// TODO Auto-generated method stub
    	evaluateGoodsMapper.updateById(evaluateGoods);
    }
    
    /**
     * 保存数据
     * @param evaluateGoods
     */
    public void saveEvaluate(EvaluateGoods evaluateGoods){
    	evaluateGoodsMapper.saveEvaluate(evaluateGoods);
    }
    
    /**
     * 获得某个商品的平均分数
     * @param goodsId
     */
    public BigDecimal getAverageScoreByGooodsId(Integer goodsId){
    	return evaluateGoodsMapper.getAverageScoreByGooodsId(goodsId);
    }
}
