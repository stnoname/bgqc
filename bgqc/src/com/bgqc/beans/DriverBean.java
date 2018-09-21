package com.bgqc.beans;

public class DriverBean extends UserBean{
	private Integer driver_id; // 司机编号
	private Integer driver_user_id; // 司机用户编号【下选框 对应user编号】
	private Integer driver_state; // 工作状态：00 空闲；10 待发车；20 运输中
	private Integer driver_enable; // 司机状态：00 可用；10 休息；20 停用
	private String driver_reason; // 司机不可用原因
	private Integer driver_missions;
	public Integer getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(Integer driver_id) {
		this.driver_id = driver_id;
	}
	public Integer getDriver_user_id() {
		return driver_user_id;
	}
	public void setDriver_user_id(Integer driver_user_id) {
		this.driver_user_id = driver_user_id;
	}
	public Integer getDriver_state() {
		return driver_state;
	}
	public void setDriver_state(Integer driver_state) {
		this.driver_state = driver_state;
	}
	public Integer getDriver_enable() {
		return driver_enable;
	}
	public void setDriver_enable(Integer driver_enable) {
		this.driver_enable = driver_enable;
	}
	public String getDriver_reason() {
		return driver_reason;
	}
	public void setDriver_reason(String driver_reason) {
		this.driver_reason = driver_reason;
	}
	
	public Integer getDriver_missions() {
		return driver_missions;
	}
	public void setDriver_missions(Integer driver_missions) {
		this.driver_missions = driver_missions;
	}
	@Override
	public String toString() {
		return "DriverBean [getDriver_id()=" + getDriver_id() + ", getDriver_user_id()=" + getDriver_user_id()
				+ ", getDriver_state()=" + getDriver_state() + ", getDriver_enable()=" + getDriver_enable()
				+ ", getDriver_reason()=" + getDriver_reason() + ", getUser_id()=" + getUser_id() + ", getPassword()="
				+ getPassword() + ", getName()=" + getName() + ", getAuth()=" + getAuth() + ", getOffice_phone()="
				+ getOffice_phone() + ", getMobile_phone()=" + getMobile_phone() + ", getV_phone()=" + getV_phone()
				+ ", getDepartment()=" + getDepartment() + ", getJob()=" + getJob() + ", getPicture()=" + getPicture()
				+ ", getUser_state()=" + getUser_state() + ", getUser_enable()=" + getUser_enable() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
}
