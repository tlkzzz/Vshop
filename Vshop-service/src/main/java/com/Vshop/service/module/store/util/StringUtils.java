package com.Vshop.service.module.store.util;

import java.util.ArrayList;
import java.util.List;

import com.Vshop.core.entity.vo.ImageVo;
import com.Vshop.core.entity.vo.StoreVo;

public class StringUtils {
	
	public static List<ImageVo> getImageList(StoreVo storeVo){
		String slide = storeVo.getStoreSlide();
		String slideUrl = storeVo.getStoreSlideUrl();
		String arrSlide[]=null;
		String arrSlideUrl[]=null;
		if(slide!=null && !"".equals(slide)){
			arrSlide = slide.split(",");
		}
		if(slideUrl!=null && !"".equals(slideUrl)){
			arrSlideUrl = slideUrl.split(",");
		}
		List<ImageVo> imageVoList = new ArrayList<ImageVo>();
		ImageVo imageVo = null;
		for(int i=0;i<arrSlide.length;i++){
			if(arrSlide[i]!=null){
				imageVo = new ImageVo();
				imageVo.setImageUrl(arrSlide[i]);
				imageVo.setTargetUrl(arrSlideUrl[i]);
			}
			imageVoList.add(imageVo);
		}
		return imageVoList;
	}
	

}
