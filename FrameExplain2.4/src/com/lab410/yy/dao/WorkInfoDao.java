package com.lab410.yy.dao;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lab410.yy.datautil.Record;
import com.lab410.yy.hibernate.Hibernate;
import com.lab410.yy.hibernate.RealTimeDataModel;
import com.lab410.yy.para.JParameter;

public class WorkInfoDao {
	static DecimalFormat df = new DecimalFormat("#.0");
	private WorkInfoDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static void InsertToDb(List<Record> recordList){
		Session session = Hibernate.currentSession();
		Transaction tx = session.beginTransaction();
		if(recordList.size() > 0){
			int i = 0;
			for(Record record : recordList){
				RealTimeDataModel realtimeData = new RealTimeDataModel();
				realtimeData.setMachineSno(record.getMachineSno());//拼接字符串
				realtimeData.setWeaveLength(record.getWeaveLength());
				realtimeData.setWeiNumber(record.getWeiNumber());
				if(record.getRunTime()==0 || (record.getRunTime()+record.getWholeStopTime()==0)){
					realtimeData.setRunEfficiency(0.0f);
				}else{
					float res = Float.parseFloat(df.format(100*(float)(record.getRunTime())/(record.getRunTime()+record.getWholeStopTime())));
					System.out.println(res);
					realtimeData.setRunEfficiency(res);
				}
				realtimeData.setRunTime(record.getRunTime());
				realtimeData.setWholeStopNumber(record.getWholeStopNumber());
				realtimeData.setWholeStopTime(record.getWholeStopTime());
				realtimeData.setH1WeiTingNumber(record.getH1WeiTingNumber());
				realtimeData.setH1WeiTingTime(record.getH1WeiTingTime());
				realtimeData.setJingTingNumber(record.getJingTingNumber());
				realtimeData.setJingTingTime(record.getJingTingTime());
				realtimeData.setOtherStopNumber(record.getOtherStopNumber());
				realtimeData.setOtherStopTime(record.getOtherStopTime());
				realtimeData.setC1H1(record.getC1H1());
				realtimeData.setC1H2(record.getC1H2());
				realtimeData.setC2H1(record.getC2H1());
				realtimeData.setC2H2(record.getC2H2());
				realtimeData.setC3H1(record.getC3H1());
				realtimeData.setC3H2(record.getC3H2());
				realtimeData.setC4H1(record.getC4H1());
				realtimeData.setC4H2(record.getC4H2());
				realtimeData.setTingJingPian(record.getTingJingPian());
				realtimeData.setLeftTwistFlat(record.getLeftTwistFlat());
				realtimeData.setRightTiwistFlat(record.getRightTiwistFlat());
				realtimeData.setFeiBianSha(record.getFeiBianSha());
				realtimeData.setChuWeiQi(record.getChuWeiQi());
				realtimeData.setWorkingStatus(record.getWorkingStatus());
				realtimeData.setClearBytes(record.getClearBytes());
				realtimeData.setDate(record.getDate());
				realtimeData.setTime(record.getTime());
				realtimeData.setBanci(getBanciAccordTime(record.getTimeinMillis()));//这位暂时保留。置为none
				realtimeData.setTimeinMillis(record.getTimeinMillis());
				session.save(realtimeData);
				realtimeData = null;
				i++;
				if(i % 20 == 0){  
					session.flush();
					session.clear();
				}				
			}
		}else{
			
		}
		tx.commit();
		Hibernate.closeSession();
	}
	
	private  static String getBanciAccordTime(long timeinMillis){
		String c = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeinMillis);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(hour >= JParameter.Astart && hour <JParameter.Aend){   //一般情况
				c = "A";
		}else if(hour >=JParameter.Bstart && hour <JParameter.Bend){
				c = "B";
		}else{
				c = "C";   //约定A班B班不会跨越0点
		}
		return c;
	}
}
