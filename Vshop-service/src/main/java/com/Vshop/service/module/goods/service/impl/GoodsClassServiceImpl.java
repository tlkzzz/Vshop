package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.entity.GoodsClass;
import com.Vshop.core.entity.GoodsStoreCls;
import com.Vshop.service.module.goods.dao.GoodsClassDao;
import com.Vshop.service.module.goods.service.GoodsClassService;

/**
 * 商品分类接口实现
 * @author lkang
 *
 */
@Service
public class GoodsClassServiceImpl implements GoodsClassService{
	
	@Resource
    private GoodsClassDao goodsClassDao;

	/**
     * 保存分类
     * @param goodsClass
     */
	public void save(GoodsClass goodsClass) {
		goodsClassDao.save(goodsClass);
	}

	/**
     * 修改分类
     * @param goodsClass
     */
	public void update(GoodsClass goodsClass) {
        goodsClassDao.update(goodsClass);
	}

	/**
     * 删除
     * @param id
     */
	public void delete(Integer id) {
		goodsClassDao.delete(id);
	}
	
	/**
     * 通过id查询分类
     * @param gcId
     * @return
     */
	public GoodsClass findById(Integer gcId) {
		return goodsClassDao.findById(gcId);
	}

	/**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
	public List<GoodsClass> findList(int parentid) {
		return goodsClassDao.findList(parentid);
	}
	
	/**
     * 查询所有的分类
     * @return
     */
	public List<GoodsClass> findAll() {
		return goodsClassDao.findAll();
	}

	/**
     * 根据不同条件查询条数，页面验证用
     * @param goodsClass
     * @return
     */
	public int findCount(GoodsClass goodsClass) {
		return goodsClassDao.findCount(goodsClass);
	}
   
	/**
     * 根据父goodsClass查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
	@Override
	public List<GoodsClass> findListbyishow(GoodsClass goodsClass) {
		return goodsClassDao.findListbyishow(goodsClass);
	}
    
	/**
     * 查询所有的分类
     * @return
     */
	@Override
	public List<GoodsClass> findAllbyisshow(GoodsClass goodsClass) {
		return goodsClassDao.findAllbyisshow(goodsClass);
	}
	
	/**
     * 商品分类api中设置一级分类下的gcLastChild
     * @param classList
     * @return
     */
    public List<GoodsClass> setApiGcLastChild(List<GoodsClass> classList){
    	for(GoodsClass goodsClass:classList){ //遍历第一级分类
    		List<GoodsClass> list = goodsClassDao.findChild(goodsClass.getGcId());
			//新建一个字符串,存储每个分类第三级分类下的第一个分类的名称
			String classStr = "";
			//判断一级分类下有没有二级分类
			if(list!=null){ 
				for(GoodsClass secondClass:list){
					//判断二级分类下有没有三级分类
					if(secondClass.getClassList()!=null){ 
						for(int i=0;i<secondClass.getClassList().size();i++){
							if(i==0){
								classStr += secondClass.getClassList().get(i).getGcName() + "  ";
							}
						}
					}
				}
			}
			goodsClass.setGcLastChild(classStr);
		}
    	return classList;
    }
    
    
    /**
     * 通过父id查询子分类
     * @param gcParentId
     * @return
     */
    public List<GoodsClass> findChild(Integer gcParentId){
    	return goodsClassDao.findChild(gcParentId);
    }

	@Override
	public List<GoodsClass> findListByStoreId(GoodsStoreCls gsc) {
		// TODO Auto-generated method stub
		return goodsClassDao.findListByStoreId(gsc);
	}

	@Override
	public List<GoodsClass> findLeafListByStoreId(GoodsStoreCls gsc) {
		// TODO Auto-generated method stub
		return goodsClassDao.findLeafListByStoreId(gsc);
	}

	@Override
	public List<GoodsClass> findByList(List<Integer> gcIdList) {
		// TODO Auto-generated method stub
		return goodsClassDao.findByList(gcIdList);
	}
}
