package com.iwebirth.sxfj.jms;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component("messageSender")
public class MessageSender {
	@Autowired
	private JmsTemplate jmsTemplate;
	public void send(final String s){
		jmsTemplate.send(new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				Message m = session.createTextMessage(s);
				return m;
			}
		});
	}
	public void send(final Serializable obj){
		jmsTemplate.send(new MessageCreator() {			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// 可以设置消息的各种参数，例如优先级、过期时间等
				Message m = session.createObjectMessage(obj);
				return m;
			}
		});
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate){
		this.jmsTemplate = jmsTemplate;
	}
	public JmsTemplate getJmsTemplate(){
		return this.jmsTemplate;
	}
}
