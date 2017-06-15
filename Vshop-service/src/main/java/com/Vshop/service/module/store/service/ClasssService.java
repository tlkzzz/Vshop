package com.Vshop.service.module.store.service;

import java.util.List;

import com.Vshop.core.entity.Classs;
import com.Vshop.service.module.store.vo.ClasssVo;
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
public interface ClasssService {
	
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
    
    public List<Classs> findList(int parentid);
}
