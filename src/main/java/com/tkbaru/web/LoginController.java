package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tkbaru.model.LoginContext;
import com.tkbaru.model.User;
import com.tkbaru.service.LoginService;
import com.tkbaru.service.UserService;

@Controller
@SessionAttributes("loginContext")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginManager;
	
	@Autowired
	UserService userManager;
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loadLoginPage(Locale locale, Model model) {
		logger.info("[loadLoginPage] " + "");

		String messageText = "";
		
		if (!loginManager.checkDB()) {
			messageText = "System Is Not Ready";
			
			model.addAttribute("hideLogin", true);
			model.addAttribute("collapseFlag", "");
			model.addAttribute("messageText", messageText);
			
			return "login";
		}

		model.addAttribute("hideLogin", false);
		model.addAttribute("collapseFlag", "collapse");
		model.addAttribute("messageText", messageText);
		
		return "login";
	}

	@RequestMapping(value = "/dologin.html", method = RequestMethod.POST)	
	public String dologin(@RequestParam("username") String userName, @RequestParam("password") String userPswd, Model model) {
		logger.info("[doLogin] " + "");
		
		boolean loginSuccess = loginManager.successLogin(userName); 

		if (!loginSuccess) {
			String messageText = "Better check yourself, you're not looking too good.";
		
			model.addAttribute("collapseFlag", "");
			model.addAttribute("messageText", messageText);
			
			return "login";
		} else {
			User userdata = loginManager.createUserContext(userName);
			
			LoginContext lc = new LoginContext();
			lc.setUserLogin(userdata);
			
			model.addAttribute("loginContext", lc);			
			return "redirect:/dashboard.html";
		}
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String dologout(Locale locale, Model model, SessionStatus sessionStatus) {
		logger.info("[dologout] " + "");
		
		String messageText = "";

		model.addAttribute("collapseFlag", "collapse");
		model.addAttribute("messageText", messageText);
		
		sessionStatus.setComplete();
				
		return "login";		
	}
}
