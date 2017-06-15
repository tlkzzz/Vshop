package com.Vshop.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.Vshop.core.common.Collections3;
import com.Vshop.core.entity.GoodsAttribute;
import com.Vshop.core.entity.GoodsType;
import com.Vshop.core.entity.base.GoodsAttributeValue;
import com.Vshop.core.entity.base.TypeBrand;
import com.Vshop.core.entity.base.TypeSpec;
import com.Vshop.core.entity.vo.GoodsTypeVO;
import com.Vshop.service.module.goods.dao.AttributeValueDao;
import com.Vshop.service.module.goods.dao.GoodsAttributeDao;
import com.Vshop.service.module.goods.dao.GoodsTypeDao;
import com.Vshop.service.module.goods.dao.TypeBrandDao;
import com.Vshop.service.module.goods.dao.TypeSpecDao;
import com.Vshop.service.module.goods.service.GoodsTypeService;
import com.Vshop.service.module.goods.vo.GoodsTypeVo;
import com.Vshop.service.utils.page.Pager;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService{
	
	@Resource
	private GoodsTypeDao goodsTypeDao;
	
	@Resource
    private GoodsAttributeDao attributeDao;
	
	@Resource
    private TypeBrandDao tbDao;
    @Resource
    private TypeSpecDao tsDao;
    
    @Resource
    private AttributeValueDao attrValueDao;
	
	@Override
	public void save(GoodsType goodsType) {
		goodsTypeDao.save(goodsType);
	}

	@Override
	public void update(GoodsType goodsType) {
		goodsTypeDao.update(goodsType);
	}

	@Override
	public void delete(Integer typeId) {
		goodsTypeDao.delete(typeId);
		//删除属性
		attributeDao.deleteByType(typeId);
		//删除属性值
		attributeDao.deleteByType(typeId);
	}

	@Override
	public GoodsType findById(Integer typeId) {
		return goodsTypeDao.findById(typeId);
	}

	@Override
	public List<GoodsType> findList() {
		return goodsTypeDao.findList();
	}

	@Override
	public GoodsTypeVO selectTypeFetchOther(Integer typeId) {
		return goodsTypeDao.selectTypeFetchOther(typeId);
	}

	@Override
	public int findCount(Pager pager) {
		return goodsTypeDao.findCount(pager);
	}

	@Override
	public List<GoodsType> findPageList(Pager pager) {
		return goodsTypeDao.findPageList(pager);
	}

	/*public List<GoodsType> findTypeByClassId() {
		return goodsTypeDao.findTypeByClassId();
	}*/

	@Override
	public void saveGoodsType(GoodsTypeVo vo) {
		GoodsType type = vo.getGoodsType();
		goodsTypeDao.save(type);
		int typeId = type.getTypeId();
		this.saveOther(vo, typeId);

	}

	@Override
	public void updateGoodsType(GoodsTypeVo vo) {
		//先删除
        deleteOther(vo.getGoodsType().getTypeId());
        //修改good_type
        goodsTypeDao.update(vo.getGoodsType());
        //重新批量插入
        this.saveOther(vo,vo.getGoodsType().getTypeId());
	}

	@Override
	public void updateType(GoodsType type) {
		goodsTypeDao.updateType(type);
	}
	
	private void deleteOther(int typeId){

        attrValueDao.deleteBatch(typeId);
        attributeDao.deleteBatch(typeId);
        tbDao.delete(typeId);
        tsDao.delete(typeId);
    }
	
	/**
     * 插入其余的表
     * @param vo
     * @param typeId
     */
    private void saveOther(GoodsTypeVo vo,int typeId){
        //重新构造插入good_attribute,shop_attribute_value
        List<GoodsAttribute> attrList= Lists.newArrayList();
        if(Collections3.isNotEmpty(vo.getAttrList())){
            for(GoodsAttribute attr:vo.getAttrList()){
                if(!StringUtils.isBlank(attr.getAttrName()) && !StringUtils.isBlank(attr.getAttrValue()) && attr.getAttrId() == null){
                    attr.setTypeId(typeId);
                    attrList.add(attr);
                    attributeDao.save(attr);
                    //这里应该用批量插入，可惜解决不了返回主键
                    if(!attr.getAttrValue().contains(",")){
                        GoodsAttributeValue attrValue = new GoodsAttributeValue();
                        attrValue.setAttrId(attr.getAttrId());
                        attrValue.setTypeId(attr.getTypeId());
                        attrValue.setAttrValueName(attr.getAttrValue());
                        attrValueDao.save(attrValue);
                    }else{
                        for(String value : attr.getAttrValue().split(",")){
                            GoodsAttributeValue attrValue = new GoodsAttributeValue();
                            attrValue.setAttrId(attr.getAttrId());
                            attrValue.setTypeId(attr.getTypeId());
                            attrValue.setAttrValueName(value);
                            attrValueDao.save(attrValue);
                        }
                    }
                }
            }
        }
        //重新构造插入good_type_brand
        if(Collections3.isNotEmpty(vo.getBrandList())){
            List<TypeBrand> tbList= Lists.newArrayList();
            for(TypeBrand tb:vo.getBrandList()){
                if(tb.getBrandId() != null){
                    tb.setTypeId(typeId);
                    tbList.add(tb);
                }
            }
            tbDao.batchSave(tbList);
        }
        //重新构造插入good_type_spec
        if(Collections3.isNotEmpty(vo.getSpecList())){
            List<TypeSpec> tsList= Lists.newArrayList();
            for(TypeSpec ts:vo.getSpecList()){
                if(ts.getSpId() != null){
                    ts.setTypeId(typeId);
                    tsList.add(ts);
                }
            }
            tsDao.batchSave(tsList);
        }
    }
	

}
