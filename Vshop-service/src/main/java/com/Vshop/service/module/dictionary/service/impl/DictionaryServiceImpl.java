package com.Vshop.service.module.dictionary.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.Vshop.core.cache.jedis.JedisStatus;
import com.Vshop.core.cache.jedis.JedisUtils;
import com.Vshop.core.entity.base.Dictionary;
import com.Vshop.service.module.dictionary.dao.DictionaryDao;
import com.Vshop.service.module.dictionary.service.DictionaryService;
import com.Vshop.service.utils.page.Pager;

/**
* <p>Title:数据词典service</p>
* <p>Description: 使用缓存必须先安装reids</p>
* @author linjm
* @date 2015年10月13日
* @version 1.0
**/
@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService{

	@Resource 
	private DictionaryDao dictionaryDao;
	
	@Override
	public void save(Dictionary dictionary) {
		dictionaryDao.save(dictionary);
		//缓存更新  需要安装redis
//		updateDicCache(dictionary);
	}

	@Override
	public void update(Dictionary dictionary) {
		dictionaryDao.update(dictionary);
		//缓存更新 需要安装redis
//		updateDicCache(dictionary);
	}

	@Override
	public void delete(Integer dictionaryId) {
		/**
		*先删除缓存数据 
		*删除不成功取的时候会重新放入
		*请别调整这里的删除顺序
		**/
		//需要安装redis
//		delDicCache(dictionaryId+"");
		//删除数据库数据
		dictionaryDao.delete(dictionaryId);
	}

	@Override
	public Dictionary findByDictionaryId(Integer dictionaryId) {
		return dictionaryDao.findByDictionaryId(dictionaryId);
	}

	@Override
	public int countDictionaryidList(Dictionary dictionary) {
		return dictionaryDao.countDictionaryidList(dictionary);
	}

	@Override
	public List<Dictionary> queryDictionaryidList(Pager pager) {
		return dictionaryDao.queryDictionaryidList(pager);
	}

	@Override
	public List<Dictionary> findDictionaryByCode(String groupCode) {
		List<Dictionary> diclist;
//		if(JedisStatus.JEDIS_STATUS){
//		需要安装redis
//			Object obj = JedisUtils.getObject("dic:"+groupCode);
//			if(obj == null){
//				diclist = dictionaryDao.findDictionaryByCode(groupCode);
//				//10分钟
//				JedisUtils.setObject("dic:"+groupCode, diclist, 600);
//				log.debug("dic:存入redis");
//			}else{
//				diclist = (List<Dictionary>)obj;
//				log.debug("dic:转化成功");
//			}
//		}else{
			diclist = dictionaryDao.findDictionaryByCode(groupCode);
//		}
		return diclist;
	}

	@Override
	public Dictionary findDictionaryByDictionaryId(Integer dictionaryId) {

		return dictionaryDao.findDictionaryByDictionaryId(dictionaryId);
	}
	
	/**
	 * 更新缓存数据
	 * 需要安装redis
	 * @param dictionary
	 */
	public void updateDicCache(Dictionary dictionary){
		if(JedisStatus.JEDIS_STATUS){
			Pager pager = new Pager();
			Dictionary dic = new Dictionary();
			//这里默认是20条字典项超过20条就自己设置大小
			//pager.setPageSize(100);
			pager.setCondition(dic);
			List<Dictionary> diclist = dictionaryDao.queryDictionaryidList(pager);
			JedisUtils.setObject("dic:"+dictionary.getDictionaryCode(), diclist, 600);
			log.debug("dic:更新redis");
		}
	}
	
	/**
	 * 删除缓存
	 * 需要安装redis
	 * @param value
	 */
	public void delDicCache(String value){
		if(StringUtils.isEmpty(value)) return;
		Dictionary dic = dictionaryDao.findDictionaryByDictionaryId(Integer.parseInt(value));
		if(dic==null) return;
		JedisUtils.delObject("dic:"+dic.getDictionaryCode());
	}
}
