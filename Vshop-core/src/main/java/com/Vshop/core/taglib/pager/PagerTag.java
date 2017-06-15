package com.Vshop.core.taglib.pager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.MessageFormat;

public class PagerTag  extends TagSupport {

    private String formId;
    private int curPage;
    private int totalPages;
    /**
     * 标签开始执行，该标签没有标签体
     */
    @Override
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    /**
     * 向页面输出每一页的数字连接
     * @param object
     */
    private void printLink(String...object) {
        String linkString = "<a href={0}?page={1}>{2}</a>";
        String divPageString = MessageFormat.format(linkString, object);
        try {
            pageContext.getOut().write(divPageString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修饰输出
     * @param stringModify
     */
    public void printModify(String stringModify){
        try{
            pageContext.getOut().write(stringModify);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
