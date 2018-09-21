package com.bgqc.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import com.bgqc.beans.CarBean;
import com.bgqc.beans.MsgBean;
import com.bgqc.service.base.CarService;

/*
 * author	马福宝
 * Car主service类，查、增、删、改、4个功能，都被相应的action调用
 */

@Service
public class CarServiceImpl implements CarService{
	
	@Resource
	private SqlSessionTemplate sqlSession;
	
	//查看车辆，直接跳转无处理
	@Override
	public List<CarBean> getCar(CarBean carbean) {
		
		return sqlSession.selectList("Car.selectAll",carbean);
	}
	//新增用户前，看有没有重复的车牌号，因为必须保证车牌号唯一
	@Override
	public MsgBean addCar(CarBean carbean) {
		List<CarBean> l=sqlSession.selectList("Car.selectNumber",carbean);
		String s,t;
		if(l.size()>0){
			s="抱歉，新增车辆车牌号"+l.get(0).getCar_number()+"已存在，请核对后再试！";
			t="失败";
		}else{
			sqlSession.insert("Car.insert", carbean);
			s="插入成功";
			t="成功";
		}
		return new MsgBean(t,s);
	}
	//修改车辆
	@Override
	public MsgBean editCar(CarBean carbean) {
		int num=sqlSession.update("Car.update", carbean);
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

	//删除用户之前，为了避免丢失历史信息，不允许删除有过历史信息的车辆
	@Override
	public MsgBean delCar(CarBean carbean) {
		int r;
		String t,s;


		r=sqlSession.selectOne("Car.delfindinrequests", carbean);
		if(r>0){
			t="失败";
			s="抱歉，该用户有过请求，不能删除，请选择停用";
		}else{
			r=sqlSession.delete("Car.del", carbean);
			if(r==0){
				t="失败";
				s="未知错误";
			}else{
				t="成功";
				s="删除成功";
			}
		}

		return new MsgBean(t,s);
	}
}
	
	
