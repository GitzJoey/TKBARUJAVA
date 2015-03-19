package com.tkbaru.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tkbaru.model.LoginContext;
import com.tkbaru.model.User;
import com.tkbaru.service.LoginService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginManager;
	
	@Autowired
	private LoginContext loginContextSession;
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loadLoginPage(Locale locale, Model model, @RequestParam(value="e", required=false) String errParam) {
		logger.info("[loadLoginPage] " + "");

		String messageText = "";
		
		if (!loginManager.checkDB()) {
			messageText = "System Is Not Ready";
			
			model.addAttribute("hideLogin", true);
			model.addAttribute("collapseFlag", "");
			model.addAttribute("messageText", messageText);
			
			return "login";
		}
		
		if (errParam != null) {
			if (errParam.equalsIgnoreCase("invalid")) {
				messageText = "Invalid username or password";	
			} else if (errParam.equalsIgnoreCase("expired")) {
				messageText = "Session expired";
			} else if (errParam.equalsIgnoreCase("session")) {
				messageText = "Invalid Session";
			} else {
				messageText = "";
			}
			
			model.addAttribute("hideLogin", false);
			model.addAttribute("collapseFlag", "");
			model.addAttribute("messageText", messageText);
			
			return "login";			
		}

		model.addAttribute("hideLogin", false);
		model.addAttribute("collapseFlag", "collapse");
		model.addAttribute("messageText", messageText);
		
		return "login";
	}

	@RequestMapping(value = "/dologin.html", method = RequestMethod.GET)	
	public String dologin(Locale locale, Model model) {
		logger.info("[doLogin] " + "");

		User userdata = loginManager.createUserContext(SecurityContextHolder.getContext().getAuthentication().getName());
		loginContextSession.setUserLogin(userdata);
		
		model.addAttribute("loginContext", loginContextSession);
		
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String dologout(Locale locale, Model model, HttpServletRequest httpServletRequest) {
		logger.info("[dologout] " + "");
		
		SecurityContextHolder.getContext().setAuthentication(null);
		
		String messageText = "";

		model.addAttribute("collapseFlag", "collapse");
		model.addAttribute("messageText", messageText);
						
		return "login";		
	}
}
