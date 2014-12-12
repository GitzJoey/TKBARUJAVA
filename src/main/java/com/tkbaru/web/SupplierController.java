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
import org.springframework.web.bind.annotation.RequestParam;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Supplier;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.SupplierService;

@Controller
public class SupplierController {
	private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);
	
	@Autowired
	SupplierService supplierManager;
	
	@Autowired
	LookupService lookupManager;
	
	@RequestMapping(value = "/supplier/list.html", method = RequestMethod.GET)
	public String listSupplier(Locale locale, Model model) {

		logger.info("list");
		
		model.addAttribute("supplierList", supplierManager.getAllSupplier());
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SUPPLIER;
	}
	
	@RequestMapping(value = "/supplier/add.html", method = RequestMethod.GET)
	public String addSupplier(Locale locale, Model model) {

		model.addAttribute("supplierForm", new Supplier());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SUPPLIER;
	}

	@RequestMapping(value = "/supplier/edit/{selectedId}.html", method = RequestMethod.GET)
	public String editSupplier(Locale locale, Model model, @PathVariable Integer selectedId) {
		
		Supplier selectedSupplier = supplierManager.getSupplierById(selectedId);
		
		model.addAttribute("supplierForm", selectedSupplier);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_SUPPLIER;
	}

	@RequestMapping(value = "/supplier/delete/{selectedId}.html", method = RequestMethod.GET)
	public String deleteSupplier(Locale locale, Model model, @PathVariable Integer selectedId) {

		supplierManager.deleteSupplier(selectedId);
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/supplier/list.html";
	}

	@RequestMapping(value = "/supplier/save.html", method = RequestMethod.POST)
	public String userSave(Locale locale, Model model, @ModelAttribute("supplierForm") Supplier supp) {
		
		if (supp.getSupplierId() == 0) {
			supplierManager.addNewSupplier(supp);			
		} else {
			supplierManager.editSupplier(supp);
		}

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/supplier/list.html";
	}	
}
