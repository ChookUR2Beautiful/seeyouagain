package com.xmniao.entity;

public class Resultable {
	
	private String status;
	
	private String message;
	

	public Resultable() {
		
	}

	public Resultable(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
