package com.tkbaru.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.tkbaru.model.Truck;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.TruckService;
import com.tkbaru.service.UserService;

@Controller
@RequestMapping("/truck")
public class TruckController {
	private static final Logger logger = LoggerFactory.getLogger(TruckController.class);

	@Autowired
	TruckService truckManager;
	
	@Autowired
	UserService userManager;

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
	
	@RequestMapping(method = RequestMethod.GET)
	public String truckPageLoad(Locale locale, Model model) {
		logger.info("[truckPageLoad] : " + "");
		
		model.addAttribute("truckList", truckManager.getAllTruck());
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_TRUCK;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addTruck(Locale locale, Model model) {
		logger.info("[addTruck] : " + "");
		
		model.addAttribute("truckForm", new Truck());
		model.addAttribute("truckTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_TRUCK_TYPE));
		model.addAttribute("weightTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_WEIGHT_TYPE));
		model.addAttribute("driverDDL", userManager.getAllUserByType(""));
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_TRUCK;
	}
	
	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String editTruck(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[editTruck] " + "selectedId = " + selectedId);
			
		Truck selectedTruck = truckManager.getTruckById(selectedId);
		
		model.addAttribute("truckForm", selectedTruck);
		model.addAttribute("truckTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_TRUCK_TYPE));
		model.addAttribute("weightTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_WEIGHT_TYPE));
		model.addAttribute("driverDDL", userManager.getAllUser());
		model.addAttribute("statusDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_STATUS));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_TRUCK;
	}
	
	@RequestMapping(value = "/delete/{selectedId}", method = RequestMethod.GET)
	public String deleteTruck(Locale locale, Model model, @PathVariable Integer selectedId, RedirectAttributes redirectAttributes) {
		logger.info("[deleteTruck] : " + "selectedId = " + selectedId);

		truckManager.deleteTruck(selectedId);
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/truck";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveTruck(Locale locale, Model model, @ModelAttribute("truckForm") Truck truck, RedirectAttributes redirectAttributes) {	
		
		if (truck.getTruckId() == null) { 
			truck.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			truck.setCreatedDate(new Date());
			truck.setTruckStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
			logger.info("[saveTruck] " + "addTruck: " + truck.toString());
			truckManager.addTruck(truck); 
		} else {
			truck.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
			truck.setUpdatedDate(new Date());
			truck.setTruckStoreEntity(loginContextSession.getUserLogin().getStoreEntity());
			logger.info("[saveTruck] " + "editTruck: " + truck.toString());
			truckManager.editTruck(truck); 
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/truck";
	}

}
