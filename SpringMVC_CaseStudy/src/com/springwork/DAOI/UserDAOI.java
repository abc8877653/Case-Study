package com.springwork.DAOI;

import com.springwork.Models.User;

public interface UserDAOI {

	enum SQL{
		CHECK_IF_REGISTERED("SELECT * FROM users WHERE email = ?"),
		REGISTER_NEW_USER("INSERT INTO users VALUES(?,?,?,?)");
			
		private final String query;
		private SQL(String query) {
			this.query = query;
		}
		
		public String getQuery() {
			return this.query;
		}
	}
	
	public int createNewUser(User user);
	
	public User getUserByEmail(String email);
	
	public boolean validateUser(String databasePass, String userInputPass);
}
