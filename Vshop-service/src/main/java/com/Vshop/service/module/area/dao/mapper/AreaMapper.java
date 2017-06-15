package com.Vshop.service.module.area.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.Area;

/**
 *    
 * 项目名称：gt-front   
 * 类名称：AreaMapper   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月27日 下午11:05:09   
 * 修改人：liuhao   
 * 修改时间：2014年12月27日 下午11:05:09   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface AreaMapper {
	public List<Area> queryAll();
	
	public List<Area> queryChildList(@Param("parentId") Integer parentid);
	
	public Area queryParentList(@Param("areaId") Integer parentid);
	
	public String getAreaByAreaId(Integer areaId);
	
	/**
     *  查询市
     * @param areaId
     * @return
     */
    public List<Area> queryByAreaId(@Param("areaId") String areaId);
	
	/**
     * 根据城市id查询县个数
     * @return
     */
    public List<Area> queryCityCount();

    /**
     * 查询省市
     * @return
     */
    public List<Area> queryProvince();
}
