package com.dharun.crm.accounts;

import java.util.HashMap;
import java.util.List;

import com.dharun.crm.repository.CrmRepository;

public class AccountsModel implements AccountsModelCallBack {
	private AccountsControllerModelCallBack accountsController;
	public AccountsModel(AccountsControllerModelCallBack accountsController) {
		this.accountsController = accountsController;
	}
	@Override
	public void checkAccounts(String username, String phoneNumber) {
		boolean  account = CrmRepository.getInstance().verifyAccounts(username,phoneNumber);
		if(!account) {
			accountsController.invalidAccounts("Invalid ACCOUNT Credential");
		}
		else {
		CrmRepository.getInstance().updateContactsProfit();
			accountsController.verifedAccounts();
		}
	}
	@Override
	public HashMap<String, String> getLeads() {
		HashMap<String,String> leads = CrmRepository.getInstance().getLeads();
		return leads;
	}
	@Override
	public void callLead(String key) {
		CrmRepository.getInstance().callLead(key);
		
	}
	@Override
	public List<List> getContact() {
		List<List> contacts = CrmRepository.getInstance().getContacts();
		return contacts;
		
	}

}
