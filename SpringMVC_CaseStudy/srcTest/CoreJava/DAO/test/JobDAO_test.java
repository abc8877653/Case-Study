package CoreJava.DAO.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springwork.DAO.JobDAO;
import com.springwork.Models.Job;

import oracle.jdbc.driver.OracleDriver;

class JobDAO_test {

	private final static String url = "jdbc:oracle:thin:@localhost:1521:javasms";
	private final static String user = "SYSTEM";
	private final static String password = "P@ssw0rd";

	protected static Connection conn = null;
	
	private JobDAO jobDAO = new JobDAO();
	
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
	final void testCreateNewJob() {
		Job job = new Job(1, "test", "Alabama", "fulltime", 123, "test", "test", "a@gmail.com");
		int actualResult = jobDAO.createNewJob(job);
		int expectedResult = -1;
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	final void testSearchJob() {
		List<Job> jobList = jobDAO.searchJob("test", "");
		Job actualResult = jobList.get(0);
		Job expectedResult = new Job(1, "test", "Alabama", "fulltime", 123, "test", "test", "a@gmail.com");
		
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
	final void testGetUserJobsByEmail() {
		List<Job> jobList = jobDAO.getUserJobsByEmail("a@gmail.com");
		Job actualResult = jobList.get(0);
		Job expectedResult = new Job(1, "test", "Alabama", "fulltime", 123, "test", "test", "a@gmail.com");
		
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
	final void testCreateNewJID() {
		int expectedResult = 17;
		int actualResult = jobDAO.createNewJID();
		
		assertEquals(expectedResult, actualResult);
	}

}
