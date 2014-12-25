package com.tkbaru.web;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tkbaru.common.Constants;
import com.tkbaru.model.User;
import com.tkbaru.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	ReportService reportManager;
	
	@RequestMapping(value="/id/{reportid}", method = RequestMethod.GET)
	public String selectedReportPage(Locale locale, Model model, @PathVariable("reportid") String reportId) {
		logger.info("[selectedReportPage] " + "id: " + reportId);
		
		model.addAttribute("reportId", reportId);
		
		return Constants.JSPPAGE_REPORT;
	}

	@RequestMapping(value="/gen/{outputType}/{reportName}", method = RequestMethod.GET)
	public ModelAndView selectedReportPage(Locale locale, 
										Model model, 
										@PathVariable("outputType") String outputType, 
										@PathVariable("reportName") String reportName) {
		logger.info("[selectedReportPage] " + "reportName: " + reportName + ", outputType: " + outputType);

		ModelAndView mav = null;
		JRDataSource datasource = null;
		Map<String,Object> parameterMap = new HashMap<String,Object>();

		String beanOutputType = "";
		if (outputType.toLowerCase().equals(Constants.JASPERREPORT_OUTPUTTYPE_HTML.toLowerCase())) { beanOutputType = "_html"; } 
		else if (outputType.toLowerCase().equals(Constants.JASPERREPORT_OUTPUTTYPE_PDF.toLowerCase())) { beanOutputType = "_pdf"; }
		else if (outputType.toLowerCase().equals(Constants.JASPERREPORT_OUTPUTTYPE_CSV.toLowerCase())) { beanOutputType = "_csv"; }
		else if (outputType.toLowerCase().equals(Constants.JASPERREPORT_OUTPUTTYPE_PDF.toLowerCase())) { beanOutputType = "_xls"; }
		else { }
		
		if (reportName.toUpperCase().equals("USER")) {
			datasource = reportManager.generateReportUserDS(); 	
			parameterMap.put("datasource", datasource);
			
			mav = new ModelAndView(reportName + beanOutputType, parameterMap); 
		} else if (reportName.toUpperCase().equals("RPT2")) {
			
		} else {
			
		}
		
		logger.info("[selectedReportPage] " + "mav: " + mav.toString());
		
		return mav;
	}
}
