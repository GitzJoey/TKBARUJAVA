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
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Price;
import com.tkbaru.model.Product;
import com.tkbaru.service.PriceLevelService;
import com.tkbaru.service.PriceService;
import com.tkbaru.service.ProductService;

@Controller
@RequestMapping("/price")
public class PriceController {
	private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
	
	@Autowired
	private LoginContext loginContextSession;
	
	@Autowired
	private ProductService productManager;
	
	@Autowired
	private PriceService priceManager;
	
	@Autowired
	private PriceLevelService priceLevelManager;
	
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
		
		List<Product> productList = productManager.getAllProduct();
		model.addAttribute("productList", productList);
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
	
	@RequestMapping(value = "/addprice", method = RequestMethod.POST)
	public String reviseAddItems(Locale locale, Model model, @ModelAttribute("todayPriceForm") Product todayPriceForm) {
		logger.info("[soAddItems] " + "varId: ");
		Price i = new Price();
		i.setCreatedDate(new Date());
		i.setCreatedBy(loginContextSession.getUserLogin().getUserId());
		if(todayPriceForm.getPrice()==null){
			todayPriceForm.setPrice(new ArrayList<Price>());
		}
		todayPriceForm.getPrice().add(i);
		model.addAttribute("priceLevelDDL", priceLevelManager.getAllPriceLevel());
		model.addAttribute("todayPriceForm", todayPriceForm);
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_TODAYPRICE;
	}
	
	@RequestMapping(value = "/removeprice/{varId}", method = RequestMethod.POST)
	public String poRemoveItems(Locale locale, Model model,@ModelAttribute("todayPriceForm") Product todayPriceForm, @PathVariable String varId) {
		logger.info("[poRemoveItems] " + "varId: " + varId);
		
		List<Price> iLNew = new ArrayList<Price>();
		for (int x = 0; x < todayPriceForm.getPrice().size(); x++) {
			if (x == Integer.parseInt(varId))
				continue;
			iLNew.add(todayPriceForm.getPrice().get(x));
		}

		todayPriceForm.setPrice(iLNew);
		
		
		model.addAttribute("todayPriceForm", todayPriceForm);
		model.addAttribute("priceLevelDDL", priceLevelManager.getAllPriceLevel());
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return Constants.JSPPAGE_TODAYPRICE;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveProduct(Locale locale, Model model, @ModelAttribute("todayPriceForm") Product todayPriceForm, RedirectAttributes redirectAttributes) {
		
		Product product = productManager.getProductById(todayPriceForm.getProductId());

		if (todayPriceForm.getPrice() != null && todayPriceForm.getPrice().size() > 0) {
			for(Price pu:todayPriceForm.getPrice()) {
				if (pu.getProduct() == null) {
					pu.setProductId(todayPriceForm.getProductId());
					pu.setProduct(todayPriceForm);
				}
			}			
		}
		
		product.setPrice(todayPriceForm.getPrice());
		
		if (todayPriceForm.getProductId() == 0) { 
			logger.info("[saveProduct] " + "addProduct: " + todayPriceForm.toString());
			productManager.addProduct(product); 
		} else {
			logger.info("[saveProduct] " + "editProduct: " + todayPriceForm.toString());
			productManager.editProduct(product); 
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/price/todayprice";
	}

}
