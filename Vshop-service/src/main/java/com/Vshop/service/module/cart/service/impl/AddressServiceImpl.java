package com.Vshop.service.module.cart.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.Vshop.core.entity.base.Address;
import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.module.cart.dao.AddressDao;
import com.Vshop.service.module.cart.service.AddressService;
import com.Vshop.service.utils.page.Pager;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;

    public List<Address> queryAddreassMemberId(Integer memberId) {
        return addressDao.queryAddreassMemberId(memberId);
    }


    /**
     * 保存收货地址
     */
    public Map<String,Object> saveAddress(String jsondata,Integer memberId) {

        Map<String,Object> map = Maps.newHashMap();
        try {
        Address address = new Address();
            JSONObject jsonObj = new JSONObject(jsondata);
            String trueName = jsonObj.getString("trueName");
            int provinceId =  Integer.parseInt(jsonObj.getString("provinceId"));
            int cityId =  Integer.parseInt(jsonObj.getString("cityId"));
            int areaId =  Integer.parseInt(jsonObj.getString("areaId"));
            String mobPhone = jsonObj.getString("mobPhone");
//            String telPhone = jsonObj.getString("telPhone");
            String areaInfo = jsonObj.getString("areaInfo");
            String addresss = jsonObj.getString("address");
            address.setTrueName(trueName);
            address.setProvinceId(provinceId);
            address.setCityId(cityId);
            address.setAreaId(areaId);
            address.setMobPhone(mobPhone);
//            address.setTelPhone(telPhone);
            address.setAreaInfo(areaInfo);
            address.setAddress(addresss);
//           String addressId =  jsonObj.getString("addressId");
//            if(addressId !=null){
//            	address.setAddressId(Integer.parseInt(addressId));
//            }
//        	Address address = JsonUtils.fromJson(jsondata, Address.class);
            if (address.getAddressId() != null) {
                addressDao.updateAddress(address);
                map.put("success",true);
            } else {
                address.setIsDefault("0");
                address.setCityId(address.getCityId());
                address.setAreaId(address.getAreaId());
                address.setProvinceId(address.getProvinceId());
                address.setMemberId(memberId);
                addressDao.saveAddress(address);
                map.put("success",true);
                map.put("data",address);
            }

        } catch (Exception e) {
            log.error("保存收货地址失败" + e.getMessage());
            map.put("success",false);
        }
        return map;
    }

    /**
     * 保存收货地址
     */
    public int updateAddress(String jsondata) {
        int result = 0;
        try {
        	   Address address = new Address();
               JSONObject jsonObj = new JSONObject(jsondata);
               int addressId = Integer.parseInt( jsonObj.getString("addressId"));
               String trueName = jsonObj.getString("trueName");
               int provinceId =  Integer.parseInt(jsonObj.getString("provinceId"));
               int cityId =  Integer.parseInt(jsonObj.getString("cityId"));
               int areaId =  Integer.parseInt(jsonObj.getString("areaId"));
               String mobPhone = jsonObj.getString("mobPhone");
//               String telPhone = jsonObj.getString("telPhone");
               String areaInfo = jsonObj.getString("areaInfo");
               String addresss = jsonObj.getString("address");
               address.setAddressId(addressId);
               address.setTrueName(trueName);
               address.setProvinceId(provinceId);
               address.setCityId(cityId);
               address.setAreaId(areaId);
               address.setMobPhone(mobPhone);
//               address.setTelPhone(telPhone);
               address.setAreaInfo(areaInfo);
               address.setAddress(addresss);
          
//            Address address = JsonUtils.fromJson(jsondata, Address.class);
            if (address.getAddressId() != null) {
                addressDao.updateAddress(address);
                result = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存收货地址失败" + e.getMessage());
        }
        return result;
    }


    @Override
    public void deleteAddress(String addressId) {
        addressDao.deleteAddress(addressId);
    }


    @Override
    public Address queryById(Integer addressId) {
        // TODO Auto-generated method stub
        return addressDao.queryById(addressId);
    }


    public int updateDef(String addressId, String memberId) {
        int result = 0;
        if (addressId != null) {
            Address address = new Address();
            address.setMemberId(Integer.valueOf(memberId));
            address.setIsDefault("0");
            addressDao.updateAddress(address);
            address.setMemberId(null);
            address.setAddressId(Integer.valueOf(addressId));
            address.setIsDefault("1");
            addressDao.updateAddress(address);
            result = 1;
        }
        return result;
    }


    @Override
    public int countfindAll(Address address) {
        return addressDao.countfindAll(address);
    }

    @Override
    public List<Address> findList(Pager pager) {
        List<Address> listAddress=addressDao.findList(pager);
        return listAddress;
    }
    
    /**
	 * 保存收货地址
	 * @param address
	 */
	public void saveAddress(Address address){
		addressDao.saveAddress(address);
	}
	
	/**
	 * 修改收货地址
	 * @param address
	 */
	public void updateAddress(Address address){
		addressDao.updateAddress(address);
	}

}
