package com.tkbaru.web;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Supplier;
import com.tkbaru.model.TruckVendor;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.TruckVendorService;

@Controller
@RequestMapping("/master/vendor/trucking")
public class TruckVendorController {
	private static final Logger logger = LoggerFactory.getLogger(TruckVendorController.class);
	
	@Autowired
	TruckVendorService TruckVendorManager;
	
	@Autowired
	LookupService lookupManager;
	
	@Autowired
	private LoginContext loginContextSession;
	
	@RequestMapping(method=RequestMethod.GET)
	public String truckVendorPageLoad(Locale locale, Model model) {		
		logger.info("[truckVendorPageLoad] " + "");
		
		List<TruckVendor> truckVendor = TruckVendorManager.getAllTruckVendor();
		
		model.addAttribute("TruckVendorList", truckVendor);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_TRUCK_VENDOR;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addTruckVendor(Locale locale, Model model) {
		logger.info("[addTruckVendor] : " + "");
		
		model.addAttribute("truckVendorForm", new TruckVendor());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_TRUCK_VENDOR;
	}
	

	@RequestMapping(value="/edit/{selectedId}", method=RequestMethod.GET)
	public String editStore(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[editTruckVendor] " + "selectedId = " + selectedId);
		
		TruckVendor selectedTruckVendor = TruckVendorManager.getTruckVendorById(selectedId);
		
		logger.info("[editTruckVendor] " + "selectedStore = " + selectedTruckVendor.toString());
		
		model.addAttribute("truckVendorForm", selectedTruckVendor);
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		model.addAttribute("bankDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_BANK));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_TRUCK_VENDOR;
	}

	@RequestMapping(value="/delete/{selectedId}", method=RequestMethod.GET)
	public String deleteStore(Locale locale, Model model, @PathVariable Integer selectedId, RedirectAttributes redirectAttributes) {
		logger.info("[deleteStore] " + "selectedId = " + selectedId);
		
		TruckVendorManager.deleteTruckVendor(selectedId);
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/master/vendor/trucking";
	}
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveTruckVendor(Locale locale, Model model, @ModelAttribute("truckVendorForm") TruckVendor truckVendor, RedirectAttributes redirectAttributes) {
		
		if (truckVendor.getVendorTruckId() == null) {
			logger.info("[saveStore] " + "addStore: " + truckVendor.toString());
			TruckVendorManager.addTruckVendor(truckVendor); 
		} else { 
			logger.info("[saveStore] " + "editStore: " + truckVendor.toString());
			TruckVendorManager.editTruckVendor(truckVendor);
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/master/vendor/trucking";
	}
	
}
