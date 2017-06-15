package com.Vshop.front.module.tag;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.core.entity.base.EvaluateGoods;
import com.Vshop.front.module.tag.utils.ParamsUtils;
import com.Vshop.service.module.trade.service.EvaluateGoodsService;
import com.Vshop.service.utils.page.Pager;

import freemarker.template.TemplateModelException;

/**
 * 课程评论列表
 * @author cgl
 * 添加时间：2015年08月10日14:27:55
 */
@Component
public class GoodsEvalutebyIdTag extends BaseFreeMarkerTag {
	 @Resource
	 private EvaluateGoodsService evaluateGoodsService;
		/**
		 * 课程评论列表
		 * @param MemberId 会员id
		 * @param StoreId  学院id
		 */
	 @SuppressWarnings("rawtypes")
		protected Object exec(Map params) throws TemplateModelException {
			EvaluateGoods evaluateGoods=new EvaluateGoods();
			// 需要返回数据的类型 TagsDataType.java tagDataType等于5表示总条数 等于2表示取得分页内容
			String tagType = ParamsUtils.getString(params.get("tagDataType"));
			// 页码
			int pageNo = ParamsUtils.getInt(params.get("pageno"));
			// 每页数量
			int pageSize = ParamsUtils.getInt(params.get("pagesize"));
			// 学院id
			Integer storeId = ParamsUtils.getInt(params.get("storeid"));
			// 学院MemberId
			Integer MemberId = ParamsUtils.getInt(params.get("MemberId"));
			//商家自己登陆
			if(storeId != 0&&storeId!=null){
				evaluateGoods.setGevalStoreId(storeId);
			}else{
			//用户自己登陆
				if(MemberId!=0&&MemberId!=null){
					evaluateGoods.setGevalFrommemberid(MemberId);
				}
			}
			// 准备分页pager
			Pager pager = new Pager();
			if (pageSize != 0) {
				pager.setPageSize(pageSize);
			}
			//查分页的list
			if(TagsDataType.PAGE_LIST.equals(tagType)){
			if (pageNo != 0) {
				pager.setPageNo(pageNo);
			}
			//查找list
			pager.setCondition(evaluateGoods);
		    List<EvaluateGoods> evaluateGoodsList = evaluateGoodsService.findPageList(pager);
			    return evaluateGoodsList;
			}else if(TagsDataType.RECORD_COUNT.equals(tagType)){
				//查分页的总条数
				int evaluateGoodsCount = evaluateGoodsService.findCount(evaluateGoods);
				return evaluateGoodsCount;
			}
			return null;
		}

}
