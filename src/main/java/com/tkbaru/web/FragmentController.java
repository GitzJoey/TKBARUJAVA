package com.tkbaru.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Customer;
import com.tkbaru.model.Person;
import com.tkbaru.model.Supplier;
import com.tkbaru.model.User;
import com.tkbaru.service.LookupService;

@Controller
public class FragmentController {
	private static final Logger logger = LoggerFactory.getLogger(FragmentController.class);

	@Autowired
	LookupService lookupManager; 
	
	@RequestMapping(value = "/fragment/addphone.html", method = RequestMethod.GET)
	public String addPhone(@RequestParam(value="count") int addphonecount, Model model) {		
		
		model.addAttribute("userForm", new User());
		model.addAttribute("addphonecount", addphonecount);
	 
		logger.info("return phone");
		
		return "fragment/phone";
	}

	@RequestMapping(value = "/fragment/addbank.html", method = RequestMethod.GET)
	public String addBank(@RequestParam(value="count") int elCount, @RequestParam(value="module") String elMod, Model model) {		
		logger.info("Adding bank via AJAX, counter: " + elCount + ", module: " + elMod);
		
		model.addAttribute("module", elMod);

		if (elMod.toUpperCase().equals("SUPPLIER")) {
			model.addAttribute("supplierForm", new Supplier());
		} else if (elMod.toUpperCase().equals("CUSTOMER")) {
			
		} else {
			
		}

		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("counter", elCount);
	 
		return "fragment/bank";
	}
	
	@RequestMapping(value = "/fragment/{module}/addbank/{counter}", method = RequestMethod.POST)
	public String addBankData(@RequestBody Customer c, @PathVariable("module") String elMod, @PathVariable("counter") int count, Model model) {
		
		model.addAttribute("customerForm", c);
		model.addAttribute("counter", count);
		model.addAttribute("module", elMod);
		
		logger.info("Creating Bank Account for module: " + elMod + ", counter: " + count);
		
		return "fragment/bank";
	}
	
	@RequestMapping(value = "/fragment/{module}/addperson", method = RequestMethod.POST)
	public @ResponseBody String addPerson(@RequestBody Person p, @PathVariable("module") String elMod, Model model) {
		model.addAttribute("module", elMod);
		
		return "fragment/person";
	}
}
