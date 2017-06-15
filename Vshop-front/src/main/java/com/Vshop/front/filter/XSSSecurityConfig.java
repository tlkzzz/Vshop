package com.Vshop.front.filter;

public class XSSSecurityConfig {
	   /**  
     * CHECK_HEADER：是否开启header校验  
     */  
    public static boolean IS_CHECK_HEADER=false;    
       
    /**  
     * CHECK_PARAMETER：是否开启parameter校验  
     */  
    public static boolean IS_CHECK_PARAMETER=true;   
       
    /**  
     * IS_LOG：是否记录日志  
     */  
    public static boolean IS_LOG=true;   
       
    /**  
     * IS_LOG：是否中断操作  
     */  
    public static boolean IS_CHAIN=true;   
       
    /**  
     * REPLACE：是否开启替换  
     */  
    public static boolean REPLACE=true;  
    
    /**  
     * FILTER_ERROR_PAGE:过滤后错误页面  
     */  
    public static String FILTER_ERROR_PAGE = "/commons/error.ftl";  
    
    /**  
     * IS_FILTER_REFERER：是否开启防盗链
     */  
    public static boolean IS_FILTER_REFERER=false; 
    /**  
     * 替换非法字符的字符串  
     */  
    public static String REPLACEMENT = "";  
}
