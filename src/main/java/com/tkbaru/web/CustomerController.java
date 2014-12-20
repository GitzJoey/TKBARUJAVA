package com.tkbaru.web;

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
import com.tkbaru.model.Customer;
import com.tkbaru.service.CustomerService;
import com.tkbaru.service.LookupService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerManager;
	
	@Autowired
	LookupService lookupManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String customerPageLoad(Locale locale, Model model) {
		logger.info("[customerPageLoad] " + "");
		
		model.addAttribute("customerList", customerManager.getAllCustomer());
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_CUSTOMER;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addCustomer(Locale locale, Model model) {
		logger.info("[addCustomer] : " + "");
		
		model.addAttribute("customerForm", new Customer());
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_CUSTOMER;
	}

	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String editCustomer(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[editCustomer] " + "selectedId = " + selectedId);
		
		Customer selectedCustomer = customerManager.getCustomerById(selectedId);
		
		model.addAttribute("customerForm", selectedCustomer);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_CUSTOMER;
	}

	@RequestMapping(value = "/delete/{selectedId", method = RequestMethod.GET)
	public String deleteCustomer(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[deleteCustomer] " + "selectedId = " + selectedId);
		
		customerManager.deleteCustomer(selectedId);
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/customer";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveCustomer(Locale locale, Model model, @ModelAttribute("customerForm") Customer cust) {
		logger.info("[saveCustomer] " + cust.toString());
		
		if (cust.getCustomerId() == 0) { customerManager.addCustomer(cust); }
		else { customerManager.editCustomer(cust); }
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/customer";
	}
}
