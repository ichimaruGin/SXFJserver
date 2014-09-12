package com.lab410.yy.server;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.mina.core.session.IoSession;

import com.lab410.yy.para.JParameter;

public class ClearTimerManager {
	private IoSession session;
	private static Timer timer = new Timer();  //一个Timer对象即可，故采用static
	public ClearTimerManager(IoSession session){
		this.session = session;
	}
	public void start(){
		Calendar c =Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY,JParameter.clearHour);
		c.set(Calendar.MINUTE,JParameter.clearMinute);
		c.set(Calendar.SECOND,JParameter.clearSecond);
		Date startDate = c.getTime();
		if(startDate.before(new Date())){
			//定的时刻在当前时刻之前，timer会立刻启动
			//因此需要将定的时刻推迟一段时间(如果是一天一更就增加24小时)
			startDate = addTime(JParameter.clearPeriod, startDate);
		}
		timer.schedule(new ClearTimerTask(), startDate, JParameter.clearPeriod);
	}
	public  class ClearTimerTask extends TimerTask{

		@Override
		public void run() {
			if(!session.isConnected()){
				cancel();
				System.out.println("关闭task: sessionId--->"+session.getId());
			}
			session.write(JParameter.DATA_CLEAR);  //请求清零
		}
		
	}
	/**
	 * @param amount 增加的天数
	 * @param startDate  起始时刻
	 * **/
	private Date addDay(int amount,Date startDate){
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DAY_OF_MONTH, amount);
		return c.getTime();
	}
	/**
	 * @param amountInMillis 增加的毫秒数
	 * @param startDate 起始时刻
	 * **/
	private Date addTime(long amountInMillis,Date startDate){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(startDate.getTime()+amountInMillis);
		return c.getTime();
	}
}
