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
		if(!holdRequests.isEmpty()) {
			holdRequests.remove(0);
		}
	}
//	PRINTING ALL HOLD REQUEST
	public void printHoldRequest() {
		if(!holdRequests.isEmpty()) {
			System.out.println("==============================");
			System.out.println("No.\t\tBook's Title\t\tBorrower's Name\t\tRequest Date");
			System.out.println("==============================");
			for(int i = 0; i < holdRequests.size(); i++) {
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
	public void changeBookInfo() throws Exception{
		Scanner input = new Scanner(System.in);
			
	}
	
}
