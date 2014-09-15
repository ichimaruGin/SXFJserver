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
	private static final int[] ports = {6660,6661,6662,6663,6664,6665,6666,6667,6668};
	//private static final int[] ports = {6668};
	public static void start(int[] ports) throws IOException{
		   IoAcceptor ioAcceptor = new NioSocketAcceptor();
		   //acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		   ioAcceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new MyProtocolCodecFactory(Charset.forName("UTF-8"))));
		   ioAcceptor.setHandler(new ServerIoHandler());
		   ioAcceptor.getSessionConfig().setReadBufferSize(2048);
		   ioAcceptor.getSessionConfig().setReaderIdleTime(2*60*10);//	2次读数据取不到，就认为是超时
		   ioAcceptor.getSessionConfig().setWriterIdleTime(60*10);//10分钟
		   for(int port : ports){
			   ioAcceptor.bind(new InetSocketAddress(port));
			   logger.info("bind at port "+port);
		   }
	}

	
	public static void main(String[] args){
		try {
			loadBanciProperties();
			new DbInsertThread().start();
			Server.start(ports);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		System.out.println("Server start! @ "+new java.sql.Timestamp(System.currentTimeMillis()));
	}
	
	
	public static void loadBanciProperties(){
		File f = new File("banci.properties");
		if(f.exists()){
			try {
				InputStream is = new FileInputStream(f);
				Properties prop = new Properties();
				prop.load(is);
				Set<String> keys = prop.stringPropertyNames();
				Iterator<?> i = keys.iterator();
				System.out.println("----------banci.properties-------------");
				JParameter.Astart = Integer.parseInt(prop.getProperty("Astart", "8"));
				JParameter.Aend = Integer.parseInt(prop.getProperty("Aend", "16"));
				JParameter.Bstart = Integer.parseInt(prop.getProperty("Bstart", "16"));
				JParameter.Bend = Integer.parseInt(prop.getProperty("Bend", "0"));
				JParameter.Cstart = Integer.parseInt(prop.getProperty("Cstart", "0"));
				JParameter.Cend = Integer.parseInt(prop.getProperty("Cend", "8"));
				JParameter.requestInterval = Integer.parseInt(prop.getProperty("requestInterval", "1"));
				JParameter.clearHour = Integer.parseInt(prop.getProperty("clearHour","23"));
				JParameter.clearMinute = Integer.parseInt(prop.getProperty("clearMinute","0"));
				JParameter.clearSecond = Integer.parseInt(prop.getProperty("clearSecond", "0"));
				JParameter.clearPeriod = Integer.parseInt(prop.getProperty("clearPeriod","86400000"));
				System.out.println("清零时刻："+JParameter.clearHour+":"+JParameter.clearMinute+":"+JParameter.clearSecond+"  间隔:"+JParameter.clearPeriod/1000+"秒");
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
}
