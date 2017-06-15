package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.Vshop.core.common.Collections3;
import com.Vshop.core.entity.GoodsAttribute;
import com.Vshop.core.entity.base.GoodsAttributeValue;
import com.Vshop.service.module.goods.dao.AttributeValueDao;
import com.Vshop.service.module.goods.dao.GoodsAttributeDao;
import com.Vshop.service.module.goods.service.AttributeService;

@Service
public class AttributeServiceImpl implements AttributeService {

	@Resource
    private GoodsAttributeDao attributeDao;

    @Resource
    private AttributeValueDao attributeValueDao;
    
	@Override
	public GoodsAttribute findById(int id) {
		return attributeDao.findById(id);
	}

	@Override
	public void save(GoodsAttribute goodsAttribute) {
		//修改attribute表
        goodsAttribute.setAttrValue(Collections3.extractToString(goodsAttribute.getAvList(),"attrValueName",","));
        attributeDao.update(goodsAttribute);
        //修改attribute——value表
        List<GoodsAttributeValue> list = goodsAttribute.getAvList();
        if(list != null){
            for(GoodsAttributeValue av : list){
                //根据是否删除的标志位决定是否删除
                if(av.getDelSign() == null){
                    av.setAttrId(goodsAttribute.getAttrId());
                    av.setTypeId(goodsAttribute.getTypeId());
                    if(av.getAttrValueId() != null){
                        attributeValueDao.update(av);
                    }else {
                        attributeValueDao.save(av);
                    }
                }else{
                        attributeValueDao.deleteById(av.getDelSign());
                }
            }
        }
	}
	
	/**
     * 删除属性和属性值
     * @param attrId
     */
    public void deleteAttrById(Integer attrId){
    	//删除属性
    	attributeDao.delete(attrId);
    	//删除属性值
    	attributeValueDao.deleteByAttrId(attrId);
    }

}
