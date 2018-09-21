package com.bgqc.beans;

public class CarReportBean {
	private String car_id;
	private String car_number;
	private Integer taskcount;
	private Integer requestcount;
	private Long usetime;
	public String getCar_id() {
		return car_id;
	}
	public void setCar_id(String car_id) {
		this.car_id = car_id;
	}
	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
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
		return "CarReportBean [car_id=" + car_id + ", car_number=" + car_number + ", taskcount=" + taskcount
				+ ", requestcount=" + requestcount + ", usetime=" + usetime + "]";
	}
	
}
