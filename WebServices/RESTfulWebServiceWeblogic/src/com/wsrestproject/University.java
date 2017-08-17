package com.wsrestproject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value = "/university")
public class University {
	
	@GET
	@Path("/htmlval")
	@Produces(value = MediaType.TEXT_HTML)
	public String getHTMLUniversityInfo() {
		return "<html><head><title>University Information</title></head>"
				+ "<body><h1>Indian University</h1></body>";
	}
	
	@GET
	@Path("/textval")
	@Produces(value = MediaType.TEXT_PLAIN)
	public String getXMLLUniversityInfo() {
		return "Name: Indian University";
	}
	@GET
	@Path("/calculator/addition")
	@Produces(value = MediaType.TEXT_PLAIN)
	public String calc(int num1, int num2) {
		 String value = String.valueOf(num1+num2);
		 		 return value;
	}
}
