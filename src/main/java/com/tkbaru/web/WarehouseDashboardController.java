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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.common.Converter;
import com.tkbaru.model.Deliver;
import com.tkbaru.model.Items;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Lookup;
import com.tkbaru.model.ProductUnit;
import com.tkbaru.model.PurchaseOrder;
import com.tkbaru.model.Receipt;
import com.tkbaru.model.SalesOrder;
import com.tkbaru.model.Stocks;
import com.tkbaru.model.StocksOut;
import com.tkbaru.model.WarehouseDashboard;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.PurchaseOrderService;
import com.tkbaru.service.ReportService;
import com.tkbaru.service.SalesOrderService;
import com.tkbaru.service.StocksOutService;
import com.tkbaru.service.StocksService;
import com.tkbaru.service.WarehouseService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

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
	
	@Autowired
	private ReportService reportManager;

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
		
		model.addAttribute(Constants.PAGE_TITLE, "");

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
		
		model.addAttribute(Constants.PAGE_TITLE, "");

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

		List<Lookup> unit = lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_UNIT);
		List<Lookup> unitL = new ArrayList<Lookup>();
		
		for (Lookup l:unit) {
			for (ProductUnit pu:selectedItemsObject.getProductEntity().getProductUnit()) {
				if (pu.getUnitCodeLookup().getLookupKey().equals(l.getLookupKey())) {
					unitL.add(l);
				}
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
		model.addAttribute("unitDDL", unitL);

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);

		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

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

				if (warehouseDashboard.getReceipt().getReceiptId() == null) {

					warehouseDashboard.getReceipt().setCreatedBy(loginContextSession.getUserLogin().getUserId());
					warehouseDashboard.getReceipt().setCreatedDate(new Date());
					warehouseDashboard.getReceipt().setReceiptStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
					
					for (ProductUnit pu:items.getProductEntity().getProductUnit()) {
						if (pu.getIsBaseUnit()) {
							warehouseDashboard.getReceipt().setBaseUnitCodeLookup(pu.getUnitCodeLookup());
						}
						if (pu.getUnitCodeLookup().getLookupKey().equals(warehouseDashboard.getReceipt().getUnitCodeLookup().getLookupKey())) {
							warehouseDashboard.getReceipt().setBaseBruto(warehouseDashboard.getReceipt().getBruto() * pu.getConversionValue());
							warehouseDashboard.getReceipt().setBaseTare(warehouseDashboard.getReceipt().getTare() * pu.getConversionValue());
							warehouseDashboard.getReceipt().setBaseNet(warehouseDashboard.getReceipt().getNet() * pu.getConversionValue());
						}
					}
					
					Stocks stocks = new Stocks();
					stocks.setPurchaseOrderEntity(po);
					stocks.setProductEntity(items.getProductEntity());
					stocks.setWarehouseEntity(po.getWarehouseEntity());
					stocks.setProdQuantity(warehouseDashboard.getReceipt().getNet());
					stocks.setCurrentQuantity(warehouseDashboard.getReceipt().getNet());
					stocks.setCreatedBy(loginContextSession.getUserLogin().getUserId());
					stocks.setCreatedDate(new Date());
					stocks.setStocksStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
					stocksList.add(stocks);
				}
				receiptList.add(warehouseDashboard.getReceipt());
			}
		}

		boolean isAllArrived = checkAllArrived(po.getItemsList());
		
		if (isAllArrived) po.setPoStatusLookup(lookupManager.getLookupByKey("L013_WP"));
		
		poManager.editPurchaseOrder(po);

		for (Stocks stocks : stocksList) {
			stocksManager.addOrCreateStocks(stocks);
		}

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return "redirect:/warehouse/dashboard/id/" + po.getWarehouseEntity().getWarehouseId();
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
			List<Deliver> dl = new ArrayList<Deliver>();
			Deliver d = new Deliver();
			d.setDeliverStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
			d.setDeliverItemsEntity(i);
			
			dl.add(d);
			i.setDeliverList(dl);
		}
		
		soList.add(selectedSoObject);
		
		WarehouseDashboard warehouseDashboard = new WarehouseDashboard();
		warehouseDashboard.setSelectedWarehouse(warehouseId);
		warehouseDashboard.setSelectedSales(salesId);
		warehouseDashboard.setSalesOrderList(soList);
		
		model.addAttribute("warehouseDashboard", warehouseDashboard);
		model.addAttribute("selectedSoObject", selectedSoObject);
		model.addAttribute("warehouseSelectionDDL", warehouseManager.getAllWarehouse());
		model.addAttribute("unitDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_UNIT));
		model.addAttribute("flow", "Outflow");

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_WAREHOUSE_DASHBOARD;
	}

	@RequestMapping(value = "/savedeliver/{salesId}/{warehouseId}", method = RequestMethod.POST)
	public String saveDeliver(Locale locale, 
										Model model, 
										@ModelAttribute("warehouseDashboard") WarehouseDashboard warehouseDashboard, 
										RedirectAttributes redirectAttributes, 
										@PathVariable int salesId, 
										@PathVariable int warehouseId) {
		logger.info("[saveDeliver] " + "salesId: " + salesId + ", warehouseId: " + warehouseId);

		SalesOrder sales = salesManager.getSalesOrderById(salesId);

		List<Items> itemsList = sales.getItemsList();
		List<Stocks> stocksList = new ArrayList<Stocks>();
		List<StocksOut> stocksOutList = new ArrayList<StocksOut>();
		for (Items items : itemsList) {
			for (Items itemX : warehouseDashboard.getSalesOrderList().get(0).getItemsList()) {
				if (itemX.getItemsId() == items.getItemsId()) {
					
					for(ProductUnit pu:items.getProductEntity().getProductUnit()) {
						if (pu.getIsBaseUnit()) {
							itemX.getDeliverList().get(0).setBaseUnitCodeLookup(pu.getUnitCodeLookup());
						}
						if (pu.getUnitCodeLookup().getLookupKey().equals(itemX.getDeliverList().get(0).getUnitCodeLookup().getLookupKey())) {
							itemX.getDeliverList().get(0).setBaseBruto(itemX.getDeliverList().get(0).getBruto() * pu.getConversionValue());							
						}
					}
					itemX.getDeliverList().get(0).setCreatedBy(loginContextSession.getUserLogin().getUserId());
					itemX.getDeliverList().get(0).setCreatedDate(new Date());					
					items.setDeliverList(itemX.getDeliverList());
					
					StocksOut stocksOut = new StocksOut();
					stocksOut.setSalesOrderEntity(sales);
					stocksOut.setProductEntity(items.getProductEntity());
					stocksOut.setWarehouseEntity(warehouseManager.getWarehouseById(warehouseId));
					stocksOut.setProdQuantity(itemX.getDeliverList().get(0).getBruto());
					stocksOut.setCreatedBy(loginContextSession.getUserLogin().getUserId());
					stocksOut.setCreatedDate(new Date());
					stocksOut.setSalesStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
					stocksOutList.add(stocksOut);
					
					
					Stocks s = items.getStocksEntity();
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
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return "redirect:/warehouse/dashboard/id/" + warehouseDashboard.getSelectedWarehouse();
	}
	
	@RequestMapping(value = "/receipt/generate/{selectedPo}/{itemId}", method = RequestMethod.GET)
	public ModelAndView receiptGenerate(Locale locale, Model model, @PathVariable String selectedPo, @PathVariable int itemId,
			HttpServletResponse response) throws JRException {
		logger.info("[poGenerate] " + "selectedPo: " + selectedPo);

		ModelAndView mav = null;
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		PurchaseOrder po = poManager.getPurchaseOrderById(Integer.parseInt(selectedPo));

		JRDataSource ds = reportManager.generateReportDS_PurchaseOrder(po);

		parameterMap.put("datasource", ds);
		mav = new ModelAndView("po_receipt_pdf", parameterMap);

		return mav;
	}
	
	@RequestMapping(value = "/deliver/generate/{selectedSo}/{itemId}", method = RequestMethod.GET)
	public ModelAndView deliverGenerate(Locale locale, Model model, @PathVariable String selectedSo, @PathVariable int itemId,
			HttpServletResponse response) throws JRException {
		logger.info("[poGenerate] " + "selectedSo: " + selectedSo);

		ModelAndView mav = null;
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		SalesOrder so = salesManager.getSalesOrderById(Integer.parseInt(selectedSo));

		JRDataSource ds = reportManager.generateReportDS_SalesOrder(so);

		parameterMap.put("datasource", ds);
		mav = new ModelAndView("so_deliver_pdf", parameterMap);

		return mav;
	}
}
