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
import com.tkbaru.service.LoginService;
import com.tkbaru.service.StoreService;

@Controller
@RequestMapping("/static")
public class StaticController {
	private static final Logger logger = LoggerFactory.getLogger(StaticController.class);

	@Autowired
	StoreService storeManager;
	
	@Autowired
	LoginService loginManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String staticPageLoad(Locale locale, Model model) {

		return "redirect:/static/index.html";
	}

	@RequestMapping(value="/index.html", method = RequestMethod.GET)
	public String staticIndex(Locale locale, Model model) {
		logger.info("[staticIndex] : " + "");

		boolean dbReady = loginManager.checkDB();
		if (!dbReady) {
			return Constants.JSPPAGE_STATIC;
		}
		
		model.addAttribute("storeData", storeManager.getDefaultStore());
		
		return Constants.JSPPAGE_STATIC;
	}
}
