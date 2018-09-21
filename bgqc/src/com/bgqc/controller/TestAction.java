package com.bgqc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bgqc.beans.MsgBean;
import com.bgqc.beans.TimeBetweenBean;
import com.bgqc.service.base.TestService;
import com.bgqc.util.Lg;

@Controller
public class TestAction {
	@Resource
	TestService testservice;
	
	@ResponseBody
	@RequestMapping("test")
	public MsgBean test(HttpSession session,Integer num,TimeBetweenBean timebetweenbean){
		Lg.debug(num);
		MsgBean mb=testservice.test(num, timebetweenbean.getFromtime(),timebetweenbean.getTotime());
		
		return mb;
	}
}
