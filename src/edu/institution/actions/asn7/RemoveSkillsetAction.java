package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInUser;

public class RemoveSkillsetAction implements MenuAction {

	/**
	 * Prompt the user for the skillset to remove and remove the entered skillset from the logged in user’s skillsets.
	 *  If the entered skillset is not in the logged in user’s skillsets, then return true to keep the user signed in 
	 *  and do NOT call theApplicationHelper.decrementSkillsetCount(String)method for the entered skillset
	 */
	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		System.out.println("What Skillset do you want to remove? ");
		String skillset = scanner.nextLine();
		if(loggedInUser.getSkillsets() == null || loggedInUser.getSkillsets().isEmpty() || (!(loggedInUser.getSkillsets().contains(skillset)))) {
			System.out.println("This user does not have that skillset to remove");

		}
		else if(loggedInUser.getSkillsets().contains(skillset)) {
			loggedInUser.removeSkillset(skillset);
			ApplicationHelper.decrementSkillsetCount(skillset);
			// Push skillset to history stack
			UndoAction.history.push(skillset);
			// Push action to history stack
			UndoAction.history.push("Remove Skillset Action");

//			UndoAction.skillQueue.add(skillset);

		}

		return true;
	}

}
