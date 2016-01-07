package com.tkbaru.web;

import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Lookup;
import com.tkbaru.service.LookupService;

@Controller
@RequestMapping("/admin/lookup")
public class LookupController {
	private static final Logger logger = LoggerFactory.getLogger(LookupController.class);
	
	@Autowired
	LookupService lookupManager;

	@Autowired
	private LoginContext loginContextSession;

	@RequestMapping(method = RequestMethod.GET)
	public String lookupPageLoad(Locale locale, Model model) {
		logger.info("[lookupPageLoad] " + "");
		
		List<Lookup> lookupList = lookupManager.getAllLookup();
		
		model.addAttribute("lookupList", lookupList);
		model.addAttribute("categoryDDL", lookupManager.getAllCategory());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_LOOKUP;
	}

	@RequestMapping(value = "/bycategory/{category}", method = RequestMethod.GET)
	public String lookupListByCat(Locale locale, Model model, @PathVariable String category) {
		logger.info("[lookupListByCat] " + "");
		
		List<Lookup> lookupList = lookupManager.getLookupByCategory(category);
		
		model.addAttribute("lookupList", lookupList);
		model.addAttribute("categoryDDL", lookupManager.getAllCategory());
		model.addAttribute("selectedCat", category);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_LOOKUP;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String lookupAdd(Locale locale, Model model) {
		logger.info("[lookupAdd] " + "");
		
		model.addAttribute("lookupForm", new Lookup());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("MaintainabilityDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_YESNOSELECTION));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_LOOKUP;
	}

	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String lookupEdit(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[lookupEdit] " + "");
		
		Lookup l = lookupManager.getLookupById(selectedId);
		
		model.addAttribute("lookupForm", l);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("MaintainabilityDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_YESNOSELECTION));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_LOOKUP;
	}

	@RequestMapping(value = "/delete/{selectedId}", method = RequestMethod.GET)
	public String lookupDelete(Locale locale, Model model, @PathVariable Integer selectedId, RedirectAttributes redirectAttributes) {
		logger.info("[lookupDelete] " + "");
		
		lookupManager.deleteLookup(selectedId);
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/admin/lookup";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String lookupSave(Locale locale, Model model, @ModelAttribute("lookupForm") Lookup lookup, RedirectAttributes redirectAttributes) {
		
		if (lookup.getLookupId() == 0) {
			logger.info("[lookupSave] " + "addLookup: " + lookup.toString());
			lookupManager.addLookup(lookup);
		} else {
			logger.info("[lookupSave] " + "editLookup: " + lookup.toString());
			lookupManager.editLookup(lookup);
		}

		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/admin/lookup";
	}
}
