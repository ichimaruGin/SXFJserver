package com.lab410.yy.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.lab410.yy.codec.MyProtocolCodecFactory;
import com.lab410.yy.para.JParameter;

public class Server {
	
	private static final Logger logger = Logger.getLogger(Server.class);

	public static void start(int port) throws IOException{
		   IoAcceptor ioAcceptor = new NioSocketAcceptor();
		   //acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		   ioAcceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new MyProtocolCodecFactory(Charset.forName("UTF-8"))));
		   ioAcceptor.setHandler(new ServerIoHandler());
		   ioAcceptor.getSessionConfig().setReadBufferSize(2048);
		   ioAcceptor.getSessionConfig().setReaderIdleTime(2*60*10);//	2次读数据取不到，就认为是超时
		   ioAcceptor.getSessionConfig().setWriterIdleTime(60*10);//10分钟
		   ioAcceptor.bind(new InetSocketAddress(port));
		   
	}
	
	public static void loadBanciProperties(){
		File f = new File("banci.properties");
		if(f.exists()){
			try {
				InputStream is = new FileInputStream(f);
				Properties properties = new Properties();
				properties.load(is);
				Set<String> keys = properties.stringPropertyNames();
				Iterator<?> i = keys.iterator();
				System.out.println("----------实际文件:banci.properties-------------");
				while(i.hasNext()){
					String keyName = (String)i.next();
					System.out.println(keyName+" = "+properties.getProperty( keyName));
				}
				System.out.println("----------最后得到：banci.properties-------------");
				JParameter.Astart = Integer.parseInt(properties.getProperty("Astart", "8"));
				JParameter.Aend = Integer.parseInt(properties.getProperty("Aend", "16"));
				JParameter.Bstart = Integer.parseInt(properties.getProperty("Bstart", "16"));
				JParameter.Bend = Integer.parseInt(properties.getProperty("Bend", "0"));
				JParameter.Cstart = Integer.parseInt(properties.getProperty("Cstart", "0"));
				JParameter.Cend = Integer.parseInt(properties.getProperty("Cend", "8"));
				JParameter.requestInterval = Integer.parseInt(properties.getProperty("requestInterval", "1"));
				JParameter.clearHour = Integer.parseInt(properties.getProperty("clearHour","23"));
				System.out.println("Astart:"+JParameter.Astart+"\nAend:"+JParameter.Aend+"\nBstart:"+JParameter.Bstart+"\nBend:"+JParameter.Bend+"\nCstart:"+JParameter.Cstart+"\nCend:"+JParameter.Cend+"\nrequestInterval:"+JParameter.requestInterval+"\nclearHour:"+JParameter.clearHour);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("banci.properties not found");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("No such file: banci.properties");
		}
	}
	
	public static void main(String[] args){
		loadBanciProperties();
		new DbInsertThread().start();
		try {
			Server.start(6668);
			System.out.println("Server start! @ "+new java.sql.Timestamp(System.currentTimeMillis()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Server start error");
		}
	}
}
