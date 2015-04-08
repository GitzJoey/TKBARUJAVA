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
import com.tkbaru.model.Product;
import com.tkbaru.model.PurchaseOrder;
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
		
//		SalesOrder so = loginContext.getSoList().get(tabId);
		
//		so.setCustomerSearchResults(custList);
		
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
		
		

//		List<Customer> custList = salesOrderManager.searchCustomer(querySearch);
		
		Customer customer = customerManager.getCustomerById(customerid);
		
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(customer);
		
		
//		SalesOrder so = loginContext.getSoList().get(tabId);
		
//		so.setCustomerSearchResults(custList);
		
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
		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value="/save/{customerId}/{tabId}", method = RequestMethod.POST)
	public String salesSave(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext,@PathVariable int customerId, @PathVariable int tabId) {
		
		

		loginContextSession.setSoList(loginContext.getSoList());
		SalesOrder so = loginContext.getSoList().get(tabId);
		logger.info("[salesSave] " + "soId: "+so.getSalesId());
		so.setSalesStatus("L016_WA");
		so.setStatusLookup(lookupManager.getLookupByKey("L016_WA"));

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
		
		Customer customer = customerManager.getCustomerById(customerId);
		
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(customer);

		loginContextSession.setSoList(loginContext.getSoList());
		loginContextSession.getSoList().get(tabId).setItemsList(itemList);
		
		model.addAttribute("soForm", so);
		model.addAttribute("customerId", customerId);
		model.addAttribute("customerList", custList);
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

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
		
//		reviseSalesForm.setSalesTypeLookup(lookupManager.getLookupByKey(reviseSalesForm.getSalesType()));

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
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_PAYMENT;
	}
	
	@RequestMapping(value = "/addnewtab/{customerId}", method = RequestMethod.POST)
	public String addPoForm(Locale locale, Model model, @PathVariable int customerId) {
		logger.info("[soAddNewTab] ");

		SalesOrder newSales = new SalesOrder();
		newSales.setCustomerId(customerId);
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
