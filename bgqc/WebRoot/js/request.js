Date.prototype.Format = function(fmt) { //author: meizz   
	var o = {
		"M+" : this.getMonth() + 1, //月份   
		"d+" : this.getDate(), //日   
		"h+" : this.getHours(), //小时   
		"m+" : this.getMinutes(), //分   
		"s+" : this.getSeconds(), //秒   
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度   
		"S" : this.getMilliseconds() //毫秒   
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}


/**
 * 
 */
layui.use([ 'laydate', 'jquery', 'table', 'layer', 'form' ,'element' ], function() {
	$ = layui.$;
	table = layui.table;
	form = layui.form;

	var winid;
	var addoredit;



	var laydate = layui.laydate;

	//执行一个laydate实例
	var start = laydate.render({
		elem : '#needstarttime', //指定元素
		type : 'datetime',
		value : getFormatDate(),
			//laydate.now(),
		format:"yyyy-MM-dd HH:mm:ss",
		//min :  getFormatDate(),
		min : getFormatDate(),
		max : '2099-12-31 23:59:59',
		choose : function(datas){
			end.min = datas;
			end.start = datas;
		}
	});

	//执行一个laydate实例
	var end = laydate.render({
		elem : '#needendtime', //指定元素
		type : 'datetime',
		//value : getFormatDate(),
		format:"yyyy-MM-dd HH:mm:ss",
		//min :  $('#needstarttime').val(),
		min : getFormatDate(),
		max : '2099-12-31 23:59:59',
		choose : function(datas){
			start.max=datas;
		}
	});

	if (name == '') {
		layer.alert("您还未登录", {
			icon : 0,
			closeBtn : 0
		}, function() {
			window.location.href = 'index.jsp';
		});
	}
	if (auth != 20 && auth != 0) {
		layer.alert("仅管理员和用户可以访问本页面", {
			icon : 0,
			closeBtn : 0
		}, function() {
			window.location.href = 'index.jsp';
		});
	}

	table.render({
		elem : '#requeststable',
		url : '../getRequests.do', //请求后台的URL（*）
		toolbar : '#barOn',
		method : 'POST',
		where : {},
		even : true, //奇偶行变色
		id : 'requeststable',
		page : {
			limit : 10,
			limits : [ 10, 20, 50, 100 ],
		},
		title : '请车管理',
		cols : [ [
			{
				field : 'request_id',
				title : '序号',
				hide : true
			},
			{
				field : 'request_user_id',
				title : '用户id',
				hide : true
			},
			{
				field : 'name',
				title : '请车人',
				align : 'center',
				minWidth : 80,
				templet : '<div>{{d.userBean.name}}</div>'
			},
			{
				field : 'requesttime',
				title : '请车时间',
				align : 'center',
				minWidth : 160,
				templet : function(d) {
					return new Date(d.requesttime).Format("yyyy-MM-dd hh:mm:ss");
				}
			},
			{
				field : 'needstarttime',
				title : '起始时间',
				align : 'center',
				minWidth : 160,
				templet : function(d) {
					return new Date(d.needstarttime).Format("yyyy-MM-dd hh:mm:ss");
				}
			},
			{
				field : 'needendtime',
				title : '结束时间',
				align : 'center',
				minWidth : 160,
				templet : function(d) {
					return new Date(d.needendtime).Format("yyyy-MM-dd hh:mm:ss");
				}
			},
			{
				field : 'needduringtime',
				title : '时长',
				minWidth : 60,
				align : 'center'
			},
			{
				field : 'needplace',
				title : '目的地',
				minWidth : 80,
				align : 'center'
			},
			{
				field : 'needreason',
				title : '原因',
				minWidth : 80,
				align : 'center'
			},
			{
				field : 'needseats',
				title : '座位数',
				minWidth : 80,
				align : 'center'
			},
			{
				field : 'request_state',
				title : '状态',
				align : 'center',
				minWidth : 80,
				templet : function(d) {
					if (d.request_state == 10) {
						return "草稿";
					} else if (d.request_state == 20) {
						return "已申请";
					} else if (d.request_state == 60){
						return "未派单";
					} else if (d.request_state == 70 ){
						return "已派单";
					} else if (d.request_state == 80 ){
						return "已出发";
					} else if (d.request_state == 90){
						return "已完成";
					}
				}
			},
			{
				field : 'bar',
				toolbar : '#barIn',
				minWidth : 200
			}
		] ],
	});
	table.on('tool(requeststable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		console.log(data)
		if (layEvent === 'detail') { //查看
			addoredit = 'detail';
			form.val("requestsform", data);
			$("#requestsform :input").attr("disabled", "disabled");
			$("#requestsform button").css("display", "none");
			form.render();
			winid = layer.open({
				type : 1,
				title : '查看详细信息',
				content : $('#requestsform'),
				area : [ '450px', '500px' ],
				scrollbar : false,
			});
		}
		if (layEvent === 'edit') { //编辑
			addoredit = 'edit';
			/*data.user_enable=data.user_enable+"";
			data.user_state=data.user_state+"";*/
			form.val("requestsform", data);
			$("#requestsform :input").removeAttr("disabled", "disabled");
			$("#requestsform button").css("display", "");
			form.render();
			winid = layer.open({
				type : 1,
				title : '编辑相关信息',
				content : $('#requestsform'),
				area : [ '450px', '500px' ],
				scrollbar : false
			});
			formre();
		}
		if (layEvent === 'del') { //删除
			layer.confirm('你确定要删除吗？', function(index) {
				$.ajax({
					type : "POST",
					url : "../delRequests.do",
					data : {
						'request_id' : data.request_id
					},
					dataType : "json",
					success : function(msg) {

						//layer.msg(msg);
						if (msg.title == '成功') {
							layer.close(winid);
							formre();
						}
					},
				})
				layer.close(index);
			});
		}
		if (layEvent === 'repass') { //重置内容
			let name = data.name;
			layer.confirm('你确定要重置吗？ ', function(index) {
				$.ajax({
					type : "POST",
					url : "../repassRequests.do",
					data : data,
					dataType : "json",
					success : function(msg) {

						layer.msg(msg.msg);
						if (msg.title == '成功') {
							formre();
						}
					}
				})
				layer.close(index);
			});
		}
	});
	table.on('toolbar(requeststable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if (layEvent === 'add') { //新增
			addoredit = 'add';
			$("#requestsform :input").removeAttr("disabled");
			$("#requestsform button").css("display", "");
			form.render();
			
			winid = layer.open({
				type : 1,
				title : '新增详细信息',
				content : $('#requestsform'),
				area : [ '450px', '500px' ],
				scrollbar : false,
				end : function() {
					formre();
				}
			});
		}
		if (layEvent === 'reload') { //搜索
			formre();
		}

	});
	form.on('submit(save)', function(data) {
		console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		data.field.request_state = 10;
//		data.field.needstarttime = new Date(data.field.needstarttime);
//		data.field.needendtime = new Date(data.field.needendtime);
		if (addoredit == 'add') {

			var obj = {};
			obj.needstarttime = $('#needstarttime').val();
			obj.needendtime = $('#needendtime').val();
			
			var checkedReresult = checktime(obj);
			console.log(data.field) ;
			if (checkedReresult) {
				$.ajax({
					type : "POST",
					url : "../addRequests.do",
					data : data.field,
					dataType : "json",
					success : function(msg) {

						layer.msg(msg.msg);
						if (msg.title == '成功') {
							layer.close(winid);
							formre();
						}
					}
				})
			}
		} else if (addoredit == "edit") {
			$.ajax({
				type : "POST",
				url : "../editRequests.do",
				data : data.field,
				dataType : "json",
				success : function(msg) {

					layer.msg(msg.msg);
					if (msg.title == '成功') {
						layer.close(winid);
						formre();
					}
				}
			})
		}

	});
	form.on('submit(submit)', function(data) {
		console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		data.field.request_state = 20;
//		data.field.needstarttime = new Date(data.field.needstarttime);
//		data.field.needendtime = new Date(data.field.needendtime);
		
		if (addoredit == 'add') {
			var obj = {};
			obj.needstarttime = $('#needstarttime').val();
			obj.needendtime = $('#needendtime').val();
			var checkedReresult = checktime(obj);
			if (checkedReresult) {
				
				$.ajax({
					type : "POST",
					url : "../addRequests.do",
					data : data.field,
					dataType : "json",
					success : function(msg) {
						layer.msg(msg.msg);
						if (msg.title == '成功') {
							layer.close(winid);
							formre();
						}
					}
				})
			}
		} else if (addoredit == "edit") {
			$.ajax({
				type : "POST",
				url : "../editRequests.do",
				data : data.field,
				dataType : "json",
				success : function(msg) {

					layer.msg(msg.msg);
					if (msg.title == '成功') {
						layer.close(winid);
						formre();
					}
				}
			})
		}
		return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

	});

	form.on('checkbox(enablebox)', function(data) {
		formre();
	});

	function formre() {
		let findname = $('#findname').val();
		table.reload('requeststable', {
			where : {
				needplace : findname,
			} //设定异步数据接口的额外参数
		,page: {
			curr: 1 //重新从第 1 页开始
	}
		});
	}
	form.verify({
		string : function(value,item){
			if(value!=''){
			if(/^.{16,}$/.test(value)){
				return '内容过长';
			}
			}
		},
	});
	
	function getFormatDate() {
		var d = new Date(); 
		var year = d.getFullYear(); 
		var month = d.getMonth()+1; 
		var date = d.getDate(); 
		var day = d.getDay(); 
		var hours = d.getHours(); 
		var minutes = d.getMinutes(); 
		var seconds = d.getSeconds(); 
		var ms = d.getMilliseconds(); 
		var curDateTime= year;
		if(month>9)
		curDateTime = curDateTime +"-"+month;
		else
		curDateTime = curDateTime +"-0"+month;
		if(date>9)
		curDateTime = curDateTime +"-"+date;
		else
		curDateTime = curDateTime +"-0"+date;
		if(hours>9)
		curDateTime = curDateTime +" "+hours;
		else
		curDateTime = curDateTime +" 0"+hours;
		if(minutes>9)
		curDateTime = curDateTime +":"+minutes;
		else
		curDateTime = curDateTime +":0"+minutes;
		if(seconds>9)
		curDateTime = curDateTime +":"+seconds;
		else
		curDateTime = curDateTime +":0"+seconds;
		return curDateTime;
	}
	
});


$(document).ready(function() {
	$("#needendtime").blur(function() { // 失去焦点
		var obj = {};
		obj.needstarttime = $('#needstarttime').val();
		obj.needendtime = $('#needendtime').val();
		var checkedReresult = checktime(obj);

	});
	
	
	
});
