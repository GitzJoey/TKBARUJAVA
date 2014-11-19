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
public class LookupController {
	private static final Logger logger = LoggerFactory.getLogger(LookupController.class);
	
	@RequestMapping(value = "/admin/lookup.html", method = RequestMethod.GET)
	public String lookupPageLoad(Locale locale, Model model) {

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_LOOKUP;
	}

	@RequestMapping(value = "/admin/lookup/add.html", method = RequestMethod.GET)
	public String lookupAdd(Locale locale, Model model) {

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_LOOKUP;
	}

	@RequestMapping(value = "/admin/lookup/edit.html", method = RequestMethod.GET)
	public String lookupEdit(Locale locale, Model model) {

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_LOOKUP;
	}

	@RequestMapping(value = "/admin/lookup/delete.html", method = RequestMethod.GET)
	public String lookupDelete(Locale locale, Model model) {
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_LOOKUP;
	}

}
