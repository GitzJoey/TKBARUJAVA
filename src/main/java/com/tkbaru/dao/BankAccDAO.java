package com.tkbaru.dao;

import java.util.List;

import com.tkbaru.model.BankAccount;

public interface BankAccDAO {
	public List<BankAccount> getBankAccountByIds(int bankAccId);
}
