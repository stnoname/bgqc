package com.bgqc.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bgqc.beans.CarBean;
import com.bgqc.beans.LayuiBean;
import com.bgqc.beans.MsgBean;
import com.bgqc.service.base.CarService;
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
public class CarAction {
	@Resource
	CarService carservice;
	
	@ResponseBody
	@RequestMapping("getCar")
	//获取用户列表，记得加page和limit两个参数，用于分页
	public LayuiBean<CarBean> getUser(HttpSession session,CarBean carbean,Integer page,Integer limit){
		//每次要分页的查询之前运行这个
		PageHelper.startPage(page,limit);
		List<CarBean> l = carservice.getCar(carbean);
		Lg.debug("getCar action");
		//把返回值走Lay.rt函数，返回一个LayuiBean给前台
		return Lay.rt(l);
	}
	/*
	 * 以下是增删改和重置密码，很简单不细说了。
	 * 需要注意，由于所有5个方法都被切，所以参数记得待session，否则切面获取不到session，无法判断登录状态
	 */
	@ResponseBody
	@RequestMapping("addCar")
	public MsgBean addUser(HttpSession session,CarBean carbean){
		Lg.debug("addcar action");
		Lg.debug(carbean);
		MsgBean mb = carservice.addCar(carbean);
		Lg.debug(mb);
		return mb;
	}
	
	@ResponseBody
	@RequestMapping("editCar")
	public MsgBean editUser(HttpSession session,CarBean carbean){
		Lg.debug("editCar action");
		Lg.debug(carbean);
		MsgBean mb = carservice.editCar(carbean);
		Lg.debug(mb);
		return mb;
	}
	
	@ResponseBody
	@RequestMapping("delCar")
	public MsgBean delUser(HttpSession session,CarBean carbean){
		Lg.debug("delCar action");
		Lg.debug(carbean);
		MsgBean mb = carservice.delCar(carbean);
		Lg.debug(mb);
		return mb;
	}
	
	
}
	

