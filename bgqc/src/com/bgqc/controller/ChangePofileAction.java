package com.bgqc.controller;


import java.util.HashMap;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bgqc.beans.MsgBean;
import com.bgqc.beans.UserBean;
import com.bgqc.service.base.ChangePofileService;


/*
 * 用户模块的后台总Action，大家每人都得写一个这样的类
 * 
 * 
 * 
 * 
*/
@Controller
public class ChangePofileAction {
	@Resource
	ChangePofileService changepofileservice;
	
	@ResponseBody
	@RequestMapping("changePassword")
	//获取用户列表，记得加page和limit两个参数，用于分页
	public MsgBean changePassword(HttpSession session,String oldpassword,String newpassworda,String newpasswordb){
		UserBean userbean=(UserBean)session.getAttribute("user");
		Map <String,Object>map=new HashMap<String,Object>();
		userbean.setPassword(oldpassword);
		map.put("user", userbean);
		map.put("newpassworda",newpassworda);
		map.put("newpasswordb",newpasswordb);
		MsgBean mb = changepofileservice.changePassword(map);
		
		
		return mb;
	}
	@ResponseBody
	@RequestMapping("exit")
	public MsgBean exit(HttpSession session){
		session.setAttribute("user",null);
		String t,s;
		t="成功";
		s="退出成功";
		return new MsgBean(t,s);
	}
	
	
}
