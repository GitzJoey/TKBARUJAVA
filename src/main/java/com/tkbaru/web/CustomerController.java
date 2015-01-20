package com.tkbaru.web;

import java.util.ArrayList;
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
import com.tkbaru.model.BankAccount;
import com.tkbaru.model.Customer;
import com.tkbaru.model.Person;
import com.tkbaru.model.PhoneList;
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
		logger.info("[addCustomer] " + "");
		
		model.addAttribute("customerForm", new Customer());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_CUSTOMER;
	}

	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String editCustomer(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[editCustomer] " + "selectedId: "  + selectedId);
		
		Customer selectedCustomer = customerManager.getCustomerById(selectedId);
		
		logger.info("[editCustomer] " + "selectedCustomer: " + selectedCustomer.toString());
		
		model.addAttribute("customerForm", selectedCustomer);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));
		
		model.addAttribute("activeTab", "custDataTab");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_CUSTOMER;
	}

	@RequestMapping(value = "/delete/{selectedId}", method = RequestMethod.GET)
	public String deleteCustomer(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[deleteCustomer] " + "selectedId: " + selectedId);
		
		customerManager.deleteCustomer(selectedId);
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/customer";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveCustomer(Locale locale, Model model, @ModelAttribute("customerForm") Customer cust, RedirectAttributes redirectAttributes) {	

		if (cust.getCustomerId() == 0) { 
			logger.info("[saveCustomer] " + "addCustomer: " + cust.toString());
			customerManager.addCustomer(cust);			
		} else {
			logger.info("[saveCustomer] " + "editCustomer: " + cust.toString());
			customerManager.editCustomer(cust); 			
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/customer";
	}
	
	@RequestMapping(value="/edit/{customerId}/bank/{bankAccButtonMode}/{bankAccId}", method = RequestMethod.POST)
	public String bankCRUD(Locale locale, Model model, 
								@ModelAttribute("customerForm") Customer cust, 
								@PathVariable Integer customerId,
								@PathVariable String bankAccButtonMode,
								@PathVariable Integer bankAccId) {
		logger.info("[bankCRUD] " + "customerId: " + customerId + ", bankAccButtonMode:" + bankAccButtonMode + ", bankAccId: " + bankAccId);

		if (bankAccButtonMode.toUpperCase().equals("ADDBANK")) {
			cust.getBankAccList().add(new BankAccount());	
		} else if (bankAccButtonMode.toUpperCase().equals("EDITBANK")) {
			int index = -1;
			
			for (int x = 0; x < cust.getBankAccList().size(); x++) {			
				if (cust.getBankAccList().get(x).getBankAccId() == bankAccId) { index = x; }			
			}
			
			if (index != -1) {
				model.addAttribute("editBankIdx", index);
			}			
		} else {
			List<BankAccount> newList = new ArrayList<BankAccount>();

			for (BankAccount ba:cust.getBankAccList()) {
				if (ba.getBankAccId() == bankAccId) continue;
				newList.add(ba);
			}

			cust.setBankAccList(newList);			
		}
		
		model.addAttribute("customerForm", cust);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));

		if (bankAccButtonMode.toUpperCase().equals("ADDBANK")) {
			model.addAttribute("activeTab", "bankAccTab");
			model.addAttribute("editBankIdx", cust.getBankAccList().size() - 1);
			model.addAttribute("bankAccButtonMode", "addbank");				
		} else if (bankAccButtonMode.toUpperCase().equals("EDITBANK")) {
			model.addAttribute("activeTab", "bankAccTab");
			model.addAttribute("bankAccButtonMode", "editbank");
		} else {
			model.addAttribute("activeTab", "bankAccTab");
		}
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_CUSTOMER;
	}

	@RequestMapping(value="/edit/{customerId}/bank/{bankAccButtonMode}/{bankAccId}/{confirmMode}", method = RequestMethod.POST)
	public String bankCRUDConfirmation(Locale locale, Model model, 
							@ModelAttribute("customerForm") Customer cust, 
							@PathVariable Integer customerId,
							@PathVariable String bankAccButtonMode,
							@PathVariable Integer bankAccId,
							@PathVariable String confirmMode) {
		logger.info("[bankCRUDConfirmation] " + "customerId: " + customerId + ", bankAccButtonMode: " + bankAccButtonMode + ", bankAccId:" + bankAccId + ", confirmMode:" + confirmMode);

		if (bankAccButtonMode.toUpperCase().equals("ADDBANK")) {
			if (confirmMode.toUpperCase().equals("DISCARD")) {
				List<BankAccount> newList = new ArrayList<BankAccount>();
				
				for (int x = 0; x < cust.getBankAccList().size(); x++) {
					if (x == (cust.getBankAccList().size() - 1)) continue;
					newList.add(cust.getBankAccList().get(x));
				}
	
				cust.setBankAccList(newList);
			}
		}
		
		model.addAttribute("customerForm", cust);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));
		
		model.addAttribute("activeTab", "bankAccTab");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_CUSTOMER;
	}
	
	@RequestMapping(value="/edit/{customerId}/person/{personButtonMode}/{personId}", method = RequestMethod.POST)
	public String personCRUD(Locale locale, Model model, 
								@ModelAttribute("customerForm") Customer cust, 
								@PathVariable Integer customerId,
								@PathVariable String personButtonMode,
								@PathVariable Integer personId) {
		logger.info("[personCRUD] " + "customerId: " + customerId + ", personButtonMode:" + personButtonMode + ", personId: " + personId);

		if (personButtonMode.toUpperCase().equals("ADDPERSON")) {
			cust.getPicList().add(new Person());	
		} else if (personButtonMode.toUpperCase().equals("EDITPERSON")) {
			int index = -1;
			
			for (int x = 0; x < cust.getPicList().size(); x++) {			
				if (cust.getPicList().get(x).getPersonId() == personId) { index = x; }			
			}
			
			if (index != -1) {
				model.addAttribute("editPersonIdx", index);
			}			
		} else {
			List<Person> newList = new ArrayList<Person>();

			for (Person p:cust.getPicList()) {
				if (p.getPersonId() == personId) continue;
				newList.add(p);
			}

			cust.setPicList(newList);			
		}
		
		model.addAttribute("customerForm", cust);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));

		model.addAttribute("activeTab", "picTab");
		
		if (personButtonMode.toUpperCase().equals("ADDPERSON")) {
			model.addAttribute("editPersonIdx", cust.getPicList().size() - 1);
			model.addAttribute("personButtonMode", "addperson");				
		} else if (personButtonMode.toUpperCase().equals("EDITBANK")) {
			model.addAttribute("personButtonMode", "editperson");
		} else {
			
		}
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_CUSTOMER;
	}
	
	@RequestMapping(value="/edit/{customerId}/person/{personButtonMode}/{personId}/{confirmMode}", method = RequestMethod.POST)
	public String personCRUDConfirmation(Locale locale, Model model, 
							@ModelAttribute("customerForm") Customer cust, 
							@PathVariable Integer customerId,
							@PathVariable String personButtonMode,
							@PathVariable Integer personId,
							@PathVariable String confirmMode) {
		logger.info("[personCRUDConfirmation] " + "customerId: " + customerId + ", personButtonMode: " + personButtonMode + ", personId:" + personId + ", confirmMode:" + confirmMode);

		if (personButtonMode.toUpperCase().equals("ADDPERSON")) {
			if (confirmMode.toUpperCase().equals("DISCARD")) {
				List<Person> newList = new ArrayList<Person>();
				
				for (int x = 0; x < cust.getPicList().size(); x++) {
					if (x == (cust.getPicList().size() - 1)) continue;
					newList.add(cust.getPicList().get(x));
				}
	
				cust.setPicList(newList);
			}
		}
		
		model.addAttribute("customerForm", cust);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));
		
		model.addAttribute("activeTab", "picTab");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_CUSTOMER;
	}	
	
	@RequestMapping(value="/edit/{customerId}/person/{personId}/{personIndex}/{phoneMode}/{phoneListId}/{phoneListIndex}", method = RequestMethod.POST)
	public String phoneCRUD(Locale locale, Model model, 
								@ModelAttribute("customerForm") Customer cust, 
								@PathVariable Integer customerId,
								@PathVariable Integer personId,
								@PathVariable Integer personIndex,
								@PathVariable String phoneMode,
								@PathVariable Integer phoneListId,
								@PathVariable Integer phoneListIndex) {
		logger.info("[phoneCRUD] " + "customerId: " + customerId + ", personId: " + personId + ", personIndex: " + personIndex +", phoneMode: " + phoneMode + ", phoneListId: " + phoneListId + ", phoneListIndex: " + phoneListIndex);

		if (phoneMode.toUpperCase().equals("ADDPHONE")) {
			for (int x = 0; x < cust.getPicList().size(); x++) {
				if (x == personIndex) {
					cust.getPicList().get(x).getPhoneList().add(new PhoneList());
				}
			}			
		} else {
			List<PhoneList> newList = new ArrayList<PhoneList>();
			
			for (Person p:cust.getPicList()) {
				if (p.getPersonId() != personId) continue;
				for (int x = 0; x < p.getPhoneList().size(); x++) {
					if (x == phoneListIndex) continue;
					newList.add(p.getPhoneList().get(x));
				}
				p.setPhoneList(newList);	
			}
		}
		
		model.addAttribute("customerForm", cust);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));

		model.addAttribute("activeTab", "picTab");
		
		if (phoneMode.toUpperCase().equals("ADDPHONE")) {
			model.addAttribute("editPersonIdx", personIndex);
			model.addAttribute("personButtonMode", "addperson");				
		} else if (phoneMode.toUpperCase().equals("DELETEPHONE")) {
			
		} else {
			
		}
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_CUSTOMER;
	}
}
