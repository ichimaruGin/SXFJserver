package com.lab410.yy.datautil;

/**
 * @author YY_410
 * **/

public class Record {
	
	private String machineSno;       //标识
	private int weaveLength;  			//织布长度
	private int weiNumber;			//纬数
	private float runEfficiency; //运转效率
	private int runTime;	//运转时间
	private int wholeStopNumber,wholeStopTime;		//总停次数、总停时间
	private int h1WeiTingNumber,h1WeiTingTime;		//H1纬停次数、H1纬停时间
	private int jingTingNumber,jingTingTime; 		//经停次数、经停时间	
	private int otherStopNumber,otherStopTime;		//其他停次数、其他停时间
	private int C1H1,C1H2;
	private int C2H1,C2H2;
	private int C3H1,C3H2;
	private int C4H1,C4H2;
	private int tingJingPian;					//停经片
	private int leftTwistFlat,rightTiwistFlat;	//左绞片、右绞片
	private int feiBianSha;						//废边纱
	private int chuWeiQi;						//储纬器
	private int clearBytes;
	private int workingStatus;  // 工作状态  为1则说明机器在运转
	
	private String time;
	private String date;
	private long timeinMillis;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getTimeinMillis() {
		return timeinMillis;
	}
	public void setTimeinMillis(long timeinMillis) {
		this.timeinMillis = timeinMillis;
	}

	public int getWeaveLength() {
		return weaveLength;
	}
	public void setWeaveLength(int weaveLength) {
		this.weaveLength = weaveLength;
	}
	public int getWeiNumber() {
		return weiNumber;
	}
	public void setWeiNumber(int weiNumber) {
		this.weiNumber = weiNumber;
	}

	public float getRunEfficiency() {
		return runEfficiency;
	}
	public void setRunEfficiency(float runEfficiency) {
		this.runEfficiency = runEfficiency;
	}
	public int getRunTime() {
		return runTime;
	}
	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}
	public int getWholeStopNumber() {
		return wholeStopNumber;
	}
	public void setWholeStopNumber(int wholeStopNumber) {
		this.wholeStopNumber = wholeStopNumber;
	}
	public int getWholeStopTime() {
		return wholeStopTime;
	}
	public void setWholeStopTime(int wholeStopTime) {
		this.wholeStopTime = wholeStopTime;
	}
	public int getH1WeiTingNumber() {
		return h1WeiTingNumber;
	}
	public void setH1WeiTingNumber(int h1WeiTingNumber) {
		this.h1WeiTingNumber = h1WeiTingNumber;
	}
	public int getH1WeiTingTime() {
		return h1WeiTingTime;
	}
	public void setH1WeiTingTime(int h1WeiTingTime) {
		this.h1WeiTingTime = h1WeiTingTime;
	}
	public int getJingTingNumber() {
		return jingTingNumber;
	}
	public void setJingTingNumber(int jingTingNumber) {
		this.jingTingNumber = jingTingNumber;
	}
	public int getJingTingTime() {
		return jingTingTime;
	}
	public void setJingTingTime(int jingTingTime) {
		this.jingTingTime = jingTingTime;
	}
	public int getOtherStopNumber() {
		return otherStopNumber;
	}
	public void setOtherStopNumber(int otherStopNumber) {
		this.otherStopNumber = otherStopNumber;
	}
	public int getOtherStopTime() {
		return otherStopTime;
	}
	public void setOtherStopTime(int otherStopTime) {
		this.otherStopTime = otherStopTime;
	}
	public int getC1H1() {
		return C1H1;
	}
	public void setC1H1(int c1h1) {
		C1H1 = c1h1;
	}
	public int getC1H2() {
		return C1H2;
	}
	public void setC1H2(int c1h2) {
		C1H2 = c1h2;
	}
	public int getC2H1() {
		return C2H1;
	}
	public void setC2H1(int c2h1) {
		C2H1 = c2h1;
	}
	public int getC2H2() {
		return C2H2;
	}
	public void setC2H2(int c2h2) {
		C2H2 = c2h2;
	}
	public int getC3H1() {
		return C3H1;
	}
	public void setC3H1(int c3h1) {
		C3H1 = c3h1;
	}
	public int getC3H2() {
		return C3H2;
	}
	public void setC3H2(int c3h2) {
		C3H2 = c3h2;
	}
	public int getC4H1() {
		return C4H1;
	}
	public void setC4H1(int c4h1) {
		C4H1 = c4h1;
	}
	public int getC4H2() {
		return C4H2;
	}
	public void setC4H2(int c4h2) {
		C4H2 = c4h2;
	}
	public int getTingJingPian() {
		return tingJingPian;
	}
	public void setTingJingPian(int tingJingPian) {
		this.tingJingPian = tingJingPian;
	}
	public int getLeftTwistFlat() {
		return leftTwistFlat;
	}
	public void setLeftTwistFlat(int leftTwistFlat) {
		this.leftTwistFlat = leftTwistFlat;
	}
	public int getRightTiwistFlat() {
		return rightTiwistFlat;
	}
	public void setRightTiwistFlat(int rightTiwistFlat) {
		this.rightTiwistFlat = rightTiwistFlat;
	}
	public int getFeiBianSha() {
		return feiBianSha;
	}
	public void setFeiBianSha(int feiBianSha) {
		this.feiBianSha = feiBianSha;
	}
	public int getChuWeiQi() {
		return chuWeiQi;
	}
	public void setChuWeiQi(int chuWeiQi) {
		this.chuWeiQi = chuWeiQi;
	}
	public int getWorkingStatus() {
		return workingStatus;
	}
	public void setWorkingStatus(int workingStatus) {
		this.workingStatus = workingStatus;
	}

	public void setClearBytes(int clearBytes){
		this.clearBytes = clearBytes;
	}
	public int getClearBytes(){
		return this.clearBytes;
	}
	public String getMachineSno() {
		return machineSno;
	}
	public void setMachineSno(String machineSno) {
		this.machineSno = machineSno;
	}
	

}