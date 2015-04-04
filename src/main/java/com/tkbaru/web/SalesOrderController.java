package com.tkbaru.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Customer;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.SalesOrder;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.SalesOrderService;
import com.tkbaru.service.SearchService;

@Controller
@RequestMapping("/sales")
public class SalesOrderController {
	private static final Logger logger = LoggerFactory.getLogger(SalesOrderController.class);

	@Autowired
	SalesOrderService salesOrderManager;
	
	@Autowired
	LookupService lookupManager;
	
	@Autowired
	SearchService searchManager;
	
	@Autowired
	private LoginContext loginContextSession;

	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(true);
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String salesNew(Locale locale, Model model) {
		logger.info("[salesNew] " + "");

		SalesOrder so = new SalesOrder();
		so.setSalesStatus("L016_D");
		
		model.addAttribute("soForm", so);
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}

	@RequestMapping(value="/search/cust/{searchQuery}", method = RequestMethod.POST)
	public String salesSearchCustomer(Locale locale, Model model, @ModelAttribute("soForm") SalesOrder so,  @PathVariable String searchQuery) {
		logger.info("[salesSearchCustomer] " + "searchQuery: " + searchQuery);

		List<Customer> custList = salesOrderManager.searchCustomer(searchQuery);
		
		so.setCustomerSearchResults(custList);
		
		model.addAttribute("soForm", so);
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}

	@RequestMapping(value="/revise", method = RequestMethod.GET)
	public String salesRevise(Locale locale, Model model) {
		logger.info("[salesRevise] " + "");
		
		List<SalesOrder> soList = salesOrderManager.getSalesOrderByStatus("");
		
		model.addAttribute("reviseSalesList", soList);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_REVISE;
	}

	@RequestMapping(value="/revise/{selectedId}", method = RequestMethod.GET)
	public String reviseSelectedSales(Locale locale, Model model, @PathVariable int selectedId) {
		logger.info("[reviseSelectedSales] " + "selectedId: " + selectedId);
		
		SalesOrder so = salesOrderManager.getSalesOrderById(selectedId);
		
		model.addAttribute("reviseSalesForm", so);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_REVISE;
	}

	@RequestMapping(value="/payment", method = RequestMethod.GET)
	public String salesPayment(Locale locale, Model model) {
		logger.info("[salesPayment] " + "");

		List<SalesOrder> soList = salesOrderManager.getSalesOrderByStatus("");
		
		model.addAttribute("paymentSalesList", soList);

		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_PAYMENT;
	}

	@RequestMapping(value="/payment/addpayment/{selectedSO}", method = RequestMethod.GET)
	public String salesAddPayment(Locale locale, Model model, @PathVariable Integer selectedSO) {
		logger.info("[salesAddPayment] " + "selectedSO: " + selectedSO);

		SalesOrder so = salesOrderManager.getSalesOrderById(selectedSO);
		
		model.addAttribute("paymentSalesForm", so);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_PAYMENT;
	}
}
