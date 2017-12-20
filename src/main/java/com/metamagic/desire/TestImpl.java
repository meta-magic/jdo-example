package com.metamagic.desire;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable="true")
@EmbeddedOnly
public class TestImpl {

	private String value;

	private String name;
	
	
	
	public TestImpl(String value, String name) {
		super();
		this.value = value;
		this.name = name;
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



	@Override
	public String toString() {
		return "TestImpl [value=" + value + ", name=" + name + "]";
	}



	@Override
	public int hashCode() {
		return value.hashCode();
	}


}
