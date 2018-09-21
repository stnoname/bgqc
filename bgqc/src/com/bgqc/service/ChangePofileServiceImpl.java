package com.bgqc.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.bgqc.beans.MsgBean;
import com.bgqc.beans.UserBean;
import com.bgqc.service.base.ChangePofileService;
@Service
public class ChangePofileServiceImpl implements ChangePofileService{
	@Resource
	private SqlSessionTemplate sqlSession;
	@Override
	public MsgBean changePassword(Map<String,Object> map) {
		String s,t;
		String oldpassword=DigestUtils.md5DigestAsHex(((UserBean)map.get("user")).getPassword().getBytes());
		String newpassworda=DigestUtils.md5DigestAsHex(((String)map.get("newpassworda")).getBytes());
		String newpasswordb=DigestUtils.md5DigestAsHex(((String)map.get("newpasswordb")).getBytes());
		UserBean userbean=(UserBean)(map.get("user"));
		if(newpassworda.equals(newpasswordb)==false){
			t="失败";
			s="两次密码不一致";
		}else{
			userbean.setPassword(oldpassword);
			List<UserBean> l=sqlSession.selectList("User.login",userbean);
			if(l.size()==0){
				t="失败";
				s="原密码错误，请确认后重试";
			}else{
				userbean.setPassword(newpassworda);
				int i=sqlSession.update("User.changepassword",userbean);
				if(i==0){
					t="失败";
					s="未知错误";
				}else{
					t="成功";
					s="密码修改完成";
				}
			}
		}
		
		
		return new MsgBean(t,s);
	}
	
}
