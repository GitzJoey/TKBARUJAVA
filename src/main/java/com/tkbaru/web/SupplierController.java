package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;

@Controller
public class SupplierController {
	private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);
	
	@RequestMapping(value = "/supplier/add.html", method = RequestMethod.GET)
	public String addSupplier(Locale locale, Model model) {
		
		return Constants.JSPPAGE_SUPPLIER;
	}
}
