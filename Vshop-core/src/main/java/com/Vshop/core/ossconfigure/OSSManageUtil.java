package com.Vshop.core.ossconfigure;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.ObjectMetadata;
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.model.ObjectMetadata;
import com.google.common.collect.Maps;

public class OSSManageUtil {
	
		static final String success = "success";
	    static final String result = "result";
	    static final String fileName = "filename";
	    // 最大文件大小
	 	private static long maxSize = 5000000;
	 	
	 	// 定义允许上传的文件扩展名
	 	private static final Map<String, String> extMap = new HashMap<String, String>();

		static {
			// 其中images,flashs,medias,files,对应文件夹名称,对应dirName
			// key文件夹名称
			// value该文件夹内可以上传文件的后缀名
			extMap.put("images", "gif,GIF,jpg,JPG,jpeg,JPEG,png,PNG,bmp,BMP");
			extMap.put("flashs", "swf,SWF,flv,FLV");
			extMap.put("medias", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,SWF,FLV,MP3,WAV,WMA,WMV,MID,AVI,MPG,ASF,RM,RMVB");
			extMap.put("files", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,DOC,DOCX,XLS,XLSX,PPT,HTM,HTML,TXT,ZIP,RAR,GZ,BZ2");
			extMap.put("sensitive", "txt,TXT");
		}
		/**
		 * 文件大小转换为字符串格式
		 * @param size 文件大小(单位B)
		 * @return
	     */
		public static String convertFileSize(long size) {
			long kb = 1024;
			long mb = kb * 1024;
			long gb = mb * 1024;

			if (size >= gb) {
				return String.format("%.1f GB", (float) size / gb);
			} else if (size >= mb) {
				float f = (float) size / mb;
				return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
			} else if (size >= kb) {
				float f = (float) size / kb;
				return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
			} else
				return String.format("%d B", size);
		}

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
   public static Map<String,Object> uploadFile(OSSConfigure ossConfigure,MultipartFile[] File,String remotePath,String type,HttpServletRequest request) throws Exception{
	   Map<String,Object> map = Maps.newHashMap();
	   String url="";
	   try {
	   for(MultipartFile file : File){
		   if(file.getOriginalFilename().equals("")){
				map.put(success,false);
	            map.put(result, null);
	            return map;
			}else{
		   CommonsMultipartFile cf= (CommonsMultipartFile)file; 
	        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	        File f = fi.getStoreLocation();
	        String n=file.getOriginalFilename();
		   Map<String,Object> map1 = fileUpload(ossConfigure,n,f,remotePath,type,request);
		   if("true".equals(map1.get(success).toString())){
				String imgPath = map1.get(result).toString();
				url += imgPath + ",";
			}else{
				map.put(success, false);
				map.put(result, map1.get(result));
				return map;
			}
			} 
       }
	   map.put(success, true); // 成功或者失败
		if(url.length()>0){
			map.put(result, url.substring(0, url.length() - 1)); // 上传成功的所有的图片地址的路径
		}else{
			map.put(result, url); // 上传成功的所有的图片地址的路径
		}
   } catch (NullPointerException e) {
		map.put(success,false);
       map.put(result, "上传失败");
		e.printStackTrace();
	} catch (IOException e) {
		map.put(success,false);
       map.put(result, "上传失败");
		e.printStackTrace();
	}
	   
       return map;
   }

   public static Map<String,Object> fileUpload(OSSConfigure ossConfigure,String a,File fname,String remotePath,String type,HttpServletRequest request) throws Exception{
	   Map<String,Object> map = Maps.newHashMap();
	   map.put(success,false);
	   String url="";
	   InputStream fileContent=new FileInputStream(fname);  
	   	// 获取内容类型
		String contentType = request.getContentType();
	
		int contentLength = request.getContentLength();
		
		System.out.println(contentLength);
		//文件格式
		String n=a.substring(a.lastIndexOf(".") + 1).toLowerCase();
		if(fname.length()<1){ 
           //上传图片为空
           map.put(result, "请选择文件后上传");
       }else
		if (!Arrays.<String> asList(extMap.get(type).split(",")).contains(n)) {// 检查扩展名
       	map.put(result, "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(type) + "格式。");
		}else 
			if (contentType == null || !contentType.startsWith("multipart")) {
			System.out.println("请求不包含multipart/form-data流");
			map.put(result, "请求不包含multipart/form-data流");
		}else if (maxSize < contentLength) {
			System.out.println("上传文件大小超出文件最大大小");
			map.put(result, "上传文件大小超出文件最大大小[" + convertFileSize(maxSize) + "]");
		} else if (!ServletFileUpload.isMultipartContent(request)) {
			map.put(result, "请选择文件");
		}else{
			ClientConfiguration conf = new ClientConfiguration();
			// Set the maximum number of allowed open HTTP connections
			conf.setMaxConnections(100);
			// Set the amount of time to wait (in milliseconds) when initially establishing
			// a connection before giving up and timing out
			conf.setConnectionTimeout(5000);

			// Set the maximum number of retry attempts for failed retryable requests
			conf.setMaxErrorRetry(3);

			// Set the amount of time to wait (in milliseconds) for data to betransfered over
			// an established connection before the connection times out and is closed
			conf.setSocketTimeout(5000);
			
			 OSSClient ossClient=new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(), ossConfigure.getAccessKeySecret());  
			    System.out.println(ossClient);
			 if (ossClient.doesBucketExist(ossConfigure.getBucketName())) {
		           System.out.println("您已经创建Bucket" + ossConfigure.getBucketName() + "");
		       } else {
		           System.out.println("您的Bucket不存在，创建Bucket" + ossConfigure.getBucketName() + "");
		           // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket
		           // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
		           ossClient.createBucket(ossConfigure.getBucketName());
		       }
		       BucketInfo info = ossClient.getBucketInfo(ossConfigure.getBucketName());
		       System.out.println("Bucket " + ossConfigure.getBucketName() + "的信息如下：");
		       System.out.println("\t数据中心" + info.getBucket().getLocation());
		       System.out.println("\t创建时间" + info.getBucket().getCreationDate());
		       System.out.println("\t用户标志" + info.getBucket().getOwner());
		       ObjectMetadata objectMetadata=new ObjectMetadata();
//		       长度
		       objectMetadata.setContentLength(fileContent.available());
//		       缓存
		       objectMetadata.setCacheControl("no-cache");
		       objectMetadata.setHeader("Pragma", "no-cache");
		      
//		       如果不设置上传类型打开后会默认下载
		       objectMetadata.setContentType(a.substring(a.lastIndexOf(".")).toLowerCase());
		       //完整路径
		       String name=String.valueOf(new DateTime().getMillis())+"."+n;
		       String fn=remotePath;
		       objectMetadata.setContentDisposition("inline;filename=" + name);
		       //上传文件     			//头名+自定义文件夹+ file文件对象+ objectmetadate
		       ossClient.putObject(ossConfigure.getBucketName(),  fn+name, fileContent, objectMetadata);
		       System.out.println(ossConfigure.getAccessUrl() +"/"+fn+name);
		        url ="/"+fn+name;
		       map.put(result, url);
               map.put(success, true);	   
		}
	   
	   return map;
   }
   /**
    * 上传商品图片
    * @param ossConfigure
    * @param f
    * @param remotePath
    * @return
    * @throws Exception
    */
   public static String uploadFile(OSSConfigure ossConfigure,String f,String remotePath) throws Exception{  
       InputStream fileContent=null;
       File file=new File(f);
       fileContent=new FileInputStream(file);  
         								//中间 --key ---vilue
       OSSClient ossClient=new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(), ossConfigure.getAccessKeySecret());  
       System.out.println(ossClient+"oss对象创建成功");
    
       //创建上传Object的Metadata  
       ObjectMetadata objectMetadata=new ObjectMetadata();  
//       长度
       objectMetadata.setContentLength(fileContent.available());  
//       缓存
       objectMetadata.setCacheControl("no-cache");  
       objectMetadata.setHeader("Pragma", "no-cache"); 
//       System.out.println(file.getName().substring(file.getName().lastIndexOf(".")));
//       如果不设置上传类型打开后会默认下载
       objectMetadata.setContentType(file.getName().substring(file.getName().lastIndexOf(".")));  
//       objectMetadata.setContentType(".png");
       objectMetadata.setContentDisposition("inline;filename=" + file.getName());  
       //上传文件     			//头名+自定义文件夹�?+ file文件对象+ objectmetadate
       ossClient.putObject(ossConfigure.getBucketName(), remotePath + file.getName(), fileContent, objectMetadata);  
       System.out.println(ossConfigure.getAccessUrl()+"/" +remotePath + file.getName());  
       return ossConfigure.getAccessUrl()+"/" +remotePath + file.getName();  
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
   public static List<String> deleteFileList(OSSConfigure ossConfigure,List list){
	   OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(),ossConfigure.getAccessKeyId(), ossConfigure.getAccessKeySecret());
	   DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(ossConfigure.getBucketName()).withKeys(list));
		//deletedObjects 为删除路径图片名称
	   List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
		return deletedObjects;

   }


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

//    public static void main(String[] args) {
//    	OSSConfigure ossConfigure=new OSSConfigure();
//    	File file=new File("C:\\QQ图片20160406115140h.gif");
//    String fileurl;
//	try {
//		fileurl = OSSManageUtil.uploadFile(ossConfigure, file, "img/");
//	} catch (Exception e) {
//		// TODO 自动生成
//		e.printStackTrace();
//	}
//
//    	OSSClient client = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessKeyId(), ossConfigure.getAccessKeySecret());
//    	boolean found = client.doesObjectExist(ossConfigure.getBucketName(), "img/ssss.txt");
//    	System.out.println(found);
    //删除单个文件
//    OSSManageUtil.deleteFile(ossConfigure, "img/ssss.txt");
    	//删除多个文件
//    	List<String> list=new ArrayList<String>();
//    	list.add("img/sssss.txt");
//    	list.add("img/ssss.txt");

//   int k= 	OSSManageUtil.deleteFileList(ossConfigure, list);
//   System.out.println(k);

//	}


}