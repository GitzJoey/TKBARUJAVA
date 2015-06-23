package com.tkbaru.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.RoleService;
import com.tkbaru.service.SmsInService;
import com.tkbaru.service.StoreService;
import com.tkbaru.service.UserService;

@Controller
@RequestMapping("/admin/smsin")
public class SmsInController {

	@Autowired
	UserService userManager;

	@Autowired
	LookupService lookupManager;

	@Autowired
	RoleService roleManager;

	@Autowired
	StoreService storeManager;
	
	@Autowired
	SmsInService smsInManager;

	@Autowired
	private LoginContext loginContextSession;

	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String sms(Locale locale, Model model) {

		model.addAttribute("smsList", smsInManager.getAllSmsIn());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "sms_in";
	}

}
