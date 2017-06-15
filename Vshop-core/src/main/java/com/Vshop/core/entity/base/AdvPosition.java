/**
 * 
 */
package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * <p>Title: AdvPosition.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: Vshop.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
@Data
@ToString
public class AdvPosition implements Serializable {
	
	private static final long serialVersionUID = -442872740570454934L;
	
	/**
	 * id
	 */
	private Integer apId;
	/**
	 * 广告位置名
	 */
	private String apName;
	/**
	 * 广告位简介
	 */
	private String apIntro;
	/**
	 * 广告类别：0图片1文字2幻灯3Flash
	 */
	private Integer apClass; 
	
	/**
	 * 广告展示方式：0幻灯片1多广告展示2单广告展示
	 */
	private Integer apDisplay;
	
	/**
	 * 广告位是否启用：0不启用1启用
	 */
	private Integer isUse;
	
	/**
	 * 广告位宽度
	 */
	private Integer apWidth;
	
	/**
	 * 广告位高度
	 */
	private Integer apHeight;
	
	/**
	 * 取值关键字
	 */
	private String apKey;

}
