package com.dharun.crm.user;

import java.util.List;

public interface UserModelCallBack {

	void checkCredentials(String username, String password);

	void addUser(String name, String phoneNumber);

	void addContact(List<Integer> call, String username, String phoneNumber, String accNo, String location,
			int loanAmount);

}
