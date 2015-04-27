package com.tkbaru.web;

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
import com.tkbaru.dao.PriceLevelDAO;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Price;
import com.tkbaru.model.PriceLevel;
import com.tkbaru.model.Product;

@Controller
@RequestMapping("/price")
public class PriceLeveLController {
	private static final Logger logger = LoggerFactory.getLogger(PriceLeveLController.class);

	@Autowired
	private LoginContext loginContextSession;
	
	@Autowired
	private PriceLevelDAO priceLevelManager;

	@RequestMapping(value="/pricelevel", method = RequestMethod.GET)
	public String priceLevelPageLoad(Locale locale, Model model) {
		logger.info("[priceLevelPageLoad] " + "");
		
		List<PriceLevel> priceLevelList = priceLevelManager.loadAll();
		model.addAttribute("priceLevelList", priceLevelList);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_PRICELEVEL;
	}
	
	@RequestMapping(value="/addpricelevel", method = RequestMethod.GET)
	public String addPriceLevel(Locale locale, Model model) {
		logger.info("[updatePrice] " + "");
		
		model.addAttribute("priceLevelForm", new PriceLevel());
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_PRICELEVEL;
	}
	
	@RequestMapping(value="/updatepricelevel/{productId}", method = RequestMethod.GET)
	public String updatePrice(Locale locale, Model model ,@PathVariable int productId) {
		logger.info("[updatePrice] " + "");
		
		PriceLevel priceLevel = priceLevelManager.load(productId);
		
		model.addAttribute("priceLevelForm", priceLevel);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_PRICELEVEL;
	}
	
	@RequestMapping(value="/savepricelevel", method = RequestMethod.POST)
	public String saveProduct(Locale locale, Model model, @ModelAttribute("priceLevelForm") PriceLevel priceLevelForm, RedirectAttributes redirectAttributes) {	

		
		
		if (priceLevelForm.getPriceLevelId() == 0) { 
			logger.info("[saveProduct] " + "addProduct: " + priceLevelForm.toString());
			priceLevelManager.addPriceLevel(priceLevelForm);
		} else {
			logger.info("[saveProduct] " + "editProduct: " + priceLevelForm.toString());
			priceLevelManager.editPriceLevel(priceLevelForm); 
		}
		
		List<PriceLevel> priceLevelList = priceLevelManager.loadAll();
		model.addAttribute("priceLevelList", priceLevelList);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/price/pricelevel";
	}

}
