package com.Vshop.service.module.store.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author llf
 * @Package com.Vshop.service.module.store.vo
 * @Description:
 * @date 2014/12/11 11:20
 */
@Data
public class VerifyVo implements Serializable{

    private static final long serialVersionUID = -2720667686365264297L;

    private List<String> className;

    private String rate;
}
