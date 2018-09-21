package com.bgqc.beans;

/*
 * @author 李挺
 * 本类是我自定义的，用来在增删改的时候，返回成功还是失败的信息的
 * 其中title只能定义状态，目前只包括“成功”和“失败”两种，前台根据title来判断结果
 * 当然，如果需要可以额外添加
 * msg是客户能看到的提示信息
 * 建议而不必须使用
 */

public class MsgBean {
	String title;
	String msg;
	
	public MsgBean(String title, String msg) {
		super();
		this.title = title;
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public MsgBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MsgBean [title=" + title + ", msg=" + msg + "]";
	}
	
}
