package edu.institution.actions.asn3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserAction implements MenuAction,Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5245517885336270191L;

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		List<LinkedInUser> users = userRepository.retrieveAll();
		
		for(LinkedInUser linkedInUser : users) 
		{
			if (linkedInUser != null) {
				System.out.println(linkedInUser.getUsername());

			}
			else{
				continue;
			}
			
		}
		
		return true;
	}
	

}
