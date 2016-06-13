package com.tkbaru.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;

@Controller
@RequestMapping("/warehouse/stocks/merge")
public class StocksMergeController {
	private static final Logger logger = LoggerFactory.getLogger(StocksMergeController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String mergePageLoad(Locale locale, Model model) {
		logger.info("[mergePageLoad] : " + "");
		
		return Constants.JSPPAGE_STOCKS_MERGE;
	}
}
