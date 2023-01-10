package edu.institution.actions.asn3;

import java.io.Serializable;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class AddUserAction implements MenuAction,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3814094998934943258L;
	

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		String username,password,type;
		
		System.out.println(" Enter a username ");
		username = scanner.nextLine();
		
		System.out.println(" Enter a password ");
		password = scanner.nextLine();
		
		System.out.println(" Enter a type: P for premium or S for standard");
		type = scanner.nextLine();
		
		if (type.equals("P") || type.equals("S")) {
			LinkedInUser user = new LinkedInUser(username,password,type);
			
			try {
				//System.out.println("In try");
				userRepository.add(user);
				// Push user to history stack
				UndoAction.history.push(user.getUsername());
				// Push Action to history stack
				UndoAction.history.push("Add User Action");


				//saveAll();
				System.out.println("Signed up succesffuly");
			} catch (LinkedInException e) {
				e.printStackTrace();
			}
		}
		
		else {
			System.out.println(" Invalid user type");
			
		}
	
		return true;
	}
}
