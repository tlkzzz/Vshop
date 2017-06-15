package com.Vshop.service.module.trade.dao.mapper;

import java.util.List;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.TransportSupplier;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：TransportMapper
 * @类描述： 运费模板
 * @创建人：shining
 * @创建时间：2014年12月7日 下午10:48:46
 * @修改人：shining
 * @修改时间：2014年12月7日 下午10:48:46
 * @修改备注：
 * @version
 * 
 */
@SqlMapper
public interface TransportSupplierMapper {
	/**
	 * 
	 * @Title: queryList
	 * @Description: 查询列表
	 * @param @param pager
	 * @param @return 设定文件
	 * @return List<TransportSupplier> 返回类型
	 * @throws
	 */
	public List<TransportSupplier> queryList(Pager pager);

	public TransportSupplier findById(Integer id);

	public void save(TransportSupplier transport);

	public void delete(Integer id);
	
	public void update(TransportSupplier transport);
	
	/**
	 * 通过店铺id 获得当前的默认运费模板
	 * @param storeId
	 * @return
	 */
	public TransportSupplier getDefTransportBySupplierId(Integer supplierId);
	
	
	
}
