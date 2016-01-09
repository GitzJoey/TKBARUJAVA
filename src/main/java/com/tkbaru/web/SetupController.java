package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Setup;
import com.tkbaru.service.SetupService;

@Controller
@RequestMapping(value = "/setup")
public class SetupController {
	private static final Logger logger = LoggerFactory.getLogger(SetupController.class);
	
	@Autowired
	SetupService setupManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupPageLoad(Locale locale, Model model) {
		logger.info("[setupPageLoad] " + "");
		
		model.addAttribute("setupForm", new Setup());

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_SETUP;
	}

	@RequestMapping(value = "/install", method = RequestMethod.POST)
	public String setupInstall(Locale locale, Model model, @ModelAttribute("setupForm") Setup setupData) {
		logger.info("[setupInstall] " + "");
		

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_SETUP;
	}

	@RequestMapping(value = "/encrypt/{text}", method = RequestMethod.GET)
	public String setupPageLoad(Locale locale, Model model, @PathVariable String text) {
		logger.info("[setupPageLoad] " + "text: " + text);
				
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		model.addAttribute("eText", setupManager.encryptString(text));
		
		return Constants.JSPPAGE_SETUP;
	}
}
