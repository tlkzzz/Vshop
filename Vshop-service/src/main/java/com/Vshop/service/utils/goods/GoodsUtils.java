package com.Vshop.service.utils.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.Vshop.core.entity.GoodsSpec;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsAttrIndex;
import com.Vshop.core.entity.base.GoodsSpecIndex;
import com.Vshop.core.entity.vo.GoodsAttrVo;
import com.Vshop.core.entity.vo.GoodsSpecVo;
import com.Vshop.core.jackson.JsonUtils;

/**
 * 
 * @author cgl
 * @date 2015年06月29日14:50:01
 * @discription 此工具类,是用来将goods实体类中的spec,attr等字段,将其字符串序列化成指定的实体类
 */
public class GoodsUtils {

	/**
	 * 	将goods实体类中的goodsSpec字段中的字符串值,序列化成goodsSpecIndex实体类
	 * @param goodsSpec
	 * @param goodsId
	 * @param gcId
	 * @param typeId
	 * @return GoodsSpecIndex的list
	 */
	public static List<GoodsSpecIndex> goodsSpecStrToGoodsSpecIndexClass(String goodsSpec,
			Integer goodsId,
			Integer gcId,
			Integer typeId){
		if(goodsSpec == null || goodsSpec.trim().equals("")){
			return null;
		}
		//准备list
		List<GoodsSpecIndex> list = new ArrayList<GoodsSpecIndex>();
		HashMap<String, String> map = (HashMap<String, String>) JsonUtils.readJsonToMap(goodsSpec);
		//遍历map
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String> entrySpec = it.next();
			//得到key
			String spId = entrySpec.getKey();
			//在map中,嵌套了一个map
			HashMap<String, String> valueMap = (HashMap<String, String>) JsonUtils.readJsonToMap(entrySpec.getValue());
			Iterator<Entry<String, String>> valueKeyIt = valueMap.entrySet().iterator();
				//内层循环
				while(valueKeyIt.hasNext()){
					//准备实体类
					GoodsSpecIndex goodsSpecIndex = new GoodsSpecIndex();
					Entry<String, String> entry = valueKeyIt.next();
					String spValueId = entry.getKey();
					String spValueName = entry.getValue();
					//注入值
					goodsSpecIndex.setGoodsId(goodsId);
					goodsSpecIndex.setGcId(gcId);
					goodsSpecIndex.setTypeId(typeId);
					goodsSpecIndex.setSpId(Integer.parseInt(spId));
					goodsSpecIndex.setSpValueId(Integer.parseInt(spValueId));
					goodsSpecIndex.setSpValueName(spValueName);
					list.add(goodsSpecIndex);
				}
		}
		return list;
	}
	
	/**
	 * 将goods实体类中的goodsSpec字段中的字符串值进行提炼获得以逗号分隔的secValueid字符串
	 * @return String
	 */
	public static String getgoodsSpecValueId(String goodsSpec){
		if(goodsSpec == null || goodsSpec.trim().equals("")){
			return null;
		}
		Iterator<List<GoodsSpecVo>> it = goodsSpecStrToMapList(goodsSpec).values().iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			List<GoodsSpecVo> list = it.next();
			for(int i = 0; i < list.size(); i++){
				sb.append(list.get(i).getSpValueId() + ",");
			}
		}
		String str = sb.substring(0, sb.length()-1);
		return str;
	}
	
	public static void main(String[] args) {
		String str = "{\"1\":\"{\\\"58\\\":\\\"红色\\\",\\\"60\\\":\\\"蓝色\\\"}\",\"9\":\"{\\\"55\\\":\\\"L\\\",\\\"56\\\":\\\"M\\\"}\"}";
		System.out.println(goodsSpecStrToMapList(str));
	}
	
	/**
	 * 将goods实体类中的goodsSpec字段中的字符串值,序列化成map
	 * map结构:
	 * 	外层map的键为规格id(spId),值为内层map
	 * 	内层为GoodsSpecVo形的list
	 * @return Map<Integer, List<GoodsSpecVo>>
	 */
	public static Map<String, List<GoodsSpecVo>> goodsSpecStrToMapList(String goodsSpec){
		if(goodsSpec == null || goodsSpec.trim().equals("")){
			return null;
		}
		//准备返回的map(外层)
		Map<String, List<GoodsSpecVo>> returnMap = new HashMap<String, List<GoodsSpecVo>>();
		HashMap<String, String> map = (HashMap<String, String>) JsonUtils.readJsonToMap(goodsSpec);
		//遍历map
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String> entrySpec = it.next();
			//得到key
			String spId = entrySpec.getKey();
			//在map中,嵌套了一个map
			HashMap<String, String> valueMap = (HashMap<String, String>) JsonUtils.readJsonToMap(entrySpec.getValue());
			Iterator<Entry<String, String>> valueKeyIt = valueMap.entrySet().iterator();
			//准备内层的list
			List<GoodsSpecVo> list = new ArrayList<GoodsSpecVo>();
				//内层循环
				while(valueKeyIt.hasNext()){
					//准备给GoodsSpecVo实体类
					GoodsSpecVo goodsSpecVo = new GoodsSpecVo();
					Entry<String, String> entry = valueKeyIt.next();
					String spValueId = entry.getKey();
					String spValueName = entry.getValue();
					goodsSpecVo.setSpId(Integer.parseInt(spId));
					goodsSpecVo.setSpValueId(Integer.parseInt(spValueId));
					goodsSpecVo.setSpValueName(spValueName);
					//放入list
					list.add(goodsSpecVo);
				}
				//将内层map 放入外层map
				returnMap.put(spId, list);
		}
		return returnMap;
	}
	
	/**
	 * 将goods实体类中的goodsSpec字段中的字符串值,序列化成map
	 * map结构:
	 * 	外层map的键为规格名称(spName),值为内层map
	 * 	内层为GoodsSpecVo形的list
	 * @return Map<String, List<GoodsSpecVo>>
	 */
	public static Map<String, List<GoodsSpecVo>> goodsSpecStrToMapAndList(String goodsSpec, String specName){
		if(goodsSpec == null || goodsSpec.trim().equals("")){
			return null;
		}
		//准备返回的map(外层)
		Map<String, List<GoodsSpecVo>> returnMap = new HashMap<String, List<GoodsSpecVo>>();
		HashMap<String, String> map = (HashMap<String, String>) JsonUtils.readJsonToMap(goodsSpec);
		HashMap<String, String> specNameMap = (HashMap<String, String>) JsonUtils.readJsonToMap(specName);
		//遍历map
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String> entrySpec = it.next();
			//得到key
			String spId = entrySpec.getKey();
			//在map中,嵌套了一个map
			HashMap<String, String> valueMap = (HashMap<String, String>) JsonUtils.readJsonToMap(entrySpec.getValue());
			Iterator<Entry<String, String>> valueKeyIt = valueMap.entrySet().iterator();
			//准备内层的list
			List<GoodsSpecVo> list = new ArrayList<GoodsSpecVo>();
				//内层循环
				while(valueKeyIt.hasNext()){
					//准备给GoodsSpecVo实体类
					GoodsSpecVo goodsSpecVo = new GoodsSpecVo();
					Entry<String, String> entry = valueKeyIt.next();
					String spValueId = entry.getKey();
					String spValueName = entry.getValue();
					goodsSpecVo.setSpId(Integer.parseInt(spId));
					goodsSpecVo.setSpValueId(Integer.parseInt(spValueId));
					goodsSpecVo.setSpValueName(spValueName);
					//放入list
					list.add(goodsSpecVo);
				}
				String specNameStr = specNameMap.get(spId);
				//将内层map 放入外层map
				returnMap.put(specNameStr, list);
		}
		return returnMap;
	}
	
	/**
	 * 通过specGoods这个实体,以及goods实体类中的goodsSpec 属性,获得当前这个规格商品的goodsspecVo的list
	 */	
	public static List<GoodsSpecVo> getGoodsSpecVoList(GoodsSpec goodsSpec, Goods goods){
		if(goodsSpec == null || goods == null){
			return null;
		}
		if(goodsSpec.getSpecGoodsSpec() == null){
			return null;
		}
		if(goodsSpec.getSpecName() == null){
			return null;
		}
		if(goods.getGoodsSpec() == null){
			return null;
		}
		Map<String, String> colImgMap = null;
		
		if(goods.getGoodsColImg() != null && !goods.getGoodsColImg().equals("")){
			colImgMap = JsonUtils.readJsonToMap(goods.getGoodsColImg());
		}
		
		/**规格名称map*/
		HashMap<String, String> spNames = (HashMap<String, String>) JsonUtils.readJsonToMap(goodsSpec.getSpecName());
		/**规格值map*/
		HashMap<String, String> specValues = (HashMap<String, String>) JsonUtils.readJsonToMap(goodsSpec.getSpecGoodsSpec());
		
		List<GoodsSpecVo> list = new ArrayList<GoodsSpecVo>();
		
		Map<String, List<GoodsSpecVo>> map = goodsSpecStrToMapList(goods.getGoodsSpec());
		
		Iterator<Entry<String, List<GoodsSpecVo>>> its = map.entrySet().iterator();
		
		while (its.hasNext()) {
			
			Entry<String, List<GoodsSpecVo>> entry = its.next();
			
			for(GoodsSpecVo goodsSpecVo : entry.getValue()){
				
				if(spNames.containsKey(goodsSpecVo.getSpId()+"")){
					
					goodsSpecVo.setSpName(spNames.get(goodsSpecVo.getSpId()+""));
					
					if(specValues.containsKey(goodsSpecVo.getSpValueId()+"")){
						
						/**是否存在对应的图片*/
						if(colImgMap != null){
							
							goodsSpecVo.setColImg(colImgMap.get(goodsSpecVo.getSpValueId()+""));
							
						}
						
						list.add(goodsSpecVo);
					}
				}
				
			}
		}
		
		return list;
		
	}

	
	/**
     * 获取到商品的详细sku情况
     * 参数:
     * 1. specGoods实体
     * 2. goods实体
     * 返回:
     * 返回的是一个GoodsSpec实体类,在这个实体类中
     * sepcMap这个参数 键为:当前商品规格的规格名称. 值为:当前规格的规格值名称,如果为空,则说明该商品没有规格
     * colImg这个参数是,当前商品所属的规格中的颜色这个规格,的自定义图片,如果为空则说明没有自定义图片
     */
	public static GoodsSpec getSepcMapAndColImgToGoodsSpec(Goods goods, GoodsSpec goodsSpec){
		if(goodsSpec.getSpecName() != null && !goodsSpec.getSpecName().trim().equals("")){
			//准备map
			Map<String, String> map = new HashMap<String, String>();
			String sepcNameAndValueStr = goods.getGoodsSpec();
			String specValueStr = goodsSpec.getSpecGoodsSpec();
			Map<String, String> sepcNameMap = JsonUtils.readJsonToMap(goods.getSpecName());
			Map<String, String> sepcNameAndValueMap = JsonUtils.readJsonToMap(sepcNameAndValueStr);
			Map<String, String> specValueNameMap = JsonUtils.readJsonToMap(specValueStr);
			Iterator<Entry<String, String>> sepcNameAndValueEntry = sepcNameAndValueMap.entrySet().iterator();
			while (sepcNameAndValueEntry.hasNext()) {
				Entry<String, String> e = sepcNameAndValueEntry.next();
				String specId = e.getKey();
				String specValueMapStr = e.getValue();
				Map<String, String> specValueMap = JsonUtils.readJsonToMap(specValueMapStr);
				Iterator<String> specValueIdIt = specValueMap.keySet().iterator();
				while (specValueIdIt.hasNext()) {
					String specValueId = specValueIdIt.next();
					String specValueName = specValueMap.get(specValueId);
					if(specValueNameMap.containsKey(specValueId)){
						map.put(sepcNameMap.get(specId), specValueName);
					}
				}
				
			}
			goodsSpec.setSepcMap(map);
			
			//设置图片
			if(goods.getGoodsColImg() != null && !goods.getGoodsColImg().trim().equals("")){
				String goodsColImgStr = goods.getGoodsColImg();
				Map<String, String> goodsColImgMap = JsonUtils.readJsonToMap(goodsColImgStr);
				Iterator<String> colImg = goodsColImgMap.keySet().iterator();
				while (colImg.hasNext()) {
					String specValueId = colImg.next();
					if(specValueNameMap.containsKey(specValueId)){
						goodsSpec.setColImg(goodsColImgMap.get(specValueId));
					}
				}
			}
			
		}
		
		return goodsSpec;
	}

	/**
	 * 将goods实体类中的goodsSpec字段中的字符串值,序列化成map
	 * map结构:
	 * 	外层map的键为规格id(spId),值为内层map
	 * 	内层map的键为规格值id(spValueId),值为规格值名称
	 * @return Map<Integer, Map<Integer, String>>
	 */
	public static Map<Integer, Map<Integer, String>> goodsSpecStrToMap(String goodsSpec){
		if(goodsSpec == null || goodsSpec.trim().equals("")){
			return null;
		}
		//准备返回的map(外层)
		Map<Integer, Map<Integer, String>> returnMap1 = new HashMap<Integer, Map<Integer,String>>();
		HashMap<String, String> map = (HashMap<String, String>) JsonUtils.readJsonToMap(goodsSpec);
		//遍历map
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String> entrySpec = it.next();
			//得到key
			String spId = entrySpec.getKey();
			//在map中,嵌套了一个map
			HashMap<String, String> valueMap = (HashMap<String, String>) JsonUtils.readJsonToMap(entrySpec.getValue());
			Iterator<Entry<String, String>> valueKeyIt = valueMap.entrySet().iterator();
			//准备返回的map(内层)
			Map<Integer, String> returnMap2 = new HashMap<Integer, String>();
				//内层循环
				while(valueKeyIt.hasNext()){
					Entry<String, String> entry = valueKeyIt.next();
					String spValueId = entry.getKey();
					String spValueName = entry.getValue();
					returnMap2.put(Integer.parseInt(spValueId), spValueName);
				}
				//将内层map 放入外层map
				returnMap1.put(Integer.parseInt(spId), returnMap2);
		}
		return returnMap1;
	}
	
	/**
	 * 将goods实体类中的goodsColImg字段中的字符串值,序列化成map
	 * map结构:
	 * 	键为goodsValueId也就是图片对应的规格值id
	 * 	值为对应的用户自定义图片名
	 * @return Map<String, String>
	 */
	public static Map<String, String> goodsColImgStrToMap(String goodsColImg){
		if(goodsColImg == null || goodsColImg.trim().equals("")){
			return null;
		}
		//准备返回的map
		Map<String, String> returnMap = new HashMap<String, String>();
		returnMap = (HashMap<String, String>) JsonUtils.readJsonToMap(goodsColImg);
		return returnMap;
	}
	
	/**
	 * 将goodsSpec实体类中的specGoodsSpec字段中的字符串值,序列化成map
	 * map结构:
	 * 	键为goodsValueId也就是图片对应的规格值id
	 * 	值为对应的自定义规格名称
	 * @return Map<String, String>
	 */
	public static Map<String, String> specGoodsSpecToMap(String specGoodsSpec){
		return goodsColImgStrToMap(specGoodsSpec);
	}
	
	/**
	 * 将goodsSpec实体类中的specGoodsSpec字段中的字符串值进行提炼获得以逗号分隔的secValueid字符串
	 * @return String
	 */
	public static String getThisGoodsAllSpecValueId(String specGoodsSpec){
		if(specGoodsSpec == null || specGoodsSpec.trim().equals("")){
			return null;
		}
		Map<String, String> map = specGoodsSpecToMap(specGoodsSpec);
		Set<String> set = map.keySet();
		String str = "";
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			str += it.next() + ",";
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}
	
	/**
	 * 	将goods实体类中的goodsAttr字段中的字符串值,序列化成goodsAttrIndex实体类
	 * @param goodsAttr
	 * @param goodsId
	 * @param gcId
	 * @param typeId
	 * @return GoodsSpecIndex的list
	 */
	public static List<GoodsAttrIndex> goodsAttrStrToGoodsSpecIndexClass(String goodsAttr,
			Integer goodsId,
			Integer gcId,
			Integer typeId){
		if(goodsAttr == null || goodsAttr.trim().equals("")){
			return null;
		}
		//准备要返回的list
		List<GoodsAttrIndex> list = new ArrayList<GoodsAttrIndex>();
		HashMap<String, String> map = (HashMap<String, String>) JsonUtils.readJsonToMap(goodsAttr);
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			//得到key
			String attrId = it.next();
			//在map中,嵌套了一个map
			HashMap<String, String> valueMap = (HashMap<String, String>) JsonUtils.readJsonToMap(map.get(attrId));
			Iterator<Entry<String, String>> valueKeyIt = valueMap.entrySet().iterator();
				//内层循环
				while(valueKeyIt.hasNext()){
					Entry<String, String> entryAttr = valueKeyIt.next();
					if(entryAttr.getKey().equals("name")){
						
					}else{
						//准备实体类
						GoodsAttrIndex goodsAttrIndex = new GoodsAttrIndex();
						//准备实体类
						goodsAttrIndex.setGoodsId(goodsId);
						goodsAttrIndex.setGcId(gcId);
						goodsAttrIndex.setTypeId(typeId);
						goodsAttrIndex.setAttrId(Integer.parseInt(attrId));
						goodsAttrIndex.setAttrValueId(Integer.parseInt(entryAttr.getKey()));
						list.add(goodsAttrIndex);
					}
				}
		}
		return list;
	}
	
	/**
	 * 	将goods实体类中的goodsAttr字段中的字符串值,序列化成GoodsAttrVo实体类
	 * @param goodsAttr
	 * @param goodsId
	 * @param gcId
	 * @param typeId
	 * @return GoodsSpecIndex的list
	 */
	public static List<GoodsAttrVo> goodsAttrStrToGoodsAttrVoClass(String goodsAttr){
		if(null == goodsAttr || StringUtils.isEmpty(goodsAttr)){
			return null;
		}
		//准备要返回的list
		List<GoodsAttrVo> list = new ArrayList<GoodsAttrVo>();
		HashMap<String, String> map = (HashMap<String, String>) JsonUtils.readJsonToMap(goodsAttr);
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		//准备实体类
		GoodsAttrVo goodsAttrVo = null;
		while (it.hasNext()) {
			//得到key
			String attrId = it.next();
			//在map中,嵌套了一个map
			HashMap<String, String> valueMap = (HashMap<String, String>) JsonUtils.readJsonToMap(map.get(attrId));
			Iterator<Entry<String, String>> valueKeyIt = valueMap.entrySet().iterator();
			goodsAttrVo = new GoodsAttrVo();
				//内层循环
				while(valueKeyIt.hasNext()){
					//属性id
					goodsAttrVo.setAttrId(Integer.parseInt(attrId));
					Entry<String, String> entryAttr = valueKeyIt.next();
					if(entryAttr.getKey().equals("name")){
						//属性名称
						goodsAttrVo.setAttrName(entryAttr.getValue());
					}else{
						//属性值id
						goodsAttrVo.setAttrValueId(Integer.parseInt(entryAttr.getKey()));
						//属性值名称
						goodsAttrVo.setAttrValueName(entryAttr.getValue());
					}
				}
				list.add(goodsAttrVo);
		}
		return list;
	}
	
}
