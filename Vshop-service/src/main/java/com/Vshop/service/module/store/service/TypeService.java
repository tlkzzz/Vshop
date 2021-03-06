package com.Vshop.service.module.store.service;

import java.util.List;

import com.Vshop.core.entity.Type;
import com.Vshop.service.module.store.vo.TypesVo;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：ClasssService   
 * 类描述：   
 * 创建人：weiyue   
 * 创建时间：2014年11月6日 下午10:59:18   
 * 修改人：weiyue   
 * 修改时间：2014年11月6日 下午10:59:18   
 * 修改备注：   
 * @version    
 *
 */
public interface TypeService {
	
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
    
    public List<Type> findList(int parentid);
    
    List<Type> findAllList();
}
