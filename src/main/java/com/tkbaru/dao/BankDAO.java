package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.Bank;

public interface BankDAO {
	public void batchAddBank(List<Bank> bankList);
}
