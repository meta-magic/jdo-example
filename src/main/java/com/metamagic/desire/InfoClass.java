package com.metamagic.desire;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable="true")
@EmbeddedOnly
public class InfoClass {

	private String infoId;
	
	public InfoClass() {
		
	}
	
	public InfoClass(String infoId) {
		this.infoId = infoId;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	
}
