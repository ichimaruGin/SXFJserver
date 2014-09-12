package com.lab410.yy.datautil;

import org.apache.log4j.*;
import java.sql.Date;
import java.sql.Time;

public class DataParser {
	
	
	private DataParser() {
		// TODO Auto-generated constructor stub
	}
	private static Logger logger = Logger.getLogger(DataParser.class);
	

	
	private  int hexStringToInt(String s){
	/**	int data = 0;
		for(int index=0;index<s.length();index++){
			data +=  s.codePointAt(index)*(Math.pow(16, index));
		}  **/
		return Integer.parseInt(s, 16);  //按16进制将字符串转成int   “FF” -> 255

	}
	
	@SuppressWarnings("finally")
	public static Record parseRecord(String frame){    //数据位都为16字节 占4个字符
		Record record = new Record();
		if(frame.length() % 8 !=0 || frame.length() != 224){
			logger.error("数据长度错误");
			return null;
		}
		try{
			String[] datas = new String[frame.length()/8];
			for(int i = 0;i < datas.length;i++){
				datas[i] = frame.substring(i*8, i*8+8);
			}
			record.setMachineSno(datas[0]);
			record.setWeaveLength(Integer.parseInt(datas[1], 16));
			record.setWeiNumber(Integer.parseInt(datas[2], 16));
			record.setRunEfficiency(Integer.parseInt(datas[3], 16));
			record.setRunTime(Integer.parseInt(datas[4], 16));
			record.setWholeStopNumber(Integer.parseInt(datas[5], 16));
			record.setWholeStopTime(Integer.parseInt(datas[6], 16));
			record.setH1WeiTingNumber(Integer.parseInt(datas[7], 16));
			record.setH1WeiTingTime(Integer.parseInt(datas[8], 16));
			record.setJingTingNumber(Integer.parseInt(datas[9], 16));
			record.setJingTingTime(Integer.parseInt(datas[10], 16));
			record.setOtherStopNumber(Integer.parseInt(datas[11], 16));
			record.setOtherStopTime(Integer.parseInt(datas[12], 16));
			record.setC1H1(Integer.parseInt(datas[13], 16));
			record.setC1H2(Integer.parseInt(datas[14], 16));
			record.setC2H1(Integer.parseInt(datas[15], 16));
			record.setC2H2(Integer.parseInt(datas[16], 16));
			record.setC3H1(Integer.parseInt(datas[17], 16));
			record.setC3H2(Integer.parseInt(datas[18], 16));
			record.setC4H1(Integer.parseInt(datas[19], 16));
			record.setC4H2(Integer.parseInt(datas[20], 16));
			record.setTingJingPian(Integer.parseInt(datas[21], 16));
			record.setLeftTwistFlat(Integer.parseInt(datas[22], 16));
			record.setRightTiwistFlat(Integer.parseInt(datas[23], 16));
			record.setFeiBianSha(Integer.parseInt(datas[24], 16));
			record.setChuWeiQi(Integer.parseInt(datas[25], 16));
			record.setWorkingStatus(Integer.parseInt(datas[26], 16));
			record.setClearBytes(Integer.parseInt(datas[27], 16));
			record.setDate(new Date(System.currentTimeMillis()).toString());//以到达server的时间为准
			record.setTime(new Time(System.currentTimeMillis()).toString());
			record.setTimeinMillis(System.currentTimeMillis());
		}catch(Exception e){
			logger.error(e.toString());
			record = null;
		}finally{
			return record;
		}
	}

}
