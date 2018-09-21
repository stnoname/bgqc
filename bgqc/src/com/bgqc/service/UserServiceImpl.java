package com.bgqc.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.bgqc.beans.MsgBean;
import com.bgqc.beans.UserBean;
import com.bgqc.service.base.UserService;
/*
 * author	李挺
 * user主service类，查、增、删、改、重置密码一共5个功能，都被相应的action调用
 */

@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private SqlSessionTemplate sqlSession;
	
	//查看用户，直接跳转无处理
	@Override
	public List<UserBean> getUser(UserBean userbean) {
		
		return sqlSession.selectList("User.selectAll",userbean);
	}
	//新增用户前，看有没有重复的手机号，因为必须保证手机号唯一
	@Override
	public MsgBean addUser(UserBean userbean) {
		List<UserBean> l=sqlSession.selectList("User.selectPhone",userbean);
		String s,t;
		if(l.size()>0){
			s="抱歉，新增用户电话号码与"+l.get(0).getName()+"相同，请核对后再试！";
			t="失败";
		}else{
			sqlSession.insert("User.insert", userbean);
			s="插入成功";
			t="成功";
		}
		return new MsgBean(t,s);
	}
	//修改用户
	@Override
	public MsgBean editUser(UserBean userbean) {
		int num=sqlSession.update("User.update", userbean);
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

	//删除用户之前，为了避免丢失历史信息，不允许删除是司机的用户和有过历史信息的用户
	@Override
	public MsgBean delUser(UserBean userbean) {
		int r;
		String t,s;
		r=sqlSession.selectOne("User.delfindindrivers", userbean);
		if(r>0){
			t="失败";
			s="抱歉，该用户为现存司机，不能删除，请选择停用";
		}else{
			r=sqlSession.selectOne("User.delfindinrequests", userbean);
			if(r>0){
				t="失败";
				s="抱歉，该用户有过请求，不能删除，请选择停用";
			}else{
				r=sqlSession.delete("User.del", userbean);
				if(r==0){
					t="失败";
					s="未知错误";
				}else{
					t="成功";
					s="删除成功";
				}
			}
		}
		return new MsgBean(t,s);
	}
	
	//重置密码，直接修改为123456的md5码
	@Override
	public MsgBean repassUser(UserBean userbean) {
		int num=sqlSession.update("User.repass", userbean);
		String t,s;
		if(num>0){
			t="成功";
			s="重置密码成功";
		}else{
			t="失败";
			s="未知错误，重置密码失败";
		}
		
		return new MsgBean(t,s);
	}
	//测试用
	@Test
	public void aaa(){
		String md5Password = DigestUtils.md5DigestAsHex("123456".getBytes());
		System.out.println(md5Password);
	}
}
