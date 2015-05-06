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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Items;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Payment;
import com.tkbaru.model.Product;
import com.tkbaru.model.ProductUnit;
import com.tkbaru.model.PurchaseOrder;
import com.tkbaru.model.Supplier;
import com.tkbaru.service.BankService;
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

	@Autowired
	BankService bankManager;

	@Autowired
	private LoginContext loginContextSession;
	
	

	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(true);
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat,true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String poNew(Locale locale, Model model) {
		logger.info("[poNew] " + "");

		if (loginContextSession.getPoList().isEmpty()) {			
			PurchaseOrder po = new PurchaseOrder();

			po.setPoCode(poManager.generatePOCode());
			po.setPoStatus("L013_D");
			po.setStatusLookup(lookupManager.getLookupByKey("L013_D"));
			po.setPoCreatedDate(new Date());
			po.setShippingDate(new Date());
			po.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			po.setCreatedDate(new Date());
			
			loginContextSession.getPoList().add(po);
		}
		
		for(PurchaseOrder po : loginContextSession.getPoList()){
			for (Items items : po.getItemsList()) {
				Product prod = productManager.getProductById(items.getProductId());
				items.setProductLookup(prod);
			}
		}

		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/revise/{selectedId}", method = RequestMethod.GET)
	public String reviseForm(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[revise] " + "");

		PurchaseOrder selectedPo = poManager.getPurchaseOrderById(selectedId);

		model.addAttribute("reviseForm", selectedPo);
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_REVISE;
	}

	@RequestMapping(value = "/additems/{tabId}/{varId}", method = RequestMethod.POST)
	public String poAddItems(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, @PathVariable String tabId, @PathVariable String varId) {
		logger.info("[poAddItems] " + "varId: " + varId);

		Items item = new Items();
		item.setProductId(Integer.parseInt(varId));
		Product product = productManager.getProductById(Integer.parseInt(varId));
		item.setProductLookup(product);
		item.setCreatedDate(new Date());
		item.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		
		for(ProductUnit productUnit :product.getProductUnit()){
			if(productUnit.isBaseUnit() == true){
				item.setBaseUnitCode(productUnit.getUnitCode());
			}
		}
		
		loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList().add(item);
		
		for(PurchaseOrder po : loginContext.getPoList()){	
			po.setPoTypeLookup(lookupManager.getLookupByKey(po.getPoType()));
			po.setSupplierLookup(supplierManager.getSupplierById(po.getSupplierId()));
			po.setWarehouseLookup(warehouseManager.getWarehouseById(po.getWarehouseId()));
			po.setStatusLookup(lookupManager.getLookupByKey(po.getPoStatus()));
			
			for(Items items : po.getItemsList()){
				Product prod = productManager.getProductById(items.getProductId());
				items.setProductLookup(prod);
				
				
				
				
			}
		
		}
		

		loginContextSession.setPoList(loginContext.getPoList());

		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/additems/{varId}", method = RequestMethod.POST)
	public String reviseAddItems(Locale locale, Model model, @ModelAttribute("reviseForm") PurchaseOrder reviseForm, @PathVariable String varId) {
		logger.info("[poAddItems] " + "varId: " + varId);
		
		reviseForm.setPoTypeLookup(lookupManager.getLookupByKey(reviseForm.getPoType()));

		Items i = new Items();
		i.setProductId(Integer.parseInt(varId));
		
		Product product = productManager.getProductById(Integer.parseInt(varId));
		i.setProductLookup(product);
		i.setCreatedDate(new Date());
		i.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		
		for(ProductUnit productUnit : product.getProductUnit()){
			if(productUnit.isBaseUnit()){
			i.setBaseUnitCode(productUnit.getUnitCode());
			}
		}
		reviseForm.getItemsList().add(i);
		
		for (Items item : reviseForm.getItemsList()){
			item.setProductLookup(productManager.getProductById(item.getProductId()));
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCode()));
		}

		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		model.addAttribute("reviseForm", reviseForm);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_REVISE;
	}

	@RequestMapping(value = "/removeitems/{varId}", method = RequestMethod.POST)
	public String poRemoveItems(Locale locale, Model model,@ModelAttribute("reviseForm") PurchaseOrder reviseForm, @PathVariable String varId) {
		logger.info("[poRemoveItems] " + "varId: " + varId);
		
		reviseForm.setPoTypeLookup(lookupManager.getLookupByKey(reviseForm.getPoType()));
		reviseForm.setStatusLookup(lookupManager.getLookupByKey(reviseForm.getPoStatus()));

		List<Items> iLNew = new ArrayList<Items>();

		for (int x = 0; x < reviseForm.getItemsList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			iLNew.add(reviseForm.getItemsList().get(x));
		}

		reviseForm.setItemsList(iLNew);
		
		for(Items item : reviseForm.getItemsList()){
			item.setProductLookup(productManager.getProductById(item.getProductId()));
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCode()));
		}
		
		model.addAttribute("reviseForm", reviseForm);
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_REVISE;
	}

	@RequestMapping(value = "/removeitems/{tabId}/{varId}", method = RequestMethod.POST)
	public String poRemoveItemsMulti(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, @PathVariable String tabId, @PathVariable String varId) {
		logger.info("[poRemoveItems] " + "varId: " + varId);

		List<Items> iLNew = new ArrayList<Items>();

		for (int x = 0; x < loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			iLNew.add(loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList().get(x));
		}

		loginContext.getPoList().get(Integer.parseInt(tabId)).setItemsList(iLNew);

		for (Items items : loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList()) {
			Product prod = productManager.getProductById(items.getProductId());
			items.setProductLookup(prod);
		}
		
		loginContextSession.getPoList().get(Integer.parseInt(tabId)).setItemsList(loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList());

		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/addpoform", method = RequestMethod.GET)
	public String addPoForm(Locale locale, Model model) {
		logger.info("[poAddPoForm] ");

		PurchaseOrder newPo = new PurchaseOrder();
		newPo.setPoStatus("L013_D");
		newPo.setStatusLookup(lookupManager.getLookupByKey("L013_D"));
		newPo.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		newPo.setCreatedDate(new Date());

		loginContextSession.getPoList().add(newPo);

		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String poPayment(Locale locale, Model model) {
		logger.info("[poPayment] " + "");

		model.addAttribute("paymentList",poManager.getPurchaseOrderByStatus("L013_WP"));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/newpayment/{selectedPo}", method = RequestMethod.GET)
	public String poPaymentAdd(Locale locale, Model model, @PathVariable Integer selectedPo) {
		logger.info("[poNew] " + "");

		PurchaseOrder po = poManager.getPurchaseOrderById(selectedPo);
		
		model.addAttribute("poForm", po);
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

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/editpayment/{selectedPo}", method = RequestMethod.GET)
	public String poPaymentEdit(Locale locale, Model model, @PathVariable Integer selectedPo) {
		logger.info("[poPayment] " + "");

		PurchaseOrder po = poManager.getPurchaseOrderById(selectedPo);
		
		model.addAttribute("poForm", po);
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/revise", method = RequestMethod.GET)
	public String poRevise(Locale locale, Model model) {
		logger.info("[poRevise] " + "");
		
		model.addAttribute("reviseList",poManager.getPurchaseOrderByStatus("L013_WP"));
		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",warehouseManager.getAllWarehouse());
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_REVISE;
	}

	@RequestMapping(value = "/save/{varId}", method = RequestMethod.POST)
	public String poSave(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, RedirectAttributes redirectAttributes, @PathVariable String varId) {
		logger.info("[poSave] " + "varId: " + varId);

		loginContextSession.setPoList(loginContext.getPoList());
		
		PurchaseOrder po = loginContext.getPoList().get(Integer.parseInt(varId));
		po.setPoStatus("L013_WA");
		po.setStatusLookup(lookupManager.getLookupByKey("L013_WA"));
		
		List<Items> itemList = new ArrayList<Items>();
		for (Items items : loginContext.getPoList().get(Integer.parseInt(varId)).getItemsList()) {
			Product prod = productManager.getProductById(items.getProductId());
			items.setProductLookup(prod);
			
			
			for(ProductUnit productUnit : prod.getProductUnit()){
				if(productUnit.getUnitCode().equals(items.getUnitCode())){
				
				items.setToBaseValue(productUnit.getConversionValue());
				items.setToBaseQty(productUnit.getConversionValue()*items.getProdQuantity());
				}
			}
		
			
			itemList.add(items);
		}

		if (po.getPoId() == 0) {
			po.setCreatedDate(new Date());
			poManager.addPurchaseOrder(po);
		} else {
			poManager.editPurchaseOrder(po);
		}

		
		
		for(PurchaseOrder poVar : loginContext.getPoList()){
			poVar.setPoTypeLookup(lookupManager.getLookupByKey(poVar.getPoType()));
			poVar.setSupplierLookup(supplierManager.getSupplierById(poVar.getSupplierId()));
			poVar.setWarehouseLookup(warehouseManager.getWarehouseById(poVar.getWarehouseId()));
			poVar.setStatusLookup(lookupManager.getLookupByKey(poVar.getPoStatus()));
			
			for (Items items : poVar.getItemsList()) {
				Product prod = productManager.getProductById(items.getProductId());
				items.setProductLookup(prod);
				
				
			}
		}

		loginContextSession.setPoList(loginContext.getPoList());
		loginContextSession.getPoList().get(Integer.parseInt(varId)).setItemsList(itemList);

		model.addAttribute("productSelectionDDL",productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,Constants.PAGEMODE_ADD);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;

	}

	@RequestMapping(value = "/cancel/{tabId}", method = RequestMethod.POST)
	public String poCancel(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext, BindingResult result, RedirectAttributes redirectAttributes, @PathVariable String tabId) {
		logger.info("[poCancel] " + "");

		if (!loginContext.getPoList().isEmpty()) {
			PurchaseOrder po = loginContext.getPoList().get(Integer.parseInt(tabId));
			po.setPoStatus("L013_D");
			po.setStatusLookup(lookupManager.getLookupByKey("L013_D"));
			loginContext.getPoList().remove(po);

			List<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();
			for (PurchaseOrder pos : loginContext.getPoList()) {
				pos.setStatusLookup(lookupManager.getLookupByKey(pos.getPoStatus()));
				poList.add(pos);
			}

			loginContextSession.setPoList(poList);
		}

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);

		return Constants.JSPPAGE_DASHBOARD;
	}

	@RequestMapping(value = "/saverevise", method = RequestMethod.POST)
	public String reviseSave(Locale locale, Model model, @ModelAttribute("reviseForm") PurchaseOrder reviseForm, RedirectAttributes redirectAttributes) {
		logger.info("[reviseSave] " + "");

		reviseForm.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
		reviseForm.setUpdatedDate(new Date());
		
		List<Items> itemList = new ArrayList<Items>();
		for (Items items : reviseForm.getItemsList()) {
			Product prod = productManager.getProductById(items.getProductId());
			items.setProductLookup(prod);
			for(ProductUnit productUnit : prod.getProductUnit()){
				if(productUnit.getUnitCode().equals(items.getUnitCode())){
				
				items.setToBaseValue(productUnit.getConversionValue());
				items.setToBaseQty(productUnit.getConversionValue()*items.getProdQuantity());
				}
			}
			itemList.add(items);
		}
		
		poManager.editPurchaseOrder(reviseForm);

		model.addAttribute("reviseForm", reviseForm);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,Constants.PAGEMODE_EDIT);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);

		return "redirect:revise";
	}

	@RequestMapping(value = "/retrieve/supplier", method = RequestMethod.GET)
	public @ResponseBody
	String poRetrieveSupplier(@RequestParam("supplierId") String supplierId) {
		logger.info("[poRetrieveSupplier] " + "supplierId: " + supplierId);

		Supplier supp = supplierManager.getSupplierById(Integer.parseInt(supplierId));

		String htmlTag = "" + "<strong>" + supp.getSupplierName() + "</strong>" + "";

		return htmlTag;
	}

	@RequestMapping(value = "/addpayment/{paymentType}", method = RequestMethod.POST)
	public String poAddPayments(Locale locale, Model model, @ModelAttribute("poForm") PurchaseOrder poForm, @PathVariable String paymentType) {
		logger.info("[poAddPayments] ");
		
		poForm.setPoTypeLookup(lookupManager.getLookupByKey(poForm.getPoType()));
		poForm.setStatusLookup(lookupManager.getLookupByKey(poForm.getPoStatus()));
		
		for(Items item : poForm.getItemsList()){
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCode()));
		}

		Payment i = new Payment();
		i.setPaymentType(paymentType);
		i.setPaymentTypeLookup(lookupManager.getLookupByKey(paymentType));
		poForm.getPaymentList().add(i);
		
		for(Payment payment : poForm.getPaymentList()){
			payment.setPaymentTypeLookup(lookupManager.getLookupByKey(payment.getPaymentType()));
			if(payment.getBankCode()!=null){
				payment.setBankCodeLookup(lookupManager.getLookupByKey(payment.getBankCode()));
			}
		}
		
		poForm.setPaymentList(poForm.getPaymentList());
		
		model.addAttribute("poForm", poForm);
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

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/removepayment/{varId}", method = RequestMethod.POST)
	public String poRemovePayments(Locale locale, Model model, @ModelAttribute("poForm") PurchaseOrder poForm,@PathVariable String varId) {
		logger.info("[poRemovePayment] " + "varId: " + varId);
		
		poForm.setPoTypeLookup(lookupManager.getLookupByKey(poForm.getPoType()));
		
		List<Payment> payLNew = new ArrayList<Payment>();

		for (int x = 0; x < poForm.getPaymentList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			payLNew.add(poForm.getPaymentList().get(x));
		}

		poForm.setPaymentList(payLNew);
		
		for(Payment payment : poForm.getPaymentList()){
			payment.setPaymentTypeLookup(lookupManager.getLookupByKey(payment.getPaymentType()));
		}
		
		model.addAttribute("poForm", poForm);
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/savepayment", method = RequestMethod.POST)
	public String paymentSave(Locale locale, Model model,@ModelAttribute("poForm") PurchaseOrder poForm,RedirectAttributes redirectAttributes) {
		logger.info("[paymentSave] " + "");

		PurchaseOrder po = poManager.getPurchaseOrderById(poForm.getPoId());
		po.setUpdatedDate(new Date());
		po.setPaymentList(poForm.getPaymentList());
	
		long totalHutang = 0;
		
		for(Items items : po.getItemsList()){
			totalHutang += (items.getProdQuantity() * items.getProdPrice());
		}
		
		long totalPayment = 0;
		
		for(Payment payment : poForm.getPaymentList()) {
			if(payment.getPaymentStatus() != null) {
				if(payment.getPaymentType().equals("L017_CASH") && payment.getPaymentStatus().equals("L018_C")) {
					totalPayment += payment.getTotalAmount(); 
				}
				if(payment.getPaymentType().equals("L017_GIRO") && payment.getPaymentStatus().equals("L021_FR")) {
					totalPayment += payment.getTotalAmount(); 
				}
				if(payment.getPaymentType().equals("L017_TRANSFER") && payment.getPaymentStatus().equals("L020_B")) {
					totalPayment += payment.getTotalAmount(); 
				}
				if(payment.getPaymentType().equals("L017_TERM") && payment.getPaymentStatus().equals("L019_C")) {
					totalPayment += payment.getTotalAmount(); 
				}
			}	
		}
		
		if(totalHutang == totalPayment) {
			po.setPoStatus("L013_C");
		}
		
		poManager.editPurchaseOrder(po);

		model.addAttribute("cashStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("termStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TERM));
		model.addAttribute("giroStatusDDL",lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);
		return "redirect:payment";
	}

}
