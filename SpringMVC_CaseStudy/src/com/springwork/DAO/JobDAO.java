package com.springwork.DAO;

import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.springwork.DAOI.JobDAOI;
import com.springwork.Models.Job;

public class JobDAO extends OracleConnection implements JobDAOI{

	/*
	 * Create a new job
	 * @param job
	 * @return 0 / -1
	 */
	@Override
	public int createNewJob(Job job) {
		connect();
		try {
			ps = conn.prepareStatement(SQL.CREATE_NEW_JOB.getQuery());

			ps.setInt(1, job.getJid());
			ps.setString(2, job.getTitle());
			ps.setString(3, job.getLocation());
			ps.setString(4, job.getJobType());
			ps.setInt(5, job.getAnnual_Salary());
			ps.setString(6, job.getDescription());
			ps.setString(7, job.getCompany());
			ps.setString(8, job.getEmail());

			int psResult = ps.executeUpdate();

			if (psResult == 1)
				return 0;
			else
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			dispose();
		}
	}
	
	/*
	 * Search for jobs with/without location
	 * @param jobTitle, jobLocation
	 * @return jobList
	 */
	@Override
	public List<Job> searchJob(String jobTitle, String jobLocation) {
		jobTitle = jobTitle.toLowerCase();
		List<Job> jobList = new ArrayList<Job>();
		connect();
		String[] jobTitleArr = jobTitle.split(" ");
		try {
			//If location is not used
			if (jobLocation.equals("")) {
				if (jobTitleArr.length == 1) {
					ps = conn.prepareStatement(SQL.GET_JOBS_V1.getQuery());
					ps.setString(1, "%" + jobTitle + "%");
				}
				if (jobTitleArr.length == 2) {
					ps = conn.prepareStatement(SQL.GET_JOBS_V2.getQuery());
					ps.setString(1, "%" + jobTitle + "%");
					ps.setString(2, "%" + jobTitleArr[0] + "%");
					ps.setString(3, "%" + jobTitleArr[1] + "%");
				}
				if (jobTitleArr.length == 3) {
					ps = conn.prepareStatement(SQL.GET_JOBS_V3.getQuery());
					ps.setString(1, "%" + jobTitle + "%");
					ps.setString(2, "%" + jobTitleArr[0] + "%");
					ps.setString(3, "%" + jobTitleArr[1] + "%");
					ps.setString(4, "%" + jobTitleArr[2] + "%");
				}
				rs = ps.executeQuery();
				while(rs.next()) {
					jobList.add(new Job(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8)));
				}
				return jobList;
			}
			
			//If location is used for additional search parameter
			jobLocation = jobLocation.toLowerCase();
			if (jobTitleArr.length == 1) {
				ps = conn.prepareStatement(SQL.GET_JOBS_WITH_LOCATION_V3.getQuery());
				ps.setString(1, "%" + jobTitle + "%");
				ps.setString(2, jobLocation);
			}
			if (jobTitleArr.length == 2) {
				ps = conn.prepareStatement(SQL.GET_JOBS_WITH_LOCATION_V3.getQuery());
				ps.setString(1, "%" + jobTitle + "%");
				ps.setString(2, "%" + jobTitleArr[0] + "%");
				ps.setString(3, "%" + jobTitleArr[1] + "%");
				ps.setString(4, jobLocation);
			}
			if (jobTitleArr.length == 3) {
				ps = conn.prepareStatement(SQL.GET_JOBS_WITH_LOCATION_V3.getQuery());
				ps.setString(1, "%" + jobTitle + "%");
				ps.setString(2, "%" + jobTitleArr[0] + "%");
				ps.setString(3, "%" + jobTitleArr[1] + "%");
				ps.setString(4, "%" + jobTitleArr[2] + "%");
				ps.setString(5, jobLocation);
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				jobList.add(new Job(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
			return jobList;
		} catch (Exception e) {
			System.out.println("Job Search Error!");
		}
		return null;
	}

	/*
	 * Get jobs posted by a user
	 * @param email
	 * @return jobList
	 */
	@Override
	public List<Job> getUserJobsByEmail(String email) {
		List<Job> jobList = new ArrayList<Job>();
		connect();
		try {
			ps = conn.prepareStatement(SQL.GET_JOBS_BY_EMAIL.getQuery());
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
	 * Create new JID for the new job
	 */
	public int createNewJID() {
		connect();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(SQL.CREATE_UNIQUE_ID.getQuery());
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			System.out.println("Job ID Error!");
		}
		return 1;
	}

}
