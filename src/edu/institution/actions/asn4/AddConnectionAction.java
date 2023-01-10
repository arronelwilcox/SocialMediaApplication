package edu.institution.actions.asn4;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class AddConnectionAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {

		
			System.out.println(" What is the username you would like to connect with?");
			
			String userToConnect = scanner.nextLine();
			
			LinkedInUser userConnect = userRepository.retrieve(userToConnect);
 
			if(userConnect != null) {
				if(loggedInUser.equals(userConnect)) {
					System.out.println("You cannot connect to yourself");
				}
				else if(loggedInUser.getConnections().contains(userConnect)) {
					System.out.println("You are already connected with this user");
				}
				
				else {
					try {
						loggedInUser.addConnection(userConnect);
						System.out.println("added successfully");
						// Push user connection to history stack
						UndoAction.history.push(userConnect.getUsername());
						// Push action to history stack
						UndoAction.history.push("Add Connection Action");
						
		
					} catch (LinkedInException e) {
						e.printStackTrace();
					}
				}
			}
			else {
				System.out.println("There is no user with that username");
			}
			
		
		return true;
	}

		

}
