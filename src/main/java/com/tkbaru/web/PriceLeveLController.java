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
@RequestMapping("/price")
public class PriceLeveLController {
	private static final Logger logger = LoggerFactory.getLogger(PriceLeveLController.class);

	@Autowired
	private LoginContext loginContextSession;

	@RequestMapping(value="/pricelevel", method = RequestMethod.GET)
	public String priceLevelPageLoad(Locale locale, Model model) {
		logger.info("[priceLevelPageLoad] " + "");
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_PRICELEVEL;
	}

}
