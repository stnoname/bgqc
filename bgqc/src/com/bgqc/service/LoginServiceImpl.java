package com.bgqc.service;

import java.util.List;


import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.bgqc.beans.UserBean;
import com.bgqc.service.base.LoginService;
/*
 * author	李挺
 * 用于登录的service，首先把用户录入的密码进行md5加密，这样才能跟后台比较
 * 由于要同时返回登录是否成功和用户信息，所以用map包一下
 */

@Service
public class LoginServiceImpl implements LoginService{
	@Resource
	private SqlSessionTemplate sqlSession;
	@Override
	public UserBean login(UserBean userbean) {
		String md5password=DigestUtils.md5DigestAsHex(userbean.getPassword().getBytes());
		userbean.setPassword(md5password);
		List<UserBean> l=sqlSession.selectList("User.login",userbean);
		UserBean userbeanreturn = new UserBean();
		if(l.size()>0){
			userbeanreturn=l.get(0);
		}
		
		return userbeanreturn;
	}
	
}
