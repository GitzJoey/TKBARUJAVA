package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tkbaru.common.Constants;
import com.tkbaru.model.BankAccount;
import com.tkbaru.model.Customer;
import com.tkbaru.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerManager;
	
	public String customerPageLoad(Locale locale, Model model) {
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "customer";
	}
	
	@RequestMapping(value="/add.html", method = RequestMethod.GET)
	public String addCustomer(Locale locale, Model model) {
		logger.info("Landed in Customer Page! The client locale is {}.", locale);
		
		model.addAttribute("customerForm", new Customer());
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_CUSTOMER;
	}

	@RequestMapping(value="/save.html", method = RequestMethod.POST)
	public String saveCustomer(Locale locale, Model model, @ModelAttribute("customerForm") Customer cust) {
		logger.info("Landed in Customer Page! The client locale is {}.", locale);
		
		logger.info(cust.toString());
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/customer";
	}
}
