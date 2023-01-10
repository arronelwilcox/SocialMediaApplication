package edu.institution.actions.asn10;


import java.util.Scanner;
import java.util.Stack;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;

import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class UndoAction implements MenuAction {

	// publicly available to all actions
	public static Stack<String> history = new Stack<String> ();

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {

		String choice;
	
		if(history.empty()) {
			System.out.println("There were no actions left to undo");
			return true;
		}
	
		System.out.println("The last menu option selected was "+ '"'  + " " + history.lastElement() + '"' 
		+ " involving "+ loggedInUser.getUsername() + ". UNDO(Y/N)");
		
		// Store the users choice of Y or N in variable choice.
		choice = scanner.nextLine();
		// if choice is Y
		if(choice.equals("Y")) {
			// retrieves the last element in the stack.
			String lastAction = history.pop();
			
			if(lastAction.equals("Add Connection Action")) {
				try {
					LinkedInUser userConnection = userRepository.retrieve(history.pop());
					loggedInUser.removeConnection(userConnection);
					System.out.println(userConnection.getUsername() + " has been removed as a connection");
				} catch (LinkedInException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(lastAction.equals("Remove Connection Action")) {
				try {
					LinkedInUser userConnection = userRepository.retrieve(history.pop());
					loggedInUser.addConnection(userConnection);
					System.out.println(userConnection.getUsername() + " has been added back as a connection");
				} catch (LinkedInException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(lastAction.equals("Add User Action")) {
				LinkedInUser addedUser = userRepository.retrieve(history.pop());
				userRepository.delete(addedUser);
				System.out.println(addedUser.getUsername() + " has been removed as a user");

			}
		
			else if (lastAction.equals("Add Skillset Action")) {
				String skillset = history.pop();
				loggedInUser.removeSkillset(skillset);
				System.out.println(skillset + " has been removed from the Skillset list");
			}
			
			else if (lastAction.equals("Remove Skillset Action")) {
				String skillset = history.pop();
				loggedInUser.addSkillset(skillset);
				System.out.println(skillset + " has been added back to the Skillset list");
			}

			
		}
		else if(choice.equals("N")) {
			return true;
		}
		else {
			System.out.print("Incorrect choice");
			// return true to keep the user logged in.
			return true;
		}
		return true;
	}

}
