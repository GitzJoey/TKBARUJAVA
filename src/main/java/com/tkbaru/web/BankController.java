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
@RequestMapping("/bank")
public class BankController {
	private static final Logger logger = LoggerFactory.getLogger(BankController.class);
	
	@RequestMapping(value="/upload", method = RequestMethod.GET)
	public String bankUpload(Locale locale, Model model) {
		logger.info("[bankUpload] " + "");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_BANK;
	}

	@RequestMapping(value="/consolidate", method = RequestMethod.GET)
	public String bankConsolidate(Locale locale, Model model) {
		logger.info("[bankConsolidate] " + "");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_BANK;
	}

}
