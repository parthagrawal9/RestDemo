package com.spring.rest.spring_rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		Connection c = null;
    	Statement stmt = null;
	     try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "root");
	         stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
	         while ( rs.next() ) {
	            int id = rs.getInt("id");
	            String  name = rs.getString("name");
	            int age  = rs.getInt("age");
	            String  address = rs.getString("address").trim();
	            float salary = rs.getFloat("salary");
	            allEmployeeData.add(new Employee(id,name,age,address,salary));
	         }
	         rs.close();
	         stmt.close();
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         
	         //System.exit(0);
	      }
		return allEmployeeData;
	}
}