package com.tkbaru.web;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
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
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Price;
import com.tkbaru.model.PriceLevel;
import com.tkbaru.model.Stocks;
import com.tkbaru.model.TodayPrice;
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
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		dateFormat.setLenient(true);
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	private List<Price> generatePriceList(Date forDate) {
		List<Price> pList = new ArrayList<Price>();
		
		List<PriceLevel> priceLevelList = priceLevelManager.getAllPriceLevel(); 

		if (priceLevelList.size() == 0) return null;
		
		for(PriceLevel pl:priceLevelList) {
			Price p = new Price();
			p.setPriceLevelEntity(priceLevelManager.getPriceLevelById(pl.getPriceLevelId()));
			p.setPriceStatusLookup(lookupManager.getLookupByKey("L001_A"));
			p.setInputDate(forDate);
			p.setMarketPrice(new BigDecimal(0));
			p.setPrice(new BigDecimal(0));
			
			pList.add(p);
		}
		
		return pList;
	}
	
	@RequestMapping(value="/todayprice", method = RequestMethod.GET)
	public String todayPricePageLoad(Locale locale, Model model) {
		logger.info("[todayPricePageLoad] " + "");
		
		List<Stocks> stocksList = stocksManager.getAllStocks();
				
		model.addAttribute("stocksList", stocksList);
		model.addAttribute("priceLevelList", priceLevelManager.getAllPriceLevel());
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_TODAYPRICE;
	}

	@RequestMapping(value="/updateprice/{inputDate}", method = RequestMethod.GET)
	public String updatePrice(Locale locale, Model model , @PathVariable String inputDate) throws ParseException {
		logger.info("[updatePrice] " + "inputDate: " + inputDate);

		Date d = new SimpleDateFormat("dd-MM-yyyy_HH:mm").parse(inputDate);

		boolean priceInputed = priceManager.checkExistPriceForDate(d);		

		List<Stocks> stocksList = stocksManager.getAllStocks();
		
		if (priceInputed) {
			model.addAttribute("stocksList", stocksList);
			model.addAttribute("priceLevelList", priceLevelManager.getAllPriceLevel());
			
			model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
			model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
			model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_SHOW);
			model.addAttribute("errorMessageText", "Price already inputted");
			
			return Constants.JSPPAGE_TODAYPRICE;
		}
		
		for(Stocks s:stocksList) {
			s.setPriceList(generatePriceList(d));
		}
		
		model.addAttribute("todayPriceForm", new TodayPrice(d, stocksList));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_TODAYPRICE;
	}

	@RequestMapping(value="/saveprice/s/{stocksId}/save", method = RequestMethod.POST)
	public String savePricePerStock(Locale locale, Model model , @ModelAttribute("todayPriceForm") TodayPrice todayPrice, @PathVariable int stocksId, RedirectAttributes redirectAttributes) {
		logger.info("[savePricePerStock] " + "stocksId: " + stocksId);
		
		for (Stocks s:todayPrice.getStocksList()) {
			if (s.getStocksId() != stocksId) continue;
			for (Price p:s.getPriceList()) {
				p.setPriceStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
				p.setCreatedBy(loginContextSession.getUserLogin().getUserId());
				p.setCreatedDate(new Date());
				p.setStocksEntity(s);
				p.setPriceStatusLookup(lookupManager.getLookupByKey("L001_A"));
				p.setPriceLevelEntity(priceLevelManager.getPriceLevelById(p.getPriceLevelEntity().getPriceLevelId()));
			}					
			priceManager.addMultiplePrice(s.getPriceList());
		}
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/price/todayprice";
	}
	
	@RequestMapping(value="/saveprice", method = RequestMethod.POST)
	public String savePrice(Locale locale, Model model , @ModelAttribute("todayPriceForm") TodayPrice todayPrice, RedirectAttributes redirectAttributes) {
		logger.info("[savePrice] " + "");
		
		for (Stocks s:todayPrice.getStocksList()) {
			for (Price p:s.getPriceList()) {
				p.setPriceStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
				p.setCreatedBy(loginContextSession.getUserLogin().getUserId());
				p.setCreatedDate(new Date());
				p.setStocksEntity(s);
				p.setPriceStatusLookup(lookupManager.getLookupByKey("L001_A"));
				p.setPriceLevelEntity(priceLevelManager.getPriceLevelById(p.getPriceLevelEntity().getPriceLevelId()));
			}					
			priceManager.addMultiplePrice(s.getPriceList());
		}
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/price/todayprice";
	}
}