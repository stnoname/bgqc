package com.bgqc.service.base;

import java.util.List;

import com.bgqc.beans.CarReportBean;
import com.bgqc.beans.DriverReportBean;
import com.bgqc.beans.TimeBetweenBean;
import com.bgqc.beans.UserReportBean;

public interface ReportService {

	List<CarReportBean> getCarReport(TimeBetweenBean timebetweenbean);

	List<DriverReportBean> getDriverReport(TimeBetweenBean timebetweenbean);

	List<UserReportBean> getUserReport(TimeBetweenBean timebetweenbean);

}
