/* Arron WIlcox
 * CIS 2217 - Assignment 2 - LinkedInUser
 * 05-23-2020
 * THis class extends the Abstract user account Class and also implements the comparable interface. This
 *  Class Overrides the set type method created in the user account class and also the compare to method .
 *  An array list is created to hold all of the connections between linked in user accounts. THis classs contains methods that
 *  adds , removes , and sorts connections alphabetically. Also, if a particular user is already connected with a user they are 
 *  trying to connect with, a message will appear teling the user they are already conneccted. Vis versa happens if a user tries to
 *  remove a connection that they do not have then a message will appear saying they are not connected the that user. 
 *  Then there is also a method that returns the list of connections for a particular user. Finally, user accounts can also be compared
 *  to one another
 */

package edu.institution.asn2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;

import java.util.Iterator;

// Inherited from user account and implements the interface Comparable
public class LinkedInUser extends UserAccount implements Comparable<LinkedInUser>{
	
	private static final long serialVersionUID = 2L;

	// Creates a variable to hold the type of account 
	private String type;

	// Array list for connections amoung linked in user.
	private List<LinkedInUser> connections = new ArrayList<LinkedInUser>();
	
    // Set to add skillsets with no duplicates
	private Set<String> skillsets;

	
	// Contructor called to set the username and password of the linked in user.
	public LinkedInUser(String usernme, String password) {

		// calls the parent class methods to create a Linked in user acoount.
		super(usernme, password);
		
	}
	
	public LinkedInUser(String username, String password, String type) {
		super(username, password);
		this.type = type;
	}
	
	public LinkedInUser(String username, String password, String type, List<LinkedInUser> connections) {
		super(username, password);
		this.type = type;
		this.connections = connections;
	}

	// returns the type of user.
	public String getType() {

		return this.type;

	}

	// takes a String parameter of what type of account a user wants.
	@Override
	public void setType(String type) {
		this.type = type;
	}

	//  Adds the provided connection to the list of connections for this user.
	// Throws an exception if  user is already connected
	public void addConnection(LinkedInUser user) throws LinkedInException

	{
		// If the user already has a connection to the other account then the exception is thrown and 
		// a message is displayed."You are already connected with this user"
		for(LinkedInUser linkedInUser : this.connections)
		{
	
			if(linkedInUser.equals(user))
			{
		
				throw new LinkedInException("You are already connected with this user");
		
			}
		
		}
	
		// If this is not already a connection then the connection is asdded.
		this.connections.add(user);
	
	}

	// Removes the supplied user from the connections List.
	// If the supplied user is not connected to this user, then throw
	// a new LinkedInException with the message,
	// “You are NOT connected with this user”.
	public void removeConnection(LinkedInUser user) throws LinkedInException 
	{
		// Iterator used to cycle throughout the connections to see if a particular can be removed.
		Iterator<LinkedInUser> connectionsIterator = this.connections.iterator();
	
		// While there is data in the connections iterator
		while(connectionsIterator.hasNext()) 
		{
			// The next linkedinUser is looked at 
			LinkedInUser linkedInUser = connectionsIterator.next();
		
			// if the user name is equal to the one needing removed 
			if(linkedInUser.equals(user)) 
			{
		
				// then the connection is removed 
				this.connections.remove(user);
				return;
		
			}
	
		}
		// If no connection has ever been made then the specified user cannot be removed 
		// and a message is displayed to let the current user know they are not connected to that particular user
		// that they wanted to remove. Therefore, no action is needed.
		throw new LinkedInException("You are NOT connected with this user");

	}

	// return a copy of this users’ connections – create a new List
	// with the same LinkedInUsers in it as this users’ connections.
	// Do not return the connections List directly, since this violates
	// the principle of encapsulation.
	public ArrayList<LinkedInUser> getConnections() {

		// Returns a newly create Array List with all the edited connections
		return new ArrayList<LinkedInUser>(this.connections);
	}
	
	/** Returns the skillsets. */
	public Set<String> getSkillsets(){
		return this.skillsets;
	}

	public void setSkillsets(Set<String> skillsets) {
		this.skillsets = skillsets;
	}

	/** Adds the supplied skillset to this user. */
	public void addSkillset(String skillset) {
		skillsets = this.getSkillsets();
		if(skillsets == null) {
			skillsets  = new HashSet<String>();
			skillsets.add(skillset);
		}
		//ApplicationHelper.incrementSkillsetCount(skillset);
		else {
			skillsets.add(skillset);
		}
		
	}
	
	/** Removes the supplied skillset from this user. */
	public void removeSkillset(String skillset) {
		//ApplicationHelper.decrementSkillsetCount(skillset);
		skillsets.remove(skillset);
		
	}

	// Override method that compares Two user accounts and ignores Capitialization when sorting 
	// through Alphabetically.
	@Override
	public int compareTo(LinkedInUser user) {
		
		return this.getUsername().compareToIgnoreCase(user.getUsername());

	}

	
}
