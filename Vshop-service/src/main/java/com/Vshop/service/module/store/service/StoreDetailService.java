//package com.Vshop.service.module.store.service;
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
// * 类名称：StoreDetailService   
// * 类描述：   
// * 修改备注：   
// * @version    
// *
// */
//public interface StoreDetailService {
//	
//	/**
//	 * 
//	 * @Title: countAdminLog 
//	 * @Description: TODO(count总数查询)  数据库适配后废弃
//	 * @param @param pager
//	 * @param @return    设定文件 
//	 * @return int    返回类型 
//	 * @throws
//	 */
//    public int countStoreDetail(Pager pager);
//    
//    /**
//     * 
//     * @Title: queryAdminLogList 
//     * @Description: TODO(带分页list 查询) 
//     * @param @param pager
//     * @param @return    设定文件 
//     * @return List<AdminLog>    返回类型 
//     * @throws
//     */
//    public List<StoreDetail> queryStoreDetailList(Pager pager);
//    
//    /**
//     * 
//     * @Title: delete 
//     * @Description: TODO(根据id删除数据) 
//     * @param @param id    设定文件 
//     * @return void    返回类型 
//     * @throws
//     */
//    public void delete(Long id);
//
//    
//    /**
//     * 
//     * @Title: findLogById 
//     * @Description: TODO(根据ID 查询明细) 
//     * @param @param id
//     * @param @return    设定文件 
//     * @return AdminLog    返回类型 
//     * @throws
//     */
//    public StoreDetail findById(Integer id);
//
//
//    StoreDetail findByIdUinonGrade(Integer id);
//    /**
//     * 
//     * @Title: updateDetail 
//     * @Description: TODO(更新店铺详情) 
//     * @param @param storeDetail    设定文件 
//     * @return void    返回类型 
//     * @throws
//     */
//    public void updateDetail(StoreDetail storeDetail);
//    
//    
//    /**
//	 * @Description: TODO(count总数查询) 
//	 * @param @param storeDetail
//	 * @return int    返回类型 
//	 * @throws
//	 */
//    public int countStoreDetail(StoreDetail storeDetail);
//    
//}
