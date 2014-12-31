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
import com.tkbaru.model.Function;
import com.tkbaru.service.FunctionService;

@Controller
public class FunctionController {
	private static final Logger logger = LoggerFactory.getLogger(FunctionController.class);
	
	@Autowired
	FunctionService functionManager;
		
	@RequestMapping(value = "/admin/function.html", method = RequestMethod.GET)
	public String functionPageLoad(Locale locale, Model model) {
		logger.info("[functionPageLoad] " + "");

		List<Function> fList = functionManager.getAllFunctions();
		
		model.addAttribute("functionList", fList);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_FUNCTION;
	}

	@RequestMapping(value = "/admin/function/add.html", method = RequestMethod.GET)
	public String functionAdd(Locale locale, Model model) {
		logger.info("[functionAdd] " + "");
		
		model.addAttribute("fForm", new Function());
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_FUNCTION;
	}

	@RequestMapping(value = "/admin/function/edit/{selectedId}.html", method = RequestMethod.GET)
	public String functionEdit(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[functionEdit] " + "");
		
		Function f = functionManager.getFunctionById(selectedId);
		
		model.addAttribute("fForm", f);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		logger.info(String.valueOf(selectedId));
		return Constants.JSPPAGE_FUNCTION;
	}

	@RequestMapping(value = "/admin/function/delete/{selectedId}.html", method = RequestMethod.GET)
	public String functionDelete(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[functionDelete] " + "");
		
		functionManager.deleteFunction(selectedId);
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/admin/function.html";
	}

	@RequestMapping(value = "/admin/function/save.html", method = RequestMethod.POST)
	public String functionSave(Locale locale, Model model, @ModelAttribute("fForm") Function func) {
		
		if (func.getFunctionId() == 0) {
			logger.info("[functionSave] " + "addFunction: " + func.toString());
			functionManager.addFunction(func);
		} else {
			logger.info("[functionSave] " + "editFunction: " + func.toString());
			functionManager.editFunction(func);
		}
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/admin/function.html";
	}

}
