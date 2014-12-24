package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;

@Controller
@RequestMapping("/report")
public class ReportController {
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	/*
	@RequestMapping(method = RequestMethod.GET)
	public String reportPageLoad(Locale locale, Model model) {
		logger.info("[reportPageLoad] " + "");
		
		return Constants.JSPPAGE_REPORT;
	}
	
	@RequestMapping(name="/id/{reportid}", method = RequestMethod.GET)
	public String selectedReportPage(Locale locale, Model model, @PathVariable Integer reportId) {
		
		return Constants.JSPPAGE_REPORT;
	}
	*/
}
