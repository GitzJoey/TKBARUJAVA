package com.tkbaru.web;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Lookup;
import com.tkbaru.service.LookupService;

@Controller
public class LookupController {
	private static final Logger logger = LoggerFactory.getLogger(LookupController.class);
	
	@Autowired
	LookupService lookupManager;
	
	@RequestMapping(value = "/admin/lookup.html", method = RequestMethod.GET)
	public String lookupPageLoad(Locale locale, Model model) {

		List<Lookup> lookupList = lookupManager.getAllLookup();
		
		model.addAttribute("lookupList", lookupList);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_LOOKUP;
	}

	@RequestMapping(value = "/admin/lookup/add.html", method = RequestMethod.GET)
	public String lookupAdd(Locale locale, Model model) {

		model.addAttribute("lookupForm", new Lookup());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_LOOKUP;
	}

	@RequestMapping(value = "/admin/lookup/edit/{selectedId}.html", method = RequestMethod.GET)
	public String lookupEdit(Locale locale, Model model, @PathVariable Integer selectedId) {

		Lookup l = lookupManager.getLookupById(selectedId);
		
		model.addAttribute("lookupForm", l);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_LOOKUP;
	}

	@RequestMapping(value = "/admin/lookup/delete/{selectedId}.html", method = RequestMethod.GET)
	public String lookupDelete(Locale locale, Model model, @PathVariable Integer selectedId) {
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_LOOKUP;
	}

}
