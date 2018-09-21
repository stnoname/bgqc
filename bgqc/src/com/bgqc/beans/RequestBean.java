package com.bgqc.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



public class RequestBean {

	private Integer request_id;
	private Integer request_user_id;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	private Date requesttime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	private Date needstarttime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	private Date needendtime;
	private String needduringtime;
	private String needplace;
	private String needreason;
	private Integer needseats;
	private Integer request_task_id;
	private Integer request_state;
	private UserBean userBean;
	public Integer getRequest_id() {
		return request_id;
	}
	public void setRequest_id(Integer request_id) {
		this.request_id = request_id;
	}
	public Date getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(Date requesttime) {
		this.requesttime = requesttime;
	}
	public Date getNeedstarttime() {
		return needstarttime;
	}
	public void setNeedstarttime(Date needstarttime) {
		this.needstarttime = needstarttime;
	}
	public Date getNeedendtime() {
		return needendtime;
	}
	public void setNeedendtime(Date needendtime) {
		this.needendtime = needendtime;
	}
	public String getNeedduringtime() {
		return needduringtime;
	}
	public void setNeedduringtime(String needduringtime) {
		this.needduringtime = needduringtime;
	}
	public String getNeedplace() {
		return needplace;
	}
	public void setNeedplace(String needplace) {
		this.needplace = needplace;
	}
	public String getNeedreason() {
		return needreason;
	}
	public void setNeedreason(String needreason) {
		this.needreason = needreason;
	}
	public Integer getNeedseats() {
		return needseats;
	}
	public void setNeedseats(Integer needseats) {
		this.needseats = needseats;
	}
	public Integer getRequest_task_id() {
		return request_task_id;
	}
	public void setRequest_task_id(Integer request_task_id) {
		this.request_task_id = request_task_id;
	}
	public Integer getRequest_state() {
		return request_state;
	}
	public void setRequest_state(Integer request_state) {
		this.request_state = request_state;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public Integer getRequest_user_id() {
		return request_user_id;
	}
	public void setRequest_user_id(Integer request_user_id) {
		this.request_user_id = request_user_id;
	}
	public RequestBean() {
		
	}
	public RequestBean(Integer request_id, Date requesttime, Date needstarttime, Date needendtime,
			String needduringtime, String needplace, String needreason, Integer needseats, Integer request_task_id,
			Integer request_state, UserBean userBean) {
		
		this.request_id = request_id;
		this.requesttime = requesttime;
		this.needstarttime = needstarttime;
		this.needendtime = needendtime;
		this.needduringtime = needduringtime;
		this.needplace = needplace;
		this.needreason = needreason;
		this.needseats = needseats;
		this.request_task_id = request_task_id;
		this.request_state = request_state;
		this.userBean = userBean;
	}
	@Override
	public String toString() {
		return "RequestsBean [request_id=" + request_id + ", requesttime=" + requesttime + ", needstarttime="
				+ needstarttime + ", needendtime=" + needendtime + ", needduringtime=" + needduringtime + ", needplace="
				+ needplace + ", needreason=" + needreason + ", needseats=" + needseats + ", request_task_id="
				+ request_task_id + ", request_state=" + request_state + ", userBean=" + userBean + "]";
	}
	
	
	
	
}
