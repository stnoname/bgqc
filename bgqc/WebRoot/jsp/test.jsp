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
		<title>压力测试</title>
		<%@ include file="head.html" %>
	</head>

	<body class="layui-layout-body">
	
			<%@ include file="tophtml.html"%>
			<%@ include file="lefthtml.html"%>
			<div class="layui-body">
				<div style="padding:20px">
					<h2>压力测试</h2>
					<form id="userform" class="layui-form layui-form-pane" action='' style="padding-top:20px;width:800px" lay-filter="userform">
						<div class="layui-form-item ">
							<label class="layui-form-label">*时间段</label>
							<div class="layui-input-block">
								<input readonly type="text" name="timefromto" required lay-verify="required" 
									placeholder="请选择随机生成的时间段" autocomplete="off" class="layui-input" id="timefromto"></input>
							</div>
						</div>
						<div class="layui-form-item ">
							<label class="layui-form-label">*任务条数</label>
							<div class="layui-input-block">
								<input type="text" name="num" required lay-verify="required|number" 
									placeholder="请输入随机生成的任务条数" autocomplete="off" class="layui-input" id="num"></input>
							</div>
						</div>		
						
						
						<button class="layui-btn" lay-filter="submit" lay-submit="">提交</button>
					</form>
				</div>
			</div>
		</div>
	</body>
	<script src="../js/test.js"></script>
</html>
