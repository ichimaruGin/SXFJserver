package com.lab410.yy.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

import org.apache.log4j.*;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.lab410.yy.datautil.CopyOnReadRecordList;
import com.lab410.yy.datautil.Record;
import com.lab410.yy.para.JParameter;


public class ServerIoHandler extends IoHandlerAdapter{
	
	private Logger logger = Logger.getLogger(ServerIoHandler.class);
	//private ArrayList<IoSession> clientList = new ArrayList<>();
	private int sessionCount = 0;
	ThreadLocal<Calendar> calendars = new ThreadLocal<Calendar>();  //用ThreadLocal为每个连接维护一个日历
	@Override
	public void exceptionCaught(IoSession session, Throwable e)
			throws Exception {
		// TODO Auto-generated method stub
		//super.exceptionCaught(session, e);
		logger.error("session@id="+session.getId()+"异常,强制断开连接\n异常为："+e.toString()+":"+e.getMessage());
		session.close(true);		
	}
	
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		CopyOnReadRecordList.getInstance().add((Record)message);
		super.messageReceived(session, message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("send to sessionid="+session.getId()+"  :"+(String)message);
		super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		logger.info("sessionClosed---"+"session_id:"+session.getId()+"RemoteClientIp:"+session.getRemoteAddress()+" 持续时间:"
		+(System.currentTimeMillis()-session.getCreationTime())/1000f+"秒");
		logger.info("客户端断开,当前客户端数量为："+(--sessionCount));
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionCreated(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub	
		//session.write(JParameter.REQUEST_RECONNECT); //最后请求重连 然后server会主动断开与客户端的连接
//		if(status == IdleStatus.READER_IDLE){
//			System.out.println("读超时");//认为链接已断开
//			session.close(true);
//		}
//		else if(status == IdleStatus.WRITER_IDLE){
//			System.out.println("写超时");
//			Calendar c = calendars.get();//取出当前线程维护的Calendar对象
//			c.setTimeInMillis(System.currentTimeMillis());
//			int hour = c.get(Calendar.HOUR_OF_DAY);
//			int minute = c.get(Calendar.MINUTE);
//			if((hour == JParameter.Aend-1 || hour == JParameter.Bend-1 || hour == JParameter.Cend-1) && minute >=(60-JParameter.requestInterval)){  
//				session.write(JParameter.DATA_CLEAR);  //请求清零
//				System.out.println("清零操作sessionid=:"+session.getId()+"时间@"+new java.sql.Time(System.currentTimeMillis()));
//			}
//			/********去掉主动请求*******/
//			else{
//				session.write(JParameter.DATA_REQUEST);//请求数据	
//				System.out.println("请求数据@sessionid="+session.getId()+"时间@"+new java.sql.Time(System.currentTimeMillis()));
//			}
//		}					
		super.sessionIdle(session, status);
	}
	/***
	 * 利用session中的ip来去除clientList
	 * 
	 * **/
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		logger.info("sessionOpened---"+"sessionId:"+session.getId()+" ip:"+session.getRemoteAddress());
		System.out.print("连接建立倒计时:");
		for(int i=5;i>=0;i--){
			System.out.print(i+" ");
			Thread.sleep(1000L);//休眠1s
		}
		System.out.println("");
		session.write(JParameter.DATA_CONNECT_OK);
		logger.info("新客户端加入,当前客户端数量为："+(++sessionCount));	
//		if(calendars.get() == null){  //必定为null
//			calendars.set(Calendar.getInstance());//为当前线程提供一个Calendar实例
//		}
		ClearTimerManager ctm = new ClearTimerManager(session);
		ctm.start();
		super.sessionOpened(session);
	}
	Iterator<IoSession> session_iter = null;
	/**
	 * @param 
	 * currnet_session 当前加入的会话
	 * **/
//	public void checkSession(IoSession currnet_session){
//		if(clientList.size() == 0)
//			;
//		else{
//			session_iter = clientList.iterator();
//			IoSession session = null;
//			while(session_iter.hasNext()){
//				session = session_iter.next();
//				if(session.getRemoteAddress().toString().split(":")[0].equals(currnet_session.getRemoteAddress().toString().split(":")[0])){
//					if(!session.isClosing() || session.isConnected()){
//						session.close(true);//如果旧会话未关闭，则关闭旧会话
//						System.out.println("关闭旧会话");
//					}
//					session_iter.remove();  //移除客户列表中的旧会话
//				}
//			}
//		}
//		clientList.add(currnet_session);
//	}
}
