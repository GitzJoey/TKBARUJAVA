package com.tkbaru.web;

import java.util.List;
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

import com.tkbaru.common.Constants;
import com.tkbaru.model.User;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.RoleService;
import com.tkbaru.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userManager;
	
	@Autowired
	LookupService lookupManager;
	
	@Autowired
	RoleService roleManager;
	
	@RequestMapping(value = "/admin/user.html", method = RequestMethod.GET)
	public String userPageLoad(Locale locale, Model model) {
		logger.info("[userPageLoad] " + "");
		
		List<User> userdata = userManager.getAllUser();

		model.addAttribute("userList", userdata);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_USER;
	}

	@RequestMapping(value = "/admin/user/add.html", method = RequestMethod.GET)
	public String userAdd(Locale locale, Model model) {
		logger.info("[userAdd] " + "");
		
		model.addAttribute("userForm", new User());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("roleDDL", roleManager.getAllRole());
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_USER;
	}

	@RequestMapping(value = "/admin/user/edit/{selectedId}.html", method = RequestMethod.GET)
	public String userEdit(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[userEdit] " + "");
		
		User selectedUser = userManager.getUserById(selectedId);
			
		model.addAttribute("userForm", selectedUser);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("roleDDL", roleManager.getAllRole());
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_USER;
	}

	@RequestMapping(value = "/admin/user/delete/{selectedId}.html", method = RequestMethod.GET)
	public String userDelete(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[userDelete] " + "");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_USER;
	}

	@RequestMapping(value = "/admin/user/save.html", method = RequestMethod.POST)
	public String userSave(Locale locale, Model model, @ModelAttribute("userForm") User usr) {
		
		if (usr.getUserId() == 0) {
			logger.info("[userSave] " + "addNewUser: " + usr.toString());
			userManager.addNewUser(usr);			
		} else {
			logger.info("[userSave] " + "editUser: " + usr.toString());
			userManager.editUser(usr);
		}
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_USER;
	}	
}
