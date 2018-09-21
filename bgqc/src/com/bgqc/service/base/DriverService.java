package com.bgqc.service.base;

import java.util.List;

import com.bgqc.beans.DriverBean;
import com.bgqc.beans.MsgBean;

//接口，必须有
public interface DriverService {
	public List<DriverBean> getDrivers(DriverBean driversbean);

	public MsgBean addDrivers(DriverBean driversbean);

	public MsgBean editDrivers(DriverBean driversbean);

	public List<DriverBean> getNewDriver(DriverBean driversbean);

	public MsgBean delDrivers(DriverBean driversbean);

}
