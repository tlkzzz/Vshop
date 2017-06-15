package com.Vshop.front.module.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Vshop.core.entity.base.Address;
import com.Vshop.service.module.cart.service.AddressService;

/**
 * 收货地址api
 * @author kviuff
 * @date 2015-07-20
 */
@Controller
@RequestMapping("/address/api")
public class AddressApi extends BaseApi {
	
	@Resource
	private AddressService addressService;
	
	/**
	 * 根据会员id获取收货地址列表
	 * @param memberId
	 * @return
	 */
	@RequestMapping("addressList")
	@ResponseBody
	public JSONObject userAddressList(
			@RequestParam(value = "memberId") Integer memberId){
		jsonObj = new JSONObject();
		try {
			List<Address> addressList = new ArrayList<Address>();
			addressList = addressService.queryAddreassMemberId(memberId);
			
			jsonObj.put("result", 1);
	        jsonObj.put("data", addressList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", 0);
	        jsonObj.put("msg", "服务器异常");
	        jsonObj.put("data", "无收货地址");
		}
		
		return jsonObj;
	}
	
	/**
	 * 根据地址id获取地址详细
	 * @param memberId
	 * @return
	 */
	@RequestMapping("addressDetail")
	@ResponseBody
	public JSONObject getAddressByAddressId(
			@RequestParam(value = "addressId") Integer addressId
			){
		jsonObj = new JSONObject();
		try {
			Address address = new Address();
			address = addressService.queryById(addressId);
			jsonObj.put("result", 1);
	        jsonObj.put("data", address);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", 0);
	        jsonObj.put("msg", "服务器异常");
	        jsonObj.put("data", "无收货地址");
		}
		
		return jsonObj;
	}
	
	/**
	 * 保存收货地址
	 * @param memberId
	 * @return
	 */
	@RequestMapping("saveAddress")
	@ResponseBody
	public JSONObject saveAddress(
			@RequestParam(value = "memberId") Integer memberId,
			@RequestParam(value = "trueName") String trueName,
			@RequestParam(value = "mobPhone") String mobPhone,
			@RequestParam(value = "telPhone",required=false,defaultValue="") String telPhone,
			@RequestParam(value = "areaId") Integer areaId,
			@RequestParam(value = "cityId") Integer cityId,
			@RequestParam(value = "areaInfo") String areaInfo,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "provinceId") Integer provinceId,
			@RequestParam(value = "zipCode") Integer zipCode,
			@RequestParam(value = "addressId",required=false,defaultValue="") String addressId
			){
		jsonObj = new JSONObject();
		try {
			Address addressbean = new Address();
			addressbean.setMemberId(memberId);
			addressbean.setTrueName(trueName);
			addressbean.setAddress(address);
			addressbean.setProvinceId(provinceId);
			addressbean.setCityId(cityId);
			addressbean.setAreaId(areaId);
			addressbean.setMobPhone(mobPhone);
			if(StringUtils.isNoneBlank(telPhone)){
				addressbean.setTelPhone(telPhone);
			}
			addressbean.setAreaInfo(areaInfo);
			addressbean.setZipCode(zipCode);
			if(StringUtils.isNotBlank(addressId)){
				addressbean.setAddressId(Integer.valueOf(addressId));
				addressService.updateAddress(addressbean);
			}else{
				addressbean.setIsDefault("0"); //设置当前为非默认
				addressService.saveAddress(addressbean);
			}
			
			jsonObj.put("result", 1);
	        jsonObj.put("msg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", 0);
	        jsonObj.put("msg", "保存失败");
		}
		
		return jsonObj;
	}
	
	/**
	 * 根据地址id删除地址
	 * @param memberId
	 * @return
	 */
	@RequestMapping("delAddress")
	@ResponseBody
	public JSONObject deleteAddressByAddressId(
			@RequestParam(value = "addressId") String addressId
			){
		jsonObj = new JSONObject();
		try {
			addressService.deleteAddress(addressId);
			jsonObj.put("result", 1);
	        jsonObj.put("msg", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", 0);
	        jsonObj.put("msg", "删除失败");
		}
		
		return jsonObj;
	}
	
	/**
	 * 根据收货地址id和用户id修改默认收货地址
	 * @param memberId
	 * @return
	 */
	@RequestMapping("updateAddressDef")
	@ResponseBody
	public JSONObject updateAddressDef(
			@RequestParam(value = "addressId") String addressId,
			@RequestParam(value = "memberId") String memberId){
		jsonObj = new JSONObject();
		try {
			addressService.updateDef(addressId, memberId);
			jsonObj.put("result", 1);
			jsonObj.put("msg", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("result", 0);
	        jsonObj.put("msg", "修改失败");
		}
		
		return jsonObj;
	}
	
}
