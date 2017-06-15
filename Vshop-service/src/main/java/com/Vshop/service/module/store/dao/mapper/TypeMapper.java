package com.Vshop.service.module.store.dao.mapper;

import java.util.List;

import com.Vshop.core.entity.Type;
import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.service.module.store.vo.TypesVo;
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
public interface TypeMapper{
	 
    public List<TypesVo> queryClasssList(Pager pager);
    public List<Type> queryClasssParentList();
    public void save(Type classs);
    public void delete(Long id);
    public Type queryById(int id);
    public void update(Type classs);

    /**
     * 查询子节点
     * @return
     */
    public List<Type> queryClasssChildrenList(Type classs);

    /**
     * 去重
     * @param classs
     * @return
     */
    public int findCount(Type classs);
    
    /**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
    List<Type> findList(int parentid);
    
    List<Type> findAllList();
}
