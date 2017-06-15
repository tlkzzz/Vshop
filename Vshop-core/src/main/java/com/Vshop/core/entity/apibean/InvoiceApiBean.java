package com.Vshop.core.entity.apibean;

import lombok.Data;
import lombok.ToString;

/**
 * 发票接口实体
 * @author liukai
 */
@Data
@ToString
public class InvoiceApiBean {
	
	/**
	 * 发票索引id
	 */
	private Integer invId;
	
	/**
	 * 发票抬头[普通发票]
	 */
	private String invTitle;
	
	/**
	 * 会员ID
	 */
	private Integer memberId;
	
}
