package com.bgqc.beans;

import java.util.List;
/*
 * @author	李挺
 * 本类是layui的列表标准返回格式的bean
 * 其中code和msg两个属性是定死的
 * data代表数据，是个类
 * count代表数据总行数。
 * 注意count不等于data的size，因为size只是本页数据，count是总数据个数
 * 具体调用可以参见util包里的Lay类
 */
public class LayuiBean<T> {
	private int code=0;
	private String msg="";
	private long count=0;
	private List<T> data=null;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return msg;
	}
	public void setMessage(String message) {
		this.msg = message;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public LayuiBean(int code, String message, long count, List<T> data) {
		super();
		this.code = code;
		this.msg = message;
		this.count = count;
		this.data = data;
	}
	public LayuiBean() {
		super();
	}
	@Override
	public String toString() {
		return "LayuiBean [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	}
	
	
	
	
}
