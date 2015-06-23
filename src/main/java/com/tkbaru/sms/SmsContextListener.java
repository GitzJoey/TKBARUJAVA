package com.tkbaru.sms;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Component
public class SmsContextListener implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	    final SmsService smsService = (SmsService)springContext.getBean("SmsService");
		try {
			smsService.stopService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	    final SmsService smsService = (SmsService)springContext.getBean("SmsService");
		try {
			smsService.startService();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
