package com.dharun.crm.user;

import java.util.List;

public interface UserViewCallBack {

	void invalidCredentials(String string);

	void invalidOption(String errMsg);

	void addedSuccesfull(String string);

	void callReceived(List<Integer> call, String username, String phoneNumber);

	void addDetails(List<Integer> call, String username, String phoneNumber);

	void rejected();

	void contactAdded(float monthlyIntrest);

	void noCalls(String noCall);

	void getContact(List contact);

}
