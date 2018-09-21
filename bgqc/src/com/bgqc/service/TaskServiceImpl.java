package com.bgqc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bgqc.beans.CarBean;
import com.bgqc.beans.DriverBean;
import com.bgqc.beans.MsgBean;
import com.bgqc.beans.RequestBean;
import com.bgqc.beans.TaskBean;
import com.bgqc.service.base.TaskService;
import com.bgqc.util.Lg;
import com.bgqc.websocket.WebSocketServer;
@Service
public class TaskServiceImpl implements TaskService{
	@Resource
	private SqlSessionTemplate sqlSession;
	
	@Override
	public TaskBean addTask(TaskBean taskbean) {
		Lg.debug("addtask in                "+taskbean);

		taskbean.setOrdertime(new Date());
		taskbean.setTask_state(60);
		sqlSession.insert("Task.insert",taskbean);
		Lg.debug("是否拿回了id"+taskbean);
		
		Lg.debug("addtask out                "+taskbean);
		return taskbean;
	}
	@Override
	
	public List<TaskBean> getTask(TaskBean taskbean) {
		Lg.debug("进入gettask的service，"+taskbean.getName());
		Lg.debug(taskbean);
		if(taskbean.getName()==null){
			taskbean.setName("");
		}
		List<TaskBean> list=sqlSession.selectList("Task.selectAll",taskbean);
		return list;
	}
	@Override
	public List<DriverBean> getTaskDriver(DriverBean driverbean) {
		if(driverbean.getName()==null){
			driverbean.setName("");
		}
		List<DriverBean> list=sqlSession.selectList("Task.selectDriver",driverbean);
		
		return list;
	}
	@Override
	public List<CarBean> getTaskCar(CarBean carbean) {
		if(carbean.getCar_number()==null){
			carbean.setCar_number("");
		}
		List<CarBean> list=sqlSession.selectList("Task.selectCar",carbean);
		return list;
	}
	@Override
	public List<RequestBean> getTaskRequested(Long task_id) {
		List<RequestBean> list = null;
		if(task_id==null){
			list=new ArrayList<RequestBean>();
		}else{
			list=sqlSession.selectList("Task.selectRequested",task_id);
		}
		
		
		return list;
	}
	@Override
	public List<RequestBean> getTaskRequesting() {
		List<RequestBean> list = sqlSession.selectList("Task.selectRequesting");
		
		
		return list;
	}
	@Override
	public MsgBean addTaskRequest(RequestBean requestbean) {
		String t,s;
		List <TaskBean>l=sqlSession.selectList("Task.selectTaskById",requestbean.getRequest_task_id());
		if(l.size()==0){
			t="失败";
			s="未找到该任务，请刷新页面后重试";
		}else{
			if(l.get(0).getTask_state()!=60){
				t="失败";
				s="该任务不处于未派单状态，无法派单";
			}else{
				List <RequestBean>ll=sqlSession.selectList("Task.selectTaskNoDouble",requestbean);
				if(ll.size()>0){
					t="失败";
					s="车上已经有此人，不能重复派单";
				}else{
					Integer i=sqlSession.update("Task.addTaskRequest",requestbean);
					if(i>0){
						t="成功";
						s="派单成功";
						Lg.debug("推送信息");
						WebSocketServer.sendMessage("你的请求已经被受理，请注意查收",requestbean.getRequest_user_id()+"");
					}else{
						t="失败";
						s="派单失败，未知错误";
					}
				}
			}
		}
		
		return new MsgBean(t,s);
	}
	@Override
	public MsgBean removeTaskRequest(RequestBean requestbean) {
		String t,s;
		List <TaskBean>l=sqlSession.selectList("Task.selectTaskById",requestbean.getRequest_task_id());
		if(l.size()==0){
			t="失败";
			s="未找到该任务，请刷新页面后重试";
		}else{
			if(l.get(0).getTask_state()!=60){
				t="失败";
				s="该任务不处于未派单状态，无法移除";
			}else{
				Integer i=sqlSession.update("Task.removeTaskRequest",requestbean);
				if(i>0){
					t="成功";
					s="移除成功";
				}else{
					t="失败";
					s="移除失败，未知错误";
				}
			}
		}
		
		return new MsgBean(t,s);
	}
	@Override
	public MsgBean refuseTaskRequest(RequestBean requestbean) {
		String t,s;
		Integer i=sqlSession.update("Task.refuseTaskRequest",requestbean);
		if(i>0){
			t="成功";
			s="驳回成功";
		}else{
			t="失败";
			s="驳回失败，未知错误";
		}
		return new MsgBean(t,s);
	}
	@Override
	public MsgBean decideTask(TaskBean taskbean) {
		String t,s;
		Integer i=sqlSession.update("Task.decideTask",taskbean);
		sqlSession.update("Task.updateTaskDriver",taskbean);
		sqlSession.update("Task.updateTaskCar",taskbean);
		if(i>0){
			t="成功";
			s="派单已确认，随时可以出发";
		}else{
			t="失败";
			s="派单失败，请刷新后重试";
		}
		return new MsgBean(t,s);
	}
	@Override
	public MsgBean editTaskDriver(TaskBean taskbean) {
		String t,s;
		Integer i=sqlSession.update("Task.editTaskDriver",taskbean);
		if(i>0){
			t="成功";
			s="修改司机成功";
		}else{
			t="失败";
			s="修改司机失败，请刷新后重试";
		}
		return new MsgBean(t,s);
	}
	@Override
	public MsgBean editTaskCar(TaskBean taskbean) {
		String t,s;
		Integer i=sqlSession.update("Task.editTaskCar",taskbean);
		if(i>0){
			t="成功";
			s="修改车辆成功";
		}else{
			t="失败";
			s="修改车辆失败，请刷新后重试";
		}
		return new MsgBean(t,s);
	}
	@Override
	public MsgBean editTaskmaybegintime(TaskBean taskbean) {
		String t,s;
		Integer i=sqlSession.update("Task.editTaskmaybegintime",taskbean);
		if(i>0){
			t="成功";
			s="修改出发时间成功";
		}else{
			t="失败";
			s="修改出发时间失败，请刷新后重试";
		}
		return new MsgBean(t,s);
	}
	@Override
	public MsgBean goTask(TaskBean taskbean) {
		String t,s;
		taskbean.setOrdertime(new Date());
		Integer i=sqlSession.update("Task.goTask",taskbean);
		sqlSession.update("Task.updateTaskDriver",taskbean);
		sqlSession.update("Task.updateTaskCar",taskbean);
		if(i>0){
			t="成功";
			s="出发已确认，请监控完成时间";
		}else{
			t="失败";
			s="出发失败，请刷新后重试";
		}
		return new MsgBean(t,s);
	}
	@Override
	public MsgBean backTask(TaskBean taskbean) {
		String t,s;
		taskbean.setOrdertime(new Date());
		Integer i=sqlSession.update("Task.backTask",taskbean);
		sqlSession.update("Task.updateTaskDriver",taskbean);
		sqlSession.update("Task.updateTaskCar",taskbean);
		sqlSession.update("Task.delTaskById",taskbean);
		if(i>0){
			t="成功";
			s="任务完毕，已返回";
		}else{
			t="失败";
			s="未知错误，请刷新后重试";
		}
		return new MsgBean(t,s);
	}
	@Override
	public MsgBean delTask(TaskBean taskbean) {
		String t,s;
		Integer i=sqlSession.delete("Task.delTask",taskbean);
		if(i>0){
			t="成功";
			s="任务删除成功";
		}else{
			t="失败";
			s="未知错误，请刷新后重试";
		}
		return new MsgBean(t,s);
	}
	@Override
	public MsgBean redecideTask(TaskBean taskbean) {
		String t,s;
		Integer i=sqlSession.update("Task.redecideTask",taskbean);
		sqlSession.update("Task.updateTaskDriver",taskbean);
		sqlSession.update("Task.updateTaskCar",taskbean);
		if(i>0){
			t="成功";
			s="返回派单成功，你现在可以对任务进行修改了";
		}else{
			t="失败";
			s="返回派单失败，请刷新后重试";
		}
		return new MsgBean(t,s);
	}

}
