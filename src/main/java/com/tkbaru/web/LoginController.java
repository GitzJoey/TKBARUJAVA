package com.tkbaru.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

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
	
	@Autowired
	CookieLocaleResolver localeResolver;
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loadLoginPage(Locale locale, 
								Model model, 
								@RequestParam(value="e", required=false) String errParam, 
								HttpServletRequest request,
								HttpServletResponse response) {
		logger.info("[loadLoginPage] " + "IP Address: " + request.getRemoteAddr() + "(" + request.getHeader("X-FORWARDED-FOR") + ") locale.getLanguage(): " + locale.getLanguage());

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
			} else if (errParam.equalsIgnoreCase("relogin")){
				messageText = "You are successfully logout. please login again.";
			} else if (errParam.equalsIgnoreCase("session")) {
				messageText = "Invalid Session";
			} else {
				
			}
			
			model.addAttribute("hideLogin", false);
			model.addAttribute("collapseFlag", "");
			model.addAttribute("messageText", messageText);
			
			return "login";			
		}

		List<String> metisMenuCookies = new ArrayList<String>(Arrays.asList(
			"metisMenuState_0", "metisMenuState_1", "metisMenuState_2", "metisMenuState_3", "metisMenuState_4", "metisMenuState_5", "metisMenuState_6", "metisMenuState_7", "metisMenuState_8", "metisMenuState_9" 
		));
		
		boolean localeCookieFound = false;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c:cookies) {
				if (c.getName().equals(localeResolver.getCookieName())) {
					localeCookieFound = true;
					logger.info("[loadLoginPage] " + "tkbaruLocaleCookie Value: " + c.getValue());
				}
				if (metisMenuCookies.contains(c.getName())) {
					//logger.info("[loadLoginPage] " + "metisMenuState: " + c.getName() + ", Value: " + c.getValue());
				}
			}
		}
		
		if (!localeCookieFound) {
			Cookie newC = new Cookie(localeResolver.getCookieName(), locale.getLanguage());
			newC.setPath("/");
			newC.setMaxAge(60 * 60 * 24); //24h
			newC.setSecure(false);
			response.addCookie(newC);
			logger.info("[loadLoginPage] " + " Creating tkbaruLocaleCookie");
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
	
		if (loginContextSession == null) loginContextSession = new LoginContext();
		loginContextSession.setUserLogin(userdata);
		
		model.addAttribute("loginContext", loginContextSession);
		
		return "redirect:/dashboard";
	}
	
	@RequestMapping(value = "/changepass.html", method = RequestMethod.GET)
	public String changePassword(Locale locale, Model model) {
		logger.info("[changePassword] " + "");

		String messageText = "";

		model.addAttribute("userForm", loginContextSession.getUserLogin());
		model.addAttribute("loginContext", loginContextSession);

		model.addAttribute("collapseFlag", "collapse");
		model.addAttribute("messageText", messageText);
		
		return "change_pass";
		
	}

	@RequestMapping(value = "/change_pass/save", method = RequestMethod.POST)
	public String savePassword(Locale locale, Model model, @ModelAttribute("userForm") User usr) {
		logger.info("[savePassword] " + "");

		loginContextSession.setUserLogin(loginManager.changePassword(usr.getUserId(), usr.getUserPassword()));
		
		String messageText = "";

		model.addAttribute("loginContext", loginContextSession);

		model.addAttribute("collapseFlag", "collapse");
		model.addAttribute("messageText", messageText);
		
		return "change_pass";
		
	}

}
