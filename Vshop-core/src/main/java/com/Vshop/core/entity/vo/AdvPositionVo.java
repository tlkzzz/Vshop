/**
 * 
 */
package com.Vshop.core.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.Vshop.core.entity.base.Adv;
import com.Vshop.core.entity.base.AdvPosition;

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
public class AdvPositionVo extends AdvPosition implements Serializable {

	private static final long serialVersionUID = -4397902735660590865L;
	
	/**
	 * 广告列表
	 */
	private List<Adv> advList;
	

}
