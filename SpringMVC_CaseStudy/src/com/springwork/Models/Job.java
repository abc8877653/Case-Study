/**
 * 
 */
package com.springwork.Models;

/**
 * @author Students
 *
 */
public class Job {

	private int jid;
	private String title;
	private String location;
	private String jobType;
	private int annual_Salary;
	private String description;
	private String company;
	private String email;
	
	public Job() {
		
	}
	
	public Job(int jid, String title, String location, String jobType, int annual_Salary,
			String description, String company, String email) {
		this.jid = jid;
		this.title = title;
		this.company = company;
		this.location = location;
		this.jobType = jobType;
		this.annual_Salary = annual_Salary;
		this.description = description;
		this.email = email;
	}

	/**
	 * @return the jid
	 */
	public int getJid() {
		return jid;
	}

	/**
	 * @param jid the jid to set
	 */
	public void setJid(int jid) {
		this.jid = jid;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the jobType
	 */
	public String getJobType() {
		return jobType;
	}

	/**
	 * @param jobType the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	/**
	 * @return the annual_Salary
	 */
	public int getAnnual_Salary() {
		return annual_Salary;
	}

	/**
	 * @param annual_Salary the annual_Salary to set
	 */
	public void setAnnual_Salary(int annual_Salary) {
		this.annual_Salary = annual_Salary;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	
	
	
}
