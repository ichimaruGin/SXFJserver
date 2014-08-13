package com.lab410.yy.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.lab410.yy.para.JParameter;

public class ClientIoHandler extends IoHandlerAdapter{

	public ClientIoHandler() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		String msg = (String)message;
		System.out.println(msg+"  "+new java.sql.Timestamp(System.currentTimeMillis()));
		if(msg.equals("DATA_CONNECT_OK"))
			session.write(JParameter.frame_verify);
		else if(msg.equals("DATA_REQUEST"))
			session.write(JParameter.new_frame1);
		else if(msg.equals("DATA_CLEAR"))
			session.write(JParameter.new_frame1_clear);
		else if(msg.equals("DATA_RECEIVE_TIMEOUT"))
			session.write(JParameter.new_frame1);
		super.messageReceived(session, message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SENT:"+(String)message);
		super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
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
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
	}

}
