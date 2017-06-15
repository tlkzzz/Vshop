package com.Vshop.service.module.trade.dao;

import java.util.List;

import com.Vshop.core.entity.TransportSupplier;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：TransportDao
 * @类描述： 运费模板
 * @创建人：shining
 * @创建时间：2014年12月7日 下午10:47:25
 * @修改人：shining
 * @修改时间：2014年12月7日 下午10:47:25
 * @修改备注：
 * @version
 * 
 */
public interface TransportSupplierDao {
	/**
	 * 
	 * @Title: queryList
	 * @Description: 查询列表
	 * @param @param pager
	 * @param @return 设定文件
	 * @return List<Transport> 返回类型
	 * @throws
	 */
	public List<TransportSupplier> queryList(Pager pager);

	/**
	 * 
	 * @Title: findById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return TransportSupplier 返回类型
	 * @throws
	 */
	public TransportSupplier findById(Integer id);

	/**
	 * 
	 * @Title: save
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param transport 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void save(TransportSupplier transport);

	/**
	 * 
	 * @Title: delete
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void delete(Integer id);
	
	/**
	 * 
	 * @Title: update
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void update(TransportSupplier transport);
	
	/**
	 * 通过店铺id 获得当前的默认运费模板
	 * @param storeId
	 * @return
	 */
	public TransportSupplier getDefTransportBySupplierId(Integer supplierId);
	
}
