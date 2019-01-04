package com.springwork.DAO;

import java.util.ArrayList;
import java.util.List;

import com.springwork.DAOI.ApplicationDAOI;
import com.springwork.Models.Application;
import com.springwork.Models.Job;

public class ApplicationDAO extends OracleConnection implements ApplicationDAOI{

	/*
	 * Checks if user already applied to the job
	 * @param email, jid
	 * @return boolean
	 */
	@Override
	public boolean checkIfApplied(String email, int jid) {
		connect();
		try {
			ps = conn.prepareStatement(SQL.CHECK_APPLICATION.getQuery());
			ps.setString(1, email);
			ps.setInt(2, jid);
			rs = ps.executeQuery();
			if(rs.next()) {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Application Error!");
		}
		return true;
	}
	
	/*
	 * Applies user to the job
	 * @param email, jid
	 */
	@Override
	public void createNewApplicant(String email, int jid) {
		connect();
		try {
			ps = conn.prepareStatement(SQL.APPLY_TO_JOB.getQuery());
			ps.setString(1, email);
			ps.setInt(2, jid);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("Application Error!");
		}
	}

	/*
	 * Gets a list of jobs that the user applied to
	 * @param email
	 * @return jobList
	 */
	@Override
	public List<Job> userAppliedJobs(String email) {
		List<Job> jobList = new ArrayList<Job>(); 
		connect();
		try {
			ps = conn.prepareStatement(SQL.GET_JOBS_APPLIED_TO.getQuery());
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next()) {
				jobList.add(new Job(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
			return jobList;
		} catch (Exception e) {
			System.out.println("Applied Jobs List Error!");
		}
		return null;
	}

	/*
	 * Gets a list of applicants for a job the user posted
	 * @param jid
	 * @return applicationList
	 */
	@Override
	public List<Application> myJobApplicants(int jid) {
		List<Application> applicationList = new ArrayList<Application>(); 
		connect();
		try {
			ps = conn.prepareStatement(SQL.GET_MY_JOB_APPLICANTS.getQuery());
			ps.setInt(1, jid);
			rs = ps.executeQuery();
			while(rs.next()) {
				applicationList.add(new Application(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
			return applicationList;
		} catch (Exception e) {
			System.out.println("Applicant List Error!");
		}
		return null;
	}

}
