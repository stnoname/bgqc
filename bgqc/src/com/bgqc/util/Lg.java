package com.bgqc.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/*
 * @author	李挺
 * 用于输出的方法，相当于sop
 */

public class Lg {
	static Logger log=LogManager.getLogger("my log");
	public static void trace(Object s){
		if(s!=null){
			log.trace("ltblue say:   "+s.toString());
		}
	}
	public static void error(Object s){
		if(s!=null){
			log.error("ltblue say:   "+s.toString());
		}
	}
	public static void info(Object s){
		if(s!=null){
			log.info("ltblue say:   "+s.toString());
		}
	}
	public static void debug(Object s){
		if(s!=null){
			log.debug("ltblue say:   "+s.toString());
		}
	}
	public static void warn(Object s){
		if(s!=null){
			log.warn("ltblue say:   "+s.toString());
		}
	}
	public static void fatal(Object s){
		if(s!=null){
			log.fatal("ltblue say:   "+s.toString());
		}
	}
	
}
