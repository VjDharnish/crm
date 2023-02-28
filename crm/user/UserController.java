package com.dharun.crm.user;
import java.util.List;

import com.dharun.crm.repository.*;
public class UserController implements UserControllerCallBack,UserControllerModelCallBack {
	private UserViewCallBack userView;
	private UserModelCallBack userModel; 
	public UserController(UserViewCallBack userView) {
		this.userView =userView;
		this.userModel = new UserModel(this);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void checkCredentials(String username, String phoneNumber) {
	 userModel.checkCredentials(username, phoneNumber);
		
	}
	@Override
	public void addUser(String name, String phoneNumber) {
		 userModel.addUser(name,phoneNumber);
		
		
	}
	@Override
	public void invalidOption(String errMsg) {
		userView.invalidOption(errMsg);
		
	}
	@Override
	public void addedSuccesfull() {
		userView.addedSuccesfull("Your Profile added Successfully Plz Login to Continue");
		
		
	}

	@Override
	public void isAccept(char option,List<Integer>call,String username,String phoneNumber) {
		switch(option) {
		case '1':
			userView.addDetails(call,username,phoneNumber);
			break;
		case '2':
			userView.rejected();
			break;
		default:
			userView.invalidOption("Invalid Option");
		}
		
	}
	@Override
	public void callReceived(List<Integer> call,String username, String phoneNumber) {
		userView.callReceived(call,username,phoneNumber);
		
	}
	@Override
	public void addContact(List<Integer> call, String username, String phoneNumber, String accNo, String location,
			int loanAmount) {
		userModel.addContact(call,username,phoneNumber,accNo,location,loanAmount);
		
	}
	@Override
	public void contactAdded(float monthlyIntrest) {
		userView.contactAdded(monthlyIntrest);
		
	}
	@Override
	public void noCalls(String noCall) {
		userView.noCalls(noCall);
		
	}
	@Override
	public void showContact(List contact) {
		userView.getContact(contact);
		
	}
	@Override
	public void invalidCredentials(String string) {
		userView.invalidCredentials(string);
		
	}}
