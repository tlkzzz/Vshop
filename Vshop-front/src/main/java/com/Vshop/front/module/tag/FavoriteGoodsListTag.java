package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.Favorites;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.front.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.cart.service.FavoritesService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
 * 我的课程收藏
 * 
 * @author gyh
 * @version 2015-07-16 13:30:00
 */
@Component
public class FavoriteGoodsListTag extends BaseFreeMarkerTag {

	@Resource
	private FavoritesService favoritesService;

	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		Favorites favorites = new Favorites();
		//需要返回数据的类型 TagsDataType.java
	    String flags = ParamsUtils.getString(params.get("flags"));
		//需要返回数据的类型 TagsDataType.java
		String tagType = ParamsUtils.getString(params.get("tagDataType"));
		// 页码
		int pageNo = ParamsUtils.getInt(params.get("pageNo"));
		// 每页数量
		int pageSize = ParamsUtils.getInt(params.get("pageSize"));
		// 按照时间升序、降序
	    int order = ParamsUtils.getInt(params.get("order"));
		if (SecurityUtils.getSubject().isAuthenticated()) {
			if(CacheUtils.getCacheUser().getMember()!=null&&CacheUtils.getCacheUser().getMember().getMemberId()!=null){
				favorites.setMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
			}
		}
		Pager pager = new Pager();
		if (pageSize != 0) {
			pager.setPageSize(pageSize);
		}
		if(flags.equals("goods")){
			favorites.setFavType("goods");
		}else if(flags.equals("store")){
			favorites.setFavType("store");
		}
		pager.setCondition(favorites);
		//查分页的list
		if(TagsDataType.PAGE_LIST.equals(tagType)){
			// 排序
			favorites.setOrderBy(order);
			if (pageNo != 0) {
				pager.setPageNo(pageNo);
			}
			// 查找list
			List<Favorites> storeorgoodsList=null;
			if(flags.equals("goods")){
				storeorgoodsList= favoritesService.findFavoriteGoodsList(pager);
			}else if(flags.equals("store")){
				storeorgoodsList=favoritesService.findFavoriteStoreList(pager);
				for(Favorites favo:storeorgoodsList){
					if(favo.getFavTime()!=null&&!"".equals(favo.getFavTime())){
						favo.setFavTimestr(DateUtils.getTimestampByLong(favo.getFavTime()));
					}
				}
			}
			return storeorgoodsList;
		}else if(TagsDataType.RECORD_COUNT.equals(tagType)){//查分页的总条数
			//pager.setCondition(favorites);
			Integer totalCount = null;
			if(flags.equals("goods")){
				totalCount= favoritesService.FavoriteGoodsCount(favorites);
			}else if(flags.equals("store")){
				totalCount=favoritesService.FavoriteStoreCount(favorites);
			}
			return totalCount;
		}
		return null;
	}

}
