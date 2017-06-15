package com.Vshop.core.entity.base;

import java.util.Date;

import lombok.Data;

@Data
public class StoreExpand {
	/**
     * shop_store_expand.store_id (店铺索引id)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private Integer storeId;

    /**
     * shop_store_expand.short_name (店铺简称)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String shortName;

    /**
     * shop_store_expand.store_no (店铺编号)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String storeNo;

    /**
     * shop_store_expand.contacter (联系人)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String contacter;

    /**
     * shop_store_expand.mobile
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String mobile;

    /**
     * shop_store_expand.fixed_tel
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String fixedTel;

    /**
     * shop_store_expand.fax
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String fax;

    /**
     * shop_store_expand.bus_type (经营业态-关联经营业态表)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private Integer busType;

    /**
     * shop_store_expand.market_type (市场类型)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private Integer marketType;

    /**
     * shop_store_expand.bus_license_no (营业执照注册号)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String busLicenseNo;

    /**
     * shop_store_expand.bus_licen_purl (营业执照图片)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String busLicenPurl;

    /**
     * shop_store_expand.dishui_regist_no (地税税务登记编号)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String dishuiRegistNo;

    /**
     * shop_store_expand.dishui_regist_purl (地税税务登记图片)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String dishuiRegistPurl;

    /**
     * shop_store_expand.guoshui_regist_no (国税税务登记编号)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String guoshuiRegistNo;

    /**
     * shop_store_expand.guoshui_regist_purl (国税税务登记照片)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String guoshuiRegistPurl;

    /**
     * shop_store_expand.zzshui_purl (增值税一般纳税人照片)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String zzshuiPurl;

    /**
     * shop_store_expand.legaler (法定代表人)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String legaler;

    /**
     * shop_store_expand.legaler_id (法定代表人身份证)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String legalerId;

    /**
     * shop_store_expand.legaler_purl (法定代表人身份证照片)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String legalerPurl;

    /**
     * shop_store_expand.account_bank (结算银行)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private String accountBank;

    /**
     * shop_store_expand.account_method (结算方式)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private Integer accountMethod;

    /**
     * shop_store_expand.account_cycle (结算周期)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private Integer accountCycle;

    /**
     * shop_store_expand.acount_date (月结日期)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private Date acountDate;

    /**
     * shop_store_expand.create_time (创建时间)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private Date createTime;

    /**
     * shop_store_expand.update_time (修改时间)
     * @ibatorgenerated 2016-03-12 15:36:04
     */
    private Date updateTime;
    
    private String storeLogo;
    
    private String remark;
}
