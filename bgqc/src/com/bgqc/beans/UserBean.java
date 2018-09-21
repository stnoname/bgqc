package com.bgqc.beans;


/*
 * author 李挺
 * 用户的实体类，与数据库字段一致
 */
public class UserBean {
	private Integer user_id;
	private String password;
	private String name;
	private Integer auth;
	private String office_phone;
	private String mobile_phone;
	private String v_phone;
	private String department;
	private String job;
	private String picture;
	private Integer user_state;
	private Integer user_enable;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAuth() {
		return auth;
	}
	public void setAuth(Integer auth) {
		this.auth = auth;
	}
	public String getOffice_phone() {
		return office_phone;
	}
	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}
	public String getMobile_phone() {
		return mobile_phone;
	}
	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}
	public String getV_phone() {
		return v_phone;
	}
	public void setV_phone(String v_phone) {
		this.v_phone = v_phone;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getUser_state() {
		return user_state;
	}
	public void setUser_state(Integer user_state) {
		this.user_state = user_state;
	}
	public Integer getUser_enable() {
		return user_enable;
	}
	public void setUser_enable(Integer user_enable) {
		this.user_enable = user_enable;
	}
	@Override
	public String toString() {
		return "UserBean [user_id=" + user_id + ", password=" + password + ", name=" + name + ", auth=" + auth
				+ ", office_phone=" + office_phone + ", mobile_phone=" + mobile_phone + ", v_phone=" + v_phone
				+ ", department=" + department + ", job=" + job + ", picture=" + picture + ", user_state=" + user_state
				+ ", user_enable=" + user_enable + "]";
	}
	public UserBean(Integer user_id, String password, String name, Integer auth, String office_phone,
			String mobile_phone, String v_phone, String department, String job, String picture, Integer user_state,
			Integer user_enable) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.name = name;
		this.auth = auth;
		this.office_phone = office_phone;
		this.mobile_phone = mobile_phone;
		this.v_phone = v_phone;
		this.department = department;
		this.job = job;
		this.picture = picture;
		this.user_state = user_state;
		this.user_enable = user_enable;
	}
	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
