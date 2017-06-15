package com.Vshop.core.entity.base;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StoreSearch {
	private Integer storeId;//店铺id
	private String storeName;//店铺名称
	private Integer registeredCapital = 0;//注册资金
	private Integer storeCredit = 0;//信用等级
	private String address = "未填写";//地址
	private String storeLabel = "/upload/web/noslide.png";//店铺logo
}
