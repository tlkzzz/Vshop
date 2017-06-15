package com.Vshop.service.module.trade.dao;


import java.math.BigDecimal;
import java.util.List;

import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.admin.module.trade.dao
 * @Description:
 * @date 2014/11/12 13:00
 */
public interface EvaluateGoodsDao {
	
	public List<EvaluateGoods> findPageList(Pager pager);

    /**
     * 总条数
     * @param evaluateGoods
     * @return
     */
    int findCount(EvaluateGoods evaluateGoods);

    /**
     * 删除
     * @param id
     */
    void delete(int id);
    

    void updateById(EvaluateGoods evaluateGoods);
    
    /**
     * 保存数据
     * @param evaluateGoods
     */
    public void saveEvaluate(EvaluateGoods evaluateGoods);
    
    /**
     * 获得某个商品的平均分数
     * @param goodsId
     */
    public BigDecimal getAverageScoreByGooodsId(Integer goodsId);
}
