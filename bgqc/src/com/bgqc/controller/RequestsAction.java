package com.bgqc.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bgqc.beans.LayuiBean;
import com.bgqc.beans.MsgBean;
import com.bgqc.beans.RequestBean;
import com.bgqc.beans.UserBean;
import com.bgqc.service.base.RequestsService;
import com.bgqc.util.Lay;
import com.bgqc.util.Lg;
import com.github.pagehelper.PageHelper;

@Controller
public class RequestsAction {

	@Resource
	RequestsService requestsService;
	
	@RequestMapping("getRequests")
	@ResponseBody
	public LayuiBean<RequestBean> getRequests(HttpSession session,RequestBean RequestBean,Integer page,Integer limit){
		
		PageHelper.startPage(page,limit);
		List<RequestBean> list = requestsService.getRequests(RequestBean);
		Lg.debug("getRequests action");
		return Lay.rt(list);
	}
	@RequestMapping("getRequestsAndroid")
	@ResponseBody
	public List<RequestBean> getRequestsAndroid(HttpSession session,RequestBean RequestBean){
		
		List<RequestBean> list = requestsService.getRequests(RequestBean);

		return list;
	}
	
	@RequestMapping("addRequests")
	@ResponseBody
	public MsgBean addRequests(HttpSession session,RequestBean RequestBean){
		UserBean u = (UserBean)session.getAttribute("user");
		RequestBean.setUserBean(u);
		Lg.debug("addRequests action");
		Lg.debug(RequestBean);
		MsgBean msgBean = requestsService.addRequests(RequestBean);
		Lg.debug(msgBean);
		return msgBean;
	}
	
	@RequestMapping("editRequests")
	@ResponseBody
	public MsgBean editRequests(HttpSession session,RequestBean RequestBean){
		UserBean u = (UserBean) session.getAttribute("user");
		RequestBean.setUserBean(u);
		List<RequestBean> list = requestsService.getRequests(RequestBean);
		if(list.size()>0){
			if(u.getUser_id().equals(list.get(0).getUserBean().getUser_id())){
				Lg.debug("editRequests action");
				Lg.debug(RequestBean);
				MsgBean msgBean = requestsService.editRequests(RequestBean);
				Lg.debug(msgBean);
				return msgBean;
			}
		
	}
		
		
		return new MsgBean("title","您没有权限修改！");
	}
	
	@RequestMapping("delRequests")
	@ResponseBody
	public MsgBean delRequests(HttpSession session,RequestBean RequestBean){
		UserBean userBean = (UserBean) session.getAttribute("user");
		if(userBean.getUser_id().equals(RequestBean.getUserBean().getUser_id())){
		Lg.debug("delRequests action");
		Lg.debug(RequestBean);
		MsgBean msgBean = requestsService.delRequests(RequestBean);
		Lg.debug(msgBean);
		return msgBean;
		}
		return new MsgBean("title","您没有权限删除！");
	}
	
	@RequestMapping("repassRequests")
	@ResponseBody
	public MsgBean repassRequests(HttpSession session,RequestBean RequestBean){
		Lg.debug("repassRequests action");
		Lg.debug(RequestBean);
		MsgBean msgBean = requestsService.repassRequests(RequestBean);
		Lg.debug(msgBean);
		return msgBean;
	}
}
