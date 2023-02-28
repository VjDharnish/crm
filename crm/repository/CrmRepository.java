package com.dharun.crm.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CrmRepository {
	private static CrmRepository crmDbInstance;
	private static Connection connection;

	private CrmRepository() {

	}

	public static CrmRepository getInstance() {
		if (crmDbInstance == null) {
			crmDbInstance = new CrmRepository();
			try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRM", "root", "Idharu@10");
			} catch (SQLException e) {
				
				System.out.println("SQL EXCEPTION CONNECTION");
			}
		}
		return crmDbInstance;
	}


	public boolean checkCredentials(String username, String phoneNumber) {
		Statement statement;
		String s;
		try {
			statement = connection.createStatement();
			s = "select * from userCredentials where phoneNumber = \"" + phoneNumber + "\" and name = \""+username+"\"";
			ResultSet resultSet = statement.executeQuery(s);
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Sql Exception(User Credential)");
		}
		return false;

	}

	public boolean addUser(String username, String phoneNumber) {
		Statement statement;
		String s;
		String insert;
		boolean existingUser = false;
		try {
			statement = connection.createStatement();
			s = "select * from userCredentials where phonenumber = \"" + phoneNumber + "\"";
			ResultSet resultSet = statement.executeQuery(s);
			
			existingUser = resultSet.next();
			

			if (!existingUser) {
				insert = "insert into userCredentials (name,phoneNumber) values (\"" + username + "\",\"" + phoneNumber
						+ "\")";
				statement.execute(insert); // ERROR
				String query2 = "INSERT into leads (name,phoneNumber) values (\"" + username + "\",\"" + phoneNumber + "\")";
				statement.execute(query2);

			}

		} catch (SQLException e) {
			System.out.println("sql exception (add user)");
		}

		return existingUser;

	}

	public boolean isLead(String phoneNumber) {
		boolean isLead = false;
		Statement statement;
		String s;
		try {
			statement = connection.createStatement();
			s = "SELECT isLead from userCredentials where phoneNumber = \"" + phoneNumber + "\"";
			ResultSet resultSet = statement.executeQuery(s);
			if (resultSet.next())
				isLead = resultSet.getBoolean("isLead");

		} catch (SQLException e) {
			System.out.println("sql exception (is lead)");
		}
		return isLead;
	}

	public boolean isCall(String phoneNumber) {
		boolean isCall = false;
		Statement statement;
		String s;
		try {
			statement = connection.createStatement();
			s = "SELECT isCall  from leads where phoneNumber = \"" + phoneNumber + "\"";
			ResultSet rs = statement.executeQuery(s);
			if (rs.next())
				isCall = rs.getBoolean("isCall");
		} catch (SQLException e) {
			System.out.println("sql exception (is Call)");
		}
		return isCall;
	}

	public List<Integer> Call(String phoneNumber) {
		Statement statement;
		String s;
		List<Integer> arr = new ArrayList<>();
		try {
			statement = connection.createStatement();
			s = "SELECT minimumLoanAmount,interestPercent from Accounts ";
			ResultSet rs = statement.executeQuery(s);
			while (rs.next()) {
				arr.add(rs.getInt("minimumLoanAmount"));
				arr.add(rs.getInt("interestPercent"));
			}
		} catch (SQLException e) {
			System.out.println("sql exception (call)");
		}
		return arr;
	}

	public void addContact(List<Integer> call, String username, String phoneNumber, String accNo, String location,
			int loanAmount, float monthlyIntrest) {
		Statement statement;
		String s;
		try {
			statement = connection.createStatement();
			 s = "INSERT INTO Contacts (name,phoneNumber,accountnumber,location,loanAmount,monthlyInterestPercent,monthlyInterest) VALUES ( \""
					+ username + "\",\"" + phoneNumber + "\",\"" + accNo + "\",\"" + location + "\","
					+ loanAmount + "," + call.get(1) + "," + monthlyIntrest + ")";
			statement.execute(s);


//			s = "INSERT INTO contacts (name,phoneNumber,accountnumber,location,loanAmount,monthlyInterest ) VALUES (\"" +  username + "\",\"" + phoneNumber + "\",\"" + accNo + "\",\"" + location + "\",\" " + loanAmount + "\",\" "
//					 + "\",\"" + monthlyIntrest + "\")";
//			statement.execute(s);
			s = "UPDATE userCredentials set isLead = " + false + " where phoneNumber= \"" + phoneNumber + "\"";
			statement.execute(s);
			s = "Delete from leads where phoneNumber = \"" + phoneNumber + "\"";
			statement.execute(s);
			return;
		} catch (Exception e) {
			System.out.println("sql exception(add contact)");
		}
		return;
	}

	public List getContact(String phoneNumber) {
		Statement statement;
		List arr = new ArrayList<>();
		String s;
		try {
			statement = connection.createStatement();
			s = "SELECT  loanAmount,monthlyInterestPercent,monthlyProfit from contacts where phoneNumber = \"" + phoneNumber+"\"";
			ResultSet rs = statement.executeQuery(s);
			while (rs.next()) {
				arr.add(rs.getInt("loanAmount"));
				arr.add(rs.getInt("monthlyInterestPercent"));
				arr.add(rs.getFloat("monthlyProfit"));
			}

		} catch (Exception e) {
			System.out.println("SQL Exception (getcontact)");
		}
		return arr;

	}

	public boolean verifyAccounts(String username, String phoneNumber) {
		boolean validAccounts = true;
		try {
			Statement stmt = connection.createStatement();

			String query = "SELECT * FROM accounts";

			ResultSet resultSet = stmt.executeQuery(query);

			if (!resultSet.next()) {
				try {
					Statement stmt1 = connection.createStatement();

					 query = "INSERT INTO accounts (name,phoneNumber) VALUES ( \"" + username + "\",\""
							+ phoneNumber + "\")";

					stmt1.execute(query);
				} catch (SQLException e) {
					System.out.println("sql exception in validateAccountsCredentials()-1");
				}
			} else {
				try {
					Statement stmt2 = connection.createStatement();

					String query2 = "SELECT * FROM accounts WHERE name = \"" + username + "\" AND phoneNumber = \"" + phoneNumber + "\"";

					 resultSet = stmt2.executeQuery(query2);

					if (resultSet.next())
						validAccounts = true;
					else 
						validAccounts = false;
				} catch (SQLException e) {
					System.out.println("sql exception in verify Accounts()-2");
				}
			}

		} catch (SQLException e) {
			System.out.println("sql exception in verify Account()-3" + e);
		}


	return validAccounts;
	
	
	}

	public HashMap<String, String> getLeads() {
		HashMap<String, String> leads = new HashMap<>();
		Statement statement;
		String s;
		try {
			statement = connection.createStatement();
			s = "SELECT name,phoneNumber from leads";
			ResultSet rs = statement.executeQuery(s);
			while (rs.next()) {
				leads.put(rs.getString("phoneNumber"), rs.getString("name"));
			}

		} catch (Exception e) {
			System.out.println("sql exception (getLeads)");
		}
		return leads;

	}

	public void callLead(String key) {
		Statement statement;
		String s;
		try {
			statement = connection.createStatement();
			s = "UPDATE leads set isCall = " + true +" where phoneNumber =\""+key+"\"";
			statement.execute(s);
		} catch (Exception e) {
			System.out.println("sql exception (callLead)");
		}
	}

	public List<List> getContacts() {
		List<List> allContactDetails = new ArrayList<>();

		try {
			Statement statement = connection.createStatement();

			String s = "SELECT * FROM contacts";

			ResultSet resultSet = statement.executeQuery(s);

			while (resultSet.next()) {
				List contactDetails = new ArrayList<>();
				contactDetails.add(resultSet.getString("name"));
				contactDetails.add(resultSet.getString("phoneNumber"));
				contactDetails.add(resultSet.getString("accountnumber"));
				contactDetails.add(resultSet.getString("location"));
				contactDetails.add(resultSet.getInt("loanAmount"));
				contactDetails.add(resultSet.getInt("monthlyInterestPercent"));
				contactDetails.add(resultSet.getInt("monthlyInterest"));
				contactDetails.add(resultSet.getFloat("profit"));
				allContactDetails.add(contactDetails);
			}
		} catch (SQLException e) {
			System.out.println("sql exception (getContacts)");
		}
		return allContactDetails;
	}

	public void updateContactsProfit() {
		try {
			float totalProfitAmount = 0;
			Statement stmt = connection.createStatement();

			String query = "SELECT monthlyInterest FROM Contacts";

			ResultSet resultSet = stmt.executeQuery(query);

			while(resultSet.next()) {
				totalProfitAmount += resultSet.getFloat("monthlyInterest");
			}

			try {
			String query2 = "SELECT monthlyInterest,phoneNumber FROM Contacts";
			ResultSet resultSet1 = stmt.executeQuery(query2);
			while(resultSet1.next()) {
				float individualProfit = (float)(resultSet1.getFloat("monthlyInterest")
						/ totalProfitAmount)*100;
				String phoneNumber = resultSet1.getString("phoneNumber");
				try {
					Statement stmt1 = connection.createStatement();

					String query1 = "UPDATE Contacts SET profit = " + individualProfit + " WHERE phoneNumber = \"" + phoneNumber + "\"";
					stmt1.execute(query1);

				} catch (SQLException e) {
					System.out.println("sql exception in updateContacts()-1");
				}
			}
			}catch(Exception e) {
				System.out.println("Sql exception in updateContacts()-2");
			}
		} catch (SQLException e) {
			System.out.println("sql exception in updateContacts()-3");
		}
	}

}
