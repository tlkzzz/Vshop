package com.Vshop.service.module.setting.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Setting;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.setting.dao.SettingDao;
import com.Vshop.service.module.setting.dao.mapper.SettingMapper;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：ClasssMapper   
 * 类描述：   
 * 创建人：weiyue   
 * 创建时间：2014年11月12日 下午10:43:47   
 * 修改人：weiyue   
 * 修改时间：2014年11月12日 下午10:43:47   
 * 修改备注：   
 * @version    
 *
 */
@Service("settingDao")
public class SettingDaoImpl extends BaseDao implements SettingDao {
    @Resource
    private SettingMapper settingMapper;

    /**
	 * 根据店铺二级域名查询设置信息
	 * @param map
	 * @return
	 */
    public List<Setting> queryClasssMap(Map map){
    	return settingMapper.queryClasssMap(map);
    }
    
    /**
     * 保存系统设置
     * @param setting
     */
    public void save(Setting setting){
    	settingMapper.save(setting);
    }
    
    /**
     * 根据名称删除
     * @param name
     */
    public void delete(String name){
    	settingMapper.delete(name);
    }
    
    /**
     * 根据名称修改值
     * @param setting
     */
    public void update(Setting setting){
    	settingMapper.update(setting);
    }
    
    /**
     * 根据名称查询
     * @param name
     * @return
     */
    public Setting queryByName(String name){
    	return settingMapper.queryByName(name);
    }
}
