/**
 * 所有的leyui，要使用前都得以use开头，把用到的模块都写进去
 */
layui.use([ 'laydate', 'jquery', 'table','layer','form','element','tableSelect' ], function() {
	var $=layui.$;
	var table=layui.table;
	var form=layui.form;
	var element = layui.element;
	var tableSelect = layui.tableSelect;
	var laydate=layui.laydate;
	var addoredit="";
	var task_stateArray=[];
	var task_forwardArray=[];
	var task_gobackArray=[];
	var driver_stateArray=[];
	var car_stateArray=[];
	var requestedtablecount=0;
	$("#taskform input").attr("disabled","disabled");
	task_stateArray[0]="";
	task_forwardArray[0]="";
	task_gobackArray[0]="";
	task_stateArray[50]="新建";
	task_forwardArray[50]="保存信息";
	task_gobackArray[50]="清空信息";
	task_stateArray[60]="未派单";
	task_forwardArray[60]="确认派单";
	task_gobackArray[60]="删除任务";
	task_stateArray[70]="已派单";
	task_forwardArray[70]="确认出发";
	task_gobackArray[70]="重新派单";
	task_stateArray[80]="已出发";
	task_forwardArray[80]="任务完成";
	task_gobackArray[80]="";
	task_stateArray[90]="已完成";
	task_forwardArray[90]="";
	task_gobackArray[90]="";
	driver_stateArray[0]="空闲";
	driver_stateArray[10]="待发车";
	driver_stateArray[20]="繁忙";
	
	car_stateArray[0]="空闲";
	car_stateArray[10]="待发车";
	car_stateArray[20]="繁忙";
	
	//$("input:hidden").attr("type","input");
	
	//权限判断
	
	if(name==''){
		layer.alert("您还未登录", { icon: 0 , closeBtn: 0 } ,function() {
		window.location.href='../index.jsp';
		});
	}else if(auth!=20){
		layer.alert("仅管理员可以访问本页面",{ icon: 0 , closeBtn: 0 } , function() {
		window.location.href='../index.jsp';
		});
	}
	tableSelect.render({			//任务的
		elem: '#taskselect',
		searchKey: 'name',
		table: {
			url : '../getTask.do', 
			even:true,			//奇偶行变色
			method:'POST',
//			id:'tasktable',		//表格的名称，貌似没啥用
			page : {			//分页情况
				limit:10,		//每页默认10行
				limits:[10, 20,50,100],	//可以改成每页10，20，50，100
			},
			cols: [[
				{ type: 'radio' },
				{ field: 'name', title: '司机',minWidth:100, align :'center',},
				{ field: 'car_number', title: '车辆',minWidth:100,align :'center', },
				
				{ field: 'seatsshow', title: '座位数',minWidth:100,align :'center',templet:function(d){
					var usedseats=d.usedseats;
					var seats=d.seats;
					if(usedseats==null){
						usedseats=0;
					}
					if(seats==null){
						seats=0;
					}
					return usedseats+"/"+seats;
				}},
				
				{ field: 'task_stateshow', title: '状态',minWidth:100,align :'center',templet:function(d){
					return task_stateArray[d.task_state];
				}},
				
				{ field: 'maybegintime', title: '预计开始时间',minWidth:200,align :'center', },
				{ field: 'ordertime', title: '派单时间',minWidth:200,align :'center', },
				{ field: 'begintime', title: '出发时间',minWidth:200,align :'center', },
				{ field: 'endtime', title: '结束时间',minWidth:200,align :'center', },
				{ field:"seats",hide:true},
				{ field:"usedseats",hide:true},
				{ field:"task_state",hide:true},
			]]
		},
		done: function (elem, data) {
			//alert(data.data[0].name);
//			console.log("selecttable date");
//			console.log(data);
			if(data.data.length==0){
				return;
			}
			document.getElementById("taskform").reset();		//清空数据
			form.val("taskform", data.data[0]);
			task_form_button();
			retablerequested();
			retablerequesting();
			

		}
	})
	
	tableSelect.render({			//司机的
		elem: '#name',
		searchKey: 'name',
		table: {
			url : '../getTaskDriver.do', 
			even:true,			//奇偶行变色
			method:'POST',
			id:'taskdrivertable',		//表格的名称，貌似没啥用
			page : {			//分页情况
				limit:10,		//每页默认10行
				limits:[10, 20,50,100],	//可以改成每页10，20，50，100
			},
			cols: [[
				{ type: 'radio' },
				{ field: 'name', title: '司机',minWidth:100, align :'center',},
				{ field: 'driver_stateshow', title: '状态',minWidth:100,align :'center',templet:function(d){
					return driver_stateArray[d.driver_state];
				}},
				{ field: 'driver_missions', title: '相关任务',minWidth:100, align :'center',},
				{ field: 'mobile_phone', title: '手机',minWidth:150, align :'center',},
				{ field:"driver_id",hide:true},
				{ field:"user_id",hide:true},
				{ field:"driver_state",hide:true},
			]]
		},
		done: function (elem, data) {
			console.log("driver date");
			console.log(data);
			if(data.data.length==0){
				return;
			}
			form.val("taskform", data.data[0]);
			$("#task_driver_id").val(data.data[0].driver_id);
			if($("#task_id").val()!=""
				&&$("#task_state").val()==60
				&&data.data[0].driver_id!=null
				&&data.data[0].driver_id!=''){
				$.ajax({
					type:"POST",
					url:'../editTaskDriver.do',
					data:{
						task_id:$("#task_id").val(),
						task_driver_id:data.data[0].driver_id
					},
					dataType : "json",
					success : function(data) {
						layer.msg(data.msg);
					}
				});
			}
			
			
		}
	})
	
	tableSelect.render({			//车辆的
		elem: '#car_number',
		searchKey: 'car_number',
		table: {
			url : '../getTaskCar.do', 
			even:true,			//奇偶行变色
			method:'POST',
			id:'taskdrivertable',		//表格的名称，貌似没啥用
			page : {			//分页情况
				limit:10,		//每页默认10行
				limits:[10, 20,50,100],	//可以改成每页10，20，50，100
			},
			cols: [[
				{ type: 'radio' },
				{ field: 'car_number', title: '车牌号',minWidth:100, align :'center',},
				{ field: 'car_stateshow', title: '状态',minWidth:100,align :'center',templet:function(d){
					return car_stateArray[d.car_state];
				}},
				{ field: 'car_missions', title: '相关任务',minWidth:100, align :'center',},
				{ field:"car_id",hide:true},
				{ field:"car_state",hide:true},
			]]
		},
		done: function (elem, data) {
			console.log("car date");
			console.log(data);
			if(data.data.length==0){
				return;
			}
			form.val("taskform", data.data[0]);
			$("#task_car_id").val(data.data[0].car_id);
			task_form_button();
			if($("#task_id").val()!=""
				&&$("#task_state").val()==60
				&&data.data[0].car_id!=null
				&&data.data[0].car_id!=''){
				$.ajax({
					type:"POST",
					url:'../editTaskCar.do',
					data:{
						task_id:$("#task_id").val(),
						task_car_id:data.data[0].car_id
					},
					dataType : "json",
					success : function(data) {
						layer.msg(data.msg);
					}
				});
			}
			
		}
	})
	
	laydate.render({
		elem: '#maybegintime' //指定元素
    	,type: 'datetime'
    	,format:"yyyy-MM-dd HH:mm:ss"
    	,btns: ['now', 'confirm']
		,calendar: true
		,min: getFormatDate()

		,done: function(value, date, endDate){
//		    console.log(value); //得到日期生成的值，如：2017-08-18
//		    console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
//		    console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
			if($("#task_id").val()!=""
				&&$("#task_state").val()==60
				&&value!=''){
				$.ajax({
					type:"POST",
					url:'../editTaskmaybegintime.do',
					data:{
						task_id:$("#task_id").val(),
						maybegintime:value
					},
					dataType : "json",
					success : function(data) {
						layer.msg(data.msg);
					}
				});
			}
		  }
	});	
	
	$("#taskadd").click(function(){							//新增任务
		document.getElementById("taskform").reset();		//清空数据
		$("#task_state").val(50);
		$("#seats").val(0);
		$("#usedseats").val(0);
		task_form_button();
		retablerequested();
		retablerequesting();
		
	});
	form.on('submit(taskforward)', function(data){				//前进按钮
		//console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		//console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		//console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		var task_state=eval($("#task_state").val());		
		
		if(task_state==50){										//如果是新建的话
			if($("#task_driver_id").val()==""){
				layer.alert("请选择司机。");
				return false;
			}
			if($("#task_car_id").val()==""){
				layer.alert("请选择车辆。");
				return false;
			}
			if($("#maybegintime").val()==""){
				layer.alert("请选择预计发车时间。");
				return false;
			}
			$.ajax({
				type:"POST",
				url:'../addTask.do',
				data:data.field,
				dataType : "json",
				success : function(data) {
					if(data.task_id!=null){
						layer.msg("保存成功，任务已经生成，可以派单了");
						document.getElementById("taskform").reset();		//清空数据
						form.val("taskform", data);
						task_form_button();
						retablerequested();
						retablerequesting();
					}else{
						layer.msg("未知错误，请刷新后重试");
					}
				}
			});
		}else if(task_state==60){
			if(requestedtablecount==0){
				layer.msg("确认派单前请确保车上有人");
				return false;
			}
			$.ajax({
				type:"POST",
				url:'../decideTask.do',
				data:data.field,
				dataType : "json",
				success : function(data) {
					layer.msg(data.msg);
					if(data.title=="成功"){
						$("#task_state").val(70);
						task_form_button();
						retablerequested();
						retablerequesting();
					}
				}
			});
		}else if(task_state==70){
			var tt=getFormatDate();
			data.field.begintime=tt;
			$.ajax({
				type:"POST",
				url:'../goTask.do',
				data:data.field,
				dataType : "json",
				success : function(data) {
					layer.msg(data.msg);
					if(data.title=="成功"){
						$("#task_state").val(80);
						task_form_button();
						$("#begintime").val(tt);
					}
				}
			});
		}else if(task_state==80){
			
			var tt=getFormatDate();
			data.field.endtime=tt;
			
			$.ajax({
				type:"POST",
				url:'../backTask.do',
				data:data.field,
				dataType : "json",
				success : function(data) {
					layer.msg(data.msg);
					if(data.title=="成功"){
						$("#task_state").val(90);
						task_form_button();
						$("#endtime").val(tt);
					}
				}
			});
		}
		
		return false;
	});
	
	form.on('submit(taskgoback)', function(data){				//回退按钮
		//console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		//console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		//console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		var task_state=eval($("#task_state").val());		
		
		if(task_state==50){										//如果是新建的话
			document.getElementById("taskform").reset();
			$("#task_state").val(50);
			task_form_button();
		}else if(task_state==60){
			if(requestedtablecount>0){
				layer.msg("删除任务前请确保车上没有人");
				return false;
			}
			$.ajax({
				type:"POST",
				url:'../delTask.do',
				data:data.field,
				dataType : "json",
				success : function(data) {
					layer.msg(data.msg);
					if(data.title=="成功"){
						document.getElementById("taskform").reset();
						$("#task_state").val(0);
						task_form_button();
						$("#task_state").val('');
					}
				}
			});
		}else if(task_state==70){
			$.ajax({
				type:"POST",
				url:'../redecideTask.do',
				data:data.field,
				dataType : "json",
				success : function(data) {
					layer.msg(data.msg);
					if(data.title=="成功"){
						$("#task_state").val(60);
						task_form_button();
						retablerequested();
						retablerequesting();
					}
				}
			});
		}
		
		return false;
	});

	function task_form_button(){
		$("#taskform input").removeAttr("disabled");
		var task_state=$("#task_state").val();
		var seats=$("#seats").val();
		
		if(seats==null||seats==""){seats=0}
		var usedseats=$("#usedseats").val();
		if(usedseats==null||usedseats==""){usedseats=0}
		$("#seatsshow").val(usedseats+"/"+seats);
		
		
		$("#task_stateshow").val(task_stateArray[task_state]);
		
		
		
		
		if(task_forwardArray[task_state]==""){
			$("#taskforward").css("visibility","hidden");
		}else{
			$("#taskforward").text(task_forwardArray[task_state]);
			$("#taskforward").css("visibility","");
			
		}
		
		if(task_gobackArray[task_state]==""){
			$("#taskgoback").css("visibility","hidden");
		}else{
			$("#taskgoback").text(task_gobackArray[task_state]);
			$("#taskgoback").css("visibility","");
			
		}
		
		
		
		
		form.render();	
		if(task_state==50||task_state==60){
			$("#name").removeAttr("disabled");
			$("#car_number").removeAttr("disabled");
			$("#maybegintime").removeAttr("disabled");
		}else{
			$("#name").attr("disabled","disabled");
			$("#car_number").attr("disabled","disabled");
			$("#maybegintime").attr("disabled","disabled");
		}
	}
	
	
	table.render({						//有任务的任务表
		elem : '#requestedtable',	//表的选择
		url : '../getTaskRequested.do', //请求后台的URL（*）
		method:'POST',
		where:{task_id:$("#task_id").val()},			//默认无任何参数
		even:true,			//奇偶行变色
		id:'requestedtable',		//表格的名称，貌似没啥用
		height:"170",
		done: function(res, curr, count){
			requestedtablecount=count;
		 },
		title:'单车',		//表格的标题，没啥用
		cols : [[				//每个列的字段
			{
				field : 'request_id',	//字段唯一标识，要求和后端传来的json字段一致，基本就是javabean的字段
				hide:true			//隐藏，因为序号不可见比较好
			},
			{
				field : 'request_user_id',	
				hide:true			
			},
			{
				field : 'name',
				title : '姓名',
				align :'center',	//居中显示
				templet:function(d){
					return d.userBean.name;
				},
				width:100,
			}, 
			{
				field : 'needplace',
				title : '目的地',
				align :'center',	//居中显示
				//width:150,
			},
			{
				field : 'needstarttime',
				title : '最早出发时间',
				width:160,		
				align :'center',	//居中显示
			},
			{
				field : 'needendtime',
				title : '最晚出发时间',
				width:160,		
				align :'center',	//居中显示
				//hide:true
			},
			{
				field : 'needduringtime',
				title : '用时',
				width:100,
				align :'center',	//居中显示
				//hide:true
			},
			{
				field : 'requesttime',
				title : '申请时间',
				width:160,		
				align :'center',	//居中显示
			},
			{
				field : 'mobile_phone',
				align :'center',
				title : '手机',
				width:120,
				templet:function(d){
					return d.userBean.mobile_phone;
				}
			},
			{
				field : 'needseats',
				align :'center',
				title : '座位',
				width:80,
			},
			{
				field : 'request_task_id',	
				hide:true			
			},
			{
				field : 'request_state',	
				hide:true			
			},
			{
				field : 'user_id',	
				hide:true,
				templet:function(d){
					return d.userBean.user_id;
				}
			},
			{
				field:'bar',		//行内工具栏
				toolbar: '#barrequested',	//工具栏的选择
				width:180		//不然容易显示不下……
			}
			]],
	});
	
	table.render({						//没任务的任务表
		elem : '#requestingtable',	//表的选择
		url : '../getTaskRequesting.do', //请求后台的URL（*）
		method:'POST',
		where:{task_id:$("#task_id").val()},			//默认无任何参数
		even:true,			//奇偶行变色
		id:'requestingtable',		//表格的名称，貌似没啥用
		height:"full-520",
		page : {			//分页情况
			limit:10,		//每页默认10行
			limits:[10, 20,50,100],	//可以改成每页10，20，50，100
		},
		title:'单车',		//表格的标题，没啥用
		cols : [[				//每个列的字段
			{
				field : 'request_id',	//字段唯一标识，要求和后端传来的json字段一致，基本就是javabean的字段
				hide:true			//隐藏，因为序号不可见比较好
			},
			{
				field : 'request_user_id',	
				hide:true			
			},
			{
				field : 'name',
				title : '姓名',
				align :'center',	//居中显示
				templet:function(d){
					return d.userBean.name;
				},
				width:100,
			}, 
			{
				field : 'needplace',
				title : '目的地',
				align :'center',	//居中显示
				//width:150,
			},
			{
				field : 'needstarttime',
				title : '最早出发时间',
				width:160,		
				align :'center',	//居中显示
			},
			{
				field : 'needendtime',
				title : '最晚出发时间',
				width:160,		
				align :'center',	//居中显示
				//hide:true
			},
			{
				field : 'needduringtime',
				title : '用时',
				width:100,
				align :'center',	//居中显示
				//hide:true
			},
			{
				field : 'requesttime',
				title : '申请时间',
				width:160,		
				align :'center',	//居中显示
			},
			{
				field : 'mobile_phone',
				align :'center',
				title : '手机',
				width:120,
				templet:function(d){
					return d.userBean.mobile_phone;
				}
			},
			{
				field : 'needseats',
				align :'center',
				title : '座位',
				width:80,
			},
			{
				field : 'request_task_id',	
				hide:true			
			},
			{
				field : 'request_state',	
				hide:true			
			},
			{
				field : 'user_id',	
				hide:true,
				templet:function(d){
					return d.userBean.user_id;
				}
			},
			{
				field:'bar',		//行内工具栏
				toolbar: '#barrequesting',	//工具栏的选择
				width:180		//不然容易显示不下……
			}
			]],
	});
	function retablerequested(){												//刷新表单
	
		table.reload('requestedtable',{									//刷新表
			  where: {												//两个参数，分别是是否停用，
				  task_id:$("#task_id").val()
			  } //设定异步数据接口的额外参数
	
		});
		
	}
	function retablerequesting(){												//刷新表单
		
		table.reload('requestingtable',{									//刷新表
			  where: {												//两个参数，分别是是否停用，
				  
			  } //设定异步数据接口的额外参数
		
		});
		
	}
	
	
	
	
	
	
	
	//行内按钮的代码
	table.on('tool(requestingtable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		//console.log(data)
		if(layEvent === 'add'){ //添加
			var task_state=$("#task_state").val();
			var nowseats=$("#usedseats").val();
			var allseats=$("#seats").val();
			if(nowseats==''){
				nowseats=0;
				$("#usedseats").val(0);
			}
			if(allseats==''){
				allseats=0;
				$("#seats").val(0);
			}
			
			if($("#task_id").val()==''){
				layer.msg('请选择任务');
				return;
			}
			if(task_state!=60){
				layer.msg('请选择未派单任务');
				return;
			}
			if(allseats-nowseats<obj.data.needseats){
				layer.confirm("该车辆位置不足，真的要添加吗？",function(){
					lineadd(obj);
				});
			}else{
				lineadd(obj);
			}
		}
		if(layEvent === 'refuse'){ //驳回
			if(obj.data.request_state!=20){
				layer.msg('你只能驳回提交的请求');
				return;
			}
			layer.confirm("你确定要驳回吗？",function(){
				delete obj.data.userBean;
				$.ajax({
					type:"POST",
					url:'../refuseTaskRequest.do',
					data:obj.data,
					dataType : "json",
					success : function(data) {
						layer.msg(data.msg);
						retablerequesting();
					}
				});
			});
		}
		if(layEvent === 'detail'){ //查看
			showdetail(data);
			
		}
	});
	
	function showdetail(data){
		console.log(data);
		data.name=data.userBean.name;
		data.mobile_phone=data.userBean.mobile_phone;
		data.department=data.userBean.department;
		data.office_phone=data.userBean.office_phone;
		data.job=data.userBean.job;
		data.v_phone=data.userBean.v_phone;
		form.val("requestform",data);				//给表单赋初始值
		$("#formimg").attr('src',"../img/"+data.userBean.picture);
		$("#requestform :input").attr("disabled","disabled");	//查看，把所有表单都锁死
		form.render();										//重新渲染表单
		winid=layer.open({									//打开一个弹窗
	    	type:1,											//弹窗类型
	    	title:'查看用户信息',							
	    	content:$('#requestform'),							//弹窗的主内容，本处是个表单
	    	area: ['1200px','400px'],						//大小，试出来的
	    	scrollbar:false,								//没有滚动条，貌似没用
	    });
	}
	
	table.on('tool(requestedtable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		//console.log(data)
		if(layEvent === 'remove'){ //移除
			var task_state=$("#task_state").val();
			var nowseats=$("#usedseats").val();
			var allseats=$("#seats").val();
			if(nowseats==''){
				nowseats=0;
				$("#usedseats").val(0);
			}
			if(allseats==''){
				allseats=0;
				$("#seats").val(0);
			}
			if(task_state!=60){
				layer.msg('移除错误');
				return;
			}
			//obj.data.request_task_id=$("#task_id").val();
			delete obj.data.userBean;
			console.log(obj.data);
			$.ajax({
				type:"POST",
				url:'../removeTaskRequest.do',
				data:obj.data,
				dataType : "json",
				success : function(data) {
					layer.msg(data.msg);
					
					$("#usedseats").val(eval($("#usedseats").val())-eval(obj.data.needseats));
					$("#seatsshow").val($("#usedseats").val()+"/"+$("#seats").val());
					retablerequested();
					retablerequesting();
				}
			});
		}
		if(layEvent === 'detail'){ //查看
			showdetail(data);
			
		}
	});
	function lineadd(obj){
		obj.data.request_task_id=$("#task_id").val();
		delete obj.data.userBean;
		console.log(obj.data);
		$.ajax({
			type:"POST",
			url:'../addTaskRequest.do',
			data:obj.data,
			dataType : "json",
			success : function(data) {
				layer.msg(data.msg);
				if(data.title=="成功"){
					$("#usedseats").val(eval($("#usedseats").val())+eval(obj.data.needseats));
					$("#seatsshow").val($("#usedseats").val()+"/"+$("#seats").val());
					retablerequested();
					retablerequesting();
				}
				
			}
		});
	}
	
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