package com.bgqc.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bgqc.beans.MsgBean;
import com.bgqc.beans.UserBean;
import com.bgqc.service.base.ChangeUserService;
import com.bgqc.util.Lg;


@Controller
public class ChangeUserAction {
	@Resource
	ChangeUserService changeuserservice;

	@ResponseBody
	@RequestMapping("changeUser")
	public MsgBean changeUser(HttpSession session,UserBean newuserbean){
		UserBean userbean=(UserBean)session.getAttribute("user");
		
		Lg.debug("action");
		Lg.debug("oldbean"+userbean);
		Lg.debug("newbean"+newuserbean);
		MsgBean mb = changeuserservice.changeUser(userbean,newuserbean);
		Lg.debug("oldbeancccccc"+userbean);
		Lg.debug("newbeanccccccc"+newuserbean);
		return mb;
	}
	@ResponseBody
	@RequestMapping("getUserInfo")
	public UserBean getUserInfo(HttpSession session){
		UserBean userbean=(UserBean)session.getAttribute("user");
		
//		Lg.debug("action");
//		Lg.debug("oldbean"+userbean);
//		Lg.debug("newbean"+newuserbean);
//		MsgBean mb = changeuserservice.changeUser(userbean,newuserbean);
//		Lg.debug("oldbeancccccc"+userbean);
//		Lg.debug("newbeanccccccc"+newuserbean);
		return userbean;
	}
}
