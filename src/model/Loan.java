/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Phucdz
 */
public class Loan {
	private Borrower borrower;
	private Book book;
	private Staff issuer;
	private Date issuedDate;
	private Date dateReturned;
	private Staff receiver;
	private Boolean finePaid;

	public Loan() {
	}

	public Loan(Borrower borrower, Book book, Staff issuer, Date issuedDate, Date dateReturned, Staff receiver, Boolean finePaid) {
		this.borrower = borrower;
		this.book = book;
		this.issuer = issuer;
		this.issuedDate = issuedDate;
		this.dateReturned = dateReturned;
		this.receiver = receiver;
		this.finePaid = finePaid;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Staff getIssuer() {
		return issuer;
	}

	public void setIssuer(Staff issuer) {
		this.issuer = issuer;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}

	public Staff getReceiver() {
		return receiver;
	}

	public void setReceiver(Staff receiver) {
		this.receiver = receiver;
	}

	public Boolean getFinePaid() {
		return finePaid;
	}

	public void setFinePaid(Boolean finePaid) {
		this.finePaid = finePaid;
	}
	
	public double computeFine() {
		double totalFine = 0;
		if(!finePaid) {
			Date iDate = issuedDate;
			Date rDate = new Date();
			
			long days = ChronoUnit.DAYS.between(rDate.toInstant(), iDate.toInstant());
			days = 0 - days;
//			RESUME
		}
		return totalFine;
	}
	public void payFine() {
		double totalFine = computeFine();
		if(totalFine > 0) {
			System.out.println("Total Fine Generated: Result " + totalFine);
			System.out.println("Do you want to pay ? (Y/N)");
			Scanner input = new Scanner(System.in);
			String choice = input.next();
			if(choice.equals("Y") || choice.equals("y")) {
				finePaid = true;
				
			}
			if(choice.equals("N") || choice.equals("n")) {
				finePaid = false;
			}
		} else {
			System.out.println("No fine is generated");
			finePaid = true;
		}
	}
	public void renewIssueBook(Date date) {
		issuedDate = date;
		System.out.println("The deadline of the book " + getBook().getTitle() + " has been extended");
		System.out.println("Issued Book is successfully renewed!");
	}
}
