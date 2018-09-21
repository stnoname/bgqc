package com.bgqc.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bgqc.beans.CarReportBean;
import com.bgqc.beans.DriverReportBean;
import com.bgqc.beans.TimeBetweenBean;
import com.bgqc.beans.UserReportBean;
import com.bgqc.service.base.ReportService;
@Service
public class ReportServiceImpl implements ReportService{
	@Resource
	private SqlSessionTemplate sqlSession;
	@Override
	public List<CarReportBean> getCarReport(TimeBetweenBean timebetweenbean) {
		
		List<CarReportBean> l=sqlSession.selectList("Report.getCarReport",timebetweenbean);
		
		return l;
	}
	@Override
	public List<DriverReportBean> getDriverReport(TimeBetweenBean timebetweenbean) {
		List<DriverReportBean> l=sqlSession.selectList("Report.getDriverReport",timebetweenbean);
		
		return l;
	}
	@Override
	public List<UserReportBean> getUserReport(TimeBetweenBean timebetweenbean) {
		List<UserReportBean> l=sqlSession.selectList("Report.getUserReport",timebetweenbean);
		
		return l;
	}

}
