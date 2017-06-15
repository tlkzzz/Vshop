package com.Vshop.front.module.store.util;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.Vshop.core.entity.base.EvaluateStore;
import com.Vshop.core.entity.vo.ImageVo;
import com.Vshop.core.entity.vo.StoreVo;

public class Util implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6891066834605206884L;

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
		if(arrSlide!=null){
			for(int i=0;i<arrSlide.length;i++){
				if(arrSlide[i]!=null){
					imageVo = new ImageVo();
					imageVo.setImageUrl(arrSlide[i]);
					imageVo.setTargetUrl(arrSlideUrl[i]);
				}
				imageVoList.add(imageVo);
			}
		}
		return imageVoList;
	}
	
	public static String getAverageCreditFormat(EvaluateStore evaluateStore){
		Double result = (evaluateStore.getSevalServicecredit()+evaluateStore.getSevalDesccredit()+evaluateStore.getSevalDeliverycredit())/3;
		NumberFormat numberFormat= NumberFormat.getNumberInstance() ; 
		numberFormat.setMaximumFractionDigits(2); 
		return numberFormat.format(result);
	}
	

}
