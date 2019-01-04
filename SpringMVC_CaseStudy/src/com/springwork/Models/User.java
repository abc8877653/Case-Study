package com.springwork.Models;

public class User {

	private String email;
	private String password;
	private String fName;
	private String lName;
	
	public User(){
		
	}
	
	public User(String email, String password, String fName, String lName) {
		super();
		this.email = email;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the fname
	 */
	public String getfName() {
		return fName;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fName) {
		this.fName = fName;
	}
	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}
	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	
	
}
