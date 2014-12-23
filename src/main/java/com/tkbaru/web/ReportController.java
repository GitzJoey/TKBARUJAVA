package com.tkbaru.web;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;

@Controller
@RequestMapping(name="/report")
public class ReportController {

	@RequestMapping(method = RequestMethod.GET)
	public String reportPageLoad(Locale locale, Model model) {
		
		return Constants.JSPPAGE_REPORT;
	}
	
	@RequestMapping(name="/{reportid}", method = RequestMethod.GET)
	public String selectedReportPage(Locale locale, Model model) {
		
		return Constants.JSPPAGE_REPORT;
	}
}
