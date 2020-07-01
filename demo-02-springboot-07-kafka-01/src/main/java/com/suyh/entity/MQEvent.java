package com.suyh.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 
 * 消息队列的统一消息对象类.
 * 
 * @author whsonga
 * @date  2020-03-31
 *
 */
@Getter
@Setter
@ToString
public class MQEvent<T> implements Serializable {

	private static final long serialVersionUID = -6312797435038522646L;

	public MQEvent() {
	}

	public MQEvent(String eventId, String eventType) {
		this.eventId = eventId;
		this.eventType = eventType;
	}

	public MQEvent(String eventId, String eventType, T data) {
		this.eventId = eventId;
		this.eventType = eventType;
		this.data = data;
	}
	
	/**
	 * 消息id，每条消息唯一标识, 可以采用uuid等
	 */
	private String eventId;  
	
	/**
	 * 消息类型，用于共享性topic区分不同业务
	 */
	private String eventType; 	
	
	/**
	 * 消息业务对象
	 */
	private T data;
}
