package com.bgqc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bgqc.beans.CarBean;
import com.bgqc.beans.DriverBean;
import com.bgqc.beans.LayuiBean;
import com.bgqc.beans.MsgBean;
import com.bgqc.beans.RequestBean;
import com.bgqc.beans.TaskBean;

import com.bgqc.service.base.TaskService;
import com.bgqc.util.Lay;
import com.bgqc.util.Lg;
import com.github.pagehelper.PageHelper;

@Controller
public class TaskAction {
	@Resource
	TaskService taskservice;
	
	@ResponseBody
	@RequestMapping("addTask")
	public TaskBean addTask(HttpSession session,TaskBean taskbean){
		TaskBean taskbeanreturn=taskservice.addTask(taskbean);
		
		return taskbeanreturn;
	}
	
	@ResponseBody
	@RequestMapping("getTask")
	public LayuiBean<TaskBean> getTask(HttpSession session,Integer page,Integer limit,TaskBean taskbean){
		Lg.debug("到了getTask内");
		PageHelper.startPage(page,limit);
		Lg.debug("分页完毕");
		List<TaskBean> list=taskservice.getTask(taskbean);
		return Lay.rt(list);
	}
	
	@ResponseBody
	@RequestMapping("getTaskDriver")
	public LayuiBean<DriverBean> getTaskDriver(HttpSession session,Integer page,Integer limit,DriverBean driverbean){
		Lg.debug("到了getTaskDriver内");
		PageHelper.startPage(page,limit);
		Lg.debug("分页完毕");
		List<DriverBean> list=taskservice.getTaskDriver(driverbean);
		return Lay.rt(list);
	}
	
	@ResponseBody
	@RequestMapping("getTaskCar")
	public LayuiBean<CarBean> getTaskCar(HttpSession session,Integer page,Integer limit,CarBean carbean){
		Lg.debug("到了getTaskCar内");
		PageHelper.startPage(page,limit);
		Lg.debug("分页完毕");
		List<CarBean> list=taskservice.getTaskCar(carbean);
		return Lay.rt(list);
	}
	
	@ResponseBody
	@RequestMapping("getTaskRequested")
	public LayuiBean<RequestBean> getTaskRequested(HttpSession session,Long task_id){
		Lg.debug("到了getTaskRequested内");
		List<RequestBean> list=taskservice.getTaskRequested(task_id);
		return Lay.rtnopage(list);
	}
	
	@ResponseBody
	@RequestMapping("getTaskRequesting")
	public LayuiBean<RequestBean> getTaskRequesting(HttpSession session,Integer page,Integer limit){
		Lg.debug("到了getTaskRequesting内");
		PageHelper.startPage(page,limit);
		Lg.debug("分页完毕");
		List<RequestBean> list=taskservice.getTaskRequesting();
		return Lay.rt(list);
	}
	
	@ResponseBody
	@RequestMapping("addTaskRequest")
	public MsgBean addTaskRequest(HttpSession session,RequestBean requestbean){
		MsgBean mb=taskservice.addTaskRequest(requestbean);
		
		return mb;
	}
	
	@ResponseBody
	@RequestMapping("removeTaskRequest")
	public MsgBean removeTaskRequest(HttpSession session,RequestBean requestbean){
		MsgBean mb=taskservice.removeTaskRequest(requestbean);
		
		return mb;
	}
	@ResponseBody
	@RequestMapping("refuseTaskRequest")
	public MsgBean refuseTaskRequest(HttpSession session,RequestBean requestbean){
		MsgBean mb=taskservice.refuseTaskRequest(requestbean);
		
		return mb;
	}
	
	@ResponseBody
	@RequestMapping("decideTask")
	public MsgBean decideTask(HttpSession session,TaskBean taskbean){
		MsgBean mb=taskservice.decideTask(taskbean);
		
		return mb;
	}
	@ResponseBody
	@RequestMapping("editTaskDriver")
	public MsgBean editTaskDriver(HttpSession session,TaskBean taskbean){
		MsgBean mb=taskservice.editTaskDriver(taskbean);
		
		return mb;
	}
	@ResponseBody
	@RequestMapping("editTaskCar")
	public MsgBean editTaskCar(HttpSession session,TaskBean taskbean){
		MsgBean mb=taskservice.editTaskCar(taskbean);
		
		return mb;
	}
	@ResponseBody
	@RequestMapping("editTaskmaybegintime")
	public MsgBean editTaskmaybegintime(HttpSession session,TaskBean taskbean){
		MsgBean mb=taskservice.editTaskmaybegintime(taskbean);
		
		return mb;
	}
	@ResponseBody
	@RequestMapping("goTask")
	public MsgBean goTask(HttpSession session,TaskBean taskbean){
		MsgBean mb=taskservice.goTask(taskbean);
		
		return mb;
	}
	@ResponseBody
	@RequestMapping("backTask")
	public MsgBean backTask(HttpSession session,TaskBean taskbean){
		MsgBean mb=taskservice.backTask(taskbean);
		
		return mb;
	}
	@ResponseBody
	@RequestMapping("delTask")
	public MsgBean delTask(HttpSession session,TaskBean taskbean){
		MsgBean mb=taskservice.delTask(taskbean);
		
		return mb;
	}
	@ResponseBody
	@RequestMapping("redecideTask")
	public MsgBean redecideTask(HttpSession session,TaskBean taskbean){
		MsgBean mb=taskservice.redecideTask(taskbean);
		
		return mb;
	}
	
}
