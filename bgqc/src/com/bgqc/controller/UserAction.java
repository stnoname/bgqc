package com.bgqc.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bgqc.beans.LayuiBean;
import com.bgqc.beans.MsgBean;
import com.bgqc.beans.UserBean;
import com.bgqc.service.base.UserService;
import com.bgqc.util.Lay;
import com.bgqc.util.Lg;
import com.github.pagehelper.PageHelper;

/*
 * 用户模块的后台总Action，大家每人都得写一个这样的类
 * 
 * 
 * 
 * 
*/
@Controller
public class UserAction {
	@Resource
	UserService userservice;
	
	@ResponseBody
	@RequestMapping("getUser")
	//获取用户列表，记得加page和limit两个参数，用于分页
	public LayuiBean<UserBean> getUser(HttpSession session,UserBean userbean,Integer page,Integer limit){
		//每次要分页的查询之前运行这个
		PageHelper.startPage(page,limit);
		List<UserBean> l = userservice.getUser(userbean);
		Lg.debug("getUser action");
		//把返回值走Lay.rt函数，返回一个LayuiBean给前台
		return Lay.rt(l);
	}
	/*
	 * 以下是增删改和重置密码，很简单不细说了。
	 * 需要注意，由于所有5个方法都被切，所以参数记得待session，否则切面获取不到session，无法判断登录状态
	 */
	@ResponseBody
	@RequestMapping("addUser")
	public MsgBean addUser(HttpSession session,UserBean userbean){
		Lg.debug("addUser action");
		Lg.debug(userbean);
		MsgBean mb = userservice.addUser(userbean);
		Lg.debug(mb);
		return mb;
	}
	
	@ResponseBody
	@RequestMapping("editUser")
	public MsgBean editUser(HttpSession session,UserBean userbean){
		Lg.debug("editUser action");
		Lg.debug(userbean);
		MsgBean mb = userservice.editUser(userbean);
		Lg.debug(mb);
		return mb;
	}
	
	@ResponseBody
	@RequestMapping("delUser")
	public MsgBean delUser(HttpSession session,UserBean userbean){
		Lg.debug("delUser action");
		Lg.debug(userbean);
		MsgBean mb = userservice.delUser(userbean);
		Lg.debug(mb);
		return mb;
	}
	
	@ResponseBody
	@RequestMapping("repassUser")
	public MsgBean repassUser(HttpSession session,UserBean userbean){
		Lg.debug("repassUser action");
		Lg.debug(userbean);
		MsgBean mb = userservice.repassUser(userbean);
		Lg.debug(mb);
		return mb;
	}
	
}
