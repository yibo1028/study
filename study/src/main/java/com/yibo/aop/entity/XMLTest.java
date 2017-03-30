package com.yibo.aop.entity;

import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 类说明
 * 
 * @author Yibo Liu
 * @version 2017年3月27日
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "XMLTest")
public class XMLTest implements Serializable {

	private static final long serialVersionUID = 641996587236478146L;
	private String mailTo;
	private String mailSubject;
	private String mailContent;
	private String[] ccTo;

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String[] getCcTo() {
		return ccTo;
	}

	public void setCcTo(String[] ccTo) {
		this.ccTo = ccTo;
	}
/*
	<?xml version="1.0" encoding="utf-8" standalone="yes"?>
	<XMLTest>
	 <mailTo>mailTo</mailTo>
	    <mailSubject>邮件主题</mailSubject>
	    <mailContent>邮件内容</mailContent>
	    <ccTo>aa</ccTo>
	    <ccTo>bb</ccTo>
	    <ccTo>cc</ccTo>
	</XMLTest>
	*/
	public static void test() {
		try {
			JAXBContext jc = JAXBContext.newInstance(XMLTest.class);
			Marshaller ms = jc.createMarshaller();
			XMLTest st = new XMLTest();
			st.setMailContent("邮件内容");
			st.setMailSubject("邮件主题");
			st.setMailTo("mailTo");
			st.setCcTo(new String[]{"aa", "bb", "cc"});
			StringWriter writer = new StringWriter();
			ms.marshal(st, writer);
			String result = writer.toString();
			System.out.println("对象转XML字符串：\n " + result + "\n");
			System.out.println("---------------------------------------- \n");
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			XMLTest _XMLTest = (XMLTest) unmarshaller.unmarshal(new StringReader(result));

			System.out.println("翻转XML为对象：\n" + _XMLTest.getMailContent());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		test();
	}

}
