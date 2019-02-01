package com.ex.interceptor;


public class ExLog {
	//sessionObject
	private Object object;
	
	public void setObject(Object object) {
		this.object = object;
	}
	public Object getObject() {
		return object;
	}
	@Override
	public String toString() {
		return "Log [SessionObject=" + object + "]";
	}
	
}
