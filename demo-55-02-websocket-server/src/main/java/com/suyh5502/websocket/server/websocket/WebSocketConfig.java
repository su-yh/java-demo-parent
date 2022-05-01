package com.suyh5502.websocket.server.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * 
 * 说明：WebScoket配置处理器
 * 把处理器和拦截器注册到spring websocket中
 * @author 传智.BoBo老师
 * @version 1.0
 * @date 2016年10月27日
 */
@Component("webSocketConfig")
//配置开启WebSocket服务用来接收ws请求
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	//注入处理器
	@Resource
	private ChatWebSocketHandler webSocketHandler;
	@Resource
	private ChatHandshakeInterceptor chatHandshakeInterceptor;

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//添加一个处理器还有定义处理器的处理路径
		registry.addHandler(webSocketHandler, "/ws").addInterceptors(chatHandshakeInterceptor);
	}
}
