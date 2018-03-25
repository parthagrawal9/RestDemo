package com.spring.rest.spring_rest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

//@NamedQueries({
//	@NamedQuery(name = Employee.FIND_ALL_EMPLOYEES, query = "select e from Employee e"),
//  @NamedQuery(name = Employee.FIND_EMPLOYEE_BY_ID, query="select e from Employee e where e.id = :id")
//}) 

@Entity
@NamedQuery(name = Employee.FIND_ALL_EMPLOYEES, query = "select e from Employee e")
public class Employee {

	protected static final String FIND_ALL_EMPLOYEES = "find_all_employees";
	
	@Id
	private int employeeId;
	private String employeeName;
	private int employeeAge;
	private String employeeAddress;
	private int employeeSalary;
	
	public Employee(int employeeId, String employeeName, int employeeAge,String employeeAddress, int employeeSalary) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
		this.employeeAddress = employeeAddress;
		this.employeeSalary = employeeSalary;
	}

	public Employee() {
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public float getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(int employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
}
