<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>

<title>司机管理</title>

<%@ include file="head.html" %>

</head>

<body>

	
	<!-- display:none -->
	<form id="driversform" class="layui-form layui-form-pane" action=''
		style="padding-top:20px;width:100%;display:none" lay-filter="driversform">
		<div class="layui-row layui-col-space30 layui-container"
			style="width:100%">
			<input type="hidden" name="user_id" /> 
			<input type="hidden" name="driver_user_id" />
			<input type="hidden" name="driver_id" />
			<div class="layui-col-md6">
				<div class="layui-form-item">
					<label class="layui-form-label">*司机姓名</label>
					<div class="layui-input-block">
					<input class="layui-input locked" id="userformname" name="name" placeholder="请选择司机" readonly></input>
					
					<!-- 
						<input type="text" name="name" required
							lay-verify="required|username" placeholder="请输入用户姓名"
							autocomplete="off" class="layui-input locked" id="userformname"></input>
							 -->
							
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">*手机号</label>
					<div class="layui-input-block">
						<input type="text" name="mobile_phone" required
							lay-verify="required|mphone" placeholder="请输入手机号"
							autocomplete="off" class="layui-input locked locked1" id="userformmobile_phone"></input>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">座机号</label>
					<div class="layui-input-block">
						<input type="text" name="office_phone" lay-verify="ophone"
							placeholder="请输入座机号" autocomplete="off" class="layui-input locked locked1"
							id="userformoffice_phone"></input>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">V网小号</label>
					<div class="layui-input-block">
						<input type="text" name="v_phone" lay-verify="vphone"
							placeholder="请输入小号" autocomplete="off" class="layui-input locked locked1"
							id="userformv_phone"></input>
					</div>
				</div>

			</div>

			<div class="layui-col-md6">
				<div class="layui-form-item">
					<label class="layui-form-label">部门</label>
					<div class="layui-input-block">
						<input type="text" name="department" lay-verify="string"
							placeholder="请输入部门" autocomplete="off" class="layui-input locked locked1"
							id="userformdepartment"></input>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">职务</label>
					<div class="layui-input-block">
						<input type="text" name="job" lay-verify="string"
							placeholder="请输入职务" autocomplete="off" class="layui-input locked locked1"
							id="userformjob"></input>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">司机状态</label>
					<div class="layui-input-block">
						<input type="radio" name="driver_enable" value=0 title="可用" lay-filter="filter" checked>
						<input type="radio" name="driver_enable" value=10 title="休息" lay-filter="filter">
						<input type="radio" name="driver_enable" value=20 title="停用" lay-filter="filter">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">工作状态</label>
					<div class="layui-input-block">
						<input type="radio" name="driver_state" value=0 title="空闲" checked>
						<input type="radio" name="driver_state" value=10 title="待发">
						<input type="radio" name="driver_state" value=20 title="途中">
					</div>
				</div>

				

			</div>
			<div class="layui-col-md12">
			<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<textarea class="layui-textarea" name="driver_reason" 
							lay-verify="string" placeholder="请输入司机不可用原因"
							autocomplete="off" class="layui-input" id="driver_reason">
						</textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-row">
			<div class="layui-form-item layui-col-md6 layui-col-md-offset3">
				<div class="layui-input-block">
					<button class="layui-btn layui-submit" lay-submit=""
						lay-filter="submit">提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</div>
	</form>

			<%@ include file="tophtml.html"%>
			<%@ include file="lefthtml.html"%>
			<div class="layui-body">
				<div style="padding:20px">
					<h2>司机管理</h2>
					<table id="driverstable" class="layui-table" lay-filter="driverstable">

					</table>
				</div>
			</div>
		</div>
	
</body>


<script src="tableSelect.js"></script>
<script src="../js/driver.js"></script>
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
			新增司机</button>
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
