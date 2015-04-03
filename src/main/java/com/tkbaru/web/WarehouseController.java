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
import com.tkbaru.model.WarehouseDashboard;
import com.tkbaru.model.Items;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.PurchaseOrder;
import com.tkbaru.model.Receipt;
import com.tkbaru.model.Warehouse;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.PurchaseOrderService;
import com.tkbaru.service.WarehouseService;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {
	private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

	@Autowired
	WarehouseService warehouseManager;
	
	@Autowired
	PurchaseOrderService poManager;
	
	@Autowired
	LookupService lookupManager;

	@Autowired
	private LoginContext loginContextSession;
	
	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(true);
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat,true);
		binder.registerCustomEditor(Date.class, orderDateEditor);

	}

	@RequestMapping(value="/dashboard", method = RequestMethod.GET)
	public String warehouseDashboardPageLoad(Locale locale, Model model) {
		logger.info("[warehousePageLoad] : " + "");

		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		
		
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_WAREHOUSE_DASHBOARD;
	}
	
	@RequestMapping(value="/displayitems/{warehouseId}", method = RequestMethod.POST)
	public String warehouseDashboardLoadProduct(Locale locale, Model model, @PathVariable int warehouseId) {
		logger.info("[warehousePageLoad] : " + "");
		
		List<PurchaseOrder> poList = poManager.getPurchaseOrderByWarehouseIdByStatus(warehouseId,"L013_WA");
		
		WarehouseDashboard dashboardModel = new WarehouseDashboard();
		dashboardModel.setPurchaseOrderList(poList);
		
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("dashboardModel", dashboardModel);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_WAREHOUSE_DASHBOARD;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String warehousePageLoad(Locale locale, Model model) {		
		logger.info("[warehousePageLoad] " + "");
		
		List<Warehouse> wList = warehouseManager.getAllWarehouse();
		
		model.addAttribute("warehouseList", wList);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_WAREHOUSE;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addWarehouse(Locale locale, Model model) {
		logger.info("[addWarehouse] : " + "");
		
		model.addAttribute("warehouseForm", new Warehouse());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_WAREHOUSE;
	}

	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String editWarehouse(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[editWarehouse] " + "selectedId = " + selectedId);
		
		Warehouse selectedWarehouse = warehouseManager.getWarehouseById(selectedId);
		
		logger.info("[editWarehouse] " + "selectedWarehouse = " + selectedWarehouse.toString());
		
		model.addAttribute("warehouseForm", selectedWarehouse);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_WAREHOUSE;
	}

	@RequestMapping(value = "/delete/{selectedId}", method = RequestMethod.GET)
	public String deleteWarehouse(Locale locale, Model model, @PathVariable Integer selectedId, RedirectAttributes redirectAttributes) {
		logger.info("[deleteWarehouse] " + "selectedId = " + selectedId);
		
		warehouseManager.deleteWarehouse(selectedId);
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/warehouse";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveWarehouse(Locale locale, Model model, @ModelAttribute("warehouseForm") Warehouse warehouse, RedirectAttributes redirectAttributes) {	
        		
		if (warehouse.getWarehouseId() == 0) {
			logger.info("[saveWarehouse] " + "addWarehouse: " + warehouse.toString());
			warehouseManager.addWarehouse(warehouse); 
		} else { 
			logger.info("[saveWarehouse] " + "editWarehouse: " + warehouse.toString());
			warehouseManager.editWarehouse(warehouse); 
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/warehouse";
	}
	
	@RequestMapping(value="/savereceipt/{poId}", method = RequestMethod.POST)
	public String saveReceipt(Locale locale, Model model, @ModelAttribute("warehouseDashboard") WarehouseDashboard warehouseDashboard, RedirectAttributes redirectAttributes,@PathVariable int poId) {	
        		
		for(PurchaseOrder purchaseOrder : warehouseDashboard.getPurchaseOrderList()){
			if(purchaseOrder.getPoId()==poId){
				PurchaseOrder po = poManager.getPurchaseOrderById(poId);
				po.setItemsList(purchaseOrder.getItemsList());
				
				Boolean isAllArrived = false;
				
				for(Items item : po.getItemsList()){
					
					long arrivalQuantity = 0;
					
					for(Receipt receipt : item.getReceiptList()){
						arrivalQuantity += receipt.getNet();
					}
					
					if(item.getProdQuantity()== arrivalQuantity){
						isAllArrived = true;
					}else{
						isAllArrived = false;
					}
				}
				
				if(isAllArrived){
					po.setPoStatus("L013_WP");
				}
				
				poManager.editPurchaseOrder(po);
			}
		}
		
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/warehouse/dashboard";
	}
}
