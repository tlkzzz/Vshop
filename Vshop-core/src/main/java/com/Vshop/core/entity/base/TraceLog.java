package com.Vshop.core.entity.base;



import lombok.Data;
import lombok.ToString;

/**
 * sns tradelog 店铺动态
 *    
 * 项目名称：Vshop-entity   
 * 类名称：TraceLog   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月7日 上午11:40:17   
 * 修改人：yanghui   
 * 修改时间：2014年11月7日 上午11:40:17   
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class TraceLog {
	/**
	 * 店铺动态id     
	 */
	private Integer straceId;
	/**
	 * 店铺id
	 */
	private Integer straceStoreid;
	/**
	 * 店铺名称      
	 */
	private String straceStorename;
	/**
	 * 店标 
	 */
	private String straceStorelogo;
	/**
	 * 动态标题 
	 */
	private String straceTitle;
	/**
	 * 发表内容    
	 */
	private String straceContent;
	/**
	 * 发表时间      
	 */
	private String straceTime;
	/**
	 * 赞数量    
	 */
	private Integer straceCool;
	/**
	 * 转播数量       
	 */
	private Integer straceSpread;
	/**
	 * 评论数量
	 */
	private Integer straceComment;
	/**
	 * 类型 1=relay,2=normal,3=new,4=coupon,5=xianshi,6=mansong,7=bundling,8=groupbuy,9=recommend,10=hotsell  
	 */
	private Integer straceType;
	/**
	 * 商品信息     
	 */
	private String straceGoodsdata;
	/**
	 * 动态状态 1正常，0屏蔽      
	 */
	private Integer straceState;
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
