package com.spring.rest.spring_rest;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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
		Employee employee = em.find(Employee.class, id);
		return employee;
	}

	@Transactional
	public void deleteEmployee(int id) {
		em.remove(em.find(Employee.class, id));
	}

	@Transactional
	public void addEmployee(Employee e) {
		em.persist(e);
	}
}
