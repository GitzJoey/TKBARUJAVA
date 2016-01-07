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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Customer;
import com.tkbaru.model.Items;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Payment;
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
			so.setSalesCode(salesOrderManager.generateSalesCode());
			so.setSalesCreatedDate(new Date());
			so.setShippingDate(new Date());
			so.setSalesStatusLookup(lookupManager.getLookupByKey("L016_D"));
			so.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			so.setCreatedDate(new Date());
			loginContextSession.getSoList().add(so);
		}
		
		for (SalesOrder soVar : loginContextSession.getSoList()) {			
			for (Items items : soVar.getItemsList()) {
				items.setProductEntity(productManager.getProductById(items.getProductEntity().getProductId()));
			}
		}
		
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value="/t/{tabId}/select/type/{soTypeValue}", method = RequestMethod.POST)
	public String salesSelectSoType(Locale locale, Model model, @PathVariable int tabId, @PathVariable String soTypeValue) {
		logger.info("[salesSelectSoType] " + "tabId: " + tabId + ", soTypeValue: " + soTypeValue);
		
		if (!loginContextSession.getSoList().isEmpty()) {
			((SalesOrder)loginContextSession.getSoList().get(tabId)).setSalesTypeLookup(lookupManager.getLookupByKey(soTypeValue));
			
			if(soTypeValue.equals("L015_WIN")){
				((SalesOrder)loginContextSession.getSoList().get(tabId)).setCustomerEntity(null);
			} else if (soTypeValue.equals("L015_S")) {

			} else {
				
			}
		} else {
			
		}

		model.addAttribute("activeTab", tabId);
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));		
		if (soTypeValue.equals("L015_WIN")) {
			model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		}
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SALESORDER;
	}

	@RequestMapping(value="/t/{tabId}/search/cust/{searchQuery}", method = RequestMethod.POST)
	public String salesSearchCustomer(Locale locale, Model model, @PathVariable int tabId, @PathVariable String searchQuery) {
		logger.info("[salesSearchCustomer] " + "tabId: " + tabId + ", searchQuery: " + searchQuery);
		
		List<Customer> custList = customerManager.searchCustomer(searchQuery);
		
		model.addAttribute("customerList", custList);
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute("searchQuery", searchQuery);
		
		model.addAttribute("activeTab", tabId);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value="/t/{tabId}/select/cust/{customerid}", method = RequestMethod.POST)
	public String salesSelectCustomer(Locale locale, Model model, @PathVariable int tabId, @PathVariable int customerid) {
		logger.info("[salesSelectCustomer] " + "tabId:" + tabId + ", customerid: " + customerid);
		
		loginContextSession.getSoList().get(tabId).setCustomerEntity(customerManager.getCustomerById(customerid));		
		
		model.addAttribute("activeTab", tabId);
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value = "/t/{tabId}/additems/{stocksId}", method = RequestMethod.POST)
	public String soAddItems(Locale locale, 
								Model model, 
								@ModelAttribute("loginContext") LoginContext loginContext, 
								@PathVariable int tabId,
								@PathVariable int stocksId) {
		logger.info("[soAddItems] " + "tabId: " + tabId + ", stocksId: " + stocksId);
		
		Stocks s = stocksManager.getStocksById(stocksId);

		Items item = new Items();		
		item.setStocksEntity(s);
		item.setProductEntity(productManager.getProductById(s.getProductEntity().getProductId()));
		item.setCreatedDate(new Date());
		item.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		if (!loginContext.getSoList().get(tabId).getSalesTypeLookup().getLookupKey().equals("L015_WIN")) {
			item.setProdPrice(s.getLatestPrice(loginContext.getSoList().get(tabId).getCustomerEntity().getPriceLevelEntity().getPriceLevelId()).getPrice().longValue());
		}
		
		for (ProductUnit productUnit:item.getProductEntity().getProductUnit()) {
			if (productUnit.getIsBaseUnit()) {
				item.setBaseUnitCodeLookup(lookupManager.getLookupByKey(productUnit.getUnitCodeLookup().getLookupKey()));
			}
		}

		loginContext.getSoList().get(tabId).getItemsList().add(item);
		
		loginContextSession.setSoList(loginContext.getSoList());

		model.addAttribute("activeTab", tabId);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value = "/t/{tabId}/removeitems/{itemIdx}", method = RequestMethod.POST)
	public String soRemoveItems(Locale locale, 
								Model model, 
								@ModelAttribute("loginContext") LoginContext loginContext, 
								@PathVariable int tabId, 
								@PathVariable int itemIdx) {
		logger.info("[soRemoveItems] " + "itemIdx: " + itemIdx);

		List<Items> iLNew = new ArrayList<Items>();
		
		for (int x = 0; x < loginContextSession.getSoList().get(tabId).getItemsList().size(); x++) {
			if (x == itemIdx) continue;
			iLNew.add(loginContextSession.getSoList().get(tabId).getItemsList().get(x));
		}		
		
		loginContextSession.getSoList().get(tabId).setItemsList(iLNew);
		
		model.addAttribute("activeTab", tabId);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SALESORDER;
	}
		
	@RequestMapping(value = "/t/{tabId}/cancel", method = RequestMethod.POST)
	public String soCancel(Locale locale, Model model, @PathVariable int tabId) {
		logger.info("[soCancel] " + "tabId: " + tabId);

		loginContextSession.getSoList().remove(tabId);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);

		return Constants.JSPPAGE_DASHBOARD;
	}
	
	@RequestMapping(value="/t/{tabId}/save", method = RequestMethod.POST)
	public String salesSave(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, @PathVariable int tabId) {
		logger.info("[salesSave] " + "tabId: " + tabId);
		
		loginContextSession.setSoList(loginContext.getSoList());
		SalesOrder so = loginContext.getSoList().get(tabId);
		so.setSalesStatusLookup(lookupManager.getLookupByKey("L016_WD"));

		if (so.getSalesTypeLookup().getLookupKey().equals("L015_WIN")) {
			so.setCustomerEntity(null);
		}
		
		so.setSalesTypeLookup(lookupManager.getLookupByKey(so.getSalesTypeLookup().getLookupKey()));
		
		List<Items> itemList = new ArrayList<Items>();
		for (Items items : loginContext.getSoList().get(tabId).getItemsList()) {
			items.setProductEntity(productManager.getProductById(items.getProductEntity().getProductId()));
			items.setStocksEntity(stocksManager.getStocksById(items.getStocksEntity().getStocksId()));
			for (ProductUnit productUnit : items.getProductEntity().getProductUnit()) {
				if(productUnit.getUnitCodeLookup().getLookupKey().equals(items.getUnitCodeLookup().getLookupKey())){
					items.setToBaseValue(productUnit.getConversionValue());
					items.setToBaseQty(items.getProdQuantity() * productUnit.getConversionValue());
				}
			}
			items.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			items.setCreatedDate(new Date());
			itemList.add(items);
		}

		if (so.getSalesId() == null) {
			so.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			so.setCreatedDate(new Date());
			so.setSalesStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
			salesOrderManager.addSalesOrder(so);
		} else {
			so.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
			so.setUpdatedDate(new Date());
			so.setSalesStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
			salesOrderManager.editSalesOrder(so);
		}
		
		loginContextSession.setSoList(loginContext.getSoList());
		loginContextSession.getSoList().get(tabId).setItemsList(itemList);
		
		model.addAttribute("activeTab", tabId);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE,Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

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
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SO_REVISE;
	}

	@RequestMapping(value="/revise/{selectedId}", method = RequestMethod.GET)
	public String reviseSelectedSales(Locale locale, Model model, @PathVariable int selectedId) {
		logger.info("[reviseSelectedSales] " + "selectedId: " + selectedId);
		
		SalesOrder so = salesOrderManager.getSalesOrderById(selectedId);
		
		model.addAttribute("reviseSalesForm", so);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SO_REVISE;
	}
	
	@RequestMapping(value = "/revise/{salesId}/additems/{stocksId}", method = RequestMethod.POST)
	public String reviseAddItems(Locale locale, Model model, @ModelAttribute("reviseSalesForm") SalesOrder reviseSalesForm, @PathVariable int salesId, @PathVariable int stocksId) {
		logger.info("[reviseAddItems] " + "salesId: " + salesId + ", stocksId: " + stocksId);
		
		Items i = new Items();
		i.setProductEntity(stocksManager.getStocksById(stocksId).getProductEntity());
		i.setCreatedDate(new Date());
		i.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		reviseSalesForm.getItemsList().add(i);
		
		for (Items item : reviseSalesForm.getItemsList()) {
			if (item.getProductEntity() != null && item.getProductEntity().getProductId() != null) {
				item.setProductEntity(productManager.getProductById(item.getProductEntity().getProductId()));
			}
			if (item.getUnitCodeLookup() != null && item.getUnitCodeLookup().getLookupKey() != null) {
				item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCodeLookup().getLookupKey()));
			}
		}
		
		if (reviseSalesForm.getCustomerEntity().getCustomerId() != null) {
			reviseSalesForm.setCustomerEntity(customerManager.getCustomerById(reviseSalesForm.getCustomerEntity().getCustomerId()));

		}
		reviseSalesForm.setSalesStatusLookup(lookupManager.getLookupByKey(reviseSalesForm.getSalesStatusLookup().getLookupKey()));
		reviseSalesForm.setSalesTypeLookup(lookupManager.getLookupByKey(reviseSalesForm.getSalesTypeLookup().getLookupKey()));

		model.addAttribute("reviseSalesForm", reviseSalesForm);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SO_REVISE;
	}
	
	@RequestMapping(value = "/revise/{salesId}/removeitems/{iIdx}", method = RequestMethod.POST)
	public String soRemoveItems(Locale locale, Model model, @ModelAttribute("reviseSalesForm") SalesOrder reviseSalesForm, @PathVariable int salesId, @PathVariable int iIdx) {
		logger.info("[soRemoveItems] " + "salesId: " + salesId + ", iIdx: " + iIdx);
		
		reviseSalesForm.setSalesStatusLookup(lookupManager.getLookupByKey(reviseSalesForm.getSalesStatusLookup().getLookupKey()));
		reviseSalesForm.setCustomerEntity(customerManager.getCustomerById(reviseSalesForm.getCustomerEntity().getCustomerId()));
		reviseSalesForm.setSalesTypeLookup(lookupManager.getLookupByKey(reviseSalesForm.getSalesTypeLookup().getLookupKey()));
		
		List<Items> iLNew = new ArrayList<Items>();
		for (int x = 0; x < reviseSalesForm.getItemsList().size(); x++) {
			if (x == iIdx) continue;
			iLNew.add(reviseSalesForm.getItemsList().get(x));
		}

		reviseSalesForm.setItemsList(iLNew);
		for (Items item : reviseSalesForm.getItemsList()) {
			item.setProductEntity(productManager.getProductById(item.getProductEntity().getProductId()));
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCodeLookup().getLookupKey()));
		}
		
		model.addAttribute("reviseSalesForm", reviseSalesForm);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SO_REVISE;
	}
	
	@RequestMapping(value = "/revise/{salesId}/save", method = RequestMethod.POST)
	public String reviseSave(Locale locale, Model model, @ModelAttribute("reviseSalesForm") SalesOrder reviseSalesForm, RedirectAttributes redirectAttributes, @PathVariable int salesId) {
		logger.info("[reviseSave] " + "salesId: " + salesId);

		reviseSalesForm.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
		reviseSalesForm.setUpdatedDate(new Date());

		for (Items items : reviseSalesForm.getItemsList()) {
			items.setProductEntity(productManager.getProductById(items.getProductEntity().getProductId()));
			items.setStocksEntity(stocksManager.getStocksById(items.getStocksEntity().getStocksId()));
			for (ProductUnit productUnit : items.getProductEntity().getProductUnit()) {
				if(productUnit.getUnitCodeLookup().getLookupKey().equals(items.getUnitCodeLookup().getLookupKey())){
					items.setToBaseValue(productUnit.getConversionValue());
					items.setToBaseQty(items.getProdQuantity() * productUnit.getConversionValue());
				}
			}
			items.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
			items.setUpdatedDate(new Date());
		}

		if (reviseSalesForm.getCustomerEntity().getCustomerId() == null) {
			reviseSalesForm.setCustomerEntity(null);
		}
		
		salesOrderManager.editSalesOrder(reviseSalesForm);
		
		model.addAttribute("reviseSalesForm", reviseSalesForm);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,Constants.PAGEMODE_EDIT);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);

		return "redirect:/sales/revise";
	}

	@RequestMapping(value="/payment", method = RequestMethod.GET)
	public String salesPayment(Locale locale, Model model) {
		logger.info("[salesPayment] " + "");

		List<SalesOrder> soList = salesOrderManager.getSalesOrderByStatus("L016_WP");
		
		model.addAttribute("paymentSalesList", soList);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SO_PAYMENT;
	}
	
	@RequestMapping(value="/giropayment/{selectedId}", method = RequestMethod.GET)
	public String giroPaymentSelectedSales(Locale locale, Model model, @PathVariable int selectedId) {
		logger.info("[giroPaymentSelectedSales] " + "selectedId: " + selectedId);
	
		SalesOrder so = salesOrderManager.getSalesOrderById(selectedId);
		Payment payment = new Payment();
		payment.setPaymentTypeLookup(lookupManager.getLookupByKey("L017_GIRO"));
		
		so.getPaymentList().add(payment);
		
		model.addAttribute("paymentSalesForm", so);
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute("cashStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("giroStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SO_PAYMENT;
	}
	
	@RequestMapping(value="/cashpayment/{selectedId}", method = RequestMethod.GET)
	public String cashPaymentSelectedSales(Locale locale, Model model, @PathVariable int selectedId) {
		logger.info("[cashPaymentSelectedSales] " + "selectedId: " + selectedId);
		
		SalesOrder so = salesOrderManager.getSalesOrderById(selectedId);
		Payment payment = new Payment();
		payment.setPaymentTypeLookup(lookupManager.getLookupByKey("L017_CASH"));
		
		so.getPaymentList().add(payment);
		
		model.addAttribute("paymentSalesForm", so);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute("cashStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("giroStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SO_PAYMENT;
	}
	
	@RequestMapping(value="/transferpayment/{selectedId}", method = RequestMethod.GET)
	public String transferPaymentSelectedSales(Locale locale, Model model, @PathVariable int selectedId) {
		logger.info("[transferPaymentSelectedSales] " + "selectedId: " + selectedId);
		
		SalesOrder so = salesOrderManager.getSalesOrderById(selectedId);
		Payment payment = new Payment();
		payment.setPaymentTypeLookup(lookupManager.getLookupByKey("L017_TRANSFER"));
		
		so.getPaymentList().add(payment);
		
		model.addAttribute("paymentSalesForm", so);
		model.addAttribute("stocksListDDL", productManager.getProductHasInStock());
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute("cashStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("giroStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

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
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SO_PAYMENT;
	}
	
	@RequestMapping(value = "/addpayment/{paymentType}", method = RequestMethod.POST)
	public String soAddPayments(Locale locale, Model model, @ModelAttribute("paymentSalesForm") SalesOrder paymentSalesForm, @PathVariable String paymentType) {
		logger.info("[soAddPayments] ");
		
		paymentSalesForm.setSalesStatusLookup(lookupManager.getLookupByKey(paymentSalesForm.getSalesStatusLookup().getLookupKey()));
		paymentSalesForm.setCustomerEntity(customerManager.getCustomerById(paymentSalesForm.getCustomerEntity().getCustomerId()));
		paymentSalesForm.setSalesTypeLookup(lookupManager.getLookupByKey(paymentSalesForm.getSalesTypeLookup().getLookupKey()));
		
		paymentSalesForm.setSalesTypeLookup(lookupManager.getLookupByKey(paymentSalesForm.getSalesTypeLookup().getLookupKey()));
		for (Items item : paymentSalesForm.getItemsList()) {
			item.setProductEntity(productManager.getProductById(item.getProductEntity().getProductId()));
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCodeLookup().getLookupKey()));
		}

		Payment i = new Payment();
		i.setPaymentTypeLookup(lookupManager.getLookupByKey(paymentType));
		paymentSalesForm.getPaymentList().add(i);
		
		for (Payment payment : paymentSalesForm.getPaymentList()) {
			payment.setPaymentTypeLookup(lookupManager.getLookupByKey(payment.getPaymentTypeLookup().getLookupKey()));
			if (payment.getBankCodeLookup() != null) {
				payment.setBankCodeLookup(lookupManager.getLookupByKey(payment.getBankCodeLookup().getLookupKey()));
			}
		}
		
		paymentSalesForm.setPaymentList(paymentSalesForm.getPaymentList());
		
		model.addAttribute("paymentSalesForm", paymentSalesForm);
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute("cashStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("giroStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SO_PAYMENT;
	}

	@RequestMapping(value = "/removepayment/{varId}", method = RequestMethod.POST)
	public String soRemovePayments(Locale locale, Model model, @ModelAttribute("paymentSalesForm") SalesOrder paymentSalesForm, @PathVariable String varId) {
		logger.info("[soRemovePayment] " + "varId: " + varId);
		
		paymentSalesForm.setSalesStatusLookup(lookupManager.getLookupByKey(paymentSalesForm.getSalesStatusLookup().getLookupKey()));
		paymentSalesForm.setCustomerEntity(customerManager.getCustomerById(paymentSalesForm.getCustomerEntity().getCustomerId()));
		paymentSalesForm.setSalesTypeLookup(lookupManager.getLookupByKey(paymentSalesForm.getSalesTypeLookup().getLookupKey()));
		for (Items item : paymentSalesForm.getItemsList()) {
			item.setProductEntity(productManager.getProductById(item.getProductEntity().getProductId()));
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getBaseUnitCodeLookup().getLookupKey()));
		}
		
		List<Payment> payLNew = new ArrayList<Payment>();
		for (int x = 0; x < paymentSalesForm.getPaymentList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			payLNew.add(paymentSalesForm.getPaymentList().get(x));
		}

		paymentSalesForm.setPaymentList(payLNew);
		for (Payment payment : paymentSalesForm.getPaymentList()) {
			payment.setPaymentTypeLookup(lookupManager.getLookupByKey(payment.getPaymentTypeLookup().getLookupKey()));
		}
		
		model.addAttribute("paymentSalesForm", paymentSalesForm);
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

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
			if (payment.getPaymentStatusLookup() != null) {
				if (payment.getPaymentTypeLookup().getLookupKey().equals("L017_CASH") && payment.getPaymentStatusLookup().getLookupKey().equals("L018_C")) {
					totalPayment += payment.getTotalAmount(); 
				}
				if (payment.getPaymentTypeLookup().getLookupKey().equals("L017_GIRO") && payment.getPaymentStatusLookup().getLookupKey().equals("L021_FR")) {
					totalPayment += payment.getTotalAmount(); 
				}
				if (payment.getPaymentTypeLookup().getLookupKey().equals("L017_TRANSFER") && payment.getPaymentStatusLookup().getLookupKey().equals("L020_B")) {
					totalPayment += payment.getTotalAmount(); 
				}
			}
		}
		
		if (totalHutang == totalPayment) {
			paymentSalesForm.setSalesStatusLookup(lookupManager.getLookupByKey("L016_C"));
		}
		
		salesOrderManager.editSalesOrder(paymentSalesForm);
		
		model.addAttribute("cashStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("giroStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);
		
		return "redirect:payment";
	}
	
	@RequestMapping(value = "/addnewtab/{customerId}", method = RequestMethod.POST)
	public String addSalesForm(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, @PathVariable int customerId) {
		logger.info("[addSalesForm] ");
		
		for (SalesOrder soForm : loginContext.getSoList()) {
			soForm.setCustomerEntity(customerManager.getCustomerById(customerId));
			soForm.setSalesStatusLookup(lookupManager.getLookupByKey(soForm.getSalesStatusLookup().getLookupKey()));
			soForm.setSalesTypeLookup(lookupManager.getLookupByKey(soForm.getSalesTypeLookup().getLookupKey()));
			for (Items items : soForm.getItemsList()) {
				items.setProductEntity(productManager.getProductById(items.getProductEntity().getProductId()));
			}
		}

		SalesOrder newSales = new SalesOrder();
		newSales.setCustomerEntity(customerManager.getCustomerById(customerId));
		newSales.setSalesStatusLookup(lookupManager.getLookupByKey("L016_D"));
		newSales.setSalesCode(salesOrderManager.generateSalesCode());
		newSales.setSalesCreatedDate(new Date());
		newSales.setShippingDate(new Date());
		newSales.setSalesStatusLookup(lookupManager.getLookupByKey("L016_D"));
		newSales.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		newSales.setCreatedDate(new Date());
		Customer customer = customerManager.getCustomerById(customerId);
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(customer);
		loginContextSession.getSoList().add(newSales);
		
		model.addAttribute("customerList", custList);
		model.addAttribute("stocksListDDL", stocksManager.getAllStocks());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
	
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value = "/addnewtab", method = RequestMethod.POST)
	public String soAddNewTab(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext) {
		logger.info("[soAddNewTab] " + "");
		
		for(SalesOrder soForm : loginContext.getSoList()){			
			soForm.setSalesStatusLookup(lookupManager.getLookupByKey(soForm.getSalesStatusLookup().getLookupKey()));
			soForm.setSalesTypeLookup(lookupManager.getLookupByKey(soForm.getSalesTypeLookup().getLookupValue()));
			for (Items items : soForm.getItemsList()) {
				items.setProductEntity(productManager.getProductById(items.getProductEntity().getProductId()));
			}
		}

		SalesOrder newSales = new SalesOrder();
	
		newSales.setSalesCode(salesOrderManager.generateSalesCode());
		newSales.setShippingDate(new Date());
		newSales.setSalesCreatedDate(new Date());
		newSales.setSalesStatusLookup(lookupManager.getLookupByKey("L016_D"));
		newSales.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		newSales.setCreatedDate(new Date());
		
		loginContextSession.getSoList().add(newSales);
	
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
	
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_SALESORDER;
	}

	@RequestMapping(value = "/check/stocks", method = RequestMethod.GET)
	public @ResponseBody String checkStocks(@RequestParam("stocksId") int stocksId, @RequestParam("qty") int qty, @RequestParam("unit") String unit) {
		logger.info("[checkStocks] " + "stocksId: " + stocksId + ", qty: " + qty + ", unit: " + unit);
		
		return "true";
	}
}