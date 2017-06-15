package com.Vshop.service.module.setting.service;

import java.util.Map;

import com.Vshop.core.entity.base.Setting;
import com.Vshop.service.module.store.vo.SettingVo;

/**
 * 系统设置
 * @author liukai
 */
public interface SettingService {
	
	/**
	 * 根据店铺二级域名查询设置信息
	 * @param map
	 * @return
	 */
    public SettingVo queryClasssMap(Map map);
    
    /**
     * 修改
     * @param setting
     */
    public void update(SettingVo settingVo);
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    Setting queryByName(String name);
    
    /**
     * 根据名称修改值
     * @param setting
     */
    void updateEntity(Setting setting);
    
    /**
     * 保存系统设置
     * @param name 名称(类型)
     * @param value 值
     */
    void save(String name,String value);
    
    /**
     * 保存系统设置
     * @param map 键为name,值为对应的valueMap
     */
    void saveSetting(Map<String,Map<String,String>> map);
    
    /**
     * 修改系统设置
     * @param map value键值对 
     */
    void updateSetting(String name,Map<String,String> map);
    
    /**
     * 根据名称和标识修改
     * @param name 名称
     * @param code 标识
     * @param codeValue 标识对应的值
     */
    void updateByNameAndcode(String name,String code,String codeValue);
    
    /**
     * 根据name获取
     * @param name
     * @return
     */
    Map<String,String> findByNameResultMap(String name);
    
    /**
     * 根据名称和标识获取对应的值
     * @param name
     * @param code
     * @return
     */
    String findByNameAndCode(String name,String code);
}
