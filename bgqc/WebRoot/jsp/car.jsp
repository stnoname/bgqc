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
		<%@ include file="head.html" %>
		<title>车辆管理</title>
	</head>

	<body class="layui-layout-body">
	
	<!-- display:none --> 
	<form id="carform" class="layui-form layui-form-pane" action='' style="display:none ;padding-top:20px;width:800px" lay-filter="carform">
			<div class="layui-row layui-col-space30 layui-container" style="width:100%">
			<input type="hidden" name="car_id" id="car_id"/>
			<div class="layui-col-md6">
			<div class="layui-form-item">
				<label class="layui-form-label">*车牌号</label>
				<div class="layui-input-block">
					<input type="text" name="car_number" required lay-verify="required|car_number" 
						placeholder="请输入车牌号" autocomplete="off" class="layui-input" id="carformnumber"></input>
				</div>
			</div>	
			
			<div class="layui-form-item">
				<label class="layui-form-label">*座位数</label>
				<div class="layui-input-block">
					<input type="text" name="seats" required lay-verify="required|number" 
						placeholder="请输入座位数" autocomplete="off" class="layui-input" id="carformseats"></input>
				</div>
			</div>	
			
			
			
			<div class="layui-form-item">
				<label class="layui-form-label">车辆状态</label>
				<div class="layui-input-block">
					<input type="radio" name="car_state" value=0 title="空闲" checked>
					<input type="radio" name="car_state" value=10 title="待出发" >
					<input type="radio" name="car_state" value=20 title="使用中">
				</div>
			</div>
			</div>
			
			<div class="layui-col-md6">
			<div class="layui-form-item">
				<label class="layui-form-label">是否可用</label>
				<div class="layui-input-block">
					<input type="radio" name="car_enable" value=0 title="可用" checked>
					<input type="radio" name="car_enable" value=10 title="暂时不可用">
					<input type="radio" name="car_enable" value=20 title="停用">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">不可用原因</label>
				<div class="layui-input-block">
					<input type="text" name="car_reason" lay-verify="string"
						placeholder="请输入原因" autocomplete="off" class="layui-input" id="carformreason"></input>
				</div>
			</div>
			</div>
			</div>
			<div class="layui-row">
			<div class="layui-form-item layui-col-md6 layui-col-md-offset3">
    			<div class="layui-input-block">
      				<button class="layui-btn layui-submit" lay-submit="" lay-filter="submit">提交</button>
      				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
    			</div>
  			</div>
  			</div>
		</form>

			<%@ include file="tophtml.html"%>
			<%@ include file="lefthtml.html"%>
			<div class="layui-body">
				<div style="padding:20px">
					<h2>车辆管理</h2>
					<table id="cartable" class="layui-table" lay-filter="cartable">

					</table>
		
				</div>
			</div>
		</div>
	</body>
	
	
	<script src="../js/car.js"></script>
		<script type="text/html" id="barIn">
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
		<a class="layui-btn layui-btn-xs" lay-event="edit">
			<i class="layui-icon">&#xe642;</i>
			编辑</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">
			<i class="layui-icon">&#xe640</i>
			删除</a>
	</script>
	
	<script type="text/html" id="barOn">
		<div>
			<button class="layui-btn layui-btn-sm" lay-event="add">
			<i class="layui-icon">&#xe608;</i>
			新增车辆</button>
			
			<div class="layui-inline">
				&nbsp;&nbsp;&nbsp;
				<input type="checkbox" name="enable" title="包含停用" id="enablebox" lay-filter="enablebox" lay-skin="primary">
			</div>
			<div class="layui-inline">
				<input class="layui-input" name="id" id="findname" autocomplete="off">
			</div>
			<button class="layui-btn layui-btn-sm" lay-event="reload">
			<i class="layui-icon">&#xe615;</i>
		</div>
	</script>
</html>
