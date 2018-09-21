package com.bgqc.websocket;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.bgqc.util.Lg;

//类注解，告诉虚拟机该类被注解为一个WebSocket端点
//@ServerEndpoint("/websocket")
@ServerEndpoint(value = "/websocket/{userid}")
@Component
public class WebSocketServer {

	private Session session;//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private static Map<String,Session> sessionPool = new HashMap<String,Session>();
	private static Map<String,String> sessionIds = new HashMap<String,String>();

	@OnMessage  //方法注解，在接收到客户端的消息时触发 
	public void onMessage(String message, Session session) throws IOException, InterruptedException{
		System.out.println("当前发送人sessionid为"+session.getId()+"发送内容为"+message);
		// Send the first message to the client
		session.getBasicRemote().sendText("This is the first server message");
		// Send 3 messages to the client every 5 seconds
		int sentMessages = 0;
		while(sentMessages < 3){
			Thread.sleep(5000);
			session.getBasicRemote().sendText("服务器所发消息. Count: " + sentMessages+" :内容： " + message);
			sentMessages++;
		}
		// Send a final message to the client
		session.getBasicRemote().sendText("This is the last server message");
	}
	@OnOpen //用户连接时触发
	public void open(Session session,@PathParam(value="userid")String userid){
		Lg.debug("推送连接，用户id是" + userid);
		this.session = session;
		sessionPool.put(userid, session);
		sessionIds.put(session.getId(), userid);
	}

	@OnClose //连接关闭触发
	public void onClose(){
		System.out.println("Connection closed");
		sessionPool.remove(sessionIds.get(session.getId()));
		sessionIds.remove(session.getId());
	}
	@OnError //发生错误时触发
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

	//信息发送的方法
	public static void sendMessage(String message,String userId){
		Session s = sessionPool.get(userId);
		if(s!=null){
			try {
				s.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//获取当前连接数
	public static int getOnlineNum(){
		return sessionPool.size();
	}

	//获取在线用户名以逗号隔开
	public static String getOnlineUsers(){
		StringBuffer users = new StringBuffer();
		for (String key : sessionIds.keySet()) {
			users.append(sessionIds.get(key)+",");
		}
		return users.toString();
	}

	//信息群发
	public static void sendAll(String msg) {
		for (String key : sessionIds.keySet()) {
			sendMessage(msg, sessionIds.get(key));
		}
	}

	//多个人发送给指定的几个用户
	public static void SendMany(String msg,String [] persons) {
		for (String userid : persons) {
			sendMessage(msg, userid);
		}
	}
}
