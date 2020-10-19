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
	
	
	
}
