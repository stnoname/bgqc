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
		<title>请车需求</title>
		<%@ include file="head.html" %>
	</head>

	<body class="layui-layout-body">

			<%@ include file="tophtml.html"%>
			<%@ include file="lefthtml.html"%>
			<div class="layui-body">
				<div style="padding:20px">
					<h2>需求申请</h2>
					<table id="requeststable" class="layui-table" lay-filter="requeststable">
					</table>
				</div>
			</div>
		</div>
		<form id="requestsform" class="layui-form layui-form-pane" action='' style="display:none;padding-top:20px;width:450px" lay-filter="requestsform">
			<div class="layui-row layui-col-space30 layui-container" style="width:100%">
			<input type="hidden" name="request_id"/>
			<div>
			 <div class="layui-form-item">
				<label style="width: 155px" class="layui-form-label">*起始时间</label>
      			<div class="layui-input-block">
        			<input style="width: 200px" type="text" required lay-verify="required|needstarttime" name="needstarttime" class="layui-input" id="needstarttime" placeholder="请输入需求发车起始时间" autocomplete="off" readonly>
      			</div>
    		</div>
				
			<div class="layui-form-item">
				<label style="width: 155px" class="layui-form-label">结束时间</label>
      			<div class="layui-input-block">
        			<input type="text" style="width: 200px" name="needendtime" class="layui-input" id="needendtime" placeholder="请输入需求发车结束时间" autocomplete="off" readonly>
    			</div>
			</div> 
		
			<div class="layui-form-item">
				<label style="width: 155px" class="layui-form-label">时长</label>
				<div class="layui-input-block">
					<input type="number" name="needduringtime" style="width: 200px"  
						value="0" autocomplete="off" class="layui-input" id="requestsformneedduringtime"></input>
				</div>
			</div>	
			<div class="layui-form-item">
				<label style="width: 155px" class="layui-form-label">*目的地</label>
				<div class="layui-input-block">
					<input type="text" name="needplace" style="width: 200px" required lay-verify="required|string"
						placeholder="请输入目的地" autocomplete="off" class="layui-input" id="requestsformneedplace"></input>
				</div>
			</div>	
			<div class="layui-form-item">
				<label style="width: 155px" class="layui-form-label">原因</label>
				<div class="layui-input-block">
					<input type="text" name="needreason" style="width: 200px" lay-verify="string" 
						placeholder="请输入需求原因" autocomplete="off" class="layui-input" id="requestsformneedreason"></input>
				</div>
			</div>	
			
			<div class="layui-form-item">
				<label style="width: 155px" class="layui-form-label">*座位数</label>
				<div class="layui-input-block">
					<input type="text" name="needseats" style="width: 200px" required lay-verify="required|number" 
						value="1" autocomplete="off" class="layui-input" id="requestsformneedseats"></input>
				</div>
			</div>
			
			</div>
			<div class="layui-row">
			<div class="layui-form-item  layui-col-md-offset3">
    			<!-- <div class="layui-input-block"> -->
    			<div class="layui-btn-container">
    				<button class="layui-btn layui-submit" lay-submit="" lay-filter="save">草稿</button>
      				<button class="layui-btn layui-submit" lay-submit="" lay-filter="submit">提交</button>
      				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
    			</div>
  			</div> 
  			</div>
		</form>

	</body>

	<script src="../js/request.js"></script>
	<script src="../js/checktime.js"></script>
	<script type="text/html" id="barIn">
		{{# var request_user_id=d.userBean.user_id}}
		{{# if(user_id==request_user_id){ }}
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
			<a class="layui-btn layui-btn-xs" lay-event="edit">
			<i class="layui-icon">&#xe642;</i>
			编辑</a>
			
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon">&#xe640</i>删除</a>
		{{#}else{ }}
			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
		{{# } }}
	</script>
	
	<script type="text/html" id="barOn">
		<div>
			<button class="layui-btn layui-btn-sm tianjia" lay-event="add">
			<i class="layui-icon">&#xe608;</i>
			新增需求</button>

    			<div class="layui-inline">
    				<label style="width:130px" class="layui-form-label">需求目的地：</label>
				</div>
				<div class="layui-inline">
					<input class="layui-input" name="id" id="findname" placeholder="请输入目的地" autocomplete="off">
				</div>
			<button class="layui-btn layui-btn-sm" lay-event="reload">
			<i class="layui-icon">&#xe615;</i>
			搜索</button>
		</div>
	</script>
	
</html>
