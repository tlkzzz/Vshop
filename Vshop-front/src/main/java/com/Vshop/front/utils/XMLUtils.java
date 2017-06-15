package com.Vshop.front.utils;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtils {
	
	
	@SuppressWarnings("rawtypes")
	public static Map<String ,String> xmlToString(InputStream in){
		Map<String ,String> map = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();
		try {
			Document doc = saxReader.read(in);
			Element root = doc.getRootElement();
			for(Iterator iter = root.elementIterator(); iter.hasNext();){  
	            Element e = (Element)iter.next(); 
//	            Log.debug(e.getName());
//	            Log.debug(e.getText());
	            map.put(e.getName(), e.getText());
//	            System.out.println(e.attributeValue("returnstatus")); 
//	            System.out.println(e.attributeValue("message")); 
//	            System.out.println(e.attributeValue("taskID")); 
//	            System.out.println(e.attributeValue("successCounts")); 
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	@SuppressWarnings("rawtypes")
	public static String formatXml(String str) throws Exception {
		Document document = null;
		document = DocumentHelper.parseText(str);
		System.out.println(document);
		
		
		Element root = document.getRootElement();
		System.out.println(root.attributeValue("result"));
		for(Iterator iter = root.elementIterator(); iter.hasNext();){  
            Element e = (Element)iter.next(); 
            System.out.println("======" + e.getName() + "---------" + e.getText());
        }
		
		
		// 格式化输出格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("gb2312");
		StringWriter writer = new StringWriter();
		// 格式化输出流
		XMLWriter xmlWriter = new XMLWriter(writer, format);
		// 将document写入到输出流
		xmlWriter.write(document);
		xmlWriter.close();

		return writer.toString();
	}
	
	
	public static void main(String[] args) {
		String str = "<xml name=\"sendOnce\" result=\"1\">";
		str += "<Item cid=\"xxx\" sid=\"xxx\" msgid=\"xxx\" total=\"2\" price=\"0.10\" remain=\"170.040\"/>";
		str += "</xml>";
		
		try {
			System.out.println(formatXml(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
