package com.Vshop.service.module.store.vo;

import lombok.Data;
import lombok.ToString;

/**
 * store setting 店铺二级域名设置前台VO类
 *    
 * 项目名称：Vshop-entity   
 * 类名称：Classs   
 * 类描述：   
 * 创建人：weiyue   
 * 创建时间：2014年11月12日 下午09:10:00   
 * 修改人：weiyue   
 * 修改时间：2014年11月12日 下午09:10:00   
 * 修改备注：   
 * @version    
 *
 */
@Data
@ToString
public class SettingVo {

	/**
	 * 是否启用二级域名
	 */
	private String enabledSubdomain;

	/**
	 * 是否可以修改二级域名
	 */
	private String subdomainEdit;
	
	/**
	 * 长度限制 如"3-12"，代表注册的域名长度限制在3到12个字符之间
	 */
	private String subdomainLength;

	/**
	 * 保留的二级域名
	 */
	private String subdomainReserved;
	
	/**
	 * 修改次数
	 */
	private String subdomainTimes;
	
}
