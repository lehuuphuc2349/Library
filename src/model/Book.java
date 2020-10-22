/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Phucdz
 */
public class Book {

	private int bookID;
	private String title, subject, author;
	private Boolean isIssued;
	private ArrayList<HoldRequest> holdRequests;

	public Book() {
	}

	public Book(int bookID, String title, String subject, String author, Boolean isIssued) {
		this.bookID = bookID;
		this.title = title;
		this.subject = subject;
		this.author = author;
		this.isIssued = isIssued;
		holdRequests = new ArrayList();
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getIsIssued() {
		return isIssued;
	}

	public void setIsIssued(Boolean isIssued) {
		this.isIssued = isIssued;
	}

	public ArrayList<HoldRequest> getHoldRequests() {
		return holdRequests;
	}

	public void setHoldRequests(ArrayList<HoldRequest> holdRequests) {
		this.holdRequests = holdRequests;
	}

//	ADD Hold Request
	public void addHoldRequest(HoldRequest hr) {
		holdRequests.add(hr);
	}
//	REMOVING HOLD REQUEST

	public void removeHoldRequest() {
		if (!holdRequests.isEmpty()) {
			holdRequests.remove(0);
		}
	}
//	PRINTING ALL HOLD REQUEST

	public void printHoldRequest() {
		if (!holdRequests.isEmpty()) {
			System.out.println("==============================");
			System.out.println("No.\t\tBook's Title\t\tBorrower's Name\t\tRequest Date");
			System.out.println("==============================");
			for (int i = 0; i < holdRequests.size(); i++) {
				System.out.print(i + "-" + "\t\t");
				holdRequests.get(i).Print();
			}
		} else {
			System.out.println("No Hold Request");
		}
	}
//	Print Info Books

	public void PrintInfoBooks() {
		System.out.println(title + "\t\t\t" + author + "\t\t\t" + subject);
	}

	public void changeBookInfo() throws Exception {
		Scanner input = new Scanner(System.in);
		String choice;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Update Author ? (Y/N)");
		choice = input.nextLine();
		if (choice.equals("Y") || choice.equals("y")) {
			System.out.println("Enter New Author: ");
			setAuthor(reader.readLine());
		}
		System.out.println("Update Subject ? (Y/N)");
		choice = input.nextLine();
		if (choice.equals("Y") || choice.equals("y")) {
			System.out.println("Enter New Subject: ");
			setSubject(reader.readLine());
		}
		System.out.println("Update Title ? (Y/N)");
		choice = input.nextLine();
		if (choice.equals("Y") || choice.equals("y")) {
			System.out.println("Enter New Title: ");
			setTitle(reader.readLine());
		}
		System.out.println("Books is successfully updated");
	}

	public void placeBookOnHold(Borrower borrower) {
		HoldRequest hr = new HoldRequest(borrower, this, new Date());

		addHoldRequest(hr);
		borrower.AddHoldRequest(hr);
		System.out.println("The book " + title + " has been successfully placed on hold by borrower " + borrower.getName());
	}

	public void makeHoldRequest(Borrower borrower) {
		boolean makeRequest = true;
		for (int i = 0; i < borrower.getBorrowedBooks().size(); i++) {
			if (borrower.getBorrowedBooks().get(i).getBook() == this) {
				System.out.println("You have already borrowed " + title);
				return;
			}
		}
		for (int i = 0; i < holdRequests.size(); i++) {
			if (holdRequests.get(i).getBorrower() == borrower) {
				makeRequest = false;
				break;
			}
		}
		if (makeRequest) {
			placeBookOnHold(borrower);
		} else {
			System.out.println("You already have one hold request for this book");
		}
	}

	public void GettingInfoHoldRequest(HoldRequest hr) {
		removeHoldRequest();
		hr.getBorrower().RemoveHoldRequest(hr);
	}

	public void issueBook(Borrower borrower, Staff staff) {
		Date today = new Date();
		ArrayList<HoldRequest> hReqests = this.holdRequests;
		for (int i = 0; i < hReqests.size(); i++) {
			HoldRequest hr = hReqests.get(i);

			long days = ChronoUnit.DAYS.between(today.toInstant(), hr.getReqDate().toInstant());
			days = 0 - days;
			if(days > Library.getInstace().getHoldRequestExpiry()) {
				removeHoldRequest();
				hr.getBorrower().RemoveHoldRequest(hr);
			}
		}
		if (isIssued) {
			System.out.println("The book: " + title + " is already issued.");
			System.out.println("Would you like to place the book on hold ? (Y/N)");
			Scanner input = new Scanner(System.in);
			String choice = input.next();
			if (choice.equals("Y") || choice.equals("y")) {
				makeHoldRequest(borrower);
			}
		} else {
			if (!holdRequests.isEmpty()) {
				boolean hasRequest = false;
				for (int i = 0; i < holdRequests.size() && !hasRequest; i++) {
					if (holdRequests.get(i).getBorrower() == borrower) {
						hasRequest = true;
					}
				}
				if (hasRequest) {
					if (holdRequests.get(0).getBorrower() == borrower) {
						GettingInfoHoldRequest(holdRequests.get(0));
					} else {
						System.out.println("Sorrry some other users have requested for this book earlier than you. So you have to wait until their hold request");
						return;
					}
				} else {
					System.out.println("Some users have already placed this book on request and you haven't, so the book can't be issued to you");
					System.out.println("Would you like to place the book on hold? (Y/N)");
					Scanner input = new Scanner(System.in);
					String choice = input.nextLine();
					if (choice.equals("Y") || choice.equals("y")) {
						makeHoldRequest(borrower);
					}
					return;
				}
			}
			setIsIssued(true);
			Loan iHistory = new Loan(borrower, this, staff, null, new Date(), null, false);
//			Library.getInstance().addLoan(iHistory);
			borrower.AddBorrwedBook(iHistory);
			System.out.println("The book " + title + " is successfully issued to " + borrower.getName() + ".");
			System.out.println("Issued by: " + staff.getName());
		}
	}
	public void returnBook(Borrower borrower, Loan loan, Staff staff) {
		loan.getBook().setIsIssued(false);
		loan.setDateReturned(new Date());
		loan.setReceiver(staff);
		borrower.RemoveBorrwedBook(loan);
		loan.payFine();
		System.out.println("The book " + loan.getBook().getTitle() + " is succesfully returned by " + borrower.getName() + ".");
		System.out.println("Received by: " + staff.getName());
	} 
}
