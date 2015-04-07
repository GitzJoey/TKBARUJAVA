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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Customer;
import com.tkbaru.model.Items;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Product;
import com.tkbaru.model.PurchaseOrder;
import com.tkbaru.model.SalesOrder;
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

	@RequestMapping(value="/search/cust/{searchQuery}/{tabId}", method = RequestMethod.POST)
	public String salesSearchCustomer(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext,  @PathVariable String searchQuery,@PathVariable int tabId) {
		logger.info("[salesSearchCustomer] " + "searchQuery: " + searchQuery);
		
		

		List<Customer> custList = salesOrderManager.searchCustomer(searchQuery);
		
		SalesOrder so = loginContext.getSoList().get(tabId);
		
		so.setCustomerSearchResults(custList);
		
		model.addAttribute("soForm", so);
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_SALESORDER;
	}
	
	@RequestMapping(value="/save/{tabId}", method = RequestMethod.POST)
	public String salesSave(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext,@PathVariable int tabId) {
		SalesOrder so = loginContext.getSoList().get(tabId);
		logger.info("[salesSave] " + "soId: " + so.getSalesId());

		if (so.getSalesId() == 0) {
			so.setCreatedDate(new Date());
			salesOrderManager.addSalesOrder(so);
		} else {
			salesOrderManager.editSalesOrder(so);
		}
		
		model.addAttribute("soForm", so);
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
	public String reviseAddItems(Locale locale, Model model, @ModelAttribute("reviseSalesForm") SalesOrder reviseSalesForm, @PathVariable String varId) {
		logger.info("[poAddItems] " + "varId: " + varId);
		
//		reviseSalesForm.setSalesTypeLookup(lookupManager.getLookupByKey(reviseSalesForm.getSalesType()));

		Items i = new Items();
		i.setProductId(Integer.parseInt(varId));
		
		Product product = productManager.getProductById(Integer.parseInt(varId));
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
	
	@RequestMapping(value = "/addnewtab", method = RequestMethod.POST)
	public String addPoForm(Locale locale, Model model) {
		logger.info("[soAddNewTab] ");

		SalesOrder newSales = new SalesOrder();
		newSales.setSalesStatus("L016_D");
		newSales.setStatusLookup(lookupManager.getLookupByKey("L016_D"));
		newSales.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		newSales.setCreatedDate(new Date());

		loginContextSession.getSoList().add(newSales);
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("soTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_TYPE));
		model.addAttribute("soStatusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_SO_STATUS));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}
}
