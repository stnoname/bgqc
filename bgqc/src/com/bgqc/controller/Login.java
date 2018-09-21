package com.bgqc.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bgqc.beans.UserBean;
import com.bgqc.service.base.LoginService;

import com.bgqc.util.Lg;
/*
 * @author	李挺
 * 普通的登录模块，没啥好说的……
 * 直接调用service就好，成功之后写个session，返回个msg
 * 由于同时要返回一个MsgBean和一个UserBean，所以用个map包了一下
 * 
 */

@Controller
public class Login {
	@Resource
	LoginService loginservice;
	
	@ResponseBody
	@RequestMapping("login")
	public UserBean login(HttpSession session,UserBean userbean){
		Lg.debug("in login"+userbean);
		UserBean userbeanreturn=loginservice.login(userbean);
		
		
		if(userbeanreturn.getName()!=null){
			session.setAttribute("user",userbeanreturn);
			Lg.debug(session.getAttribute("user"));
		}
		
		
		
		return userbeanreturn;
	}
	
	
	
}
