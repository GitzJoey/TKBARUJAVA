package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LookupController {
	private static final Logger logger = LoggerFactory.getLogger(LookupController.class);
	
	@RequestMapping(value = "/admin/lookup.html", method = RequestMethod.GET)
	public String lookupPageLoad(Locale locale, Model model) {
		return "lookup";
	}

	@RequestMapping(value = "/admin/lookup/add.html", method = RequestMethod.GET)
	public String lookupAdd(Locale locale, Model model) {
		return "lookup";
	}

	@RequestMapping(value = "/admin/lookup/edit.html", method = RequestMethod.GET)
	public String lookupEdit(Locale locale, Model model) {
		return "lookup";
	}

	@RequestMapping(value = "/admin/lookup/delete.html", method = RequestMethod.GET)
	public String lookupDelete(Locale locale, Model model) {
		return "lookup";
	}

}
