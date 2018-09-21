package com.bgqc.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.bgqc.beans.DriverBean;
import com.bgqc.beans.MsgBean;
import com.bgqc.service.base.DriverService;
import com.bgqc.util.Lg;

@Service
public class DriverServiceImpl implements DriverService {

	@Resource
	private SqlSessionTemplate sqlSession;

	// 查看用户，直接跳转无处理
	@Override
	public List<DriverBean> getDrivers(DriverBean driverbean) {
		// TODO Auto-generated method stub
		Lg.debug("driverservice");
		Lg.debug(driverbean);
		return sqlSession.selectList("Driver.selectAll", driverbean);
	}

	// 查看不在司机表中的司机
	@Override
	public List<DriverBean> getNewDriver(DriverBean driversbean) {
		// TODO Auto-generated method stub
		Lg.debug("driverservice");
		return sqlSession.selectList("Driver.selectNewDriver", driversbean);
	}

	// 新增用户前

	@Override
	public MsgBean addDrivers(DriverBean driversbean) {
		int num = sqlSession.insert("Driver.insert", driversbean);
		String s, t;
		if (num > 0) {
			s = "插入成功";
			t = "成功";
		} else {
			s = "未知错误";
			t = "插入失败";
		}
		return new MsgBean(t, s);
	}

	// 修改用户
	@Override
	public MsgBean editDrivers(DriverBean driversbean) {
		int num = sqlSession.update("Driver.update", driversbean);
		String t, s;
		if (num > 0) {
			t = "成功";
			s = "修改成功";
		} else {
			t = "失败";
			s = "未知错误，修改失败";

		}
		return new MsgBean(t, s);
	}

	// 删除司机，为避免数据丢失，只能删除没接过单的司机
	@Override
	public MsgBean delDrivers(DriverBean driversbean) {
		int r;
		String t, s;
		r = sqlSession.selectOne("Driver.delfindintasks", driversbean);
		if (r > 0) {
			t = "失败";
			s = "抱歉，该司机接过订单，不能删除，请选择停用";
		} else {
			r = sqlSession.delete("Driver.del", driversbean);
			if (r == 0) {
				t = "失败";
				s = "未知错误";
			} else {
				t = "成功";
				s = "删除成功";
			}
		}

		return new MsgBean(t, s);
	}
}
