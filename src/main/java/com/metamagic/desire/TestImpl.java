package com.metamagic.desire;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
@EmbeddedOnly
public class TestImpl {

	private String value;

	private String name;

	@Persistent(defaultFetchGroup = "true")
	private Message message;

	public TestImpl(String value, String name, Message message) {
		super();
		this.value = value;
		this.name = name;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TestImpl [value=" + value + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

}
