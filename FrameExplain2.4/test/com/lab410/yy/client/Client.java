package com.lab410.yy.client;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.lab410.yy.clientcodec.ClientProtocolCodecFactory;

public class Client {

//	private IoSession session = null;
	
	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	public  void ClientInit(SocketAddress socketAddress){
		IoConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ClientProtocolCodecFactory(Charset.forName("UTF-8"))));
		connector.setHandler(new ClientIoHandler());
		connector.setConnectTimeoutMillis(20*1000);//20s超时
		ConnectFuture cf = connector.connect(socketAddress);
//		cf.awaitUninterruptibly();
//		session = cf.getSession();
	}
	
	public static void main(String[] args){
		SocketAddress socketAddress = new InetSocketAddress("115.239.182.18", 6668);
		//SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6668);
		Client client = new Client();
		client.ClientInit(socketAddress);
	}
}
