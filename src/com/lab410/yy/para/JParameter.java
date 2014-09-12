package com.lab410.yy.para;

public class JParameter {
	/**
	 * 数据传输相关码
	 * */
	public static final String DATA_CONNECT_OK = "DATA_CONNECT_OK";
	public static final String DATA_CLEAR = "DATA_CLEAR";
	public static final String DATA_REQUEST = "DATA_REQUEST";
	public static final String DATA_HEART_BEAT = "Link_Con";
	
	/**
	 * 班次相关信息 非固定变量
	 * 在server启动时候会加载一次
	 * */
	public static int Cstart = 0;
	public static int Cend = 8;	
	public static int Astart = 8;
	public static int Aend = 16;
	public static int Bstart = 16;
	public static int Bend = 0;
	public static int requestInterval = 10;//分钟
	public static int clearHour = 23;//时	
	public static int clearMinute = 23;//时
	public static int clearSecond = 23;//时
	public static long clearPeriod = 24*60*60*1000;
	//测试数据
	public static String new_frame1       = "5A5A 0001 SN000003 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000000 5C5C";
	public static String new_frame1_clear = "5A5A 0001 SN000003 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001 5C5C";

	public static String frame_verify = "5A5A0002SN0000035C5C";
}
