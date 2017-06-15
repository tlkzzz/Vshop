package com.Vshop.core.entity.vo;

import lombok.Data;
import lombok.ToString;

/**
 * Created by rabook on 2015/3/22.
 */
@Data
@ToString
public class BannerVo implements java.io.Serializable{

    private static final long serialVersionUID = -6236555095010926620L;
    /**
     * 图片路径
     */
    private String imageUrl;

    /**
     * 图片链接
     */
    private String linkUrl;
}
