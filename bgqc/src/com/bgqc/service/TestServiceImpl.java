package com.bgqc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.bgqc.beans.UserBean;
import com.bgqc.service.base.TestService;
import com.bgqc.util.JdbcClass;
import com.bgqc.util.Lg;
@Service
public class TestServiceImpl implements TestService{
	static List<DriverBean> drivers;
	static List<UserBean> users;
	static List<CarBean> cars;
	int lastRequest=0;
	int lastTask=0;
	static String places[]={
		"苏家屯","沈阳东","文官屯","虎石台","新城子","辉山","大成","沙岭","铁岭","新台子","大青","乱石山","开原","昌图","西丰","双庙子","瓢儿屯","深井子","孤家子","榆树台","大官屯","抚顺北","前甸","章党","清原","南口前","苍石","南杂木"
	};
	List<TaskBean> tasks=new ArrayList<TaskBean>();
	List<RequestBean> requests=new ArrayList<RequestBean>();
	@Resource
	private SqlSessionTemplate sqlSession;
	Date fromtime;
	Date totime;
	public JdbcClass jdbc=null;
	public Connection con=null;
	public PreparedStatement pre = null;
	

	@Override
	public MsgBean test(int num,Date fromtime,Date totime) {
		String t,s;
		tasks=new ArrayList<TaskBean>();
		requests=new ArrayList<RequestBean>();
		this.fromtime=fromtime;
		this.totime=totime;
		Lg.debug("fromtime"+fromtime);
		Date testbegin=new Date();
		init();
		Lg.fatal("压力测试初始化完毕");
		for (int i = 0; i < num; i++) {
			Lg.debug(i);
			getNewTask();
		}
		Lg.fatal("压力测试已经获得所有信息在内存中");
		t="成功";
		s="完成了";
		insertTask();
		Lg.fatal("压力测试已经完成所有tasks的插入");
		insertRequest();
		Lg.fatal("压力测试已经完成");
		jdbc.closeAll();
		Long used=new Date().getTime()-testbegin.getTime();
		Lg.fatal("用时"+used/1000+"秒");
		return new MsgBean(t,s);
	}
	
	void insertTask(){
		String sql="insert into tasksh values (?,?,?,?,?,?,?,?)";
		try {
			pre=con.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < tasks.size(); i++) {
			try {
				
				pre.setInt(1, tasks.get(i).getTask_id());
				pre.setInt(2, tasks.get(i).getTask_driver_id());
				pre.setInt(3, tasks.get(i).getTask_car_id());
				pre.setObject(4, new java.sql.Timestamp(tasks.get(i).getMaybegintime().getTime()));
		
				pre.setObject(5, new Timestamp(tasks.get(i).getBegintime().getTime()));
				pre.setObject(6, new Timestamp(tasks.get(i).getEndtime().getTime()));
				pre.setInt(7, tasks.get(i).getTask_state());
				pre.setObject(8, new Timestamp(tasks.get(i).getOrdertime().getTime()));
				pre.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			if(i%1000==0){
				Lg.fatal("task插入了"+i);
//				try {
//					pre.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				jdbc.closePre();
			}
		}
	}
	void insertRequest(){
		String sql="insert into requestsh values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pre=con.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < requests.size(); i++) {
			try {
				
				pre.setInt(1, requests.get(i).getRequest_id());
				pre.setInt(2, requests.get(i).getRequest_user_id());
				pre.setObject(3, new Timestamp(requests.get(i).getRequesttime().getTime()));
				pre.setObject(4, new Timestamp(requests.get(i).getNeedstarttime().getTime()));
				pre.setObject(5, new Timestamp(requests.get(i).getNeedendtime().getTime()));
				pre.setString(6, requests.get(i).getNeedplace());
				pre.setString(7, requests.get(i).getNeedreason());
				pre.setInt(8, requests.get(i).getNeedseats());
				pre.setInt(9, requests.get(i).getRequest_task_id());
				pre.setInt(10, requests.get(i).getRequest_state());
				pre.setString(11, requests.get(i).getNeedduringtime());
				pre.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			if(i%1000==0){
				Lg.fatal("request插入了"+i);
//				try {
//					pre.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				jdbc.closePre();
			}
		}
	}
	
	
	
	void getNewTask(){
		DriverBean driver=drivers.get((int) (Math.random()*drivers.size()));
		CarBean car=cars.get((int) (Math.random()*cars.size()));
		Date maybegintime=randomDate(fromtime,totime);
		Date ordertime=changetime(maybegintime,-8*60*60,2*60*60);
		Date begintime=changetime(maybegintime,0,30*60);
		Date endtime=changetime(begintime,6*60*60,5*60*60);
		TaskBean taskbean=new TaskBean();
		taskbean.setBegintime(begintime);
		taskbean.setEndtime(endtime);
		taskbean.setMaybegintime(maybegintime);
		taskbean.setOrdertime(ordertime);
		taskbean.setTask_car_id(car.getCar_id());
		taskbean.setTask_driver_id(driver.getDriver_id());
		taskbean.setTask_id(lastTask);
		taskbean.setTask_state(90);
		tasks.add(taskbean);
		getNewRequest(lastTask,maybegintime,car.getSeats());
		lastTask++;
		Lg.debug(lastTask);
		return;
	}
	

	private void getNewRequest(Integer task_id,Date maybegintime, Integer seats) {
		RequestBean requestbean;
		seats=(int) (Math.random()*(seats-1)+1);
		int [] passenger=new int[seats];
		for (int i = 0; i < passenger.length; i++) {			
			while(true)
			{
				passenger[i]=users.get((int) (Math.random()*users.size())).getUser_id();
				boolean flag=false;
				for (int j = 0; j < i; j++) {
					if(passenger[i]==passenger[j]){
						flag=true;
						break;
					}
				}
				if(flag==false){
					break;
				}
			}
			Date requesttime=changetime(maybegintime,-12*60*60,2*60*60);
			Date needstarttime=changetime(maybegintime,-2*60*60,2*60*60);
			Date needendtime=changetime(maybegintime,2*60*60,2*60*60);
			String needduringtime=(int)(Math.random()*60)+"分钟";
			String needplace=places[(int) (Math.random()*places.length)];
			String needreason="去"+needplace+"。";
			Integer needseats=1;
			Integer request_task_id=task_id;
			Integer request_state=20;
			requestbean=new RequestBean();
			requestbean.setNeedduringtime(needduringtime);
			requestbean.setNeedendtime(needendtime);
			requestbean.setNeedplace(needplace);
			requestbean.setNeedreason(needreason);
			requestbean.setNeedseats(needseats);
			requestbean.setNeedstarttime(needstarttime);
			requestbean.setRequest_id(lastRequest);
			requestbean.setRequest_state(request_state);
			requestbean.setRequest_task_id(request_task_id);
			requestbean.setRequest_user_id(passenger[i]);
			requestbean.setRequesttime(requesttime);
			Lg.debug(requestbean);
			requests.add(requestbean);
			lastRequest++;
		}
	}










	void init(){
		getAllCar();
		getAllDriver();
		getAllUser();
		getLastRequest();
		getLastTask();
		jdbc=new JdbcClass();
		con=jdbc.openOracle();
		
	}
	void getAllCar(){
		if(cars==null){
			cars=sqlSession.selectList("Test.getCar");
		}
	}
	void getAllDriver(){
		if(drivers==null){
			drivers=sqlSession.selectList("Test.getDriver");
		}
	}
	void getAllUser(){
		if(users==null){
			users=sqlSession.selectList("Test.getUser");
		}
	}
	void getLastRequest(){
		if(lastRequest==0){
			Integer a=sqlSession.selectOne("Test.getLastRequest");
			
			
			if(a==null){
				lastRequest=1000000;
				return;
			}	
			if(a<1000000){
				lastRequest=1000000;
				return;
			}
			lastRequest=a;
		}
	}
	void getLastTask(){
		if(lastTask==0){
			Integer a=sqlSession.selectOne("Test.getLastTask");
			if(a==null){
				Lg.debug("task null");
				lastTask=1000000;
				return;
			}	
			if(a<1000000){
				lastTask=1000000;
				return;
			}
			lastTask=a;
		}
	}
	
	private static Date randomDate(Date beginDate,Date endDate){
		try {

			Date start = beginDate;
			Date end = endDate;
			Lg.debug("starttime"+start);
			Lg.debug("endtime"+end);
			if(start.getTime() >= end.getTime()){
				return null;
			}
			
			long date = random(start.getTime(),end.getTime());
			long time=date%(1000*60*60*24);
			date-=time;
			time=(long) (Math.random()*1000*60*60*9+1000*60*60*8);
			date+=time;
			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static long random(long begin,long end){
		long rtn = begin + (long)(Math.random() * (end - begin));
		if(rtn == begin || rtn == end){
			return random(begin,end);
		}
		return rtn;
	}
	
	private static Date changetime(Date date,long change,long rnd){
		long d=date.getTime();
		d=(long) (d+(change+rnd*Math.random()-rnd/2)*1000);
		return new Date(d);
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
