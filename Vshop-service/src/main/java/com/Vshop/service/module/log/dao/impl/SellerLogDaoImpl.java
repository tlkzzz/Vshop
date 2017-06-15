package com.Vshop.service.module.log.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Vshop.core.entity.base.SellerLog;
import com.Vshop.service.module.log.dao.SellerLogDao;
import com.Vshop.service.module.log.dao.mapper.SellerLogDaoMapper;
import com.Vshop.service.utils.page.Pager;

@Component
public class SellerLogDaoImpl implements SellerLogDao{
	
		@Autowired
		private SellerLogDaoMapper sellerLogDaoMapper;

		//增加日志
		public int saveSellerLog(SellerLog sellerLog){
			return sellerLogDaoMapper.saveSellerLog(sellerLog);
		}
		
		//删除日志
		public int deleteSellerLog(int logId){
			return sellerLogDaoMapper.deleteSellerLog(logId);
		}
		
		//修改日志
		public int updateSellerLog(SellerLog sellerLog){
			return sellerLogDaoMapper.updateSellerLog(sellerLog);
		}
		
		//查询所有日志
		public List<SellerLog> selectAllSellerLog(){
			return sellerLogDaoMapper.selectAllSellerLog();
		}

		@Override
		public int countSellerLog(SellerLog sellerLog) {
			return sellerLogDaoMapper.countSellerLog(sellerLog);
		}

		@Override
		public List<SellerLog> selectSellerLogByPager(Pager pager) {
			return sellerLogDaoMapper.selectSellerLogByPager(pager);
		}
		
		
}
