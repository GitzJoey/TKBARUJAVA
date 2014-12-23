package com.tkbaru.web;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;

@Controller
public class ActionController {

	@RequestMapping(value = "/admin/action.html", method = RequestMethod.GET)
	public String actionPageLoad(Locale locale, Model model) {		
				
		model.addAttribute("actionList", "");
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_ACTION;
	}

}
