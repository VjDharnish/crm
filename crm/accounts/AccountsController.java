package com.dharun.crm.accounts;

import java.util.HashMap;
import java.util.List;

public class AccountsController implements AccountsControllerCallBack,AccountsControllerModelCallBack {
	private AccountsViewCallBack accountsView;
	private AccountsModelCallBack accountsModel; 
	public AccountsController(AccountsViewCallBack accountsView) {
		this.accountsView =accountsView;
		this.accountsModel = new AccountsModel(this);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void checkAccounts(String username, String phoneNumber) {
		accountsModel.checkAccounts(username ,phoneNumber);
		
	}
	@Override
	public void invalidAccounts(String errMsg) {
		accountsView.invalidAccounts(errMsg);
		
	}
	@Override
	public void verifedAccounts() {
		accountsView.verifedAccounts();
		
	}
	@Override
	public void accountOptions(char option) {
		switch(option) {
		case '1':
			HashMap<String,String> leads  = accountsModel.getLeads();
			if(leads.isEmpty()) {
				accountsView.noLeads();
			}
			else {
				accountsView.showLeads(leads);
			}
			break;
		case '2':
			List<List> contacts = accountsModel.getContact();
			accountsView.showContacts(contacts);
			
			break;
		case '3':
			accountsView.backToLogin();
			break;
		default:
			accountsView.invalidOption("Invalid Option");
			break;
			
		
	}
	

}
	@Override
	public void callLead(String key) {
		accountsModel.callLead(key);
		
	}
}