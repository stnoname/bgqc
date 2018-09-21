package com.bgqc.beans;
/*
 * author 马福宝
 * 用户的实体类，与数据库字段一致
 */
public class CarBean {
	private Integer car_id;
	private String car_number;
	private Integer car_state;
	private Integer car_enable;
	private String car_reason;
	private Integer seats;
	private Integer car_missions;
	public Integer getCar_id() {
		return car_id;
	}
	public void setCar_id(Integer car_id) {
		this.car_id = car_id;
	}
	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}
	public Integer getCar_state() {
		return car_state;
	}
	public void setCar_state(Integer car_state) {
		this.car_state = car_state;
	}
	public Integer getCar_enable() {
		return car_enable;
	}
	public void setCar_enable(Integer car_enable) {
		this.car_enable = car_enable;
	}
	public String getCar_reason() {
		return car_reason;
	}
	public void setCar_reason(String car_reason) {
		this.car_reason = car_reason;
	}
	public Integer getSeats() {
		return seats;
	}
	public void setSeats(Integer seats) {
		this.seats = seats;
	}
	public Integer getCar_missions() {
		return car_missions;
	}
	public void setCar_missions(Integer car_missions) {
		this.car_missions = car_missions;
	}
	@Override
	public String toString() {
		return "CarBean [car_id=" + car_id + ", car_number=" + car_number + ", car_state=" + car_state + ", car_enable="
				+ car_enable + ", car_reason=" + car_reason + ", seats=" + seats + ", car_missions=" + car_missions
				+ "]";
	}

	
}
