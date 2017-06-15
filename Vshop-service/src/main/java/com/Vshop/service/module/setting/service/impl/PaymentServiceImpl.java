package com.Vshop.service.module.setting.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.Vshop.core.entity.base.Payment;
import com.Vshop.service.module.setting.dao.PaymentDao;
import com.Vshop.service.module.setting.service.PaymentService;

/**
 * 支付方式
 * 项目名称：Vshop-admin   
 * 类名称：PaymentServiceImpl   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月10日 下午11:33:15   
 * 修改人：liuhao   
 * 修改时间：2014年11月10日 下午11:33:15   
 * 修改备注：   
 * @version    
 *
 */

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

	@Resource
    private PaymentDao paymentDao;


	/**
	 * 查询支付方式
	 * @Title: queryAll 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @return    设定文件 
	 * @return List<Payment>    返回类型 
	 * @throws
	 */
	public List<Payment> queryAll() {
		return paymentDao.queryAll();
	}


    /**
     * 
     * @Title: save 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param payment    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void save(Payment payment){
    	payment.setCreateTime(System.currentTimeMillis());
    	paymentDao.save(payment);
		
	}


    /**
     * 
     * @Title: update 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param payment    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void update(Payment payment){
    	payment.setUpdateTime(System.currentTimeMillis());
    	paymentDao.update(payment);	
	}

    /**
     * 
     * @Title: findById 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param id
     * @param @return    设定文件 
     * @return Payment    返回类型 
     * @throws
     */
    public Payment findById(@Param("id")Long id){
    	return paymentDao.findById(id);
    }


	@Override
	public void delete(Long paymentId) {
		paymentDao.delete(paymentId);
	}

    /**
     * 查询支付名称
     *
     * @param code
     * @return
     */
    @Override
    public String queryByCode(String code) {
        return paymentDao.queryByCode(code);
    }


	@Override
	public List<Payment> querybystatelist(String paymentstate) {
		return paymentDao.querybystatelist(paymentstate);
	}


	@Override
	public Payment selectByCode(String code) {
		return paymentDao.selectByCode(code);
	}

}

