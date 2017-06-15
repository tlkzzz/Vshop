package com.Vshop.core.entity.vo;

import lombok.Data;
import lombok.ToString;

/**
 * Created by rabook on 2015/3/7.
 */
@Data
@ToString
public class PreVo implements java.io.Serializable{

    private static final long serialVersionUID = 392690847241237358L;

    private String preName;

    private Integer preTool;

    private String preToolNumber;
}
