/**
 * 所有的leyui，要使用前都得以use开头，把用到的模块都写进去
 */
layui.use([ 'laydate', 'jquery', 'table','layer','form','element' ], function() {
	var $=layui.$;
	var table=layui.table;
	var form=layui.form;
	var element = layui.element;
	var laydate=layui.laydate;
	var tabnow=0;
	var url=["../getUserReport.do","../getDriverReport.do","../getCarReport.do"]
	var tablename=["usertable","drivertable","cartable"];
	//注意，这个是每个页面都要的权限验证，先验证是否登录，再验证是否有权限，没有权限的不给看
	//alert("333");
	if(name==''){
		layer.msg("您还未登录", { icon: 0 , closeBtn: 0 } ,function() {
		window.location.href='../index.jsp';
		});
	}
	element.on('tab(maintab)', function(data){
//		  console.log(this); //当前Tab标题所在的原始DOM元素
//		  console.log(data.index); //得到当前Tab的所在下标
//		  console.log(data.elem); //得到当前的Tab大容器
		tabnow=data.index;
		
		
		
		table.reload(tablename[tabnow],{									//刷新表
				url:url[tabnow],
			  where: {												//两个参数，分别是是否停用，
				  fromtime:$("#timefromto").val().substring(0,19),
				  totime:$("#timefromto").val().substring(22,41)
				 
			  } //设定异步数据接口的额外参数
			  
		});
		
	});
	$("#timefromto").val(onemonth());
	//主表的显示情况
	table.render({
		elem : '#cartable',	//表的选择
		url : '', //请求后台的URL（*）
		//url : 'getCarReport.do', //请求后台的URL（*）
		method:'POST',
		where:{
			fromtime:$("#timefromto").val().substring(0,19),
			totime:$("#timefromto").val().substring(22,41)
		},			//默认无任何参数
		even:true,			//奇偶行变色
		totalRow:true,
		height:"full-550",
		title:'车辆报表',		//表格的标题，没啥用
		loading:true,
		cols : [[				//每个列的字段
			{
				field : 'car_id',	//字段唯一标识，要求和后端传来的json字段一致，基本就是javabean的字段
				title : '序号',		//显示出来的标题
				hide:true			//隐藏，因为序号不可见比较好
			},
			{
				field : 'car_number',
				title : '车牌号',
				align :'center',	//居中显示
			}, 
			{
				field : 'taskcount',
				title : '运行次数',
				align :'center',
				totalRow:true
			}, 
			{
				field : 'requestcount',
				align :'center',
				title : '运送人次',
				totalRow:true
			},
			{
				field : 'usetime',
				align :'center',
				title : '行驶时间',
				templet:function(d){
					return timefromsecond(d.usetime);
				}
			},
			]],
			done: function(res, curr, count){
			    //如果是异步请求数据方式，res即为你接口返回的信息。
			    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
			    console.log(res);
			    var car_number=[];
			    var taskcount=[];
			    var requestcount=[];
			    var usetime=[];
			    for(var i=0;i<res.data.length;i++){
			    	car_number[i]=res.data[i].car_number;
			    	taskcount[i]=res.data[i].taskcount;
			    	requestcount[i]=res.data[i].requestcount;
			    	usetime[i]=res.data[i].usetime;
			    }
			    var carpic = echarts.init(document.getElementById('carpic'));
			    var option = {
			            title: {
			                text: '车辆运用状况'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                },
			            },
			            legend: {
			                data:['运行次数','运送人次','行驶时间']
			            },
			            grid: {
			                left: '3%',
			                right: '4%',
			                bottom: '3%',
			                containLabel: true
			            },
			            xAxis : [
			                {
			                    type : 'category',
			                    data : car_number
			                }
			            ],
			            yAxis : [
			                {
			                	name:"运行次数",
			                    type : 'value'
			                },
			                {
			                	name:"运送人次",
			                    type : 'value'
			                },
			                {
			                	name:"行驶时间",
			                    type : 'value',
			                    offset:50,
			                    axisLabel:{
			                        formatter: function (value) {
									    return timefromsecond(value);
			                        }
			                    }
			                }
			            ],
			            series : [
			                {
			                    name:'运行次数',
			                    type:'bar',
			                    data:taskcount,
			                    yAxisIndex:0
			                },
			                {
			                    name:'运送人次',
			                    type:'bar',
			                    data:requestcount,
			                    yAxisIndex:1
			                    
			                },
			                {
			                    name:'行驶时间',
			                    type:'bar',
			                    data:usetime,
			                    yAxisIndex:2,
			                   
			                    
			                    
			                },
			            ]
			        };

			        // 使用刚指定的配置项和数据显示图表。
			        carpic.setOption(option);
		
			        
			  }
	});
	
	//以下是司机工作情况
	
	table.render({
		elem : '#drivertable',	//表的选择
		url : '', //请求后台的URL（*）
		//url : 'getDriverReport.do', //请求后台的URL（*）
		method:'POST',
		where:{
			fromtime:$("#timefromto").val().substring(0,19),
			totime:$("#timefromto").val().substring(22,41)
		},			//默认无任何参数
		even:true,			//奇偶行变色
		loading:true,
		height:"full-550",
		title:'司机报表',		//表格的标题，没啥用
		totalRow:true,
		cols : [[				//每个列的字段
			{
				field : 'driver_id',	//字段唯一标识，要求和后端传来的json字段一致，基本就是javabean的字段
				title : '序号',		//显示出来的标题
				hide:true			//隐藏，因为序号不可见比较好
			},
			{
				field : 'name',
				title : '司机姓名',
				align :'center',	//居中显示
			}, 
			{
				field : 'taskcount',
				title : '出车趟数',
				align :'center',
				totalRow:true
			}, 
			{
				field : 'requestcount',
				align :'center',
				title : '运送人次',
				totalRow:true
			},
			{
				field : 'usetime',
				align :'center',
				title : '行驶时间',
				templet:function(d){
					return timefromsecond(d.usetime);
				}
			},
			]],
			done: function(res, curr, count){
			    //如果是异步请求数据方式，res即为你接口返回的信息。
			    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
			    console.log(res);
			    var name=[];
			    var taskcount=[];
			    var requestcount=[];
			    var usetime=[];
			    for(var i=0;i<res.data.length;i++){
			    	name[i]=res.data[i].name;
			    	taskcount[i]=res.data[i].taskcount;
			    	requestcount[i]=res.data[i].requestcount;
			    	usetime[i]=res.data[i].usetime;
			    }
			    var driverpic = echarts.init(document.getElementById('driverpic'));
			    var option = {
			            title: {
			                text: '司机工作状况'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            legend: {
			                data:['出车趟数','运送人次','行驶时间']
			            },
			            grid: {
			                left: '3%',
			                right: '4%',
			                bottom: '3%',
			                containLabel: true
			            },
			            xAxis : [
			                {
			                    type : 'category',
			                    data : name
			                }
			            ],
			            yAxis : [
			                {
			                	name:"出车趟数",
			                    type : 'value'
			                },
			                {
			                	name:"运送人次",
			                    type : 'value'
			                },
			                {
			                	name:"行驶时间",
			                    type : 'value',
			                    offset:50,
			                    axisLabel:{
			                        formatter: function (value) {
									    return timefromsecond(value);
			                        }
			                    }
			                }
			            ],
			            series : [
			                {
			                    name:'出车趟数',
			                    type:'bar',
			                    data:taskcount,
			                    yAxisIndex:0
			                },
			                {
			                    name:'运送人次',
			                    type:'bar',
			                    data:requestcount,
			                    yAxisIndex:1
			                    
			                },
			                {
			                    name:'行驶时间',
			                    type:'bar',
			                    data:usetime,
			                    yAxisIndex:2
			                },
			            ]
			        };

			        // 使用刚指定的配置项和数据显示图表。
			        driverpic.setOption(option);
			   
			  }
	});
	
	
	//以下是用户请车情况
	
		table.render({
			elem : '#usertable',	//表的选择
			url : '../getUserReport.do', //请求后台的URL（*）
			method:'POST',
			where:{
				fromtime:$("#timefromto").val().substring(0,19),
				totime:$("#timefromto").val().substring(22,41)
			},			//默认无任何参数
			even:true,			//奇偶行变色
			loading:true,
			height:"full-550",
			title:'用户报表',		//表格的标题，没啥用
			totalRow:true,
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
					field : 'taskcount',
					title : '出车趟数',
					align :'center',
					totalRow:true
				}, 
				{
					field : 'usetime',
					align :'center',
					title : '行驶时间',
					
					templet:function(d){
						return timefromsecond(d.usetime);
					}
				},
				]],
				done: function(res, curr, count){
				    //如果是异步请求数据方式，res即为你接口返回的信息。
				    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
				    console.log(res);
				    var name=[];
				    var taskcount=[];
				    var usetime=[];
				    for(var i=0;i<res.data.length;i++){
				    	name[i]=res.data[i].name;
				    	taskcount[i]=res.data[i].taskcount;
				    	usetime[i]=res.data[i].usetime;
				    }
				    var userpic = echarts.init(document.getElementById('userpic'));
				    var option = {
				            title: {
				                text: '用户出行状况'
				            },
				            tooltip : {
				                trigger: 'axis',
				                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				                }
				            },
				            
				            legend: {
				                data:['出车趟数','行驶时间']
				            },
				            grid: {
				                left: '3%',
				                right: '4%',
				                bottom: '3%',
				                containLabel: true
				            },
				            xAxis : [
				                {
				                    type : 'category',
				                    data : name,
				                    axisLabel: {  
//			                               interval: 0,  
			                               formatter:function(value)  
			                               {  
			                                   return value.split("").join("\n");  
			                               }  
			                           }  
				                }
				            ],
				            yAxis : [
				                {
				                	name:"出车趟数",
				                    type : 'value'
				                },
				                {
				                	name:"行驶时间",
				                    type : 'value',
				                    axisLabel:{
				                        formatter: function (value) {
										    return timefromsecond(value);
				                        }
				                    }
				                }
				            ],
				            series : [
				                {
				                    name:'出车趟数',
				                    type:'bar',
				                    data:taskcount,
				                    yAxisIndex:0
				                },
				        
				                {
				                    name:'行驶时间',
				                    type:'bar',
				                    data:usetime,
				                    yAxisIndex:1
				                },
				            ]
				        };

				        // 使用刚指定的配置项和数据显示图表。
				        userpic.setOption(option);
				  
				  }
		});
	
	
	
	
	laydate.render({
		elem: '#timefromto' //指定元素
    	,type: 'datetime'
    	,range:true
    	,format:"yyyy-MM-dd HH:mm:ss"
    	,btns: ['now', 'confirm']
		,done: function(value, date, endDate){
//		    console.log(value); //得到日期生成的值，如：2017-08-18
//		    console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
//		    console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
			var tablename=["usertable","drivertable","cartable"];
			
			table.reload(tablename[tabnow],{									//刷新表
				url:url[tabnow],
				  where: {												//两个参数，分别是是否停用，
					  fromtime:value.substring(0,19),
					  totime:value.substring(22,41)
					 
				  } //设定异步数据接口的额外参数
				  
			});
		  }
	});	
	

	//echars
	

     // 指定图表的配置项和数据
     
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//date tools
	function onemonth(){
		var timeto=getFormatDate();
		var mm=eval(timeto.substring(5,7));
		var yy=eval(timeto.substring(0,4));
		
		mm=mm-1;
		if(mm==0){
			mm=12;
			yy=yy-1;
		}
		if(mm<10){
			mm="0"+mm;
		}
		var timefrom=yy+'-'+mm+timeto.substring(7,19);
		
		return timefrom+" - "+timeto;
		
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
	function timefromsecond(ss){
		ss=eval(ss);
		var s=ss%60;
		ss=(ss-s)/60;
		var m=ss%60;

		ss=(ss-m)/60;
		var h=ss%24;
		ss=(ss-h)/24;
		var d=ss;
		var str='';
		if(d!=0){
			str=str+d+"天";
		}
		if(h!=0){
			str=str+h+"小时";
		}
		if(m!=0){
			str=str+m+"分钟";
		}
		if(s!=0){
			str=str+s+"秒";
		}
		return str;
	}
});