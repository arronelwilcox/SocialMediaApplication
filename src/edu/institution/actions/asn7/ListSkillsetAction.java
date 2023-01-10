package edu.institution.actions.asn7;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListSkillsetAction implements MenuAction {

	/**
	 * This action is required to print each skillset associated with the logged in user in alphabetical order.
	 *  For each skillset associated with the logged in user, display the skillset, 
	 *  followed by the number of users that share that same skillset.
	 */
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		Set<String> importSkillSet = loggedInUser.getSkillsets();

		Set<String> userSkillset = new TreeSet<String>();
		
		// If the logged in user has no skillsets display the message, “You have not entered any skillsets.”
		
		if(importSkillSet != null) {
			userSkillset.addAll(importSkillSet);
			if (importSkillSet.isEmpty()) {
				System.out.println("You have not entered any skillsets.");

			}
			else {
				System.out.println(" Here are your skillsets");

				for(String skillset :importSkillSet) {
					
					int skillCount = ApplicationHelper.retrieveSkillsetCount(skillset);
					System.out.println();
					if (skillCount > 1) {
						System.out.println(skillset + " (" + skillCount + " users)");

					}
				
					else {
						System.out.println(skillset + " (" + skillCount + " user)");
					}
				}
			}
			
			
		}


		return true;
	}

}
