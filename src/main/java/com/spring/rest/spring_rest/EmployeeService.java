package com.spring.rest.spring_rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeService {
	
	@PersistenceContext
	EntityManager em;
	
//	@Autowired
//	private EntityManagerFactory emf;
	
	public List<Employee> getAllEmployees() {
		TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_ALL_EMPLOYEES, Employee.class);
		List<Employee> results = query.getResultList();
		return results;
	}

	public Employee getEmployee(int id) {
		Employee employee = em.find(Employee.class,id);
		return employee;
	}
	
	public void deleteById(int id) {
	//	em.getTransaction().begin();
		em.remove(em.find(Employee.class,id));
	//	em.getTransaction().commit();
	}
	
	public void addEmployee(int employeeId, String employeeName, int employeeAge,String employeeAddress, int employeeSalary){
		Employee e = new Employee();
		e.setEmployeeId(employeeId);
		e.setEmployeeName(employeeName);
		e.setEmployeeAge(employeeAge);
		e.setEmployeeAddress(employeeAddress);
		e.setEmployeeSalary(employeeSalary);
		//em = emf.createEntityManager();
		em.persist(e);
	}
}
