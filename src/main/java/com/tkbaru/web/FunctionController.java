package com.tkbaru.web;

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
import com.tkbaru.model.Function;
import com.tkbaru.model.LoginContext;
import com.tkbaru.service.FunctionService;

@Controller
@RequestMapping("/admin/function")
public class FunctionController {
	private static final Logger logger = LoggerFactory.getLogger(FunctionController.class);
	
	@Autowired
	FunctionService functionManager;

	@Autowired
	private LoginContext loginContextSession;
		
	@RequestMapping(method = RequestMethod.GET)
	public String functionPageLoad(Locale locale, Model model) {
		logger.info("[functionPageLoad] " + "");

		List<Function> fList = functionManager.getAllFunctions();
		
		model.addAttribute("fList", fList);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_FUNCTION;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String functionAdd(Locale locale, Model model) {
		logger.info("[functionAdd] " + "");
		
		model.addAttribute("fForm", new Function());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_FUNCTION;
	}

	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String functionEdit(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[functionEdit] " + "");
		
		Function f = functionManager.getFunctionById(selectedId);
		
		model.addAttribute("fForm", f);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_FUNCTION;
	}

	@RequestMapping(value = "/delete/{selectedId}", method = RequestMethod.GET)
	public String functionDelete(Locale locale, Model model, @PathVariable Integer selectedId, RedirectAttributes redirectAttributes) {
		logger.info("[functionDelete] " + "");
		
		functionManager.deleteFunction(selectedId);
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/admin/function";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String functionSave(Locale locale, Model model, @ModelAttribute("fForm") Function func, RedirectAttributes redirectAttributes) {
		
		if (func.getFunctionId() == null) {
			func.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			func.setCreatedDate(new Date());
			logger.info("[functionSave] " + "addFunction: " + func.toString());
			functionManager.addFunction(func);
		} else {
			func.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
			func.setUpdatedDate(new Date());
			logger.info("[functionSave] " + "editFunction: " + func.toString());
			functionManager.editFunction(func);
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/admin/function";
	}

}
