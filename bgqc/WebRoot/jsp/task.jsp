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
		
		
		
		<title>需求受理</title>
		<%@ include file="head.html" %>
	</head>
	<body class="layui-layout-body">
		
			<%@ include file="tophtml.html"%>
			<%@ include file="lefthtml.html"%>
			<style type="text/css">
				.layui-form-pane .layui-form-label{width:110px}
				.layui-form-pane .layui-input-block{margin-left: 110px;}
				.layui-mybtn {padding:0 5px;}
			</style>
			<div class="layui-body" style="z-index:0">
				<div style="padding:20px">
					<h2>需求受理</h2>
					<form id="taskform" class=" layui-form-pane layui-form " action='' style="padding-top:20px" lay-filter="taskform">
						
						<input type="hidden" id="task_id" name="task_id" value=""></input>
						<input type="hidden" id="task_driver_id" name="task_driver_id" value=""></input>
						<input type="hidden" id="task_car_id" name="task_car_id" value=""></input>
						<input type="hidden" id="seats" name="seats" value=""></input>
						<input type="hidden" id="usedseats" name="usedseats" value=""></input>
						<input type="hidden" id="task_state" name="task_state" value=""></input>
						
						
						<div class=" layui-row layui-col-space10">
							<div class=" layui-row layui-col-md1">
								<div class="layui-form-item">
									<button id="taskadd" class="layui-btn layui-mybtn" type="button">
									<i class="layui-icon">&#xe608;</i>新增任务</button>
								</div>
								<div class="layui-form-item">
									<button class="layui-btn layui-mybtn" id="taskselect" type="button"
									><i class="layui-icon">&#xe615;</i>查看任务</button>
								</div>
							</div>
							<div class=" layui-row layui-col-md1">
								<div class="layui-form-item">
									
									<button class="layui-btn" id="taskforward" style="visibility:hidden" type="submit"
									lay-submit lay-filter="taskforward">确认派单</button>
								</div>
								<div class="layui-form-item">
									<button class="layui-btn" id="taskgoback" style="visibility:hidden" type="submit"
									lay-submit lay-filter="taskgoback">删除任务</button>
								</div>
							</div>
							<div class=" layui-row layui-col-md10 layui-col-space10">
								
								<div class="layui-col-md3">
									<div class="layui-form-item">
										<label class="layui-form-label">司机</label>
										<div class="layui-input-block">
											<input disabled="disabled" class="layui-input" id="name" name="name" placeholder="请选择司机" readonly></input>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">车辆</label>
										<div class="layui-input-block">
											<input disabled="disabled" id="car_number" name="car_number" class="layui-input" placeholder="请选择车辆" readonly></input>
										</div>
									</div>
									
								</div>
								<div class="layui-col-md3">
									<div class="layui-form-item">
										<label class="layui-form-label">状态</label>
										<div class="layui-input-block">
											<input class="layui-input" id="task_stateshow" name="task_stateshow" value="" readonly></input>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">座位</label>
										<div class="layui-input-block">
											<input id="seatsshow" name="seatsshow" class="layui-input" value="" readonly></input>
										</div>
									</div>
									
								</div>
								<div class="layui-col-md3">
									<div class="layui-form-item">
										<label class="layui-form-label">预计发车</label>
										<div class="layui-input-block">
											<input disabled="disabled" class="layui-input" id="maybegintime" name="maybegintime" placeholder="请选择时间" readonly></input>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">派单时间</label>
										<div class="layui-input-block">
											<input id="ordertime" name="ordertime" class="layui-input" value="" readonly></input>
										</div>
									</div>
									
								</div>
								<div class="layui-col-md3">
									<div class="layui-form-item">
										<label class="layui-form-label">出发时间</label>
										<div class="layui-input-block">
											<input class="layui-input" id="begintime" name="begintime" value="" readonly></input>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">结束时间</label>
										<div class="layui-input-block">
											<input id="endtime" class="layui-input" name="endtime" value="" readonly></input>
										</div>
									</div>
									
								</div>
							</div>
							
						</div>
					</form>
					<table id="requestedtable" class="layui-table" lay-filter="requestedtable">
					
					</table>
					<table id="requestingtable" class="layui-table" lay-filter="requestingtable">
					
					</table>
				</div>
			</div>
		</div>
		<form id="requestform" class="layui-form layui-form-pane" action='' style="display:none ;padding-top:20px;width:100%" lay-filter="requestform">
			<div class="layui-row layui-col-space30 layui-container" style="width:100%">
				<div class="layui-col-md3">
					<img src="" class="" id="formimg" style="height:106px;margin:999 999">
			
				</div>
			
				<div class="layui-col-md3">
					<div class="layui-form-item">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-block">
							<input type="text" name="name"
								class="layui-input"></input>
						</div>
					</div>	
					<div class="layui-form-item">
						<label class="layui-form-label">手机</label>
						<div class="layui-input-block">
							<input type="text" name="mobile_phone"
								class="layui-input"></input>
						</div>
					</div>	
				
				</div>
				<div class="layui-col-md3">
					<div class="layui-form-item">
						<label class="layui-form-label">部门</label>
						<div class="layui-input-block">
							<input type="text" name="department" 
								class="layui-input"></input>
						</div>
					</div>	
					<div class="layui-form-item">
						<label class="layui-form-label">办公电话</label>
						<div class="layui-input-block">
							<input type="text" name="office_phone"
								class="layui-input"></input>
						</div>
					</div>	
				
				</div>
				<div class="layui-col-md3">
					<div class="layui-form-item">
						<label class="layui-form-label">职务</label>
						<div class="layui-input-block">
							<input type="text" name="job"
								class="layui-input"></input>
						</div>
					</div>	
					<div class="layui-form-item">
						<label class="layui-form-label">小号</label>
						<div class="layui-input-block">
							<input type="text" name="v_phone"
								class="layui-input"></input>
						</div>
					</div>	
				
				</div>
	  			
			</div>
			<div class="layui-row layui-col-space30 layui-container" style="width:100%">
				<div class="layui-col-md3">
					<div class="layui-form-item">
						<label class="layui-form-label">目的地</label>	
						<div class="layui-input-block">
							<input type="text" name="needplace"
								class="layui-input"></input>
						</div>
					</div>	
					
					<div class="layui-form-item">
						<label class="layui-form-label">请求时间</label>
						<div class="layui-input-block">
							<input type="text" name="requesttime" 
								class="layui-input"></input>
						</div>
					</div>	
				</div>
				<div class="layui-col-md3">
					<div class="layui-form-item">
						<label class="layui-form-label">请求原因</label>	
						<div class="layui-input-block">
							<input type="text" name="needreason"
								class="layui-input"></input>
						</div>
					</div>	
					
					<div class="layui-form-item">
						<label class="layui-form-label">最早出发时间</label>
						<div class="layui-input-block">
							<input type="text" name="needstarttime" 
								class="layui-input"></input>
						</div>
					</div>	
				</div>
				<div class="layui-col-md3">
					<div class="layui-form-item">
						<label class="layui-form-label">需要座位</label>	
						<div class="layui-input-block">
							<input type="text" name="needseats"
								class="layui-input"></input>
						</div>
					</div>	
					
					<div class="layui-form-item">
						<label class="layui-form-label">最晚出发时间</label>
						<div class="layui-input-block">
							<input type="text" name="needendtime" 
								class="layui-input"></input>
						</div>
					</div>	
				</div>
				<div class="layui-col-md3">
					<div class="layui-form-item">
						<label class="layui-form-label">请求状态</label>	
						<div class="layui-input-block">
							<input type="text" name="request_state"
								class="layui-input"></input>
						</div>
					</div>	
					
					<div class="layui-form-item">
						<label class="layui-form-label">用时</label>
						<div class="layui-input-block">
							<input type="text" name="needduringtime" 
								class="layui-input"></input>
						</div>
					</div>	
				</div>
			</div>
		</form>
	</body>
	
	
	<script src="../js/tableSelect.js"></script>
	<script src="../js/task.js"></script>
	<script type="text/html" id="barrequested">
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
		{{#if(document.getElementById("task_state").value==60){}}
			<a class="layui-btn layui-btn-xs" lay-event="remove">移除</a>
		{{#}}}
	</script>
	<script type="text/html" id="barrequesting">
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
		{{#if(document.getElementById("task_state").value==60){}}
			<a class="layui-btn layui-btn-xs" lay-event="add">添加</a>
		{{#}}}
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="refuse">驳回</a>
		
	</script>
	
	
	
</html>
