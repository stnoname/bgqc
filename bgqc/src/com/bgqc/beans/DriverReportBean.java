package com.bgqc.beans;

public class DriverReportBean {
	private String driver_id;
	private String name;
	private Integer taskcount;
	private Integer requestcount;
	private Long usetime;
	public String getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
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
	public Integer getRequestcount() {
		return requestcount;
	}
	public void setRequestcount(Integer requestcount) {
		this.requestcount = requestcount;
	}
	public Long getUsetime() {
		return usetime;
	}
	public void setUsetime(Long usetime) {
		this.usetime = usetime;
	}
	@Override
	public String toString() {
		return "DriverReportBean [driver_id=" + driver_id + ", name=" + name + ", taskcount=" + taskcount
				+ ", requestcount=" + requestcount + ", usetime=" + usetime + "]";
	}
	
}
