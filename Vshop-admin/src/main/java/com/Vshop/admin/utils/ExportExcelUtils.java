package com.Vshop.admin.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExportExcelUtils {
	
	public static void export(List<?> list,String url) throws Exception{
		export(list,null,url);
	}
	public static void exports(List<?> list,String url) throws Exception{
		exports(list,null,url);
	}
	//导出Excal
	public static void export(List<?> list, List<String> propertyNames, String url) throws Exception{
		
		Object obj = list.get(0);
		
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet(obj.getClass().getSimpleName() + "表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        //获取list中的对象所有属性的集合
        Field[] fields = obj.getClass().getDeclaredFields();        
        //获得这个对象属性的数目
      	int num = fields.length;
      	
      	//判断属性名字是不是为空
      	if(propertyNames != null &&  num == propertyNames.size()){      		
      		for(int i = 0; i < num; i++){
            	HSSFCell cell = row.createCell((short) i);
            
                cell.setCellValue(propertyNames.get(i));  
                cell.setCellStyle(style); 
           }
      	}
      	else{
            for(int i = 0; i < num; i++){
            	HSSFCell cell = row.createCell((short) i);  
           
                cell.setCellValue(fields[i].getName());  
                cell.setCellStyle(style); 
            } 
      	}
               	
        
        for (int i = 0; i < list.size(); i++){  
        	
        	//拿到当前的对象
        	obj = list.get(i);
        	
        	//拿到全类名,通过反射获取这个类
            Class<?> clazz = Class.forName(obj.getClass().getCanonicalName());
            //得到所有属性的集合
            fields = clazz.getDeclaredFields();       	       	
        	
        	//创建一行
            row = sheet.createRow((int) i + 1);  
            
            //创建单元格，并设置值  
            for(int j = 0; j < fields.length; j++){
            	Field field = fields[j];
//            	if(field.getName().equals("id")){
            	
            	
            	PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
            	
            	Method rm = pd.getReadMethod();
            	
            	if (rm.invoke(obj)  instanceof Integer) {
            		Integer number = (Integer) rm.invoke(obj);		
            		row.createCell((short) j).setCellValue(number);
				}
            	if(rm.invoke(obj)  instanceof String){
            		String str = (String) rm.invoke(obj);
            		row.createCell((short) j).setCellValue(str);
            	}
            	
            	if(rm.invoke(obj)  instanceof Timestamp){
            		String str = rm.invoke(obj) + "";
            		row.createCell((short) j).setCellValue(str);
            	}
            	
            }
        }
             
//        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(url + ".xls");  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }
	}
	//导出Excal
		public static void exports(List<?> list, List<String> propertyNames, String url) throws Exception{
			
			Object obj = list.get(0);
		
			
			// 第一步，创建一个webbook，对应一个Excel文件  
//	        HSSFWorkbook wb = new HSSFWorkbook(); 
	        SXSSFWorkbook wb = new SXSSFWorkbook(100000);//内存中保留 10000 条数据，以免内存溢出，其余写入 硬盘   
	       
	        
	        
	        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
//	        HSSFSheet sheet = wb.createSheet(obj.getClass().getSimpleName() + "表");  
	        Sheet sheet = wb.createSheet(obj.getClass().getSimpleName() + "表"); 
	        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
//	        HSSFRow row = sheet.createRow((int) 0);  
	        Row row = sheet.createRow((int) 0);
	        // 第四步，创建单元格，并设置值表头 设置表头居中  
	        CellStyle style = wb.createCellStyle();
//	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	        //获取list中的对象所有属性的集合
	        Field[] fields = obj.getClass().getDeclaredFields();        
	        //获得这个对象属性的数目
//	      	int num = fields.length;
	      	
//	      	//判断属性名字是不是为空
//	      	if(propertyNames != null &&  num == propertyNames.size()){      		
//	      		for(int i = 0; i < num; i++){
//	            	HSSFCell cell = row.createCell((short) i);
//	            
//	                cell.setCellValue(propertyNames.get(i));  
//	                cell.setCellStyle(style); 
//	           }
//	      	}
//	      	else{
//	            for(int i = 0; i < num; i++){
//	            	HSSFCell cell = row.createCell((short) i);
////	            	if(cell.equals("name") ||cell.equals("spbh") ||cell.equals("je") ||cell.equals("url")  ){
//	                cell.setCellValue(fields[i].getName());  
//	                cell.setCellStyle(style); 
//	            }
//	        XSSFCell.CELL_TYPE_STRING
	             Cell cell = row.createCell((short) 0);  
//	      		 HSSFCell cell = row.createCell((short) 0);  
	             cell.setCellValue("二维码地址");  
	             cell.setCellStyle(style);  
	             cell = row.createCell((short) 1);  
	             cell.setCellValue("商品名称");  
	             cell.setCellStyle(style);  
	             cell = row.createCell((short) 2);  
	             cell.setCellValue("商品编号");  
	             cell.setCellStyle(style);  
//	             cell = row.createCell((short) 3);  
//	             cell.setCellValue("金额(100=1元)");  
//	             cell.setCellStyle(style); 
	            
	            
//	            }
//	      	}
	               	
	        
	        for (int i = 0; i < list.size(); i++){  
	        	
	        	//拿到当前的对象
	        	obj = list.get(i);
	        	
	        	//拿到全类名,通过反射获取这个类
	            Class<?> clazz = Class.forName(obj.getClass().getCanonicalName());
	            //得到所有属性的集合
	            fields = clazz.getDeclaredFields();       	       	
	        	
	        	//创建一行
	            row = sheet.createRow((int) i + 1);  
	            
	            //创建单元格，并设置值  
	            for(int j = 0; j < fields.length; j++){
	            	Field field = fields[j];
	            	if(field.getName().equals("name") || field.getName().equals("spbh")  ||field.getName().equals("url")){
	            	
	            	
	            	PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
	            	
	            	Method rm = pd.getReadMethod();
	            	
	            	if (rm.invoke(obj)  instanceof Integer) {
	            		Integer number = (Integer) rm.invoke(obj);		
	            		row.createCell((short) j).setCellValue(number);
					}
	            	if(rm.invoke(obj)  instanceof String){
	            		String str = (String) rm.invoke(obj);
	            		row.createCell((short) j).setCellValue(str);
	            	}
	            	
	            	if(rm.invoke(obj)  instanceof Timestamp){
	            		String str = rm.invoke(obj) + "";
	            		row.createCell((short) j).setCellValue(str);
	            	}
	            	
	            }
	        }
	             
	        }  
//	         第六步，将文件存到指定位置  
	        try  
	        {  
	            FileOutputStream fout = new FileOutputStream(url + ".xlsx");  
	            wb.write(fout);  
	            fout.close();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }
		}
		
}
