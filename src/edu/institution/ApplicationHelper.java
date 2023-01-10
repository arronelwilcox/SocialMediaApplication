/*
 Copyright (C) 2020. Doug Estep -- All Rights Reserved.
 Copyright Registration Number: TXU002159309.

 This file is part of the Tag My Code application.

 This application is protected under copyright laws and cannot be used, distributed, or copied without prior written
 consent from Doug Estep.  Unauthorized distribution or use is strictly prohibited and punishable by domestic and
 international law.

 Proprietary and confidential.
 */
package edu.institution;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import edu.institution.asn2.LinkedInUser;

/**
 * Contains static helper methods to aid with the command line user interface.
 */
public class ApplicationHelper {

	/**
	 * Displays the supplied message to the console.
	 * 
	 * @param message the message.
	 */
	public static void showMessage(String message) {
		System.out.println("\n" + message);
	}
	
	/**
	 * Collection to retrieve the number of users that share a skill set. 
	 * The Linked Hash Map was chosen for the convenience of finding a particular 
	 * number of users based on the shared skill set amongst the users. Linked Hash map 
	 * contains the same insertion order. So the collection is same as inserted.
	 */
	private static Map <String,Integer> sharedSkillset = new LinkedHashMap<String,Integer>();
	
	/*** 
	 * Increments the number of users associated with the supplied skill set.
	 * If this is the first occurrence of the supplied skill set, then add 
	 * the skill set to your collection and default the count to one.
	 * @param skillsetthe skill set to increment.
	 */
	public static void incrementSkillsetCount(String skillset) {
		Integer count = 1; 
		// if the shared skill set does not exist or does not contain the specified skill set 
		// put the specified skill set and number of users with that skill as 1.
		if(sharedSkillset == null || (!(sharedSkillset.containsKey(skillset)))) {
			sharedSkillset.put(skillset , count);
			
		}
		
		else {
			count = sharedSkillset.get(skillset);
			sharedSkillset.put(skillset,++count);

		}
	}
	
	/**
	 *  Decrements the number of users associated with the supplied skill set.
	 * If the number of users associated with the supplied skill set is zero, 
	 * then remove the skill set from your collection. 
	 * @param skillsetthe skill set to decrement.
	 */
	public static void decrementSkillsetCount(String skillset) {
		// Holds the value associated with the skillset key;
		Integer count = retrieveSkillsetCount(skillset);
		// Check the value;
		
		// If there are no users associated with the specified skill set.
		// Remove the skillset from the sharedSkillSets set.
		if(count == 0) {
			sharedSkillset.remove(skillset);
		}
		else if(!(sharedSkillset.containsKey(skillset))) {
			System.out.println("");
		}
		else {
			// Decrement the amount of users associated with the skillset.
			sharedSkillset.put(skillset,--count);

		}
	}
	
	/** 
	 * Retrieves the number of users associated with the supplied skill set. 
	 * If the skill set is not in the collection, return -1.
	 ** @param skillsetthe skill set to lookup.
	 */
	public static int retrieveSkillsetCount(String skillset) {
		
		Integer count = -1;
		
		// if the skill set taken in the parameter is in the shared skill set
		// set, then return the number of users that have that skill set.
		// otherwise return zero.
		if (sharedSkillset.containsKey(skillset)) {
			count = sharedSkillset.get(skillset);

		}
		return count;
	}
	
	/** 
	 * Loops through each user and increments the global skill set usage count for
	 * each skill set associated with the user.
	 * @param users the list of users.
	 */
	public static void initSkillsetUsages(List<LinkedInUser> users) {
		
		Set<String> linkedInskillset = new TreeSet<String>();

		// Loop through each linkedInUser in the Users list
		// to see if they have a skillset.
		for(LinkedInUser linkedInUser : users) {
			// If the LinkedinUser has a skillset
			// Get the set of skillsets for this user.
			linkedInskillset = linkedInUser.getSkillsets();

			if(linkedInskillset != null ){
				// Get each skill within the skill set and increment it.
				for(String skillset : linkedInskillset) {
					if(skillset == null) {
						System.out.println("skillset is null");
					}
					incrementSkillsetCount(skillset);
					
				}

			}
		
			else {
				continue;
				
			}
			
			break;

		}

	}
	
}
