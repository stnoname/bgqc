package com.bgqc.util;

import java.util.Calendar;
import java.util.Date;

/*
 * @author	李挺
 * 输入一个时间，返回一个字符串，格式是yyyy-mm-dd hh:mm:ss
 */

public class TimeTools {
	public static String Yymmddhhmmss(Date data){
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		int yy=c.get(Calendar.YEAR);
		String y=yy+"";
		int mm=c.get(Calendar.MONTH);
		String m=mm<10?("0"+mm):(""+mm);
		int dd=c.get(Calendar.DAY_OF_MONTH);
		String d=dd<10?("0"+dd):(""+dd);
		int hh=c.get(Calendar.HOUR);
		String h=hh<10?("0"+hh):(""+hh);
		int mmm=c.get(Calendar.MINUTE);
		String mi=mmm<10?("0"+mmm):(""+mmm);
		int ss=c.get(Calendar.SECOND);
		String s=ss<10?("0"+ss):(""+ss);
		String ddd=y+"-"+m+"-"+d+" "+h+":"+mi+":"+s;
		return ddd;
		
	}
}
