package com.Vshop.core.ossconfigure;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.Vshop.core.common.CommonConstants;
import com.Vshop.core.common.Constants;
//import com.vixuan.service.utils.CommonConstants;

public class OSSConfigure {  
	  
    private String endpoint;  
    private String accessKeyId;  
    private String accessKeySecret;  
    private String bucketName;  
    private String accessUrl;  
    static Log log = LogFactory.getLog(OSSConfigure.class); 
    public OSSConfigure() {  
//      id
       accessKeyId=CommonConstants.ACCESS_ID;
//      key
       accessKeySecret=CommonConstants.ACCESS_KEY;
//      OSS_ENDPOINT
       endpoint=CommonConstants.OSS_ENDPOINT;
//      空间
       bucketName=CommonConstants.BUCKE_NAME;
//      url
       accessUrl=CommonConstants.ACCESS_URL;
    }  
  
    /** 
     * 通过配置文件.properties文件获取，这几项内容。 
     *  
     * @param storageConfName 
     * @throws IOException 
     */  
//    public OSSConfigures() throws IOException {  
////      id
//       accessKeyId=CommonConstants.ACCESS_ID;
////      key
//       accessKeySecret=CommonConstants.ACCESS_KEY;
////      OSS_ENDPOINT
//       endpoint=CommonConstants.OSS_ENDPOINT;
////      空间
//       bucketName=CommonConstants.BUCKE_NAME;
////      url
//       accessUrl=CommonConstants.ACCESS_URL;
//      
//      
    			
  
//        Properties prop = new Properties();  
//        prop.load(this.getClass().getClassLoader()  
//                .getResourceAsStream("conf/" + storageConfName));  
//  
//        endpoint = prop.getProperty("endpoint").trim();  
//        accessKeyId = prop.getProperty("accessKeyId").trim();  
//        accessKeySecret = prop.getProperty("accessKeySecret").trim();  
//        bucketName = prop.getProperty("bucketName").trim();  
//        accessUrl = prop.getProperty("accessUrl").trim();  
  
//    }  
  
    public OSSConfigure(String endpoint, String accessKeyId,  
            String accessKeySecret, String bucketName, String accessUrl) {  
  
        this.endpoint = endpoint;  
        this.accessKeyId = accessKeyId;  
        this.accessKeySecret = accessKeySecret;  
        this.bucketName = bucketName;  
        this.accessUrl = accessUrl;  
    }  
  
    public String getEndpoint() {  
        return endpoint;  
    }  
  
    public void setEndpoint(String endpoint) {  
        this.endpoint = endpoint;  
    }  
  
    public String getAccessKeyId() {  
        return accessKeyId;  
    }  
  
    public void setAccessKeyId(String accessKeyId) {  
        this.accessKeyId = accessKeyId;  
    }  
  
    public String getAccessKeySecret() {  
        return accessKeySecret;  
    }  
  
    public void setAccessKeySecret(String accessKeySecret) {  
        this.accessKeySecret = accessKeySecret;  
    }  
  
    public String getBucketName() {  
        return bucketName;  
    }  
  
    public void setBucketName(String bucketName) {  
        this.bucketName = bucketName;  
    }  
  
    public String getAccessUrl() {  
        return accessUrl;  
    }  
  
    public void setAccessUrl(String accessUrl) {  
        this.accessUrl = accessUrl;  
    }  
  
}  
