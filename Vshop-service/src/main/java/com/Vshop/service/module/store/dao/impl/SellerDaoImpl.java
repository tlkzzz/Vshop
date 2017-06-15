//package com.Vshop.service.module.store.dao.impl;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Repository;
//
//import com.Vshop.core.entity.Seller;
//import com.Vshop.service.module.store.dao.SellerDao;
//import com.Vshop.service.module.store.dao.mapper.SellerMapepr;
//
///**
// * @author llf
// * @Package com.Vshop.service.module.store.dao.impl
// * @Description:
// * @date 2014/12/11 15:48
// */
//@Repository
//public class SellerDaoImpl implements SellerDao{
//
//    @Resource
//    private SellerMapepr sellerMapepr;
//    /**
//     * 保存
//     *
//     * @param seller
//     */
//    @Override
//    public void save(Seller seller) {
//        sellerMapepr.save(seller);
//    }
//    
//    @Override
//    public Seller findBySellerName(String sellerName) {
//        return sellerMapepr.findBySellerName(sellerName);
//    }
//
//    @Override
//    public void updateLastLoginTime(String sellerName) {
//    	sellerMapepr.updateLastLoginTime(sellerName);
//    }
//}
