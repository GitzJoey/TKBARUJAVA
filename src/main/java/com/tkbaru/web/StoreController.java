package com.tkbaru.web;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Customer;
import com.tkbaru.model.Store;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.StoreService;

@Controller
@RequestMapping("/admin/store")
public class StoreController {
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	
	@Autowired
	StoreService storeManager;
	
	@Autowired
	LookupService lookupManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String storePageLoad(Locale locale, Model model) {		
		logger.info("[storePageLoad] " + "");
		
		List<Store> sList = storeManager.getAllStore();
		
		model.addAttribute("storeList", sList);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_STORE;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addStore(Locale locale, Model model) {
		logger.info("[addStore] : " + "");
		
		model.addAttribute("storeForm", new Store());
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_STORE;
	}

	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String editCustomer(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[editStore] " + "selectedId = " + selectedId);
		
		Store selectedStore = storeManager.getStoreById(selectedId);
		
		logger.info("[editStore] " + "selectedStore = " + selectedStore.toString());
		
		model.addAttribute("storeForm", selectedStore);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_STORE;
	}

	@RequestMapping(value = "/delete/{selectedId}", method = RequestMethod.GET)
	public String deleteStore(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[deleteStore] " + "selectedId = " + selectedId);
		
		storeManager.deleteStore(selectedId);
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_REDIRECT_TO + Constants.JSPPAGE_ADMIN_STORE;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveStore(Locale locale, Model model, @ModelAttribute("storeForm") Store store) {	
        logger.info("[saveStore] " + store.toString());
		
		if (store.getStoreId() == 0) { storeManager.addStore(store); }
		else { storeManager.editStore(store); }
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return Constants.JSPPAGE_REDIRECT_TO + Constants.JSPPAGE_ADMIN_STORE;
	}
}
