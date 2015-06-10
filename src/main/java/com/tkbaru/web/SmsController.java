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
import com.tkbaru.model.Sms;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.RoleService;
import com.tkbaru.service.StoreService;
import com.tkbaru.service.UserService;
import com.tkbaru.sms.SMSSendService;

@Controller
@RequestMapping("/sms")
public class SmsController {
	
	@Autowired
	UserService userManager;
	
	@Autowired
	LookupService lookupManager;
	
	@Autowired
	RoleService roleManager;
	
	@Autowired
	StoreService storeManager;
	
	@Autowired
	private LoginContext loginContextSession;


	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String sms(Locale locale, Model model, @ModelAttribute Sms sms) {
        
		SMSSendService smsSend = new SMSSendService(sms.getRecipientNo(), sms.getMessage());
		try {
			smsSend.startService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "sms";
	}

}
