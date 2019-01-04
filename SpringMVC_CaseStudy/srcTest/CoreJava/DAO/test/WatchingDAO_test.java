package CoreJava.DAO.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springwork.DAO.OracleConnection;
import com.springwork.DAO.WatchingDAO;
import com.springwork.Models.Job;

import oracle.jdbc.driver.OracleDriver;

class WatchingDAO_test extends OracleConnection{

	private final static String url = "jdbc:oracle:thin:@localhost:1521:javasms";
	private final static String user = "SYSTEM";
	private final static String password = "P@ssw0rd";

	protected static Connection conn = null;
	
	private WatchingDAO watchingDAO = new WatchingDAO();
	
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
	final void testCheckIfWatching() {
		boolean expectedResult = false;
		boolean actualResult = watchingDAO.checkIfWatching("b@gmail.com", 8);
		
		//Positive
		assertEquals(expectedResult, actualResult);
	}

	@Test
	final void testCreateNewWatching() {
		watchingDAO.createNewWatching("b@gmail.com", 8);
		
		boolean expectedResult = false;
		boolean actualResult = watchingDAO.checkIfWatching("b@gmail.com", 8);
		
		//Positive
		assertEquals(expectedResult, actualResult);
		
	}

	@Test
	final void testUserWatchingJobs() {
		List<Job> jobList = watchingDAO.userWatchingJobs("b@gmail.com");
		Job actualResult = jobList.get(0);
		Job expectedResult = new Job(8, "test", "Alabama", "fulltime", 123, "test", "test", "a@gmail.com");
		
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
	final void testRemoveFromWatching() {
		int actualResult = watchingDAO.removeFromWatching("a@gmail.com", 1);
		int expectedResult = 1;
		
		assertEquals(expectedResult, actualResult);
		
	}

}
