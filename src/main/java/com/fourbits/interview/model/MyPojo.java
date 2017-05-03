
package com.fourbits.interview.model;

//import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.NotEmpty;

public class MyPojo {
	
	private int id;
//	@NotEmpty
//    @Length(max = 140)
	private String fname;
	private String lname;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
