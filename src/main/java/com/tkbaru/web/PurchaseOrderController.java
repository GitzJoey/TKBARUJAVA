package com.tkbaru.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Items;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Payment;
import com.tkbaru.model.Product;
import com.tkbaru.model.ProductUnit;
import com.tkbaru.model.PurchaseOrder;
import com.tkbaru.model.Receipt;
import com.tkbaru.model.Supplier;
import com.tkbaru.service.BankService;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.ProductService;
import com.tkbaru.service.PurchaseOrderService;
import com.tkbaru.service.ReportService;
import com.tkbaru.service.SupplierService;
import com.tkbaru.service.WarehouseService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

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
	
	@Autowired
	ReportService reportManager;

	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(true);
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	private long getNetto(Items item) {
		long result = 0;

		if (item.getReceiptList().size() == 0)
			return result;

		for (Receipt r : item.getReceiptList()) {
			result += r.getNet();
		}

		return result;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String poNew(Locale locale, Model model) {
		logger.info("[poNew] " + "");

		if (loginContextSession.getPoList().isEmpty()) {
			PurchaseOrder po = new PurchaseOrder();

			po.setPoCode(poManager.generatePOCode());
			po.setPoStatusLookup(lookupManager.getLookupByKey("L013_D"));
			po.setPoCreatedDate(new Date());
			po.setShippingDate(new Date());
			po.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			po.setCreatedDate(new Date());
			po.setPoStoreEntity(loginContextSession.getUserLogin().getStoreEntity());

			loginContextSession.getPoList().add(po);
		}

		for (PurchaseOrder po : loginContextSession.getPoList()) {
			for (Items items : po.getItemsList()) {
				items.setProductEntity(productManager.getProductById(items.getProductEntity().getProductId()));
			}
		}

		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/t/{tabId}/additems/{productId}", method = RequestMethod.POST)
	public String poAddItems(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext,
			@PathVariable String tabId, @PathVariable String productId) {
		logger.info("[poAddItems] " + "tabId: " + tabId + ", productId: " + productId);

		Items item = new Items();
		item.setProductEntity(productManager.getProductById(Integer.parseInt(productId)));
		item.setCreatedDate(new Date());
		item.setCreatedBy(loginContextSession.getUserLogin().getUserId());

		for (ProductUnit productUnit : item.getProductEntity().getProductUnit()) {
			if (productUnit.getIsBaseUnit() == true) {
				item.setBaseUnitCodeLookup(
						lookupManager.getLookupByKey(productUnit.getUnitCodeLookup().getLookupKey()));
			}
		}

		loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList().add(item);

		for (PurchaseOrder po : loginContext.getPoList()) {
			po.setSupplierEntity(supplierManager.getSupplierById(po.getSupplierEntity().getSupplierId()));
			po.getSupplierEntity().getProdList().size();
			for (Items items : po.getItemsList()) {
				items.setProductEntity(productManager.getProductById(items.getProductEntity().getProductId()));
			}
		}

		loginContextSession.setPoList(loginContext.getPoList());

		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/t/{tabId}/removeitems/{productId}", method = RequestMethod.POST)
	public String poRemoveItemsMulti(Locale locale, Model model,
			@ModelAttribute("loginContext") LoginContext loginContext, @PathVariable String tabId,
			@PathVariable String productId) {
		logger.info("[poRemoveItemsMulti] " + "tabId: " + tabId + ", productId: " + productId);

		List<Items> iLNew = new ArrayList<Items>();

		for (int x = 0; x < loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList().size(); x++) {
			if (x == Integer.parseInt(productId))
				continue;
			iLNew.add(loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList().get(x));
		}

		loginContext.getPoList().get(Integer.parseInt(tabId)).setItemsList(iLNew);

		for (Items items : loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList()) {
			items.setProductEntity(productManager.getProductById(items.getProductEntity().getProductId()));
		}

		loginContextSession.getPoList().get(Integer.parseInt(tabId))
				.setItemsList(loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList());

		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/t/{tabId}/save", method = RequestMethod.POST)
	public String poSave(Locale locale, Model model, @ModelAttribute("loginContext") LoginContext loginContext,
			RedirectAttributes redirectAttributes, @PathVariable String tabId) {
		logger.info("[poSave] " + "tabId: " + tabId);

		loginContextSession.setPoList(loginContext.getPoList());

		PurchaseOrder po = loginContext.getPoList().get(Integer.parseInt(tabId));
		po.setPoStatusLookup(lookupManager.getLookupByKey("L013_WA"));

		List<Items> itemList = new ArrayList<Items>();
		for (Items items : loginContext.getPoList().get(Integer.parseInt(tabId)).getItemsList()) {
			items.setProductEntity(productManager.getProductById(items.getProductEntity().getProductId()));

			for (ProductUnit productUnit : items.getProductEntity().getProductUnit()) {
				if (productUnit.getUnitCodeLookup().getLookupKey().equals(items.getUnitCodeLookup().getLookupKey())) {
					items.setToBaseValue(productUnit.getConversionValue());
					items.setToBaseQty(productUnit.getConversionValue() * items.getProdQuantity());
				}
			}
			items.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			items.setCreatedDate(new Date());
			itemList.add(items);
		}

		if (po.getPoId() == null) {
			po.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			po.setCreatedDate(new Date());
			po.setPoStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
			poManager.addPurchaseOrder(po);
		} else {
			po.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
			po.setUpdatedDate(new Date());
			po.setPoStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
			poManager.editPurchaseOrder(po);
		}

		for (PurchaseOrder poVar : loginContext.getPoList()) {
			poVar.setSupplierEntity(supplierManager.getSupplierById(poVar.getSupplierEntity().getSupplierId()));
			poVar.setWarehouseEntity(warehouseManager.getWarehouseById(poVar.getWarehouseEntity().getWarehouseId()));
			poVar.setPoTypeLookup(lookupManager.getLookupByKey(poVar.getPoTypeLookup().getLookupKey()));
			for (Items items : poVar.getItemsList()) {
				items.setProductEntity(productManager.getProductById(items.getProductEntity().getProductId()));
				items.setBaseUnitCodeLookup(lookupManager.getLookupByKey(items.getBaseUnitCodeLookup().getLookupKey()));
				items.setUnitCodeLookup(lookupManager.getLookupByKey(items.getUnitCodeLookup().getLookupKey()));
			}
		}

		loginContextSession.setPoList(loginContext.getPoList());
		loginContextSession.getPoList().get(Integer.parseInt(tabId)).setItemsList(itemList);

		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/t/{tabId}/generate/{poId}", method = RequestMethod.GET)
	public ModelAndView poGenerate(Locale locale, Model model, @PathVariable int tabId, @PathVariable String poId,
			HttpServletResponse response) throws JRException {
		logger.info("[poGenerate] " + "tabId: " + tabId + ", poId: " + poId);

		ModelAndView mav = null;
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		PurchaseOrder po = poManager.getPurchaseOrderById(Integer.parseInt(poId));
		
		JRDataSource ds = reportManager.generateReportDS_PurchaseOrder(po);

		parameterMap.put("datasource", ds);
		mav = new ModelAndView("po_pdf", parameterMap);

		return mav;
	}
	
	
	@RequestMapping(value = "/payment/generate/{selectedPo}", method = RequestMethod.GET)
	public ModelAndView paymentGenerate(Locale locale, Model model, @PathVariable String selectedPo,
			HttpServletResponse response) throws JRException {
		logger.info("[poGenerate] " + "selectedPo: " + selectedPo);

		ModelAndView mav = null;
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		PurchaseOrder po = poManager.getPurchaseOrderById(Integer.parseInt(selectedPo));

		JRDataSource ds = reportManager.generateReportDS_PurchaseOrder(po);

		parameterMap.put("datasource", ds);
		mav = new ModelAndView("po_payment_pdf", parameterMap);

		return mav;
	}

	@RequestMapping(value = "/t/{tabId}/cancel", method = RequestMethod.POST)
	public String poCancel(Locale locale, Model model, RedirectAttributes redirectAttributes, @PathVariable int tabId) {
		logger.info("[poCancel] " + "tabId: " + tabId);

		loginContextSession.getPoList().remove(tabId);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);

		return Constants.JSPPAGE_DASHBOARD;
	}

	@RequestMapping(value = "/revise/{selectedId}", method = RequestMethod.GET)
	public String reviseForm(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[reviseForm] " + "selectedId: " + selectedId);

		PurchaseOrder selectedPo = poManager.getPurchaseOrderById(selectedId);

		model.addAttribute("reviseForm", selectedPo);
		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PO_REVISE;
	}

	@RequestMapping(value = "/revise/{poId}/additems/{productId}", method = RequestMethod.POST)
	public String reviseAddItems(Locale locale, Model model, @ModelAttribute("reviseForm") PurchaseOrder reviseForm,
			@PathVariable int poId, @PathVariable int productId) {
		logger.info("[reviseAddItems] " + "poId: " + poId + ", productId: " + productId);

		Items i = new Items();
		i.setProductEntity(productManager.getProductById(productId));
		i.setCreatedDate(new Date());
		i.setCreatedBy(loginContextSession.getUserLogin().getUserId());

		for (ProductUnit productUnit : i.getProductEntity().getProductUnit()) {
			if (productUnit.getIsBaseUnit()) {
				i.setBaseUnitCodeLookup(lookupManager.getLookupByKey(productUnit.getUnitCodeLookup().getLookupKey()));
			}
		}

		reviseForm.getItemsList().add(i);

		for (Items item : reviseForm.getItemsList()) {
			if (item.getProductEntity().getProductId() != null) {
				item.setProductEntity(productManager.getProductById(item.getProductEntity().getProductId()));
			}
			if (item.getUnitCodeLookup() != null) {
				item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCodeLookup().getLookupKey()));
			}
		}

		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		model.addAttribute("reviseForm", reviseForm);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PO_REVISE;
	}

	@RequestMapping(value = "/revise/{poId}/removeitems/{pIdx}", method = RequestMethod.POST)
	public String poReviseRemoveItems(Locale locale, Model model,
			@ModelAttribute("reviseForm") PurchaseOrder reviseForm, @PathVariable int poId, @PathVariable int pIdx) {
		logger.info("[poReviseRemoveItems] " + "poId: " + poId + ", pIdx: " + pIdx);

		List<Items> iLNew = new ArrayList<Items>();

		for (int x = 0; x < reviseForm.getItemsList().size(); x++) {
			if (x == pIdx)
				continue;
			iLNew.add(reviseForm.getItemsList().get(x));
		}

		reviseForm.setItemsList(iLNew);

		for (Items item : reviseForm.getItemsList()) {
			if (item.getProductEntity().getProductId() != null) {
				item.setProductEntity(productManager.getProductById(item.getProductEntity().getProductId()));
			}
			if (item.getUnitCodeLookup() != null) {
				item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCodeLookup().getLookupKey()));
			}
		}

		model.addAttribute("reviseForm", reviseForm);
		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PO_REVISE;
	}

	@RequestMapping(value = "/addpoform", method = RequestMethod.GET)
	public String addPoForm(Locale locale, Model model) {
		logger.info("[addPoForm] ");

		PurchaseOrder newPo = new PurchaseOrder();
		newPo.setPoCode(poManager.generatePOCode());
		newPo.setPoCreatedDate(new Date());
		newPo.setShippingDate(new Date());
		newPo.setPoStatusLookup(lookupManager.getLookupByKey("L013_D"));
		newPo.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		newPo.setCreatedDate(new Date());

		loginContextSession.getPoList().add(newPo);

		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PURCHASEORDER;
	}

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String poPayment(Locale locale, Model model) {
		logger.info("[poPayment] " + "");

		model.addAttribute("poList", poManager.getPurchaseOrderByStatus("L013_WP"));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/payment/view/{selectedId}", method = RequestMethod.GET)
	public String poPaymentView(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[poPaymentView] " + "selectedId: " + selectedId);

		model.addAttribute("ViewMode", true);
		model.addAttribute("poForm", poManager.getPurchaseOrderById(selectedId));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute("cashStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("giroStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/payment/cash/{selectedPo}", method = RequestMethod.GET)
	public String poCashPayment(Locale locale, Model model, @PathVariable Integer selectedPo) {
		logger.info("[poCashPayment] " + "");

		PurchaseOrder po = poManager.getPurchaseOrderById(selectedPo);
		Payment payment = new Payment();
		payment.setPaymentTypeLookup(lookupManager.getLookupByKey("L017_CASH"));

		po.getPaymentList().add(payment);

		model.addAttribute("poForm", po);
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute("cashStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("giroStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/payment/transfer/{selectedPo}", method = RequestMethod.GET)
	public String poTransferPayment(Locale locale, Model model, @PathVariable Integer selectedPo) {
		logger.info("[poTransferPayment] " + "");

		PurchaseOrder po = poManager.getPurchaseOrderById(selectedPo);
		Payment payment = new Payment();
		payment.setPaymentTypeLookup(lookupManager.getLookupByKey("L017_TRANSFER"));

		po.getPaymentList().add(payment);

		model.addAttribute("poForm", po);
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute("cashStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("giroStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/payment/giro/{selectedPo}", method = RequestMethod.GET)
	public String poGiroPayment(Locale locale, Model model, @PathVariable Integer selectedPo) {
		logger.info("[poGiroPayment] " + "");

		PurchaseOrder po = poManager.getPurchaseOrderById(selectedPo);
		Payment payment = new Payment();
		payment.setPaymentTypeLookup(lookupManager.getLookupByKey("L017_GIRO"));

		po.getPaymentList().add(payment);

		model.addAttribute("poForm", po);
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute("cashStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("giroStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/revise", method = RequestMethod.GET)
	public String poRevise(Locale locale, Model model) {
		logger.info("[poRevise] " + "");

		model.addAttribute("reviseList", poManager.getAllUnfinishedPurchaseOrder());

		model.addAttribute("productSelectionDDL", productManager.getAllProduct());
		model.addAttribute("supplierSelectionDDL", supplierManager.getAllSupplier());
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PO_REVISE;
	}

	@RequestMapping(value = "/revise/{poId}/save", method = RequestMethod.POST)
	public String reviseSave(Locale locale, Model model, @ModelAttribute("reviseForm") PurchaseOrder reviseForm,
			RedirectAttributes redirectAttributes, @PathVariable Integer poId) {
		logger.info("[reviseSave] " + "poId: " + poId);

		reviseForm.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
		reviseForm.setUpdatedDate(new Date());

		List<Items> itemList = new ArrayList<Items>();
		for (Items items : reviseForm.getItemsList()) {
			items.setProductEntity(productManager.getProductById(items.getProductEntity().getProductId()));
			for (ProductUnit productUnit : items.getProductEntity().getProductUnit()) {
				if (productUnit.getUnitCodeLookup().getLookupKey().equals(items.getUnitCodeLookup().getLookupKey())) {
					items.setToBaseValue(productUnit.getConversionValue());
					items.setToBaseQty(productUnit.getConversionValue() * items.getProdQuantity());
				}
			}
			items.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
			items.setUpdatedDate(new Date());
			itemList.add(items);
		}

		poManager.editPurchaseOrder(reviseForm);

		model.addAttribute("reviseForm", reviseForm);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/po/revise";
	}

	@RequestMapping(value = "/retrieve/supplier", method = RequestMethod.GET)
	public @ResponseBody Supplier poRetrieveSupplier(@RequestParam("supplierId") String supplierId) {
		logger.info("[poRetrieveSupplier] " + "supplierId: " + supplierId);

		Supplier supp = supplierManager.getSupplierById(Integer.parseInt(supplierId));

		Supplier jsonSupp = new Supplier();
		jsonSupp.setSupplierName(supp.getSupplierName());

		List<Product> pL = new ArrayList<Product>();
		for (Product p : supp.getProdList()) {
			pL.add(new Product(p.getProductId()));
		}

		jsonSupp.setProdList(pL);

		return jsonSupp;
	}

	@RequestMapping(value = "/check/supplier/prod", method = RequestMethod.GET, produces = "text/plain")
	public @ResponseBody String poCheckSupplierProd(@RequestParam("supplierId") int supplierId,
			@RequestParam("productId") int productId) {
		logger.info("[poCheckSupplierProd] " + "supplierId: " + supplierId + ", productId: " + productId);

		Supplier supp = supplierManager.getSupplierById(supplierId);

		String found = "false";

		for (Product p : supp.getProdList()) {
			if (p.getProductId() == productId) {
				found = "true";
			}
		}

		logger.info("[poCheckSupplierProd] " + "Result: " + found);

		return found;
	}

	@RequestMapping(value = "/addpayment/{paymentType}", method = RequestMethod.POST)
	public String poAddPayments(Locale locale, Model model, @ModelAttribute("poForm") PurchaseOrder poForm,
			@PathVariable String paymentType) {
		logger.info("[poAddPayments] ");

		for (Items item : poForm.getItemsList()) {
			item.setUnitCodeLookup(lookupManager.getLookupByKey(item.getUnitCodeLookup().getLookupKey()));
		}

		Payment i = new Payment();
		i.setPaymentTypeLookup(lookupManager.getLookupByKey(paymentType));
		poForm.getPaymentList().add(i);

		for (Payment payment : poForm.getPaymentList()) {
			payment.setPaymentTypeLookup(lookupManager.getLookupByKey(payment.getPaymentTypeLookup().getLookupKey()));
			if (payment.getBankCodeLookup() != null) {
				payment.setBankCodeLookup(lookupManager.getLookupByKey(payment.getBankCodeLookup().getLookupKey()));
			}
		}

		poForm.setPaymentList(poForm.getPaymentList());

		model.addAttribute("poForm", poForm);
		model.addAttribute("poTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PO_TYPE));
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		model.addAttribute("cashStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("giroStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/removepayment/{varId}", method = RequestMethod.POST)
	public String poRemovePayments(Locale locale, Model model, @ModelAttribute("poForm") PurchaseOrder poForm,
			@PathVariable String varId) {
		logger.info("[poRemovePayment] " + "varId: " + varId);

		List<Payment> payLNew = new ArrayList<Payment>();

		for (int x = 0; x < poForm.getPaymentList().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			payLNew.add(poForm.getPaymentList().get(x));
		}

		poForm.setPaymentList(payLNew);

		for (Payment payment : poForm.getPaymentList()) {
			payment.setPaymentTypeLookup(lookupManager.getLookupByKey(payment.getPaymentTypeLookup().getLookupKey()));
		}

		model.addAttribute("poForm", poForm);
		model.addAttribute("paymentTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_TYPE));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_PO_PAYMENT;
	}

	@RequestMapping(value = "/savepayment", method = RequestMethod.POST)
	public String paymentSave(Locale locale, Model model, @ModelAttribute("poForm") PurchaseOrder poForm,
			RedirectAttributes redirectAttributes) {
		logger.info("[paymentSave] " + "");

		PurchaseOrder po = poManager.getPurchaseOrderById(poForm.getPoId());
		po.setUpdatedDate(new Date());
		po.setPaymentList(poForm.getPaymentList());

		long totalHutang = 0;

		for (Items items : po.getItemsList()) {
			totalHutang += (getNetto(items) * items.getProdPrice());
		}

		long totalPayment = 0;

		for (Payment payment : poForm.getPaymentList()) {
			if (payment.getPaymentStatusLookup() != null) {
				if (payment.getPaymentTypeLookup().getLookupKey().equals("L017_CASH")
						&& payment.getPaymentStatusLookup().getLookupKey().equals("L018_C")) {
					totalPayment += payment.getTotalAmount();
				}
				if (payment.getPaymentTypeLookup().getLookupKey().equals("L017_GIRO")
						&& payment.getPaymentStatusLookup().getLookupKey().equals("L021_FR")) {
					totalPayment += payment.getTotalAmount();
				}
				if (payment.getPaymentTypeLookup().getLookupKey().equals("L017_TRANSFER")
						&& payment.getPaymentStatusLookup().getLookupKey().equals("L020_B")) {
					totalPayment += payment.getTotalAmount();
				}
			}
		}

		if (totalHutang == totalPayment) {
			po.setPoStatusLookup(lookupManager.getLookupByKey("L013_C"));
		}

		poManager.editPurchaseOrder(po);

		model.addAttribute("cashStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_CASH));
		model.addAttribute("transferStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_TRANSFER));
		model.addAttribute("giroStatusDDL",
				lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_PAYMENT_STATUS_GIRO));

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:payment";
	}

}