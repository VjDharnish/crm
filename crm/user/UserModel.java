package com.dharun.crm.user;

import java.util.List;

import com.dharun.crm.repository.CrmRepository;

public class UserModel implements UserModelCallBack {
	private UserControllerModelCallBack userController;

	public UserModel(UserControllerModelCallBack userController) {
		this.userController = userController;
	}

	@Override
	public void checkCredentials(String username, String phoneNumber) {
		boolean existingUser = CrmRepository.getInstance().checkCredentials(username, phoneNumber);
		if (existingUser) {
			boolean isLead = CrmRepository.getInstance().isLead(phoneNumber);
			if (isLead) {
				boolean isCall = CrmRepository.getInstance().isCall(phoneNumber);
				if (isCall) {
					List<Integer> call = CrmRepository.getInstance().Call(phoneNumber);
					userController.callReceived(call, username, phoneNumber);
				} else {
					userController.noCalls("NO Calls Now");
				}
			} else {
				List contact = CrmRepository.getInstance().getContact(phoneNumber);
				userController.showContact(contact);
			}
		} else {
			userController.invalidCredentials("Invalid Credentials");
		}
	}

	@Override
	public void addUser(String name, String phoneNumber) {
		boolean existingUser = CrmRepository.getInstance().addUser(name, phoneNumber);
		if (existingUser) {
			userController.invalidOption("Already Existing User Plaese Login to Continue");
		} else {
			userController.addedSuccesfull();
		}
		

	}

	@Override
	public void addContact(List<Integer> call, String username, String phoneNumber, String accNo, String location,
			int loanAmount) {
		float monthlyIntrest = ((float) call.get(1) / 100) * loanAmount;
		CrmRepository.getInstance().addContact(call, username, phoneNumber, accNo, location, loanAmount,
				monthlyIntrest);
		userController.contactAdded(monthlyIntrest);

	}

}
