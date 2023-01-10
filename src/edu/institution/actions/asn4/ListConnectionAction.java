package edu.institution.actions.asn4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListConnectionAction implements MenuAction {

	
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		List <LinkedInUser> userConnectionList = loggedInUser.getConnections();
		if (userConnectionList.isEmpty()) {
			System.out.println(" Empty connection list");
		}
		else {
			System.out.println(" Connections:");
			for(int i  =0 ; i < userConnectionList.size(); i++) {
				System.out.println(userConnectionList.get(i).getUsername());
			}
			
		}
		
		return true;
	}

}
