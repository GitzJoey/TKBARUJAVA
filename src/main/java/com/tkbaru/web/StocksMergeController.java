package com.tkbaru.web;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		
		
		model.addAttribute("stocksList", stocksList);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_STOCKS_MERGE;
	}
}
