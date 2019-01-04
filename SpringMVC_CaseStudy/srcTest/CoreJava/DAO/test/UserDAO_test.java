package CoreJava.DAO.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.springwork.DAO.UserDAO;
import com.springwork.Models.User;

import oracle.jdbc.driver.OracleDriver;

class UserDAO_test {

	private final static String url = "jdbc:oracle:thin:@localhost:1521:javasms";
	private final static String user = "SYSTEM";
	private final static String password = "P@ssw0rd";

	protected static Connection conn = null;
	
	private UserDAO userDAO = new UserDAO();
	
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
	final void testCreateNewUser() {
		User user = new User("b@gmail.com", "222", "yu", "wamg");
		int actualResult = userDAO.createNewUser(user);
		int expectedResult = -1;
		
		assertEquals(expectedResult, actualResult);
	}

	@Test
	final void testGetUserByEmail() {
		User actualResult = userDAO.getUserByEmail("b@gmail.com");
		User expectedResult = new User("b@gmail.com", "222", "yu", "wang");
		
		assertEquals(expectedResult.getEmail(), actualResult.getEmail());
		assertEquals(expectedResult.getPassword(), actualResult.getPassword());
		assertEquals(expectedResult.getfName(), actualResult.getfName());
		assertEquals(expectedResult.getlName(), actualResult.getlName());
	}

	@Test
	final void testValidateUser() {
		User user = userDAO.getUserByEmail("b@gmail.com");
		boolean actualResult = userDAO.validateUser(user.getPassword(), "222");
		boolean expectedResult = true;
		
		assertEquals(actualResult, expectedResult);
	}

}
