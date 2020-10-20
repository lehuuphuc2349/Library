/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Phucdz
 */
public class Library {

	private String name;
	private Librian librian;
	private ArrayList<Person> persons;
	private ArrayList<Book> booksInLibrary;
	private ArrayList<Loan> loans;
	public int bookReturnDeadLine;
	public double perDayFine;
	public int HoldRequestExpiry;

	private static Library obj;

	public static Library getInstace() {
		if (obj == null) {
			obj = new Library();
		}
		return obj;
	}

	public Library() {
		name = null;
		librian = null;
		persons = new ArrayList();
		booksInLibrary = new ArrayList();
		loans = new ArrayList();
	}

	public Library(String name, Librian librian, ArrayList<Person> persons, ArrayList<Book> booksInLibrary, ArrayList<Loan> loans, int bookReturnDeadLine, double perDayFine, int HoldRequestExpiry) {
		this.name = name;
		this.librian = librian;
		this.persons = persons;
		this.booksInLibrary = booksInLibrary;
		this.loans = loans;
		this.bookReturnDeadLine = bookReturnDeadLine;
		this.perDayFine = perDayFine;
		this.HoldRequestExpiry = HoldRequestExpiry;
	}

//	Getter Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Librian getLibrian() {
		return librian;
	}

	public void setLibrian(Librian librian) {
		this.librian = librian;
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}

	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}

	public ArrayList<Book> getBooksInLibrary() {
		return booksInLibrary;
	}

	public void setBooksInLibrary(ArrayList<Book> booksInLibrary) {
		this.booksInLibrary = booksInLibrary;
	}

	public ArrayList<Loan> getLoans() {
		return loans;
	}

	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

	public int getBookReturnDeadLine() {
		return bookReturnDeadLine;
	}

	public void setBookReturnDeadLine(int bookReturnDeadLine) {
		this.bookReturnDeadLine = bookReturnDeadLine;
	}

	public double getPerDayFine() {
		return perDayFine;
	}

	public void setPerDayFine(double perDayFine) {
		this.perDayFine = perDayFine;
	}

	public int getHoldRequestExpiry() {
		return HoldRequestExpiry;
	}

	public void setHoldRequestExpiry(int HoldRequestExpiry) {
		this.HoldRequestExpiry = HoldRequestExpiry;
	}

	public static Library getObj() {
		return obj;
	}

	public static void setObj(Library obj) {
		Library.obj = obj;
	}

//	Adding all People in Library
	public boolean addLibrian(Librian lib) {
		if (librian == null) {
			librian = lib;
			persons.add(lib);
			return true;
		} else {
			System.out.println("Sorry, the library already has one librian. New Librarian can't be created");
		}
		return false;
	}

	public void addClerk(Clerk clerk) {
		persons.add(clerk);
	}

	public void addBorrwer(Borrower borrower) {
		persons.add(borrower);
	}

	public void addLoans(Loan loan) {
		loans.add(loan);
	}
//	Finding People in Library

	public Borrower findBorrower() {
		System.out.println("Enter Borrwer's ID: ");
		int id = 0;
		Scanner input = new Scanner(System.in);
		try {
			id = input.nextInt();

		} catch (java.util.InputMismatchException e) {
			System.out.println(e);
		}
		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(id).getId() == id && persons.get(id).getClass().getSimpleName().equals("Borrwer"));
			return (Borrower) persons.get(i);
		}
		System.out.println("Sorry this ID did't match any Borrower's ID");
		return null;
	}

	public Clerk findClerk() {
		System.out.println("Enter Clerk's ID: ");
		int id = 0;
		Scanner input = new Scanner(System.in);
		try {
			id = input.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println(e);
		}
		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getId() == id && persons.get(id).getClass().getSimpleName().equals("Clerk"));
			return (Clerk) persons.get(i);
		}
		System.out.println("Sorry this ID didn't match any Clerk's ID");
		return null;
	}
//	Book In Library

	public void addBookInLibrary(Book b) {
		booksInLibrary.add(b);
	}

	public void removeBookFromLibrary(Book b) {
		boolean delete = true;
		for (int i = 0; i < persons.size() && delete; i++) {
			if (persons.get(i).getClass().getSimpleName().equals("Borrwer")) {
				ArrayList<Loan> borBooks = ((Borrower) (persons.get(i))).getBorrowedBooks();
				for (int j = 0; j < borBooks.size() && delete; j++) {
					if (borBooks.get(j).getBook() == b) {
						delete = false;
						System.out.println("This particular book is currently borrweed by some borrwer");
					}
				}
			}
		}
		if (delete) {
			System.out.println("Currently this book is not borrowed by anyone");
			ArrayList<HoldRequest> hRequests = b.getHoldRequests();
			if (!hRequests.isEmpty()) {
				System.out.println("This book might be on hold request by some borrowers. Deleting this book will delete the relevant hold request too.");
				System.out.println("Do you still want to delete the book ? (Y/N)");
				Scanner input = new Scanner(System.in);
				while (true) {
					String choice = input.nextLine();
					if (choice.equals("Y") || choice.equals("y") || choice.equals("N") || choice.equals("n")) {
						if (choice.equals("N") || choice.equals("n")) {
							System.out.println("Delete Successfully");
							return;
						} else {
							for (int i = 0; i < hRequests.size(); i++) {
								HoldRequest hr = hRequests.get(i);
								hr.getBorrower().RemoveHoldRequest(hr);
								b.removeHoldRequest();
							}
						}
					} else {
						System.out.println("Invalid Input. Enter (Y/N)");
					}
				}
			} else {
				System.out.println("This book has no hold requests");
				booksInLibrary.remove(b);
				System.out.println("The book is successfully removed");
			}
		} else {
			System.out.println("Delete Unsuccessful");
		}
	}
//	Searching Books

	public ArrayList<Book> searchForBooks() throws Exception {
		String choice;
		String title = "", subject = "", author = "";
		Scanner input = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("Enter either '1', '2' or '3' for search by Title, Subject, Author of book respectively");
			choice = input.nextLine();
			if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
				break;
			} else {
				System.out.println("Wrong Input");
			}
		}
		switch (choice) {
			case "1":
				System.out.println("Enter the Title of book");
				title = reader.readLine();
				break;
			case "2":
				System.out.println("Enter the Subject of Book");
				subject = reader.readLine();
				break;
			default:
				System.out.println("Enter the Author of Book");
				 {
					author = reader.readLine();
				}
				break;
		}
		ArrayList<Book> matchedBooks = new ArrayList();
		for (int i = 0; i < booksInLibrary.size(); i++) {
			Book b = booksInLibrary.get(i);
			switch (choice) {
				case "1":
					if (b.getTitle().equals(title)) {
						matchedBooks.add(b);
					}
					break;
				case "2":
					if (b.getSubject().equals(subject)) {
						matchedBooks.add(b);
					}
					break;
				default:
					if (b.getAuthor().equals(author)) {
						matchedBooks.add(b);
					}
					break;
			}
		}
		if (!matchedBooks.isEmpty()) {
			System.out.println("The books are found");
			System.out.println("No.\t\tTitle\t\tAuthor\t\tSubject");
			for (int i = 0; i < matchedBooks.size(); i++) {
				System.out.print(i + "-" + "\t\t");
				matchedBooks.get(i).PrintInfoBooks();
				System.out.println("");
			}
			return matchedBooks;
		} else {
			System.out.println("Sorry. No Books were found related to your empty");
			return null;

		}
	}
//	View Info All Book

	public void viewALLBooks() {
		if (!booksInLibrary.isEmpty()) {
			System.out.println("Book are: ");
			System.out.println("No.\t\tTitle\t\tAuthor\t\tSubject");
			for (int i = 0; i < booksInLibrary.size(); i++) {
				System.out.print(i + "-" + "\t\t");
				booksInLibrary.get(i).PrintInfoBooks();
				System.out.println("");
			}
		} else {
			System.out.println("Currently, Library has no books");
		}
	}
//	Create Person

	public void createPerson(char x) {
		Scanner input = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter name");
		String name = "";
		try {
			name = reader.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("Enter Address");
		String address = "";
		try {
			address = reader.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("Enter Phone");
		int phone = 0;
		try {
			phone = input.nextInt();
		} catch (Exception e) {
			System.out.println(e);
		}
//		Cleark
		switch (x) {
			case 'c': {
				double salary = 0;
				try {
					System.out.println("Enter Salary");
					salary = input.nextDouble();
				} catch (Exception e) {
					System.out.println(e);
				}
				Clerk c = new Clerk(-1, name, address, phone, salary, -1);
				addClerk(c);
				System.out.println("Clerk With Name: " + name + " created successfully");
				System.out.println("Your ID: " + c.getId());
				System.out.println("Your password is: " + c.getPassword());
				break;
			}
			case 'l': {
				double salary = 0;
				try {
					System.out.println("Enter Salary");
					salary = input.nextDouble();
				} catch (Exception e) {
					System.out.println(e);
				}
				Librian l = new Librian(-1, name, address, phone, salary, -1);
				if (addLibrian(l)) {
					System.out.println("Librian With Name: " + name + " created successfully");
					System.out.println("Your ID: " + l.getId());
					System.out.println("Your Password: " + l.getPassword());
				}
				break;
			}
			default:
				Borrower borrower = new Borrower(-1, name, address, phone);
				addBorrwer(borrower);
				System.out.println("Borrwer With Name: " + name);
				System.out.println("Your ID: " + borrower.getName());
				System.out.println("Your password: " + borrower.getPassword());
				break;
		}
	}
//	Compute total Fine for all loans of a borrwer

	public double computeFine(Borrower borrower) {
		System.out.println("");
		System.out.println("No.\t\tBook's Title\t\tBorrwer's Name\t\tIssued Date\t\tReturned Date\t\tResult");
		double totalFine = 0;
		double perLoanFine = 0;
		for (int i = 0; i < loans.size(); i++) {
			Loan l = loans.get(i);
			perLoanFine = l.computeFine();
			System.out.print(i + "-" + "\t\t" + loans.get(i).getBook().getTitle() + "\t\t" + loans.get(i).getBorrower().getName() + "\t\t" + loans.get(i).getIssuedDate() + "\t\t" + loans.get(i).getDateReturned() + "\t\t" + perLoanFine + "\n");
			totalFine += perLoanFine;
		}
		return totalFine;
	}
//	Create Book

	public void createBook(String title, String subject, String author) {
		Book b = new Book(-1, title, subject, author, false);
		addBookInLibrary(b);
		System.out.println("Book with Title " + b.getTitle() + " is successfully created");
	}
//	Login

	public Person Login() {
		Scanner input = new Scanner(System.in);
		int id = 0;
		String password = "";
		System.out.println("Enter ID: ");
		try {
			id = input.nextInt();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Enter password");
		password = input.nextLine();
		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getId() == id && persons.get(i).getPassword().equals(password)) {
				System.out.println("Login Successfully");
				return persons.get(i);
			}
		}
		if (librian != null) {
			if (librian.getId() == id && librian.getPassword().equals(password)) {
				System.out.println("Login successfully");
				return librian;
			}
		}
		System.out.println("Sorry!, Wrong ID or Password");
		return null;
	}
//	View History

	public void viewHistory() {
		if (!loans.isEmpty()) {
			System.out.println("Issued Book are: ");
			System.out.println("No.\t\tTitle\t\tBorrwer's Name\t\tIssuer's Name\t\tIssued Date\t\tReceiver's Name\t\tReturned Date\t\tFine Paid");

			for (int i = 0; i < loans.size(); i++) {
				if (loans.get(i).getIssuer() != null) {
					System.out.println(i + "-" + "\t" + loans.get(i).getBook().getTitle() + "\t\t" + loans.get(i).getBorrower().getName() + "\t\t" + loans.get(i).getIssuer().getName() + loans.get(i).getIssuedDate());
				}
				if (loans.get(i).getReceiver() != null) {
					System.out.println("\t" + loans.get(i).getReceiver().getName() + "\t\t" + loans.get(i).getDateReturned() + "\t\t" + loans.get(i).getFinePaid());
				} else {
					System.out.println("\t\t" + "--" + "\t\t\t" + "--" + "\t\t" + "--");

				}
			}
		} else {
			System.out.println("No issued Books");
		}
	}
}
