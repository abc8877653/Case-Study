package com.springwork.Models;

public class Watching {

	private String email;
	private int jid;
	
	
	public Watching(String email, int jid) {
		this.email = email;
		this.jid = jid;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the jid
	 */
	public int getJid() {
		return jid;
	}
	/**
	 * @param jid the jid to set
	 */
	public void setJid(int jid) {
		this.jid = jid;
	}
	
}
