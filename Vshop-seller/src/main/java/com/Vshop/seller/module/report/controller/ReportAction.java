package com.Vshop.seller.module.report.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.BalanceReport;
import com.Vshop.core.entity.base.GoodsClick;
import com.Vshop.core.entity.base.OrderReport;
import com.Vshop.core.entity.base.StorePurchaseRate;
import com.Vshop.core.jasperreport.JasperExport;
import com.Vshop.seller.utils.CommonConstants;
import com.Vshop.seller.utils.sessionKey.CacheUtils;
import com.Vshop.service.module.report.service.BalanceReportService;
import com.Vshop.service.module.report.service.OrderReportService;
import com.Vshop.service.module.report.service.StoreReportService;

@Controller
@Slf4j
@RequestMapping("/report")
public class ReportAction {
	
	@Autowired
	private StoreReportService storeReportService;
	
	@Autowired
	private OrderReportService orderReportService;
	
	@Autowired
	private BalanceReportService balanceReportService;
	
	@Autowired
	private TransactionAwareDataSourceProxy transactionAwareDataSourceProxy;
	@Autowired
	private TransactionAwareDataSourceProxy dataSourceProxy;

	/**
	 * 流量统计
	 * @return
	 */
	@RequestMapping("/clickIndex")
	public ModelAndView clickIndex(
			@RequestParam(value="toUrl",defaultValue="goodsClick") String toUrl,
			@RequestParam(value = "startTime", required=false) String startTimeStr,
			@RequestParam(value = "endTime", required=false) String endTimeStr,
			@RequestParam(value = "condition", required=false, defaultValue="today") String condition){
		ModelAndView model = new ModelAndView("/report/store/click-index");
		model.addObject("baseUrl", "clickIndex");
		//开始时间
		if(!StringUtils.isNotEmpty(startTimeStr)){
			startTimeStr = new Timestamp(System.currentTimeMillis()).toString();
		}
		model.addObject("startTime", startTimeStr);
		//结束时间
		if(!StringUtils.isNotEmpty(endTimeStr)){
			endTimeStr = new Timestamp(System.currentTimeMillis()).toString();
		}
		model.addObject("endTime", endTimeStr);
		model.addObject("toUrl", toUrl);
		model.addObject("condition", condition);
		return model;
	}

	/**
	 * 商品价格趋势
	 * @return
	 */
	@RequestMapping("/goodsClick")
	public void goodsClick(
			@RequestParam(value = "startTime", required=false) String startTimeStr,
			@RequestParam(value = "endTime", required=false) String endTimeStr,
			@RequestParam(value = "condition", required=false) String condition,
			String exportType,
			HttpServletRequest request, 
			HttpServletResponse response){
		try {
			//设置店铺id
			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			String reportPath = request.getRealPath("/")+"jasperreportTemplate/" + Constants.REPORT_GOODS + "/" + "goodsClick_" + ".jrxml";
			JasperDesign design = JRXmlLoader.load(reportPath);
//			JRDesignQuery query =(JRDesignQuery) design.getQuery();
			//动态改变sql
			JasperReport jasperReport = JasperCompileManager.compileReport(design);
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("storeId", storeId);
			Connection connection = getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param,connection);
			//关闭连接
			CloseConnection(connection);
				JasperExport jasperExport = new JasperExport();
				//生成文件
				jasperExport.export(request, response,jasperPrint,exportType);
			
		} catch (Exception e) {
			log.error("",e);
		}finally{
			try {
				response.flushBuffer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 流量统计
	 * @return
	 */
	@RequestMapping("/balanceIndex")
	public ModelAndView balanceIndex(
			@RequestParam(value="toUrl",defaultValue="balance") String toUrl,
			@RequestParam(value = "condition", required=false, defaultValue="today") String condition){
		ModelAndView model = new ModelAndView("/report/store/balance-index");
		model.addObject("baseUrl", "balanceIndex");
		//开始时间
		model.addObject("toUrl", toUrl);
		model.addObject("condition", condition);
		return model;
	}
	
	/**
	 * 商品价格趋势
	 * @return
	 */
	@RequestMapping("/balance")
	public void balance(
			@RequestParam(value = "startTime", required=false) String startTimeStr,
			@RequestParam(value = "endTime", required=false) String endTimeStr,
			@RequestParam(value = "condition", required=false) String condition,
			@RequestParam(value = "balanceState", required=false) String balanceState,
			String exportType,
			HttpServletRequest request, 
			HttpServletResponse response){
		try {
//			BalanceReport balanceReport = new BalanceReport();
			//设置店铺id
			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			String reportPath = request.getRealPath("/")+"jasperreportTemplate/" + Constants.REPORT_BALANCE + "/" + "BalanceReport" + ".jrxml";
			JasperDesign design = JRXmlLoader.load(reportPath);
			JRDesignQuery query =(JRDesignQuery) design.getQuery();
			//动态改变sql
			if(StringUtils.isNotBlank(startTimeStr)||StringUtils.isNotBlank(endTimeStr)||StringUtils.isNotBlank(balanceState)){
				query.setText(getBalanceSql(startTimeStr, endTimeStr,balanceState));
			}
			System.out.println("sql------------------------"+query.getText());
			JasperReport jasperReport = JasperCompileManager.compileReport(design);
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("storeId",storeId);
			param.put("dateFormat",condition);
			Connection connection = getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param,connection);
			//关闭连接
			CloseConnection(connection);
				JasperExport jasperExport = new JasperExport();
				//生成文件
			jasperExport.export(request, response,jasperPrint,exportType);
			
		} catch (Exception e) {
			log.error("",e);
		}finally{
			try {
				response.flushBuffer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * 订单统计
	 * @return
	 */
	@RequestMapping("/orderIndex")
	public ModelAndView sellIndex(
			@RequestParam(value="toUrl",defaultValue="orderReport") String toUrl,
			@RequestParam(value = "condition", required=false, defaultValue="%Y-%m-%d") String condition){
		ModelAndView model = new ModelAndView("/report/store/order-index");
		model.addObject("baseUrl", "orderIndex");
		model.addObject("toUrl", toUrl);
		model.addObject("condition", condition);
		return model;
	}
	
	/**
	 * 商品销量
	 * @return
	 */
	@RequestMapping("/orderReport")
	public void storeSellCount(
			@RequestParam(value = "startTime", required=false) String startTimeStr,
			@RequestParam(value = "endTime", required=false) String endTimeStr,
			@RequestParam(value = "condition", required=false) String condition,
			@RequestParam(value = "orderState", required=false) String orderState,
			String exportType,
			HttpServletRequest request, 
			HttpServletResponse response){
		try {
			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			String reportPath = request.getRealPath("/")+"jasperreportTemplate/" + Constants.REPORT_ORDER + "/" + "orderTemplate" + ".jrxml";
			JasperDesign design = JRXmlLoader.load(reportPath);
			JRDesignQuery query =(JRDesignQuery) design.getQuery();
			//动态改变sql
			if(StringUtils.isNotBlank(startTimeStr)||StringUtils.isNotBlank(endTimeStr)||StringUtils.isNotBlank(orderState)){
				query.setText(getSql(startTimeStr, endTimeStr,orderState));
			}
			System.out.println("sql------------------------"+query.getText());
			JasperReport jasperReport = JasperCompileManager.compileReport(design);
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("storeId",storeId);
			param.put("dateFormat",condition);
			Connection connection = getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param,connection);
			//关闭连接
			CloseConnection(connection);
				JasperExport jasperExport = new JasperExport();
				//生成文件
				jasperExport.export(request, response,jasperPrint,exportType);
		} catch (Exception e) {
			log.error("",e);
		}finally{
			try {
				response.flushBuffer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String getSql(String startTimeStr,String endTimeStr,String orderState){
		StringBuffer sb=new StringBuffer();
		sb.append("(select FROM_UNIXTIME(create_time/1000,$P{dateFormat}) date,count(1) num from shop_order  where store_id=$P{storeId} "+getWhere(startTimeStr, endTimeStr,orderState)+" group by  FROM_UNIXTIME(create_time/1000,$P{dateFormat}) limit 10)");
		sb.append("union");
		sb.append("(select '总订单数' date,count(1) num from shop_order  where store_id=$P{storeId} "+getWhere(startTimeStr, endTimeStr,orderState)+"  )");
		return sb.toString();
	}
	
	private String getPurchaseRateSql(String startTimeStr,String endTimeStr){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from ( (select s_goods.goods_name,sum(s_o_goods.goods_num) as num ,0 as order_flag  from shop_goods s_goods left join shop_order_goods s_o_goods on s_goods.goods_id=s_o_goods.goods_id inner join shop_order  s_order on s_order.order_id=s_o_goods.order_id where s_order.store_id=$P{storeId} ");
		sb.append(" and s_order.order_state=40 "+getPurchaseRateWhere(startTimeStr, endTimeStr)+" group by s_goods.goods_id order by sum(s_o_goods.goods_num) desc limit 10) ");
		sb.append("	union ");
		sb.append("	( ");
		sb.append("	select '购买总数'as goods_name,sum(s_o_goods.goods_num) as num,1 as order_flag  from shop_goods s_goods left join shop_order_goods s_o_goods on s_goods.goods_id=s_o_goods.goods_id inner join shop_order  s_order on s_order.order_id=s_o_goods.order_id where s_order.store_id=$P{storeId} and s_order.order_state=40  ");
		sb.append(getPurchaseRateWhere(startTimeStr, endTimeStr)+"	) ) a order by order_flag,num desc ");
		return sb.toString();
	}
	
	private String getBalanceSql(String startTimeStr,String endTimeStr,String balanceState){
		StringBuffer sb=new StringBuffer();
		sb.append("(select FROM_UNIXTIME(finnshed_time/1000,$P{dateFormat}) date,sum(goods_amount) as '商品总价格', sum(discount) as '折扣价格' ,sum(order_amount) as '支付金额' , sum(goods_amount+shipping_fee)as '订单总金额',sum(shipping_fee) as '运费价格' ,sum(voucher_price) as '代金券金额' from shop_order s_order where  payment_state=1 " );
		sb.append(" and order_state=40 AND STORE_ID=$P{storeId} "+getBalanceWhere(startTimeStr, endTimeStr, balanceState)+" GROUP BY FROM_UNIXTIME(finnshed_time/1000,$P{dateFormat}) limit 10) " );
		sb.append("union  " );
		sb.append("(  " );
		sb.append("select '总计' as date,sum(goods_amount) as '商品总价格', sum(discount) as '折扣价格' ,sum(order_amount) as '支付金额' , sum(goods_amount+shipping_fee)as '订单总金额',sum(shipping_fee) as '运费价格' ,sum(voucher_price) as '代金券金额' from shop_order s_order where  payment_state=1 and order_state=40 AND STORE_ID=$P{storeId}  "+getBalanceWhere(startTimeStr, endTimeStr, balanceState) );
		sb.append(")  " );
		return sb.toString();
		
	}
	private String getWhere(String startTimeStr,String endTimeStr,String orderState){
		StringBuffer sb=new StringBuffer();
		if(StringUtils.isNotBlank(startTimeStr)){
			sb.append(" and create_time>="+DateUtils.strToLong(startTimeStr,"yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(endTimeStr)){
			sb.append(" and create_time<="+DateUtils.strToLong(endTimeStr,"yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(orderState)){
			sb.append(" and order_state="+orderState);
		}
		
		return sb.toString();
	}
	private String getBalanceWhere(String startTimeStr,String endTimeStr,String balanceState){
		StringBuffer sb=new StringBuffer();
		if(StringUtils.isNotBlank(startTimeStr)){
			sb.append(" and finnshed_time>="+DateUtils.strToLong(startTimeStr,"yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(endTimeStr)){
			sb.append(" and finnshed_time<="+DateUtils.strToLong(endTimeStr,"yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(balanceState)){
			sb.append(" and balance_state="+balanceState);
		}
		
		return sb.toString();
	}
	private String getPurchaseRateWhere(String startTimeStr,String endTimeStr){
		StringBuffer sb=new StringBuffer();
		if(StringUtils.isNotBlank(startTimeStr)){
			sb.append(" and s_order.create_time>="+DateUtils.strToLong(startTimeStr,"yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(endTimeStr)){
			sb.append(" and s_order.create_time<="+DateUtils.strToLong(endTimeStr,"yyyy-MM-dd"));
		}
		return sb.toString();
	}
//	
//	/**
//	 * 店铺总销量
//	 * @return
//	 */
//	@RequestMapping("/storeTotalCount")
//	public void storeTotalCount(
//			@RequestParam(value = "startTime", required=false) String startTimeStr,
//			@RequestParam(value = "endTime", required=false) String endTimeStr,
//			@RequestParam(value = "condition", required=true, defaultValue="today") String condition,
//			String exportType,
//			HttpServletRequest request, 
//			HttpServletResponse response){
//		try {
//			StoreTotalCount storeTotalCount = new StoreTotalCount();
//			//设置店铺id
//			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
//			storeTotalCount.setStoreId(storeId);
//			//开始时间
//			storeTotalCount.setStartTime(Timestamp.valueOf(startTimeStr));
//			//结束时间
//			storeTotalCount.setEndTime(Timestamp.valueOf(endTimeStr));
//			storeTotalCount.setCondition(condition);
//			String reportPath = CommonConstants.REPORT_BASEPATH + Constants.REPORT_STORE_GOODS + "/" + "StoreTotalCount" + ".jasper";
//			List<StoreTotalCount> data = storeReportService.getStoreTotalCount(storeTotalCount);
//			JasperExport jasperExport = new JasperExport();
//			if(StringUtils.isNotEmpty(exportType)){
//				if(exportType.equals("pdf")){
//					jasperExport.exportPdf(request, response, reportPath, data);
//				}else if(exportType.equals("excel")){
//					jasperExport.exportExcel(request, response, reportPath, data);
//				}
//			}else{
//				jasperExport.exportHtml(request, response, reportPath, data);
//			}
//		} catch (Exception e) {
//			log.error("",e);
//		}finally{
//			try {
//				response.flushBuffer();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
	
	/**
	 * 购买率
	 * @return
	 */
	@RequestMapping("/purchaseRateIndex")
	public ModelAndView purchaseRateIndex(
			@RequestParam(value="toUrl",defaultValue="purchaseRate") String toUrl,
			@RequestParam(value = "condition", required=false, defaultValue="%Y-%m-%d") String condition){
		ModelAndView model = new ModelAndView("/report/store/purchase-rate-index");
		model.addObject("baseUrl", "purchaseRateIndex");
		model.addObject("toUrl", toUrl);
		model.addObject("condition", condition);
		return model;
	}

	/**
	 * 购买率
	 * @return
	 */
	@RequestMapping("/purchaseRate")
	public void purchaseRate(HttpServletRequest request, HttpServletResponse response,String exportType,
			@RequestParam(value = "startTime", required=false) String startTimeStr,
			@RequestParam(value = "endTime", required=false) String endTimeStr
			){
		try {
			Integer storeId = CacheUtils.getCacheUser().getStore().getStoreId();
			String reportPath = request.getRealPath("/")+"jasperreportTemplate/" + Constants.REPORT_STORE+ "/" + "StorePurchaseRate" + ".jrxml";
			JasperDesign design = JRXmlLoader.load(reportPath);
			JRDesignQuery query =(JRDesignQuery) design.getQuery();
			//动态改变sql
			if(StringUtils.isNotBlank(startTimeStr)||StringUtils.isNotBlank(endTimeStr)){
				query.setText(getPurchaseRateSql(startTimeStr, endTimeStr));
			}
			System.out.println("sql------------------------"+query.getText());
			JasperReport jasperReport = JasperCompileManager.compileReport(design);
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("storeId",storeId);
			Connection connection = getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param,connection);
			//关闭连接
			CloseConnection(connection);
				JasperExport jasperExport = new JasperExport();
				//生成文件
				jasperExport.export(request, response,jasperPrint,exportType);
		} catch (Exception e) {
			log.error("",e);
		}
	}
	
	/** 获取数据库连接
	 * @return
	 * @author kwg
	 */
	private Connection getConnection(){
		try {
			return dataSourceProxy.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 关闭数据库连接
	 * @param getConnection()
	 * @author kwg
	 */
	private void CloseConnection(Connection conn){
		if(conn!=null){
			try {
				if(!conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
