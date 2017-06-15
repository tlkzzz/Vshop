package com.Vshop.service.utils.classInfo;

import java.lang.reflect.Field;


/**
 * 获得类中的各种信息
 * @author cgl
 * time:2015年07月06日16:37:35
 */
public class ClassInfo {

	
	/**
	 * 获得属性的类型
	 * @typeName:属性的名字
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public static Class<?> getFieldType(Class<?> clazz, String typeName) throws NoSuchFieldException, SecurityException{
		Field field = null;
		//基类baseEntity字段特殊处理
		if(typeName.equals("createTime") || 
				typeName.equals("updateTime") ||
				typeName.equals("updateTime") || 
				typeName.equals("startTime") ||
				typeName.equals("endTime") ||
				typeName.equals("createTimeStr") ||
				typeName.equals("updateTimeStr") ||
				typeName.equals("startTimeStr") ||
				typeName.equals("endTimeStr") ||
				typeName.equals("isDel")){
			clazz = clazz.getSuperclass();
			if(clazz == null){
				return null;
			}
			field = clazz.getDeclaredField(typeName);
		}else{
			//利用反射获得类中的属性的类型
			field = clazz.getDeclaredField(typeName);
		}
		return field.getType();
	}
}
