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
import com.tkbaru.model.RoleFunction;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.RoleService;

@Controller
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	RoleService roleManager;
	
	@Autowired
	LookupService lookupManager;
	
	@RequestMapping(value = "/admin/role.html", method = RequestMethod.GET)
	public String rolePageLoad(Locale locale, Model model) {

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_ROLE;
	}
	
	@RequestMapping(value = "/admin/role/add.html", method = RequestMethod.GET)
	public String userAdd(Locale locale, Model model) {
		
		model.addAttribute("roleForm", new RoleFunction());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("roleDDL", roleManager.getSummaryRoleList());
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_ROLE;
	}

	@RequestMapping(value = "/admin/role/edit/{selectedId}.html", method = RequestMethod.GET)
	public String userEdit(Locale locale, Model model, @PathVariable Integer selectedId) {
		
		RoleFunction selectedRole = roleManager.getRoleFunctionById(selectedId);
		
		model.addAttribute("roleForm", selectedRole);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("roleDDL", roleManager.getSummaryRoleList());
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_ROLE;
	}

	@RequestMapping(value = "/admin/role/delete/{selectedId}.html", method = RequestMethod.GET)
	public String userDelete(Locale locale, Model model, @PathVariable Integer selectedId) {
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_ROLE;
	}

	@RequestMapping(value = "/admin/role/save.html", method = RequestMethod.POST)
	public String userSave(Locale locale, Model model, @ModelAttribute("roleForm") RoleFunction role) {
		
		if (role.getRoleId() == 0) {
			roleManager.addNewRoleFunction(role);			
		} else {
			roleManager.editRoleFunction(role);
		}
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_ROLE;
	}	
}
