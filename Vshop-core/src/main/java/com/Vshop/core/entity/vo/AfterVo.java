package com.Vshop.core.entity.vo;

import lombok.Data;
import lombok.ToString;

/**
 * Created by rabook on 2015/3/7.
 */
@Data
@ToString
public class AfterVo implements java.io.Serializable{

    private static final long serialVersionUID = 1891712987132955676L;

    private String afterName;

    private Integer afterTool;

    private String afterToolNumber;
}
