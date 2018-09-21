package com.bgqc.util;

import java.util.List;

import com.bgqc.beans.LayuiBean;
import com.github.pagehelper.Page;


/*
 * author	李挺
 * 传入一个list，返回一个LayuiBean，用于返回信息（含分页）
 */
public class Lay {
	public static <T> LayuiBean<T> rt(List<T> list) {
		Page<T> pageInfo =  (Page<T>) list;
		LayuiBean<T> lb=new LayuiBean<T>(0, "", pageInfo.getTotal(), list);
		Lg.debug(lb.toString());
		pageInfo.close();
		
		return lb;
	}
	public static <T> LayuiBean<T> rtnopage(List<T> list) {
	
		LayuiBean<T> lb=new LayuiBean<T>(0, "", list.size(), list);
		Lg.debug(lb.toString());
	
		
		return lb;
	}
}
