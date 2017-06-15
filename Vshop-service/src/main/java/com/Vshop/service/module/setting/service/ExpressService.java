package com.Vshop.service.module.setting.service;

import java.util.List;

import com.Vshop.core.entity.base.Express;
import com.Vshop.service.utils.page.Pager;

/**
 * 项目名称：Vshop-admin   
 * 类名称：ExpressService   
 * 类描述：  
 * 修改备注：   
 * @version    
 */
public interface ExpressService {

	/**
	 * 获取总记录数
	 * @param pager
	 * @return
	 */
	public int findExpressCount(Express express);

	/**
	 * 
	 * @Title: findExpressList
	 * @Description: TODO (查询所有的快递公司信息，用分页显示)
	 * @param @param pager
	 * @param @return    设定文件
	 * @return List<Account>    返回类型
	 * @throws
	 */
	public List<Express> findExpressList(Pager pager);

	/**
	 * 
	 * @Title: updateOrder
	 * @Description: TODO (查询实体信息)
	 * @param @param Express
	 * @param @return    设定文件
	 * @return Account    返回类型
	 * @throws
	 */
	public void updateOrder(Express Express);
	
	/**
	 * 
	 * @Title: updateState
	 * @Description: TODO (查询实体信息)
	 * @param @param Express
	 * @param @return    设定文件
	 * @return Account    返回类型
	 * @throws
	 */
	public void updateState(Express Express);

	/**
	 * 
	 * @Title: delete
	 * @Description: TODO (删除)
	 * @param @param id    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void delete(Long id);
	
	/**
	 * 查询所有,无分页
	 * @return
	 */
	public List<Express> findList();
	
	/**
	 * 根据id查询实体
	 * @param id
	 * @return
	 */
	public Express findById(Integer id);
	
	/**
	 * 根据显示状态和是否常用状态查询物流公司信息
	 * @param eState 显示状态,若只查询显示传1,不需要传null,传其他值无效
	 * @param eOrder 是否常用,若只显示常用传1,不需要传null,传其他值无效
	 * @return
	 */
	public List<Express> findExpressListByState(Integer eState,Integer eOrder);

}
