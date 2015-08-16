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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Price;
import com.tkbaru.model.Product;
import com.tkbaru.model.Stocks;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.PriceLevelService;
import com.tkbaru.service.PriceService;
import com.tkbaru.service.ProductService;
import com.tkbaru.service.StocksService;

@Controller
@RequestMapping("/price")
public class PriceController {
	private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
	
	@Autowired
	LoginContext loginContextSession;
	
	@Autowired
	StocksService stocksManager;
	
	@Autowired
	ProductService productManager;
	
	@Autowired
	PriceService priceManager;
	
	@Autowired
	PriceLevelService priceLevelManager;
	
	@Autowired
	LookupService lookupManager;
	
	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(true);
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	@RequestMapping(value="/todayprice", method = RequestMethod.GET)
	public String todayPricePageLoad(Locale locale, Model model) {
		logger.info("[todayPricePageLoad] " + "");
		
		Date todayDate = new Date();
		
		boolean priceInputed = priceManager.checkExistPriceForDate(todayDate);		
		
		if (priceInputed) {
			
		}
		
		List<Stocks> stocksList = stocksManager.getAllStocks();
		
		model.addAttribute("stocksList", stocksList);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_TODAYPRICE;
	}
	
	@RequestMapping(value="/updateprice/{productId}", method = RequestMethod.GET)
	public String updatePrice(Locale locale, Model model ,@PathVariable int productId) {
		logger.info("[updatePrice] " + "");
		
		Product product = productManager.getProductById(productId);
		
		model.addAttribute("priceLevelDDL", priceLevelManager.getAllPriceLevel());
		model.addAttribute("todayPriceForm", product);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_TODAYPRICE;
	}

}