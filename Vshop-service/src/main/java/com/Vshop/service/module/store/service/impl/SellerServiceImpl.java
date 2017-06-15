//package com.Vshop.service.module.store.service.impl;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.Vshop.core.entity.Seller;
//import com.Vshop.service.module.store.dao.SellerDao;
//import com.Vshop.service.module.store.service.SellerService;
//
//@Service
//public class SellerServiceImpl implements SellerService{
//
//    @Resource
//    private SellerDao sellerDao;
//
//    @Override
//    public Seller findBySellerName(String sellerName) {
//        return sellerDao.findBySellerName(sellerName);
//    }
//
//    @Override
//    public void updateLastLoginTime(String sellerName) {
//        sellerDao.updateLastLoginTime(sellerName);
//    }
//}
