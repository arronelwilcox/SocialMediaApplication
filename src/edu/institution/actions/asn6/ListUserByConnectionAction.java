package edu.institution.actions.asn6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import edu.institution.actions.asn5.Utilities;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;



public class ListUserByConnectionAction implements MenuAction,Comparable<LinkedInUser>{

	//Add a new method which displays the LinkedIn users, 
	//ordered by their number of connections 
	//(from most connections to least connections)
	//and display the number of connections they have.  
	
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		// Retrieve the list of LinkedInUsers
		List<LinkedInUser> userList = userRepository.retrieveAll();
		
		// This sorts the array in descending order (Greatest value to Least).
		Collections.sort(userList, new SortbyCollectionSize());
		
		// This displays each user's username and Connection size in descending order
		for(int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).getUsername() + "; connection size = " + userList.get(i).getConnections().size());
		}
			

		return true;
	
	}



	/*
	 * This is the Comparator class for comparing based on collection size in descending order
	 * 
	 */
	class SortbyCollectionSize implements Comparator<LinkedInUser> 
	{ 
	    // Used for sorting in descending order of 
	    // collection size
	    public int compare(LinkedInUser a, LinkedInUser b) 
	    { 
	    	return b.getConnections().size() - a.getConnections().size();
	    } 
	} 
	
	@Override
	public int compareTo(LinkedInUser o) {
		// TODO Auto-generated method stub
		return 0;
	}
}

	
	

