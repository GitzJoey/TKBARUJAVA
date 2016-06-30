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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tkbaru.common.Constants;
import com.tkbaru.model.Calendar;
import com.tkbaru.model.LoginContext;
import com.tkbaru.service.CalendarService;

@Controller
@RequestMapping("/user/calendar")
public class CalendarController {
	private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);

	@Autowired
	CalendarService calendarManager;
	
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
	public String calendarPageLoad(Locale locale, Model model) {
		logger.info("[calendarPageLoad] " + "");
		
		model.addAttribute("calendarList", calendarManager.getAllCalendarByUserId(loginContextSession.getUserLogin().getUserId()));
		model.addAttribute("id", loginContextSession.getUserLogin().getUserId());
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_PAGELOAD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_CALENDAR;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCalendar(Locale locale, Model model) {
		logger.info("[addCalendar] " + "");
		
		model.addAttribute("calendarForm", new Calendar());

		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_ADD);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_CALENDAR;
	}

	@RequestMapping(value = "/edit/{selectedId}", method = RequestMethod.GET)
	public String editCalendar(Locale locale, Model model, @PathVariable Integer selectedId) {
		logger.info("[editCalendar] " + "selectedId: "  + selectedId);
		
		Calendar c = calendarManager.getEventsById(selectedId);
		
		model.addAttribute("calendarForm", c);
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT, loginContextSession);
		model.addAttribute(Constants.PAGEMODE, Constants.PAGEMODE_EDIT);
		model.addAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		model.addAttribute(Constants.PAGE_TITLE, "");
		
		return Constants.JSPPAGE_CALENDAR;
	}

	@RequestMapping(value = "/delete/{selectedId}", method = RequestMethod.GET)
	public String deleteCaledar(Locale locale, Model model, @PathVariable Integer selectedId, RedirectAttributes redirectAttributes) {
		logger.info("[deleteCaledar] " + "selectedId: " + selectedId);
		
		calendarManager.deleteCalendar(loginContextSession.getUserLogin().getUserId(), selectedId);
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_DELETE);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/user/calendar";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveCalendar(Locale locale, Model model, @ModelAttribute("calendarForm") Calendar cal, RedirectAttributes redirectAttributes) {	

		if (cal.getCalendarId() == null) { 
			logger.info("[saveCalendar] " + "addCalendar: " + cal.toString());
			cal.setUserId(loginContextSession.getUserLogin().getUserId());
			cal.setCreatedBy(loginContextSession.getUserLogin().getUserId());
			cal.setCreatedDate(new Date());
			calendarManager.addCalendar(loginContextSession.getUserLogin().getUserId(), cal);
		} else {
			logger.info("[saveCalendar] " + "editCalendar: " + cal.toString());
			cal.setUpdatedBy(loginContextSession.getUserLogin().getUserId());
			cal.setUpdatedDate(new Date());			
			calendarManager.editCalendar(loginContextSession.getUserLogin().getUserId(), cal);
		}
		
		redirectAttributes.addFlashAttribute(Constants.PAGEMODE, Constants.PAGEMODE_LIST);
		redirectAttributes.addFlashAttribute(Constants.ERRORFLAG, Constants.ERRORFLAG_HIDE);
		
		return "redirect:/user/calendar";
	}

	@RequestMapping(value = "/get/cal", method = RequestMethod.GET)
	public @ResponseBody List<Calendar> getCalendar(@RequestParam("userId") int userId) {
		logger.info("[getCalendar] " + "userId: " + userId);

		List<Calendar> calList = new ArrayList<Calendar>();
		calList = calendarManager.getAllCalendarByUserId(userId);
		
		return calList;
	}

}
