/**
 * 
 */
package com.Vshop.service.module.adv.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.Vshop.core.cache.jedis.JedisUtils;
import com.Vshop.core.entity.base.AdvPosition;
import com.Vshop.core.entity.vo.AdvPositionVo;
import com.Vshop.service.module.adv.dao.AdvPositionDao;
import com.Vshop.service.module.adv.service.AdvPositionService;
import com.Vshop.service.utils.page.Pager;

/**
 * <p>Title: AdvPositionServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
@Service
@Slf4j
public class AdvPositionServiceImpl implements AdvPositionService {
	
	@Resource
	private AdvPositionDao advPositionDao;

	@Override
	public void save(AdvPosition advPosition) {
		advPositionDao.save(advPosition);
		
	}

	@Override
	public void update(AdvPosition advPosition) {
		advPositionDao.update(advPosition);
		
	}

	@Override
	public void delete(Integer id) {
		advPositionDao.delete(id);
	}

	@Override
	public int findAdvPositionCount(AdvPosition advPosition) {
		Integer count = advPositionDao.findAdvPositionCount(advPosition);
		if(count ==null){
			count = 0;
		}
		return count;
	}

	@Override
	public List<AdvPosition> findAllAdvPosition(AdvPosition advPosition) {
		
		return advPositionDao.findAllAdvPosition(advPosition);
	}

	@Override
	public List<AdvPosition> findAdvPositionPagerList(Pager pager) {
		
		return advPositionDao.findAdvPositionPagerList(pager);
	}

	@Override
	public AdvPosition findAdvPositionById(Integer id) {
		
		return advPositionDao.findAdvPositionById(id);
	}

	@Override
	public AdvPositionVo findAdvPositionVoList(AdvPosition advPosition) {
		AdvPositionVo  advPositionVo ;
		
//		if(JedisUtils.JEDIS_STATUS){
//			Object obj = null;
//			String key = "";
//			if(advPosition.getApKey() == null) {
//				key = "adv:"+advPosition.getApId();
//			}else{
//				key = "adv:"+advPosition.getApKey();
//			}
//			obj = JedisUtils.getObject(key);
//			if(obj == null){
//				advPositionVo = advPositionDao.findAdvPositionVoList(advPosition);
//				//60秒
//				JedisUtils.setObject(key, advPositionVo, 60);
//				log.debug("存入redis");
//			}else{
//				advPositionVo = (AdvPositionVo)obj;
//				log.debug("转化成功");
//			}
//		}else{
			advPositionVo = advPositionDao.findAdvPositionVoList(advPosition);
//		}
		return advPositionVo;
	}

//	@Override
//	public AdvPositionVo findAdvPositionVoList(Integer apId) {
//
//		return advPositionDao.findAdvPositionVoList(apId);
//	}

}
