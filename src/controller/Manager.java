/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import model.Book;
import model.Borrower;
import model.Clerk;
import model.Library;
import model.Librian;

/**
 *
 * @author Phucdz
 */
public class Manager {
//	Search 

	public void showBook(Library library) throws Exception {
		Connection connection = controller.ConnectMySQL.ConnectMySQLSever();
		String query = "Select * from Book";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		if (!result.next()) {
			System.out.println("No Books Found In Library");

		} else {
			int maxID = 0;
			do {
				if (result.getString("Title") != null && result.getString("Author") != null && result.getString("Subject") != null && result.getInt("ID") != 0) {
					String title = result.getString("Title");
					String author = result.getString("Author");
					String subject = result.getString("Subject");
					int id = result.getInt("ID");
					boolean issue = result.getBoolean("Issued");
					Book b = new Book(id, title, subject, author, issue);
					library.addBookInLibrary(b);
					if (maxID < id) {
						maxID = id;
					}
				}
			} while (result.next());
		}
	}

	public void showClerk(Library library) throws Exception {
		Connection connection = controller.ConnectMySQL.ConnectMySQLSever();
		String query = "Select ID, PNAME, ADDRESS, PASSWORD, PHONE, SALARY, DESK FROM PERSON INNER JOIN CLERK ON ID = C_ID INNER JOIN STAFF ON S_ID = C_ID";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		if (!result.next()) {
			System.out.println("No Clerk Found in Library");
		} else {
			do {
				int id = result.getInt("ID");
				String name = result.getString("PNAME");
				String address = result.getString("ADDRESS");
				int phone = result.getInt("PHONE");
				double salary = result.getDouble("SALARY");
				int desk = result.getInt("DESK");
				Clerk c = new Clerk(desk, salary, id, phone, name, address, address);
				library.addClerk(c);
			} while (result.next());
		}
	}

	public void showLibrian(Library library) throws Exception {
		Connection connection = controller.ConnectMySQL.ConnectMySQLSever();
		String query = "Select ID, PNAME, ADDRESS, PASSWORD, PHONE, SALARY, OFFICENO FROM PERSON INNER JOIN LIBRARIAN ON ID = L_ID INNER JOIN STAFF ON S_ID = L_ID";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		if (!result.next()) {
			System.out.println("No Librian Found in Library");
		} else {
			do {
				int id = result.getInt("ID");
				String name = result.getString("PNAME");
				String address = result.getString("ADDRESS");
				int phone = result.getInt("PHONE");
				double salary = result.getDouble("SALARY");
				int officeno = result.getInt("OFFICENO");
				Librian librian = new Librian(officeno, salary, id, phone, name, address, address);	
				library.addLibrian(librian);
				
			} while (result.next());
		}
	}
	public void showBorrwer(Library library) throws Exception {
		Connection connection = controller.ConnectMySQL.ConnectMySQLSever();
		String query =	"Select ID,PNAME,ADDRESS,PASSWORD,PHONENO FROM PERSON INNER JOIN BORROWER ON ID = B_ID";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		if(!result.next()) {
			System.out.println("No Borrwer Found In Library");
		} else {
			do {
				int id = result.getInt("ID");
				String name = result.getString("PNAME");
				String address= result.getString("ADDRESS");
				int phone = result.getInt("PHONENO");
				Borrower borrower = new Borrower(id, phone, name, address, address);
				library.addBorrwer(borrower);
				
			}while(result.next());
		}
	}
}

