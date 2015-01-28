package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.PurchaseOrder;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {
	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String poNew(Locale locale, Model model) {
		logger.info("[poNew] " + "");
		
		model.addAttribute("poForm", new PurchaseOrder());

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}
	
	@RequestMapping(value="/payment", method = RequestMethod.GET)
	public String poPayment(Locale locale, Model model) {
		logger.info("[poPayment] " + "");

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value="/revise", method = RequestMethod.GET)
	public String poRevise(Locale locale, Model model) {
		logger.info("[poRevise] " + "");

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_REVISE;
	}	

	@RequestMapping(value="/save", method = RequestMethod.GET)
	public String poSave(Locale locale, Model model) {
		logger.info("[poRevise] " + "");
		
		return Constants.JSPPAGE_PURCHASEORDER;
	}	
}
