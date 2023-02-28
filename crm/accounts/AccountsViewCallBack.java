package com.dharun.crm.accounts;

import java.util.HashMap;
import java.util.List;

public interface AccountsViewCallBack {

	void invalidAccounts(String errMsg);

	void verifedAccounts();

	void noLeads();

	void showLeads(HashMap<String, String> leads);

	void invalidOption(String string);

	void backToLogin();

	void showContacts(List<List> contacts);



}
