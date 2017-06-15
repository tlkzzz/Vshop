package com.Vshop.service.module.setting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Express;
import com.Vshop.service.module.setting.dao.ExpressDao;
import com.Vshop.service.module.setting.service.ExpressService;
import com.Vshop.service.utils.page.Pager;

/**
 * 
 * 
 * 项目名称：Vshop-admin 类名称：ExpressServiceImpl 类描述：service实现类 创建人：sangyuchen
 * 创建时间：2014年11月18日 上午9:44:03 修改人：sangyuchen 修改时间：2014年11月18日 上午9:44:03 修改备注：
 * 
 * @version
 * 
 */
@Service("expressService")
public class ExpressServiceImpl implements ExpressService {
	@Autowired
	private ExpressDao expressDao;

	/**
	 * 获取总记录数
	 */
	public int findExpressCount(Express express) {
		return expressDao.findExpressCount(express);
	}

	/**
	 * 查询所有的用户，用分页显示
	 */
	public List<Express> findExpressList(Pager pager) {
		return expressDao.findExpressList(pager);
	}

	@Override
	public void updateOrder(Express Express) {
		// TODO Auto-generated method stub
		expressDao.updateOrder(Express);
	}

	@Override
	public void updateState(Express Express) {
		// TODO Auto-generated method stub
		expressDao.updateState(Express);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		expressDao.delete(id);
	}

	@Override
	public List<Express> findList() {
		return expressDao.findList();
	}
	
	/**
	 * 根据id查询实体
	 * @param id
	 * @return
	 */
	public Express findById(Integer id){
		return expressDao.findById(id);
	}
	
	/**
	 * 根据显示状态和是否常用状态查询物流公司信息
	 * @param eState 显示状态,若只查询显示传1,不需要传null,传其他值无效
	 * @param eOrder 是否常用,若只显示常用传1,不需要传null,传其他值无效
	 * @return
	 */
	public List<Express> findExpressListByState(Integer eState,Integer eOrder){
		if(eState!=null&&eState!=1){
			eState = null;
		}
		if(eOrder!=null&&eOrder!=1){
			eOrder = null;
		}
		return expressDao.findExpressListByState(eState, eOrder);
	}

}
