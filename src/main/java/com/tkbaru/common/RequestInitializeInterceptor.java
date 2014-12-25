package com.tkbaru.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestInitializeInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(RequestInitializeInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			//logger.info("[preHandle] " + "");
			
	        if(request.getSession(false) == null){     
	        	logger.info("[preHandle] " + "Session is invalid");
	        	response.sendRedirect(request.getContextPath() + "/login.html");
	        }
	        
			return true;
		} catch (SystemException e) {
			return false;
		}
	}

}
