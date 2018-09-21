<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>首页</title>
		<%@ include file="head.html" %>
	</head>

	<body class="layui-layout-body">
	
			<%@ include file="tophtml.html"%>
			<%@ include file="lefthtml.html"%>
			<div class="layui-body">
				<div style="padding:20px">
					<div class="layui-form-item layui-form-pane">
						<label class="layui-form-label">查询时间段</label>
						<div class="layui-input-block">
							<input type="text" name="timefromto" placeholder="请选择查询时间段" 
								class="layui-input" id="timefromto" readonly></input>
						</div>
					</div>	
					
					<div class="layui-tab" lay-filter="maintab">
  						<ul class="layui-tab-title">
					    	<li class="layui-this">用户统计</li>
					    	<li>司机统计</li>
					    	<li>车辆统计</li>
					    	
					  	</ul>
					  	 <div class="layui-tab-content">
						    <div class="layui-tab-item layui-show">
								<div style="height:240px" id="userpic" class="layui-container">
								
								</div>
								
								<table id="usertable" class="layui-table" lay-filter="usertable">
								</table>
							</div>
						    <div class="layui-tab-item">
						    	<div style="height:240px" id="driverpic" class="layui-container">
								
								</div>
								<table id="drivertable" class="layui-table" lay-filter="drivertable">
								</table>
							</div>
						    <div class="layui-tab-item">

								<div style="height:240px" id="carpic" class="layui-container">
								
								</div>
								
								<table id="cartable" class="layui-table" lay-filter="cartable">
								</table>
							</div>
						 </div>
						
					</div>
				</div>
			</div>
		</div>
	</body>

	<script src="../js/other/echarts.simple.min.js"></script>
	<script src="../js/report.js"></script>

</html>
