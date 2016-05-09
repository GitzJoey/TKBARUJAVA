package com.tkbaru.web;

import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.User;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.RoleService;
import com.tkbaru.service.StoreService;
import com.tkbaru.service.UserService;

@Controller
@RequestMapping("/static/signup.html")
public class SignUpController {
	private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);
	
	@Autowired
	UserService userManager;
	
	@Autowired
	LookupService lookupManager;
	
	@Autowired
	RoleService roleManager;
	
	@Autowired
	StoreService storeManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String staticPageLoad(Locale locale, Model model) {
		logger.info("[userAdd] " + "");
		
		model.addAttribute("userForm", new User());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("roleDDL", roleManager.getAllRole());
		model.addAttribute("storeDDL", storeManager.getAllStore());
		model.addAttribute("userTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_USERTYPE));
		model.addAttribute("allowLoginDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_YESNOSELECTION));
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_SIGNUP;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String userSave(Locale locale, Model model, @ModelAttribute("userForm") User usr, RedirectAttributes redirectAttributes) {
		
		if (usr.getUserId() == null) {
			usr.setCreatedDate(new Date());
			usr.setRoleId(roleManager.getRoleByName("NONADMIN").getRoleId()); 
			usr.setStoreId(storeManager.getDefaultStore().getStoreId());
			usr.setUserStatus("L001_I");
			usr.setStatusLookup(lookupManager.getLookupByKey("L001_I"));			
			usr.setUserType("L024_CUS"); 
			usr.setAllowLogin("L003_YES"); 
			logger.info("[userSave] " + "addNewUser: " + usr.toString());
			
			userManager.addNewUser(usr);		
		} else {
	
		}
		
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/static/index.html";
	}

}
