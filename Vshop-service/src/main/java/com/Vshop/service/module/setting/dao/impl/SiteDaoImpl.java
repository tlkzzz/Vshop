package com.Vshop.service.module.setting.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.Site;
import com.Vshop.service.module.base.BaseDao;
import com.Vshop.service.module.setting.dao.SiteDao;
import com.Vshop.service.module.setting.dao.mapper.SiteMapper;

/**
 *    
 * 项目名称：vixuan-admin   
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
@Service("siteDao")
public class SiteDaoImpl extends BaseDao implements SiteDao {
    @Resource
    private SiteMapper siteMapper;

	@Override
	public Site findById() {
		// TODO 自动生成的方法存根
		return this.siteMapper.findById();
	}

	@Override
	public void update(Site site) {
		// TODO 自动生成的方法存根
		this.siteMapper.update(site);
	}


}
