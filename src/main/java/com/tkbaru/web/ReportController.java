package com.tkbaru.web;

import java.util.HashMap;
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
import com.tkbaru.model.LoginContext;
import com.tkbaru.service.ReportService;
import com.tkbaru.service.StocksService;

@Controller
@RequestMapping("/report")
public class ReportController {
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	ReportService reportManager;
	
	@Autowired
	StocksService stocksManager;
	
	@Autowired
	private LoginContext loginContextSession;

	@RequestMapping(value="/id/{reportid}", method = RequestMethod.GET)
	public String selectedReportPage(Locale locale, Model model, @PathVariable("reportid") String reportId) {
		logger.info("[selectedReportPage] " + "id: " + reportId);

		model.addAttribute("reportGroup", "");
		model.addAttribute("reportId", reportId);

		switch (reportId.toUpperCase()) {
			case "RPTMNTR":
				model.addAttribute("stocksList", stocksManager.getAllStocks());
				break;
			default:
				break;
		}
		
		model.addAttribute(Constants.SESSIONKEY_LOGINCONTEXT,loginContextSession);
		model.addAttribute(Constants.PAGEMODE,Constants.PAGEMODE_LIST);
		model.addAttribute(Constants.ERRORFLAG,Constants.ERRORFLAG_HIDE);
		
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
		
		switch (outputType.toLowerCase()) {
			case Constants.JASPERREPORT_OUTPUTTYPE_HTML: beanOutputType = "_html"; break;
			case Constants.JASPERREPORT_OUTPUTTYPE_PDF: beanOutputType = "_pdf"; break;
			case Constants.JASPERREPORT_OUTPUTTYPE_CSV: beanOutputType = "_csv"; break;
			case Constants.JASPERREPORT_OUTPUTTYPE_XLS: beanOutputType = "_xls"; break;
			default : break;
		}
		
		switch (reportName.toUpperCase()) {
			case "USER":
				datasource = reportManager.generateReportUserDS(); 					
				parameterMap.put("datasource", datasource);
				
				mav = new ModelAndView(reportName + beanOutputType, parameterMap); 
				break;
			default:
				break;
		}
		
		logger.info("[selectedReportPage] " + "mav: " + mav.toString());
		
		return mav;
	}
}
