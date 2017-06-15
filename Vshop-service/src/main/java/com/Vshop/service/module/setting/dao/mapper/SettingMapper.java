package com.Vshop.service.module.setting.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.Setting;
import com.Vshop.core.orm.mybatis.SqlMapper;


/**
 * 系统设置
 * @author liukai
 */
@SqlMapper
public interface SettingMapper{
	
	/**
	 * 根据店铺二级域名查询设置信息
	 * @param map
	 * @return
	 */
    List<Setting> queryClasssMap(Map map);
    
    /**
     * 保存系统设置
     * @param setting
     */
    void save(Setting setting);
    
    /**
     * 根据名称删除
     * @param name
     */
    void delete(String name);
    
    /**
     * 根据名称修改值
     * @param setting
     */
    void update(Setting setting);
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    Setting queryByName(@Param("name") String name);
}
