package com.dharun.crm.user;

import java.util.List;

public interface UserControllerModelCallBack {

	void invalidOption(String errMsg);

	void addedSuccesfull();

	void callReceived(List<Integer> call, String username, String phoneNumber);

	void contactAdded(float monthlyIntrest);

	void noCalls(String string);

	void showContact(List contact);

	void invalidCredentials(String string);

}
