package com.metamagic.desire;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable="true")
@EmbeddedOnly
public class Message {

	private String data;
	
	public Message() {
	}
	
	public Message(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Message [data=" + data + "]";
	}
}
