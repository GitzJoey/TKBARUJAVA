package com.tkbaru.web;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.PhoneList;
import com.tkbaru.model.User;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.RoleService;
import com.tkbaru.service.StoreService;
import com.tkbaru.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
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

	@RequestMapping(method = RequestMethod.GET)
	public String userPageLoad(Locale locale, Model model) {
		logger.info("[userPageLoad] " + "");
		
		List<User> userdata = userManager.getAllUser();

		model.addAttribute("userList", userdata);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_USER;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String userAdd(Locale locale, Model model) {
		logger.info("[userAdd] " + "");
		
		model.addAttribute("userForm", new User());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("roleDDL", roleManager.getAllRole());
		model.addAttribute("storeDDL", storeManager.getAllStore());
		model.addAttribute("userTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_USERTYPE));
		model.addAttribute("allowLoginDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_YESNOSELECTION));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_USER;
	}

	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String userEdit(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[userEdit] " + "selectedId: " + selectedId);
		
		User selectedUser = userManager.getUserById(selectedId);
			
		model.addAttribute("userForm", selectedUser);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));
		model.addAttribute("roleDDL", roleManager.getAllRole());
		model.addAttribute("storeDDL", storeManager.getAllStore());
		model.addAttribute("userTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_USERTYPE));
		model.addAttribute("allowLoginDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_YESNOSELECTION));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_USER;
	}

	@RequestMapping(value = "/view/{selectedId}", method = RequestMethod.GET)
	public String userView(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[userView] " + "selectedId: " + selectedId);
		
		User selectedUser = userManager.getUserById(selectedId);
			
		model.addAttribute("userForm", selectedUser);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));
		model.addAttribute("roleDDL", roleManager.getAllRole());
		model.addAttribute("storeDDL", storeManager.getAllStore());
		model.addAttribute("userTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_USERTYPE));
		model.addAttribute("allowLoginDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_YESNOSELECTION));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_VIEW);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_USER;
	}

	@RequestMapping(value = "/delete/{selectedId}", method = RequestMethod.GET)
	public String userDelete(Locale locale, Model model, @PathVariable Integer selectedId, RedirectAttributes redirectAttributes) {
		logger.info("[userDelete] " + "selectedId:" + selectedId);
		
		userManager.deleteUser(selectedId);
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/admin/user";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String userSave(Locale locale, Model model, @ModelAttribute("userForm") User usr, RedirectAttributes redirectAttributes) {
		
		if (usr.getUserId() == null) {
			usr.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			usr.setCreatedDate(new Date());
			logger.info("[userSave] " + "addNewUser: " + usr.toString());
			userManager.addNewUser(usr);		
		} else {
			usr.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
			usr.setUpdatedDate(new Date());
			logger.info("[userSave] " + "editUser: " + usr.toString());
			userManager.editUser(usr);
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/admin/user";
	}
	
	@RequestMapping(value = "/edit/{selectedId}/addphone", method = RequestMethod.POST)
	public String userAddPhone(Locale locale, Model model, @ModelAttribute("userForm") User usr, @PathVariable Integer selectedId) {
		logger.info("[userAddPhone] " + "");

		usr.getPersonEntity().getPhoneList().add(new PhoneList());
		
		model.addAttribute("userForm", usr);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));
		model.addAttribute("roleDDL", roleManager.getAllRole());
		model.addAttribute("storeDDL", storeManager.getAllStore());
		model.addAttribute("userTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_USERTYPE));
		model.addAttribute("allowLoginDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_YESNOSELECTION));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_USER;
	}

	@RequestMapping(value = "/edit/{selectedId}/removephone/{phoneIdx}", method = RequestMethod.POST)
	public String userRemovePhone(Locale locale, Model model, @ModelAttribute("userForm") User usr, @PathVariable Integer selectedId, @PathVariable Integer phoneIdx) {
		logger.info("[userRemovePhone] " + " userId:" + selectedId + ", phoneList Index:" + phoneIdx);
		
		List<PhoneList> newList = new ArrayList<PhoneList>();
		
		for (int x = 0; x < usr.getPersonEntity().getPhoneList().size(); x++) {
			if (x == phoneIdx) continue;
			newList.add(usr.getPersonEntity().getPhoneList().get(x));
		}
		
		usr.getPersonEntity().setPhoneList(newList);
		
		model.addAttribute("userForm", usr);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));
		model.addAttribute("roleDDL", roleManager.getAllRole());
		model.addAttribute("storeDDL", storeManager.getAllStore());
		model.addAttribute("userTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_USERTYPE));
		model.addAttribute("allowLoginDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_YESNOSELECTION));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_USER;
	}

}
