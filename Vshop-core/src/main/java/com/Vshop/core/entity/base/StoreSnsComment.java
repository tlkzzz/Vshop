package com.Vshop.core.entity.base;



import lombok.Data;
import lombok.ToString;

/**
 * 店铺动态评论
 *    
 * 项目名称：Vshop-entity   
 * 类名称：StoreSnsComment   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月15日 下午6:55:35   
 * 修改人：yanghui   
 * 修改时间：2014年11月15日 下午6:55:35   
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class StoreSnsComment {
    private Integer scommId;

    private Integer straceId;

    private String scommContent;

    private Integer scommMemberid;

    private String scommMembername;

    private String scommMemberavatar;

    private String scommTime;

    private Integer scommState;
    /**
	 * 是否删除0:未删除;1:已删除 
	 */
	private Integer isDel;
	/**
	 * 
	 */
	private Long createdTime;
	/**
	 * 更新时间(自更新)   
	 */
	private Long updatedTime;
	/**
	 * 页面查询条件开始时间
	 */
	private String starttime;
	/**
	 * 页面查询条件结束时间
	 */
	private String endtime;

   
}