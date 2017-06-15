package com.Vshop.service.module.store.dao.mapper;

import java.util.List;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.Classs;
import com.Vshop.core.entity.GoodsClass;
import com.Vshop.service.module.store.vo.ClasssVo;
import com.Vshop.service.utils.page.Pager;


/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：ClasssMapper   
 * 类描述：   
 * 创建人：weiyue   
 * 创建时间：2014年11月6日 下午10:47:37   
 * 修改人：weiyue   
 * 修改时间：2014年11月6日 下午10:47:37   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface ClasssMapper{
	 
    public List<ClasssVo> queryClasssList(Pager pager);
    public List<Classs> queryClasssParentList();
    public void save(Classs classs);
    public void delete(Long id);
    public Classs queryById(int id);
    public void update(Classs classs);

    /**
     * 查询子节点
     * @return
     */
    public List<Classs> queryClasssChildrenList(Classs classs);

    /**
     * 去重
     * @param classs
     * @return
     */
    public int findCount(Classs classs);
    
    /**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
    List<Classs> findList(int parentid);
}
