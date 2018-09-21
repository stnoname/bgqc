package com.bgqc.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;




public class TaskBean {
	private Integer task_id;			//任务号
	private Integer task_driver_id;		//司机编号
	private Integer task_car_id;		//车辆编号
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	private Date maybegintime;	//预计出发时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	private Date ordertime;		//派单时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	private Date begintime;	//出发时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	private Date endtime;		//结束时间
	private Integer task_state;			//任务状态
	private String name;				//司机名
	private String car_number;			//车牌号
	private Integer seats;				//座位数
	private Integer usedseats;			//已占用座位数
	public Integer getTask_id() {
		return task_id;
	}
	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}
	public Integer getTask_driver_id() {
		return task_driver_id;
	}
	public void setTask_driver_id(Integer task_driver_id) {
		this.task_driver_id = task_driver_id;
	}
	public Integer getTask_car_id() {
		return task_car_id;
	}
	public void setTask_car_id(Integer task_car_id) {
		this.task_car_id = task_car_id;
	}
	public Date getMaybegintime() {
		return maybegintime;
	}
	public void setMaybegintime(Date maybegintime) {
		this.maybegintime = maybegintime;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Integer getTask_state() {
		return task_state;
	}
	public void setTask_state(Integer task_state) {
		this.task_state = task_state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}
	public Integer getSeats() {
		return seats;
	}
	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	public Integer getUsedseats() {
		return usedseats;
	}
	public void setUsedseats(Integer usedseats) {
		this.usedseats = usedseats;
	}
	
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	@Override
	public String toString() {
		return "TaskBean [task_id=" + task_id + ", task_driver_id=" + task_driver_id + ", task_car_id=" + task_car_id
				+ ", maybegintime=" + maybegintime + ", ordertime=" + ordertime + ", begintime=" + begintime
				+ ", endtime=" + endtime + ", task_state=" + task_state + ", name=" + name + ", car_number="
				+ car_number + ", seats=" + seats + ", usedseats=" + usedseats + "]";
	}
	


	
}
