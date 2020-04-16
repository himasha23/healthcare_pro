package com.codebin.project.healthcare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Patientmokup {

	List<Patient> patients = new ArrayList<Patient>();
	
	public Patientmokup(){
		

		
		Patient patient1 = new Patient("1","vindi","colombo","13244","12","see");
		Patient patient2 = new Patient("2","kasun","galle","132234","22","redo");
		Patient patient3 = new Patient("3","su","mana","112244","32","nowa");
		
		patients = Arrays.asList(patient1,patient2,patient3);
		
		
	}
	
	public List<Patient> getPatients() throws SQLException{
		
		List<Patient> patients = new ArrayList<Patient>();
		
		ResultSet result = getConnection().createStatement().executeQuery("select * from patient");
		while(result.next()) {
			
			Patient pat = new Patient();
			
			pat.setPid(result.getString(1));
			pat.setPname(result.getString(2));
			pat.setPaddress(result.getString(3));
			pat.setPmobile(result.getString(4));
			pat.setPage(result.getString(5));
			pat.setPwd(result.getString(6));
			
			patients.add(pat);
		}
		return patients;
	
	
	}
	
	/*
	public Patient getPatient(String pname) {
		
	return patients.stream().filter(p -> p.getPname().equals(pname)).findAny().orElse(null);
		
	} 
	
	*/
	
	public void createpatient(Patient patient) throws Exception{
		
		 PreparedStatement ps = getConnection().prepareStatement("insert into patient (pid,pname,paddress,pmobile,page,pwd) values (?,?,?,?,?,?)");
		ps.setString(1, patient.getPid());
		ps.setString(2, patient.getPname());
		ps.setString(3, patient.getPaddress());
		ps.setString(4, patient.getPmobile());
		ps.setString(5, patient.getPage());
		ps.setString(6, patient.getPwd());
		
		ps.executeUpdate();
		
	}
	
	
	public void updatepatient(Patient patient) throws Exception{
		
		 PreparedStatement ps = getConnection()
				 .prepareStatement("update patient set pid=?,paddress=?,pmobile=?,page=?,pwd=? where pname=?");
		ps.setString(1, patient.getPid());
		ps.setString(2, patient.getPname());
		ps.setString(3, patient.getPaddress());
		ps.setString(4, patient.getPmobile());
		ps.setString(5, patient.getPage());
		ps.setString(6, patient.getPwd());
		
			
		int count = ps.executeUpdate();
			
			System.out.println("Updated Count :"+count);
			
		}
		
		//System.out.println("Update Count :" +count +count +count +count +count +count);
		
		
		public void droppatient(String name) throws Exception{
			
			 PreparedStatement ps = getConnection().prepareStatement("delete from patient where pname=?");
			ps.setString(1, name);
			
			int count = ps.executeUpdate();
			
			System.out.println("Updated Count :"+count);
	}
	
	
	
	
public Connection getConnection() throws SQLException{
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf_db","root","");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
}
