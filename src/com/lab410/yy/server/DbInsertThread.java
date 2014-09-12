package com.lab410.yy.server;

import java.util.List;

import org.apache.log4j.Logger;

import com.lab410.yy.dao.WorkInfoDao;
import com.lab410.yy.datautil.CopyOnReadRecordList;
import com.lab410.yy.datautil.Record;

/**
*@author YY_410
*/
public class DbInsertThread extends Thread{

	public DbInsertThread() {
		// TODO Auto-generated constructor stub
	}
	private static Logger logger = Logger.getLogger(DbInsertThread.class); 
	public boolean InsertThreadTrueFlag = true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long lastInsertTime = System.currentTimeMillis();
		List<Record> recordList = null;
		while(InsertThreadTrueFlag){
			long currentTime = System.currentTimeMillis();
			if(currentTime - lastInsertTime > 10*1000){   //10秒钟查一次
				lastInsertTime = currentTime;				
				recordList = CopyOnReadRecordList.getInstance().makeSnapShot();	//doCopy，超过10秒，尝试插入数据库
			}else{
				recordList = CopyOnReadRecordList.getInstance().makeSnapShot(100); //如果10秒内出现了100个以上的数据，则也进行插入数据库操作
			}
			
			if(recordList != null && recordList.size() > 0){
				//doInsert
				System.out.println("start insertdb");
				WorkInfoDao.InsertToDb(recordList);
				lastInsertTime = currentTime;
			}
			try {
				sleep(10*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
