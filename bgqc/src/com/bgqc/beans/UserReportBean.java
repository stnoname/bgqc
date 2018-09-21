package com.bgqc.beans;

public class UserReportBean {
	private String user_id;
	private String name;
	private Integer taskcount;
	private Long usetime;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTaskcount() {
		return taskcount;
	}
	public void setTaskcount(Integer taskcount) {
		this.taskcount = taskcount;
	}
	public Long getUsetime() {
		return usetime;
	}
	public void setUsetime(Long usetime) {
		this.usetime = usetime;
	}
	@Override
	public String toString() {
		return "UserReportBean [user_id=" + user_id + ", name=" + name + ", taskcount=" + taskcount + ", usetime="
				+ usetime + "]";
	}
	
	
}
