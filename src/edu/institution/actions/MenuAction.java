/*
 Copyright (C) 2020. Doug Estep -- All Rights Reserved.
 Copyright Registration Number: TXU002159309.

 This file is part of the Tag My Code application.

 This application is protected under copyright laws and cannot be used, distributed, or copied without prior written
 consent from Doug Estep.  Unauthorized distribution or use is strictly prohibited and punishable by domestic and
 international law.

 Proprietary and confidential.
 */
package edu.institution.actions;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.asn2.LinkedInUser;

/**
 * Defines a class which performs an action chosen from the command line user
 * interface menu.
 */
public interface MenuAction {

	/**
	 * Processes a menu action.
	 * 
	 * @param scanner        the scanner accepting user input.
	 * @param userRepository the user repository.
	 * @param loggedInUser   the logged in user.
	 * @return returns true if the logged in user should remain logged in; false if
	 *         they should be logged out.
	 */
	boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser);
}
