package com.tkbaru.web;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Person;
import com.tkbaru.model.Supplier;

@Controller
public class PersonController {
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@RequestMapping(value = "/{category}/person/add.html", method = RequestMethod.POST)
	public String addPersonSupplier(Locale locale, Model model, @PathVariable String category, @ModelAttribute("supplierForm") Supplier supplier) {
		logger.info("Add Person : " + supplier.toString());
		
		model.addAttribute("cat", category + "/add");
		model.addAttribute("personForm", new Person());
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "person";
	}
	
	@RequestMapping(value = "/{category}/person/edit.html", method = RequestMethod.GET)
	public String editPersonSupplier(Locale locale, Model model, @PathVariable String category) {
		logger.info("Edit Person");
		
		model.addAttribute("cat", category + "/edit");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "person";
	}
	
	@RequestMapping(value = "/{category}/{mode}/person/save.html", method = RequestMethod.POST)
	public String savePerson(Locale locale, Model model, @PathVariable String category, @PathVariable String mode, @ModelAttribute("personForm") Person person) {		
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		if (category.toUpperCase().equals("SUPPLIER")) {
			return "person";
		} else if (category.toUpperCase().equals("SUPPLIER")) {
			return "person";
		} else {
			return "person";
		}
	}
}
