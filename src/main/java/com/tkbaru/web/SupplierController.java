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
import com.tkbaru.model.Product;
import com.tkbaru.model.Supplier;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.SupplierService;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SupplierController {
	private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);
	
	@Autowired
	SupplierService supplierManager;
	
	@Autowired
	LookupService lookupManager;
	
	@RequestMapping(value = "/supplier", method = RequestMethod.GET)
	public String listSupplier(Locale locale, Model model) {

		logger.info("list");
		
		model.addAttribute("supplierList", supplierManager.getAllSupplier());
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SUPPLIER;
	}
	
	@RequestMapping(value = "/supplier/add", method = RequestMethod.GET)
	public String addSupplier(Locale locale, Model model) {

		model.addAttribute("supplierForm", new Supplier());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SUPPLIER;
	}

	@RequestMapping(value = "/supplier/edit/{selectedId}", method = RequestMethod.GET)
	public String editSupplier(Locale locale, Model model, @PathVariable Integer selectedId) {
		
		Supplier selectedSupplier = supplierManager.getSupplierById(selectedId);
		
		model.addAttribute("supplierForm", selectedSupplier);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_SUPPLIER;
	}

	@RequestMapping(value = "/supplier/delete/{selectedId}", method = RequestMethod.GET)
	public String deleteSupplier(Locale locale, Model model, @PathVariable Integer selectedId) {

		supplierManager.deleteSupplier(selectedId);
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/supplier";
	}

	@RequestMapping(value = "/supplier/save", method = RequestMethod.POST)
	public String userSave(Locale locale, Model model, @ModelAttribute("supplierForm") Supplier supp) {
		
		logger.info("" + supp.getBankAccList().size());
		logger.info(supp.getBankAccList().get(0).getBankAccDetail().getBankName());
		
		
		if (supp.getSupplierId() == 0) {
			supplierManager.addNewSupplier(supp);			
		} else {
			//supplierManager.editSupplier(supp);
		}

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/supplier";
	}
        
        @RequestMapping(value="/edit/{supplierId}/product/{productButtonMode}/{productId}", method = RequestMethod.POST)
	public String productCRUD(Locale locale, Model model, 
								@ModelAttribute("supplierForm") Supplier supp, 
								@PathVariable Integer customerId,
								@PathVariable String productButtonMode,
								@PathVariable Integer productId) {
		logger.info("[productCRUD] " + "productId: " + customerId + ", productButtonMode:" + productButtonMode + ", productId: " + productId);

		if (productButtonMode.toUpperCase().equals("ADDPRODUCT")) {
			supp.getProductList().add(new Product());	
		} else if (productButtonMode.toUpperCase().equals("EDITPRODUCT")) {
			int index = -1;
			
			for (int x = 0; x < supp.getBankAccList().size(); x++) {			
				if (supp.getProductList().get(x).getProductId() == productId) { index = x; }			
			}
			
			if (index != -1) {
				model.addAttribute("editProductId", index);
			}			
		} else {
			List<Product> newList = new ArrayList<Product>();

			for (Product p: supp.getProductList()) {
				if (p.getProductId() == productId) continue;
				newList.add(p);
			}

			supp.setProductList(newList);
		}
		
		model.addAttribute("supplierForm", supp);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));

		if (productButtonMode.toUpperCase().equals("ADDBANK")) {
			model.addAttribute("activeTab", "productTab");
			model.addAttribute("editProductIdx", supp.getProductList().size() - 1);
			model.addAttribute("productButtonMode", "addproduct");				
		} else if (productButtonMode.toUpperCase().equals("EDITBANK")) {
			model.addAttribute("activeTab", "productTab");
			model.addAttribute("productButtonMode", "editproduct");
		} else {
			model.addAttribute("activeTab", "productTab");
		}
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SUPPLIER;
	}
}