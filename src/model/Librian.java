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
public class Librian extends Staff{
	private int officeNumber;

	public Librian() {
	}

	public Librian(int officeNumber, double salary, int id, int phone, String name, String password, String address) {
		super(salary, id, phone, name, password, address);
		this.officeNumber = officeNumber;
	}

	@Override
	public void PrintInfo() {
		super.PrintInfo(); //To change body of generated methods, choose Tools | Templates.
		System.out.println("Office Number: " + officeNumber);
	}
	
}
