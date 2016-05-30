package com.tkbaru.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tkbaru.model.LoginContext;
import com.tkbaru.model.Price;
import com.tkbaru.model.chart.FlotBarChart;
import com.tkbaru.service.PriceService;
import com.tkbaru.service.PurchaseOrderService;
import com.tkbaru.service.SalesOrderService;

@Controller
@RequestMapping(value="/dashboard")
public class DashboardController {
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	PriceService priceManager;
	
	@Autowired
	PurchaseOrderService purchaseOrderManager;
	
	@Autowired
	SalesOrderService salesOrderManager;
	
	@Autowired
	private LoginContext loginContextSession;
	
	@RequestMapping(method = RequestMethod.GET)
	public String dashboard(Locale locale, Model model) {
		logger.info("[dashboard] " + "Locale: " + locale.toString());

		List<Price> priceList = priceManager.getLatestRetailPrice(1);
		int POPaymentDue = purchaseOrderManager.getCountPaymentDue();
		
		model.addAttribute("PriceList", priceList);
		model.addAttribute("countPaymentDue", POPaymentDue);
		model.addAttribute("loginContext", loginContextSession);
		
		return "dashboard";
	}
	
	@RequestMapping(value = "/get/sales", method = RequestMethod.GET)
	public @ResponseBody FlotBarChart retrieveSalesData(Locale locale, Model model) {
		logger.info("[retrieveSalesData] " + "Locale: " + locale.toString());
		
		FlotBarChart f = new FlotBarChart();

		Long[] arrLong = new Long[] { Long.valueOf("1355040000000"), (long) 2000};
		
		
		ArrayList y = new ArrayList();
		ArrayList x = new ArrayList();
		x.add(arrLong);
		
		y.add(x);
		f.setData(y);
		
		/*
		
		HashMap<String, String> p2 = new HashMap<String, String>();
		p2.put("1355040000000", "2000");
		//p.put("1355223600000", "3000");
		//p.put("1355306400000", "4000");
		//p.put("1355487300000", "5000");
		//p.put("1355571900000", "6000");
		ArrayList s = new ArrayList();
		
		s.add(p);
		s.add(p2);
		f.setData2(s);
		*/
		
		return f;
	}
	
}
