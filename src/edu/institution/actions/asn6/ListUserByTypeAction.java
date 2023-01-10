package edu.institution.actions.asn6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserByTypeAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		// TODO Auto-generated method stub
		List<LinkedInUser> users = userRepository.retrieveAll();
		
		List<LinkedInUser> typeSortedList = new ArrayList<LinkedInUser>();
		List<LinkedInUser> usersTypeP = new ArrayList<LinkedInUser>();
		
		List<LinkedInUser> usersTypeS = new ArrayList<LinkedInUser>();
		
		// search each LinkedinUser in the list for the types
		for(int i = 0; i < users.size();i++) {
			
			// if the user is type P add them to the user type p list.
			if((users.get(i).getType().equals("P"))) {
				usersTypeP.add(users.get(i));
			}
			
			//else if the user is type S add them to the user type s list
			else if((users.get(i).getType().equals("S"))) {
				usersTypeS.add(users.get(i));
			}
		}
		
		// add the add the userType P list and the userType S list together
		usersTypeP.addAll(usersTypeS);
		
		// Put the new list into another new list. This actionis not needed but
		// just seems right 
		typeSortedList = usersTypeP;
		
		// create a for loop that displays each sorted user by type in alphabetical order
		// based on the type first.
		for(int i = 0; i < typeSortedList.size();i++) {
			System.out.println(typeSortedList.get(i).getUsername()+"; type = "+typeSortedList.get(i).getType());
		}
		
	

		return true;
	}

}
