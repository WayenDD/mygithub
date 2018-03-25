<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=" <%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="cache-control" content="no-cache,must-revalidate">
<meta http-equiv="expires" content="0">
<title>welcome</title>
<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
<script type="text/javascript">
	var websocket = null;
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://192.168.1.12:8080/SSM/myHandler");
	} else {
		alert("浏览器不支持websocket");
	}

	websocket.onerror = function() {
		setMessageInnerHTML("websocket发生了错误");
	};

	websocket.onopen = function() {
		websocket.send("honglian");
		setMessageInnerHTML("WebSocket连接成功");
	}

	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		setMessageInnerHTML(event.data);
	}

	//连接关闭的回调方法
	websocket.onclose = function() {
		setMessageInnerHTML("WebSocket连接关闭");
	}
	
	//将消息显示在网页上
	function setMessageInnerHTML(innerHTML) {
		document.getElementById('message').innerHTML += innerHTML + '<br/>';
	}
	
	function closeWebSocket(){
		websocket.close();
	}
	
	function send(){
		var message = document.getElementById("text").value;
		websocket.send(message);
	}
</script>

</head>

<body>
	Welcome ${name}
	<br />
	<input id="text" type="text" />
	<button onclick="send()">发送消息</button>
	<hr />
	<button onclick="closeWebSocket()">关闭WebSocket连接</button>
	<hr />
	<div id="message"></div>
</body>
</html>