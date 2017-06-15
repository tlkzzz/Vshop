package com.Vshop.union.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.cps.ServicePromotionGetcodeRequest;
import com.jd.open.api.sdk.request.cps.ServicePromotionGoodsInfoRequest;
import com.jd.open.api.sdk.response.cps.ServicePromotionGetcodeResponse;
import com.jd.open.api.sdk.response.cps.ServicePromotionGoodsInfoResponse;
import com.Vshop.union.entity.APIResult;
import com.Vshop.union.entity.UnionGoods;
import com.Vshop.union.service.UnionJDService;
import com.Vshop.union.utils.UnionConstants;

/**
 * 京东联盟serviceimpl
 * 
 * @author liuzhen
 * @version 2015-10-11
 */
@Slf4j
@Service
public class UnionJDServiceImpl implements UnionJDService {

	/**
	 * 获取token
	 * 
	 * @return
	 */
	@Override
	public APIResult getAccessToken() {
		return getToken(UnionConstants.JD_GETACCESSTOKEN_URL
				+ UnionConstants.JD_CODE);
	}

	/**
	 * 刷新token
	 * 
	 * @return
	 */
	@Override
	public APIResult getRefreshAccessToken() {
		return getToken(UnionConstants.JD_REFRESH_TOKEN_URL
				+ UnionConstants.JD_REFRESH_TOKEN);
	}

	/**
	 * 获取单条推广商品链接
	 * 
	 * @param materialId
	 * @param subUnionId
	 * @return
	 */
	@Override
	public APIResult getOnePromotionCode(String materialId, String subUnionId) {

		JdClient client = new DefaultJdClient(
				UnionConstants.JD_GOODS_PROMOTION_URL,
				UnionConstants.jd_ACCESSTOKEN, UnionConstants.JD_CLIENT_ID,
				UnionConstants.JD_APPSECRET);

		ServicePromotionGetcodeRequest request = new ServicePromotionGetcodeRequest();

		request.setPromotionType(7);// 推广类型 1：商品推广，2：店铺推广，3：专柜推广，4：频道推广，7：自定义推广
									// 系统目前暂时只支持自定义推广。
		request.setMaterialId(materialId);// 物料ID 1、推广类型是商品推广，推广物料ID对应着”skuid”
											// 2、推广类型为店铺推广，推广物料ID对应着”shopID”
											// 3、推广类型为频道页推广，推广物料ID对应着”频道页类目ID”
											// 4、推广类型为自定义推广时，则该物料ID对应着”着陆页URL”
											// 5、推广类型为专柜推广时，则该物料ID对应着“类目ID_类目ID_类目ID…”
		request.setUnionId(UnionConstants.JD_UNIONID);// 联盟ID(京东客ID)
		request.setSubUnionId(subUnionId);// 子联盟Id（不能超过16个字符）
		request.setSiteSize("");// 推广位尺寸，非必填
		request.setSiteId("");// 推广位ID，非必填
		request.setChannel("PC");// 推广渠道 PC：pc推广，WL：无线推广
		request.setWebId(UnionConstants.JD_WEBID);
		request.setExtendId("村博士");
		request.setExt1("村博士");

		try {
			ServicePromotionGetcodeResponse response = client.execute(request);

			log.debug(response.getMsg() + "\n" + response.getUrl());

			if ("0".equals(response.getCode())) {

				JSONObject result = new JSONObject(response.getQueryjsResult());

				String resultCode = result.optString("resultCode", "");
				if ("0".equals(resultCode)) {
					String url = result.optString("url", "");
					return new APIResult(resultCode, url);
				} else {
					String resultMessage = result
							.optString("resultMessage", "");
					return new APIResult(resultCode, "错误状态码：" + resultCode
							+ "\t错误描述：" + resultMessage);
				}

			} else {
				return new APIResult(response.getCode(), "错误状态码："
						+ response.getCode() + "\t错误描述：" + response.getZhDesc()
						+ "  " + response.getEnDesc());
			}

		} catch (JdException e) {
			e.printStackTrace();
			return new APIResult("-1", "获取单条推广信息失败，原因：" + e.getMessage());
		}
	}

	/**
	 * 获取京东联盟推广商品信息
	 * 
	 * @param goods
	 * @return
	 */
	@Override
	public APIResult setgoodsinfo(UnionGoods goods) {
		JdClient client = new DefaultJdClient(
				UnionConstants.JD_GOODS_PROMOTION_URL,
				UnionConstants.jd_ACCESSTOKEN, UnionConstants.JD_CLIENT_ID,
				UnionConstants.JD_APPSECRET);

		ServicePromotionGoodsInfoRequest request = new ServicePromotionGoodsInfoRequest();
		request.setSkuIds(goods.getSkuId() + "");

		try {
			ServicePromotionGoodsInfoResponse response = client
					.execute(request);

			log.debug(response.getMsg() + "\n" + response.getUrl());

			if ("0".equals(response.getCode())) {

				APIResult refreshResult = getRefreshAccessToken();
				if (!"0".equals(refreshResult.getCode())) {
					return new APIResult(refreshResult.getCode(), "刷新token失败，"
							+ refreshResult.getMsg());
				}

				JSONObject result = new JSONObject(
						response.getGetpromotioninfoResult());

				result.optBoolean("sucessed", false);
				result.optString("message", "");
				JSONArray goodsList = result.optJSONArray("result");
				if (goodsList != null && goodsList.length() > 0) {
					for (int i = 0; i < goodsList.length(); i++) {
						JSONObject jgoods = (JSONObject) goodsList.get(i);

						goods.setCommisionRatioPc(jgoods.optDouble(
								"commisionRatioPc", 0.00));
						goods.setCommisionRatioWl(jgoods.optDouble(
								"commisionRatioWl", 0.00));
						goods.setEndDate(jgoods.optLong("endDate", 0));
						goods.setGoodsName(jgoods.optString("goodsName", ""));
						goods.setMaterialUrl(jgoods
								.optString("materialUrl", ""));
						goods.setShopId(jgoods.optInt("shopId", 0));
						goods.setSkuId(jgoods.optInt("skuId", 0));
						goods.setStartDate(jgoods.optLong("startDate", 0));
						goods.setUnitPrice(jgoods.optDouble("unitPrice", 0.0));
						goods.setSource(1);

						log.debug(goods.toString());
					}
				}

				return new APIResult(response.getCode(),
						response.getGetpromotioninfoResult());
			} else {
				return new APIResult(response.getCode(), "错误状态码："
						+ response.getCode() + "\t错误描述：" + response.getZhDesc()
						+ "  " + response.getEnDesc());
			}
		} catch (JdException e) {
			e.printStackTrace();
			return new APIResult("-1", "获取商品推广信息失败，原因：" + e.getMessage());
		}
	}

	/**
	 * 获取token
	 * 
	 * @param url
	 * @return
	 */
	private APIResult getToken(String url) {
		log.debug(url);
		try {
			URL uri = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestMethod("POST");
			int httpcode = conn.getResponseCode();
			log.debug(httpcode + "");

			if (httpcode == 200) {
				InputStream is = conn.getInputStream();

				String jsonStr = inputStream2String(is);

				JSONObject token = new JSONObject(jsonStr);
				log.debug(token.toString());

				int code = token.optInt("code");// 状态码

				if (code == 0) {
					String access_token = token.optString("access_token");
					UnionConstants.jd_ACCESSTOKEN = access_token;

					long expires_in = token.optLong("expires_in");
					UnionConstants.JD_EXPIRES_IN = expires_in;

					String refresh_token = token.optString("refresh_token");
					UnionConstants.JD_REFRESH_TOKEN = refresh_token;

					long time = token.optLong("time");
					UnionConstants.JD_TIME = time;

					// 下面三个暂时用不到
					String token_type = token.optString("token_type");
					int uid = token.optInt("uid");
					String user_nick = token.optString("user_nick");

					return new APIResult(code + "", token.toString());
				} else {
					// 错误描述
					String error_description = token
							.optString("error_description");
					return new APIResult(code + "", error_description);
				}

			} else {
				return new APIResult(httpcode + "", "接口请求失败");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new APIResult("-1", "系统异常，原因：" + e.getMessage());
		} catch (ProtocolException e) {
			e.printStackTrace();
			return new APIResult("-1", "系统异常，原因：" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return new APIResult("-1", "系统异常，原因：" + e.getMessage());
		}
	}

	private String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

}
