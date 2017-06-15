//package com.Vshop.service.module.store.service.impl;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.apache.commons.lang3.StringUtils;
//import org.joda.time.DateTime;
//import org.springframework.stereotype.Service;
//
//import com.Vshop.core.common.Collections3;
//import com.Vshop.core.common.DateUtils;
//import com.Vshop.core.entity.StoreDetail;
//import com.Vshop.service.module.goods.dao.GoodsDao;
//import com.Vshop.service.module.store.dao.StoreDetailDao;
//import com.Vshop.service.module.store.service.StoreDetailService;
//import com.Vshop.service.utils.page.Pager;
//
///**
// * 
// *    
// * 项目名称：Vshop-admin   
// * 类名称：StoreDetailServiceImpl   
// * 类描述：   
// * 创建人：yanghui   
// * 创建时间：2014年11月7日 下午2:04:30   
// * 修改人：yanghui   
// * 修改时间：2014年11月7日 下午2:04:30   
// * 修改备注：   
// * @version    
// *
// */
//@Service("storeDetailService")
//@Slf4j
//public class StoreDetailServiceImpl implements StoreDetailService {
//
//	@Resource
//    private StoreDetailDao storeDetailDao;
//    @Resource
//    private GoodsDao goodsDao;
//
//	/**
//	 * 
//	 * @Title: countStoreDetail 
//	 * @Description: TODO(count总数查询) 
//	 * @param @param pager
//	 * @param @return    设定文件 
//	 * @return int    返回类型 
//	 * @throws
//	 */
//	public int countStoreDetail(Pager pager) {
//		log.info("获取log列表记录数");
//		return storeDetailDao.countStoreDetail(pager);
//	}
//
//	/**
//     * 
//     * @Title: queryStoreDetailList 
//     * @Description: TODO(带分页list 查询) 
//     * @param @param pager
//     * @param @return    设定文件 
//     * @return List<StoreDetail>    返回类型 
//     * @throws
//     */
//	public List<StoreDetail> queryStoreDetailList(Pager pager) {
//
//		log.info("获取log列表List");
//        List<StoreDetail> list = storeDetailDao.queryStoreDetailList(pager);
//        //加入店铺状态
//        if(Collections3.isNotEmpty(list)){
//            for(StoreDetail storeDetail : list){
//                storeDetail.setStoreStatus(getStoreStatus(storeDetail.getStoreState(),storeDetail.getStoreEndTime()));
//            }
//        }
//
//		return list;
//	}
//
//	 /**
//     * 
//     * @Title: delete 
//     * @Description: TODO(根据id删除数据) 
//     * @param @param id    设定文件 
//     * @return void    返回类型 
//     * @throws
//     */
//	public void delete(Long id) {
//		storeDetailDao.delete(id);
//	}
//
//	 /**
//     * 
//     * @Title: findById 
//     * @Description: TODO(根据ID 查询明细) 
//     * @param @param id
//     * @param @return    设定文件 
//     * @return StoreDetail    返回类型 
//     * @throws
//     */
//	public StoreDetail findById(Integer id) {
//		return storeDetailDao.findById(id);
//	}
//
//
//    public StoreDetail findByIdUinonGrade(Integer id) {
//        return storeDetailDao.findByIdUinonGrade(id);
//    }
//
//    /**
//	 * 
//	 * @Title: updateDetail 
//	 * @Description: TODO(更新店铺详情 ) 
//	 * @param @param storeDetail    设定文件 
//	 * @return void    返回类型 
//	 * @throws
//	 */
//	public void updateDetail(StoreDetail storeDetail){
//        //商品全部下架
//       /* if(storeDetail.getStoreState() == 0){
//            goodsDao.updateState(storeDetail.getStoreId());
//            goodsCommonDao.updateLock(storeDetail.getStoreId());
//        }else{
//            storeDetail.setStoreCloseInfo("");
//        }*/
//		storeDetailDao.updateDetail(storeDetail);
//	}
//
//    /**
//     * 店铺当前状态
//     * @param storeState
//     * @param storeEndTime
//     * @return
//     */
//    private String getStoreStatus(Integer storeState ,Timestamp storeEndTime){
//        String result = "open";
//        if(storeState == 1){
//            if(storeEndTime!=null){
//                long endTime = new DateTime(storeEndTime).getMillis();
//                long now = new DateTime().getMillis();
//                long ten = new DateTime(DateUtils.getBeforeDays(storeEndTime.toString(),-10,"yyyy-MM-dd","yyyy-MM-dd")).getMillis();
//                if(endTime < now){
//                    result = "expired";
//                }else if(ten < now){
//                    result = "expire";
//                }
//            }
//        }else{
//            result = "close";
//        }
//        return result;
//    }
//    
//    /**
//	 * @Description: TODO(count总数查询) 
//	 * @param @param storeDetail
//	 * @return int    返回类型 
//	 * @throws
//	 */
//    @Override
//    public int countStoreDetail(StoreDetail storeDetail){
//    	return storeDetailDao.countStoreDetail(storeDetail);
//    }
//}
//
