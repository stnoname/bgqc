package com.bgqc.service.base;

import java.util.Map;

import com.bgqc.beans.UserBean;
//接口，必须有
public interface LoginService {
	public UserBean login(UserBean userbean);
}
