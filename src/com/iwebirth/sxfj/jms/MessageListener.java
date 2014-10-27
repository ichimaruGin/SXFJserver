package com.iwebirth.sxfj.jms;

import java.io.Serializable;

public class MessageListener {
	public void handleMessage(String msg){
		System.out.println("in MessageListener@String="+msg);
	}
	public void handleMessage(Serializable obj){
		System.out.println("in MessageListener@Serializable");
	}
}
