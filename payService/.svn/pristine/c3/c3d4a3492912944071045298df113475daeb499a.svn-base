package com.xmniao.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class MailSender {
	//邮箱服务器
	private String smtp="smtp.qq.com";
	//登陆名
	private String username="511648097@qq.com";
	//密码
	private String password="jxsfcstlzzystlk5";
	//编码方式
	private String charset="utf-8";
	//发件人邮箱
	private String fromAddress="511648097@qq.com";
	//发件人名
	private String fromName="寻蜜鸟科技技术中心";
	
	//收件人Map  key-toAddr value-toName
	private Map<String,String> toInfo = new HashMap<String,String>();
	
	public MailSender() {
		super();
		toInfo.put("shaliuchen@163.com", "");
		toInfo.put("shalivchen1988@163.com","");
	}
	
	public  Email getSimpleEmail() throws EmailException{
		SimpleEmail email = new SimpleEmail();
		email.setHostName(smtp);
		email.setAuthentication(username, password);
		email.setCharset(charset);
		email.setFrom(fromAddress,fromName,charset);
		
		String toName=null;
		for(String toAddr:toInfo.keySet()){
			toName=toInfo.get(toAddr);
			email.addTo(toAddr,toName,charset);
		}
		
		return email;
	}

	public Email getMultiPartEmail(){
		 HtmlEmail email = new HtmlEmail();
		return email;
	}
	
	public Email getHtmlEmail(){
		MultiPartEmail email = new MultiPartEmail();
		return email;
	}

	
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public void setToInfo(Map<String, String> toInfo) {
		this.toInfo = toInfo;
	}
	
	
}
