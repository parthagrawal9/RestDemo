package com.spring.rest.spring_rest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {

	final private List<String> dataList = new ArrayList<String>(Arrays.asList("Spring", "Rest", "SRC"));

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/data/{id}")
	public Data getData(@RequestParam(value = "id", defaultValue = "0") Integer id) {
		return new Data(id, dataList.get(id));
	}

	@RequestMapping("/data")
	public List<Data> getAllData() {
		List<Data> allData = new ArrayList<Data>();
		for (int i = 0; i < dataList.size(); i++) {
			allData.add(new Data(i, dataList.get(i)));
		}
		return allData;
	}

	@RequestMapping("/employees")
	public List<Employee> getAllEmplloyeeData() {
		return jdbcTemplate.query("SELECT * FROM COMPANY", new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("id"));
				employee.setEmployeeName(rs.getString("name"));
				employee.setEmployeeAge(rs.getInt("age"));
				employee.setEmployeeAddress(rs.getString("address").trim());
				employee.setEmployeeSalary(rs.getInt("salary"));
				return employee;
			}
		});
	}

	@RequestMapping("/employeesJPA")
	public List<Employee> getAllEmplloyeeDataJPA() {
		return employeeService.getAllEmployees();
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
//		employeeService.save(employee);
//		//logger.debug("Added:: " + employee);
//		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
//	}
}











