package com.Vshop.core.common;

public final class Constants {
	public final static int NUMBER_PER_PAGE = 10;

	public final static String MSG_URL = "/commons/show_msg";
	
	/**
	 * 评论图片上传路径
	 */
	public final static String EVALUATE_UPLOAD_URL = "upload/img/evaluate/";

	/**
	 * 品牌图片上传路径
	 */
	public final static String BRAND_UPLOAD_URL = "upload/img/brand/";
	
	/**
	 * 广告图片上传路径
	 */
	public final static String ADV_UPLOAD_URL = "upload/img/adv/";
	
	/**
	 * 商品图片上传路径
	 */
	public final static String GOODS_UPLOAD_URL = "upload/img/store/goods/";
	
	/**
	 * 优惠券图片上传路径
	 */
	public final static String LOGO_UPLOAD_URL = "upload/img/store/slide/";

    /**
     * 文章图片上传路径
     */
    public final static String ARRTICLE_UPLOAD_URL = "upload/attach/article/";
    /**
     * 供应商商品图片,规格图片,一切关于供应商下的图片的根目录.注意:在这目录后面需要加上供应商id
     */
    public static final String SUPPLIER_IMG_PATH = "upload/img/supplier/";
	/**
	 * 品牌图片上传路径
	 */
	public final static String LOGO_UPLOAD_VALUE = "0";
	public final static String BANNER_UPLOAD_VALUE = "1";
	
	/**
	 * 导航图片上传路径
	 */
	public final static String BANNER_UPLOAD_URL = "upload/img/banner/";
	

    /**
     * 会员相关上传路径
     */
    public final static String MEMBER_UPLOAD_URL = "upload/img/avatar/";
    /**
     * 店铺图片
     */
    public final static String STORE_UPLOAD_URL = "upload/img/store/store/";
    
    /**
     * 平台LOGO
     */
    public final static String SITE_LOGO_URL = "upload/logo/";
    /**
     * 相册图片
     */
    public final static String IMGALBUM_UPLOAD_URL = "upload/img/imgAlbum/imgAlbum/";

    public final static int FILE_SIZE_IMG = 102400000;

    public static final String USER_SESSION_KEY = "user_session_key";
    
    public static final String ADMIN_SESSION_KEY = "admin_session_key";

    /**
     * 找回密码IP地址
     */
    public static final String FORGET_IP = "http://localhost:8080/Vshop-front";

    public static final String CART_KEY = "cart_key";
    
    /**
     * seller日志EXCEL,导出和导入的路径
     */
    public static final String SELLERLOG_PATH = "/upload/attach/sellerlog";
    public static final String WXHB_PATH = "/upload/wxhb";
    /**
     * admin日志EXCEL,导出和导入的路径
     */
    public static final String ADMINLOG_PATH = "/upload/attach/adminlog";
    
    /**
     * 规格图片上传
     */
    public static final String SPECIMAGE_PATH = "upload/img/spec/";
    
    /**
     * 会员等级图片上传
     */
    public static final String MEMBER_GRADE_PATH = "upload/img/membergrade/";
    
    /**
     * 店铺内的商品图片,规格图片,一切关于店铺下的图片的根目录.注意:在这目录后面需要加上店铺id
     */
    public static final String STORE_IMG_PATH = "upload/img/store/";
    
    /**
     * 水印图片路径
     */
    public static final String MASK_PATH = "/upload/img/water";
    
    /**
     * 商品搜索索引
     */
    public static final String GOODS_SEARCH_INDEX_PATH = "/index/goods";
    
    /**
     * 店铺搜索索引
     */
    public static final String STORE_SEARCH_INDEX_PATH = "/index/store";
    
    /**
     * 报表
     * 店铺销售情况
     */
    public static final String REPORT_STORE = "/store";
    
    /**
     * 报表
     * 商品情况
     */
    public static final String REPORT_GOODS = "/goods";
    
    /**
     * 报表
     * 结算情况
     */
    public static final String REPORT_BALANCE = "/balance";
    
    /**
     * 报表
     * 订单
     */
    public static final String REPORT_ORDER = "/order";
    /**
     * 
     * 店铺二维码
     */
    public final static String STORE_TWOCODE_URL = "/upload/img/storetwocode";
    /**
     * 后台报表
     */
    public static final String REPORT_ADMIN = "/admin";
    /**
     * 
     * 支付logo
     */
    public final static String STORE_PAYMENT_LOGO = "upload/img/paymentlogo/";
    
    /**
     * 首页静态化模板
     */
    public final static String INDEX_MODEL = "/index/index.ftl";
    
    /**
     * 商品详细页静态化模板
     */
    public final static String GOODS_DETAIL_MODEL = "/product/product-detail.ftl";
    
    /**
     * 静态的主页
     */
    public final static String STATIC_INDEX = "";
    
    /**
     * 静态的商品页面
     */
    public final static String STATIC_GOODS_DETAIL = "/goods";
    
	/** 联盟商品分类图标存放路径 */
	public final static String UNION_GOODSCLASS_UPLOAD_URL = "/upload/img/union/goodsclass";
	
	/**
	 * 敏感词上传路径
	 */
	public final static String SENSITIVE_UPLOAD_URL = "/sensitive";
	
	/**
	 * 平台自营店铺id
	 */
    public final static Integer PLATFORM_STORE_ID = 0;
    
    /**
	 * 供应商相关图片上传路径
	 */
	public final static String SUPPLIER_UPLOAD_URL = "upload/img/supplier/";
}
