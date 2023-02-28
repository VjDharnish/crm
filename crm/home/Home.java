package com.dharun.crm.home;

import java.util.Scanner;

import com.dharun.crm.accounts.AccountsView;
import com.dharun.crm.user.UserView;

public class Home {
	private Scanner scanner = new Scanner(System.in);
	private UserView userView;

	public static void main(String[] args) {
		Home home = new Home();
		home.homeOption();

	}

	public void homeOption() {
		System.out.println("Welcome to CRM Application");
		System.out.println("Press 1 to User Login");
		System.out.println("Press 2 to Signup if new user");
		System.out.println("Press 3 to Accounts Login");
		System.out.println("Press 0 to Exit");
		System.out.println("Enter Your Choice");
		char option = scanner.next().charAt(0);

		switch (option) {
		case '1':
			userView = new UserView(this);
			userView.userLogin();
			break;
		case '2':
			userView = new UserView(this);
			userView.userSignup();
			break;
		case '3':
			AccountsView  accountsView  = new AccountsView(this);
			accountsView.accountsLogin();
		default:
			System.out.println("Thank You");
			
		}
	}

}
