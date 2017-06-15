package com.Vshop.supplier.utils;

import com.Vshop.core.common.PropertiesLoader;

public class CommonConstants {

    private static PropertiesLoader propertiesLoader = new PropertiesLoader("conf/Vshop.properties");

    public static final String IMG_SERVER = propertiesLoader.getProperty("img.server");
    public static final String FILE_BASEPATH = propertiesLoader.getProperty("file.basepath");
    public static final String ADMIN_SERVER = propertiesLoader.getProperty("admin.server");
    public static final String FRONT_SERVER = propertiesLoader.getProperty("front.server");
    public static final String SELLER_SERVER = propertiesLoader.getProperty("seller.server");
    public static final String MASK_TYPE = propertiesLoader.getProperty("mask.type");
    public static final String LUCENE_BASEPATH = propertiesLoader.getProperty("lucene.server");
    public static final String REPORT_BASEPATH = propertiesLoader.getProperty("report.basepath");
}
