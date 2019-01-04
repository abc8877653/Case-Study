/**
 * 
 */
package com.springwork.DAOI;

import java.util.List;

import com.springwork.Models.Job;

/**
 * @author Students
 *
 */
public interface JobDAOI {

	
	enum SQL{
		CREATE_UNIQUE_ID("SELECT MAX(jid) FROM jobs"),
		CREATE_NEW_JOB("INSERT INTO jobs VALUES(?,?,?,?,?,?,?,?)"),
		GET_JOBS_BY_EMAIL("SELECT * FROM jobs WHERE email = ?"),
		
		GET_JOBS_V1("SELECT * FROM jobs WHERE LOWER(title) LIKE ?"),
		GET_JOBS_V2("SELECT * FROM jobs WHERE LOWER(title) LIKE ? "
				+ "UNION "
				+ "SELECT * FROM jobs WHERE LOWER(title) LIKE ? "
				+ "UNION "
				+ "SELECT * FROM jobs WHERE LOWER(title) LIKE ? "),
		GET_JOBS_V3("SELECT * FROM jobs WHERE LOWER(title) LIKE ? "
				+ "UNION "
				+ "SELECT * FROM jobs WHERE LOWER(title) LIKE ? "
				+ "UNION "
				+ "SELECT * FROM jobs WHERE LOWER(title) LIKE ? "
				+ "UNION "
				+ "SELECT * FROM jobs WHERE LOWER(title) LIKE ?"),
		
		GET_JOBS_WITH_LOCATION_V1("SELECT * FROM jobs WHERE LOWER(title) LIKE ? WHERE LOWER(location) = ?"),
		GET_JOBS_WITH_LOCATION_V2("SELECT * FROM jobs WHERE LOWER(title) LIKE ? "
				+ "UNION "
				+ "SELECT * FROM jobs WHERE LOWER(title) LIKE ? "
				+ "UNION "
				+ "SELECT * FROM jobs WHERE LOWER(title) LIKE ? WHERE location = ?"),
		GET_JOBS_WITH_LOCATION_V3("SELECT * FROM jobs WHERE LOWER(title) LIKE ? "
				+ "UNION "
				+ "SELECT * FROM jobs WHERE LOWER(title) LIKE ? "
				+ "UNION "
				+ "SELECT * FROM jobs WHERE LOWER(title) LIKE ? "
				+ "UNION "
				+ "SELECT * FROM jobs WHERE LOWER(title) LIKE ? WHERE location = ?");
		
		private final String query;
		private SQL(String query) {
			this.query = query;
		}
		
		public String getQuery() {
			return this.query;
		}
	}
	
	public int createNewJob(Job job);
	
	public List<Job> searchJob(String jobTitle, String jobLocation);
	
	public List<Job> getUserJobsByEmail(String email); 

}
