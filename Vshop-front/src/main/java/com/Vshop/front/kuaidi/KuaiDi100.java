package com.Vshop.front.kuaidi;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * 快递100接口
 * @author kviuff
 * @date 2015-07-20 16:10:00
 */
public class KuaiDi100 {
	/**
	 * 
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
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String getKuaiDiContent(String com, String nu, String show, String muti, String order) {
		String content = "";
		try {
			String urlstr = "http://api.kuaidi100.com/api?id=fe81fbbc5f12f9fb&com=";
			urlstr += com;
			urlstr += "&nu=";
			urlstr += nu;
			urlstr += "&show=";
			urlstr += show;
			urlstr += "&muti=";
			urlstr += muti;
			urlstr += "&order=";
			urlstr += order;
			
			
			URL url = new URL(urlstr);
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			if (type == null)
				type = con.getContentType();

			if (type == null || type.trim().length() == 0
					|| type.trim().indexOf("text/html") < 0)
				return "";

			if (type.indexOf("charset=") > 0)
				charSet = type.substring(type.indexOf("charset=") + 8);

			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			content = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					String newContent = new String(b, 0, numRead, charSet);
					content += newContent;
				}
			}
			urlStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		String bb = "{\"message\":\"ok\",\"status\":\"1\",\"state\":\"3\",\"data\":["
				+ "{\"time\":\"2012-07-07 13:35:14\","
				+ "\"context\":\"客户已签收\"},{\"time\":\"2012-07-07 09:10:10\","
				+ "\"context\":\"离开 [北京石景山营业厅] 派送中，递送员[温]，电话[]\"},"
				+ "{\"time\":\"2012-07-06 19:46:38\",\"context\":\"到达 [北京石景山营业厅]\"},"
				+ "{\"time\":\"2012-07-06 15:22:32\",\"context\":\"离开 [北京石景山营业厅] 派送中，递送员[温]，电话[]\"},"
				+ "{\"time\":\"2012-07-06 15:05:00\",\"context\":\"到达 [北京石景山营业厅]\"},"
				+ "{\"time\":\"2012-07-06 13:37:52\",\"context\":\"离开 [北京_同城中转站] 发往 [北京石景山营业厅]\"},"
				+ "{\"time\":\"2012-07-06 12:54:41\",\"context\":\"到达 [北京_同城中转站]\"},"
				+ "{\"time\":\"2012-07-06 11:11:03\",\"context\":\"离开 [北京运转中心驻站班组] 发往 [北京_同城中转站]\"},"
				+ "{\"time\":\"2012-07-06 10:43:21\",\"context\":\"到达 [北京运转中心驻站班组]\"},"
				+ "{\"time\":\"2012-07-05 21:18:53\",\"context\":\"离开 [福建_厦门支公司] 发往 [北京运转中心_航空]\"},"
				+ "{\"time\":\"2012-07-05 20:07:27\",\"context\":\"已取件，到达 [福建_厦门支公司]\"}]}";
		
		
		KuaiDi100 aa = new KuaiDi100();
		
		
		String cc = aa.getKuaiDiContent("yuantong", "100333209755", "2", "1", "desc");
		System.out.println(cc);
		//HashMap<String, String> data = aa.toHashMap(bb);
		//System.out.println(data);
		
		/*List<GiftDetailModel> list = new ArrayList<GiftDetailModel>();
		for (int i = 0; i < 10000; i++) {
			GiftDetailModel model = new GiftDetailModel();
			model.setWinningPro(i);
			list.add(model);
		}
		
		int cc = aa.getGameWinningLevel(list);
		System.out.println(cc);*/
	}
	
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private static HashMap<String, String> toHashMap(Object object) {
		HashMap<String, String> data = new HashMap<String, String>();
		// 将json字符串转换成jsonObject
		JSONObject jsonObject = JSONObject.fromObject(object);
		Iterator it = jsonObject.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			String key = String.valueOf(it.next());
			String value = (String) jsonObject.get(key);
			data.put(key, value);
		}
		
		
		return data;
	}
	
	
	@SuppressWarnings("unused")
	private int getGameWinningLevel(List<GiftDetailModel> list) {   
		  
	    // 中奖等级：未中奖   
	    int winningLevel = -1;   
	  
	    if (list == null || list.size() <= 0) {   
	        return winningLevel;   
	    }   
	  
	    // 中奖随机号   
	    int randomWinningNo = 0;   
	    int args[] = new int[list.size() * 2];   
	    int temp = (int) Math.round(Math.random() * 1000000000) % 1000000;   
	    int j = 0;   
	  
	    for (int i = 0; i < list.size(); i++) {   
	  
	        double tmpWinningPro = list.get(i).getWinningPro();   
	  
	        if (j == 0) {   
	            args[j] = randomWinningNo;   
	        } else {   
	            args[j] = args[j - 1] + 1;   
	        }   
	        args[j + 1] = args[j] + (int) Math.round(tmpWinningPro * 10000) - 1;   
	  
	        if (temp >= args[j] && temp <= args[j + 1]) {   
	            winningLevel = i + 1;   
	            return winningLevel;   
	        }   
	        j += 2;   
	    }   
	    return winningLevel;   
	}  
	
}
