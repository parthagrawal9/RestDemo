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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {

	final private List<String> dataList = new ArrayList<String>(Arrays.asList("Spring", "Rest", "SRC"));

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/data/{id}")
	public Data getData(@PathVariable("id") Integer id) {
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
	
	@RequestMapping("/employeeJPA/{id}")
	public ResponseEntity<Employee> get(@PathVariable("id") Integer id) {
	   return new ResponseEntity<Employee>(employeeService.getEmployee(id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public void deleteEmpById(@PathVariable("id")Integer id){
		employeeService.deleteById(id);
	}
	
	@RequestMapping(value="/addtest")
	public void addEmp(){
		employeeService.addEmployee(100, "Raghav", 10, "Raghav Address", 100000);
	}

//	
//	@RequestMapping(value="/add/",method=RequestMethod.POST)
//    public ResponseEntity<Employee> emp(@RequestBody Employee e) {
//		System.out.println(e.toString());
//        return new ResponseEntity(e.toString(),HttpStatus.OK);
//    }
}











