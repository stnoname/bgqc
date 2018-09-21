/**
 * 所有的leyui，要使用前都得以use开头，把用到的模块都写进去
 */
layui.use([ 'laydate', 'jquery','layer','form','element' ], function() {
	var $=layui.$;
	var form=layui.form;
	var element = layui.element;
	var laydate=layui.laydate;

	//注意，这个是每个页面都要的权限验证，先验证是否登录，再验证是否有权限，没有权限的不给看
//	if(name!='李挺'){
//		layer.alert("您还未登录", { icon: 0 , closeBtn: 0 } ,function() {
//		window.location.href='index.jsp';
//		});
//	}
	
	$("#timefromto").val(thenyear());
	
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
		  }
	});	
	

     // 指定图表的配置项和数据
     
	form.on('submit(submit)', function(data){				//点击表单的提交按钮
		//console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
		//console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		
		//alert("a");
		
		data.field.fromtime=$("#timefromto").val().substring(0,19);
		data.field.totime=$("#timefromto").val().substring(22,41);
		console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		
		//alert("b");
		$.ajax({
			type : "POST",
			url : "../test.do",
			data : data.field,
			dataType : "json",
			success : function(msg) {
				layer.alert(msg.msg);
			}
		})
		//alert("c");
		return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//date tools
	function thenyear(){
		var timeto=getFormatDate();
		var yy=eval(timeto.substring(0,4));
		yy=yy-10;
		
		var timefrom=yy+timeto.substring(4,19);
		
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
		ss=(ss-h)/60;
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