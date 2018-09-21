/**
 * 所有的leyui，要使用前都得以use开头，把用到的模块都写进去
 */
layui.use([ 'laydate', 'jquery', 'table','layer','form','element' ], function() {
	$=layui.$;
	table=layui.table;
	form=layui.form;
	var element = layui.element;
	var winid;
	var qxlist=[];
	var enablelist=[];
	var statelist=[];
	var addoredit;
	//一堆字符串数组，为了输出
	qxlist[0]='用户'
	qxlist[10]='司机';
	qxlist[20]='管理员';
	statelist[0]='空闲';
	statelist[10]='在途';
	enablelist[0]='启用';
	enablelist[10]='停用';
	
	//注意，这个是每个页面都要的权限验证，先验证是否登录，再验证是否有权限，没有权限的不给看
	if(name==''){
		layer.alert("您还未登录", { icon: 0 , closeBtn: 0 } ,function() {
		window.location.href='../index.jsp';
		});
	} else if(auth!=20){
		layer.alert("仅管理员可以访问本页面",{ icon: 0 , closeBtn: 0 } , function() {
		window.location.href='../index.jsp';
		});
	}
	$("#erjicaidan").addClass("layui-nav-itemed");
	//主表的显示情况
	table.render({
		elem : '#usertable',	//表的选择
		url : '../getUser.do', //请求后台的URL（*）
		toolbar:'#barOn',	//表头工具栏的选择
		method:'POST',
		where:{},			//默认无任何参数
		even:true,			//奇偶行变色
		id:'usertable',		//表格的名称，貌似没啥用
		height:"full-220",
		page : {			//分页情况
			limit:10,		//每页默认10行
			limits:[10, 20,50,100],	//可以改成每页10，20，50，100
		},
		title:'用户管理',		//表格的标题，没啥用
		cols : [[				//每个列的字段
			{
				field : 'user_id',	//字段唯一标识，要求和后端传来的json字段一致，基本就是javabean的字段
				title : '序号',		//显示出来的标题
				hide:true			//隐藏，因为序号不可见比较好
			},
			{
				field : 'name',
				title : '姓名',
				align :'center',	//居中显示
			}, 
			{
				field : 'auth',
				title : '权限',
				align :'center',
				templet:function(d){		//由于传进来的是0，10，20这样的字段，要返回“用户”、“管理员”这样的汉字
					return qxlist[d.auth];
					}
				//hide:true
			}, 
			{
				field : 'office_phone',
				align :'center',
				title : '办公电话',
				minWidth:140
			},
			{
				field : 'mobile_phone',
				align :'center',
				title : '手机',
				minWidth:140
			},
			{
				field : 'v_phone',
				align :'center',
				title : '小号',
			},
			{
				field : 'department',
				align :'center',
				title : '部门',
			},
			{
				field : 'job',
				align :'center',
				title : '职务',
			},
			{
				field : 'user_enable',
				title : '是否停用',
				align :'center',
				templet:function(d){
					return enablelist[d.user_enable];
				}
			},
			{
				field : 'user_state',
				title : '状态',
				align :'center',
				templet:function(d){
					return statelist[d.user_state];
				}
			},
			{
				field:'bar',		//行内工具栏
				toolbar: '#barIn',	//工具栏的选择
				minWidth:250		//不然容易显示不下……
			}
			]],
	});

	//这部分是行内工具栏的点击事件处理
	table.on('tool(usertable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		//console.log(data)
		if(layEvent === 'detail'){ //查看
			addoredit='detail';				//由于公用一个弹出框，所以设置个全局变量记录一下
			data.user_enable=data.user_enable+"";	//由于单选框的数据默认是int格式，而表单赋初始值要string，所以转一下
			data.user_state=data.user_state+"";
			form.val("userform", data);				//给表单赋初始值
			$("#userform :input").attr("disabled","disabled");	//查看，把所有表单都锁死
			$("#userform button").css("display","none");		//查看，把所有按钮（就俩）都隐藏
			form.render();										//重新渲染表单
			winid=layer.open({									//打开一个弹窗
		    	type:1,											//弹窗类型
		    	title:'查看用户信息',							
		    	content:$('#userform'),							//弹窗的主内容，本处是个表单
		    	area: ['800px','400px'],						//大小，试出来的
		    	scrollbar:false,								//没有滚动条，貌似没用
		    });
		}
		if(layEvent === 'edit'){ 								//编辑							
			addoredit='edit';
			data.user_enable=data.user_enable+"";
			data.user_state=data.user_state+"";
			form.val("userform", data);
			$("#userform :input").removeAttr("disabled","disabled");		
			$("#userform button").css("display","");
			form.render();
			winid=layer.open({
		    	type:1,
		    	title:'编辑用户信息',
		    	content:$('#userform'),
		    	area: ['800px','400px'],
		    	scrollbar:false,
		    });
		}
		if(layEvent === 'del'){ //删除										
			let name=data.name;											//获得用户的名字
			layer.confirm('你确定要删除 '+name+" 的信息吗", function(index){	//自带的confirm弹窗，第一个是信息，第二个是回调函数
				$.ajax({													//阿贾克斯
					type : "POST",
					url : "../delUser.do",										//访问删除的action
					data : data,											//数据直接就是引用的行数据，上面有
					dataType : "json",
					success : function(msg) {
						
						layer.msg(msg.msg);									//弹出后台的返回值
						if(msg.title=='成功'){								//如果成功的话，刷新
							formre();										//本刷新函数是自定义的，在下面
						}
					}
				})
				layer.close(index);											//无论如何关闭本fonfirm											
			});   
		}
		if(layEvent === 'repass'){ 											//重置密码
			let name=data.name;
			layer.confirm('你确定要重置 '+name+" 的密码为123456吗", function(index){
				$.ajax({
					type : "POST",
					url : "../repassUser.do",
					data : data,
					dataType : "json",
					success : function(msg) {
						
						layer.msg(msg.msg);
						if(msg.title=='成功'){
							formre();
						}
					}
				})
				layer.close(index);
			});   
		}
	});
	/*
	 * 注意，一下方法是顶端工具条
	 */
	table.on('toolbar(usertable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		
		if(layEvent === 'add'){ //新增
			addoredit='add';
			$("#userform :input").removeAttr("disabled");
			$("#userform button").css("display","");
			form.render();
			document.getElementById("userform").reset();		//清空数据
		    winid=layer.open({
		    	type:1,
		    	title:'新增用户信息',
		    	content:$('#userform'),
		    	area: ['800px','400px'],
		    	scrollbar:false,
		    });
		}
		if(layEvent === 'reload'){ //搜索
		    formre();
		}
		
	});
	form.on('submit(submit)', function(data){				//点击表单的提交按钮
		//console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		//console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		if(addoredit=='add'){								//如果是新增，就新增
			$.ajax({
				type : "POST",
				url : "../addUser.do",
				data : data.field,
				dataType : "json",
				success : function(msg) {
					
					layer.msg(msg.msg);
					if(msg.title=='成功'){
						layer.close(winid);
						formre();
					}
				}
			})
		}else if(addoredit=="edit"){
			$.ajax({
				type : "POST",
				url : "../editUser.do",
				data : data.field,
				dataType : "json",
				success : function(msg) {
					
					layer.msg(msg.msg);
					if(msg.title=='成功'){
						layer.close(winid);
						formre();
					}
				}
			})
		}
		
		return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	
	form.on('checkbox(enablebox)', function(data){				//点击停用复选框，就刷新

		formre();
	
		//console.log(data.elem); //得到checkbox原始DOM对象
		//console.log(data.elem.checked); //是否被选中，true或者false
		//console.log(data.value); //复选框value值，也可以通过data.elem.value得到
		//console.log(data.othis); //得到美化后的DOM对象
	});      
	
	function formre(){												//刷新表单
		let enable=$('#enablebox').is(':checked');					//获取表头的“是否停用”状态
		let findname=$('#findname').val();							//获取搜索框内容
		let user_enable=enable?0:10;								//把“是否停用”表示改成代码
		
		table.reload('usertable',{									//刷新表

			  where: {												//两个参数，分别是是否停用，
				  name:findname,
				  user_enable:user_enable,
				  
			  } //设定异步数据接口的额外参数
				,page: {
						curr: 1 //重新从第 1 页开始
				}
		});
		

	}
	form.verify({
		username: function(value, item){ //value：表单的值、item：表单的DOM对象
			if(value!=''){
			if(/^.{2,4}$/.test(value)==false){
				return '人名应为2-4个汉字';
			}
		    if(/[\w]+/.test(value)){
		    	return '人名不应包含数字或字母';
		    }
		    if(/[\s]+/.test(value)){
		    	return '人名不应包含空格';
			}
			}
		},
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
		vphone : function(value,item){
			if(value!=''){
			if(/^[\d]{5,6}$/.test(value)==false){
				return '小号为5-6位纯数字';
			}
			}
		},
		ophone : function(value,item){
			if(value!=''){
			if(/^[\d|-]{5,13}$/.test(value)==false){
				return '格式有误';
			}
			}
		}
			
	});




});