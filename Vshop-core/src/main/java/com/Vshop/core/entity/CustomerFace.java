package com.Vshop.core.entity;

import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.vo.AfterVo;
import com.Vshop.core.entity.vo.PreVo;

/**
 * Created by rabook on 2015/3/7.
 */
@Data
@ToString
public class CustomerFace implements java.io.Serializable{

    private static final long serialVersionUID = 2306369690180903940L;

    private List<AfterVo> afterList;

    private List<PreVo> preList;

    private String storeWorkingtime;
}
