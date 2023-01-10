package edu.institution.actions.asn4;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class RemoveConnectionAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
	
			LinkedInUser userConnect;

			System.out.println(" What is the name of the user you want removed from your connections? ");
	
			String userToRemove = scanner.nextLine();
		
			userConnect = userRepository.retrieve(userToRemove);
			
			
			if(userConnect == null) {
				System.out.println("There is no user with that user name");
				
			}
			else if (userConnect != null) {
				try {
					loggedInUser.removeConnection(userConnect);
					System.out.println("The connection was removed successfully");
//					UndoAction.queue.add(userConnect);
					// Push user connection to history stack
					UndoAction.history.push(userConnect.getUsername());
					// Push action to history stack
					UndoAction.history.push("Remove Connection Action");
				
				
				} catch (LinkedInException e) {
					System.out.println("You are not connected with this user");
				}
				
			}
			else {
				try {
					throw new LinkedInException("There was no connection with that user");
				} catch (LinkedInException e) {
					e.printStackTrace();
				}
				
			}
		
		return true;
	}
}
