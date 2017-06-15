package com.Vshop.front.wechat.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.Vshop.front.pay.mobile.wxpay.util.HttpClientUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
public class WXMLUtil {

	public static Map<String,String> doXMLParse(String strxml) throws JDOMException, IOException {
		XStream xStream = new XStream(new DomDriver());
		xStream.registerConverter(new MapEntryConverter());
		xStream.alias("xml", Map.class);
		return (Map<String,String>) xStream.fromXML(strxml);
	}
	

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
//	public static Map doXMLParse(String strxml) throws JDOMException, IOException {
//		if(null == strxml || "".equals(strxml)) {
//			return null;
//		}
//		
//		Map m = new HashMap();
//		InputStream in = HttpClientUtil.String2Inputstream(strxml);
//		SAXBuilder builder = new SAXBuilder();
//		Document doc = builder.build(in);
//		Element root = doc.getRootElement();
//		List list = root.getChildren();
//		Iterator it = list.iterator();
//		while(it.hasNext()) {
//			Element e = (Element) it.next();
//			String k = e.getName();
//			String v = "";
//			List children = e.getChildren();
//			if(children.isEmpty()) {
//				v = e.getTextNormalize();
//			} else {
//				v = XMLUtil.getChildrenText(children);
//			}
//			
//			m.put(k, v);
//		}
//		
//		//关闭流
//		in.close();
//		
//		return m;
//	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(WXMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取xml编码字符集
	 * @param strxml
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static String getXMLEncoding(String strxml) throws JDOMException, IOException {
		InputStream in = HttpClientUtil.String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		in.close();
		return (String)doc.getProperty("encoding");
	}
	
	
	static class MapEntryConverter implements Converter {

        public boolean canConvert(Class clazz) {
            return AbstractMap.class.isAssignableFrom(clazz);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

            AbstractMap map = (AbstractMap) value;
            for (Object obj : map.entrySet()) {
                Map.Entry entry = (Map.Entry) obj;
                writer.startNode(entry.getKey().toString());
                Object val = entry.getValue();
                if ( null != val ) {
                    writer.setValue(val.toString());
                }
                writer.endNode();
            }

        }

        public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

            Map<String, String> map = new HashMap<String, String>();

            while(reader.hasMoreChildren()) {
                reader.moveDown();

                String key = reader.getNodeName(); // nodeName aka element's name
                String value = reader.getValue();
                map.put(key, value);

                reader.moveUp();
            }

            return map;
        }

    }

}
