package com.tkbaru.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Customer;
import com.tkbaru.model.Items;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Lookup;
import com.tkbaru.model.Payment;
import com.tkbaru.model.Product;
import com.tkbaru.model.SalesOrder;
import com.tkbaru.service.CustomerService;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.ProductService;
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
	
	@Autowired
	ProductService productManager;
	
	@Autowired
	CustomerService customerManager;

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
		
		if (loginContextSession.getPoList().isEmpty()) {
			
			SalesOrder so = new SalesOrder();
			so.setSalesStatus("L016_D");
			so.setStatusLookup(lookupManager.getLookupByKey("L016_D"));
			so.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			so.setCreatedDate(new Date());
			loginContextSession.getSoList().add(so);

		}
		
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}

	@RequestMapping(value="/search/cust/{searchQuery}", method = RequestMethod.POST)
	public String salesSearchCustomer(Locale locale, Model model,@PathVariable String searchQuery) {
		logger.info("[salesSearchCustomer] " + "searchQuery: " + searchQuery);
		List<Customer> custList = customerManager.searchCustomer(searchQuery);
		
		model.addAttribute("customerList", custList);
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value="/select/cust/{customerid}", method = RequestMethod.POST)
	public String salesSelectCustomer(Locale locale, Model model,@PathVariable int customerid) {
		logger.info("[salesSelectCustomer] " + "customerid: " + customerid);
		
		Customer customer = customerManager.getCustomerById(customerid);
		
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(customer);
				
		List<SalesOrder> soList = new ArrayList<SalesOrder>();
		
		SalesOrder so = new SalesOrder();
		so.setCustomerId(customerid);
		so.setCustomerLookup(customer);
		so.setSalesStatus("L016_D");
		so.setStatusLookup(lookupManager.getLookupByKey("L016_D"));
		so.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		so.setCreatedDate(new Date());
		soList.add(so);
		loginContextSession.setSoList(soList);
		
		model.addAttribute("customerList", custList);
		List<SalesOrder> salesOrderList = salesOrderManager.getAwaitingPaymentSales(customerid);
		if(salesOrderList != null){
			loginContextSession.getSoList().addAll(salesOrderList);
		}
		
		model.addAttribute("customerId", customerid);
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value = "/additems/{customerId}/{tabId}/{productId}", method = RequestMethod.POST)
	public String poAddItems(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, @PathVariable int customerId,@PathVariable int tabId, @PathVariable int productId) {
		logger.info("[soAddItems] " + "productId: " + productId);

		Items item = new Items();
		item.setProductId(productId);
		Product product = productManager.getProductById(productId);
		item.setProductLookup(product);
		item.setUnitCode(product.getBaseUnit());
		item.setUnitCodeLookup(lookupManager.getLookupByKey(product.getBaseUnit()));
		item.setCreatedDate(new Date());
		item.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		loginContext.getSoList().get(tabId).getItemsList().add(item);

		for (Items items : loginContext.getSoList().get(tabId).getItemsList()) {
			Product prod = productManager.getProductById(items.getProductId());
			items.setProductLookup(prod);
			items.setUnitCodeLookup(lookupManager.getLookupByKey(prod.getBaseUnit()));
		}

		loginContextSession.setSoList(loginContext.getSoList());
		
		for(SalesOrder soVar : loginContext.getSoList()){
			for (Items items : soVar.getItemsList()) {
				Product prod = productManager.getProductById(items.getProductId());
				items.setProductLookup(prod);
				items.setUnitCodeLookup(lookupManager.getLookupByKey(prod.getBaseUnit()));
			}
		}
		
		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value = "/removeitems/{customerId}/{tabId}/{productId}", method = RequestMethod.POST)
	public String poRemoveItemsMulti(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext,@PathVariable int customerId, @PathVariable int tabId, @PathVariable int productId) {
		logger.info("[soRemoveItems] " + "varId: " + productId);

		List<Items> iLNew = new ArrayList<Items>();

		for (int x = 0; x < loginContext.getSoList().get(tabId).getItemsList().size(); x++) {
			if (x == productId)
				continue;
			iLNew.add(loginContext.getSoList().get(tabId).getItemsList().get(x));
		}

		loginContext.getSoList().get(tabId).setItemsList(iLNew);
		
		for (Items items : loginContext.getSoList().get(tabId).getItemsList()) {
			Product prod = productManager.getProductById(items.getProductId());
			items.setProductLookup(prod);
			items.setUnitCodeLookup(lookupManager.getLookupByKey(prod.getBaseUnit()));
		}
		
		loginContextSession.getSoList().get(tabId).setItemsList(loginContext.getSoList().get(tabId).getItemsList());
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value = "/cancel/{tabId}", method = RequestMethod.POST)
	public String poCancel(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, BindingResult result, RedirectAttributes redirectAttributes, @PathVariable int tabId) {
		logger.info("[soCancel] " + "");

		if (!loginContext.getSoList().isEmpty()) {
			SalesOrder so = loginContext.getSoList().get(tabId);
			so.setSalesStatus("L016_D");
			so.setStatusLookup(lookupManager.getLookupByKey("L016_D"));
			loginContext.getSoList().remove(so);
			List<SalesOrder> soList = new ArrayList<SalesOrder>();
			for (SalesOrder sos : loginContext.getSoList()) {
				sos.setStatusLookup(lookupManager.getLookupByKey(sos.getSalesStatus()));
				soList.add(sos);
			}

			loginContextSession.setSoList(soList);
		}

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);

		return Constants.JSPPAGE_DASHBOARD;

	}
	
	@RequestMapping(value="/save/{customerId}/{tabId}", method = RequestMethod.POST)
	public String salesSave(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext,@PathVariable int customerId, @PathVariable int tabId) {
		logger.info("[salesSave] " + "");

		loginContextSession.setSoList(loginContext.getSoList());
		SalesOrder so = loginContext.getSoList().get(tabId);
		so.setSalesStatus("L016_WD");
		so.setStatusLookup(lookupManager.getLookupByKey("L016_WD"));
		so.setCustomerLookup(customerManager.getCustomerById(customerId));

		if (so.getSalesId() == 0) {
			so.setCreatedDate(new Date());
			salesOrderManager.addSalesOrder(so);
		} else {
			salesOrderManager.editSalesOrder(so);
		}

		List<Items> itemList = new ArrayList<Items>();
		for (Items items : loginContext.getSoList().get(tabId).getItemsList()) {
			Product prod = productManager.getProductById(items.getProductId());
			Lookup unitCodeLookup = lookupManager.getLookupByKey(prod.getBaseUnit());
			items.setProductLookup(prod);
			items.setUnitCodeLookup(unitCodeLookup);
			itemList.add(items);
		}
		
		for(SalesOrder soVar : loginContext.getSoList()){
			soVar.setCustomerLookup(customerManager.getCustomerById(customerId));
			soVar.setStatusLookup(lookupManager.getLookupByKey(soVar.getSalesStatus()));
			for (Items items : soVar.getItemsList()) {
				Product prod = productManager.getProductById(items.getProductId());
				items.setProductLookup(prod);
				items.setUnitCodeLookup(lookupManager.getLookupByKey(prod.getBaseUnit()));
			}
		}
			
		Customer customer = customerManager.getCustomerById(customerId);
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(customer);
		loginContextSession.setSoList(loginContext.getSoList());
		loginContextSession.getSoList().get(tabId).setItemsList(itemList);
		model.addAttribute("customerId",customerId);
		model.addAttribute("customerList",customerList);
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE,Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;

	}
	

	@RequestMapping(value="/revise", method = RequestMethod.GET)
	public String salesRevise(Locale locale, Model model) {
		logger.info("[salesRevise] " + "");
		List<SalesOrder> soList = salesOrderManager.getSalesOrderByStatus("L016_WP");
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
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_REVISE;
	}
	
	@RequestMapping(value = "/additems/{varId}", method = RequestMethod.POST)
	public String reviseAddItems(Locale locale, Model model, @ModelAttribute("reviseSalesForm") SalesOrder reviseSalesForm, @PathVariable int varId) {
		logger.info("[soAddItems] " + "varId: " + varId);
		Items i = new Items();
		i.setProductId(varId);
		Product product = productManager.getProductById(varId);
		i.setProductLookup(product);
		i.setUnitCode(product.getBaseUnit());
		i.setCreatedDate(new Date());
		i.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		reviseSalesForm.getItemsList().add(i);
		for (Items item : reviseSalesForm.getItemsList()){
			item.setProductLookup(productManager.getProductById(item.getProductId()));
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCode()));
		}

		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("reviseSalesForm", reviseSalesForm);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_REVISE;
	}
	
	@RequestMapping(value = "/removeitems/{varId}", method = RequestMethod.POST)
	public String poRemoveItems(Locale locale, Model model,@ModelAttribute("reviseSalesForm") SalesOrder reviseSalesForm, @PathVariable String varId) {
		logger.info("[poRemoveItems] " + "varId: " + varId);
		reviseSalesForm.setStatusLookup(lookupManager.getLookupByKey(reviseSalesForm.getSalesStatus()));
		List<Items> iLNew = new ArrayList<Items>();
		for (int x = 0; x < reviseSalesForm.getItemsList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			iLNew.add(reviseSalesForm.getItemsList().get(x));
		}

		reviseSalesForm.setItemsList(iLNew);
		for(Items item : reviseSalesForm.getItemsList()){
			item.setProductLookup(productManager.getProductById(item.getProductId()));
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCode()));
		}
		
		model.addAttribute("reviseSalesForm", reviseSalesForm);
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_REVISE;
	}

	
	@RequestMapping(value = "/saverevise", method = RequestMethod.POST)
	public String reviseSave(Locale locale, Model model, @ModelAttribute("reviseSalesForm") SalesOrder reviseSalesForm, RedirectAttributes redirectAttributes) {
		logger.info("[reviseSave] " + "");

		reviseSalesForm.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
		reviseSalesForm.setUpdatedDate(new Date());
		salesOrderManager.editSalesOrder(reviseSalesForm);
		model.addAttribute("reviseSalesForm", reviseSalesForm);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,Constants.PAGEMODE_EDIT);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);

		return "redirect:revise";
	}

	@RequestMapping(value="/payment", method = RequestMethod.GET)
	public String salesPayment(Locale locale, Model model) {
		logger.info("[salesPayment] " + "");
		List<SalesOrder> soList = salesOrderManager.getSalesOrderByStatus("L016_WP");
		model.addAttribute("reviseSalesList", soList);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_PAYMENT;
	}
	
	@RequestMapping(value="/newpayment/{selectedId}", method = RequestMethod.GET)
	public String paymentSelectedSales(Locale locale, Model model, @PathVariable int selectedId) {
		logger.info("[reviseSelectedSales] " + "selectedId: " + selectedId);
		SalesOrder so = salesOrderManager.getSalesOrderById(selectedId);
		model.addAttribute("paymentSalesForm", so);
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute("cashStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("termStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TERM));
		model.addAttribute("giroStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_PAYMENT;
	}
	
	@RequestMapping(value = "/addpayment/{paymentType}", method = RequestMethod.POST)
	public String poAddPayments(Locale locale, Model model, @ModelAttribute("paymentSalesForm") SalesOrder paymentSalesForm, @PathVariable String paymentType) {
		logger.info("[soAddPayments] ");
		paymentSalesForm.setStatusLookup(lookupManager.getLookupByKey(paymentSalesForm.getSalesStatus()));
		for(Items item : paymentSalesForm.getItemsList()){
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCode()));
		}

		Payment i = new Payment();
		i.setPaymentType(paymentType);
		i.setPaymentTypeLookup(lookupManager.getLookupByKey(paymentType));
		paymentSalesForm.getPaymentList().add(i);
		
		for(Payment payment : paymentSalesForm.getPaymentList()){
			payment.setPaymentTypeLookup(lookupManager.getLookupByKey(payment.getPaymentType()));
			if(payment.getBankCode()!=null){
				payment.setBankCodeLookup(lookupManager.getLookupByKey(payment.getBankCode()));
			}
		}
		
		paymentSalesForm.setPaymentList(paymentSalesForm.getPaymentList());
		model.addAttribute("paymentSalesForm", paymentSalesForm);
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute("cashStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("termStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TERM));
		model.addAttribute("giroStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_PAYMENT;
	}

	@RequestMapping(value = "/removepayment/{varId}", method = RequestMethod.POST)
	public String poRemovePayments(Locale locale, Model model, @ModelAttribute("paymentSalesForm") SalesOrder paymentSalesForm,@PathVariable String varId) {
		logger.info("[poRemovePayment] " + "varId: " + varId);
		List<Payment> payLNew = new ArrayList<Payment>();
		for (int x = 0; x < paymentSalesForm.getPaymentList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			payLNew.add(paymentSalesForm.getPaymentList().get(x));
		}

		paymentSalesForm.setPaymentList(payLNew);
		for(Payment payment : paymentSalesForm.getPaymentList()){
			payment.setPaymentTypeLookup(lookupManager.getLookupByKey(payment.getPaymentType()));
		}
		
		model.addAttribute("paymentSalesForm", paymentSalesForm);
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_PAYMENT;
	}
	
	@RequestMapping(value = "/savepayment", method = RequestMethod.POST)
	public String paymentSave(Locale locale, Model model,@ModelAttribute("paymentSalesForm") SalesOrder paymentSalesForm,RedirectAttributes redirectAttributes) {
		logger.info("[paymentSave] " + "");
		SalesOrder po = salesOrderManager.getSalesOrderById(paymentSalesForm.getSalesId());
		po.setUpdatedDate(new Date());
		po.setPaymentList(paymentSalesForm.getPaymentList());
	
		long totalHutang = 0;
		for(Items items : po.getItemsList()){
			totalHutang += (items.getProdQuantity() * items.getProdPrice());
		}
		
		long totalPayment = 0;
		for(Payment payment : paymentSalesForm.getPaymentList()){
			if(payment.getPaymentStatus() != null){
				if(payment.getPaymentType().equals("L017_CASH") && payment.getPaymentStatus().equals("L018_C")){
					totalPayment += payment.getTotalAmount(); 
				}
				if(payment.getPaymentType().equals("L017_GIRO") && payment.getPaymentStatus().equals("L021_FR")){
					totalPayment += payment.getTotalAmount(); 
				}
				if(payment.getPaymentType().equals("L017_TRANSFER") && payment.getPaymentStatus().equals("L020_B")){
					totalPayment += payment.getTotalAmount(); 
				}
				if(payment.getPaymentType().equals("L017_TERM") && payment.getPaymentStatus().equals("L019_C")){
					totalPayment += payment.getTotalAmount(); 
				}
			}
		}
		
		if(totalHutang == totalPayment){
			paymentSalesForm.setSalesStatus("L016_C");
		}
		
		salesOrderManager.editSalesOrder(paymentSalesForm);
		model.addAttribute("cashStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("termStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TERM));
		model.addAttribute("giroStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);
		return "redirect:payment";
	}
	
	
	
	@RequestMapping(value = "/addnewtab/{customerId}", method = RequestMethod.POST)
	public String addPoForm(Locale locale, Model model,@ModelAttribute("loginContext") LoginContext loginContext, @PathVariable int customerId) {
		logger.info("[soAddNewTab] ");
		
		for(int i =0; i<loginContext.getSoList().size();i++){
			for (Items items : loginContext.getSoList().get(i).getItemsList()) {
				Product prod = productManager.getProductById(items.getProductId());
				Lookup unitCodeLookup = lookupManager.getLookupByKey(prod.getBaseUnit());
				items.setProductLookup(prod);
				items.setUnitCodeLookup(unitCodeLookup);
			}
		}

		SalesOrder newSales = new SalesOrder();
		newSales.setCustomerId(customerId);
		newSales.setCustomerLookup(customerManager.getCustomerById(customerId));
		newSales.setSalesStatus("L016_D");
		newSales.setStatusLookup(lookupManager.getLookupByKey("L016_D"));
		newSales.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		newSales.setCreatedDate(new Date());
		Customer customer = customerManager.getCustomerById(customerId);
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(customer);
		loginContextSession.getSoList().add(newSales);
		model.addAttribute("customerId", customerId);
		model.addAttribute("customerList", custList);
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
}
