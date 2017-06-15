package com.Vshop.service.module.trade.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.Order;
import com.Vshop.core.entity.apibean.EvaluateGoodsApiBean;
import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.OrderGoods;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.module.trade.dao.EvaluateGoodsDao;
import com.Vshop.service.module.trade.service.EvaluateGoodsService;
import com.Vshop.service.module.trade.service.OrderGoodsService;
import com.Vshop.service.module.trade.service.OrderService;
import com.Vshop.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.Vshop.admin.module.trade.service.impl
 * @Description:
 * @date 2014/11/12 13:11
 */

@Repository
public class EvaluateGoodsServiceImpl implements EvaluateGoodsService{

    @Autowired
    private EvaluateGoodsDao evaluateGoodsDao;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderGoodsService orderGoodsService;

	@Override
	public List<EvaluateGoods> findPageList(Pager pager) {
		return evaluateGoodsDao.findPageList(pager);
	}

    /**
     * 总条数
     *
     * @param evaluateGoods
     * @return
     */
    @Override
    public int findCount(EvaluateGoods evaluateGoods) {
        return evaluateGoodsDao.findCount(evaluateGoods);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        evaluateGoodsDao.delete(id);
    }
    
    @Override
    public void update(EvaluateGoods evaluateGoods) {
    	// TODO Auto-generated method stub
    	evaluateGoodsDao.updateById(evaluateGoods);
    }

	@Override
	public void saveEvaluate(EvaluateGoods evaluateGoods) {
		evaluateGoodsDao.saveEvaluate(evaluateGoods);
	}
    
    /**
     * 获得某个商品的平均分数
     * @param goodsId
     */
    public BigDecimal getAverageScoreByGooodsId(Integer goodsId){
    	return evaluateGoodsDao.getAverageScoreByGooodsId(goodsId);
    }
    
    /**
     * 根据商品评论表信息,查询补全商品评论api信息
     * @param list 商品评论表信息列表集合
     * @return
     */
	@Override
	public List<EvaluateGoodsApiBean> getApiBeanList(List<EvaluateGoods> list) {
		List<EvaluateGoodsApiBean> apiBeanList = new ArrayList<EvaluateGoodsApiBean>();
		for (EvaluateGoods evaGoods : list) {
			EvaluateGoodsApiBean apiBean = new EvaluateGoodsApiBean();
			apiBean.setGevalId(evaGoods.getGevalId()); //评价ID
			apiBean.setGevalAddTime(evaGoods.getGevalAddTime()); //评价时间
			apiBean.setGevalContent(evaGoods.getGevalContent()); //信誉评价内容
			apiBean.setGevalFrommembername(evaGoods.getGevalFrommembername()); //评价人名称
			apiBean.setGevalImage(evaGoods.getGevalImage()); //晒单图片
			apiBean.setGevalIsAnonymous(evaGoods.getGevalIsAnonymous()); //是不是匿名评价  1表示是匿名评价
			apiBean.setGevalScore(evaGoods.getGevalScore()); //评分 1-5分
			//通过评价的用户id,查询用户信息
			Member member = memberService.findById(evaGoods.getGevalFrommemberid());
			if(member!=null){
				apiBean.setGevalFrommemberAvatar(member.getMemberAvatar()); //会员头像
			}
			//通过评价的订单id,查询订单信息
			Order order = orderService.findById(evaGoods.getGevalOrderId());
			if(order!=null){
				apiBean.setOrderAddTime(order.getCreateTime());
			}
			//通过评价的订单商品id,查询订单商品信息
			OrderGoods orderGoods = orderGoodsService.findById(evaGoods.getGevalOrderGoodsId());
			if(orderGoods!=null){
				apiBean.setSpecInfo(orderGoods.getSpecInfo());
			}
			apiBeanList.add(apiBean);
		}
		return apiBeanList;
	}
    
}
