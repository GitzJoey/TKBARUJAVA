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
import com.tkbaru.model.Payment;
import com.tkbaru.model.Product;
import com.tkbaru.model.ProductUnit;
import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.Stocks;
import com.tkbaru.service.CustomerService;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.ProductService;
import com.tkbaru.service.SalesOrderService;
import com.tkbaru.service.SearchService;
import com.tkbaru.service.StocksService;

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
	
	@Autowired
	StocksService stocksManager;

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
		
		if (loginContextSession.getSoList().isEmpty()) {
			SalesOrder so = new SalesOrder();
			so.setSalesStatus("L016_D");
			so.setSalesCode(salesOrderManager.generateSalesCode());
			so.setSalesCreatedDate(new Date());
			so.setShippingDate(new Date());
			so.setStatusLookup(lookupManager.getLookupByKey("L016_D"));
			so.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			so.setCreatedDate(new Date());
			loginContextSession.getSoList().add(so);
		}
		
		Customer customer = null;
		
		for (SalesOrder soVar : loginContextSession.getSoList()) {
			try {
				customer = customerManager.getCustomerById(soVar.getCustomerId());
				soVar.setCustomerLookup(customer);
			} catch (Exception e) {
				
			}
			
			soVar.setStatusLookup(lookupManager.getLookupByKey(soVar.getSalesStatus()));
			soVar.setSoTypeLookup(lookupManager.getLookupByKey(soVar.getSalesType()));
			
			for (Items items : soVar.getItemsList()) {
				Product prod = productManager.getProductById(items.getProductId());
				items.setProductLookup(prod);
			}
		}
		
		List<Customer> customerList = new ArrayList<Customer>();
		if (customer != null) {
			customerList.add(customer);
		}
		
		model.addAttribute("customerList", customerList);
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value="/select/type/{soTypeValue}/{tabId}", method = RequestMethod.POST)
	public String salesSelectSoType(Locale locale, Model model, @PathVariable String soTypeValue, @PathVariable int tabId) {
		logger.info("[salesSelectSoType] " + "soTypeValue: " + soTypeValue + ", tabId: " + tabId);

		List<SalesOrder> soList = new ArrayList<SalesOrder>();
		
		if (loginContextSession.getSoList().isEmpty()) {
			SalesOrder so = new SalesOrder();
			so.setSalesType(soTypeValue);
			so.setSoTypeLookup(lookupManager.getLookupByKey(soTypeValue));
			so.setSalesStatus("L016_D");
			so.setStatusLookup(lookupManager.getLookupByKey("L016_D"));
			so.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			so.setCreatedDate(new Date());
			if(soTypeValue.equals("L015_WIN")){
				so.setCustomerId(1);
				so.setCustomerLookup(customerManager.getCustomerById(1));
			}
			soList.add(so);
			loginContextSession.setSoList(soList);
		} else {
			((SalesOrder)loginContextSession.getSoList().get(tabId)).setSalesType(soTypeValue);
			((SalesOrder)loginContextSession.getSoList().get(tabId)).setSoTypeLookup(lookupManager.getLookupByKey(soTypeValue));
			
			if(soTypeValue.equals("L015_WIN")){
				((SalesOrder)loginContextSession.getSoList().get(tabId)).setCustomerId(1);
				((SalesOrder)loginContextSession.getSoList().get(tabId)).setCustomerLookup(customerManager.getCustomerById(1));
			} else if (soTypeValue.equals("L015_S")) {
				((SalesOrder)loginContextSession.getSoList().get(tabId)).setCustomerId(0);
				((SalesOrder)loginContextSession.getSoList().get(tabId)).setCustomerLookup(null);				
			} else {
				
			}	
		}

		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));		
		if (soTypeValue.equals("L015_WIN")) {
			model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		} else {
			
		}
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}

	@RequestMapping(value="/search/cust/{searchQuery}", method = RequestMethod.POST)
	public String salesSearchCustomer(Locale locale, Model model, @PathVariable String searchQuery) {
		logger.info("[salesSearchCustomer] " + "searchQuery: " + searchQuery);
		
		List<Customer> custList = customerManager.searchCustomer(searchQuery);
		
		model.addAttribute("customerList", custList);
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute("searchQuery", searchQuery);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value="/select/cust/{customerid}/{tabId}", method = RequestMethod.POST)
	public String salesSelectCustomer(Locale locale, Model model, @PathVariable int customerid, @PathVariable int tabId) {
		logger.info("[salesSelectCustomer] " + "customerid: " + customerid);
		
		Customer customer = customerManager.getCustomerById(customerid);
		
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(customer);
				
		((SalesOrder)loginContextSession.getSoList().get(tabId)).setCustomerId(customerid);
		((SalesOrder)loginContextSession.getSoList().get(tabId)).setCustomerLookup(customerManager.getCustomerById(customerid));
		
		model.addAttribute("customerList", custList);
		List<SalesOrder> salesOrderList = salesOrderManager.getAwaitingPaymentSales(customerid);
		if(salesOrderList != null){
			loginContextSession.getSoList().addAll(salesOrderList);
		}
		
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute("stocksListDDL", productManager.getProductHasInStock());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value="/select/walkincust", method = RequestMethod.POST)
	public String salesSelectWalkInCustomer(Locale locale, Model model) {
		logger.info("[salesSelectWalkInCustomer] " + "");
		
		Customer customer = customerManager.getCustomerById(0);
		
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(customer);
				
		List<SalesOrder> soList = new ArrayList<SalesOrder>();
		
		SalesOrder so = new SalesOrder();
		so.setCustomerId(0);
		so.setCustomerLookup(customer);
		so.setSalesStatus("L016_D");
		so.setStatusLookup(lookupManager.getLookupByKey("L016_D"));
		so.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		so.setCreatedDate(new Date());
		soList.add(so);
		loginContextSession.setSoList(soList);
		
		model.addAttribute("customerList", custList);
		List<SalesOrder> salesOrderList = salesOrderManager.getAwaitingPaymentSales(0);
		if(salesOrderList != null){
			loginContextSession.getSoList().addAll(salesOrderList);
		}
		
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute("stocksListDDL", productManager.getProductHasInStock());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}	
	
	@RequestMapping(value = "/additems/{soTypeValue}/{customerId}/{tabId}/{stocksId}", method = RequestMethod.POST)
	public String soAddItems(Locale locale, 
								Model model, 
								@ModelAttribute("loginContext") LoginContext loginContext, 
								@PathVariable String soTypeValue,
								@PathVariable int customerId,
								@PathVariable int tabId, 
								@PathVariable int stocksId) {
		logger.info("[soAddItems] " + "soTypeValue: " +  soTypeValue + ", customerId: " + customerId + ", tabId: " + tabId + ", stocksId: " + stocksId);
		
		Stocks s = stocksManager.getStocksById(stocksId);
		
		Items item = new Items();
		item.setStocksId(stocksId);
		item.setStocksLookup(s);
		item.setProductId(s.getProductLookup().getProductId());
		Product product = productManager.getProductById(s.getProductLookup().getProductId());
		item.setProductLookup(product);
		item.setCreatedDate(new Date());
		item.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		
		for (ProductUnit productUnit:product.getProductUnit()) {
			if (productUnit.isBaseUnit()) {
				item.setBaseUnitCode(productUnit.getUnitCode());
			}
		}

		loginContext.getSoList().get(tabId).getItemsList().add(item);

		for (Items items : loginContext.getSoList().get(tabId).getItemsList()) {
			Product prod = productManager.getProductById(items.getProductId());
			items.setProductLookup(prod);
		}

		loginContextSession.setSoList(loginContext.getSoList());
		
		for (SalesOrder soVar:loginContextSession.getSoList()) {
			soVar.setCustomerLookup(customerManager.getCustomerById(customerId));
			soVar.setStatusLookup(lookupManager.getLookupByKey(soVar.getSalesStatus()));
			soVar.setSoTypeLookup(lookupManager.getLookupByKey(soVar.getSalesType()));
			
			for (Items items : soVar.getItemsList()) {
				Product prod = productManager.getProductById(items.getProductId());
				items.setProductLookup(prod);
			}
		}
		
		Customer customer = customerManager.getCustomerById(customerId);
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(customer);
		
		model.addAttribute("activeTab", tabId);
		model.addAttribute("customerList",customerList);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value = "/removeitems/{soTypeValue}/{customerId}/{tabId}/{productId}", method = RequestMethod.POST)
	public String poRemoveItemsMulti(Locale locale, 
										Model model, @ModelAttribute("loginContext") 
										LoginContext loginContext, 
										@PathVariable String soTypeValue, 
										@PathVariable int customerId, 
										@PathVariable int tabId, 
										@PathVariable int itemIdx) {
		logger.info("[poRemoveItemsMulti] " + "itemIdx: " + itemIdx);

		List<Items> iLNew = new ArrayList<Items>();

		for (int x = 0; x < loginContext.getSoList().get(tabId).getItemsList().size(); x++) {
			if (x == itemIdx) continue;
			iLNew.add(loginContext.getSoList().get(tabId).getItemsList().get(x));
		}

		loginContext.getSoList().get(tabId).setItemsList(iLNew);
		
		for (Items items : loginContext.getSoList().get(tabId).getItemsList()) {
			Product prod = productManager.getProductById(items.getProductId());
			items.setProductLookup(prod);
			Stocks stock = stocksManager.getStocksById(items.getStocksId());
			items.setStocksLookup(stock);
		}
		
		Customer customer = customerManager.getCustomerById(customerId);
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(customer);
		
		loginContextSession.getSoList().get(tabId).setItemsList(loginContext.getSoList().get(tabId).getItemsList());
		
		model.addAttribute("activeTab", tabId);
		model.addAttribute("customerList", customerList);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value = "/removeitems/{tabId}/{productId}", method = RequestMethod.POST)
	public String poRemoveItemsMulti(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, @PathVariable int tabId, @PathVariable int productId) {
		logger.info("[poRemoveItemsMulti] " + "varId: " + productId);

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
		}
		
		loginContextSession.getSoList().get(tabId).setItemsList(loginContext.getSoList().get(tabId).getItemsList());
		model.addAttribute("activeTab", tabId);
		
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value = "/cancel/{tabId}", method = RequestMethod.POST)
	public String soCancel(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, RedirectAttributes redirectAttributes, @PathVariable int tabId) {
		logger.info("[soCancel] " + "");

		if (!loginContext.getSoList().isEmpty()) {
			SalesOrder so = loginContext.getSoList().get(tabId);
			
			loginContext.getSoList().remove(so);
			List<SalesOrder> soList = new ArrayList<SalesOrder>();
			for (SalesOrder sos : loginContext.getSoList()) {
				sos.setStatusLookup(lookupManager.getLookupByKey(sos.getSalesStatus()));
				soList.add(sos);
			}

			loginContextSession.setSoList(soList);
		}

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);

		return Constants.JSPPAGE_DASHBOARD;
	}
	
	@RequestMapping(value="/save/{soTypeValue}/{customerId}/{tabId}", method = RequestMethod.POST)
	public String salesSave(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, @PathVariable String soTypeValue,@PathVariable int customerId, @PathVariable int tabId) {
		logger.info("[salesSave] " + "");
		
		loginContextSession.setSoList(loginContext.getSoList());
		SalesOrder so = loginContext.getSoList().get(tabId);
		so.setSalesStatus("L016_WD");
		so.setStatusLookup(lookupManager.getLookupByKey("L016_WD"));
		so.setCustomerLookup(customerManager.getCustomerById(customerId));
		
		List<Items> itemList = new ArrayList<Items>();
		for (Items items : loginContext.getSoList().get(tabId).getItemsList()) {
			Product prod = productManager.getProductById(items.getProductId());
			items.setProductLookup(prod);
			Stocks s = stocksManager.getStocksById(items.getStocksId());
			items.setStocksLookup(s);
			for (ProductUnit productUnit : prod.getProductUnit()) {
				if(productUnit.getUnitCode().equals(items.getUnitCode())){
					items.setToBaseValue(productUnit.getConversionValue());
					items.setToBaseQty(items.getProdQuantity() * productUnit.getConversionValue());
				}
			}
			itemList.add(items);
		}

		if (so.getSalesId() == 0) {
			so.setCreatedDate(new Date());
			salesOrderManager.addSalesOrder(so);
		} else {
			salesOrderManager.editSalesOrder(so);
		}
		
		for (SalesOrder soVar : loginContext.getSoList()) {
			soVar.setCustomerLookup(customerManager.getCustomerById(customerId));
			soVar.setStatusLookup(lookupManager.getLookupByKey(soVar.getSalesStatus()));
			soVar.setSoTypeLookup(lookupManager.getLookupByKey(soVar.getSalesType()));
			for (Items items : soVar.getItemsList()) {
				Product prod = productManager.getProductById(items.getProductId());
				items.setProductLookup(prod);
			}
		}
			
		Customer customer = customerManager.getCustomerById(customerId);
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(customer);
		
		loginContextSession.setSoList(loginContext.getSoList());
		loginContextSession.getSoList().get(tabId).setItemsList(itemList);
		
		model.addAttribute("activeTab", tabId);
		model.addAttribute("customerList",customerList);
		model.addAttribute("stocksListDDL",productManager.getAllProduct());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE,Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value="/revise", method = RequestMethod.GET)
	public String salesRevise(Locale locale, Model model) {
		logger.info("[salesRevise] " + "");
		
		List<SalesOrder> soList = salesOrderManager.getSalesOrderByStatus("L016_WD");
	
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
		so.setCustomerLookup(customerManager.getCustomerById(so.getCustomerId()));
		so.setStatusLookup(lookupManager.getLookupByKey(so.getSalesStatus()));
		so.setSoTypeLookup(lookupManager.getLookupByKey(so.getSalesType()));
		
		model.addAttribute("reviseSalesForm", so);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_REVISE;
	}
	
	@RequestMapping(value = "/additems/{varId}", method = RequestMethod.POST)
	public String reviseAddItems(Locale locale, Model model, @ModelAttribute("reviseSalesForm") SalesOrder reviseSalesForm, @PathVariable int varId) {
		logger.info("[reviseAddItems] " + "varId: " + varId);
		
		Items i = new Items();
		i.setProductId(varId);
		Product product = productManager.getProductById(varId);
		i.setProductLookup(product);
		i.setCreatedDate(new Date());
		i.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		reviseSalesForm.getItemsList().add(i);
		
		for (Items item : reviseSalesForm.getItemsList()) {
			item.setProductLookup(productManager.getProductById(item.getProductId()));
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCode()));
		}
		
		reviseSalesForm.setCustomerLookup(customerManager.getCustomerById(reviseSalesForm.getCustomerId()));
		reviseSalesForm.setStatusLookup(lookupManager.getLookupByKey(reviseSalesForm.getSalesStatus()));
		reviseSalesForm.setSoTypeLookup(lookupManager.getLookupByKey(reviseSalesForm.getSalesType()));

		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("reviseSalesForm", reviseSalesForm);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_REVISE;
	}
	
	@RequestMapping(value = "/removeitems/{varId}", method = RequestMethod.POST)
	public String soRemoveItems(Locale locale, Model model, @ModelAttribute("reviseSalesForm") SalesOrder reviseSalesForm, @PathVariable String varId) {
		logger.info("[soRemoveItems] " + "varId: " + varId);
		
		reviseSalesForm.setStatusLookup(lookupManager.getLookupByKey(reviseSalesForm.getSalesStatus()));
		reviseSalesForm.setCustomerLookup(customerManager.getCustomerById(reviseSalesForm.getCustomerId()));
		reviseSalesForm.setSoTypeLookup(lookupManager.getLookupByKey(reviseSalesForm.getSalesType()));
		
		List<Items> iLNew = new ArrayList<Items>();
		for (int x = 0; x < reviseSalesForm.getItemsList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			iLNew.add(reviseSalesForm.getItemsList().get(x));
		}

		reviseSalesForm.setItemsList(iLNew);
		for (Items item : reviseSalesForm.getItemsList()) {
			item.setProductLookup(productManager.getProductById(item.getProductId()));
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCode()));
		}
		
		model.addAttribute("reviseSalesForm", reviseSalesForm);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
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
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,Constants.PAGEMODE_EDIT);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);

		return "redirect:revise";
	}

	@RequestMapping(value="/payment", method = RequestMethod.GET)
	public String salesPayment(Locale locale, Model model) {
		logger.info("[salesPayment] " + "");

		List<SalesOrder> soList = salesOrderManager.getSalesOrderByStatus("L016_WP");
		
		model.addAttribute("paymentSalesList", soList);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_PAYMENT;
	}
	
	@RequestMapping(value="/giropayment/{selectedId}", method = RequestMethod.GET)
	public String giroPaymentSelectedSales(Locale locale, Model model, @PathVariable int selectedId) {
		logger.info("[giroPaymentSelectedSales] " + "selectedId: " + selectedId);
	
		SalesOrder so = salesOrderManager.getSalesOrderById(selectedId);
		Payment payment = new Payment();
		payment.setPaymentType("L017_GIRO");
		payment.setPaymentTypeLookup(lookupManager.getLookupByKey("L017_GIRO"));
		
		so.getPaymentList().add(payment);
		
		model.addAttribute("paymentSalesForm", so);
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
	
	@RequestMapping(value="/cashpayment/{selectedId}", method = RequestMethod.GET)
	public String cashPaymentSelectedSales(Locale locale, Model model, @PathVariable int selectedId) {
		logger.info("[cashPaymentSelectedSales] " + "selectedId: " + selectedId);
		
		SalesOrder so = salesOrderManager.getSalesOrderById(selectedId);
		Payment payment = new Payment();
		payment.setPaymentType("L017_CASH");
		payment.setPaymentTypeLookup(lookupManager.getLookupByKey("L017_CASH"));
		
		so.getPaymentList().add(payment);
		
		model.addAttribute("paymentSalesForm", so);
		model.addAttribute("stocksListDDL", productManager.getProductHasInStock());
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
	
	@RequestMapping(value="/transferpayment/{selectedId}", method = RequestMethod.GET)
	public String transferPaymentSelectedSales(Locale locale, Model model, @PathVariable int selectedId) {
		logger.info("[transferPaymentSelectedSales] " + "selectedId: " + selectedId);
		
		SalesOrder so = salesOrderManager.getSalesOrderById(selectedId);
		Payment payment = new Payment();
		payment.setPaymentType("L017_TRANSFER");
		payment.setPaymentTypeLookup(lookupManager.getLookupByKey("L017_TRANSFER"));
		
		so.getPaymentList().add(payment);
		
		model.addAttribute("paymentSalesForm", so);
		model.addAttribute("stocksListDDL", productManager.getProductHasInStock());
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
	
	@RequestMapping(value="/termpayment/{selectedId}", method = RequestMethod.GET)
	public String termPaymentSelectedSales(Locale locale, Model model, @PathVariable int selectedId) {
		logger.info("[termPaymentSelectedSales] " + "selectedId: " + selectedId);
		
		SalesOrder so = salesOrderManager.getSalesOrderById(selectedId);
		Payment payment = new Payment();
		payment.setPaymentType("L017_TERM");
		payment.setPaymentTypeLookup(lookupManager.getLookupByKey("L017_TERM"));
		
		so.getPaymentList().add(payment);
		
		model.addAttribute("paymentSalesForm", so);
		model.addAttribute("stocksListDDL", productManager.getProductHasInStock());
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
	
	@RequestMapping(value = "/addpayment/{paymentType}", method = RequestMethod.POST)
	public String soAddPayments(Locale locale, Model model, @ModelAttribute("paymentSalesForm") SalesOrder paymentSalesForm, @PathVariable String paymentType) {
		logger.info("[soAddPayments] ");
		
		paymentSalesForm.setStatusLookup(lookupManager.getLookupByKey(paymentSalesForm.getSalesStatus()));
		paymentSalesForm.setCustomerLookup(customerManager.getCustomerById(paymentSalesForm.getCustomerId()));
		paymentSalesForm.setSoTypeLookup(lookupManager.getLookupByKey(paymentSalesForm.getSalesType()));
		
		paymentSalesForm.setSoTypeLookup(lookupManager.getLookupByKey(paymentSalesForm.getSalesType()));
		for (Items item : paymentSalesForm.getItemsList()) {
			item.setProductLookup(productManager.getProductById(item.getProductId()));
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCode()));
		}

		Payment i = new Payment();
		i.setPaymentType(paymentType);
		i.setPaymentTypeLookup(lookupManager.getLookupByKey(paymentType));
		paymentSalesForm.getPaymentList().add(i);
		
		for (Payment payment : paymentSalesForm.getPaymentList()) {
			payment.setPaymentTypeLookup(lookupManager.getLookupByKey(payment.getPaymentType()));
			if (payment.getBankCode() != null) {
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
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_PAYMENT;
	}

	@RequestMapping(value = "/removepayment/{varId}", method = RequestMethod.POST)
	public String soRemovePayments(Locale locale, Model model, @ModelAttribute("paymentSalesForm") SalesOrder paymentSalesForm, @PathVariable String varId) {
		logger.info("[soRemovePayment] " + "varId: " + varId);
		
		paymentSalesForm.setStatusLookup(lookupManager.getLookupByKey(paymentSalesForm.getSalesStatus()));
		paymentSalesForm.setCustomerLookup(customerManager.getCustomerById(paymentSalesForm.getCustomerId()));
		paymentSalesForm.setSoTypeLookup(lookupManager.getLookupByKey(paymentSalesForm.getSalesType()));
		for (Items item : paymentSalesForm.getItemsList()) {
			item.setProductLookup(productManager.getProductById(item.getProductId()));
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCode()));
		}
		
		List<Payment> payLNew = new ArrayList<Payment>();
		for (int x = 0; x < paymentSalesForm.getPaymentList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			payLNew.add(paymentSalesForm.getPaymentList().get(x));
		}

		paymentSalesForm.setPaymentList(payLNew);
		for (Payment payment : paymentSalesForm.getPaymentList()) {
			payment.setPaymentTypeLookup(lookupManager.getLookupByKey(payment.getPaymentType()));
		}
		
		model.addAttribute("paymentSalesForm", paymentSalesForm);
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SO_PAYMENT;
	}
	
	@RequestMapping(value = "/savepayment", method = RequestMethod.POST)
	public String paymentSave(Locale locale, Model model, @ModelAttribute("paymentSalesForm") SalesOrder paymentSalesForm, RedirectAttributes redirectAttributes) {
		logger.info("[paymentSave] " + "");
		
		SalesOrder po = salesOrderManager.getSalesOrderById(paymentSalesForm.getSalesId());
		po.setUpdatedDate(new Date());
		po.setPaymentList(paymentSalesForm.getPaymentList());
	
		long totalHutang = 0;
		for (Items items : po.getItemsList()) {
			totalHutang += (items.getProdQuantity() * items.getProdPrice());
		}
		
		long totalPayment = 0;
		for (Payment payment : paymentSalesForm.getPaymentList()) {
			if (payment.getPaymentStatus() != null) {
				if (payment.getPaymentType().equals("L017_CASH") && payment.getPaymentStatus().equals("L018_C")) {
					totalPayment += payment.getTotalAmount(); 
				}
				if (payment.getPaymentType().equals("L017_GIRO") && payment.getPaymentStatus().equals("L021_FR")) {
					totalPayment += payment.getTotalAmount(); 
				}
				if (payment.getPaymentType().equals("L017_TRANSFER") && payment.getPaymentStatus().equals("L020_B")) {
					totalPayment += payment.getTotalAmount(); 
				}
				if (payment.getPaymentType().equals("L017_TERM") && payment.getPaymentStatus().equals("L019_C")) {
					totalPayment += payment.getTotalAmount(); 
				}
			}
		}
		
		if (totalHutang == totalPayment) {
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
	public String addSalesForm(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, @PathVariable int customerId) {
		logger.info("[addSalesForm] ");
		
		for (SalesOrder soForm : loginContext.getSoList()) {
			soForm.setCustomerLookup(customerManager.getCustomerById(customerId));
			soForm.setStatusLookup(lookupManager.getLookupByKey(soForm.getSalesStatus()));
			soForm.setSoTypeLookup(lookupManager.getLookupByKey(soForm.getSalesType()));
			for (Items items : soForm.getItemsList()) {
				Product prod = productManager.getProductById(items.getProductId());
				items.setProductLookup(prod);
			}
		}

		SalesOrder newSales = new SalesOrder();
		newSales.setCustomerId(customerId);
		newSales.setCustomerLookup(customerManager.getCustomerById(customerId));
		newSales.setSalesStatus("L016_D");
		newSales.setSalesCode(salesOrderManager.generateSalesCode());
		newSales.setSalesCreatedDate(new Date());
		newSales.setShippingDate(new Date());
		newSales.setStatusLookup(lookupManager.getLookupByKey("L016_D"));
		newSales.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		newSales.setCreatedDate(new Date());
		Customer customer = customerManager.getCustomerById(customerId);
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(customer);
		loginContextSession.getSoList().add(newSales);
		
		model.addAttribute("customerList", custList);
		model.addAttribute("stocksListDDL", productManager.getProductHasInStock());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
	
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value = "/addnewtab", method = RequestMethod.POST)
	public String soAddNewTab(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext) {
		logger.info("[soAddNewTab] " + "");
		
		for(SalesOrder soForm : loginContext.getSoList()){			
			soForm.setStatusLookup(lookupManager.getLookupByKey(soForm.getSalesStatus()));
			soForm.setSoTypeLookup(lookupManager.getLookupByKey(soForm.getSalesType()));
			for (Items items : soForm.getItemsList()) {
				Product prod = productManager.getProductById(items.getProductId());
				items.setProductLookup(prod);
			}
		}

		SalesOrder newSales = new SalesOrder();
	
		newSales.setSalesCode(salesOrderManager.generateSalesCode());
		newSales.setShippingDate(new Date());
		newSales.setSalesCreatedDate(new Date());
		newSales.setSalesStatus("L016_D");
		newSales.setStatusLookup(lookupManager.getLookupByKey("L016_D"));
		newSales.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		newSales.setCreatedDate(new Date());
		
		loginContextSession.getSoList().add(newSales);
	
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
	
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}

}