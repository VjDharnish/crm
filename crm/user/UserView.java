package com.dharun.crm.user;

import com.dharun.crm.home.*;

import utility.Validator;

import java.util.List;
import java.util.Scanner;

public class UserView implements UserViewCallBack {
	private UserControllerCallBack userController;
	private Home home;
	private Scanner scanner = new Scanner(System.in);

	public UserView(Home home) {
		this.home = home;
		this.userController = new UserController(this);
	}

	public void userLogin() {
		System.out.println("Enter your Username :");
		String username = scanner.next();
		System.out.println("Enter Your Phone Number :");
		String phoneNumber = scanner.next();
		userController.checkCredentials(username, phoneNumber);

	}

	@Override
	public void invalidCredentials(String errMsg) {

		System.out.println(errMsg);
		home.homeOption();

	}

	public void userSignup() {
		System.out.println("Enter Name : ");
		String name = scanner.next();
		System.out.println("Enter Phonenumber : ");

		String phoneNumber = scanner.next();
		if (Validator.validatePhone(phoneNumber))
			userController.addUser(name, phoneNumber);
		else {
			System.out.println("Invalid Phone Number");
			userSignup();
		}

	}

	@Override
	public void invalidOption(String errMsg) {
		System.out.println(errMsg);
		home.homeOption();

	}

	@Override
	public void addedSuccesfull(String added) {
		System.out.println(added);
		home.homeOption();

	}

	@Override
	public void addDetails(List<Integer> call, String username, String phoneNumber) {
		System.out.println("Enter Account No:");
		String accNo = scanner.next();
		System.out.println("Enter Location : ");
		String location = scanner.next();
		System.out.println("Enter Loan Amount :");
		String amount = scanner.next();
		try {
			int loanAmount = Integer.parseInt(amount);
			if (loanAmount < call.get(0) || loanAmount > 2000000) {
				throw new Exception();
			}
			userController.addContact(call, username, phoneNumber, accNo, location, loanAmount);
		} catch (Exception e) {
			System.out.println("Invalid Amount");
			addDetails(call, username, phoneNumber);
		}

	}

	@Override
	public void rejected() {
		home.homeOption();

	}

	@Override
	public void callReceived(List<Integer> call, String username, String phoneNumber) {
		System.out.println(
				"You are eligible to apply loan of minimum " + call.get(0) + " with Interest " + call.get(1) + " %\n");
		System.out.println("Press 1 to Accept");
		System.out.println("Press 2 to Reject");
		char option = scanner.next().charAt(0);
		userController.isAccept(option, call, username, phoneNumber);
	}

	@Override
	public void contactAdded(float monthlyIntrest) {
		System.out.println("MOnthly Interest you have to pay " + monthlyIntrest);
		System.out.println("-------------------------------------------------");
		home.homeOption();
	}

	@Override
	public void noCalls(String noCall) {
		System.out.println(noCall);
		home.homeOption();

	}

	@Override
	public void getContact(List contact) {
		System.out.println("Contact Details");
		System.out.println("Loan Amount : " + contact.get(0));
		System.out.println("Monthly Interest Percent : " + contact.get(1));
		System.out.println("Monthly Interest : " + contact.get(2));
		home.homeOption();

	}
}
