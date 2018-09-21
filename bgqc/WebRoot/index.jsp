<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
    	<%-- <base href="<%=basePath%>"> --%>
    	<link rel="shortcut icon" href="img/favicon.ico" />
		<link rel="bookmark"href="img/favicon.ico" />
    	<link href="layui/css/layui.css" rel="stylesheet">
    	<title>办公请车系统</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="登录">
		<link href="css/index.css" rel="stylesheet">
		<title>办公请车系统</title>
		
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  	</head>
  
	<body>
		<div style="margin:100px auto 40px auto">
			<h1 style="text-align:center">宝哥请车系统&nbsp;</h1>
		</div>
		<form id="userform" class="layui-form layui-form-pane" action='' style="padding-top:20px;width:800px;margin:0 auto" lay-filter="loginform">
			<div class="layui-row layui-col-space30 layui-container" style="width:100%">
				<div class="layui-col-md6 layui-col-md-offset3 layui-mycol">
					<div class="layui-form-item">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-block">
							<input type="text" name="mobile_phone" required lay-verify="required|mphone" 
								placeholder="请输入手机号" autocomplete="off" class="layui-input" id="mobile_phone"></input>
						</div>
					</div>	
					<div class="layui-form-item">
						<label class="layui-form-label">密码</label>
						<div class="layui-input-block">
							<input type="password" name="password" required lay-verify="required" 
								placeholder="请输入密码" autocomplete="off" class="layui-input" id="password"></input>
						</div>
					</div>	
			
					<div class="layui-form-item">
						<label class="layui-form-label">记住密码</label>
						<div class="layui-input-block">
							<input type="checkbox" name="remember_user" id="remember_user" lay-skin="switch">
							
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">自动登陆</label>
						<div class="layui-input-block">
							<input type="checkbox" name="autologin" id="autologin" lay-skin="switch">
						</div>
					</div>
					<div class="layui-input-block">
     					<button class="layui-btn layui-submit" lay-submit="" lay-filter="login">登录</button>
     					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
   					</div>
				</div>
				
			</div>
		</form>
   
   
  	</body>
  	<script src="layui/layui.js"></script>
  	<script type="text/javascript">
		layui.config({base: 'js/'}).extend({
                jquery_cookie: 'other/jquery.cookie' 
                // 默认寻找base也就是js/index.js模块导入
            }).use('index');
	</script>
<!--   	<script src="js/other/jquery.cookie.js"></script> -->
	<!-- <script src="js/index.js"></script> -->
</html>
