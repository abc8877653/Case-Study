package com.springwork.DAO;

import java.util.ArrayList;
import java.util.List;

import com.springwork.DAOI.WatchingDAOI;
import com.springwork.Models.Job;

public class WatchingDAO extends OracleConnection implements WatchingDAOI{

	/*
	 * Checks if user already watching job
	 * @param email, jid
	 * @return boolean
	 */
	@Override
	public boolean checkIfWatching(String email, int jid) {
		connect();
		try {
			ps = conn.prepareStatement(SQL.CHECK_WATCHING.getQuery());
			ps.setString(1, email);
			ps.setInt(2, jid);
			rs = ps.executeQuery();
			if(rs.next()) {
				return false;
			}
		} catch (Exception e) {
				System.out.println("Check Watching Error!");
		}
		return true;
	}
	
	/*
	 * Add job to user's watching list
	 * @param email, jid
	 */
	@Override
	public void createNewWatching(String email, int jid) {
		connect();
		try {
			ps = conn.prepareStatement(SQL.WATCH_JOB.getQuery());
			ps.setString(1, email);
			ps.setInt(2, jid);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("Create New Watching Error!");
		}
	}
	
	/*
	 * Get a list of jobs the user is watching
	 * @param email
	 * @return jobList
	 */
	@Override
	public List<Job> userWatchingJobs(String email) {
		List<Job> jobList = new ArrayList<Job>(); 
		connect();
		try {
			ps = conn.prepareStatement(SQL.GET_JOBS_WATCHING.getQuery());
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next()) {
				jobList.add(new Job(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
			return jobList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/*
	 * Remove job from user's watching
	 * @param email, jid
	 */
	@Override
	public int removeFromWatching(String email, int jid) {
		connect();
		try {
			ps = conn.prepareStatement(SQL.REMOVE_FROM_WATCHING.getQuery());
			ps.setString(1, email);
			ps.setInt(2, jid);
			rs = ps.executeQuery();
			return 1;
		} catch (Exception e) {
			System.out.println("Remove From Watching Error!");
		}
		return -1;
	}
}
