package com.tkbaru.web;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.SmsOut;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.RoleService;
import com.tkbaru.service.SmsOutService;
import com.tkbaru.service.SmsServiceImpl;
import com.tkbaru.service.StoreService;
import com.tkbaru.service.UserService;

@Controller
@RequestMapping("/admin/smsout")
public class SmsOutController {

	@Autowired
	UserService userManager;

	@Autowired
	LookupService lookupManager;

	@Autowired
	RoleService roleManager;

	@Autowired
	StoreService storeManager;
	
	@Autowired
	SmsOutService smsOutService;

	@Autowired
	private LoginContext loginContextSession;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String smsout(Locale locale, Model model) {
        model.addAttribute("smsOut", new SmsOut());
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "sms_out";
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String sms(Locale locale, Model model, @ModelAttribute("smsOut") SmsOut smsOut) {

		SmsServiceImpl smsSend = new SmsServiceImpl();
		try {
			smsSend.send(smsOut.getRecipient(), smsOut.getMessage());
			
			smsOut.setCreatedDate(new Date());
			smsOutService.addSmsOutbox(smsOut);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "sms_out";
	}

}
