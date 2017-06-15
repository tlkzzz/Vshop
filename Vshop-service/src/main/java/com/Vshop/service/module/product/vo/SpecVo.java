package com.Vshop.service.module.product.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.SpecValue;

@Data
@ToString
public class SpecVo {
	private Integer spId;
	private String spName;
	private List<SpecValue> specValueList;
}
