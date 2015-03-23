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
import com.tkbaru.model.Items;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Payment;
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

	private PurchaseOrderListContainer purchaseOrderListContainer;

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

		PurchaseOrder po = new PurchaseOrder();
		po.setPoStatus("L013_D");
		po.setStatusLookup(lookupManager.getLookupByKey("L013_D"));

		model.addAttribute("poForm", po);
		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		// model.addAttribute("poTypeDDL",
		// lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String poEdit(Locale locale, Model model,
			@PathVariable Integer selectedId) {
		logger.info("[poEdit] " + "");

		PurchaseOrder selectedPo = poManager.getPurchaseOrderById(selectedId);

		model.addAttribute("poForm", selectedPo);
		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		// model.addAttribute("poTypeDDL",
		// lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/additems/{varId}", method = RequestMethod.POST)
	public String poAddItems(Locale locale, Model model,
			@ModelAttribute("poForm") PurchaseOrder po,
			@PathVariable String varId) {
		logger.info("[poAddItems] " + "varId: " + varId);

		Items i = new Items();

		i.setProductId(Integer.parseInt(varId));
		i.setProductLookup(productManager.getProductById(Integer
				.parseInt(varId)));

		po.getItemsList().add(i);

		model.addAttribute("poForm", po);
		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		// model.addAttribute("poTypeDDL",
		// lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/removeitems/{varId}", method = RequestMethod.POST)
	public String poRemoveItems(Locale locale, Model model,
			@ModelAttribute("poForm") PurchaseOrder po,
			@PathVariable String varId) {
		logger.info("[poRemoveItems] " + "varId: " + varId);

		List<Items> iLNew = new ArrayList<Items>();

		for (int x = 0; x < po.getItemsList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			iLNew.add(po.getItemsList().get(x));
		}

		po.setItemsList(iLNew);

		model.addAttribute("poForm", po);
		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		// model.addAttribute("poTypeDDL",
		// lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String poPayment(Locale locale, Model model) {
		logger.info("[poPayment] " + "");

		model.addAttribute("poList", poManager.getAllPurchaseOrder());
		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/addpayment/{selectedPo}", method = RequestMethod.GET)
	public String poPaymentAdd(Locale locale, Model model,
			@PathVariable Integer selectedPo) {
		logger.info("[poNew] " + "");

		PurchaseOrder po = poManager.getPurchaseOrderById(selectedPo);

		model.addAttribute("poForm", po);

		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		// model.addAttribute("bankSelectionDDL",
		// bankManager.);
		// model.addAttribute("poTypeDDL",
		// lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

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
		// model.addAttribute("poTypeDDL",
		// lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/revise", method = RequestMethod.GET)
	public String poRevise(Locale locale, Model model) {
		logger.info("[poRevise] " + "");

		model.addAttribute("poList", poManager.getAllPurchaseOrder());
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

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String poSave(Locale locale, Model model,
			@ModelAttribute("poForm") PurchaseOrder po,
			RedirectAttributes redirectAttributes) {
		logger.info("[poSave] " + "");
		po.setPoStatus("L013_C");
		po.setStatusLookup(lookupManager.getLookupByKey("L013_C"));

		if (po.getPoId() == 0) {
			poManager.addPurchaseOrder(po);
		} else {
			poManager.editPurchaseOrder(po);
		}

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE,
				Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG,
				Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PURCHASEORDER;
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

	public PurchaseOrderListContainer getPurchaseOrderListContainer() {

		return purchaseOrderListContainer;
	}

	public void setPurchaseOrderListContainer(
			PurchaseOrderListContainer purchaseOrderListContainer) {
		this.purchaseOrderListContainer = purchaseOrderListContainer;
	}

	@RequestMapping(value = "/addpayment", method = RequestMethod.POST)
	public String poAddPayments(Locale locale, Model model,
			@ModelAttribute("poForm") PurchaseOrder po) {
		logger.info("[poAddPayments] ");

		Payment i = new Payment();
		po.getPaymentList().add(i);

		model.addAttribute("poForm", po);
		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		// model.addAttribute("poTypeDDL",
		// lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/removepayments/{varId}", method = RequestMethod.POST)
	public String poRemovePayments(Locale locale, Model model,
			@ModelAttribute("poForm") PurchaseOrder po,
			@PathVariable String varId) {
		logger.info("[poRemoveItems] " + "varId: " + varId);

		List<Payment> iLNew = new ArrayList<Payment>();

		for (int x = 0; x < po.getPaymentList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			iLNew.add(po.getPaymentList().get(x));
		}

		po.setPaymentList(iLNew);

		model.addAttribute("poForm", po);
		model.addAttribute("productSelectionDDL",
				productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL",
				supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL",
				warehouseManager.getAllWarehouse());
		// model.addAttribute("poTypeDDL",
		// lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,
				loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_PO_PAYMENT;
	}

}
