package com.Vshop.front.filter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
  
  
/**  
 * @author winnie  
 * @date   
 * @describe request信息封装类，用于判断、处理request请求中特殊字符  
 */  
public class XSSHttpRequestWrapper extends HttpServletRequestWrapper {   

	private static Logger log = Logger.getLogger(XSSHttpRequestWrapper.class);
      
   /**  
    * 封装http请求  
    * @param request  
    */  
   public XSSHttpRequestWrapper(HttpServletRequest request) {   
       super(request);   
   }   
      
   @Override  
   public String getHeader(String name) {   
       String value = super.getHeader(name);   
       // 若开启特殊字符替换，对特殊字符进行替换 ,与Fckeditor冲突  
//       if(XSSSecurityConfig.REPLACE&&!XSSSecurityManager.isNullStr(value)){  
//    	   
//    	   return stringFilter(value);   
//       }   
       return value;   
   }   
 
   @Override 
   public String getParameter(String name) {  
       String value = super.getParameter(name);  
       // 若开启特殊字符替换，对特殊字符进行替换   
       if(XSSSecurityConfig.REPLACE&&!XSSSecurityManager.isNullStr(value)){   
           return stringFilter(value);   
       }   
       return value;   
   }   
 
   /**  
    * 没有违规的数据，就返回false;  
    *   
    * @return  
    */  
   @SuppressWarnings("unchecked")   
   private boolean checkHeader(){   
       Enumeration<String> headerParams = this.getHeaderNames();   
       while(headerParams.hasMoreElements()){   
           String headerName = headerParams.nextElement();   
           String headerValue = this.getHeader(headerName);   
           if(XSSSecurityManager.matches(headerValue)){   
               return true;   
           }   
       }   
       return false;   
   }   
      
   /**  
    * 没有违规的数据，就返回false;  
    *   
    * @return  
    */  
   @SuppressWarnings("unchecked")   
   private boolean checkParameter(){   
      Map<String,String[]> submitParams = this.getParameterMap();   
       Set<String> submitNames = submitParams.keySet();   
       for(String submitName : submitNames){   
    	   String[] submitValues = submitParams.get(submitName);
           for(String submitValue : submitValues){
        	   try {
	               log.debug(submitName+":"+submitValue+"----"+XSSSecurityManager.matches(submitValue));
	               if(XSSSecurityManager.matches(submitValue)){   
	                   return true;   
	               } 
        		} catch (Exception e) {
        			log.info("地址解码异常:"+submitValue);
        			return false;
        		}
           }  
       }
       return false;   
   }   
      
     
   /**  
    * 没有违规的数据，就返回false;  
    * 若存在违规数据，根据配置信息判断是否跳转到错误页面  
    * @param response  
    * @return  
    * @throws IOException   
     * @throws ServletException   
     */  
    public boolean validateParameter(HttpServletResponse response) throws ServletException, IOException{   
        // 开始header校验，对header信息进行校验   
        if(XSSSecurityConfig.IS_CHECK_HEADER){   
            if(this.checkHeader()){   
                return true;   
            }   
        }   
        // 开始parameter校验，对parameter信息进行校验   
        if(XSSSecurityConfig.IS_CHECK_PARAMETER){   
            if(this.checkParameter()){   
                return true;   
            }   
        }   
        return false;   
    }

	 //过滤通过页面表单提交的字符
   private static String[][] FilterChars={{"-",""},{"<","《"},{">","》"},{" ",""},{"'",""},{"\r"," "},{"\n"," "},{"\r\n"," "},{"''",""},{"&",""},{"/",""},{"\\","/"},{"\n","<br>"}};

   //过滤通过javascript脚本处理并提交的字符  
   private static String[][] FilterScriptChars={{"\n","\'+\'\\n\'+\'"},{"\r"," "},{"\\","\'+\'\\\\\'+\'"}, {"\'","\'+\'\\\'\'+\'"}};  

   /** 
    * 用特殊的字符连接字符串 
    * @param strings 要连接的字符串数组 
    * @param spilit_sign 连接字符 
    * @return 连接字符串 
    */  
   public static String stringConnect(String[] strings,String spilit_sign){  
     String str="";  
     for(int i=0;i<strings.length;i++){  
       str+=strings[i]+spilit_sign;  
     }  
     return str;  
   }  

   /** 
    * 过滤字符串里的的特殊字符 
    * @param str 要过滤的字符串 
    * @return 过滤后的字符串 
    */  
	public static String stringFilter(String str) {
		String temp = StringUtils.replace(str, "%27", "");
		temp = StringUtils.replace(temp, "*", "");
		temp = StringUtils.replace(temp, "\"", "&quot;");
		temp = StringUtils.replace(temp, "'", "");
		temp = StringUtils.replace(temp, "\\\"", "");
		temp = StringUtils.replace(temp, ";", "");
		temp = StringUtils.replace(temp, "<", "&lt;");
		temp = StringUtils.replace(temp, ">", "&gt;");
		temp = StringUtils.replace(temp, "(", "");
		temp = StringUtils.replace(temp, ")", "");
		temp = StringUtils.replace(temp, "{", "");
		temp = StringUtils.replace(temp, "}", "");
		return temp.trim();
	}
   /** 
	* 过滤脚本中的特殊字符（包括回车符(\n)和换行符(\r)） 
	* @param str 要进行过滤的字符串 
	* @return 过滤后的字符串 
	*
	*/  
	public static String stringFilterScriptChar(String str){  
		String[] str_arr=stringSpilit(str,"");  
		for(int i=0;i<str_arr.length;i++){  
			for (int j = 0; j < FilterScriptChars.length; j++) {  
			  if (FilterScriptChars[j][0].equals(str_arr[i]))  
			    str_arr[i] = FilterScriptChars[j][1];  
			}  
		}  
		return(stringConnect(str_arr,"")).trim();  
	}  


   /** 
    * 分割字符串 
    * @param str 要分割的字符串 
    * @param spilit_sign 字符串的分割标志 
    * @return 分割后得到的字符串数组 
    */  
   public static String[] stringSpilit(String str,String spilit_sign){  
     String[] spilit_string=str.split(spilit_sign);  
     if(spilit_string[0].equals(""))  
     {  
       String[] new_string=new String[spilit_string.length-1];  
       for(int i=1;i<spilit_string.length;i++)  
         new_string[i-1]=spilit_string[i];  
         return new_string;  
     }  
     else  
       return spilit_string;  
   }  
   
   public static void main(String[] args) {
	   System.out.println(stringFilter("'sjhdfkj;lqwhj82dkjs%6982%^&28121//sdh\\%"));
   }
}  