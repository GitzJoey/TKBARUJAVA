package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.BankAccount;
import com.tkbaru.model.Person;
import com.tkbaru.model.PhoneList;
import com.tkbaru.model.Supplier;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.ProductService;
import com.tkbaru.service.SupplierService;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);
	
	@Autowired
	SupplierService supplierManager;
	
	@Autowired
	ProductService productManager;
	
	@Autowired
	LookupService lookupManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listSupplier(Locale locale, Model model) {
		logger.info("[listSupplier] " + "");
		
		model.addAttribute("supplierList", supplierManager.getAllSupplier());
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SUPPLIER;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addSupplier(Locale locale, Model model) {
		logger.info("[addSupplier] " + "");
		
		model.addAttribute("supplierForm", new Supplier());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("productList", productManager.getAllProduct());
		
		model.addAttribute("activeTab", "suppDataTab");

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SUPPLIER;
	}

	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String editSupplier(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[editSupplier] " + "selectedId: " + selectedId);
		
		Supplier selectedSupplier = supplierManager.getSupplierById(selectedId);
		
		model.addAttribute("supplierForm", selectedSupplier);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));
		model.addAttribute("productList", productManager.getAllProduct());
		
		model.addAttribute("activeTab", "suppDataTab");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_SUPPLIER;
	}

	@RequestMapping(value = "/delete/{selectedId}", method = RequestMethod.GET)
	public String deleteSupplier(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[deleteSupplier] " + "");
		
		supplierManager.deleteSupplier(selectedId);
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/supplier";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String supplierSave(Locale locale, Model model, @ModelAttribute("supplierForm") Supplier supp, HttpServletRequest request) {		
		
		supp.setProdList(productManager.getProductByIds(request.getParameter("selectedPrdList")));
		
		if (supp.getSupplierId() == 0) {
			logger.info("[supplierSave] " + "addNewSupplier: " + supp.toString());			
			//supplierManager.addNewSupplier(supp);			
		} else {
			logger.info("[supplierSave] " + "editSupplier: " + supp.toString());
			//supplierManager.editSupplier(supp);
		}

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/supplier";
	}        
	
	@RequestMapping(value="/edit/{supplierId}/bank/{bankAccButtonMode}/{bankAccId}", method = RequestMethod.POST)
	public String bankCRUD(Locale locale, Model model, 
								@ModelAttribute("supplierForm") Supplier supp, 
								@PathVariable Integer supplierId,
								@PathVariable String bankAccButtonMode,
								@PathVariable Integer bankAccId) {
		logger.info("[bankCRUD] " + "supplierId: " + supplierId + ", bankAccButtonMode:" + bankAccButtonMode + ", bankAccId: " + bankAccId);

		if (bankAccButtonMode.toUpperCase().equals("ADDBANK")) {
			supp.getBankAccList().add(new BankAccount());	
		} else if (bankAccButtonMode.toUpperCase().equals("EDITBANK")) {
			int index = -1;
			
			for (int x = 0; x < supp.getBankAccList().size(); x++) {			
				if (supp.getBankAccList().get(x).getBankAccId() == bankAccId) { index = x; }			
			}
			
			if (index != -1) {
				model.addAttribute("editBankIdx", index);
			}			
		} else {
			List<BankAccount> newList = new ArrayList<BankAccount>();

			for (BankAccount ba:supp.getBankAccList()) {
				if (ba.getBankAccId() == bankAccId) continue;
				newList.add(ba);
			}

			supp.setBankAccList(newList);			
		}
		
		model.addAttribute("supplierForm", supp);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));

		if (bankAccButtonMode.toUpperCase().equals("ADDBANK")) {
			model.addAttribute("activeTab", "bankAccTab");
			model.addAttribute("editBankIdx", supp.getBankAccList().size() - 1);
			model.addAttribute("bankAccButtonMode", "addbank");				
		} else if (bankAccButtonMode.toUpperCase().equals("EDITBANK")) {
			model.addAttribute("activeTab", "bankAccTab");
			model.addAttribute("bankAccButtonMode", "editbank");
		} else {
			model.addAttribute("activeTab", "bankAccTab");
		}
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SUPPLIER;
	}

	@RequestMapping(value="/edit/{supplierId}/bank/{bankAccButtonMode}/{bankAccId}/{confirmMode}", method = RequestMethod.POST)
	public String bankCRUDConfirmation(Locale locale, Model model, 
							@ModelAttribute("supplierForm") Supplier supp, 
							@PathVariable Integer supplierId,
							@PathVariable String bankAccButtonMode,
							@PathVariable Integer bankAccId,
							@PathVariable String confirmMode) {
		logger.info("[bankCRUDConfirmation] " + "supplierId: " + supplierId + ", bankAccButtonMode: " + bankAccButtonMode + ", bankAccId:" + bankAccId + ", confirmMode:" + confirmMode);

		if (bankAccButtonMode.toUpperCase().equals("ADDBANK")) {
			if (confirmMode.toUpperCase().equals("DISCARD")) {
				List<BankAccount> newList = new ArrayList<BankAccount>();
				
				for (int x = 0; x < supp.getBankAccList().size(); x++) {
					if (x == (supp.getBankAccList().size() - 1)) continue;
					newList.add(supp.getBankAccList().get(x));
				}
	
				supp.setBankAccList(newList);
			}
		}
		
		model.addAttribute("supplierForm", supp);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));
		
		model.addAttribute("activeTab", "bankAccTab");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SUPPLIER;
	}
	
	@RequestMapping(value="/edit/{supplierId}/person/{personButtonMode}/{personId}", method = RequestMethod.POST)
	public String personCRUD(Locale locale, Model model, 
								@ModelAttribute("supplierForm") Supplier supp, 
								@PathVariable Integer supplierId,
								@PathVariable String personButtonMode,
								@PathVariable Integer personId) {
		logger.info("[personCRUD] " + "supplierId: " + supplierId + ", personButtonMode:" + personButtonMode + ", personId: " + personId);

		if (personButtonMode.toUpperCase().equals("ADDPERSON")) {
			supp.getPicList().add(new Person());	
		} else if (personButtonMode.toUpperCase().equals("EDITPERSON")) {
			int index = -1;
			
			for (int x = 0; x < supp.getPicList().size(); x++) {			
				if (supp.getPicList().get(x).getPersonId() == personId) { index = x; }			
			}
			
			if (index != -1) {
				model.addAttribute("editPersonIdx", index);
			}			
		} else {
			List<Person> newList = new ArrayList<Person>();

			for (Person p:supp.getPicList()) {
				if (p.getPersonId() == personId) continue;
				newList.add(p);
			}

			supp.setPicList(newList);			
		}
		
		model.addAttribute("supplierForm", supp);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));

		model.addAttribute("activeTab", "picTab");
		
		if (personButtonMode.toUpperCase().equals("ADDPERSON")) {
			model.addAttribute("editPersonIdx", supp.getPicList().size() - 1);
			model.addAttribute("personButtonMode", "addperson");				
		} else if (personButtonMode.toUpperCase().equals("EDITBANK")) {
			model.addAttribute("personButtonMode", "editperson");
		} else {
			
		}
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SUPPLIER;
	}
	
	@RequestMapping(value="/edit/{supplierId}/person/{personButtonMode}/{personId}/{confirmMode}", method = RequestMethod.POST)
	public String personCRUDConfirmation(Locale locale, Model model, 
							@ModelAttribute("supplierForm") Supplier supp, 
							@PathVariable Integer supplierId,
							@PathVariable String personButtonMode,
							@PathVariable Integer personId,
							@PathVariable String confirmMode) {
		logger.info("[personCRUDConfirmation] " + "supplierId: " + supplierId + ", personButtonMode: " + personButtonMode + ", personId:" + personId + ", confirmMode:" + confirmMode);

		if (personButtonMode.toUpperCase().equals("ADDPERSON")) {
			if (confirmMode.toUpperCase().equals("DISCARD")) {
				List<Person> newList = new ArrayList<Person>();
				
				for (int x = 0; x < supp.getPicList().size(); x++) {
					if (x == (supp.getPicList().size() - 1)) continue;
					newList.add(supp.getPicList().get(x));
				}
	
				supp.setPicList(newList);
			}
		}
		
		model.addAttribute("supplierForm", supp);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("providerDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PHONE_PROVIDER));
		
		model.addAttribute("activeTab", "picTab");
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SUPPLIER;
	}	
	
	@RequestMapping(value="/edit/{supplierId}/person/{personId}/{personIndex}/{phoneMode}/{phoneListId}/{phoneListIndex}", method = RequestMethod.POST)
	public String phoneCRUD(Locale locale, Model model, 
								@ModelAttribute("supplierForm") Supplier supp, 
								@PathVariable Integer supplierId,
								@PathVariable Integer personId,
								@PathVariable Integer personIndex,
								@PathVariable String phoneMode,
								@PathVariable Integer phoneListId,
								@PathVariable Integer phoneListIndex) {
		logger.info("[phoneCRUD] " + "supplierId: " + supplierId + ", personId: " + personId + ", personIndex: " + personIndex +", phoneMode: " + phoneMode + ", phoneListId: " + phoneListId + ", phoneListIndex: " + phoneListIndex);

		if (phoneMode.toUpperCase().equals("ADDPHONE")) {
			for (int x = 0; x < supp.getPicList().size(); x++) {
				if (x == personIndex) {
					supp.getPicList().get(x).getPhoneList().add(new PhoneList());
				}
			}			
		} else {
			List<PhoneList> newList = new ArrayList<PhoneList>();
			
			for (Person p:supp.getPicList()) {
				if (p.getPersonId() != personId) continue;
				for (int x = 0; x < p.getPhoneList().size(); x++) {
					if (x == phoneListIndex) continue;
					newList.add(p.getPhoneList().get(x));
				}
				p.setPhoneList(newList);	
			}
		}
		
		model.addAttribute("supplierForm", supp);
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
		
		return Constants.JSPPAGE_SUPPLIER;
	}
}