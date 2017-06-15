/**
 * 
 */
package com.Vshop.front.module.tag;

/**
 * <p>Title: TagsType.java</p>
 * <p>Description: 标签的数据返回类型</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月2日
 * @version 1.0
 */
public class TagsDataType {
	
	/**
	 * 单个对象
	 */
	public static final String ENTITY = "0";
	
	/**
	 * 无分页列表
	 */
	public static final String LIST = "1";
	
	/**分页列表
	 * 
	 */
	public static final String PAGE_LIST = "2";
	
	/**
	 * 自己和自己的子集合 树状的
	 */
	public static final String SELF_CHILD_LIST = "3";
	
	/**
	 * 树状结构
	 */
	public static final String TREE_LIST = "4"; 
	
	/**
	 * 总条数
	 */
	public static final String RECORD_COUNT = "5"; 

}
