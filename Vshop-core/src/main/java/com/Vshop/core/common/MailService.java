package com.Vshop.core.common;


import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MailService {
    private static Logger logger = LoggerFactory.getLogger(MailService.class);
    private static final String DEFAULT_ENCODING = "utf-8";
    private Template template;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private Configuration mailFreemarkerConfiguration;

    /**
     * 发送html邮件.
     */
    public void sendMailHtml(String[] toEmail,String subject,Map map,String ftlName) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail,DEFAULT_ENCODING);
            helper.setTo(toEmail); // 发送给谁
            helper.setSubject(subject); // 标题
            helper.setFrom(mailSender.getUsername()); // 来自
            String content = generateContent(map,ftlName);
            // 邮件内容，第二个参数指定发送的是HTML格式
            helper.setText(content, true);
            mailSender.send(mail); // 发送
        } catch (MessagingException e) {
            logger.error("构造邮件失败", e);
        } catch (Exception e) {
            logger.error("发送邮件失败", e);
        }
    }
    /**
     * 发送html邮件.
     */
    public void sendMailHtml(String toEmail,String subject,Map map,String ftlName) {
        sendMailHtml(new String[]{toEmail}, subject, map, ftlName);
    }

    /**
     * 使用Freemarker生成html格式内容.
     */
    private String generateContent(Map map,String ftlName) throws MessagingException {

        try {
            template = mailFreemarkerConfiguration.getTemplate(ftlName, DEFAULT_ENCODING);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException e) {
            logger.error("生成邮件内容失败, FreeMarker模板不存在", e);
            throw new MessagingException("FreeMarker模板不存在", e);
        } catch (TemplateException e) {
            logger.error("生成邮件内容失败, FreeMarker处理失败", e);
            throw new MessagingException("FreeMarker处理失败", e);
        }
    }

}
