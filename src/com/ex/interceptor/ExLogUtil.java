package com.ex.interceptor;

public class ExLogUtil {
	private static final ThreadLocal<ExLog> THREAD_LOCAL=new InheritableThreadLocal<ExLog>();
	
	public static ExLog getThreadLocalLog(){
		if(THREAD_LOCAL.get()==null){
			return new ExLog();
		}else{
			return THREAD_LOCAL.get();
		}
	}
	
	public static void setThreadLocalLog(ExLog log){
		THREAD_LOCAL.set(log);
	}
	
	public static void remove(){
		THREAD_LOCAL.remove();
	}
	

}
