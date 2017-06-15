package com.Vshop.core.jasperreport;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
/**
 * 
 * @author 陈光亮 2015年05月10日10:15:16
 *
 */
public class JasperExport {
	
	/**
	 * export自动选择输出类型
	 * export=pdf导出pdf
	 * 等于excel导出excel
	 */
	public void export(HttpServletRequest request, HttpServletResponse response,
			String jasperPath,Connection conn, String export)throws JRException, IOException{
		if(export.equals("")){
			exportHtml(request, response, jasperPath, conn);
		}else if(export.equals("pdf")){
			exportPdf(request, response, jasperPath, conn);
		}else if(export.equals("excel")){
			exportExcel(request, response, jasperPath, conn);
		}else{
			exportHtml(request, response, jasperPath, conn);
		}
	}
	/**
	 * export自动选择输出类型
	 * export=pdf导出pdf
	 * 等于excel导出excel
	 */
	public void export(HttpServletRequest request, HttpServletResponse response,
			JasperPrint jasperPrint, String export)throws JRException, IOException{
		 if("pdf".equals(export)){
			exportPdf(request, response, jasperPrint);
		}else if("excel".equals(export)){
			exportExcel(request, response, jasperPrint);
		}else{
			exportHtml(request, response,jasperPrint);
		}
	}

	/**
	 * jasper导出,导出成Html
	 * @param request
	 * @param response
	 * @param jasperPath jasper的路径
	 * @param data list数据集合
	 * @throws IOException 
	 * @throws Exception
	 */
	public void exportHtml(HttpServletRequest request, HttpServletResponse response,
			String jasperPath,Connection conn)throws JRException, IOException{
		//设置utf
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获得jasperPrint
		JasperPrint jasperPrint = getJasperPrint(request, response, jasperPath, conn);
		//创建html的输出器
		JRHtmlExporter exporter = new JRHtmlExporter();
		//设置参数
		Map imagesMap = new HashMap();
		request.getSession().setAttribute("IMAGES_MAP", imagesMap);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap);
		//设置输出流
		PrintWriter out = response.getWriter();
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		//设置jasperPrint
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		//设置图片是否显示
		exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,Boolean.TRUE);
		//设置px图片路径
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "../servlets/image?image=");
		//chart要设置这个属性
		request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
		//输出
		exporter.exportReport();
	}
	

	/**
	 * jasper导出,导出成Html
	 * @param request
	 * @param response
	 * @param jasperPath jasper的路径
	 * @param data list数据集合
	 * @throws IOException 
	 * @throws Exception
	 */
	public void exportHtml(HttpServletRequest request, HttpServletResponse response,
			JasperPrint jasperPrint )throws JRException, IOException{
		//设置utf
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		//创建html的输出器
		JRHtmlExporter exporter = new JRHtmlExporter();
		//设置参数
		Map imagesMap = new HashMap();
		request.getSession().setAttribute("IMAGES_MAP", imagesMap);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap);
		//设置输出流
		PrintWriter out = response.getWriter();
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		//设置jasperPrint
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		//设置图片是否显示
		exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,Boolean.TRUE);
		//设置px图片路径
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "../servlets/image?image=");
		//chart要设置这个属性
		request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
		//输出
		exporter.exportReport();
	}
	
	
	/**
	 * jasper导出chart
	 * @param request
	 * @param response
	 * @param jasperPath jasper的路径
	 * @param data list数据集合
	 * @throws IOException 
	 * @throws Exception
	 */
	public void exportChart(HttpServletRequest request, HttpServletResponse response,
			String jasperPath,Connection conn)throws JRException, IOException{
		//设置utf
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获得jasperPrint
		JasperPrint jasperPrint = getJasperPrint(request, response, jasperPath, conn);
		//创建html的输出器
		JRHtmlExporter exporter = new JRHtmlExporter();
		//设置参数
		Map imagesMap = new HashMap();
		request.getSession().setAttribute("IMAGES_MAP", imagesMap);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap);
		//设置输出流
		PrintWriter out = response.getWriter();
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		//设置jasperPrint
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		//设置图片是否显示
		exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,Boolean.TRUE);
		//设置px图片路径
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "../servlets/image?image=");
		//chart要设置这个属性
		request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
		//输出
		exporter.exportReport();
	}
	/**
	 * jasper导出chart
	 * @param request
	 * @param response
	 * @param jasperPath jasper的路径
	 * @param data list数据集合
	 * @throws IOException 
	 * @throws Exception
	 */
	public void exportChart(HttpServletRequest request, HttpServletResponse response,
			JasperPrint jasperPrint)throws JRException, IOException{
		//设置utf
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		//创建html的输出器
		JRHtmlExporter exporter = new JRHtmlExporter();
		//设置参数
		Map imagesMap = new HashMap();
		request.getSession().setAttribute("IMAGES_MAP", imagesMap);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap);
		//设置输出流
		PrintWriter out = response.getWriter();
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		//设置jasperPrint
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		//设置图片是否显示
		exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR,Boolean.TRUE);
		//设置px图片路径
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "../servlets/image?image=");
		//chart要设置这个属性
		request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
		//输出
		exporter.exportReport();
	}
	
	
	/**
	 * jasper导出,导出成Excel
	 * @param request
	 * @param response
	 * @param jasperPath jasper的路径
	 * @param data list数据集合
	 * @throws IOException 
	 * @throws Exception
	 */
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
			String jasperPath,Connection conn)throws JRException, IOException{
		//设置utf
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		//获得jasperPrint
		JasperPrint jasperPrint = getJasperPrint(request, response, jasperPath, conn);
		//创建excel的输出器
		JRXlsExporter exporter = new JRXlsExporter();
		//设置输出流
		OutputStream out = response.getOutputStream(); 
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,out);
		//设置jasperPrint
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		//输出
		exporter.exportReport();
	}
	/**
	 * jasper导出,导出成Excel
	 * @param request
	 * @param response
	 * @param jasperPath jasper的路径
	 * @param data list数据集合
	 * @throws IOException 
	 * @throws Exception
	 */
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
			JasperPrint jasperPrint)throws JRException, IOException{
		//设置utf
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		//创建excel的输出器
		JRXlsExporter exporter = new JRXlsExporter();
		//设置输出流
		OutputStream out = response.getOutputStream(); 
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,out);
		//设置jasperPrint
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		//输出
		exporter.exportReport();
	}

	
	/**
	 * jasper导出,导出成Pdf
	 * @param request
	 * @param response
	 * @param jasperPath jasper的路径
	 * @param data list数据集合
	 * @throws IOException 
	 * @throws Exception
	 */
	public void exportPdf(HttpServletRequest request, HttpServletResponse response,
			String jasperPath,Connection conn)throws JRException, IOException{
		//设置utf
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/pdf");
		//获得jasperPrint
		JasperPrint jasperPrint = getJasperPrint(request, response, jasperPath, conn);
		//创建excel的输出器
		JRPdfExporter exporter = new JRPdfExporter();
		//设置输出流
		OutputStream out = response.getOutputStream(); 
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,out);
		//设置jasperPrint
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		//输出
		exporter.exportReport();
	}
	
	/**
	 * jasper导出,导出成Pdf
	 * @param request
	 * @param response
	 * @param jasperPath jasper的路径
	 * @param data list数据集合
	 * @throws IOException 
	 * @throws Exception
	 */
	public void exportPdf(HttpServletRequest request, HttpServletResponse response,
			JasperPrint jasperPrint)throws JRException, IOException{
		//设置utf
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/pdf");
		//创建excel的输出器
		JRPdfExporter exporter = new JRPdfExporter();
		//设置输出流
		OutputStream out = response.getOutputStream(); 
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,out);
		//设置jasperPrint
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		//输出
		exporter.exportReport();
	}
	
	/*
	 * 加载模板,以及准备数据源,返回一个jasperPrint
	 */
	private JasperPrint getJasperPrint(HttpServletRequest request, HttpServletResponse response, 
			String jasperPath,Connection conn)throws JRException{
		//加载jasper模板
		JasperReport jasperReport = (JasperReport) JRLoader .loadObject(new File(jasperPath));
		//将list数据放入jasper指定的数据源容器
//		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		//得到jasperPrint
		JasperPrint jasperPrint;
			jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
		//返回jasperPrint
		return jasperPrint;
	}
	
}
