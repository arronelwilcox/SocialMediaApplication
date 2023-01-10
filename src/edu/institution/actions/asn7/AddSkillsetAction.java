package edu.institution.actions.asn7;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInUser;

public class AddSkillsetAction implements MenuAction {

	/**
	 * Prompt the user for the skillset to add. 
	 * A skillset can be any String.  
	 * Examples of skillsets are: “java” “software design”, “databases”, etc.  
	 */
	
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		// Prompting the user to add a skill set.
		System.out.println("What Skillset do you want to add? ");
		String skillset = scanner.nextLine();
		if(loggedInUser.getSkillsets() == null || loggedInUser.getSkillsets().isEmpty()) {
			loggedInUser.addSkillset(skillset);
			ApplicationHelper.incrementSkillsetCount(skillset);
			// Push skillset to history stack
			UndoAction.history.push(skillset);
			// Push action to history stack
			UndoAction.history.push("Add Skillset Action");
		}
		else if(loggedInUser.getSkillsets().contains(skillset)) {
			System.out.println("This user already has that skillset");
		}
		
		else {

			loggedInUser.addSkillset(skillset);
			ApplicationHelper.incrementSkillsetCount(skillset);
			// Push skillset to history stack
			UndoAction.history.push(skillset);

			// Push action to history stack
			UndoAction.history.push("AddSkillsetAction");
		

		}
	

		return true;
	}

}
