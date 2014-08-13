package com.lab410.yy.hibernate;

public class RealTimeDataModel {

	private String machineSno;//4字节长度的序列号  SN 00 00 01
	private int weaveLength;  			//织布长度
	private int weiNumber;			//纬数
	private float runEfficiency;//运转效率
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
	
	private int clearBytes; //清零操作  为1则清零
	private int workingStatus;  // 工作状态  为1则说明机器在运转

	private int id;
	private String date;
	private String time;
	private long timeinMillis;
	private String banci;   //班次 'A' 'B' 'C'
	
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
	public String getMachineSno() {
		return machineSno;
	}
	public void setMachineSno(String machineSno) {
		this.machineSno = machineSno;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	
	public void setWeaveLength(int weaveLength){
		this.weaveLength = weaveLength;
	}
	public int getWeaveLength(){
		return this.weaveLength;
	}
	
	public void setWeiNumber(int weiNumber){
		this.weiNumber = weiNumber;
	}
	public int getWeiNumber(){
		return this.weiNumber;
	}
	

	
	public float getRunEfficiency() {
		return runEfficiency;
	}
	public void setRunEfficiency(float runEfficiency) {
		this.runEfficiency = runEfficiency;
	}
	public void setRunTime(int runTime){
		this.runTime = runTime;
	}
	public int getRunTime(){
		return this.runTime;
	}
	
	public void setWholeStopNumber(int wholeStopNumber){
		this.wholeStopNumber= wholeStopNumber;
	}
	public int getWholeStopNumber(){
		return this.wholeStopNumber;
	}
	
	public void setWholeStopTime(int wholeStopTime){
		this.wholeStopTime = wholeStopTime;
	}
	public int getWholeStopTime(){
		return this.wholeStopTime;
	}
	
	public void setH1WeiTingNumber(int h1WeiTingNumber){
		this.h1WeiTingNumber = h1WeiTingNumber;
	}
	public int getH1WeiTingNumber(){
		return this.h1WeiTingNumber;
	}
	
	public void setH1WeiTingTime(int h1WeiTingTime){
		this.h1WeiTingTime = h1WeiTingTime;
	}
	public int getH1WeiTingTime(){
		return this.h1WeiTingTime;
	}
	
	public void setJingTingNumber(int jingTingNumber){
		this.jingTingNumber = jingTingNumber;
	}
	public int getJingTingNumber(){
		return this.jingTingNumber;
	}
	
	public void setJingTingTime(int jingTingTime){
		this.jingTingTime = jingTingTime;
	}
	public int getJingTingTime(){
		return this.jingTingTime;
	}
	
	public void setOtherStopNumber(int otherStopNumber){
		this.otherStopNumber = otherStopNumber;
	}
	public int getOtherStopNumber(){
		return this.otherStopNumber;
	}
	
	public void setOtherStopTime(int otherStopTime){
		this.otherStopTime = otherStopTime;
	}
	public int getOtherStopTime(){
		return this.otherStopTime;
	}
	
	public void setC1H1(int C1H1){
		this.C1H1 = C1H1;
	}
	public int getC1H1(){
		return this.C1H1;
	}
	
	public void setC1H2(int C1H2){
		this.C1H2 = C1H2;
	}
	public int getC1H2(){
		return this.C1H2;
	}
	
	public void setC2H1(int C2H1){
		this.C2H1 = C2H1;
	}
	public int getC2H1(){
		return this.C2H1;
	}

	public void setC2H2(int C2H2){
		this.C2H2 = C2H2;
	}
	public int getC2H2(){
		return this.C2H2;
	}
	
	public void setC3H1(int C3H1){
		this.C3H1 = C3H1;
	}
	public int getC3H1(){
		return this.C3H1;
	}

	public void setC3H2(int C3H2){
		this.C3H2 = C3H2;
	}
	public int getC3H2(){
		return this.C3H2;
	}

	public void setC4H1(int C4H1){
		this.C4H1 = C4H1;
	}
	public int getC4H1(){
		return this.C4H1;
	}

	public void setC4H2(int C4H2){
		this.C4H2 = C4H2;
	}
	public int getC4H2(){
		return this.C4H2;
	}
	
	public void setTingJingPian(int tingJingPian){
		this.tingJingPian = tingJingPian;
	}
	public int getTingJingPian(){
		return this.tingJingPian;
	}
	
	public void setLeftTwistFlat(int leftTwistFlat){
		this.leftTwistFlat = leftTwistFlat;
	}
	public int getLeftTwistFlat(){
		return this.leftTwistFlat;
	}
	
	public void setRightTiwistFlat(int rightTiwistFlat){
		this.rightTiwistFlat = rightTiwistFlat;
	}
	public int getRightTiwistFlat(){
		return this.rightTiwistFlat;
	}
	
	public void setFeiBianSha(int feiBianSha){
		this.feiBianSha = feiBianSha;
	}
	public int getFeiBianSha(){
		return this.feiBianSha;
	}
	
	public void setChuWeiQi(int chuWeiQi){
		this.chuWeiQi = chuWeiQi;
	}
	public int getChuWeiQi(){
		return this.chuWeiQi;
	}
	
	public void setClearBytes(int clearBytes){
		this.clearBytes = clearBytes;
	}
	public int getClearBytes(){
		return this.clearBytes;
	}
	
	public void setWorkingStatus(int workingStatus){
		this.workingStatus = workingStatus;
	}
	public int getWorkingStatus(){
		return this.workingStatus;
	}
	
	
	public void setBanci(String banci){
		this.banci = banci;
	}
	public String getBanci(){
		return this.banci;
	}
}
