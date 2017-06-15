package com.Vshop.core.entity;



import lombok.Data;
import lombok.ToString;



/**
 * SiteSet admin系统设置的站点设置
 * 
 * @项目名称：vixuan-entity
 * @类名称：SiteSet
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@Data
@ToString
public class Site {
	private Integer siteId;
	//网站名称
	private String siteName;
	//网站标题
	private String siteTitle;
	//关键字
	private String siteKey;
	//底部信息
	private String siteDbxx;
	//描述
	private String siteDiscription;
	//退货时间
	private String returnTime;
	//网站Logo
	private String siteLogo1;
	//网站admin登陆页面logo
	private String siteLogo2;
	//网站admin首页logo
	private String siteLogo3;
	private String siteLogo4;
	private String siteLogo5;
	private String siteLogo6;

}
