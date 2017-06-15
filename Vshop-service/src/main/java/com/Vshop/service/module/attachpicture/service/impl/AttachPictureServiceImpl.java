package com.Vshop.service.module.attachpicture.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.AttachPicture;
import com.Vshop.service.module.attachpicture.dao.AttachPictureDao;
import com.Vshop.service.module.attachpicture.service.AttachPictureService;
import com.Vshop.service.utils.page.Pager;

@Service
public class AttachPictureServiceImpl implements AttachPictureService{
	
	@Resource
	private AttachPictureDao attachPictureDao;
	
	/**
	 * 保存图片库图片属性
	 * @param attachPicture
	 */
	@Override
	public void save(AttachPicture attachPicture) {
		attachPictureDao.save(attachPicture);
	}
	
	/**
	 * 修改图片库图片属性
	 * @param attachPicture
	 */
	@Override
	public void update(AttachPicture attachPicture) {
		attachPictureDao.update(attachPicture);
	}
	
	/**
	 * 根据id删除图片空间中图片数据
	 */
	@Override
	public void delete(Integer id) {
		attachPictureDao.delete(id);
	}
	
	/**
	 * 查询分页条数(包括查询条件)
	 * @param pager
	 * @return
	 */
	@Override
	public int findCount(Pager pager) {
		return attachPictureDao.findCount(pager);
	}
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@Override
	public List<AttachPicture> findPageList(Pager pager) {
		return attachPictureDao.findPageList(pager);
	}
	
	/**
	 * 上传图片保存到图片空间
	 * @param map com.Vshop.core.common.FileUtils.fileUpload方法返回值
	 * @param storeId 店铺id,没有为null
	 */
	@Override
	public void saveUpload(Map<String,Object> map,Integer storeId) {
		AttachPicture attachPicture = new AttachPicture();
		attachPicture.setLocalName((String) map.get("filename"));
		attachPicture.setLocalPath((String) map.get("result"));	
		attachPicture.setRealName((String) map.get("originalfilename"));
		if(storeId!=null){
			attachPicture.setStoreid(storeId);
		}
		attachPictureDao.save(attachPicture);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@Override
	public AttachPicture findById(Integer id) {
		return attachPictureDao.findById(id);
	}
	
	/**
	 * 查询所有不含分页
	 * @return
	 */
	public List<AttachPicture> findList(){
		return attachPictureDao.findList();
	}
	
	/**
	 * 根据店铺id查询
	 * @return
	 */
	public List<AttachPicture> findListByStoreID(Integer storeid){
		return attachPictureDao.findListByStoreID(storeid);
	}
}
