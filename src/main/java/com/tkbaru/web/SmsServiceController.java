package com.tkbaru.web;

import java.io.IOException;
import java.util.Locale;

import org.smslib.GatewayException;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.RoleService;
import com.tkbaru.service.StoreService;
import com.tkbaru.service.UserService;
import com.tkbaru.sms.SmsService;

@Controller
@RequestMapping("/sms")
public class SmsServiceController {

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

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String start(Locale locale, Model model) {

		SmsService sms = new SmsService();
		try {
			sms.startService();
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "sms_start";
	}
	
	
	
	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public String stop(Locale locale, Model model) {

		try {
			Service.getInstance().stopService();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SMSLibException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "sms_stop";
	}

}
