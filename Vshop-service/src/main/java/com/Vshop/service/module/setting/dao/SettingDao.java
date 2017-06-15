package com.Vshop.service.module.setting.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.Setting;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：ClasssMapper   
 * 类描述：   
 * 创建人：weiyue   
 * 创建时间：2014年11月12日 下午10:42:57   
 * 修改人：weiyue   
 * 修改时间：2014年11月12日 下午10:42:57   
 * 修改备注：   
 * @version    
 *
 */
public interface SettingDao {
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
