package CoreJava.DAO.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springwork.DAO.ApplicationDAO;
import com.springwork.Models.Application;
import com.springwork.Models.Job;

import oracle.jdbc.driver.OracleDriver;

class ApplicationDAO_test {

	private final static String url = "jdbc:oracle:thin:@localhost:1521:javasms";
	private final static String user = "SYSTEM";
	private final static String password = "P@ssw0rd";

	protected static Connection conn = null;
	
	private ApplicationDAO applicationDAO = new ApplicationDAO();
	
	@BeforeClass
	static void setUpBeforeClass() throws Exception {
		DriverManager.registerDriver(new OracleDriver());
		DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:javasms", "SYSTEM", "P@ssw0rd");
	}

	@BeforeEach
	void setUp() throws Exception {
		conn = DriverManager.getConnection(url, user, password);
	}

	@Test
	final void testCheckIfApplied() {
		boolean expectedResult = false;
		boolean actualResult = applicationDAO.checkIfApplied("b@gmail.com", 1);
		
		//Postive
		assertEquals(expectedResult, actualResult);
	}

	@Test
	final void testCreateNewApplicant() {
		applicationDAO.createNewApplicant("b@gmail.com", 8);
		
		boolean expectedResult = false;
		boolean actualResult = applicationDAO.checkIfApplied("b@gmail.com", 8);
		
		//Positive
		assertEquals(expectedResult, actualResult);
	}

	@Test
	final void testUserAppliedJobs() {
		List<Job> jobList = applicationDAO.userAppliedJobs("b@gmail.com");
		Job actualResult = jobList.get(0);
		Job expectedResult = new Job(1, "test", "Alabama", "fulltime", 123, "test", "test", "a@gmail.com");
		
		//Positive
		assertEquals(expectedResult.getJid(), actualResult.getJid());
		assertEquals(expectedResult.getTitle(), actualResult.getTitle());
		assertEquals(expectedResult.getLocation(), actualResult.getLocation());
		assertEquals(expectedResult.getJobType(), actualResult.getJobType());
		assertEquals(expectedResult.getAnnual_Salary(), actualResult.getAnnual_Salary());
		assertEquals(expectedResult.getDescription(), actualResult.getDescription());
		assertEquals(expectedResult.getCompany(), actualResult.getCompany());
		assertEquals(expectedResult.getEmail(), actualResult.getEmail());
	}

	@Test
	final void testMyJobApplicants() {
		List<Application> applicantsList = applicationDAO.myJobApplicants(1);
		
		Application actualResult = applicantsList.get(1);
		Application expectedResult = new Application("b@gmail.com", "yu", "wang");
		
		//Positive
		assertEquals(expectedResult.getEmail(), actualResult.getEmail());
		assertEquals(expectedResult.getfName(), actualResult.getfName());
		assertEquals(expectedResult.getlName(), actualResult.getlName());
		
	}

}
