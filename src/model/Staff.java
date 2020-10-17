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
public class Staff extends Person{
	private double salary;

	public Staff() {
	}
	
	public Staff(double salary, int id, int phone, String name, String password, String address) {
		super(id, phone, name, password, address);
		this.salary = salary;
	}

	@Override
	public void PrintInfo() {
		super.PrintInfo(); //To change body of generated methods, choose Tools | Templates.
		System.out.println("Salary: " + salary);
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	
}
