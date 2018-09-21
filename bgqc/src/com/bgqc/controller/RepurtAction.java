package com.bgqc.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bgqc.beans.CarReportBean;
import com.bgqc.beans.DriverReportBean;
import com.bgqc.beans.LayuiBean;

import com.bgqc.beans.TimeBetweenBean;
import com.bgqc.beans.UserReportBean;
import com.bgqc.service.base.ReportService;

import com.bgqc.util.Lay;
import com.bgqc.util.Lg;



@Controller
public class RepurtAction {
	@Resource
	ReportService reportservice;
	
	@ResponseBody
	@RequestMapping("getCarReport")
	public LayuiBean<CarReportBean> getCarReport(HttpSession session,TimeBetweenBean timebetweenbean){
		
		List<CarReportBean> l = reportservice.getCarReport(timebetweenbean);
		Lg.debug("getCarReport");
		
		return Lay.rtnopage(l);
	}
	
	@ResponseBody
	@RequestMapping("getDriverReport")
	public LayuiBean<DriverReportBean> getDriverReport(HttpSession session,TimeBetweenBean timebetweenbean){
		
		List<DriverReportBean> l = reportservice.getDriverReport(timebetweenbean);
		Lg.debug("getDriverReport");
		
		return Lay.rtnopage(l);
	}
	
	@ResponseBody
	@RequestMapping("getUserReport")
	public LayuiBean<UserReportBean> getUserReport(HttpSession session,TimeBetweenBean timebetweenbean){
		
		List<UserReportBean> l = reportservice.getUserReport(timebetweenbean);
		Lg.debug("getUserReport");
		
		return Lay.rtnopage(l);
	}
	
	
}
