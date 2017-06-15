package com.Vshop.service.module.trade.service;

import java.math.BigDecimal;
import java.util.List;

import com.Vshop.core.entity.Transport;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：TransportService
 * @类描述： 运费模板
 * @创建人：shining
 * @创建时间：2014年12月7日 下午10:45:08
 * @修改人：shining
 * @修改时间：2014年12月7日 下午10:45:08
 * @修改备注：
 * @version
 * 
 */
public interface TransportService {
	/**
	 * 
	 * @Title: queryList
	 * @Description: 查询列表
	 * @param @param pager
	 * @param @return 设定文件
	 * @return List<Transport> 返回类型
	 * @throws
	 */
	public List<Transport> queryList(Pager pager);

	/**
	 * 
	 * @Title: findById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 设定文件
	 * @return Transport 返回类型
	 * @throws
	 */
	public Transport findById(Integer id);

	/**
	 * 
	 * @Title: addCopy
	 * @Description: 复制模板
	 * @param @param id 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void addCopy(Integer id);

	/**
	 * 
	 * @Title: delete
	 * @Description: 删除模板
	 * @param @param id 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void delete(Integer id);
	
	/**
	 * 
	 * @Title: save
	 * 增加运费模板
	 */
	public void save(Integer storeId, String title,String tranStr);
	
	/**
	 * 
	 * @Title: 修改
	 * update
	 */
	public void update(Integer transportId, Integer storeId, String title,String tranStr);
	
	/**
	 * 
	 * @Title: 修改默认运费模板
	 * setDefaultTransport
	 */
	public void updateDefaultTransport(Integer storeId, Integer transportId);
	
	/**
	 * 通过店铺id 获得当前的默认运费模板
	 * @param storeId
	 * @return
	 */
	public Transport getDefTransportByStoreId(Integer storeId);
	
	/**
	 * 参数: 1. transportId: 运费模板id,
	 * 		2. type: 送货类型(平邮py 快递kd EMS es),
	 * 		3. cityId: 二级地区id(市级id),
	 * 		4. goodsCount: 商品数量
	 * 
	 * return:	对应的运费模板的运费总价格
	 * 
	 * 注:如果二级地区id(cityId)设为null,那么返回的价格是默认的运费模板(全国)的价格标准
	 */
	public BigDecimal getFreightForTransport(Integer transportId, String type, Integer cityId, int goodsCount);
	
	/**
	 * 参数: 1. storeId: 店铺id,
	 * 		2. type: 送货类型(平邮py 快递kd EMS es),
	 * 		3. cityId: 二级地区id(市级id),
	 * 		4. goodsCount: 商品数量
	 * 
	 * return:	对应的运费模板的运费总价格
	 * 
	 * 注:如果二级地区id(cityId)设为null,那么返回的价格是默认的运费模板(全国)的价格标准
	 */
	public BigDecimal getFreightForStore(Integer storeId, String type, Integer cityId, int goodsCount);
	
	
}
