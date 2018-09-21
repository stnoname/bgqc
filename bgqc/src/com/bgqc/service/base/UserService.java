package com.bgqc.service.base;

import java.util.List;

import com.bgqc.beans.MsgBean;

import com.bgqc.beans.UserBean;
//接口，必须有
public interface UserService {
	public List<UserBean> getUser(UserBean userbean);

	public MsgBean addUser(UserBean userbean);

	public MsgBean editUser(UserBean userbean);

	public MsgBean delUser(UserBean userbean);

	public MsgBean repassUser(UserBean userbean);

}
