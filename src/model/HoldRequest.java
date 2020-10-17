/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Phucdz
 */
public class HoldRequest {
	Borrower borrower;
	Book book;
	Date reqDate;

	public HoldRequest() {
	}

	public HoldRequest(Borrower borrower, Book book, Date reqDate) {
		this.borrower = borrower;
		this.book = book;
		this.reqDate = reqDate;
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

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}
	public void Print() {
		System.out.println(book.getTitle() + "\t\t\t" + "\t\t\t" + reqDate);
	}
}
