package com.springwork.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springwork.DAO.ApplicationDAO;
import com.springwork.DAO.JobDAO;
import com.springwork.DAO.UserDAO;
import com.springwork.DAO.WatchingDAO;
import com.springwork.Models.Application;
import com.springwork.Models.Job;
import com.springwork.Models.User;

@Controller
public class MainController {

	/*
	 * Login page
	 */
	@RequestMapping("/")
	public ModelAndView login(HttpSession session) {
		ModelAndView mav = new ModelAndView("login");
		session.setAttribute("user", null);
		return mav;
	}
	
	/*
	 * Validate user login information. Set user in session to user object if passes validation
	 * and moves to search page.
	 * Stay in login page with error message if wrong / empty credentials.
	 */
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView processLogin(HttpSession session,@RequestParam("email") String email, 
			@RequestParam("password") String password) {
		ModelAndView mav = new ModelAndView("login");
		User user = new UserDAO().getUserByEmail(email);
		
		if (user == null) {
			mav.addObject("loginError",  "Wrong Credentials");
			return mav;
		}
		boolean validateUser = new UserDAO().validateUser(user.getPassword(), password);
		if(validateUser) {
			session.setAttribute("user", user);
			mav = new ModelAndView("search");
			return mav;
		}
		mav.addObject("loginError",  "Wrong Credentials");
		return mav;
	}
	
	/*
	 * Search page
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		return new ModelAndView("search");
	}
	
	/*
	 * Profile page
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profilePage(HttpSession session) {
		ModelAndView mav = new ModelAndView("profile");
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		return mav;
	}
	
	/*
	 * User applies to job.
	 * Checks if the application already exists or user is watching the job
	 * Status messages based on which case is true
	 */
	@RequestMapping(value = "/applyToJob", method = RequestMethod.GET)
	public ModelAndView applyToJob(HttpSession session) {
		ModelAndView mav = new ModelAndView("profile");
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		User user = (User) session.getAttribute("user");
		Job job = (Job) session.getAttribute("job");
		
		//Check if user is applied to / watching the job
		if (new ApplicationDAO().checkIfApplied(user.getEmail(), job.getJid()) && new WatchingDAO().checkIfWatching(user.getEmail(), job.getJid())) {
			// Check if the user created this job posting
			if(user.getEmail().equals(job.getEmail())) {
				mav.addObject("ApplicationStatus", "Cannot Apply To Own Posting");
				return mav;
			}
			// Apply user to job
			new ApplicationDAO().createNewApplicant(user.getEmail(), job.getJid());
			mav.addObject("ApplicationStatus", "Application Successful");
			return mav;
		}
		mav.addObject("ApplicationStatus", "Already applied to or watching job");
		return mav;
		
	}
	
	/*
	 * Add job to user's watching list.
	 */
	@RequestMapping(value = "/watchJob", method = RequestMethod.GET)
	public ModelAndView watchJob(HttpSession session) {
		ModelAndView mav = new ModelAndView("profile");
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		User user = (User) session.getAttribute("user");
		Job job = (Job) session.getAttribute("job");
		
		//Check if user is watching / applied to job
		if (new ApplicationDAO().checkIfApplied(user.getEmail(), job.getJid()) && new WatchingDAO().checkIfWatching(user.getEmail(), job.getJid())) {
			//Check if user created job posting
			if(user.getEmail().equals(job.getEmail())) {
				mav.addObject("WatchingStatus", "Cannot Watch Own Posting");
				return mav;
			}
			// Successful watching
			new WatchingDAO().createNewWatching(user.getEmail(), job.getJid());
			mav.addObject("WatchingStatus", "Watching Successful");
			return mav;
		}
		mav.addObject("WatchingStatus", "Already watching or applied to job");
		return mav;
		
	}
	
	/*
	 * Unwatch a job
	 */
	@RequestMapping(value = "/unwatchJob", method = RequestMethod.POST)
	public ModelAndView unwatchJob(HttpSession session) {
		ModelAndView mav = new ModelAndView("jobPage");
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Job job = (Job) session.getAttribute("job");
		User user = (User) session.getAttribute("user");
		
		//Check if user is watching the job before allowing removal
		if (!new WatchingDAO().checkIfWatching(user.getEmail(), job.getJid())) {
			new WatchingDAO().removeFromWatching(user.getEmail(), job.getJid());
			mav.addObject("unwatchStatus", "Job Removed From Watching");
			return mav;
		}
		mav.addObject("unwatchStatus", "You are not watching this job");
		return mav;
	}
	
	/*
	 * Next three methods are for the user profile page
	 * List of Jobs for the three buttons, Applications, Watching and My Posts
	 */
	@RequestMapping(value = "/myApplicationList", method = RequestMethod.GET)
	public ModelAndView myApplicationList(HttpSession session) {
		ModelAndView mav = new ModelAndView("profile");
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		User user = (User) session.getAttribute("user");
		List<Job> jobList = new ApplicationDAO().userAppliedJobs(user.getEmail());
		mav.addObject("jobList", jobList);
		session.setAttribute("jobList", jobList);
		return mav;
	}
	
	@RequestMapping(value = "/myWatchingList", method = RequestMethod.GET)
	public ModelAndView myWatchingList(HttpSession session) {
		ModelAndView mav = new ModelAndView("profile");
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		User user = (User) session.getAttribute("user");
		List<Job> jobList = new WatchingDAO().userWatchingJobs(user.getEmail());
		mav.addObject("jobList", jobList);
		session.setAttribute("jobList", jobList);
		return mav;
	}
	
	@RequestMapping(value = "/myJobsList", method = RequestMethod.GET)
	public ModelAndView myJobsList(HttpSession session) {
		ModelAndView mav = new ModelAndView("profile");
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		User user = (User) session.getAttribute("user");
		List<Job> jobList = new JobDAO().getUserJobsByEmail(user.getEmail());
		mav.addObject("jobList", jobList);
		session.setAttribute("jobList", jobList);
		return mav;
	}
	
	// ------------------------------------------------------------------------------------
	
	/*
	 * Register Page
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerPage(HttpSession session) {
		if(session.getAttribute("user") != null) {
			session.setAttribute("user", null);
		}
		return new ModelAndView("register");
	}
	
	/*
	 * Register process
	 */
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView registerProcess(@ModelAttribute("userModel") User user) {
		ModelAndView mav = new ModelAndView();
		
		if(new UserDAO().getUserByEmail(user.getEmail()) != null) {
			mav = new ModelAndView("register");
			mav.addObject("registerStatus", "Email Already Exists");
		}
	
		int success = new UserDAO().createNewUser(user);
		
		//Check if registration was successful
		if (success == -1) {
			mav = new ModelAndView("register");
			mav.addObject("registerStatus", "Failed to Register");
		}
		else {
			mav = new ModelAndView("login");
			mav.addObject("registerStatus", "Registration Successful");
		}
		return mav;
	}
	
	/*
	 * Create job page
	 */
	@RequestMapping(value = "/createJob", method = RequestMethod.GET)
	public ModelAndView createJobPage(HttpSession session) {
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		return new ModelAndView("createJob");
				
	}
	
	/*
	 * Job creation process
	 */
	@RequestMapping(value = "/createJobProcess", method = RequestMethod.POST)
	public ModelAndView createJobProcess(HttpSession session, @RequestParam("title") String title, @RequestParam("company") String company, @RequestParam("location") String location, @RequestParam("jobType") String jobType,
			@RequestParam("annual_salary") int annual_salary, @RequestParam("description") String description) {
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		ModelAndView mav = new ModelAndView();
		User user = (User) session.getAttribute("user");
		//Create new jid and gets user email for the new job
		Job job = new Job(new JobDAO().createNewJID(), title, location, jobType, annual_salary, description, company, user.getEmail());

		
		int success = new JobDAO().createNewJob(job);
		
		// Checks if job creation was successful
		if (success == -1) {
			mav = new ModelAndView("createJob");
			mav.addObject("createJobStatus", "Failed to Create Job");
		}
		else {
			mav = new ModelAndView("search");
			mav.addObject("createJobStatus", "Job Created");
		}
		return mav;
	}
	
	/*
	 * Job Page
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/jobPage", method = RequestMethod.GET)
	public ModelAndView jobPage(@RequestParam("jobNumber") int jobNumber, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		ModelAndView mav = new ModelAndView("jobPage");
		//Get the job the user clicked on using jobList and jobNumber as index
		List<Job> jobList = (List<Job>) session.getAttribute("jobList");
		Job job = jobList.get(jobNumber - 1);
		User user = (User) session.getAttribute("user");
		if(user.getEmail().equals(job.getEmail())) {
			mav = new ModelAndView("posterJobPage");
			mav.addObject(job);
			session.setAttribute("job", job);
			return mav;
		}
		mav.addObject(job);
		session.setAttribute("job", job);
		return mav;
	}
	
	@RequestMapping(value = "/posterJobPage", method = RequestMethod.GET)
	public ModelAndView posterJobPage(HttpSession session) {
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		return new ModelAndView("posterJobPage");
	}
	
	
	@RequestMapping(value = "/applicantsList", method = RequestMethod.GET)
	public ModelAndView applicantsList(HttpSession session) {
		if(session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		ModelAndView mav= new ModelAndView("applicantsList");
		Job job = (Job) session.getAttribute("job");
		List<Application> applicantsList = new ApplicationDAO().myJobApplicants(job.getJid());
		mav.addObject("applicantsList", applicantsList);
		return mav;
	}
	
	/*
	 * Job Search Process
	 */
	@RequestMapping(value = "jobSearchProcess", method = RequestMethod.GET)
	public ModelAndView jobSearchProcess(@RequestParam("jobTitle") String jobTitle, @RequestParam("jobLocation") String jobLocation, HttpSession session) {
		ModelAndView mav = new ModelAndView("jobSearchResults");
		if (jobTitle.equals("")) {
			mav.addObject("searchStatus", "Please enter a job title");
			return mav;
		}
		List <Job> jobList = new JobDAO().searchJob(jobTitle, jobLocation);
		mav.addObject("jobList", jobList);
		session.setAttribute("jobList", jobList);
		return mav;
		
	}
	
	@ModelAttribute("userModel")
	public User setupUserModel(String email, String password, String fName, String lName) {
		return new User(email, password, fName, lName);
	}	
}
