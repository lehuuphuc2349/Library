/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Book;
import model.Borrower;
import model.Clerk;
import model.HoldRequest;
import model.Library;
import model.Librian;
import model.Loan;
import model.Person;
import model.Staff;

/**
 *
 * @author Phucdz
 */
public class Manager {
//	Show 

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
		String query = "Select ID,PNAME,ADDRESS,PASSWORD,PHONENO FROM PERSON INNER JOIN BORROWER ON ID = B_ID";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		if (!result.next()) {
			System.out.println("No Borrwer Found In Library");
		} else {
			do {
				int id = result.getInt("ID");
				String name = result.getString("PNAME");
				String address = result.getString("ADDRESS");
				int phone = result.getInt("PHONENO");
				Borrower borrower = new Borrower(id, phone, name, address, address);
				library.addBorrwer(borrower);

			} while (result.next());
		}
	}

	public void showLoans(Library library) throws Exception {
		Connection connection = controller.ConnectMySQL.ConnectMySQLSever();
		String query = "Select * from Loans";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		if (!result.next()) {
			System.out.println("No Loans Found In Library");
		} else {
			do {
				int borrID = result.getInt("BORROWER");
				int bookID = result.getInt("BOOK");
				int issueID = result.getInt("ISSUER");
				Integer receiID = (Integer) result.getObject("RECEIVER");
				int rd = 0;
				Date rdate;
				Date idate = new Date(result.getTimestamp("ISS_DATE").getTime());
				if (receiID != null) {
					rdate = new Date(result.getTimestamp("RET_DATE").getTime());
					rd = (int) receiID;
				} else {
					rdate = null;
				}
				boolean fineStatus = result.getBoolean("FINE_PAID");
				boolean set = true;
				Borrower bb = null;
				for (int i = 0; i < library.getPersons().size() && set; i++) {
					if (library.getPersons().get(i).getId() == borrID) {
						set = false;
						bb = (Borrower) (library.getPersons().get(i));
					}
				}
				set = true;
				Staff staff[] = new Staff[2];
				if (issueID == library.getLibrian().getId()) {
					staff[0] = library.getLibrian();
				} else {
					for (int k = 0; k < library.getPersons().size() && set; k++) {
						if (library.getPersons().get(k).getId() == issueID && library.getPersons().get(k).getClass().getSimpleName().equals("Clerk")) {
							set = false;
							staff[0] = (Clerk) (library.getPersons().get(k));
						}
					}
				}
				set = true;
				if (receiID == null) {
					staff[1] = null;
					rdate = null;
				} else {
					if (rd == library.getLibrian().getId()) {
						staff[1] = library.getLibrian();
					} else {
						for (int k = 0; k < library.getPersons().size() && set; k++) {
							if (library.getPersons().get(k).getId() == rd && library.getPersons().get(k).getClass().getSimpleName().equals("Clerk")) {
								set = false;
								staff[1] = (Clerk) library.getPersons().get(k);
							}
						}
					}
				}
				set = true;
				ArrayList<Book> books = library.getBooksInLibrary();

				for (int k = 0; k < library.getBooksInLibrary().size() && set; k++) {
					if (books.get(k).getBookID() == bookID) {
						set = false;
						Loan loan = new Loan(bb, books.get(k), idate, rdate, staff[0], staff[1], fineStatus);
						ArrayList<Loan> loans = library.getLoans();
						loans.add(loan);

					}
				}

			} while (result.next());
		}
	}
	public void ShowHoldBooks(Library library) throws Exception {
		Connection connection = controller.ConnectMySQL.ConnectMySQLSever();
		String query = "Select * from HOLDBOOKS";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		if(!result.next()) {
			System.out.println("No Books On Hold");
		} else {
			do {
				int borrID = result.getInt("BORROWER");
				int bookID = result.getInt("BOOK");
				Date off = new Date(result.getDate("REQ_DATE").getTime());
				boolean set = true;
				Borrower borrower = null;
				ArrayList<Person> persons = library.getPersons();
				for(int i =0 ; i <persons.size() && set ; i++) {
					if(persons.get(i).getId() == borrID) {
						set = false;
						borrower = (Borrower) persons.get(i);
					}
				}
				set = true;
				ArrayList<Book> books = library.getBooksInLibrary();
				for(int i = 0; i < books.size() && set; i++) {
					if(books.get(i).getBookID() == bookID) {
						set = false;
						HoldRequest holdBook = new HoldRequest(borrower,books.get(i),off);
						books.get(i).addHoldRequest(holdBook);
						borrower.AddHoldRequest(holdBook);
						
					}
				}
			}while(result.next());
		}
	}
	public void ShowBorrowerInfo(Library library) throws Exception {
		Connection connection = controller.ConnectMySQL.ConnectMySQLSever();
		String query = "Select ID, BOOK FROM PERSON INNER JOIN BORROWER ON ID = B_ID INNER JOIN BORROWED_BOOK ON B_ID = BORROWER";
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(query);
		if(!result.next()) {
			System.out.println("No Borrwer has borrowed book yet from library");
			
		} else {
			do {
				int id = result.getInt("ID");
				int bookID = result.getInt("BOOK");
				Borrower borrwer = null;
				boolean set = true;
				boolean okay = true;
				for(int i = 0; i < library.getPersons().size() && set; i++) {
					if(library.getPersons().get(i).getClass().getSimpleName().equals("BORROWER")) {
						if(library.getPersons().get(i).getId() == id) {
							set = false;
							borrwer = (Borrower) library.getPersons().get(i);
						}
					}
				}
				set = true;
				ArrayList<Loan> books = library.getLoans();
				for(int i = 0; i < books.size() && set; i++) {
					if(books.get(i).getBook().getBookID() == bookID && books.get(i).getReceiver() == null) {
						set = false;
						Loan bBook = new Loan(borrwer,books.get(i).getBook(), books.get(i).getIssuer(),null, books.get(i).getIssuedDate(),null,books.get(i).getFinePaid());
						borrwer.AddBorrwedBook(bBook);
					}
				}
			}while(result.next());
		}
		ArrayList<Person> persons = library.getPersons();
		int max = 0;
		for(int i = 0; i < persons.size(); i++) {
			if(max < persons.get(i).getId()) {
				max = persons.get(i).getId();
			}
		}
	}

}
