package com.Vshop.service.module.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.Vshop.core.entity.base.GoodsImages;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.goods.dao.GoodsImagesDao;
import com.Vshop.service.module.goods.service.GoodsImagesService;

@Service
public class GoodsImagesServiceImpl implements GoodsImagesService{
    
    @Resource
    private GoodsImagesDao goodsImagesDao;

	/**
	 * 保存图片
	 */
	public void saveGoodsImages(String data) {
		String divImg = JsonUtils.getjsonvalue(data, "divImg").toString();
		List<GoodsImages> list =  Lists.newArrayList();
		if(Strings.isNotEmpty(divImg)){
			list = readJsonList(divImg, GoodsImages.class);
		}
		if(list!=null && list.size()>0){
			for(GoodsImages goodsImages : list){//解析规格参数数据
				String goodsAllImgs = goodsImages.getGoodsAllImgs();
				String [] goodsImgs = goodsAllImgs.split(",");
				for(int i = 0 ; i<goodsImgs.length ; i++){
					goodsImages.setGoodsImage(goodsImgs[i]);
					goodsImages.setGoodsImageSort((short)0);
					goodsImages.setIsDefault((short)0);
					/**保存goodsImages*/
					goodsImagesDao.saveGoodsImages(goodsImages);
				}
			}
		}
	}


	/**
	 * 解析json属性，放到实体里面去
	 * @Title: readJsonList 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param jsondata
	 * @param @param collectionClass
	 * @param @return    设定文件 
	 * @return List<SpecVO>    返回类型 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static List<GoodsImages> readJsonList(String jsondata,Class<?> collectionClass) {
		try {
			ObjectMapper mapper = new ObjectMapper(); 
			JavaType javaType = JsonUtils.getCollectionType(ArrayList.class, collectionClass); 
			List<GoodsImages> lst =  (List<GoodsImages>)mapper.readValue(jsondata, javaType); 
			
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}
