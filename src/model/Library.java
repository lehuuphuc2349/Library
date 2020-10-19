/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
		if(obj == null) {
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
		if(librian == null) {
			librian = lib;
			persons.add(lib);
			return true;
		} else 
			System.out.println("Sorry, the library already has one librian. New Librarian can't be created");
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
			
		} catch(java.util.InputMismatchException e) {
			System.out.println(e);
		} 
		for(int i = 0; i < persons.size(); i++) {
			if(persons.get(id).getId() == id && persons.get(id).getClass().getSimpleName().equals("Borrwer"));
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
		} catch(java.util.InputMismatchException e) {
			System.out.println(e);
		}
		for(int i = 0; i < persons.size(); i++) {
			if(persons.get(i).getId() == id && persons.get(id).getClass().getSimpleName().equals("Clerk"));
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
	}

}
