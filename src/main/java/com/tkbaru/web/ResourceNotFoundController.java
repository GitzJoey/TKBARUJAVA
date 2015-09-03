package com.tkbaru.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.model.LoginContext;

@Controller
public class ResourceNotFoundController {

	@Autowired
	private LoginContext loginContextSession;

	@RequestMapping(value = "/not_found.html", method = RequestMethod.GET)
	public String notFoundPage(Locale locale, Model model) {		
		String messageText = "";
		
		model.addAttribute("loginContext", loginContextSession);

		model.addAttribute("collapseFlag", "collapse");
		model.addAttribute("messageText", messageText);
		
		return "e404";
	}
}
