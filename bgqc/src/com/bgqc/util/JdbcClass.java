package com.bgqc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;





//import java.sql.*;


public class JdbcClass {
	public Connection con = null;// 创建一个数据库连接
    public PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
    public ResultSet result = null;// 创建一个结果集对象
    static String url = "jdbc:oracle:thin:@172.31.43.3:1521:orcl";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
    //static String url = "jdbc:oracle:oci:@ORCL";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
    static String user = "grp3";// 用户名,系统默认的账户名
    static String password = "grp3";// 你安装时选设置的密码
    
    static {
    	try {
    		//Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("jdbc加载成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}// 加载Oracle驱动程序
    }
    
	public Connection openOracle() {
		System.out.println("连接前");
        try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 获取连接
        System.out.println("连接成功！");
	    return con;
	}

	public void closePre() {
		try
        {
            if (pre != null)
                pre.close();
            System.out.println("预编译语句对象已关闭！");
        }
        catch (Exception e)
        {
        	System.out.println("预编译语句对象关闭出现问题！");
            e.printStackTrace();
        }
	}
	public void closeResult() {
		try
        {
            if (result != null)
            	result.close();
            System.out.println("结果集对象已关闭！");
        }
        catch (Exception e)
        {
        	System.out.println("结果集对象关闭出现问题！");
            e.printStackTrace();
        }
	}
	public void closeOracle() {
		try
        {
            if (con != null)
                con.close();
            System.out.println("数据库连接已关闭！");
        }
        catch (Exception e)
        {
        	System.out.println("数据库连接关闭出现问题！");
            e.printStackTrace();
        }
	}
	public void closeAll(){
		closeResult();
		closePre();
		closeOracle();
		
	}
	public ResultSet selectOracle(String sql,List<String> canshu){

		con =openOracle();
		/*InitialContext ctx = null;
		System.out.println("连接池1");
		try {
			ctx = new InitialContext();
		} catch (NamingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		DataSource ds = null;
		System.out.println("连接池2");
		try {
			ds = (DataSource) ctx.lookup("java:comp/env/jdbcoracle");
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("连接池3");
		try {
			con = ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("连接池4");*/
		try {
			pre = con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("pre输入sql出错");
			e.printStackTrace();
			
		}
		System.out.println("sql语句为"+sql);
		System.out.println("参数数量为"+ (canshu.size()));
		for(int i=0;i<canshu.size();i++){
			try {
				System.out.println("pre写入参数，第" + (i+1) + "个,内容为"+canshu.get(i));
				pre.setObject(i+1,(String)(canshu.get(i)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("pre写入参数有误，第" + (i+1) + "个,内容为"+canshu.get(i));
				e.printStackTrace();
			}

		}
		
		
		
		try {
			result = pre.executeQuery();
			System.out.println("pre查找成功");
		} catch (SQLException e) {
			System.out.println("pre查找出错");
			e.printStackTrace();
		}
		
		
		return result;
	}
	public boolean UpdataOracle(String sql,List<String> canshu){
		con =openOracle();
		boolean flag=false;
		try {
			pre = con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("pre输入sql出错");
			e.printStackTrace();
			
		}
		System.out.println("sql语句为"+sql);
		for(int i=0;i<canshu.size();i++){
			try {
				
				pre.setObject(i+1,(String)(canshu.get(i)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("pre写入参数有误，第" + (i+1) + "个,内容为"+canshu.get(i));
				e.printStackTrace();
			}

		}
		
		int flagint = 0;
		try {
			flagint = pre.executeUpdate();
			
			System.out.println("sql语句写入成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flagint>0){
			flag=true;
			}
		closeAll();
		return flag;
	}
	/*@Test
	public void test(){
		con =openOracle();
		String sql="insert into hsdata (id,title,text,writer,edittime,state) values(hsdatas.nextval,?,?,?,?,?)";
		
		try {
			pre = con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 1000000; i++) {
			try {
				pre.setString(1,"title"+i);
				pre.setString(2,"text"+i);
				pre.setString(3,"name"+i);
				pre.setString(4,TimeTools.Yymmddhhmmss(new Date()));
				pre.setString(5,"已审核");
				pre.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
				if(i%100==0){	
					System.out.println(i);
				}
				
			}
		}
		closeAll();
	}*/
	
}
