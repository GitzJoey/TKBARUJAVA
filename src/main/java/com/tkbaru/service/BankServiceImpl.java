package com.tkbaru.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tkbaru.dao.BankDAO;
import com.tkbaru.model.Bank;

@Service
public class BankServiceImpl implements BankService {
	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	BankDAO bankDAO;
	
	@Override
	@Transactional
	public boolean bankUpload(Bank bank) {
		logger.info("[bankUpload] " + "");
		
		try {
			String path = servletContext.getRealPath("/") + "resources\\file\\bank\\";

			File f = new File(bank.getFileBinary().getOriginalFilename());
			bank.getFileBinary().transferTo(f);
			
			bankDAO.batchAddBank(processingFile_BCA(f));
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return false;
	}
	
	@SuppressWarnings("deprecation")
	private List<Bank> processingFile_BCA(File f) {
		List<Bank> result = new ArrayList<Bank>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			String sCurrentLine;
			
			String accNum = "";
			String accHolderName = "";
			String currCode = "";
			String begBal = "";
			String endBal = "";
			String db = "";
			String cr = "";

			NumberFormat format = NumberFormat.getInstance(Locale.US);
	        
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains("No. Rekening")) {
					accNum = sCurrentLine.split(",")[2].substring(1);
					logger.info(accNum);
					continue;
				} else if (sCurrentLine.contains("Nama")) {
					accHolderName = sCurrentLine.split(",")[2];
					logger.info(accHolderName);
					continue;					
				} else if (sCurrentLine.contains("Mata Uang")) {
					currCode = sCurrentLine.split(",")[2];
					logger.info(currCode);
					continue;										
				} else if (sCurrentLine.contains("Saldo Awal")) {
					begBal = sCurrentLine.split(",")[2];
					logger.info(begBal);
					continue;															
				} else if (sCurrentLine.contains("Kredit")) {
					db = sCurrentLine.split(",")[2];
					logger.info(db);
					continue;															
				} else if (sCurrentLine.contains("Debet")) {
					cr = sCurrentLine.split(",")[2];
					logger.info(cr);
					continue;					
				} else if (sCurrentLine.contains("Saldo Akhir")) {
					endBal = sCurrentLine.split(",")[2];
					logger.info(endBal);
					continue;	
				} else if (sCurrentLine.contains("Tanggal,Keterangan,Cabang,Jumlah,,Saldo")) {
					continue;
				} else if (sCurrentLine.length() == 0) {
					continue;
				} else {
					
				}
				
				Bank b = new Bank();
			
				String[] splitted = sCurrentLine.split(",");
				b.setTransactionDate(new Date(Calendar.getInstance().get(Calendar.YEAR), Integer.parseInt(splitted[0].substring(1).split("/")[0]), Integer.parseInt(splitted[0].substring(1).split("/")[1])));
				b.setTransactionDesc(splitted[1]);
				b.setTransactionBranch(splitted[2].substring(1));
				b.setTransactionAmount(format.parse(splitted[3]).longValue());
				b.setTransactionDirection(splitted[4]);
				b.setTransactionBalance(Double.valueOf(splitted[5]).longValue());
				
				b.setAccNumber(accNum.trim());
				b.setAccHolderName(accHolderName.trim());
				b.setCurrencyCode(currCode);

				result.add(b);
			}
			
			for (Bank b:result) {
				b.setBeginningBalance(format.parse(begBal).longValue());
				b.setDebitTotalAmount(format.parse(db).longValue());
				b.setCreditTotalAmount(format.parse(cr).longValue());
				b.setEndingBalance(format.parse(endBal).longValue());
				
				logger.info(b.toString());
			}			
		} catch(IOException err) {
			logger.info("[processingFile] " + "IOException: " + err.getMessage());
		} catch (Exception e) {
			logger.info("[processingFile] " + "Exception: " + e.getMessage());
		}
		
		return result;
	}

}
