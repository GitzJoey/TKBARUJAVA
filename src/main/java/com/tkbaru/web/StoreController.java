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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;
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
	
	@Autowired
	private LoginContext loginContextSession;

	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(true);
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}

	@RequestMapping(method=RequestMethod.GET)
	public String storePageLoad(Locale locale, Model model) {		
		logger.info("[storePageLoad] " + "");
		
		List<Store> sList = storeManager.getAllStore();
		
		model.addAttribute("storeList", sList);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_STORE;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addStore(Locale locale, Model model) {
		logger.info("[addStore] : " + "");
		
		model.addAttribute("storeForm", new Store());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("ynDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_YESNOSELECTION));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_STORE;
	}

	@RequestMapping(value="/edit/{selectedId}", method=RequestMethod.GET)
	public String editStore(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[editStore] " + "selectedId = " + selectedId);
		
		Store selectedStore = storeManager.getStoreById(selectedId);
		
		logger.info("[editStore] " + "selectedStore = " + selectedStore.toString());
		
		model.addAttribute("storeForm", selectedStore);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("ynDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_YESNOSELECTION));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_STORE;
	}

	@RequestMapping(value="/delete/{selectedId}", method=RequestMethod.GET)
	public String deleteStore(Locale locale, Model model, @PathVariable Integer selectedId, RedirectAttributes redirectAttributes) {
		logger.info("[deleteStore] " + "selectedId = " + selectedId);
		
		storeManager.deleteStore(selectedId);
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/admin/store";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveStore(Locale locale, Model model, @ModelAttribute("storeForm") Store store, RedirectAttributes redirectAttributes) {	
        	
		Store defStore = storeManager.getDefaultStore();
		
		if (store.getStoreId() == null) {
			store.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			store.setCreatedDate(new Date());
	
			if (store.getIsDefaultLookup().getLookupKey().equals("L003_YES") && defStore != null) {
				storeManager.setAllStoreIsDefaultNo();
			}
			
			logger.info("[saveStore] " + "addStore: " + store.toString());
			storeManager.addStore(store); 
		} else { 
			store.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
			store.setUpdatedDate(new Date());
			
			if (store.getIsDefaultLookup().getLookupKey().equals("L003_YES") 
					&& defStore != null
					&& store.getStoreId() != defStore.getStoreId()) {
				storeManager.setAllStoreIsDefaultNo(store);
			} else {
				logger.info("[saveStore] " + "editStore: " + store.toString());
				storeManager.editStore(store); 
			}
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/admin/store";
	}
}
