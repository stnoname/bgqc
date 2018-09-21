/**
 * 所有的leyui，要使用前都得以use开头，把用到的模块都写进去
 */
layui.use([  'jquery', 'layer','form','element' ], function() {
	$=layui.$;
	table=layui.table;
	form=layui.form;
	var element = layui.element;
	var winid;
	$("#changepasswordbutton").click(function(){

		winid=layer.open({									//打开一个弹窗
	    	type:1,											//弹窗类型
	    	title:'修改密码',							
	    	content:$('#changepassword'),							//弹窗的主内容，本处是个表单
//	    	area: ['540px','360px'],						//大小，试出来的
	    	scrollbar:false,								//没有滚动条，貌似没用
	    });
		return false;
	});
	
	
	form.on('submit(changepasswordsubmit)', function(data){				//点击表单的提交按钮
		//console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		//console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		if(data.newpasswordb!=data.newpassworda){
			layer.msg("两次输入密码不同");
			return false;
		}else{
			$.ajax({
				type : "POST",
				url : "changePassword.do",
				data : data.field,
				dataType : "json",
				success : function(msg) {
					
					layer.msg(msg.msg);
					if(msg.title=='成功'){
						layer.close(winid);
					}
				}
			})
		}

		
		return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	
	


});