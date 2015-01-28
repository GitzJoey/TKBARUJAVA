package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;

@Controller
@RequestMapping("/sales")
public class SalesOrderController {
	private static final Logger logger = LoggerFactory.getLogger(SalesOrderController.class);

	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String salesNew(Locale locale, Model model) {
		logger.info("[salesNew] " + "");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}

	@RequestMapping(value="/revise", method = RequestMethod.GET)
	public String salesRevise(Locale locale, Model model) {
		logger.info("[salesRevise] " + "");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_REVISE;
	}

	@RequestMapping(value="/payment", method = RequestMethod.GET)
	public String salesPayment(Locale locale, Model model) {
		logger.info("[salesPayment] " + "");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_PAYMENT;
	}
}
