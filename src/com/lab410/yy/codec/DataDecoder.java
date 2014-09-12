package com.lab410.yy.codec;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.lab410.yy.datautil.DataParser;
import com.lab410.yy.datautil.Record;
import com.lab410.yy.para.JParameter;

public class DataDecoder extends CumulativeProtocolDecoder{
	
	//private static Logger logger = Logger.getLogger(DataDecoder.class);
	private List<IoSession> sessionList = new ArrayList<>();
	private static final int frame_length = 236;
	private static final int frame_verify_length = 20;
	private Charset charset;
	public DataDecoder(Charset charset){
		this.charset = charset;
	}
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		//in默认容量为2048
		if(in.limit()>=frame_verify_length || in.limit() >= frame_length){
			String frame = trimAll(in.getString(Charset.forName("utf-8").newDecoder()));
			System.out.println(frame);
			if(isValid(frame)){
				if("0001".equals(frame.substring(4, 8))){
					//数据
					Record record = DataParser.parseRecord(frame.substring(8, 232));
					out.write(record);
				}else if("0002".equals(frame.substring(4, 8))){
					//连接建立,发送第一帧数据请求命令
					session.setAttribute("machineSno", frame.substring(8, 16));
					checkSessionList(session, frame.substring(8, 16));
					session.write(JParameter.DATA_REQUEST);
				}else if("0003".equals(frame.substring(4,8))){
					session.write(JParameter.DATA_HEART_BEAT);
				}
			}else{
				System.out.println("非法数据帧头尾");
				session.close(true);
			}
			return true;
		}else{
			System.out.println("非法长度数据");
			session.close(true);
		}
		return true;
		//return false; 事实上，如果一次采集的数据不满足长度要求，那么，就直接舍弃这组数据(这个协议解码器就是这么处理的)，即返回true;如果想保留这组数据，让它和下一次来的数据一起再次被读进来，则返回false
	}
	
	/**
	 * @return 检查帧头帧尾
	 * **/
	private boolean isValid(String frame){
		if((frame.startsWith("5a5a")||frame.startsWith("5A5A"))&&(frame.endsWith("5c5c")||frame.endsWith("5C5C")))
			return true;
		else
			return false;
	}
	/**
	 * @return 去除所有空格号
	 * **/
	private String trimAll(String frame){
		return frame.replaceAll("\\s", "");
	}

	public static String replaceBlank(String str) {
	        String dest = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\\s*|\n|\r|\t");  
	            Matcher m = p.matcher(str);
	            dest = m.replaceAll("");
	        }
	    return dest;
	}
	/**
	 * @param currentSession 传入当前的会话
	 * @param machineSno  传入当前机器编号
	 * **/
	private void checkSessionList(IoSession currentSession,String machineSno){
		if(sessionList.size() == 0)
			;
		else{
			Iterator<IoSession> sessionIter = sessionList.iterator();
			IoSession session = null;
			while(sessionIter.hasNext()){
				session = sessionIter.next();
				if(session.getAttribute("machineSno").equals(machineSno)){				
					if(!session.isClosing() || session.isConnected()){
						session.close(true);//如果旧会话未关闭，则关闭旧会话
						System.out.println("关闭机器连接,型号为："+machineSno);
					}
					sessionIter.remove();
					break;
				}
			}
		}
		sessionList.add(currentSession);
		System.out.println("新增机器连接,型号为："+machineSno);
	}
}

