package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;

@Controller
@RequestMapping("/monitor")
public class MonitorController {
	private static final Logger logger = LoggerFactory.getLogger(MonitorController.class);

	@Autowired
	private LoginContext loginContextSession;

	@RequestMapping(value="/todaydelivery", method = RequestMethod.GET)
	public String todayDeliveryMonitorPage(Locale locale, Model model) {
		logger.info("[todayDeliveryMonitorPage] : " + "");

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_TODAYDELIVERY;
	}

	@RequestMapping(value="/stocks", method = RequestMethod.GET)
	public String stocksMonitorPage(Locale locale, Model model) {
		logger.info("[stocksMonitorPage] : " + "");
			
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_STOCKS;
	}

}
