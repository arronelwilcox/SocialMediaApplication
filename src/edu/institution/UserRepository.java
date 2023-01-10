/* Arron Wilcox
 * CIS 2217
 * 

 Copyright (C) 2020. Doug Estep -- All Rights Reserved.
 Copyright Registration Number: TXU002159309.

 This file is part of the Tag My Code application.

 This application is protected under copyright laws and cannot be used, distributed, or copied without prior written
 consent from Doug Estep.  Unauthorized distribution or use is strictly prohibited and punishable by domestic and
 international law.

 Proprietary and confidential.
 */
package edu.institution;

import java.util.List;

import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

/**
 * Defines a class which adds, removes, and retrieves user information.
 */
public interface UserRepository {

	
	
	/**
	 * Sets the supplied file path and file name to properties of this class and
	 * reads (deserializes) previously saved data into a List of LinkedInUser's. Set
	 * the List of LinkedInUser's to a property of this class. If there is no
	 * previously saved data, then this method should initialize a new list of
	 * LinkedInUser's and sets that list to this class.
	 * 
	 * @param filePath the file path to the file containing the user data.
	 * @param fileName the file name containing the user data.
	 * @throws ClassNotFoundException 
	 * 
	 */
	void init(String filePath, String fileName) ;

	
 
	/**
	 * Ensures the supplied user is ready to add and, if so, adds the user to the
	 * list of users established from the init method. A user is ready to add if a
	 * user name, password, and user type is supplied. The user type must also be a
	 * 'P' or an 'S'.
	 * 
	 * @param users the users.
	 * @throws LinkedInException thrown if the user is not ready to add.
	 * 
	 */
	void add(LinkedInUser user) throws LinkedInException;
	
	
		

	/**
	 * Overwrites (serializes) the list of LinkedInUser's set on this class to the
	 * file system.
	 * @param userRepository 
	
	 * @throws ClassNotFoundException 
	 * @throws LinkedInException 
	 */
	void saveAll();
	

	/**
	 * Removes the supplied LinkedIn user from the list of LinkedInUser's set to
	 * this class and serializes the list of LinkedInUser's set on this class to the
	 * file system..
	 * 
	 * @param user the user to delete.
	 */
	void delete(LinkedInUser user);
	
	/**
	 * Returns the LinkedIn user associated with the supplied user name.
	 * 
	 * @param username identifies the user to retrieve.
	 * @return the user or null if not found.
	 */
	LinkedInUser retrieve(String username);
	

	/**
	 * Returns a COPY of all LinkedIn users in the repository.
	 * 
	 * @return the copy of all LinkedIn users or empty list if there are no users in
	 *         the repository.
	 */
	List<LinkedInUser> retrieveAll();
	
}
