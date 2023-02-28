package com.dharun.crm.accounts;

public interface AccountsControllerCallBack {

	void checkAccounts(String username, String phoneNumber);

	void accountOptions(char option);

	void callLead(String key);

}
