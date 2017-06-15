package com.Vshop.core.entity.base;

import java.util.Date;

import lombok.Data;

/**
 * 品牌
 * Created by ss on 2014/10/10.
 */
@Data
public class Wxpacket extends BaseEntity{
private  String id;
private String goodsid;
private String sjzfc;//随机字符串
private String spbh;//商品编号
}
