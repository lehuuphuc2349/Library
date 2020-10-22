/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Phucdz
 */
public class Borrower extends Person{
	private ArrayList<Loan> borrowedBooks;
	private ArrayList<HoldRequest> onHoldRequests;

	public Borrower() {
	}

	public Borrower(int id, int phone, String name, String password, String address) {
		super(id, phone, name, password, address);
		borrowedBooks = new ArrayList();
		onHoldRequests = new ArrayList();
	}

	Borrower(int i, String name, String address, int phone) {
	}

	@Override
	public void PrintInfo() {
		super.PrintInfo(); //To change body of generated methods, choose Tools | Templates.
		PrintBorrowedBooks();
		PrintOnHoldBooks(); 
		
	}
	public void PrintBorrowedBooks() {
		if(!borrowedBooks.isEmpty()) {
			System.out.println("Borrowed Books are: ");
			System.out.println("No.\t\tTitle\t\tAuthor\t\tSubject: ");
			for(int i = 0; i < borrowedBooks.size(); i++) {
				System.out.print(i + "-" + "\t\t");
				borrowedBooks.get(i).getBook().PrintInfoBooks();
				System.out.println("");
			}
		} else {
			System.out.println("No Borrwed Books");
		}
	}
	
	public void PrintOnHoldBooks() {
		if(!onHoldRequests.isEmpty()) {
			System.out.println("On Hold Books Are: ");
			System.out.println("No.\t\tTitle\t\tAuthor\t\tSubject");
			for(int i = 0; i < onHoldRequests.size(); i++) {
				System.out.print(i+ "-" + "\t\t");
				onHoldRequests.get(i).getBook().PrintInfoBooks();
			}
		}
	}
	public void UpdateBorrwedInfo() throws Exception{
		String choice;
		Scanner input = new Scanner(System.in);
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Do you want to update " + getName() + " 's Name ? (Y/N)");
		choice = input.nextLine();
		if(!choice.equals("Y")) {
			System.out.println("Type New Name: ");
			setName(buff.readLine());
			System.out.println("The name is successfully updated");
		}
		System.out.println("Do you want to update " + getName() + " 's Address (Y/N)");
		choice = input.nextLine();
		if(!choice.equals("Y")) {
			System.out.println("Type New Address: ");
			setAddress(buff.readLine());
			System.out.println("The Address is Successfully Updated");
		}
		System.out.println("Do you want to update " + getName() + " 's Phone Number");
		choice = input.nextLine();
		if(!choice.equals("Y")) {
			System.out.println("Type New Phone Number:");
			setPhone(input.nextInt());
			System.out.println("The Phone is Successfully Updated");
		}
	}
	public void AddBorrwedBook(Loan book) {
		borrowedBooks.add(book);
	}
	public void RemoveBorrwedBook(Loan book){
		borrowedBooks.remove(book);
	}	
	public void AddHoldRequest(HoldRequest hr) {
		onHoldRequests.add(hr);
	}
	public void RemoveHoldRequest(HoldRequest hr) {
		onHoldRequests.remove(hr);
	}

	public ArrayList<Loan> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(ArrayList<Loan> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	public ArrayList<HoldRequest> getOnHoldRequests() {
		return onHoldRequests;
	}

	public void setOnHoldRequests(ArrayList<HoldRequest> onHoldRequests) {
		this.onHoldRequests = onHoldRequests;
	}
	
	
}
