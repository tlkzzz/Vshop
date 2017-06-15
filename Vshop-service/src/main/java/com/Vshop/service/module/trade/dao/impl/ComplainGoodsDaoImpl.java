package com.Vshop.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.base.ComplainGoods;
import com.Vshop.service.module.trade.dao.ComplainGoodsDao;
import com.Vshop.service.module.trade.dao.mapper.ComplainGoodsMapper;

/**
 * Created by rabook on 2014/12/21.
 */
@Repository
public class ComplainGoodsDaoImpl implements ComplainGoodsDao{

    @Resource
    private ComplainGoodsMapper complainGoodsMapper;

    @Override
    public List<ComplainGoods> findByComplainId(int id) {
        return complainGoodsMapper.findByComplainId(id);
    }
}
