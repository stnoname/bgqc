package com.bgqc.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bgqc.beans.MsgBean;
import com.bgqc.beans.RequestBean;
import com.bgqc.service.base.RequestsService;


@Service
public class RequestsServiceImpl implements RequestsService {

	@Resource
	private SqlSessionTemplate sqlSession;
	@Override
	public List<RequestBean> getRequests(RequestBean RequestBean) {
		// TODO Auto-generated method stub
//		requestBean.
		System.out.println(RequestBean);
		return sqlSession.selectList("Requests.selectAll", RequestBean);
	}

	@Override
	public MsgBean addRequests(RequestBean RequestBean) {
		// TODO Auto-generated method stub
		String t,s;
		if(checkTime(RequestBean)){
		RequestBean.setRequest_task_id(0);
		int num=sqlSession.insert("Requests.insert", RequestBean);
		if(num>0){
			t="成功";
			s="添加成功";
		}else{
			t="失败";
			s="未知错误，添加失败";
		}
		}else{
			t="失败";
			s="日期填写不符合逻辑，添加失败";
		}
		
		return new MsgBean(t,s);
	}

	@Override
	public MsgBean editRequests(RequestBean RequestBean) {
		// TODO Auto-generated method stub
		RequestBean.setRequest_task_id(0);
		int num=sqlSession.update("Requests.update", RequestBean);
		String t,s;
		if(num>0){
			t="成功";
			s="修改成功";
		}else{
			t="失败";
			s="未知错误，修改失败";
		}
		
		return new MsgBean(t,s);
	}

	@Override
	public MsgBean delRequests(RequestBean RequestBean) {
		// TODO Auto-generated method stub
		int num=sqlSession.delete("Requests.delete", RequestBean);
		String t,s;
		if(num>0){
			t="成功";
			s="删除成功";
		}else{
			t="失败";
			s="未知错误，删除失败";
		}
		
		return new MsgBean(t,s);
	}

	@Override
	public MsgBean repassRequests(RequestBean RequestBean) {
		// TODO Auto-generated method stub
		int num=sqlSession.delete("Requests.delete", RequestBean);
		String t,s;
		if(num>0){
			t="成功";
			s="删除成功";
		}else{
			t="失败";
			s="未知错误，删除失败";
		}
		
		return new MsgBean(t,s);
	}

	private Boolean checkTime(RequestBean RequestBean){
		int i = RequestBean.getNeedstarttime().compareTo(RequestBean.getNeedendtime());
		if(i>0){
		return false;
		}else{
			return true;
		}
	}
}
