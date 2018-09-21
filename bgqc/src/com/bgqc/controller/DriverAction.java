package com.bgqc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bgqc.beans.DriverBean;
import com.bgqc.beans.LayuiBean;
import com.bgqc.beans.MsgBean;
import com.bgqc.service.base.DriverService;
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
public class DriverAction {
	@Resource
	// UserService userservice;
	DriverService driverservice;

	@ResponseBody
	@RequestMapping("getDrivers")
	// 获取用户列表，记得加page和limit两个参数，用于分页
	public LayuiBean<DriverBean> getDrivers(HttpSession session, Integer page, Integer limit,
			DriverBean driversbean) {
		// 每次要分页的查询之前运行这个
		System.out.println("++++++++++++++++++" + page);
		System.out.println("++++++++++++++++++" + limit);

		PageHelper.startPage(page, limit);
		List<DriverBean> l = driverservice.getDrivers(driversbean);

		Lg.debug("getDrivers action");
		// 把返回值走Lay.rt函数，返回一个LayuiBean给前台
		return Lay.rt(l);

	}

	// 新增司机时，点击姓名，出现选择列表。
	@ResponseBody
	@RequestMapping("getNewDriver")
	// 获取用户列表，记得加page和limit两个参数，用于分页
	public LayuiBean<DriverBean> getNewDriver(HttpSession session, Integer page, Integer limit,
			DriverBean driversbean) {
		// 每次要分页的查询之前运行这个
		System.out.println("！！！！！！！！！！！！！！" + page);
		System.out.println("！！！！！！！！！！！！！！" + limit);

		PageHelper.startPage(page, limit);
		List<DriverBean> l = driverservice.getNewDriver(driversbean);

		Lg.debug("getNewDriver action");
		
		// 把返回值走Lay.rt函数，返回一个LayuiBean给前台
		return Lay.rt(l);

	}

	/*
	 * 以下是增删改和重置密码，很简单不细说了。
	 * 需要注意，由于所有5个方法都被切，所以参数记得待session，否则切面获取不到session，无法判断登录状态
	 */

	@ResponseBody
	@RequestMapping("addDrivers")
	public MsgBean addDrivers(HttpSession session, DriverBean driversbean) {
		Lg.debug("addDrivers action");
		Lg.debug(driversbean);
		MsgBean mb = driverservice.addDrivers(driversbean);
		Lg.debug(mb);
		return mb;
	}

	@ResponseBody
	@RequestMapping("editDrivers")
	public MsgBean editUser(HttpSession session, DriverBean driversbean) {
		Lg.debug("editDrivers action");
		Lg.debug(driversbean);
		MsgBean mb = driverservice.editDrivers(driversbean);
		Lg.debug(mb);
		return mb;
	}

	@ResponseBody
	@RequestMapping("delDrivers")
	public MsgBean delDrivers(HttpSession session, DriverBean driversbean) {
		Lg.debug("delDrivers action");
		Lg.debug(driversbean);
		MsgBean mb = driverservice.delDrivers(driversbean);
		Lg.debug(mb);
		return (mb);
	}

}
