package com.dharun.crm.user;

import java.util.List;

public interface UserControllerCallBack {

	void checkCredentials(String username, String password);

	void addUser(String name, String phoneNumber);

	void isAccept(char option, List<Integer> call, String username, String phoneNumber);

	void addContact(List<Integer> call, String username, String phoneNumber, String accNo, String location, int loanAmount);

}
