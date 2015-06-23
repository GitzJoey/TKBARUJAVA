package com.tkbaru.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Modem;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.ModemService;
import com.tkbaru.service.RoleService;
import com.tkbaru.service.StoreService;
import com.tkbaru.service.UserService;

@Controller
@RequestMapping("/admin/modem")
public class ModemController {

	@Autowired
	UserService userManager;

	@Autowired
	LookupService lookupManager;

	@Autowired
	RoleService roleManager;

	@Autowired
	StoreService storeManager;
	
	@Autowired
	ModemService modemManager;

	@Autowired
	private LoginContext loginContextSession;

	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String sms(Locale locale, Model model) {

		model.addAttribute("modem", modemManager.getModemById(1));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "modem";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveModem(Locale locale, Model model, @ModelAttribute("modem") Modem modem) {

		modemManager.editModem(modem);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "modem";
	}


}
