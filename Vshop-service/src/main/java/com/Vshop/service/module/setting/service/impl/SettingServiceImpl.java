package com.Vshop.service.module.setting.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.Vshop.core.cache.jedis.JedisUtils;
import com.Vshop.core.common.Collections3;
import com.Vshop.core.common.Reflections;
import com.Vshop.core.entity.base.Setting;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.setting.dao.SettingDao;
import com.Vshop.service.module.setting.service.SettingService;
import com.Vshop.service.module.store.vo.SettingVo;

/**
 * 
 * 项目名称：Vshop-admin 类名称：SettingServiceImpl 类描述： 创建人：weiyue 创建时间：2014年11月5日
 * 下午10:15:48 修改人：weiyue 修改时间：2014年11月5日 下午10:15:48 修改备注：
 * 
 * @version
 * 
 */
@Service
public class SettingServiceImpl implements SettingService {

	@Resource
	private SettingDao settingDao;
	
	/**
	 * 根据店铺二级域名查询设置信息
	 * @param map
	 * @return
	 */
	@Override
	public SettingVo queryClasssMap(Map map) {
		SettingVo sv = new SettingVo();
		List<Setting> list = settingDao.queryClasssMap(map);
        if(Collections3.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals("enabled_subdomain")) {
                    sv.setEnabledSubdomain(list.get(i).getValue());
                } else if (list.get(i).getName().equals("subdomain_edit")) {
                    sv.setSubdomainEdit(list.get(i).getValue());
                } else if (list.get(i).getName().equals("subdomain_length")) {
                    sv.setSubdomainLength(list.get(i).getValue());
                } else if (list.get(i).getName().equals("subdomain_reserved")) {
                    sv.setSubdomainReserved(list.get(i).getValue());
                } else if (list.get(i).getName().equals("subdomain_times")) {
                    sv.setSubdomainTimes(list.get(i).getValue());
                }
            }
        }
		return sv;
	}

	/**
     * 修改
     * @param setting
     */
	@Override
	public void update(SettingVo settingVo) {

        Field[] fields = settingVo.getClass().getDeclaredFields();
        for(Field field : fields){
            Setting setting = new Setting();
            setting.setName(field.getName());
            try {
                setting.setValue(PropertyUtils.getProperty(settingVo,field.getName()).toString());
            } catch (Exception e){
                throw Reflections.convertReflectionExceptionToUnchecked(e);
            }
            settingDao.update(setting);
        }
	}
	
	/**
     * 根据名称查询,从缓存查询
     * @param name
     * @return
     */
    @Override
    public Setting queryByName(String name) {
    	Setting setting;
    	//判断是否开启缓存
//    	if(JedisUtils.JEDIS_STATUS){ //开启
//    		Object obj = null;
//    		String key = "setting:points";
//    		obj = JedisUtils.getObject(key);
//    		if(obj == null){
//    			setting = settingDao.queryByName(name);
//    			JedisUtils.setObject(key, setting, 60);
//    		}else{
//    			setting = (Setting)obj;
//    		}
//    	}else{ //未开启,直接查询
    		setting = settingDao.queryByName(name);
//    	}
        return setting;
    }
    
    /**
     * 根据名称修改值,插入缓存
     * @param setting
     */
    @Override
    public void updateEntity(Setting setting) {
    	//判断是否开启缓存
    	/*if(JedisUtils.JEDIS_STATUS){ //开启
    		String key = "setting:points";
    		JedisUtils.setObject(key, setting, 60);
    	}*/
        settingDao.update(setting);
    }
    
    
    /**
     * 保存系统设置
     * @param name 名称(类型)
     * @param value 值
     */
    public void save(String name,String value){
    	Setting setting = new Setting();
    	setting.setName(name);
    	setting.setValue(value);
    	//判断是否开启缓存
    	/*if(JedisUtils.JEDIS_STATUS){ //开启
    		String key = "setting:points";
    		JedisUtils.setObject(key, setting, 60);
    	}*/
    	settingDao.save(setting);
    }
    
    /**
     * 保存系统设置
     * @param map 键为name,值为对应的valueMap
     */
    public void saveSetting(Map<String,Map<String,String>> map){
    	Set<String> set = map.keySet();
    	for(String str:set){  //遍历第一层map,键为name,值为value
    		Setting setting  = new Setting();
    		JSONObject jsonObject = new JSONObject();
    		Map<String,String> mapStr = map.get(str);
    		Set<String> strSet = mapStr.keySet();
    		for(String setStr:strSet){ //将value拆分成json
    			jsonObject.put(setStr, mapStr.get(setStr));
    		}
    		setting.setName(str);
    		setting.setValue(jsonObject.toString());
    		//判断是否开启缓存
        	/*if(JedisUtils.JEDIS_STATUS){ //开启
        		String key = "setting:points";
        		JedisUtils.setObject(key, setting, 60);
        	}*/
    		settingDao.save(setting);
    	}
    }
    
    /**
     * 修改系统设置
     * @param map value键值对 
     */
    public void updateSetting(String name,Map<String,String> map){
    	Setting setting  = new Setting();
    	Set<String> set = map.keySet();
    	JSONObject jsonObject = new JSONObject();
    	for(String str:set){
    		jsonObject.put(str, map.get(str));
    	}
    	setting.setName(name);
    	setting.setValue(jsonObject.toString());
    	this.updateEntity(setting);
    }
    
    /**
     * 根据名称和标识修改
     * @param name 名称
     * @param code 标识
     * @param codeValue 标识对应的值
     */
    public void updateByNameAndcode(String name,String code,String codeValue){
    	Setting setting = this.queryByName(name);
    	Map<String,String> map = JsonUtils.readJsonToMap(setting.getValue());
    	JSONObject jsonObject = new JSONObject();
    	Set<String> set = map.keySet(); 
    	for(String str:set){
    		if(code.equals(str)){ //判断当前的code是否为要修改的code
    			jsonObject.put(str, codeValue);
    		}else{
    			jsonObject.put(str, map.get(str));
    		}
    	}
    	setting.setValue(jsonObject.toString());
    	this.updateEntity(setting);
    }
    
    /**
     * 根据name获取
     * @param name
     * @return
     */
    public Map<String,String> findByNameResultMap(String name){
    	Setting setting = this.queryByName(name);
    	Map<String,String> map = JsonUtils.readJsonToMap(setting.getValue());
    	return map;
    }
    
    /**
     * 根据名称和标识获取对应的值
     * @param name
     * @param code
     * @return
     */
    public String findByNameAndCode(String name,String code){
    	Setting setting = this.queryByName(name);
    	Map<String,String> map = JsonUtils.readJsonToMap(setting.getValue());
    	if(map!=null){
    		if(map.get(code)!=null){
    			return map.get(code);
    		}else{
    			return "";
    		}
    	}else{
    		return "";
    	}
    }
    
}