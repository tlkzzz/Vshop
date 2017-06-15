package com.Vshop.service.module.store.vo;

import lombok.Data;
import lombok.ToString;

/**
 * store class 店铺分类VO表
 *    
 * 项目名称：Vshop-entity   
 * 类名称：Classs   
 * 类描述：   
 * 创建人：weiyue   
 * 创建时间：2014年11月7日 下午09:08:00   
 * 修改人：weiyue   
 * 修改时间：2014年11月6日 下午09:08:00   
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class TypesVo {

	/**
	 * 父节点的ID
	 */
	private Integer pId;

	/**
	 * 父节点的名称
	 */
	private String pName;
	/**
	 * 父节点的排序
	 */
	private Integer pSort;
	/**
	 * 子节点的ID
	 */
	private Integer cId;
	
	/**
	 * 子节点的名称
	 */
	private String cName;
	/**
	 * 子节点的排序
	 */
	private Integer cSort;
	
	
}
