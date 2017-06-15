package com.Vshop.service.module.setting.service;

import java.util.List;

import com.Vshop.core.entity.Site;
import com.Vshop.core.entity.base.OffPayArea;

/**
 * @author llf
 * @Package com.vixuan.service.module.setting.service
 * @Description:
 * @date 2014/12/8 14:44
 */
public interface SiteService {

	/**
	 * 查询所有信息
	 * @return 
	 */
	public Site  findById();
  
	/**
	 * 修改信息
	 * @param site
	 */
	public  void update(Site site);
	

}
