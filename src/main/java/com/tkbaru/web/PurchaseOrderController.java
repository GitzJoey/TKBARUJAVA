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
import com.tkbaru.model.PurchaseOrder;
import com.tkbaru.model.Supplier;
import com.tkbaru.service.BankService;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.ProductService;
import com.tkbaru.service.PurchaseOrderService;
import com.tkbaru.service.SupplierService;
import com.tkbaru.service.WarehouseService;
import com.tkbaru.validator.PurchaseOrderValidator;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {
	private static final Logger logger = LoggerFactory
			.getLogger(PurchaseOrderController.class);

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
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat,
				true);
		binder.registerCustomEditor(Date.class, orderDateEditor);

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String poNew(Locale locale, Model model) {
		logger.info("[poNew] " + "");

		if (loginContextSession.getPoList().isEmpty()) {
			PurchaseOrder po = new PurchaseOrder();
			po.setPoStatus("L013_D");
			po.setStatusLookup(lookupManager.getLookupByKey("L013_D"));
			po.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			po.setCreatedDate(new Date());
			loginContextSession.getPoList().add(po);

		}

		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager
				.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/revise/{selectedId}", method = RequestMethod.GET)
	public String reviseForm(Locale locale, Model model,
			@PathVariable Integer selectedId) {
		logger.info("[revise] " + "");

		PurchaseOrder selectedPo = poManager.getPurchaseOrderById(selectedId);

		model.addAttribute("reviseForm", selectedPo);
		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager
				.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_REVISE;
	}

	@RequestMapping(value = "/additems/{tabId}/{varId}", method = RequestMethod.POST)
	public String poAddItems(Locale locale, Model model,
			@ModelAttribute("loginContext") LoginContext loginContext,
			@PathVariable String tabId, @PathVariable String varId) {
		logger.info("[poAddItems] " + "varId: " + varId);

		Items item = new Items();

		item.setProductId(Integer.parseInt(varId));
		Product product = productManager
				.getProductById(Integer.parseInt(varId));
		item.setProductLookup(product);
		item.setUnitCode(product.getBaseUnit());
		item.setCreatedDate(new Date());
		item.setCreatedBy(loginContextSession.getUserLogin().getUserId());

		loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList()
				.add(item);
		
		List<Items> itemList = new ArrayList<Items>();
		for(Items items : loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList()){
			Product prod = productManager.getProductById(items.getProductId());
			items.setProductLookup(prod);
			itemList.add(items);
		}
		
		loginContextSession.setPoList(loginContext.getPoList());

		loginContextSession.getPoList().get(Integer.parseInt(tabId)).setItemsList(itemList);

		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager
				.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/additems/{varId}", method = RequestMethod.POST)
	public String reviseAddItems(Locale locale, Model model,
			@ModelAttribute("reviseForm") PurchaseOrder reviseForm,
			@PathVariable String varId) {
		logger.info("[poAddItems] " + "varId: " + varId);

		Items i = new Items();

		i.setProductId(Integer.parseInt(varId));
		Product product = productManager
				.getProductById(Integer.parseInt(varId));
		i.setProductLookup(product);
		i.setUnitCode(product.getBaseUnit());
		i.setCreatedDate(new Date());
		i.setCreatedBy(loginContextSession.getUserLogin().getUserId());

		reviseForm.getItemsList().add(i);

		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager
				.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute("reviseForm", reviseForm);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_REVISE;
	}

	@RequestMapping(value = "/removeitems/{varId}", method = RequestMethod.POST)
	public String poRemoveItems(Locale locale, Model model,
			@ModelAttribute("reviseForm") PurchaseOrder reviseForm,
			@PathVariable String varId) {
		logger.info("[poRemoveItems] " + "varId: " + varId);

		List<Items> iLNew = new ArrayList<Items>();

		for (int x = 0; x < reviseForm.getItemsList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			iLNew.add(reviseForm.getItemsList().get(x));
		}

		reviseForm.setItemsList(iLNew);

		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}
	
	@RequestMapping(value = "/removeitems/{tabId}/{varId}", method = RequestMethod.POST)
	public String poRemoveItemsMulti(Locale locale, Model model,
			@ModelAttribute("loginContext") LoginContext loginContext,
			@PathVariable String tabId,@PathVariable String varId) {
		logger.info("[poRemoveItems] " + "varId: " + varId);

		List<Items> iLNew = new ArrayList<Items>();

		for (int x = 0; x < loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			iLNew.add(loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList().get(x));
		}

		loginContext.getPoList().get(Integer.parseInt(tabId)).setItemsList(iLNew);
		
		loginContextSession.getPoList().get(Integer.parseInt(tabId)).setItemsList(loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList());

		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
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

		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager
				.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String poPayment(Locale locale, Model model) {
		logger.info("[poPayment] " + "");

		model.addAttribute("paymentList",
				poManager.getPurchaseOrderByStatus("L013_WP"));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/newpayment/{selectedPo}", method = RequestMethod.GET)
	public String poPaymentAdd(Locale locale, Model model,
			@PathVariable Integer selectedPo) {
		logger.info("[poNew] " + "");

		PurchaseOrder po = poManager.getPurchaseOrderById(selectedPo);

		model.addAttribute("poForm", po);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/editpayment/{selectedPo}", method = RequestMethod.GET)
	public String poPaymentEdit(Locale locale, Model model,
			@PathVariable Integer selectedPo) {
		logger.info("[poPayment] " + "");

		PurchaseOrder po = poManager.getPurchaseOrderById(selectedPo);

		model.addAttribute("poForm", po);
		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager
				.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/revise", method = RequestMethod.GET)
	public String poRevise(Locale locale, Model model) {
		logger.info("[poRevise] " + "");

		model.addAttribute("reviseList", poManager.getAllPurchaseOrder());
		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_REVISE;
	}

	@RequestMapping(value = "/save/{varId}", method = RequestMethod.POST)
	public String poSave(Locale locale, Model model,
			@ModelAttribute("loginContext") LoginContext loginContext,
			BindingResult result, RedirectAttributes redirectAttributes,
			@PathVariable String varId) {
		logger.info("[poSave] " + "");

		loginContextSession.setPoList(loginContext.getPoList());
		PurchaseOrder po = loginContext.getPoList()
				.get(Integer.parseInt(varId));

		po.setPoStatus("L013_C");
		po.setStatusLookup(lookupManager.getLookupByKey("L013_C"));

		if (po.getPoId() == 0) {
			po.setCreatedDate(new Date());
			poManager.addPurchaseOrder(po);
		} else {
			poManager.editPurchaseOrder(po);
		}
		
		List<Items> itemList = new ArrayList<Items>();
		for(Items items : loginContext.getPoList().get(Integer.parseInt(varId)).getItemsList()){
			Product prod = productManager.getProductById(items.getProductId());
			items.setProductLookup(prod);
			itemList.add(items);
		}
		
		loginContextSession.setPoList(loginContext.getPoList());

		loginContextSession.getPoList().get(Integer.parseInt(varId)).setItemsList(itemList);

		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager
				.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,
				Constants.PAGEMODE_ADD);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,
				Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;

	}

	@RequestMapping(value = "/cancel/{tabId}", method = RequestMethod.POST)
	public String poCancel(Locale locale, Model model,
			@ModelAttribute("loginContext") LoginContext loginContext,
			BindingResult result, RedirectAttributes redirectAttributes,
			@PathVariable String tabId) {
		logger.info("[poCancel] " + "");

		if (!loginContext.getPoList().isEmpty()) {
			PurchaseOrder po = loginContext.getPoList().get(
					Integer.parseInt(tabId));
			po.setPoStatus("L013_D");
			po.setStatusLookup(lookupManager.getLookupByKey("L013_D"));
			loginContext.getPoList().remove(po);
			
			List<PurchaseOrder> poList = new ArrayList<PurchaseOrder>();
			for(PurchaseOrder pos : loginContext.getPoList()){
				pos.setStatusLookup(lookupManager.getLookupByKey(pos.getPoStatus()));
				poList.add(pos);
			}

			loginContextSession.setPoList(poList);
		}

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);

		return Constants.JSPPAGE_DASHBOARD;

	}

	@RequestMapping(value = "/saverevise", method = RequestMethod.POST)
	public String reviseSave(Locale locale, Model model,
			@ModelAttribute("reviseForm") PurchaseOrder reviseForm,
			RedirectAttributes redirectAttributes) {
		logger.info("[reviseSave] " + "");

		reviseForm.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
		reviseForm.setUpdatedDate(new Date());

		poManager.editPurchaseOrder(reviseForm);

		model.addAttribute("reviseForm", reviseForm);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,
				Constants.PAGEMODE_EDIT);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,
				Constants.ERRORFLAG_HIDE);

		return "redirect:revise";
	}

	@RequestMapping(value = "/retrieve/supplier", method = RequestMethod.GET)
	public @ResponseBody String poRetrieveSupplier(
			@RequestParam("supplierId") String supplierId) {
		logger.info("[poRetrieveSupplier] " + "supplierId: " + supplierId);

		Supplier supp = supplierManager.getSupplierById(Integer
				.parseInt(supplierId));

		String htmlTag = "" + "<strong>" + supp.getSupplierName() + "</strong>"
				+ "";

		return htmlTag;
	}

	@RequestMapping(value = "/addpayment", method = RequestMethod.POST)
	public String poAddPayments(Locale locale, Model model,
			@ModelAttribute("poForm") PurchaseOrder poForm) {
		logger.info("[poAddPayments] ");

		Payment i = new Payment();
		poForm.getPaymentList().add(i);

		model.addAttribute("poForm", poForm);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/removepayment/{varId}", method = RequestMethod.POST)
	public String poRemovePayments(Locale locale, Model model,
			@ModelAttribute("poForm") PurchaseOrder poForm,
			@PathVariable String varId) {
		logger.info("[poRemovePayment] " + "varId: " + varId);

		List<Payment> payLNew = new ArrayList<Payment>();

		for (int x = 0; x < poForm.getPaymentList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			payLNew.add(poForm.getPaymentList().get(x));
		}

		poForm.setPaymentList(payLNew);

		model.addAttribute("poForm", poForm);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/savepayment", method = RequestMethod.POST)
	public String paymentSave(Locale locale, Model model,
			@ModelAttribute("poForm") PurchaseOrder poForm,
			RedirectAttributes redirectAttributes) {
		logger.info("[paymentSave] " + "");

		poForm.setUpdatedDate(new Date());

		poManager.editPurchaseOrder(poForm);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,
				Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,
				Constants.ERRORFLAG_HIDE);
		return "redirect:payment";
	}

}
