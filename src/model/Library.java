/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

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
	
	
	
}
