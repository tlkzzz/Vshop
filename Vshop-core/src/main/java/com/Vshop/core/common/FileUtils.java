
package com.Vshop.core.common;

import com.google.common.collect.Maps;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rabook on 2015/3/18.
 */
public class FileUtils {

	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
    static final String success = "success";
    static final String result = "result";
    static final String fileName = "filename";
    // 最大文件大小
 	private static long maxSize = 1000000;
 	
 	// 定义允许上传的文件扩展名
 	private static final Map<String, String> extMap = new HashMap<String, String>();
 	
 	static{
		// 其中images,flashs,medias,files,对应文件夹名称,对应dirName
		// key文件夹名称
		// value该文件夹内可以上传文件的后缀名
		extMap.put("images", "gif,jpg,jpeg,png,bmp");
		extMap.put("flashs", "swf,flv");
		extMap.put("medias", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("files", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		extMap.put("sensitive", "txt");
	}

    
    /**
     * 上传文件-多图上传
     * @param myFiles
     * @param targetDir
     * @param imgDir
     * @param type  文件格式类型
     * @param rename  是否重命名 0：原文件名 1：文件重命名
     * @return
     */
    public static Map<String,Object> fileUpload(MultipartFile[] myFiles,
            String targetDir,String imgDir,HttpServletRequest request,String type,int rename) throws IOException {
    	Map<String,Object> map = Maps.newHashMap();
        String originalFilename = "";
    	try {
    		for(MultipartFile myFile : myFiles){
    			Map<String,Object> map1 = fileUpload(myFile, targetDir, imgDir,request,type,rename);
    			if("true".equals(map1.get(success).toString())){
    				String imgPath = map1.get(result).toString();
    				originalFilename += imgPath + ",";
    			}
    		}
    		map.put(success, true); // 成功或者失败
    		if(originalFilename.length()>0){
    			map.put(result, originalFilename.substring(0, originalFilename.length() - 1)); // 上传成功的所有的图片地址的路径
    		}else{
    			map.put(result, originalFilename); // 上传成功的所有的图片地址的路径
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
    
    
    /**
     * 上传文件-单图上传
     * @param myFile
     * @param targetDir
     * @param imgDir
     * @param type  文件格式类型
     * @param rename  是否重命名 0：原文件名 1：文件重命名
     * @return
     * @throws IOException
     * @throws NullPointerException
     */
    public static Map<String,Object> fileUpload(MultipartFile myFile,
    		String targetDir,String imgDir,HttpServletRequest request,String type,int rename) throws IOException,NullPointerException {

        Map<String,Object> map = Maps.newHashMap();
        String originalFilename ;
        map.put(success,false);
		// boolean errorFlag = true;
		// 获取内容类型
		String contentType = request.getContentType();
		int contentLength = request.getContentLength();
		// 文件保存目录路径
		String savePath = targetDir;
		// 文件保存目录URL
		File uploadDir = new File(savePath);
		String fileExt = myFile.getOriginalFilename().substring(myFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
		
        if(myFile.isEmpty()){
            //上传图片为空
            map.put(result, "请选择文件后上传");
        }else if (!Arrays.<String> asList(extMap.get(type).split(",")).contains(fileExt)) {// 检查扩展名
        	map.put(result, "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(type) + "格式。");
		} else if (contentType == null || !contentType.startsWith("multipart")) {
			System.out.println("请求不包含multipart/form-data流");
			map.put(result, "请求不包含multipart/form-data流");
		} else if (maxSize < contentLength) {
			System.out.println("上传文件大小超出文件最大大小");
			map.put(result, "上传文件大小超出文件最大大小[" + maxSize + "]");
		} else if (!ServletFileUpload.isMultipartContent(request)) {
			map.put(result, "请选择文件");
		} else if (!uploadDir.isDirectory()) {// 检查目录
			map.put(result, "上传目录[" + savePath + "]不存在");
		} else if (!uploadDir.canWrite()) {
			map.put(result, "上传目录[" + savePath + "]没有写权限");
		} else{
        	mkdir(targetDir + imgDir);
        	if(rename==0){
        		originalFilename = myFile.getOriginalFilename();
        		org.apache.commons.io.FileUtils.copyInputStreamToFile(myFile.getInputStream(),
                        new File(targetDir + imgDir, originalFilename));
                map.put("totalSize", getSize((double) myFile.getInputStream().available()));
                map.put(result, imgDir + "/" + originalFilename);
                map.put(fileName , originalFilename);
                map.put("originalfilename", myFile.getOriginalFilename());
                map.put(success, true);
        	}else{
        		originalFilename = String.valueOf(new DateTime().getMillis())+
                        myFile.getOriginalFilename().substring( myFile.getOriginalFilename().lastIndexOf("."));
        		org.apache.commons.io.FileUtils.copyInputStreamToFile(myFile.getInputStream(),
                        new File(targetDir + imgDir, originalFilename));
                map.put("totalSize", getSize((double) myFile.getInputStream().available()));
                map.put(result, imgDir + "/" + originalFilename);
                map.put(fileName , originalFilename);
                map.put("originalfilename", myFile.getOriginalFilename());
                map.put(success, true);
        	}
        
            
        }
        return map;
    }
    
    /**
	 * 根据字节大小获取带单位的大小。
	 * @param size
	 * @return
	 */
	public static String getSize(double size) {
		DecimalFormat df = new DecimalFormat("0.00");
		if (size > 1024 * 1024) {
			double ss = size / (1024 * 1024);
			return df.format(ss) + " M";
		} else if (size > 1024) {
			double ss = size / 1024;
			return df.format(ss) + " KB";
		} else {
			return size + " bytes";
		}
	}
	
	/**
	 * 将文件路径规则化，去掉其中多余的/和\，去掉可能造成文件信息泄漏的../
	 */
	public static String normalizePath(String path) {
		path = path.replace('\\', '/');
		path = FileUtils.replaceEx(path, "../", "/");
		path = FileUtils.replaceEx(path, "./", "/");
		if (path.endsWith("..")) {
			path = path.substring(0, path.length() - 2);
		}
		path = path.replaceAll("/+", "/");
		return path;
	}

	public static File normalizeFile(File f) {
		String path = f.getAbsolutePath();
		path = normalizePath(path);
		return new File(path);
	}


	/**
	 * 以二进制方式读取文件
	 */
	public static byte[] readByte(String fileName) {
		fileName = normalizePath(fileName);
		try {
			FileInputStream fis = new FileInputStream(fileName);
			byte[] r = new byte[fis.available()];
			fis.read(r);
			fis.close();
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 以二进制方式读取文件
	 */
	public static byte[] readByte(File f) {
		f = normalizeFile(f);
		try {

			FileInputStream fis = new FileInputStream(f);
			byte[] r = readByte(fis);
			fis.close();
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取指定流，并转换为二进制数组
	 */
	public static byte[] readByte(InputStream is) {
		try {
			byte[] r = new byte[is.available()];
			is.read(r);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制数组写入指定文件
	 */
	public static boolean writeByte(String fileName, byte[] b) {
		fileName = normalizePath(fileName);
		try {
			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(fileName));
			fos.write(b);
			fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将二进制数组写入指定文件
	 */
	public static boolean writeByte(File f, byte[] b) {
		f = normalizeFile(f);
		try {
			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(f));
			fos.write(b);
			fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 以指定编码读取指定URL中的文本
	 */
	public static String readURLText(String urlPath, String encoding) {
		try {
			URL url = new URL(urlPath);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), encoding));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line + "\n");
			}
			in.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除文件，不管路径是文件还是文件夹，都删掉。<br>
	 * 删除文件夹时会自动删除子文件夹。
	 */
	public static boolean delete(String path) {
		path = normalizePath(path);
		File file = new File(path);
		return delete(file);
	}

	/**
	 * 删除文件，不管路径是文件还是文件夹，都删掉。<br>
	 * 删除文件夹时会自动删除子文件夹。
	 */
	public static boolean delete(File f) {
		f = normalizeFile(f);
		if (!f.exists()) {
			//LogUtil.getLogger().warn("文件或文件夹不存在：" + f);
			logger.info("文件或文件夹不存在：" + f);
			return false;
		}
		if (f.isFile()) {
			//LogUtil.getLogger().info("删除："+f.getAbsolutePath());
			return f.delete();
		} else {
			return FileUtils.deleteDir(f);
		}
	}

	/**
	 * 删除文件夹及其子文件夹
	 */
	private static boolean deleteDir(File dir) {
		dir = normalizeFile(dir);
		try {
			//LogUtil.getLogger().info("删除："+dir);
			return deleteFromDir(dir) && dir.delete(); // 先删除完里面所有内容再删除空文件夹
		} catch (Exception e) {
			//LogUtil.getLogger().warn("删除文件夹操作出错");
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * 创建文件夹
	 */
	public static boolean mkdir(String path) {
		path = normalizePath(path);
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return true;
	}

	/**
	 * 通配符方式删除指定目录下的文件或文件夹。<br>
	 * 文件名支持使用正则表达式（文件路径不支持正则表达式）
	 */
	public static boolean deleteEx(String fileName) {
		fileName = normalizePath(fileName);
		int index1 = fileName.lastIndexOf("\\");
		int index2 = fileName.lastIndexOf("/");
		index1 = index1 > index2 ? index1 : index2;
		String path = fileName.substring(0, index1);
		String name = fileName.substring(index1 + 1);
		File f = new File(path);
		if (f.exists() && f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (Pattern.matches(name, files[i].getName())) {
					logger.info("删除："+files[i].getAbsolutePath());
					files[i].delete();
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 删除文件夹里面的所有文件,但不删除自己本身
	 */
	public static boolean deleteFromDir(String dirPath) {
		dirPath = normalizePath(dirPath);
		File file = new File(dirPath);
		return deleteFromDir(file);
	}

	/**
	 * 删除文件夹里面的所有文件和子文件夹,但不删除自己本身
	 * 
	 * @param file
	 * @return
	 */
	public static boolean deleteFromDir(File dir) {
		dir = normalizeFile(dir);
		if (!dir.exists()) {
			logger.info("文件夹不存在：" + dir);
			return false;
		}
		if (!dir.isDirectory()) {
			logger.info(dir + "不是文件夹");
			return false;
		}
		File[] tempList = dir.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			logger.info("删除："+dir);
			if (!delete(tempList[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 从指定位置复制文件到另一个文件夹，复制时不符合filter条件的不复制
	 */
	public static boolean copy(String oldPath, String newPath, FileFilter filter) {
		oldPath = normalizePath(oldPath);
		newPath = normalizePath(newPath);
		File oldFile = new File(oldPath);
		File[] oldFiles = oldFile.listFiles(filter);
		boolean flag = true;
		if (oldFiles != null) {
			for (int i = 0; i < oldFiles.length; i++) {
				if (!copy(oldFiles[i], newPath + "/" + oldFiles[i].getName())) {
					flag = false;
				}
			}
		}
		return flag;
	}

	/**
	 * 从指定位置复制文件到另一个文件夹
	 */
	public static boolean copy(String oldPath, String newPath) {
		oldPath = normalizePath(oldPath);
		newPath = normalizePath(newPath);
		File oldFile = new File(oldPath);
		return copy(oldFile, newPath);
	}

	public static boolean copy(File oldFile, String newPath) {
		oldFile = normalizeFile(oldFile);
		newPath = normalizePath(newPath);
		if (!oldFile.exists()) {
			logger.info("文件或者文件夹不存在：" + oldFile);
			return false;
		}
		if (oldFile.isFile()) {
			return copyFile(oldFile, newPath);
		} else {
			return copyDir(oldFile, newPath);
		}
	}

	/**
	 * 复制单个文件
	 */
	private static boolean copyFile(File oldFile, String newPath) {
		oldFile = normalizeFile(oldFile);
		newPath = normalizePath(newPath);
		if (!oldFile.exists()) { // 文件存在时
			logger.info("文件不存在：" + oldFile);
			return false;
		}
		if (!oldFile.isFile()) { // 文件存在时
			logger.info(oldFile + "不是文件");
			return false;
		}
		if(oldFile.getName().equalsIgnoreCase("Thumbs.db")){
			logger.info(oldFile + "忽略此文件");
			return true;
		}
		
		try {
			int byteread = 0;
			InputStream inStream = new FileInputStream(oldFile); // 读入原文件
			File newFile = new File(newPath);
			//如果新文件是一个目录，则创建新的File对象
			if(newFile.isDirectory()){
				newFile = new File(newPath,oldFile.getName());
			}
			FileOutputStream fs = new FileOutputStream(newFile);
			byte[] buffer = new byte[1024];
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			fs.close();
			inStream.close();
		} catch (Exception e) {
			logger.info("复制单个文件" + oldFile.getPath() + "操作出错。错误原因:" + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 复制整个文件夹内容
	 */
	private static boolean copyDir(File oldDir, String newPath) {
		oldDir = normalizeFile(oldDir);
		newPath = normalizePath(newPath);
		if (!oldDir.exists()) { // 文件存在时
			logger.info("文件夹不存在：" + oldDir);
			return false;
		}
		if (!oldDir.isDirectory()) { // 文件存在时
			logger.info(oldDir + "不是文件夹");
			return false;
		}
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File[] files = oldDir.listFiles();
			File temp = null;
			for (int i = 0; i < files.length; i++) {
				temp = files[i];
				if (temp.isFile()) {
					if (!FileUtils.copyFile(temp, newPath + "/" + temp.getName())) {
						return false;
					}
				} else if (temp.isDirectory()) {// 如果是子文件夹
					if (!FileUtils.copyDir(temp, newPath + "/" + temp.getName())) {
						return false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			logger.info("复制整个文件夹内容操作出错。错误原因:" + e.getMessage());
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 移动文件到指定目录
	 */
	public static boolean move(String oldPath, String newPath) {
		oldPath = normalizePath(oldPath);
		newPath = normalizePath(newPath);
		return copy(oldPath, newPath) && delete(oldPath);
	}

	/**
	 * 移动文件到指定目录
	 */
	public static boolean move(File oldFile, String newPath) {
		oldFile = normalizeFile(oldFile);
		newPath = normalizePath(newPath);
		return copy(oldFile, newPath) && delete(oldFile);
	}

	/**
	 * 将可序列化对象序列化并写入指定文件
	 */
	public static void serialize(Serializable obj, String fileName) {
		fileName = normalizePath(fileName);
		try {
			FileOutputStream f = new FileOutputStream(fileName);
			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(obj);
			s.flush();
			s.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将可序列化对象序列化并返回二进制数组
	 */
	public static byte[] serialize(Serializable obj) {
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			ObjectOutputStream s = new ObjectOutputStream(b);
			s.writeObject(obj);
			s.flush();
			s.close();
			return b.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从指定文件中反序列化对象
	 */
	public static Object unserialize(String fileName) {
		fileName = normalizePath(fileName);
		try {
			FileInputStream in = new FileInputStream(fileName);
			ObjectInputStream s = new ObjectInputStream(in);
			Object o = s.readObject();
			s.close();
			return o;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从二进制数组中反序列化对象
	 */
	public static Object unserialize(byte[] bs) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(bs);
			ObjectInputStream s = new ObjectInputStream(in);
			Object o = s.readObject();
			s.close();
			return o;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将一个字符串中的指定片段全部替换，替换过程中不进行正则处理。<br>
	 * 使用String类的replaceAll时要求片段以正则表达式形式给出，有时较为不便，可以转为采用本方法。
	 */
	public static String replaceEx(String str, String subStr, String reStr) {
		if (str == null) {
			return null;
		}
		if (subStr == null || subStr.equals("") || subStr.length() > str.length() || reStr == null) {
			return str;
		}
		StringBuffer sb = new StringBuffer();
		int lastIndex = 0;
		while (true) {
			int index = str.indexOf(subStr, lastIndex);
			if (index < 0) {
				break;
			} else {
				sb.append(str.substring(lastIndex, index));
				sb.append(reStr);
			}
			lastIndex = index + subStr.length();
		}
		sb.append(str.substring(lastIndex));
		return sb.toString();
	}
	
	/**
	 * 通过File对象创建文件
	 * 
	 * @param file
	 * @param filePath
	 */
	public static void createFile(File file, String filePath) {
		int potPos = filePath.lastIndexOf('/') + 1;
		String folderPath = filePath.substring(0, potPos);
		createFolder(folderPath);
		FileOutputStream outputStream = null;
		FileInputStream fileInputStream = null;
		try {
			outputStream = new FileOutputStream(filePath);
			fileInputStream = new FileInputStream(file);
			byte[] by = new byte[1024];
			int c;
			while ((c = fileInputStream.read(by)) != -1) {
				outputStream.write(by, 0, c);
			}
		} catch (IOException e) {
			e.getStackTrace().toString();
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建文件夹
	 * 
	 * @param filePath
	 */
	public static void createFolder(String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (Exception ex) {
			System.err.println("Make Folder Error:" + ex.getMessage());
		}
	}
	
	/**
	 * 获取某个文件夹下的所有文件名称和上传时间
	 * 
	 * @param filePath
	 */
	public static File[] getFileListByFilePath(String filePath){
		File file = new File(filePath);
		File[] tempList = null;
		if(!file.exists()){
			logger.info("文件夹不存在：" + filePath);
		}else if(!file.isDirectory()){
			logger.info(filePath + "不是文件夹");
		}else{
			tempList = file.listFiles();
		}
		return tempList;
	}
	
	public static void main(String[] args) {
		File[] aaa = getFileListByFilePath("/Users/zhaorh/java/B2B2C/trunk/filebase/sensitive");
		System.out.println(aaa);
		for (File file : aaa) {
			System.out.println(file.getName());
			System.out.println(file.getPath());
			System.out.println(DateUtils.formatLongToStr(file.lastModified(), "yyyy-MM-dd HH:mm:ss"));
		}
	}

}
