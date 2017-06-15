package com.Vshop.core.entity;


import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.BaseEntity;
import com.Vshop.core.entity.base.TransportExtend;

@Data
@ToString
public class Transport extends BaseEntity{
	private Integer id;
	private String title;
	private Integer sendTplId;
	private Integer storeId;

	/**
	 * 0:未删除;1.已删除
	 */
	private int isDel;
	
	/**
	 * 是否是默认的运费模板 0:否 1:是
	 */
	private Integer isDefault;
	
	private List<TransportExtend> transportExtendList;
}
