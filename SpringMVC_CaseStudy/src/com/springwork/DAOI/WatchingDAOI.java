package com.springwork.DAOI;

import java.util.List;

import com.springwork.Models.Job;

public interface WatchingDAOI {

	
	enum SQL{
		CHECK_WATCHING("SELECT * FROM watching WHERE email = ? AND jid = ?"),
		WATCH_JOB("INSERT INTO watching VALUES (?, ?)"),
		GET_JOBS_WATCHING("SELECT * FROM jobs "
				+ "JOIN watching ON jobs.jid = watching.jid "
				+ "WHERE watching.email = ?"),
		REMOVE_FROM_WATCHING("DELETE FROM watching WHERE email = ? AND jid = ?");
		
		private final String query;
		private SQL(String query) {
			this.query = query;
		}
		
		public String getQuery() {
			return this.query;
		}
	}
	
	public boolean checkIfWatching(String email, int jid);
	
	public void createNewWatching(String email, int jid);
	
	public List<Job> userWatchingJobs(String email); 
	
	public int removeFromWatching(String email, int jid);
}
