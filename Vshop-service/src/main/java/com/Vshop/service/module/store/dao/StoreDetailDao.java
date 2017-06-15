//package com.Vshop.service.module.store.dao;
//
//
//import java.util.List;
//
//import com.Vshop.core.entity.StoreDetail;
//import com.Vshop.service.utils.page.Pager;
//
///**
// * 
// *    
// * 项目名称：Vshop-admin   
// * 类名称：StoreDetailDao   
// * 类描述：   
// * 修改备注：   
// * @version    
// *
// */
//public interface StoreDetailDao {
//	
//	public int countStoreDetail(Pager pager);
//	
//	public List<StoreDetail> queryStoreDetailList(Pager pager) ;
//	
//	public void delete(Long id) ;
//	
//	public StoreDetail findById(Integer id) ;
//
//    StoreDetail findByIdUinonGrade(Integer id);
//
//    public void updateDetail(StoreDetail storeDetail);
//    
//    public void updateDomain(StoreDetail storeDetail);
//    
//    /**
//	 * @Description: TODO(count总数查询) 
//	 * @param @param storeDetail
//	 * @return int    返回类型 
//	 * @throws
//	 */
//    public int countStoreDetail(StoreDetail storeDetail);
//}
