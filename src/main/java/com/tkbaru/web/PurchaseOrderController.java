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

import com.tkbaru.common.Constants;
import com.tkbaru.model.Items;
import com.tkbaru.model.PurchaseOrder;
import com.tkbaru.model.Supplier;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.ProductService;
import com.tkbaru.service.PurchaseOrderService;
import com.tkbaru.service.SupplierService;
import com.tkbaru.service.WarehouseService;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {
	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
	
	@Autowired
	PurchaseOrderService poManager;
	
	@Autowired
	ProductService productManager;
	
	@Autowired
	SupplierService supplierManager;
	
	@Autowired
	LookupService lookupManager;

	@Autowired
	WarehouseService warehouseManager;
	
	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(true);
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String poNew(Locale locale, Model model) {
		logger.info("[poNew] " + "");
		
		PurchaseOrder po = new PurchaseOrder();
		po.setPoStatus("L013_D");
		po.setStatusLookup(lookupManager.getLookupByKey("L013_D"));
		
		model.addAttribute("poForm", po);
		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		//model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value="/additems/{varId}", method = RequestMethod.POST)
	public String poAddItems(Locale locale, Model model, @ModelAttribute("poForm") PurchaseOrder po, @PathVariable String varId) {
		logger.info("[poAddItems] " + "varId: " + varId);
		
		Items i = new Items();
		
		i.setProductId(1);
		i.setProductLookup(productManager.getProductById(Integer.parseInt(varId)));
		
		po.getItemsList().add(i);
		
		model.addAttribute("poForm", po);
		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		//model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value="/removeitems/{varId}", method = RequestMethod.POST)
	public String poRemoveItems(Locale locale, Model model, @ModelAttribute("poForm") PurchaseOrder po, @PathVariable String varId) {
		logger.info("[poRemoveItems] " + "varId: " + varId);
		
		List<Items> iLNew = new ArrayList<Items>();
		
		for(int x=0; x<po.getItemsList().size(); x++) {
			if (x == Integer.parseInt(varId)) continue;
			iLNew.add(po.getItemsList().get(x));
		}
		
		po.setItemsList(iLNew);

		model.addAttribute("poForm", po);
		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		//model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		
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
	
	@RequestMapping(value="/retrieve/supplier", method = RequestMethod.GET)
	public @ResponseBody String poRetrieveSupplier(@RequestParam("supplierId") String supplierId) {
		logger.info("[poRetrieveSupplier] " + "supplierId: " + supplierId);
		
		Supplier supp = supplierManager.getSupplierById(Integer.parseInt(supplierId));
		
		String htmlTag = "" +
			"<strong>" + supp.getSupplierName() + "</strong>" +	
			"";
		
		return htmlTag;
	}	
	
}
