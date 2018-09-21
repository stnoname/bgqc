package com.bgqc.service.base;

import java.util.List;

import com.bgqc.beans.CarBean;
import com.bgqc.beans.DriverBean;
import com.bgqc.beans.MsgBean;
import com.bgqc.beans.RequestBean;
import com.bgqc.beans.TaskBean;

public interface TaskService {
	

	public List<TaskBean> getTask(TaskBean taskbean);

	public List<DriverBean> getTaskDriver(DriverBean driverbean);

	public List<CarBean> getTaskCar(CarBean carbean);

	public TaskBean addTask(TaskBean taskbean);

	public List<RequestBean> getTaskRequested(Long task_id);

	public List<RequestBean> getTaskRequesting();

	public MsgBean addTaskRequest(RequestBean requestbean);

	public MsgBean removeTaskRequest(RequestBean requestbean);

	public MsgBean refuseTaskRequest(RequestBean requestbean);

	public MsgBean decideTask(TaskBean taskbean);

	public MsgBean editTaskDriver(TaskBean taskbean);

	public MsgBean editTaskCar(TaskBean taskbean);

	public MsgBean editTaskmaybegintime(TaskBean taskbean);

	public MsgBean goTask(TaskBean taskbean);

	public MsgBean backTask(TaskBean taskbean);

	public MsgBean delTask(TaskBean taskbean);

	public MsgBean redecideTask(TaskBean taskbean);

}
