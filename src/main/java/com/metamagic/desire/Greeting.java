package com.metamagic.desire;

import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class Greeting {
	

	@PrimaryKey
	@Persistent(column = "_id", customValueStrategy = "uuid")
	private String id;

	private String name;
	
	private String msg;
	
	@Persistent(defaultFetchGroup = "true")
	private Set<TestImpl> tests;
	
	public Greeting(String name, String msg, Set<TestImpl> tests) {
		super();
		this.name = name;
		this.msg = msg;
		this.tests = tests;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Set<TestImpl> getTests() {
		return tests;
	}

	public void setTests(Set<TestImpl> tests) {
		this.tests = tests;
	}

	
	@Override
	public String toString() {
		return "Greeting [id=" + id + ", name=" + name + ", msg=" + msg + ", tests=" + tests + "]";
	}

	@Override
	public int hashCode() {
		return id.hashCode()+tests.hashCode();
	}
	
	
}
