//客户端的主要API就是 创建websocket、监听打开、关闭、发送信息、处理信息等方法。













layui.use([ 'layer'], function() {
	layer.config({
//		anim: 6, //默认动画风格
//		skin: 'layui-layer-molv', //默认皮肤
//		shade :[0.5, '#000000']
		tipsMore:true
	});
    var websocket = null;
    //判断当前浏览器是否支持WebSocket  window.location.host  localhost:8080
    if('WebSocket' in window) {
    	if(user_id!=null&&user_id!=""){
    		var base="ws:"+window.location.host+"/bgqc/websocket/"+user_id;
            websocket = new WebSocket(base);
        }
    }
    	
//     else if('MozWebSocket' in window) {
//    	websocket = new MozWebSocket("ws://localhost:8080/UserLogin/websocketServer");
//    } else {//如果环境不支持websocket，则使用sockJS来模拟连接实现
//        websocket = new SockJS("http://localhost:8080/UserLogin/sockjsServer");//sockjs/websocket，则是不支持websocket，使用sockJS模拟实现的连接。
//    }
    //打开时
    websocket.onopen = function(evnt) {
        //console.log("  websocket.onopen  ");
    };
    //这个事件是接受后端传过来的数据
    websocket.onmessage = function(evnt) {
    	//根据业务逻辑解析数据
    	layer.alert(event.data);
//        $("#msg").append("<p>(<font color='red'>" + evnt.data + "</font>)</p>");
        //console.log("  websocket.onmessage   ");
    };
    websocket.onerror = function(evnt) {
        //console.log("  websocket.onerror  ");
    };
    websocket.onclose = function(evnt) {
        //console.log("  websocket.onclose  ");
    };
 
  
});