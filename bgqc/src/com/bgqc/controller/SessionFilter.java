package com.bgqc.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

import com.bgqc.util.Lg;

@WebFilter(urlPatterns = "*.do")
/*
 * 过滤所有的.do请求，并进行判断
 * 看session里，有没有user，有的话说明登录了，放行，否则判断访问的方法
 * 对login.do放行，否则没法登录了，其他的拦住
 */
public class SessionFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Lg.debug("开始过滤");
		String[] notFilter = new String[] { "login.do","Android","test.do"};
		String strUri = request.getRequestURI() ;
		Lg.debug("uri"+strUri);
		HttpSession session=request.getSession();
		if(session.getAttribute("user")!=null){
			filterChain.doFilter(request, response);
			return;
		}
		for (int i = 0; i < notFilter.length; i++) {
			if(strUri.indexOf(notFilter[i])>-1){
				filterChain.doFilter(request, response);
				return;
			}
		}
		Lg.debug("被拦截了，貌似没有登录，请求是；"+strUri);
	}

}
