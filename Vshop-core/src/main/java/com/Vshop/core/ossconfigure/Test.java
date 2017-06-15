package com.Vshop.core.ossconfigure;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.ObjectMetadata;

public class Test {
	 /** 
	    * 上传OSS服务器文 
	   * @Title: uploadFile  
	   * @Description:  
	   * @param @param ossConfigure 
	   * @param @param file 
	   * @param @param remotePath 
	   * @param @return 
	   * @param @throws Exception    设定文件  
	   * @return String    返回类型  
	   * @throws 
	    */  
	   public static String uploadFile(File file,String remotePath) throws Exception{  
	       InputStream fileContent=null;  
	       fileContent=new FileInputStream(file);  
	         								//中间 --key ---vilue
	       OSSClient ossClient=new OSSClient("http://oss-cn-hangzhou.aliyuncs.com", "utRFfXRDf8jEQcqP", "vbcABd1puWDmDcEmpf0huWjneGuLRr");  
	       System.out.println(ossClient+"7777777777");
	       if (ossClient.doesBucketExist("vixuan-shop")) {
	           System.out.println("您已经创建Bucket" + "vixuan-shop" + "");
	       } else {
	           System.out.println("您的Bucket不存在，创建Bucket" + "vixuan-shop" + "");
	           // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket
	           // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
	           ossClient.createBucket("vixuan-shop");
	       }
	       BucketInfo info = ossClient.getBucketInfo("vixuan-shop");
	       System.out.println("Bucket " + "vixuan-shop" + "的信息如下：");
	       System.out.println("\t数据中心" + info.getBucket().getLocation());
	       System.out.println("\t创建时间" + info.getBucket().getCreationDate());
	       System.out.println("\t用户标志" + info.getBucket().getOwner());
//	       String remoteFilePath = remotePath.substring(0, remotePath.length()).replaceAll("\\\\","/")+"/";  
	       //创建上传Object的Metadata  
	       ObjectMetadata objectMetadata=new ObjectMetadata();  
//	       长度
	       objectMetadata.setContentLength(fileContent.available());  
//	       缓存
	       objectMetadata.setCacheControl("no-cache");  
	       objectMetadata.setHeader("Pragma", "no-cache"); 
//	       System.out.println(file.getName().substring(file.getName().lastIndexOf(".")));
//	       如果不设置上传类型打开后会默认下载
	       objectMetadata.setContentType(file.getName().substring(file.getName().lastIndexOf(".")));  
//	       objectMetadata.setContentType(".png");
	       objectMetadata.setContentDisposition("inline;filename=" + file.getName());  
	       //上传文件     			//头名+自定义文件夹�?+ file文件对象+ objectmetadate
	       ossClient.putObject("vixuan-shop", remotePath + file.getName(), fileContent, objectMetadata);  
//	       System.out.println(ossConfigure.getAccessUrl()+"/" +remotePath + file.getName());  
//	       return ossConfigure.getAccessUrl()+"/" +remotePath + file.getName();  
	       return "";
	   }  
	     
	     
	   /** 
	    * 根据key删除OSS服务器上的文件
	   * @Title: deleteFile  
	   * @Description:  
	   * @param @param ossConfigure 
	   * @param @param filePath    设定文件  
	   * @return void    返回类型  
	   * @throws 
	    */  
	   public static void deleteFile(OSSConfigure ossConfigure,String filePath){  
	       OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(),ossConfigure.getAccessKeyId(), ossConfigure.getAccessKeySecret());  
	       ossClient.deleteObject(ossConfigure.getBucketName(), filePath);  
	   }  
	   
	   /** 
	    * 根据key删除OSS服务器上的文件
	   * @Title: deleteFile  
	   * @Description:  
	   * @param @param ossConfigure 
	   * @param @param filePath    设定文件  
	   * @return void    返回类型  
	   * @throws 
	    */  
//	   public static List<String> deleteFileList(OSSConfigure ossConfigure,List list){  
//		   OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(),ossConfigure.getAccessKeyId(), ossConfigure.getAccessKeySecret());  
//		   DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(ossConfigure.getBucketName()).withKeys(list));
//			//deletedObjects 为删除路径图片名称
//		   List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
//			return deletedObjects;
//	         
//	   }  
	   
	     
	    /** 
	    * Description: 判断OSS服务文件上传时文件的contentType 
	    * @Version1.0 
	    * @param FilenameExtension 文件后缀 
	    * @return String  
	    */  
	    public static String contentType(String FilenameExtension){  
	       if(FilenameExtension.equals("BMP")||FilenameExtension.equals("bmp")){return "image/bmp";}  
	       if(FilenameExtension.equals("GIF")||FilenameExtension.equals("gif")){return "image/gif";}  
	       if(FilenameExtension.equals("JPEG")||FilenameExtension.equals("jpeg")||  
	          FilenameExtension.equals("JPG")||FilenameExtension.equals("jpg")||     
	          FilenameExtension.equals("PNG")||FilenameExtension.equals("png")){return "image/png";}  
	       if(FilenameExtension.equals("HTML")||FilenameExtension.equals("html")){return "text/html";}  
	       if(FilenameExtension.equals("TXT")||FilenameExtension.equals("txt")){return "text/plain";}  
	       if(FilenameExtension.equals("VSD")||FilenameExtension.equals("vsd")){return "application/vnd.visio";}  
	       if(FilenameExtension.equals("PPTX")||FilenameExtension.equals("pptx")||  
	           FilenameExtension.equals("PPT")||FilenameExtension.equals("ppt")){return "application/vnd.ms-powerpoint";}  
	       if(FilenameExtension.equals("DOCX")||FilenameExtension.equals("docx")||  
	           FilenameExtension.equals("DOC")||FilenameExtension.equals("doc")){return "application/msword";}  
	       if(FilenameExtension.equals("XML")||FilenameExtension.equals("xml")){return "text/xml";}  
	       return "text/html";  
	       
	    }  
	    
	    public static void main(String[] args) {
//	    	OSSConfigure ossConfigure=new OSSConfigure();
	    	File file=new File("C:\\sample1.jpg");
	    String fileurl;
		try {
			fileurl = Test.uploadFile( file, "img/");
		} catch (Exception e) {
			// TODO 自动生成
			e.printStackTrace();
		}
	   
//	    	OSSClient client = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(), ossConfigure.getAccessKeySecret());
//	    	boolean found = client.doesObjectExist(ossConfigure.getBucketName(), "img/ssss.txt");
//	    	System.out.println(found);
	    //删除单个文件	
//	    OSSManageUtil.deleteFile(ossConfigure, "img/ssss.txt");
	    	//删除多个文件
//	    	List<String> list=new ArrayList<String>();
//	    	list.add("img/sssss.txt");
//	    	list.add("img/ssss.txt");
	    	
	//   int k= 	OSSManageUtil.deleteFileList(ossConfigure, list);
	//   System.out.println(k);
	    	
		}
}
