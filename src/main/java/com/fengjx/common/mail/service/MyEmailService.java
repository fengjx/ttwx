package com.fengjx.common.mail.service;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.fengjx.common.system.exception.MyRuntimeException;
import com.fengjx.common.mail.vo.SendMailVo;

/**
 * 邮件发送服务
 * 
 * @author fengjx xd-fjx@qq.com
 * @version MyEmailService.java 2014年9月27日
 */
public class MyEmailService {

	@Autowired
	private JavaMailSenderImpl sender;

	public void send(SendMailVo mail)
			throws MessagingException, UnsupportedEncodingException {
		if (SendMailVo.TYPE_TEXT.equals(mail.getType())) {
			sendTextMail(mail);
		} else if (SendMailVo.TYPE_HTML.equals(mail.getType())) {
			sendHtmlMail(mail);
		} else if (SendMailVo.TYPE_ATTR.equals(mail.getType())) {
			sendAttrMail(mail);
		} else {
			throw new MyRuntimeException("邮件类型未确定");
		}
	}

	/**
	 * 发送存文本邮件
	 * 
	 * @param mail
	 */
	private void sendTextMail(SendMailVo mail) {
		// 注意SimpleMailMessage只能用来发送text格式的邮件</span>
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(mail.getToUser());// 接受者
		simpleMailMessage.setFrom(sender.getUsername());
		simpleMailMessage.setSubject(mail.getSubject());// 主题
		simpleMailMessage.setText(mail.getContent());// 邮件内容
		sender.send(simpleMailMessage);
	}

	/**
	 * 发送html格式的邮件
	 * 
	 * @param mail
	 * @throws MessagingException
	 */
	private void sendHtmlMail(SendMailVo mail)	throws MessagingException {
		MimeMessage mailMessage = sender.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true);
		messageHelper.setTo(mail.getToUser());// 接受者
		messageHelper.setFrom(sender.getUsername());
		messageHelper.setSubject(mail.getSubject());// 主题
		// 邮件内容，注意加参数true，表示启用html格式
		messageHelper.setText(mail.getContent(), true);
		sender.send(mailMessage);
	}

	/**
	 * 发送带附件的邮件
	 * 
	 * @param mail
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	private void sendAttrMail(SendMailVo mail) throws MessagingException, UnsupportedEncodingException {
		MimeMessage mailMessage = sender.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true);
		messageHelper.setTo(mail.getToUser());// 接受者
		messageHelper.setFrom(sender.getUsername());
		messageHelper.setSubject(mail.getSubject());// 主题
		// 邮件内容，注意加参数true，表示启用html格式
		messageHelper.setText(mail.getContent(),true);
		// 附件内容
		//messageHelper.addInline("a", new File("E:/xiezi.jpg"));
		// 这里的方法调用和插入图片是不同的，使用MimeUtility.encodeWord()来解决附件名称的中文问题
		if(!mail.getAttrFiles().isEmpty()){
			for(File file : mail.getAttrFiles()){
				messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()),file);
			}
		}
		sender.send(mailMessage);
	}

}
