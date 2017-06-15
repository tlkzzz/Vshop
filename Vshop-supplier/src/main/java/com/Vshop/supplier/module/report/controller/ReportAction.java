package com.Vshop.supplier.module.report.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jersey.repackaged.com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
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

import com.google.common.base.Joiner;
import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.jasperreport.JasperExport;
import com.Vshop.supplier.utils.sessionKey.CacheUtils;
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
		
		Connection connection = getConnection();
		try {
			String loadTemplatePath = getLoadTemplate(request, Constants.REPORT_GOODS, "goodsClick_");
			boolean isAction = false;
			String querySql = null;
			Map<String, Object> params = Maps.newHashMap();
			exportResult(request, response, exportReady(loadTemplatePath, isAction, querySql, connection, params), exportType);
		} catch (Exception e) {
			log.error("",e);
		}finally{
			try {
				CloseConnection(connection);
				response.flushBuffer();
			} catch (IOException e) {
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
		
		Connection connection = getConnection();
		try {
			String loadTemplatePath = getLoadTemplate(request, Constants.REPORT_BALANCE, "BalanceReport");
			boolean isAction = false;
			String querySql = null;
			if(StringUtils.isNotBlank(startTimeStr) || StringUtils.isNotBlank(endTimeStr) || StringUtils.isNotBlank(balanceState)){
				isAction = true;
				querySql = getBalanceSql(startTimeStr, endTimeStr, balanceState);
			}
			Map<String, Object> params = Maps.newHashMap();
			params.put("dateFormat", condition);
			exportResult(request, response, exportReady(loadTemplatePath, isAction, querySql, connection, params), exportType);
		} catch (Exception e) {
			log.error("",e);
		}finally{
			try {
				CloseConnection(connection);
				response.flushBuffer();
			} catch (IOException e) {
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
		
		Connection connection = getConnection();
		try {
			String loadTemplatePath = getLoadTemplate(request, Constants.REPORT_ORDER, "orderTemplate");
			boolean isAction = false;
			String querySql = null;
			if(StringUtils.isNotBlank(startTimeStr) || StringUtils.isNotBlank(endTimeStr) || StringUtils.isNotBlank(orderState)){
				isAction = true;
				querySql = getOrderSql(startTimeStr, endTimeStr, orderState);
			}
			Map<String, Object> params = Maps.newHashMap();
			params.put("dateFormat",condition);
			exportResult(request, response, exportReady(loadTemplatePath, isAction, querySql, connection, params), exportType);
		} catch (Exception e) {
			log.error("",e);
		}finally{
			try {
				CloseConnection(connection);
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getOrderSql(String startTimeStr, String endTimeStr, String orderState){
		String _where = getWhere(startTimeStr, endTimeStr, orderState);
		return Joiner.on(" ").join(//
					"(SELECT FROM_UNIXTIME(so.create_time/1000,$P{dateFormat}) AS `date`, COUNT(1) AS num ",//
					    "FROM (SELECT sog.order_id AS order_id FROM shop_goods sg INNER JOIN shop_order_goods sog ON sg.goods_id = sog.goods_id ",//
					    	  "WHERE sg.supplier_id = $P{supplierId}) go ",//
					    	  "INNER JOIN shop_order so ON go.order_id = so.order_id ",
					    	  String.format("WHERE 1 = 1 %s ", _where),//
					    	  "GROUP BY `date` LIMIT 10) ",//
					"UNION ",//
					"(SELECT '总订单数' AS `date`, COUNT(1) AS num ",// 
					    "FROM (SELECT sog.order_id AS order_id FROM shop_goods sg INNER JOIN shop_order_goods sog ON sg.goods_id = sog.goods_id ",//
					    	  "WHERE sg.supplier_id = $P{supplierId}) go ",//
					    	  "INNER JOIN shop_order so ON go.order_id = so.order_id ",//
					    	  String.format("WHERE 1 = 1 %s )", _where)//
				);
	}
	
	private String getPurchaseRateSql(String startTimeStr, String endTimeStr){
		String _where = getPurchaseRateWhere(startTimeStr, endTimeStr);
		return Joiner.on(" ").join(
					"SELECT * FROM ( ",//
					   "(SELECT sg.goods_name, IFNULL(SUM(og.goods_num), 0) AS num, 0 AS order_flag ",// 
					       "FROM shop_goods sg LEFT JOIN shop_order_goods og ON sg.goods_id = og.goods_id INNER JOIN shop_order  so ON so.order_id = og.order_id ",//
					    String.format("WHERE sg.supplier_id = $P{supplierId} AND so.order_state = 40 %s ", _where),//
					    "GROUP BY sg.goods_id ORDER BY IFNULL(SUM(og.goods_num), 0) DESC LIMIT 10) ",//
					"UNION ",
					   "(SELECT '购买总数' AS goods_name, IFNULL(SUM(og.goods_num), 0) AS num, 1 AS order_flag ",// 
					       "FROM shop_goods sg LEFT JOIN shop_order_goods og ON sg.goods_id = og.goods_id INNER JOIN shop_order  so ON so.order_id = og.order_id ",//
					    String.format("WHERE sg.supplier_id = $P{supplierId} AND so.order_state = 40 %s) ", _where),//
					") a ORDER BY order_flag, num DESC "//
				);
	}
	
	private String getBalanceSql(String startTimeStr, String endTimeStr, String balanceState){
		String _where = getBalanceWhere(startTimeStr, endTimeStr, balanceState);
		return Joiner.on(" ").join(//
					"(SELECT ",
						"FROM_UNIXTIME(so.finnshed_time/1000, $P{dateFormat}) AS `date`,",//
						"IFNULL(SUM(so.goods_amount), 0) AS '商品总价格',",//
						"IFNULL(SUM(so.discount), 0) AS '折扣价格',",//
						"IFNULL(SUM(so.order_amount), 0) AS '支付金额',",//
						"IFNULL(SUM(so.goods_amount + so.shipping_fee), 0) AS '订单总金额',",//
						"IFNULL(SUM(so.shipping_fee), 0) AS '运费价格',",//
						"IFNULL(SUM(so.voucher_price), 0) AS '代金券金额' ",//
					    "FROM shop_order so INNER JOIN shop_order_goods og ON so.order_id = og.order_id INNER JOIN shop_goods sg ON og.goods_id = sg.goods_id ",// 
					 String.format("WHERE so.payment_state = 1 AND so.order_state = 40 AND sg.supplier_id = $P{supplierId} %s GROUP BY `date`) ", _where),//
					 "UNION ",// 
					 "(SELECT ",// 
						"'总计' AS `data`,",//
						"IFNULL(SUM(so.goods_amount), 0) AS '商品总价格',",// 
						"IFNULL(SUM(so.discount), 0) AS '折扣价格',",//
						"IFNULL(SUM(so.order_amount), 0) AS '支付金额',",// 
						"IFNULL(SUM(so.goods_amount + so.shipping_fee), 0) AS '订单总金额',",//
						"IFNULL(SUM(so.shipping_fee), 0) AS '运费价格',",//
						"IFNULL(SUM(so.voucher_price), 0) AS '代金券金额' ",// 
					    "FROM shop_order so INNER JOIN shop_order_goods og ON so.order_id = og.order_id INNER JOIN shop_goods sg ON og.goods_id = sg.goods_id ",// 
					 String.format("WHERE so.payment_state = 1 AND so.order_state = 40 AND sg.supplier_id = $P{supplierId} %s) ", _where)//
				);
	}
	
	private String getWhere(String startTimeStr,String endTimeStr, String orderState){
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotBlank(startTimeStr)){
			sb.append(" AND so.create_time >= " + DateUtils.strToLong(startTimeStr, "yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(endTimeStr)){
			sb.append(" AND so.create_time <= " + DateUtils.strToLong(endTimeStr, "yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(orderState)){
			sb.append(" AND so.order_state = " + orderState);
		}
		return sb.toString();
	}
	
	private String getBalanceWhere(String startTimeStr, String endTimeStr, String balanceState){
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotBlank(startTimeStr)){
			sb.append(" AND so.finnshed_time >= " + DateUtils.strToLong(startTimeStr, "yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(endTimeStr)){
			sb.append(" AND so.finnshed_time <= " + DateUtils.strToLong(endTimeStr, "yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(balanceState)){
			sb.append(" AND so.balance_state = " + balanceState);
		}
		return sb.toString();
	}
	
	private String getPurchaseRateWhere(String startTimeStr, String endTimeStr){
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotBlank(startTimeStr)){
			sb.append(" AND so.create_time >= " + DateUtils.strToLong(startTimeStr, "yyyy-MM-dd"));
		}
		if(StringUtils.isNotBlank(endTimeStr)){
			sb.append(" AND so.create_time <= " + DateUtils.strToLong(endTimeStr, "yyyy-MM-dd"));
		}
		return sb.toString();
	}
	
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
		
		Connection connection = getConnection();
		try {
			String loadTemplatePath = getLoadTemplate(request, Constants.REPORT_STORE, "StorePurchaseRate");
			boolean isAction = false;
			String querySql = null;
			if(StringUtils.isNotBlank(startTimeStr) || StringUtils.isNotBlank(endTimeStr)){
				isAction = true;
				querySql = getPurchaseRateSql(startTimeStr, endTimeStr);
			}
			Map<String, Object> params = Maps.newHashMap();
			exportResult(request, response, exportReady(loadTemplatePath, isAction, querySql, connection, params), exportType);
		} catch (Exception e) {
			log.error("",e);
		}finally{
			try {
				CloseConnection(connection);
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
				e.printStackTrace();
			}
		}
	}
	
	private JasperPrint exportReady(String loadTemplatePath, boolean isAction, String querySql, Connection connection, Map<String, Object> params) throws JRException{
		Map<String, Object> _params = Maps.newHashMap(params);
		_params.put("supplierId", CacheUtils.getCacheUser().getSupplier().getId());
		JasperDesign design = JRXmlLoader.load(loadTemplatePath);
		JRDesignQuery query = (JRDesignQuery) design.getQuery();
		if(isAction) query.setText(querySql);
		
		log.info(String.format("execute SQL: %s", query.getText()));
		
		JasperReport jasperReport = JasperCompileManager.compileReport(design);
		return JasperFillManager.fillReport(jasperReport, _params, connection);
	}
	
	private void exportResult(HttpServletRequest request, HttpServletResponse response, JasperPrint jasperPrint, String exportType) throws JRException, IOException{
		new JasperExport().export(request, response, jasperPrint, exportType);
	}
	
	private String getLoadTemplate(HttpServletRequest request, String templateType, String templateName){
		return String.format("%sjasperreportTemplate%s\\%s.jrxml", request.getServletContext().getRealPath("/"), templateType, templateName);
	}
}
