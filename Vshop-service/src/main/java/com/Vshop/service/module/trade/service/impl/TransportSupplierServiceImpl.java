package com.Vshop.service.module.trade.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.TransportSupplier;
import com.Vshop.core.entity.base.TransportExtend;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.core.state.transport.TransportState;
import com.Vshop.service.module.trade.dao.TransportSupplierDao;
import com.Vshop.service.module.trade.dao.TransportExtendDao;
import com.Vshop.service.module.trade.service.TransportSupplierService;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：Vshop-seller
 * @类名称：TransportSupplierServiceImpl
 * @类描述： 运费模板
 * @创建人：shining
 * @创建时间：2014年12月7日 下午10:46:32
 * @修改人：shining
 * @修改时间：2014年12月7日 下午10:46:32
 * @修改备注：
 * @version
 * 
 */
@Service
public class TransportSupplierServiceImpl implements TransportSupplierService {
	@Resource
	private TransportSupplierDao transportSupplierDao;

	@Resource
	private TransportExtendDao transportExtendDao;

	/**
	 * 查询列表
	 */
	@Override
	public List<TransportSupplier> queryList(Pager pager) {
		List<TransportSupplier> transportList = transportSupplierDao.queryList(pager);
		
		if(transportList != null){

			// 查询出各运费模板相关的运费模板扩展表数据
			for (TransportSupplier transport : transportList) {
				
				if(transport != null && transport.getId() != null){
					
					List<TransportExtend> transportExtendList = transportExtendDao.queryByTransportId(transport.getId());
		
					transport.setTransportExtendList(transportExtendList);

				}
				
			}
	
			return transportList;
	
			}
		
		return null;
	}

	@Override
	public TransportSupplier findById(Integer id) {
		TransportSupplier transport = transportSupplierDao.findById(id);

		// 查询出运费模板相关的运费模板扩展表数据
		List<TransportExtend> transportExtendList = transportExtendDao
				.queryByTransportId(transport.getId());

		transport.setTransportExtendList(transportExtendList);

		return transport;
	}

	/**
	 * 复制模板
	 */
	@Override
	public void addCopy(Integer id) {
		TransportSupplier transport = transportSupplierDao.findById(id);

		// 复制运费模板表内容
		TransportSupplier transportCopy = new TransportSupplier();
		transportCopy.setSendTplId(transport.getSendTplId());
		transportCopy.setSupplierId(transport.getSupplierId());
		transportCopy.setTitle(transport.getTitle() + "的副本");
		transportCopy.setCreateTime(System.currentTimeMillis());

		transportSupplierDao.save(transportCopy);

		// 复制运费模板扩展表内容
		List<TransportExtend> transportExtendList = transportExtendDao
				.queryByTransportId(transport.getId());

		for (TransportExtend transportExtend : transportExtendList) {
			TransportExtend transportExtendCopy = new TransportExtend();

			transportExtendCopy.setAreaId(transportExtend.getAreaId());
			transportExtendCopy.setTopAreaId(transportExtend.getTopAreaId());
			transportExtendCopy.setAreaName(transportExtend.getAreaName());
			transportExtendCopy.setSnum(transportExtend.getSnum());
			transportExtendCopy.setSprice(transportExtend.getSprice());
			transportExtendCopy.setXnum(transportExtend.getXnum());
			transportExtendCopy.setXprice(transportExtend.getXprice());

			transportExtendCopy.setTransportId(transportCopy.getId());
			transportExtendCopy.setTransportTitle(transportCopy.getTitle());

			transportExtendDao.save(transportExtendCopy);
		}

	}

	/**
	 * 删除模板
	 */
	@Override
	public void delete(Integer id) {
		transportSupplierDao.delete(id);
		transportExtendDao.delete(id);
	}

	/**
	 * @Title: save
	 * 增加运费模板
	 */
	@Override
	public void save(Integer supplierId, String title, String tranStr) {
		//运费模板
		TransportSupplier transport = new TransportSupplier();
		transport.setSendTplId(1);//写死的
		transport.setSupplierId(supplierId);
		transport.setTitle(title);
		transport.setCreateTime(System.currentTimeMillis());
		//保存模板
		transportSupplierDao.save(transport);
		saveTransportExtend(tranStr, title, transport.getId());
	}

	@Override
	public void update(Integer transportId, Integer supplierId, String title, String tranStr) {
		//运费模板
		TransportSupplier transport = new TransportSupplier();
		transport.setId(transportId);
		transport.setTitle(title);
		transport.setSupplierId(supplierId);
		transport.setUpdateTime(System.currentTimeMillis());
		transportSupplierDao.update(transport);
		//删除原先的
		transportExtendDao.delete(transportId);
		saveTransportExtend(tranStr, title, transportId);
	}
	
	private void saveTransportExtend(String tranStr, String title, Integer transportId){
		List<Object> list = JsonUtils.readJsonList(tranStr, TransportExtend.class);
		for(Object obj : list){
			TransportExtend transportExtend = (TransportExtend) obj;
			if(transportExtend.getAreaId() == null || transportExtend.getAreaName() == null){
				transportExtend.setAreaId("");
				transportExtend.setAreaName("全国");
			}
			transportExtend.setTransportTitle(title);
			transportExtend.setTransportId(transportId);
			//保存模板扩展
			transportExtendDao.save(transportExtend);
		}
		
	}
	
	
	/**
	 * 
	 * @Title: 修改默认运费模板
	 * setDefaultTransport
	 */
	@Override
	public void updateDefaultTransport(Integer supplierId, Integer transportId) {
		
		TransportSupplier transport = new TransportSupplier();
		
		transport.setSupplierId(supplierId);
		
		transport.setIsDefault(TransportState.NOT_DEFAULT);
		
		transport.setUpdateTime(System.currentTimeMillis());
		
		transportSupplierDao.update(transport);
		
		/*设置当前这个运费模板为默认的*/
		transport.setId(transportId);
		
		transport.setIsDefault(TransportState.IS_DEFAULT);
		
		transportSupplierDao.update(transport);
		
	}
	
	/**
	 * 通过店铺id 获得当前的默认运费模板
	 * @param supplierId
	 * @return
	 */
	public TransportSupplier getDefTransportBySupplierId(Integer supplierId){

		TransportSupplier transport = transportSupplierDao.getDefTransportBySupplierId(supplierId);
		
		if(transport != null && transport.getId() != null){
			
			// 查询出运费模板相关的运费模板扩展表数据
			List<TransportExtend> transportExtendList = transportExtendDao.queryByTransportId(transport.getId());
	
			transport.setTransportExtendList(transportExtendList);
	
			return transport;
		}
		
		return null;
	}
	

	/**
	 * 通过transportId,二级地区id(市级id),商品数量
	 * 得到对应的运费模板的运费价格
	 * 如果二级地区id(cityId)设为null,那么返回的价格是默认的运费模板(全国)的价格标准
	 */
	@Override
	public BigDecimal getFreightForTransport(Integer transportId,String type, Integer cityId, int goodsCount) {
			return getTotalFreight(transportId, type, cityId, goodsCount);
	}
	
	/**
	 * 通过supplierId,送货类型(平邮py 快递kd EMS es),二级地区id(市级id),商品数量
	 * 得到对应的运费模板的运费价格
	 * 如果二级地区id(cityId)设为null,那么返回的价格是默认的运费模板(全国)的价格标准
	 */
	@Override
	public BigDecimal getFreightForSupplier(Integer supplierId, String type, Integer cityId, int goodsCount) {
		
		if(supplierId != null){
			TransportSupplier transport = getDefTransportBySupplierId(supplierId);
			
			if(transport != null){
				return getTotalFreight(transport.getId(), type, cityId, goodsCount);
			}
			
		}
		
		return null;
	}
	
	/**
	 * 得到运费
	 */
	private BigDecimal getTotalFreight(Integer transportId,String type, Integer cityId, int goodsCount){
		if(transportId != null && goodsCount != 0){
			/**总运费*/
			BigDecimal totalFreight = new BigDecimal(0);
			
			TransportExtend transportExtendCondition = new TransportExtend();
			
			/*
			 * 运费模板id 
			 */
			transportExtendCondition.setTransportId(transportId);
			
			/*
			 * 送货类型
			 */
			transportExtendCondition.setType(type);
			
			TransportExtend transportExtend = null;
			
			/**用默认的模板运费*/
			if(cityId == null){
				
				transportExtend = transportExtendDao.selectTransportExtendByCondition(transportExtendCondition);
				
			}else{/**查询特定的*/
				
				transportExtendCondition.setAreaId(cityId + "");
				
				transportExtend = transportExtendDao.selectTransportExtendByCondition(transportExtendCondition);
				
			}
			
			if(transportExtend != null){
				totalFreight = getFreightByTransportExtend(transportExtend, goodsCount);
			}else{
				
				transportExtendCondition.setAreaId(null);
				
				transportExtend = transportExtendDao.selectTransportExtendByCondition(transportExtendCondition);
				
				totalFreight = getFreightByTransportExtend(transportExtend, goodsCount);
			}
			
			if(totalFreight != null){
				return totalFreight;
			}
		}
		return null;
	}
	
	/**
	 * 通过transportExtend计算运费
	 */
	private BigDecimal getFreightByTransportExtend(TransportExtend transportExtend, int goodsCount){
		
		if(transportExtend != null && goodsCount != 0){
			
			/*
			 * 最低起批数量
			 */
			int snum = transportExtend.getSnum();
			
			/*
			 * 判断商品数量,是否超过最低的起批数量
			 */
			if(snum >= goodsCount){/*未超过*/
				
				return BigDecimal.valueOf(transportExtend.getSprice());
				
			}else{/*超过*/
				
				BigDecimal baseFreight = BigDecimal.valueOf(transportExtend.getSprice());
				
				int outCount = goodsCount - transportExtend.getSnum();
				
				int xnum = transportExtend.getXnum();
				
				if(outCount % xnum != 0){
					
					int baseNum = (outCount / xnum) + 1;
					
					BigDecimal outPrice = BigDecimal.valueOf(baseNum*transportExtend.getXprice());
					
					return baseFreight.add(outPrice);
					
				}else{
					
					int baseNum = outCount / xnum;
					
					BigDecimal outPrice = BigDecimal.valueOf(baseNum*transportExtend.getXprice());
					
					return baseFreight.add(outPrice);
					
				}
				
			}
			
		}
		
		return null;
	}
	
	
}
