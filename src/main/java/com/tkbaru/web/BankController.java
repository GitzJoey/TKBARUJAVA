package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Bank;
import com.tkbaru.model.LoginContext;
import com.tkbaru.service.BankService;
import com.tkbaru.service.LookupService;

@Controller
@RequestMapping("/bank")
public class BankController {
	private static final Logger logger = LoggerFactory.getLogger(BankController.class);
	
	@Autowired
	BankService bankManager;
	
	@Autowired
	LookupService lookupManager;
	
	@Autowired
	private LoginContext loginContextSession;
	
	@RequestMapping(value="/upload", method = RequestMethod.GET)
	public String bankUpload(Locale locale, Model model) {
		logger.info("[bankUpload] " + "");

		model.addAttribute("bankForm", new Bank());
		model.addAttribute("bankProviderDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_BANK;
	}

	@RequestMapping(value="/uploaded", method = RequestMethod.POST)
	public String bankFileUploaded(Locale locale, Model model, @ModelAttribute("bankForm") Bank bank) {
		logger.info("[bankFileUploaded] " + "");
		
		bankManager.bankUpload(bank);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_SHOW);

		return Constants.JSPPAGE_BANK;		
	}
	
	@RequestMapping(value="/consolidate", method = RequestMethod.GET)
	public String bankConsolidate(Locale locale, Model model) {
		logger.info("[bankConsolidate] " + "");
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_BANK;
	}

}
