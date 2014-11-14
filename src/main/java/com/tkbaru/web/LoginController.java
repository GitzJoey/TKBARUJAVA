package com.tkbaru.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tkbaru.model.User;
import com.tkbaru.service.LoginService;
import com.tkbaru.service.UserService;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginManager;
	
	@Autowired
	UserService userManager;
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Landed in Login Page! The client locale is {}.", locale);
				
		String messageText = "";

		model.addAttribute("collapseFlag", "collapse");
		model.addAttribute("messageText", messageText);
		
		return "login";
	}

	@RequestMapping(value = "/dologin.html", method = RequestMethod.POST)
	public String dologin(@RequestParam("username") String userName, @RequestParam("password") String userPswd, Model model) {
		logger.info("Process dologin! Parameter = " + "userName:" + userName + ", userPswd:" + userPswd);
		
		boolean loginSuccess = loginManager.successLogin(userName); 

		User userdata = loginManager.createUserContext(userName);
		
		
		if (!loginSuccess) {
			String messageText = "Better check yourself, you're not looking too good.";
		
			model.addAttribute("collapseFlag", "");
			model.addAttribute("messageText", messageText);
			
			return "login";
		}
		
		return "redirect:/dashboard.html";
	}

}
