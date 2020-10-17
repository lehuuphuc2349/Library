/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Phucdz
 */
public class Clerk extends Staff{
	private int deskNumber;

	public Clerk() {
	}

	public Clerk(int deskNumber, double salary, int id, int phone, String name, String password, String address) {
		super(salary, id, phone, name, password, address);
		this.deskNumber = deskNumber;
	}

	@Override
	public void PrintInfo() {
		super.PrintInfo(); //To change body of generated methods, choose Tools | Templates.
		System.out.println("Desk Number:" + deskNumber);
	}

	public int getDeskNumber() {
		return deskNumber;
	}

	public void setDeskNumber(int deskNumber) {
		this.deskNumber = deskNumber;
	}
	
	
}
