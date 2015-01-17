package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.service.StocksService;

@Controller
@RequestMapping("/monitor/stocks")
public class StocksController {
	private static final Logger logger = LoggerFactory.getLogger(StocksController.class);
	
	@Autowired
	StocksService stocksManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String stocksPageLoad(Locale locale, Model model) {
		logger.info("[stocksPageLoad] " + "");
		
		model.addAttribute("stocksList", stocksManager.getAllStocks());
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_STOCKS;
	}
}
