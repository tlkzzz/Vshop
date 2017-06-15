package com.Vshop.front.module.tag;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.Vshop.core.freemarker.BaseFreeMarkerTag;
import com.Vshop.front.kuaidi.KuaiDi100;
import com.Vshop.front.module.tag.utils.ParamsUtils;

import freemarker.template.TemplateModelException;

/**
 * 快递100查询标签
 * @author kviuff
 *
 */
@Component
public class KuaiDiTag extends BaseFreeMarkerTag {

	/**
	 * 快递100查询标签
	 * @param com String 是 	要查询的快递公司代码，不支持中文，对应的公司代码见
	 				《API URL 所支持的快递公司及参数说明》和《支持的国际类快递及参数说明》。
	 	 			如果找不到您所需的公司，请发邮件至 kuaidi@kingdee.com 咨询（大小写不敏感）
	 * @param nu String 是 	要查询的快递单号，请勿带特殊符号，不支持中文（大小写不敏感）
	 * @param show  String 	是 	返回类型：
					0：返回json字符串，
					1：返回xml对象，
					2：返回html对象，
					3：返回text文本。
					如果不填，默认返回json字符串。
	 * @param muti 	String 	是 	返回信息数量：
	 				1:返回多行完整的信息，
					0:只返回一行信息。
					不填默认返回多行。
	 * @param order String 	是 	排序：
					desc：按时间由新到旧排列，
					asc：按时间由旧到新排列。
					不填默认返回倒序（大小写不敏感）
	 */
	@SuppressWarnings("rawtypes")
	protected Object exec(Map params) throws TemplateModelException {
		String com = ParamsUtils.getString(params.get("com"));
		String nu = ParamsUtils.getString(params.get("nu"));
		String show = ParamsUtils.getString(params.get("show"));
		String muti = ParamsUtils.getString(params.get("muti"));
		String order = ParamsUtils.getString(params.get("order"));
		KuaiDi100 kuaidi = new KuaiDi100();
		String content = kuaidi.getKuaiDiContent(com, nu, show, muti, order);
		
		JSONObject jsonObject = JSONObject.fromObject(content);
		
		return jsonObject;
	}
	
}
