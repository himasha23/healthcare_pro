package com.codebin.project.healthcare;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("patientservice")
public class PatientResource {
	
	Patientmokup mockup = new Patientmokup();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Patient> getPatient() throws SQLException{
		
		
		return mockup.getPatients();
		
		
	}
	
	@POST
	@Path("patient")
	public Patient createnewPatient(Patient p) {
		mockup.createpatient(p);
		return p;
	}

	
/*	@GET
	@Path("patient/{pname}")
	public Patient getPatient(@PathParam("pname") String pname) {
		
		
		return mockup.getPatient(pname);
	}
	
	*/
}
