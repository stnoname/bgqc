package com.bgqc.service;

import java.util.List;
import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import com.bgqc.beans.MsgBean;
import com.bgqc.beans.UserBean;
import com.bgqc.service.base.ChangeUserService;
import com.bgqc.util.Lg;
@Service
public class ChangeUserServiceImpl implements ChangeUserService{
	@Resource
	private SqlSessionTemplate sqlSession;
	@Override
	public MsgBean changeUser(UserBean userbean,UserBean newuserbean) {
		Lg.debug("service");
		String s,t;
		List<UserBean> l=sqlSession.selectList("User.selectmobile_phone",newuserbean);
		newuserbean.setUser_id(userbean.getUser_id());
		Lg.debug("oldbeanbbbbbb"+userbean);
		Lg.debug("newbeanbbbbb"+newuserbean);
		if(l.size()>1){
			s="抱歉，新电话号码与"+l.get(0).getName()+"相同，请核对后再试！";
			t="失败";
		}else if(l.size()==1){
			if(l.get(0).getUser_id().equals(userbean.getUser_id())){
				int i = sqlSession.update("User.changeuser", newuserbean);
				if(i>0){
					t="成功";
					s="修改成功";
					userbean.setName(newuserbean.getName());
					userbean.setMobile_phone(newuserbean.getMobile_phone());
					userbean.setOffice_phone(newuserbean.getOffice_phone());
					userbean.setV_phone(newuserbean.getV_phone());
					userbean.setDepartment(newuserbean.getDepartment());
					userbean.setJob(newuserbean.getJob());
				}else{
					t="失败";
					s="未知错误，修改失败";
				}
			}else{
				s="抱歉，新电话号码与"+l.get(0).getName()+"相同，请核对后再试！";
				t="失败";
			}
		}else{
			int i= sqlSession.update("User.changeuser", newuserbean);
			if(i>0){
				t="成功";
				s="修改成功";
				userbean.setName(newuserbean.getName());
				userbean.setMobile_phone(newuserbean.getMobile_phone());
				userbean.setOffice_phone(newuserbean.getOffice_phone());
				userbean.setV_phone(newuserbean.getV_phone());
				userbean.setDepartment(newuserbean.getDepartment());
				userbean.setJob(newuserbean.getJob());
			}else{
				t="失败";
				s="未知错误，修改失败";
			}
		}
		
		return new MsgBean(t,s);
	}	
}		
	
	

