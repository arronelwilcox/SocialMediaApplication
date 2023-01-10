package edu.institution.actions.asn3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class DeleteUserAction implements MenuAction,Serializable {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser)
	{
		List<LinkedInUser> users = new ArrayList<LinkedInUser>();
		LinkedInUser user = null;
		String username,password;
	
		System.out.println(" What user name would you like to delete ?");
		username = scanner.nextLine();
		
		
		// Store the LinkedInUser from the given username into the user variable.
		user = userRepository.retrieve(username);
		
		if(user != null)
		{
		
			System.out.println(" What is the user's password ?");
			password = scanner.nextLine();

			if(user.isPasswordCorrect(password))
			{
				userRepository.delete(user);

				// Delete all user connections with this user
				users = userRepository.retrieveAll();
				for(LinkedInUser eachUser : users) 
				{
					if(eachUser.getConnections().contains(user) || loggedInUser.getConnections().contains(user)) {
						try {
							eachUser.removeConnection(user);
							loggedInUser.removeConnection(user);

						} catch (LinkedInException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
						continue;
					}
				}
				System.out.println(" User deleted successfully");
				System.out.println("Any Connection is deleted as well");
			}
			else 
			{
				System.out.println(" That password is incorrect");
				
			}
		}
		else {
			System.out.println(" User does not exist");
		}
	
	if (user == loggedInUser) {
		return false;
	}
	else {
		return true;
	}
			
	}
	
}		
	

	
	


