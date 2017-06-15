package com.Vshop.service.module.trade.service;

import java.util.List;

import com.Vshop.core.entity.base.ComplainGoods;

/**
 * Created by rabook on 2014/12/21.
 */
public interface ComplainGoodsService {

    List<ComplainGoods> findByComplainId(int id);
}
