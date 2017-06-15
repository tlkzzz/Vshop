package com.Vshop.core.entity.vo;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by rabook on 2015/3/22.
 */
@Data
@ToString
public class FaceVo implements java.io.Serializable{

    private static final long serialVersionUID = -9042760384261375877L;

    private MultipartFile[] bannerFiles;

    private MultipartFile[] recommendFiles;

    private String[] bannerUrl;

    private String[] recommendUrl;

    private String[] brandIds;
}
