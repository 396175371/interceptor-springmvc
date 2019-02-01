package com.ex.interceptor;

import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class ExLogInterceptor implements HandlerInterceptor {
	private  final Logger logger = LoggerFactory.getLogger(ExLogInterceptor.class);
	private String session_key;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try{
			if(handler instanceof HandlerMethod){
				ExLog Log=new ExLog();
				Object object=new Object();
				if(!StringUtils.isEmpty(getSession_key())){
					object=request.getSession().getAttribute(getSession_key());
					Log.setObject(object);
					//通过threadLocal保存当前登录用户
					ExLogUtil.setThreadLocalLog(Log);
				}
				HandlerMethod method=(HandlerMethod)handler;
				//获取当前方法所在的当前类
				Class<?> clazz=method.getMethod().getDeclaringClass();
				//请求的类名
				String clazzName=clazz.getName();
				//请求的方法名
				String methodName=method.getMethod().getName();
				//遍历请求中Attribute
				Enumeration attrNames= request.getAttributeNames();
				while(attrNames.hasMoreElements()){
					String name=(String) attrNames.nextElement();
					Object Attrobject=request.getAttribute(name);
					logger.info("开始访问:controller:"+clazzName+",controllerMethod:"+methodName+",Attrname:"+name+"value:"+Attrobject.toString());
				}
				//遍历请求中param
				Map<String, Object> paramMap=request.getParameterMap();
				for(Entry<String, Object> entry:paramMap.entrySet()){
					System.out.println("key:"+entry.getKey()+",value:"+entry.getValue().toString());
					logger.info("开始访问:controller:"+clazzName+",controllerMethod:"+methodName+",Param:key:"+entry.getKey()+",value:"+entry.getValue().toString());

				}
				logger.info("开始访问:controller:"+clazzName+",controllerMethod:"+methodName+",user:"+ExLogUtil.getThreadLocalLog().toString());

				return true;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		try{
			if(handler instanceof HandlerMethod){
				HandlerMethod method=(HandlerMethod)handler;
				Class<?> clazz=method.getMethod().getDeclaringClass();
				String clazzName=clazz.getName();
				String methodName=method.getMethod().getName();
				logger.info("访问:controller:"+clazzName+",controllerMethod:"+methodName+",user:"+ExLogUtil.getThreadLocalLog().toString());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		try{
			if(handler instanceof HandlerMethod){
				HandlerMethod method=(HandlerMethod)handler;
				Class<?> clazz=method.getMethod().getDeclaringClass();
				String clazzName=clazz.getName();
				String methodName=method.getMethod().getName();
				logger.info("访问结束:controller:"+clazzName+",controllerMethod:"+methodName+",user:"+ExLogUtil.getThreadLocalLog().toString());
				ExLogUtil.remove();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
}
