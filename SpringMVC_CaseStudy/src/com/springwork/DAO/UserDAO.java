package com.springwork.DAO;

import java.sql.SQLException;

import com.springwork.DAOI.UserDAOI;
import com.springwork.Models.User;


public class UserDAO extends OracleConnection implements UserDAOI {

	/*
	 * Create new user
	 * @oaram user
	 * return 0 / -1
	 */
	@Override
	public int createNewUser(User user) {
		connect();
		try {
			ps = conn.prepareStatement(SQL.REGISTER_NEW_USER.getQuery());

			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getfName());
			ps.setString(4, user.getlName());

			int psResult = ps.executeUpdate();

			if (psResult == 1)
				return 0;
			else
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} 
	}
	
	/*
	 * Get User object by email
	 * @oaram email
	 * @return user
	 */
	@Override
	public User getUserByEmail(String email) {
		connect();
		try {
			ps = conn.prepareStatement(SQL.CHECK_IF_REGISTERED.getQuery());
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				return user;
			}
			conn.close();
		} catch (Exception e) {
			System.out.println("Get User Error!");
		}
		return null;
	}
	
	/*
	 * Validate user password
	 * @param databasePass, userInputPass
	 * @return boolean
	 */
	@Override
    public boolean validateUser(String databasePass, String userInputPass){
    	return databasePass.equals(userInputPass);
    }
}
