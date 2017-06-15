package com.Vshop.service.module.setting.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;


/**
 * @author llf
 * @Package com.Vshop.service.module.setting.vo
 * @Description:
 * @date 2014/12/8 17:26
 */
@Data
@ToString
public class AreaVo {

    private Integer provinceId;

    private List<CityVo> cityList;

}
