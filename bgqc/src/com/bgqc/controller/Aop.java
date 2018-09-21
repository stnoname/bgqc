package com.bgqc.controller;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Controller;

import com.bgqc.beans.UserBean;
import com.bgqc.util.Lg;

/*
 * @author	李挺
 * 本类用于在controller层，通过aop的方式来控制用户权限
 * 
 */

@Controller
@Aspect
public class Aop {
	/*
	 * @author	李挺
	 * 本方法切入了UserAction,权限是20的才能访问，即管理员。
	 * 暂时每增加一个action，都得加个这样的方法，即不同权限。
	 * 甚至可能某些action里，不同方法的权限也不同
	 * 本方法调用aop方法
	 */
	@Around("execution(* com.bgqc.controller.UserAction.*(..))"
			+ " or execution(* com.bgqc.controller.CarAction.*(..))"
			+ " or execution(* com.bgqc.controller.TaskAction.*(..))"
			+ " or execution(* com.bgqc.controller.DriverAction.*(..))")
	public Object user(ProceedingJoinPoint pjp){
		int[] needauth={20};
		return aop(pjp,needauth);
	}
	
	/*
	 * @author	李挺
	 * 本方法是权限验证的核心方法，为了复用写成私有
	 * 通过获得方法的参数列表，获得方法的session，再从中获得user对象，进行权限判断
	 * 未登录的用户已经被拦截器直接过滤掉了，即SessionFilter类
	 * 所以本方法只验证权限即可
	 */
	
	
	private Object aop(ProceedingJoinPoint pjp,int[] needauth) {
		Lg.debug("开始验证权限");
		Object[] args = pjp.getArgs();
		for (int i = 0; i < args.length; i++) {
			if(args[i] instanceof HttpSession){
				HttpSession session=(HttpSession)args[i];
				UserBean userbean=(UserBean) session.getAttribute("user");
				for (int j = 0; j < needauth.length; j++) {
					if(userbean.getAuth()==needauth[j]){
						Object ret = null;
						try {
							Lg.debug("权限验证成功，权限为"+userbean.getAuth());
							ret = pjp.proceed(args);
						} catch (Throwable e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return ret;
					}
				}
			}
		}
		Lg.debug("权限验证失败");
		return null;
	}
}
