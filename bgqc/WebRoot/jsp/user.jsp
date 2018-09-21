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
		<title>用户管理</title>
		<%@ include file="head.html" %>
	
	</head>

	<body class="layui-layout-body">
	
	<!-- display:none --> 
	<form id="userform" class="layui-form layui-form-pane" action='' style="display:none ;padding-top:20px;width:800px" lay-filter="userform">
			<div class="layui-row layui-col-space30 layui-container" style="width:100%">
			<input type="hidden" name="user_id"/>
			<div class="layui-col-md6">
			<div class="layui-form-item ">
				<label class="layui-form-label">*姓名</label>
				<div class="layui-input-block">
					<input type="text" name="name" required lay-verify="required|username" 
						placeholder="请输入用户姓名" autocomplete="off" class="layui-input" id="userformname"></input>
				</div>
			</div>	
			<div class='layui-form-item'>
				<label class="layui-form-label">*权限：</label>
				<div class="layui-input-block">
      				<select name="auth" lay-verify="required" id="authselect">
	        			<option value=""></option>
        				<option value="0">用户</option>
        				<option value="10">司机</option>
        				<option value="20">管理员</option>
      				</select>
    			</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">*手机号</label>
				<div class="layui-input-block">
					<input type="text" name="mobile_phone" required lay-verify="required|mphone" 
						placeholder="请输入手机号" autocomplete="off" class="layui-input" id="userformmobile_phone"></input>
				</div>
			</div>	
			<div class="layui-form-item">
				<label class="layui-form-label">座机号</label>
				<div class="layui-input-block">
					<input type="text" name="office_phone" lay-verify="ophone"
						placeholder="请输入座机号" autocomplete="off" class="layui-input" id="userformoffice_phone"></input>
				</div>
			</div>	
			<div class="layui-form-item">
				<label class="layui-form-label">V网小号</label>
				<div class="layui-input-block">
					<input type="text" name="v_phone" lay-verify="vphone" 
						placeholder="请输入小号" autocomplete="off" class="layui-input" id="userformv_phone"></input>
				</div>
			</div>	
			
			</div>
			
			<div class="layui-col-md6">
			<div class="layui-form-item">
				<label class="layui-form-label">部门</label>
				<div class="layui-input-block">
					<input type="text" name="department" lay-verify="string"
						placeholder="请输入部门" autocomplete="off" class="layui-input" id="userformdepartment"></input>
				</div>
			</div>	
			<div class="layui-form-item">
				<label class="layui-form-label">职务</label>
				<div class="layui-input-block">
					<input type="text" name="job" lay-verify="string"
						placeholder="请输入职务" autocomplete="off" class="layui-input" id="userformjob"></input>
				</div>
			</div>	
			<div class="layui-form-item">
				<label class="layui-form-label">是否停用</label>
				<div class="layui-input-block">
					<input type="radio" name="user_enable" value=0 title="可用" checked>
					<input type="radio" name="user_enable" value=10 title="停用">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">是否在途</label>
				<div class="layui-input-block">
					<input type="radio" name="user_state" value=0 title="空闲" checked>
					<input type="radio" name="user_state" value=10 title="在途">
				</div>
			</div>
			</div>
			</div>
			<div class="layui-row">
			<div class="layui-form-item layui-col-md6 layui-col-md-offset3">
    			<div class="layui-input-block">
      				<button class="layui-btn layui-submit " lay-submit="" lay-filter="submit">提交</button>
      				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
    			</div>
  			</div>
  			</div>
		</form>
		
			<%@ include file="tophtml.html"%>
			<%@ include file="lefthtml.html"%>
			<div class="layui-body">
				<div style="padding:20px">
					<h2>用户管理</h2>
					<table id="usertable" class="layui-table" lay-filter="usertable">
					</table>
				</div>
			</div>
		</div>
	</body>
	
	<script src="../js/user.js"></script>
	<script type="text/html" id="barIn">

		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs ltblue-del" lay-event="repass">重置密码</a>

	</script>
	
	<script type="text/html" id="barOn">
		<div>
			<button class="layui-btn layui-btn-sm" lay-event="add">
			<i class="layui-icon">&#xe608;</i>
			新增用户</button>
			
			<div class="layui-inline">
				&nbsp;&nbsp;&nbsp;
				<input type="checkbox" name="enable" title="包含停用" id="enablebox" lay-filter="enablebox" lay-skin="primary">
			</div>
			<div class="layui-inline">
				
				<input class="layui-input" name="id" id="findname" autocomplete="off">
			</div>
			<button class="layui-btn layui-btn-sm" lay-event="reload">
			<i class="layui-icon">&#xe615;</i>
			搜索</button>
		</div>
	</script>
</html>
