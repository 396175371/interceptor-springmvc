package com.ex.interceptor;


import java.util.Map;


public class ExLog {
	//sessionObject
	private Map<String,Object> sessionObjects;

	public Map<String, Object> getSessionObjects() {
		return sessionObjects;
	}

	public void setSessionObjects(Map<String, Object> sessionObjects) {
		this.sessionObjects = sessionObjects;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer=new StringBuffer("Log[SessionObjects=");
		if(null!=sessionObjects){
            for (Map.Entry<String,Object> entry:sessionObjects.entrySet()){
                stringBuffer.append("{");
                stringBuffer.append(entry.getKey());
                stringBuffer.append(":");
                stringBuffer.append(entry.getValue());
                stringBuffer.append("}");
            }
        }
        stringBuffer.append("]");
		return stringBuffer.toString();
	}
	
}
