package com.tkbaru.web;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Stocks;
import com.tkbaru.service.StocksService;

@Controller
@RequestMapping("/warehouse/stocks/merge")
public class StocksMergeController {
	private static final Logger logger = LoggerFactory.getLogger(StocksMergeController.class);

	@Autowired
	StocksService stocksManager;
	
	@Autowired
	private LoginContext loginContextSession;

	@RequestMapping(method = RequestMethod.GET)
	public String mergePageLoad(Locale locale, Model model) {
		logger.info("[mergePageLoad] " + "");

		List<Stocks> stocksList = stocksManager.getAllStocks();
		
		
		model.addAttribute("stocksFromList", stocksList);
		model.addAttribute("stocksToList", stocksList);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_STOCKS_MERGE;
	}

	@RequestMapping(value = "/fs/{fromStocksId}/wf/{fromWarehouseId}/ts/{toStocksId}/wt/{toWarehouseId}", method = RequestMethod.GET)
	public String mergeStocks(Locale locale, Model model, RedirectAttributes redirectAttributes, 
			@PathVariable Integer fromStocksId, 
			@PathVariable Integer toStocksId,
			@PathVariable Integer fromWarehouseId,
			@PathVariable Integer toWarehouseId) {
		logger.info("[mergeStocks] " + "fromStocksId: " + fromStocksId + ", toStocksId: " + toStocksId);

		stocksManager.mergeStocks(fromStocksId, toStocksId, fromWarehouseId, toWarehouseId, loginContextSession.getUserLogin().getUserId(), loginContextSession.getUserLogin().getStoreId());
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		redirectAttributes.addFlashAttribute(Constants.PAGE_TITLE, "");

		return "redirect:/warehouse/stocks/merge";
	}

}
