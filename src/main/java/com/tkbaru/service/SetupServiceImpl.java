package com.tkbaru.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkbaru.dao.SetupDAO;

@Service
public class SetupServiceImpl implements SetupService {

	@Autowired
	SetupDAO setupDAO;
	
	@Autowired
	FunctionService functionManager;
	
	@Autowired
	RoleService roleManager;
	
	@Autowired
	UserService userManager;
	
	@Autowired
	CustomerService customerManager;
	
	@Autowired
	StoreService storeManager;
	
	@Autowired
	LookupService lookupManager;
	
	@Override
	public boolean checkDBConnection() {
		
		return setupDAO.checkDBConnection();
	}

	private List<String> checkInitDataValidity() {
		List<String> isNotValid = new ArrayList<String>();

		//Check Store
		if (storeManager.getAllStore().size() == 0) {
			isNotValid.add("Store");
		}
		
		//Check Functions
		if (functionManager.getAllFunctions().size() == 0) {
			isNotValid.add("Functions");
		}
		
		//Check Role
		if (roleManager.getAllRole().size() == 0) {
			isNotValid.add("Role");
		}
		
		//Check User
		if (!userManager.checkUserTableHasData()) {
			isNotValid.add("User");
		}
		
		//Check Default Customer
		if (customerManager.getAllCustomer().size() == 0) {
			isNotValid.add("Customer");
		}
		
		//Check Lookup
		if (lookupManager.getAllLookup().size() == 0) {
			isNotValid.add("Lookup");
		}
		
		return isNotValid;
	}

	@Override
	public boolean generateInitData() {
		List<String> errorModule = checkInitDataValidity();

		if (errorModule.isEmpty()) return true;
		
		for (String s: errorModule) {
			if (s.equalsIgnoreCase("Lookup")) {
				lookupManager.generateDefaultLookup();
			} else if(s.equalsIgnoreCase("Store")) {
				storeManager.generateDefaultStore();
			} else if (s.equalsIgnoreCase("Functions")) {
				functionManager.generateDefaultFunctions();
			} else if (s.equalsIgnoreCase("Role")) {
				roleManager.generateDefaultRoles();
			} else if (s.equalsIgnoreCase("User")) {
				userManager.generateDefaultUser();
			} else if (s.equalsIgnoreCase("Customer")) {
				customerManager.generateDefaultCustomer();
			} else if (s.equalsIgnoreCase("Lookup")) {
				lookupManager.generateDefaultLookup();
			} else if (s.equalsIgnoreCase("")) {
				
			} else {
				
			}
		}
		
		return false;
	}

}
