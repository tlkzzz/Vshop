package com.Vshop.core.entity.base;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @author llf
 * @Package com.Vshop.entity
 * @Description:
 * @date 2014/12/16 14:15
 */
@Data
@ToString
public class WebCode extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6290869807280612342L;

    private Integer codeId;

    private Integer webId;

    private String codeType;

    private String varName;

    private String showName;

    private String codeInfo;

    private Integer isShow;

    private Integer sort;
}
