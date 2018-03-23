package com.spring.rest.spring_rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {
	
	final private List<String> dataList = new ArrayList<String>(Arrays.asList("Spring","Rest","SRC"));
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@RequestMapping("/data/{id}")
	public Data getData(@RequestParam(value="id", defaultValue="0") Integer id){
		return new Data(id, dataList.get(id));
	}
	
	@RequestMapping("/data")
	public List<Data> getAllData(){
		List<Data> allData = new ArrayList<Data>();

		allData.clear();
		for (int i = 0; i < dataList.size(); i++) {
			allData.add(new Data(i, dataList.get(i)));
		}
		return allData;
	}	
	
	@RequestMapping("/employees")
	public List<Employee> getAllEmplloyeeData(){	
		List<Employee> allEmployeeData= new ArrayList<Employee>();
		return jdbcTemplate.queryForList("SELECT * FROM COMPANY", Employee.class);
	}
}