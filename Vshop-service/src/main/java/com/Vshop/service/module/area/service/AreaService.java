package com.Vshop.service.module.area.service;


import java.util.List;
import java.util.Map;

import com.Vshop.core.entity.Area;


/**
 *    
 * 项目名称：gt-front   
 * 类名称：AreaService   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月27日 下午11:08:50   
 * 修改人：liuhao   
 * 修改时间：2014年12月27日 下午11:08:50   
 * 修改备注：   
 * @version    
 *
 */
public interface AreaService {
	
	public List<Area> queryAll();
	
	public List<Area> queryChildList(Integer parentid);
	
	public Area queryParentList(Integer parentid);
	
	public String getAreaByAreaId(Integer areaId);
	
	/**
     * 查询省市区信息
     * @return
     */
	public Map<String,Object> queryAllArea();
	
	/**
	 * 获得一个 省市的地区信息
	 */
	public List<Area> getProvinceCityArea();
	
	/**
	 * 获取所有的地区信息
	 * @return
	 */
	List<Area> queryProvince();

}
