package com.Vshop.service.module.stat.dao.mapper;

import java.util.List;

import com.Vshop.core.orm.mybatis.SqlMapper;
import com.Vshop.core.entity.base.Member;


/**
 * 
 *    
 * 项目名称：Vshop-admin   
 * 类名称：MemberMapper   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月7日 下午11:54:39   
 * 修改人：liuhao   
 * 修改时间：2014年11月7日 下午11:54:39   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface StatMemberMapper{
	 

	/**
	 * 按日查询
	 * @Title: queryStatMemberList 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param member
	 * @param @return    设定文件 
	 * @return List<Member>    返回类型 
	 */
    public List<Member> queryStatMemberList(Member member);
    
    /**
     * 按月查询
     * @Title: queryStatMemberMonthList 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param member
     * @param @return    设定文件 
     * @return List<Member>    返回类型 
     */
    public List<Member> queryStatMemberMonthList(Member member);
    
    
   
}
