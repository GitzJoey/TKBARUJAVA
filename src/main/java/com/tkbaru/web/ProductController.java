package com.tkbaru.web;

import java.util.ArrayList;
import java.util.Date;
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
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Product;
import com.tkbaru.model.ProductUnit;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.ProductService;

@Controller
@RequestMapping("/master/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productManager;
	
	@Autowired
	LookupService lookupManager;

	@Autowired
	private LoginContext loginContextSession;

	@RequestMapping(method = RequestMethod.GET)
	public String productPageLoad(Locale locale, Model model) {
		logger.info("[productPageLoad] : " + "");
		
		model.addAttribute("productList", productManager.getAllProduct());
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PRODUCT;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addProduct(Locale locale, Model model) {
		logger.info("[addProduct] : " + "");
		
		model.addAttribute("productForm", new Product());
		model.addAttribute("productTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PRODUCT_TYPE));
		model.addAttribute("unitDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_UNIT));
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
	
		return Constants.JSPPAGE_PRODUCT;
	}
	
	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String editProduct(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[editProduct] " + "selectedId = " + selectedId);
			
		Product selectedProduct = productManager.getProductById(selectedId);
		
		model.addAttribute("productForm", selectedProduct);
		model.addAttribute("productTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PRODUCT_TYPE));
		model.addAttribute("unitDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_UNIT));
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PRODUCT;
	}
	
	@RequestMapping(value = "/delete/{selectedId}", method = RequestMethod.GET)
	public String deleteProduct(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[deleteProduct] : " + "selectedId = " + selectedId);

		productManager.deleteProduct(selectedId);
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/product";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveProduct(Locale locale, Model model, @ModelAttribute("productForm") Product prod, RedirectAttributes redirectAttributes) {	

		if (prod.getProductUnit() != null && prod.getProductUnit().size() > 0) {
			for(ProductUnit pu:prod.getProductUnit()) {
				if (pu.getProductEntity() == null) {
					pu.setProductEntity(prod);
				}
			}			
		}
		
		if (prod.getProductId() == null) {
			prod.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			prod.setCreatedDate(new Date());
			prod.setProductStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
			logger.info("[saveProduct] " + "addProduct: " + prod.toString());
			productManager.addProduct(prod); 
		} else {
			prod.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
			prod.setUpdatedDate(new Date());
			prod.setProductStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
			logger.info("[saveProduct] " + "editProduct: " + prod.toString());
			productManager.editProduct(prod); 
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/master/product";
	}

	@RequestMapping(value="/addunit/{selectedUnit}", method = RequestMethod.POST)
	public String addProductUnit(Locale locale, Model model, @ModelAttribute("productForm") Product prod, @PathVariable Integer selectedUnit) {	
		logger.info("[addProductUnit] " + "prod: " + prod.toString() + "selectedUnit: " + selectedUnit);
		
		if (prod.getProductUnit() == null) {
			List<ProductUnit> l = new ArrayList<ProductUnit>();
			l.add(new ProductUnit());
			
			prod.setProductUnit(l);
		} else {
			prod.getProductUnit().add(new ProductUnit());
		}
		
		model.addAttribute("productForm", prod);
		model.addAttribute("productTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PRODUCT_TYPE));
		model.addAttribute("unitDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_UNIT));
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		if (prod.getProductId() == null) { model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD); }
		else { model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT); }
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_PRODUCT;
	}
	
	@RequestMapping(value="/removeunit/{selectedUnitIndex}", method = RequestMethod.POST)
	public String removeProductUnit(Locale locale, Model model, @ModelAttribute("productForm") Product prod, @PathVariable Integer selectedUnitIndex) {	
		logger.info("[removeProductUnit] " + "selectedUnitIndex: " + selectedUnitIndex);

		List<ProductUnit> newPU = new ArrayList<ProductUnit>();
		
		for (int x=0; x<prod.getProductUnit().size(); x++) {
			if (x == selectedUnitIndex) continue;
			newPU.add(prod.getProductUnit().get(x));
		}
		
		prod.setProductUnit(newPU);
		
		model.addAttribute("productForm", prod);
		model.addAttribute("productTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PRODUCT_TYPE));
		model.addAttribute("unitDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_UNIT));
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		if (prod.getProductId() == null) { model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD); }
		else { model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT); }
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PRODUCT;
	}
}