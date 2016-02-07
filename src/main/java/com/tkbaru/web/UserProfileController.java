package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.User;
import com.tkbaru.service.LoginService;
import com.tkbaru.service.UserService;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController {
	private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

	@Autowired
	UserService userManager;
	
	@Autowired
	LoginService loginManager;
	
	@Autowired
	private LoginContext loginContextSession;

	@RequestMapping(value = "/view/{selectedId}", method = RequestMethod.GET)
	public String userView(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[userView] " + "selectedId: " + selectedId);
		
		User selectedUser = userManager.getUserById(selectedId);
			
		model.addAttribute("userForm", selectedUser);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_VIEW);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_USER_PROFILE;
	}

	@RequestMapping(value = "/changepass", method = RequestMethod.GET)
	public String changePassword(Locale locale, Model model) {
		logger.info("[changePassword] " + "");

		String messageText = "";

		model.addAttribute("userForm", loginContextSession.getUserLogin());
		model.addAttribute("loginContext", loginContextSession);

		model.addAttribute("collapseFlag", "collapse");
		model.addAttribute("errorMessageText", messageText);
		
		return "change_pass";
	}

	@RequestMapping(value = "/changepass/save", method = RequestMethod.POST)
	public String saveNewPassword(Locale locale, Model model, @RequestParam("inputOldPassword") String oldPassword, @ModelAttribute("userForm") User usr, RedirectAttributes redirectAttributes) {
		logger.info("[saveNewPassword] " + "");

		String messageText = "";
		
		if (loginManager.checkPassword(usr.getUserName(), oldPassword)) {
			loginContextSession.setUserLogin(loginManager.changePassword(usr.getUserId(), usr.getUserPassword()));
		} else {
			messageText = "Failed.";
			model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_SHOW);
		}
		

		model.addAttribute("loginContext", loginContextSession);

		redirectAttributes.addFlashAttribute("collapseFlag", "collapse");
		redirectAttributes.addFlashAttribute("errorMessageText", messageText);
		
		return "redirect:/user/profile/view/" + usr.getUserId();
	}

}
