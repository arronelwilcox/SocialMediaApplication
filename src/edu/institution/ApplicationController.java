/*
 Copyright (C) 2020. Doug Estep -- All Rights Reserved.
 Copyright Registration Number: TXU002159309.

 This file is part of the Tag My Code application.

 This application is protected under copyright laws and cannot be used, distributed, or copied without prior written
 consent from Doug Estep.  Unauthorized distribution or use is strictly prohibited and punishable by domestic and
 international law.

 Proprietary and confidential.
 */
package edu.institution;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.institution.actions.MenuAction;
import edu.institution.actions.NotImplementedAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.actions.asn3.AddUserAction;
import edu.institution.actions.asn3.DeleteUserAction;
import edu.institution.actions.asn3.ListUserAction;
import edu.institution.actions.asn3.SignoffAction;
import edu.institution.actions.asn4.AddConnectionAction;
import edu.institution.actions.asn4.DegreeOfSeparationAction;
import edu.institution.actions.asn4.ListConnectionAction;
import edu.institution.actions.asn4.RemoveConnectionAction;
import edu.institution.actions.asn6.ListUserAlphabeticallyAction;
import edu.institution.actions.asn6.ListUserByConnectionAction;
import edu.institution.actions.asn6.ListUserByTypeAction;
import edu.institution.actions.asn7.AddSkillsetAction;
import edu.institution.actions.asn7.ListSkillsetAction;
import edu.institution.actions.asn7.RemoveSkillsetAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

/**
 * This class drives the execution of the application by presenting the menu and
 * responding to user actions.
 */
public class ApplicationController {
	private static final int QUIT = 16;

	private UserRepository userRepository;
	private LinkedInUser loggedInUser;

	public ApplicationController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Process the options chosen by the user.
	 */
	public void process() {
		List<MenuAction> actions = new ArrayList<>();

		// assignment 3 actions
		actions.add(new ListUserAction());
		actions.add(new AddUserAction());
		actions.add(new DeleteUserAction());
		actions.add(new SignoffAction());

		// assignment 4 actions
		actions.add(new ListConnectionAction());
		actions.add(new AddConnectionAction());
		actions.add(new RemoveConnectionAction());
		actions.add(new DegreeOfSeparationAction());

		// assignment 6 actions
		actions.add(new ListUserAlphabeticallyAction());
		actions.add(new ListUserByConnectionAction());
		actions.add(new ListUserByTypeAction());
		// assignment 7 actions
		actions.add(new AddSkillsetAction());
		actions.add(new RemoveSkillsetAction());
		actions.add(new ListSkillsetAction());

		// assignment 10 actions
		actions.add(new UndoAction());

		try (Scanner scanner = new Scanner(System.in);) {
			int choice = -1;
			do {
				establishLoggedInUser(scanner);
				displayMenu();

				choice = promptForChoice(scanner);
				if (choice < 1 || choice > QUIT) {
					ApplicationHelper.showMessage("Invalid choice. Try again.");
				} else {
					if (choice != QUIT) {
						MenuAction action = actions.get(choice - 1);
						if (!action.process(scanner, userRepository, loggedInUser)) {
							loggedInUser = null;
						}
						userRepository.saveAll();
					}
				}
			} while (choice != QUIT);
		}

		ApplicationHelper.showMessage("Goodbye");
	}

	/**
	 * Establishes the root user if there are no users in the system. If there are 1
	 * or more users, then this method prompts the user to login with an existing
	 * user.
	 * 
	 * @param scanner the scanner for user input.
	 */
	private void establishLoggedInUser(Scanner scanner) {
		if (loggedInUser != null) {
			// user is already logged in
			return;
		}

		if (userRepository.retrieveAll().isEmpty()) {
			initiateRootUser(scanner);
		} else {
			while (loggedInUser == null) {
				login(scanner);
			}
		}
	}

	/**
	 * Prompts the user to login.
	 * 
	 * @param scanner the scanner used to prompt the user.
	 */
	private void login(Scanner scanner) {
		ApplicationHelper.showMessage("Please login to continue.");

		ApplicationHelper.showMessage("User name: ");
		String username = scanner.nextLine();
		LinkedInUser user = userRepository.retrieve(username);
		if (user == null) {
			System.out.println("There is no user with that user name");
		}

		if (user != null) {
			ApplicationHelper.showMessage("Password: ");
			String password = scanner.nextLine();
			boolean correct = user.isPasswordCorrect(password);
			if (correct) {
				loggedInUser = user;
			} else {
				System.out.println("Password mismatch");
			}
		}
	}

	/**
	 * Establishes the root user and password.
	 * 
	 * @param scanner the scanner used to prompt the user.
	 */
	private void initiateRootUser(Scanner scanner) {
		ApplicationHelper.showMessage("There are no users in the system.  Please establish a root user and password");

		ApplicationHelper.showMessage("user name:");
		String rootUser = scanner.nextLine();

		ApplicationHelper.showMessage("password:");
		String password = scanner.nextLine();

		try {
			LinkedInUser user = new LinkedInUser(rootUser, password);
			user.setType("P");
			userRepository.add(user);

			loggedInUser = user;
		} catch (LinkedInException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Displays the command line interface menu to the console.
	 */
	private void displayMenu() {
		System.out.println("\nWelcome " + loggedInUser.getUsername());

		// Assignment 3
		System.out.println("1. List all Users");
		System.out.println("2. Sign up a New User");
		System.out.println("3. Delete a User");
		System.out.println("4. Sign off");

		// Assignment 4
		System.out.println("5. List Connections");
		System.out.println("6. Add Connection");
		System.out.println("7. Remove Connection");
		System.out.println("8. Degree of Separation");

		// Assignment 6
		System.out.println("9. List Users Alphabetically");
		System.out.println("10. List Users By Connection");
		System.out.println("11. List Users By Type");

		// Assignment 7
		System.out.println("12. Add Skillset");
		System.out.println("13. Remove Skillset");
		System.out.println("14. List Skillsets");

		// Assignment 10
		System.out.println("15. Undo");

		System.out.println("16. Quit");
	}

	/**
	 * Prompts the user for integer input.
	 * 
	 * @param scanner the scanner used to prompt the user.
	 * @return the user-supplied integer.
	 */
	private int promptForChoice(Scanner scanner) {
		String choice = scanner.nextLine();
		try {
			return Integer.parseInt(choice);
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}
}
