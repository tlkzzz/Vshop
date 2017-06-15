package com.Vshop.core.entity.base;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

/**
 * @author llf
 * @Package com.Vshop.core.entity.store
 * @Description:
 * @date 2014/11/14 15:43
 */

@Data
@ToString
public class Store extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 6160718121026169524L;

    private Integer storeId;

    private String storeName;//店铺名称

    private Integer storeAuth;//店铺认证,0-未认证，1-认证

    private Integer nameAuth;//店主认证，0-未认证，1-认证

    private Integer gradeId;//店铺等级

    private Integer memberId;//会员id

    private String memberName;//会员名称

    private String storeOwnerCard;//身份证

    private Integer scId;//店铺分类
    
    private Integer stId;//销售渠道分类

    private Integer areaId;//地区id

    private String areaInfo;//地区内容，冗余数据

    private String storeAddress;//详细地区

    private String storeZip;//邮政编码

    private String storeTel;//电话号码
    
    private String storeSms;//*******短信接口字段

    private String storeImage;//证件上传

    private String storeImage1;//执照上传

    private Integer storeState;//店铺状态，0关闭，1开启，2审核中

    private String storeCloseInfo;//店铺关闭原因

    private Integer storeSort;//店铺排序

    private Long storeTime;//店铺时间

    //private Long storeEndTime;//店铺关闭时间

    private String storeLabel;//店铺logo

    private String storeBanner;//店铺横幅
    
    private String storeLogo;//店标

    private String storeKeywords;//店铺seo关键字

    private String storeDescription;//店铺seo描述

    private String storeQq;//QQ

    private String storeWw;//阿里旺旺
    
    private String description;//店铺简介
    
    private String storeZy;//主营商品

    private String storeDomain;//店铺二级域名

    private Integer storeDomainTimes;//二级域名修改次数

    private Integer storeRecommend;//推荐，0为否，1为是，默认为0

    private String storeTheme;//店铺当前主题

    private Integer storeCredit;//店铺信用

    private Float praiseRate;//店铺好评率

    private Float storeDesccredit;//描述相符度分数

    private Float storeServicecredit;//服务态度分数

    private Float storeDeliverycredit;//发货速度分数
    
    private String storeCode;//店铺二维码

    private Integer storeCollect;//店铺收藏数量
    
    private String storeSlide;//店铺幻灯片
    
    private String storeSlideUrl;//店铺幻灯片链接
    
    private String storeCenterQuicklink;//*****卖家中心的常用操作快捷链接

    private String storeStamp;//店铺印章

    private String storePrintdesc;//打印订单页面下方说明文字

    private Integer storeSales;//店铺销量
    
    private String storePresales;//售前客服
    
    private String storeAftersales;//售后客服

    private String storeWorkingtime;//工作时间
    
	private Integer cityId;//所在地(市)
	 
    private Integer provinceId;//店铺所在地(省)
    
    private Integer storeClick;//店铺点击量
    
    private Integer storeGoodsCount; //商品数量
    
    private String storeclassname; //店铺分类名称
    
    private String gradename; //店铺等级名称
    
//    private Long createtime;//店铺创建时间
    
    private Long storeLogintime;//当前登陆时间
    
    private Long storeLastLogintime;//上次登陆时间
    
    private Timestamp storeLastLogintimestr;
    
    /**
     * 判断商品是否被用户搜藏(不映射数据库)
     */
    private Integer isFav;
    
    private String storeLongitude;//店铺经度
    
    private String storeAtitude;//店铺纬度
    
    /**
     * 传数据 数据库不存在
     */
    private String flag;
    
    private String bankName;
    private String bankAccountName;
    private String bankAccountNumber;
    private String weichatAccountNumber;
    
    private Integer storeType;
    
     
}
