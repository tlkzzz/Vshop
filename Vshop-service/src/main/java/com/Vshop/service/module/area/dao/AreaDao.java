package com.Vshop.service.module.area.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.Area;


/**
 * 
 */
public interface AreaDao {

	
	public List<Area> queryAll();
	
	public List<Area> queryChildList(Integer parentid);

	public Area queryParentList(@Param("parentId") Integer parentid);
	
	public String getAreaByAreaId(Integer areaId);
	
	/**
     * 根据城市id查询县个数
     * @return
     */
    public List<Area> queryCityCount();

    /**
     *  查询市
     * @param areaId
     * @return
     */
    public List<Area> queryByAreaId(String areaId);

    /**
     * 查询省市
     * @return
     */
    public List<Area> queryProvince();
	
}
