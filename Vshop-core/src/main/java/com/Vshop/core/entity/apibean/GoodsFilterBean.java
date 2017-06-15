package com.Vshop.core.entity.apibean;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GoodsFilterBean {
	/**
	 * 搜索结果所对应的规格
	 */
	private List<SpecApiBean> specList;
	
}
