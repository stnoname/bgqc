package com.bgqc.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TimeBetweenBean {
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	private Date fromtime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	private Date totime;
	public Date getFromtime() {
		return fromtime;
	}
	public void setFromtime(Date fromtime) {
		this.fromtime = fromtime;
	}
	public Date getTotime() {
		return totime;
	}
	public void setTotime(Date totime) {
		this.totime = totime;
	}
	@Override
	public String toString() {
		return "TimeBetweenBean [fromtime=" + fromtime + ", totime=" + totime + "]";
	}
}
