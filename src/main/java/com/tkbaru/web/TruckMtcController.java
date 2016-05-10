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
import com.tkbaru.model.TruckMaintenance;
import com.tkbaru.service.LookupService;
import com.tkbaru.service.TruckMtcService;
import com.tkbaru.service.TruckService;

@Controller
@RequestMapping("/master/truck/maintenance")
public class TruckMtcController {
	private static final Logger logger = LoggerFactory.getLogger(TruckMtcController.class);

	@Autowired
	TruckMtcService truckMtcManager;
	
	@Autowired
	TruckService truckManager;
	
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

	@RequestMapping(value="/tr/{selectedTruckId}",method = RequestMethod.GET)
	public String mtcPageLoad(Locale locale, Model model, @PathVariable Integer selectedTruckId) {
		logger.info("[mtcPageLoad] : " + "");
		
		Truck truck = truckManager.getTruckById(selectedTruckId);

		model.addAttribute("truckId", selectedTruckId);
		model.addAttribute("truckEntity", truck);
		model.addAttribute("mtcList", truck.getTruckMaintenances());		
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_TRUCK_MTC;
	}
	
	@RequestMapping(value="/tr/{truckId}/add", method = RequestMethod.GET)
	public String addTruckMaintenance(Locale locale, Model model, @PathVariable int truckId) {
		logger.info("[addTruckMaintenance] : " + "");
		
		TruckMaintenance mtc = new TruckMaintenance();
		
		mtc.setTruckId(truckId);
		
		model.addAttribute("mtcForm", mtc);
		model.addAttribute("maintenanceTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_TRUCK_MTC_TYPE));
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
	
		return Constants.JSPPAGE_TRUCK_MTC;
	}
	
	@RequestMapping(value = "/tr/{truckId}/edit/{selectedId}", method = RequestMethod.GET)
	public String editTruckMaintenance(Locale locale, Model model, @PathVariable Integer truckId, @PathVariable Integer selectedId) {
		logger.info("[editTruckMaintenance] " + "truckId:" + truckId + "selectedId = " + selectedId);
			
		TruckMaintenance selectedMtc = truckMtcManager.getTruckMaintenanceById(selectedId);
		
		model.addAttribute("mtcForm", selectedMtc);
		model.addAttribute("maintenanceTypeDDL", lookupManager.getLookupByCategory(Constants.LOOKUPCATEGORY_TRUCK_MTC_TYPE));
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");

		return Constants.JSPPAGE_TRUCK_MTC;
	}
	
	@RequestMapping(value = "/tr/{truckId}/delete/{selectedId}", method = RequestMethod.GET)
	public String deleteTruckMaintenance(Locale locale, Model model, @PathVariable Integer truckId, @PathVariable Integer selectedId) {
		logger.info("[deleteTruckMaintenance] : " + "truckId:" + truckId + ", selectedId = " + selectedId);

		truckMtcManager.deleteTruckMaintenance(selectedId);
		
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/master/truck/maintenance/tr/" + truckId;
	}
	
	@RequestMapping(value="/tr/{truckId}/save", method = RequestMethod.POST)
	public String saveTruckMaintenance(Locale locale, Model model, @ModelAttribute("mtcForm") TruckMaintenance mtc, RedirectAttributes redirectAttributes, @PathVariable Integer truckId) {	
		
		if (mtc.getTruckMaintenanceId() == null) {
			mtc.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			mtc.setCreatedDate(new Date());
			logger.info("[saveTruckMaintenance] " + "addTruckMaintenance: " + mtc.toString());
			truckMtcManager.addTruckMaintenance(mtc); 
		} else {
			mtc.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
			mtc.setUpdatedDate(new Date());
			logger.info("[saveTruckMaintenance] " + "editTruckMaintenance: " + mtc.toString());
			truckMtcManager.editTruckMaintenance(mtc); 
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);

		return "redirect:/master/truck/maintenance/tr/" + mtc.getTruckId();
	}
}