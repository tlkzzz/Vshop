package com.Vshop.service.module.setting.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * @author llf
 * @Package com.Vshop.service.module.setting.vo
 * @Description:
 * @date 2014/12/8 17:48
 */
@Data
@ToString
public class CityVo {

    private Integer cityId;

    private List<Integer> countyIdList;
}
