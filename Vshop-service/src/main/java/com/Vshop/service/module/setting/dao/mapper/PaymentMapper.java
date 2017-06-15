package com.Vshop.service.module.setting.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Vshop.core.entity.base.Payment;
import com.Vshop.core.orm.mybatis.SqlMapper;


/**
 * 支付方式
 *    
 * 项目名称：Vshop-admin   
 * 类名称：PaymentMapper   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年11月10日 下午11:33:35   
 * 修改人：liuhao   
 * 修改时间：2014年11月10日 下午11:33:35   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface PaymentMapper{
	 
    /**
     * 
     * @Title: queryAll 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @return    设定文件 
     * @return List<Payment>    返回类型 
     * @throws
     */
    public List<Payment> queryAll();
    
    /**
     * 
     * @Title: save 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param payment    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void save(Payment payment);
    
    /**
     * 
     * @Title: update 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param payment    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void update(Payment payment);
    
    /**
     * 
     * @Title: findById 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param id
     * @param @return    设定文件 
     * @return Payment    返回类型 
     * @throws
     */
    public Payment findById(@Param("paymentId") Long id);
    
    /**
     * 
     * @Title: delete 
     * @Description: TODO(根据id删除数据) 
     * @param @param id    设定文件 
     * @return void    返回类型 
     * @throws
     */
	public void delete(@Param("paymentId") Long paymentId);

    /**
     * 查询支付名称
     * @param code
     * @return
     */
    String queryByCode(@Param("code") String code);
    
    Payment selectById(Integer id);
	
	Payment selectByCode(String code);
	
	 /**
     * 
     * @Title: querybystate 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @return    设定文件 
     * @return List<Payment>
     * @throws 接口状态0禁用1启用
     */
    public List<Payment> querybystatelist(String paymentstate);
}
