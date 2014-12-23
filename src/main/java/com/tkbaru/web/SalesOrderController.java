package com.tkbaru.web;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;

@Controller
@RequestMapping(name="/po")
public class SalesOrderController {

	@RequestMapping(method = RequestMethod.GET)
	public String salesPageLoad(Locale locale, Model model) {
		
		return Constants.JSPPAGE_SALESORDER;
	}
	
}
