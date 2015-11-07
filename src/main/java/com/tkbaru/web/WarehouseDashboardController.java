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
import com.tkbaru.common.Converter;
import com.tkbaru.model.Deliver;
import com.tkbaru.model.Items;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.PurchaseOrder;
import com.tkbaru.model.Receipt;
import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.Stocks;
import com.tkbaru.model.StocksOut;
import com.tkbaru.model.WarehouseDashboard;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.PurchaseOrderService;
import com.tkbaru.service.SalesOrderService;
import com.tkbaru.service.StocksOutService;
import com.tkbaru.service.StocksService;
import com.tkbaru.service.WarehouseService;

@Controller
@RequestMapping("/warehouse/dashboard")
public class WarehouseDashboardController {
	private static final Logger logger = LoggerFactory.getLogger(WarehouseDashboardController.class);

	@Autowired
	WarehouseService warehouseManager;

	@Autowired
	PurchaseOrderService poManager;

	@Autowired
	SalesOrderService salesManager;

	@Autowired
	StocksService stocksManager;

	@Autowired
	StocksOutService stocksOutManager;

	@Autowired
	LookupService lookupManager;

	@Autowired
	private LoginContext loginContextSession;

	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		dateFormat.setLenient(true);

		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);

		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	@RequestMapping(method=RequestMethod.GET)
	public String warehouseDashboardPageLoad(Locale locale, Model model) {
		logger.info("[warehouseDashboardPageLoad] : " + "");

		model.addAttribute("warehouseDashboard", new WarehouseDashboard());

		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_WAREHOUSE_DASHBOARD;
	}

	@RequestMapping(value="/id/{warehouseId}", method=RequestMethod.GET)
	public String warehouseDashboardLoadByWarehouseId(Locale locale, Model model, @PathVariable int warehouseId) {
		logger.info("[warehouseDashboardLoadByWarehouseId] : " + "selectedWarehouse: " + warehouseId);

		Date todayDate = new Date();
		Date startDate = Converter.addDays(todayDate, -7);
		Date endDate = Converter.addDays(todayDate, 7);
		
		List<PurchaseOrder> poList = poManager.getPurchaseOrderByWarehouseIdByShippingDate(warehouseId, startDate, endDate);

		List<SalesOrder> soList = salesManager.getSalesOrderByStatus("L016_WD");

		WarehouseDashboard warehouseDashboard = new WarehouseDashboard();
		warehouseDashboard.setSelectedWarehouse(warehouseId);
		warehouseDashboard.setPurchaseOrderList(poList);
		warehouseDashboard.setSalesOrderList(soList);

		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("warehouseDashboard", warehouseDashboard);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_WAREHOUSE_DASHBOARD;
	}

	@RequestMapping(value="/id/{warehouseId}/loadreceipt/{poId}/{itemId}", method=RequestMethod.GET)
	public String loadReceipt(Locale locale, Model model, @PathVariable int warehouseId, @PathVariable int poId, @PathVariable int itemId) {
		logger.info("[loadReceipt] : " + "selectedWarehouse: " + warehouseId + ", poId: " + poId);

		List<PurchaseOrder> poList = poManager.getPurchaseOrderByWarehouseIdByStatus(warehouseId, "L013_WA");

		PurchaseOrder selectedPoObject = null;
		Items selectedItemsObject = null;

		for (PurchaseOrder po : poList) {
			if (po.getPoId() == poId) {
				selectedPoObject = po;
			}
			po.getItemsList().size();
			for (Items item : po.getItemsList()) {
				if (item.getItemsId() == itemId) {
					selectedItemsObject = item;
				}
				item.getReceiptList().size();
			}
		}

		WarehouseDashboard warehouseDashboard = new WarehouseDashboard();
		warehouseDashboard.setSelectedWarehouse(warehouseId);
		warehouseDashboard.setSelectedPO(poId);
		warehouseDashboard.setSelectedItems(itemId);
		warehouseDashboard.setPurchaseOrderList(poList);

		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("warehouseDashboard", warehouseDashboard);
		model.addAttribute("selectedPoObject", selectedPoObject);
		model.addAttribute("selectedItemsObject", selectedItemsObject);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_WAREHOUSE_DASHBOARD;
	}

	@RequestMapping(value = "/savereceipt/{poId}/{itemId}", method = RequestMethod.POST)
	public String saveReceipt(Locale locale, Model model, @ModelAttribute("warehouseDashboard") WarehouseDashboard warehouseDashboard, RedirectAttributes redirectAttributes, @PathVariable int poId, @PathVariable int itemId) {
		logger.info("[saveReceipt] " + "poId: " + poId + ", itemId: " + itemId);

		PurchaseOrder po = poManager.getPurchaseOrderById(poId);

		List<Items> itemsList = po.getItemsList();
		List<Stocks> stocksList = new ArrayList<Stocks>();
		for (Items items : itemsList) {
			if (items.getItemsId() == itemId) {
				List<Receipt> receiptList = items.getReceiptList();

				if (warehouseDashboard.getReceipt().getReceiptId() == 0) {

					warehouseDashboard.getReceipt().setCreatedBy(loginContextSession.getUserLogin().getUserId());
					warehouseDashboard.getReceipt().setCreatedDate(new Date());

					Stocks stocks = new Stocks();
					stocks.setPoId(poId);
					stocks.setProductId(items.getProductId());
					stocks.setWarehouseId(po.getWarehouseId());
					stocks.setProdQuantity(warehouseDashboard.getReceipt().getNet());
					stocks.setCurrentQuantity(warehouseDashboard.getReceipt().getNet());
					stocks.setCreatedBy(loginContextSession.getUserLogin().getUserId());
					stocks.setCreatedDate(new Date());
					stocksList.add(stocks);
				}
				receiptList.add(warehouseDashboard.getReceipt());
			}
		}

		boolean isAllArrived = checkAllArrived(po.getItemsList());
		
		if (isAllArrived) po.setPoStatus("L013_WP");
		
		poManager.editPurchaseOrder(po);

		for (Stocks stocks : stocksList) {
			stocksManager.addOrCreateStocks(stocks);
		}

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/warehouse/dashboard/id/" + po.getWarehouseId();
	}

	private boolean checkAllArrived(List<Items> items) {
		boolean result = true;
		
		for (Items i:items) {
			if (i.getReceiptList().size() == 0) {
				result = false;
			}
		}
		
		return result;
	}

	@RequestMapping(value = "/id/{warehouseId}/loaddeliver/{salesId}", method = RequestMethod.GET)
	public String loadDeliver(Locale locale, 
								Model model,
								@PathVariable int warehouseId, @PathVariable int salesId) {
		logger.info("[loadDeliver] : " + "selectedWarehouse: " + warehouseId + ", salesId: " + salesId);

		List<SalesOrder> soList = new ArrayList<SalesOrder>();
		SalesOrder selectedSoObject = salesManager.getSalesOrderById(salesId); 

		for (Items i:selectedSoObject.getItemsList()) {
			List<Deliver> d = new ArrayList<Deliver>();
			d.add(new Deliver());
			i.setDeliverList(d);
		}
		
		soList.add(selectedSoObject);
		
		WarehouseDashboard warehouseDashboard = new WarehouseDashboard();
		warehouseDashboard.setSelectedWarehouse(warehouseId);
		warehouseDashboard.setSelectedSales(salesId);
		warehouseDashboard.setSalesOrderList(soList);
		
		model.addAttribute("warehouseDashboard", warehouseDashboard);
		model.addAttribute("selectedSoObject", selectedSoObject);
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute("flow", "Outflow");
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_WAREHOUSE_DASHBOARD;
	}

	@RequestMapping(value = "/savedeliver/{salesId}/{warehouseId}", method = RequestMethod.POST)
	public String saveDashboardDeliver(Locale locale, 
										Model model, 
										@ModelAttribute("warehouseDashboard") WarehouseDashboard warehouseDashboard, 
										RedirectAttributes redirectAttributes, 
										@PathVariable int salesId, 
										@PathVariable int warehouseId) {
		logger.info("[saveDashboardDeliver] " + "salesId: " + salesId + ", warehouseId: " + warehouseId);

		SalesOrder sales = salesManager.getSalesOrderById(salesId);

		List<Items> itemsList = sales.getItemsList();
		List<Stocks> stocksList = new ArrayList<Stocks>();
		List<StocksOut> stocksOutList = new ArrayList<StocksOut>();
		for (Items items : itemsList) {
			for (Items itemX : warehouseDashboard.getSalesOrderList().get(0).getItemsList()) {
				if (itemX.getItemsId() == items.getItemsId()) {
					items.setDeliverList(itemX.getDeliverList());

					StocksOut stocksOut = new StocksOut();
					stocksOut.setSalesId(salesId);
					stocksOut.setProductId(items.getProductId());
					stocksOut.setWarehouseId(warehouseId);
					stocksOut.setProdQuantity(itemX.getDeliverList().get(0).getBruto());
					stocksOut.setCreatedBy(loginContextSession.getUserLogin().getUserId());
					stocksOut.setCreatedDate(new Date());
					stocksOutList.add(stocksOut);
					
					
					Stocks s = items.getStocksLookup();
					s.setCurrentQuantity(s.getCurrentQuantity() - itemX.getDeliverList().get(0).getBruto());
					s.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
					s.setUpdatedDate(new Date());
					stocksList.add(s);
				}
			}
		}
		
		sales.setSalesStatusLookup(lookupManager.getLookupByKey("L016_WP"));

		salesManager.editSalesOrder(sales);

		for (StocksOut stocks : stocksOutList) {
			stocksOutManager.addOrCreateStocksOut(stocks);
		}

		stocksManager.updateStocks(stocksList);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/warehouse/dashboard/id/" + warehouseDashboard.getSelectedWarehouse();
	}
}
