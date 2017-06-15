package com.Vshop.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.Vshop.core.entity.Transport;
import com.Vshop.service.module.goods.dao.TranSportDao;
import com.Vshop.service.module.goods.dao.mapper.TranSportMapper;

@Repository
public class TranSportDaoImpl implements TranSportDao {

    @Resource
    private TranSportMapper sportMapper;

	@Override
	public List<Transport> queryStoreList(Integer storeId) {
		// TODO Auto-generated method stub
		return sportMapper.queryStoreList(storeId);
	}
    

    
}
