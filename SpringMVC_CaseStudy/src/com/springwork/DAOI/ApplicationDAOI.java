package com.springwork.DAOI;

import java.util.List;

import com.springwork.Models.Application;
import com.springwork.Models.Job;
import com.springwork.Models.User;

public interface ApplicationDAOI {

	enum SQL{
		CHECK_APPLICATION("SELECT * FROM application WHERE email = ? AND jid = ?"),
		APPLY_TO_JOB("INSERT INTO application VALUES (?, ?)"),
		GET_JOBS_APPLIED_TO("SELECT * FROM jobs "
				+ "JOIN application ON jobs.jid = application.jid "
				+ "WHERE application.email = ?"),
		GET_MY_JOB_APPLICANTS("SELECT users.email, users.fName, users.lName FROM users "
				+ "JOIN application ON users.email = application.email "
				+ "WHERE application.jid = ?");
		
		private final String query;
		private SQL(String query) {
			this.query = query;
		}
		
		public String getQuery() {
			return this.query;
		}
	}
	
	public boolean checkIfApplied(String email, int jid);
	
	public void createNewApplicant(String email, int jid);
	
	public List<Job> userAppliedJobs(String email); 
	
	public List<Application> myJobApplicants(int jid);
}
