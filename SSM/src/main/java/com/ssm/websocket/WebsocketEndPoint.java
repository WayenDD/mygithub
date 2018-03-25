package com.ssm.websocket;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebsocketEndPoint extends TextWebSocketHandler {
//	public static CopyOnWriteArraySet<WebsocketEndPoint> webSocketSet = new CopyOnWriteArraySet<WebsocketEndPoint>();   
	public static ConcurrentHashMap<String, WebsocketEndPoint> webSocketMap = new ConcurrentHashMap<String, WebsocketEndPoint>();
	private static int onlineCount = 0; 
	private String userid="";
	private WebSocketSession usersession;


	public static synchronized int getOnlineCount() {  
		return onlineCount;  
	}  

	public static synchronized void addOnlineCount() {  
		WebsocketEndPoint.onlineCount++;  
	}  

	public static synchronized void subOnlineCount() {  
		WebsocketEndPoint.onlineCount--;  
	}  

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		System.out.println("进入了工具类");
		TextMessage returnMessage = new TextMessage(message.getPayload()+" 后台的消息");

		if (!WebsocketEndPoint.webSocketMap.keySet().contains(message.getPayload())) {
			this.webSocketMap.put(message.getPayload(), this);
			this.userid=message.getPayload();
			this.usersession=session;
			addOnlineCount();
		}
		System.out.println("当前在线人数为"+getOnlineCount());
//		session.sendMessage(returnMessage);
//		sendMessage("honglian");
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("一客户端断开连接");
	}
	
	public static void sendMessage(String userid) throws IOException{
		Iterator<Entry<String, WebsocketEndPoint>> iterator = webSocketMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, WebsocketEndPoint> next = iterator.next();
			String name = next.getKey();
			WebsocketEndPoint we = next.getValue();
			if (name.equals(userid)) {
				we.usersession.sendMessage(new TextMessage("hello "+ userid));
			}
		}
	}
	
	public void sendThread(){
		new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						sendMessage("honglian");
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
