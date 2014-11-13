package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@RequestMapping(value="/dashboard.html", method = RequestMethod.GET)
	public String dashboard(Locale locale, Model model) {
		logger.info("Landed in Dashboard Page! The client locale is {}.", locale);
		
		return "dashboard";
	}
}
