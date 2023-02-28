package com.dharun.crm.accounts;

import java.util.HashMap;
import java.util.List;

public interface AccountsModelCallBack {

	void checkAccounts(String username, String phoneNumber);

	HashMap<String, String> getLeads();

	void callLead(String key);

	List<List> getContact();

}
