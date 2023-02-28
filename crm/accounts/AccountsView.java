package com.dharun.crm.accounts;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.dharun.crm.home.Home;
public class AccountsView implements AccountsViewCallBack{
	private AccountsControllerCallBack accountsController;
	private Scanner scanner = new Scanner(System.in);
	private Home home;
	public AccountsView(Home home){
		this.accountsController=new AccountsController(this);
		this.home =home;
	}
	public void accountsLogin(){
		System.out.println("Enter User name :");
		String username  = scanner.next();
		System.out.println("Enter Phone number : ");
		String phoneNumber =scanner.next();
		accountsController.checkAccounts(username,phoneNumber);
	}
	@Override
	public void invalidAccounts(String errMsg) {
		System.out.println(errMsg+"\n");
		home.homeOption();
		
	}
	@Override
	public void verifedAccounts() {
		System.out.println("Accounts Verified");
		accountsOptions();
		
	}
	private void accountsOptions() {
		System.out.println("\nPress 1 to view Leads");
		System.out.println("Press 2 to Show Contacts");
		System.out.println("Press 3 to  back to Login");
		System.out.println("Enter your Choice ");
		char option = scanner.next().charAt(0);
		accountsController.accountOptions(option);
		
	}
	@Override
	public void noLeads() {
		System.out.println("NO LEADS AVAILABLE");
		accountsOptions();
		
	}
	@Override
	public void showLeads(HashMap<String, String> leads) {
		Set<String> keys  = leads.keySet();
		System.out.println("Name\tContact Number");
		for(String key:keys) {
			System.out.println(leads.get(key)+"\t"+key);
			System.out.println("1.call Lead\t 2.Next Lead\t 3. Back");
			char option = scanner.next().charAt(0);
			if(option =='1') {
				accountsController.callLead(key);
				System.out.println("Notification Sent to "+ key);
			}
			else if(option == '3') {
				accountsOptions();
				return;
			}

		}
		
		System.out.println("----------------------------------");
		accountsOptions();
		
	}
	@Override
	public void invalidOption(String errMsg) {
		System.out.println(errMsg+"\n");
		accountsOptions();
		
	}
	@Override
	public void backToLogin() {
		home.homeOption();
		
	}
	@Override
	public void showContacts(List<List> contacts) {
		System.out.println("---------------CONTACTS------------------");
		for(List contact : contacts) {
			
			System.out.println("User Name - " + contact.get(0));
			System.out.println("Mobile Number - " + contact.get(1));
			System.out.println("Location - " + contact.get(2));
			System.out.println("Account Number - " + contact.get(3));
			System.out.println("Loan Amount - " + contact.get(4));
			System.out.println("Monthly Interest Percent - " + contact.get(5));
			System.out.println("Monthly Interest Amount - " + contact.get(6));
			System.out.println("Individual Profit Percent - " + contact.get(7));
			System.out.println("------------------------------------------------");
		}
		System.out.println("------------------------------------------------");
		
		accountsOptions();
	}
		
}
	

