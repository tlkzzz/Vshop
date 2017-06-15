package com.Vshop.service.module.trade.service;

import java.math.BigDecimal;
import java.util.List;

import com.Vshop.core.entity.apibean.EvaluateGoodsApiBean;
import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.admin.module.trade.service
 * @Description:
 * @date 2014/11/12 13:09
 */
public interface EvaluateGoodsService {

    
    /**
     * 分页列表
     * @param pager
     * @return
     */
    List<EvaluateGoods> findPageList(Pager pager);
    
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
    
    void update(EvaluateGoods evaluateGoods);

    
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
    
    /**
     * 根据商品评论表信息,查询补全商品评论api信息
     * @param list 商品评论表信息列表集合
     * @return
     */
    public List<EvaluateGoodsApiBean> getApiBeanList(List<EvaluateGoods> list);

}
