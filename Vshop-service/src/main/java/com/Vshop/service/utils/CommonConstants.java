package com.Vshop.service.utils;

import com.Vshop.core.common.PropertiesLoader;

public class CommonConstants {
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("conf/Vshop.properties");

    public static final String IMG_SERVER = propertiesLoader.getProperty("img.server");
    public static final String FILE_BASEPATH = propertiesLoader.getProperty("file.basepath");
    public static final String ADMIN_SERVER = propertiesLoader.getProperty("admin.server");
    public static final String FRONT_SERVER = propertiesLoader.getProperty("front.server");
    public static final String SELLER_SERVER = propertiesLoader.getProperty("seller.server");
    //public static final String IMG_BASEPATH = propertiesLoader.getProperty("img.basepath");
    public static final String LUCENE_BASEPATH = propertiesLoader.getProperty("lucene.server");
    public static final String REPORT_BASEPATH = propertiesLoader.getProperty("report.basepath");
    public static final String MODEL_BASEPATH = propertiesLoader.getProperty("model.basepath");
    public static final String STATIC_PAGE_BASEPATH = propertiesLoader.getProperty("staticpage.basepath");
}
