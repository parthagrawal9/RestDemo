package com.spring.rest.spring_rest;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeService {
	
	@PersistenceContext
	EntityManager em;

	public List<Employee> getAllEmployees() {
		TypedQuery<Employee> query = em.createNamedQuery(Employee.FIND_ALL_EMPLOYEES, Employee.class);
		List<Employee> results = query.getResultList();
		return results;
	}

	public Employee getEmployee(int id) {
		Employee employee = em.find(Employee.class,id);
		return employee;
	}	
}
