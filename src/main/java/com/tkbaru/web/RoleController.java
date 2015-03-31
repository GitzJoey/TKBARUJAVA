package com.tkbaru.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
import com.tkbaru.common.Converter;
import com.tkbaru.model.Function;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Role;
import com.tkbaru.service.FunctionService;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.RoleService;

@Controller
@RequestMapping("/admin/role")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	RoleService roleManager;

	@Autowired
	FunctionService functionManager;
	
	@Autowired
	LookupService lookupManager;
	
	@Autowired
	private LoginContext loginContextSession;

	@RequestMapping(method = RequestMethod.GET)
	public String rolePageLoad(Locale locale, Model model) {

		model.addAttribute("rList", roleManager.getAllRole());
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_ROLE;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addRole(Locale locale, Model model) {
		
		model.addAttribute("roleForm", new Role());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("functionListLeft", functionManager.getAllFunctions());
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_ROLE;
	}

	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String editRole(Locale locale, Model model, @PathVariable Integer selectedId) {
		
		Role selectedRole = roleManager.getRoleById(selectedId);
		
		model.addAttribute("roleForm", selectedRole);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		List<Function> unselectedList = new ArrayList<Function>();
		for (Function allF:functionManager.getAllFunctions()) {
			boolean found = false;
			for (Function selF:selectedRole.getFunctionList()) {
				if (allF.getFunctionId() == selF.getFunctionId()) { found = true; }
			}
			if (!found) {
				unselectedList.add(allF);
			}
		}
		
		model.addAttribute("functionListLeft", unselectedList);
		model.addAttribute("functionListRight", selectedRole.getFunctionList());
		model.addAttribute("selectedFunction", selectedRole.getAllFunctionIdInString());
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_ROLE;
	}

	@RequestMapping(value = "/delete/{selectedId}", method = RequestMethod.GET)
	public String deleteRole(Locale locale, Model model, @PathVariable Integer selectedId, RedirectAttributes redirectAttributes) {
		
		roleManager.deleteRole(selectedId);
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/admin/role";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveRole(Locale locale, Model model, @ModelAttribute("roleForm") Role role, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		role.setFunctionList(functionManager.getFunctionById(Converter.convertToIntegerList(request.getParameter("selectedFunc"))));
		
		if (role.getRoleId() == 0) {
			logger.info("[saveRole] " + "addRole: " + role.toString());
			roleManager.addRole(role);			
		} else {
			logger.info("[saveRole] " + "editRole: " + role.toString());
			roleManager.editRole(role);
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/admin/role";
	}	
}
