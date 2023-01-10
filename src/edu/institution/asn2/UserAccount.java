/* Arron WIlcox
 * CIS 2217 - Assignment 2 - LinkedInUser
 * 05-23-2020
 * This Program was created to be tested and used on future projects. It consists of a user account object with username and password as 
 * data fields and various methods that help implement new user accounts including overridden hashCode, Equals, and toString methods.
 * This Class was updated to an abstract classs which holds one abstract method setType. Which takes a string and
 * sets a particular User account subclass to a specified type.
 */

package edu.institution.asn2;

import java.io.Serializable;

public abstract class UserAccount implements Serializable {
	
	private static final long serialVersionUID = 3L;
	
	private String username,password;
	
	/*** Sets the type of this user.*
	 *  @param type the type.*/
	public abstract void setType(String type);
	
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
	}
	// returns the username supplied in the constructor
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	
	// return true if the argument is the same as this account’s password, 
	// false otherwise 
	public boolean isPasswordCorrect(String password) {
		if(this.password.equals(password)) {
			return true;
		}
		else {
			return false;
		}
		
	}

	//Display the username for the account
	@Override
	public String toString() {
		
		//String prompt = "";
		//prompt+= username;
		return username;
	}

	// uses the username as the unique identifier of an account
	@Override 
	public int hashCode() {
		
		int id = getUsername().hashCode();
		return id;
		
	}

	// two acconts are equal if their username is the same 
		public boolean equals(String username) {
			if (this.username == (username)) {
				return true;
			}
			else {
				return false;
			}
		}
	

		
	
}
