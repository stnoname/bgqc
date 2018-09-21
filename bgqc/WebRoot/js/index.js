/**
 * 
 */
layui.define(['jquery', 'jquery_cookie'], function (exports) {
    var $ = layui.jquery;  
    // 可能需要导入多个扩展插件
    $ = layui.jquery_cookie($);

    // 作为入口无需注册模块,所以直接null
    exports('index', null);
});




layui.use(['jquery','layer','form','jquery_cookie'], function() {
	$=layui.$;
	form=layui.form;

	
	form.on('submit(login)', function(data){
		//console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		//console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		
		$.ajax({
			type : "POST",
			url : "login.do",
			data : data.field,
			dataType : "json",
		
			success : function(data) {
				
				if(data.name!=null){
					layer.msg("登录成功",{ icon: 0 , closeBtn: 0 });
					user_id=data.user_id;
					name=data.name;
					auth=data.auth;
					saveUserInfo();
					window.location.href='jsp/report.jsp';
					return;
				}else{
					return;
				}
			}
		})

		return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	
	$(function() {
		if(window.location.href.indexOf("index.jsp")==-1){
			//alert($.cookie("autologin"))
			//alert(name);
			auto();	
			//alert("222");
			return;
		}
	
		if($.cookie("remember_user")=='true'){
			$("#remember_user").prop("checked", true);
			$("#mobile_phone").val($.cookie("mobile_phone"));
			$("#password").val($.cookie("password"));
			if($.cookie("autologin")=='true'){
				$("#autologin").prop("checked", true);
			}
		}
		
		form.render();
		//alert($.cookie("remember_user"));
		//alert($.cookie("autologin"));
		if ($.cookie("remember_user")=="true") {
			//alert("ppp"+$.cookie("autologin"));
			if($.cookie("autologin")=='true') {
			
				//alert("pppaaa"+$.cookie("autologin"));
				$.ajax({
					type : "POST",
					url : "login.do",
					data : {
						mobile_phone:$.cookie("mobile_phone"),
						password:$.cookie("password")
					},
					dataType : "json",
				
					success : function(data) {
						if(data.name!=null){
							layer.msg("登录成功",{ icon: 0 , closeBtn: 0 });
							user_id=data.user_id;
							name=data.name;
							auth=data.auth;
							saveUserInfo();
							window.location.href='jsp/report.jsp';
							return;
						}else{
							return;
						}
					}
				})
				return; 
			}
		
			
		}
	});

	function saveUserInfo() {
		if ($("#remember_user").prop("checked") == true) {
			var mobile_phone = $("#mobile_phone").val();
			var password = $("#password").val();
			$.cookie("remember_user", "true", {
				expires: 7,
				
			}); // 存储一个带7天期限的 cookie
			$.cookie("mobile_phone", mobile_phone, {
				expires: 7
			}); // 存储一个带7天期限的 cookie
			$.cookie("password", password, {
				expires: 7
			}); // 存储一个带7天期限的 cookie
			if($("#autologin").prop("checked")){
				$.cookie("autologin", "true", {
					expires: 7,
					path:"/"
					
				}); // 存储一个带7天期限的 cookie
			}else{
				$.cookie("autologin", "false", {
					expires: -1,
					path:'/'
				}); // 存储一个带7天期限的 cookie
			}
			
		} else {
			$.cookie("remember_user", "false", {
				expires: -1
			}); // 删除 cookie
			$.cookie("mobile_phone", '', {
				expires: -1
			});
			$.cookie("password", '', {
				expires: -1
			});
			$.cookie("autologin", "false", {
				expires: -1,
				path:'/'
			}); // 删除 cookie
		}
	}
	form.verify({
		string : function(value,item){
			if(value!=''){
			if(/^.{16,}$/.test(value)){
				return '内容过长';
			}
			}
		},
		mphone : function(value,item){
			if(value!=''){
			if(/^[\d]{11}$/.test(value)==false){
				return '必须为11位纯数字，作为登陆账号使用';
			}
			}
		},
			
	});
	$("#exitbutton").click(function(){

		$.ajax({
			type : "POST",
			url : "../exit.do",
			data : {},
			dataType : "json",
			success : function(msg) {
				if(msg.title=="成功"){
					$.cookie("autologin", "false", {
						expires: 7,
						path:'/'
					}); // 删除 cookie
					
					window.location.href='../index.jsp';
				}
			}
		});
		
		return false;
	});
	function auto(){
		
		//alert("fdsfs54f564");
		if($.cookie("autologin")=="true"&&user_id==''){
			$.ajax({
				type : "POST",
				url : "../login.do",
				data : {
					mobile_phone:$.cookie("mobile_phone"),
					password:$.cookie("password")
				},
				dataType : "json",
			
				success : function(data) {
					
					if(data.name!=null){
						layer.msg("自动登录成功",{ icon: 0 , closeBtn: 0 });
						user_id=data.user_id;
						name=data.name;
						auth=data.auth;
						//elbds();
						//alert("555");
						
						return;
					}else{
						return;
					}
				}
			})
			return; 
		}
	}
});