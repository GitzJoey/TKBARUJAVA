package com.tkbaru.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tkbaru.model.User;

@Controller
public class FragmentController {
	private static final Logger logger = LoggerFactory.getLogger(FragmentController.class);
	
	@RequestMapping(value = "/fragment/addphone.html", method = RequestMethod.GET)
	public String addPhone(@RequestParam(value="count") int addphonecount, Model model) {		
		
		model.addAttribute("userForm", new User());
		model.addAttribute("addphonecount", addphonecount);
	 
		logger.info("return phone");
		
		return "fragment/phone";
	}
}
