package edu.institution.actions.asn4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class DegreeOfSeparationAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		//userConnections = loggedInUser.getConnections();
		System.out.println("Enter the user you wish to find the degree of seperation for: ");
		
		String userToFind = scanner.nextLine();
		
		if(userToFind.equals(loggedInUser.getUsername())) {
			
			System.out.println(" The target user cannot be the logged in user.");
		
		}
		else {
			ArrayList<String> alreadySearchedUsers = new ArrayList<String>(); 
			
			// holds the names of the connections between the loggedInUser and the Selected user.
			ArrayList<String> userChain = new ArrayList<String>(); 
			
			userChain.add(loggedInUser.getUsername());
			// LinkedInUser object that holds the LinkedInUser from a given username.
			LinkedInUser user = userRepository.retrieve(userToFind);
			
			// check if user is valid
			if(user == null) {
				System.out.println(" User does not exist");
				return true;
			}

			int degreeOfSep = degOfSep(loggedInUser,userToFind,0,userChain,alreadySearchedUsers);
			if(degreeOfSep == -1) {
				System.out.println(" There is no path to that target user");

			}
		}

		
		return true;
	}
	
	public int degOfSep(LinkedInUser user,
			String userToFind, 
			int degree,
			ArrayList<String> userChain,
			ArrayList<String> alreadySearchedUsers) {
		
		if(degree < 1) {		
		// all connections first
			for(LinkedInUser userInConnections : user.getConnections() ) {
				if(userInConnections.getUsername().equals(userToFind)) {
					System.out.println(" The degree of separation is " + degree);
					for(String userInChain : userChain ) {
						
						System.out.print(userInChain + " -> ");
						
					}
					System.out.println(userToFind);
					return degree;
				}
				
			}
		}
		alreadySearchedUsers.add(user.getUsername());
		
		for (int i = 0;i < user.getConnections().size(); i++) {
			String currentUserName = user.getConnections().get(i).getUsername();
			// if users connectionUser is equal to the userToFind, then return degreeOfSeparation.
			if(currentUserName.equals(userToFind)) {
				
				System.out.println(" The degree of separation is " + degree);

				// 
				for(String userInChain : userChain ) {
					
					System.out.print(userInChain + " -> ");
					
				}
				System.out.println(userToFind);
				return degree;
			}
		
			// if users connectionUser has already been searched,  then skip
			else if(alreadySearchedUsers.contains(currentUserName)) {
				
				continue;
			}
			
			// else add the user that was just checked to the alreadySerachedUsrs and recursively call again.
			else {
				
				userChain.add(currentUserName);
				int degreeOfSep = degOfSep(user.getConnections().get(i), userToFind,++degree,userChain,alreadySearchedUsers);
				if(degreeOfSep == -1) {
					degree = 1;
					userChain.remove(currentUserName);
					continue;
				}
				else {
					return degreeOfSep;
				}
			}
			
		}
		
		return -1;
	}

}
