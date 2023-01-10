package edu.institution.actions.asn3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.institution.ApplicationHelper;
import edu.institution.LinkedInCLI;
import edu.institution.UserRepository;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class SerializedUserRepository implements UserRepository {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//File file = new File("LinkedInUser.dat");
	private LinkedInUser userAccount;
	
	private List<LinkedInUser> users ;//= new ArrayList<LinkedInUser>();
	//private String filePath = file.getAbsolutePath();
	//private List <LinkedInUser> userList ;
	private String filePath,fileName;

	//private LinkedInUser user ;

	//Add suppressWarnings as suggested in lesson video for loading data from file
	@SuppressWarnings("unchecked")
	@Override
	public void init(String filePath, String fileName) {
	
		this.filePath = filePath;
		
		this.fileName = fileName;
		
		File file = new File(filePath + fileName);
		List <LinkedInUser> usersFromFile = new ArrayList<LinkedInUser>();
		
		if (file.exists()) {
			try (FileInputStream fins = new FileInputStream(file);
					ObjectInputStream oinput = new ObjectInputStream(fins);)
			{	
				// As suggested, casting warning is suppressed 
				try {
					usersFromFile = (List<LinkedInUser>) oinput.readObject();
				
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			} catch (FileNotFoundException e) {
				System.out.println(" NOT FOUND" );
		    	new File(filePath + fileName);

	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	        	System.out.println("File format is NOT correct");

	            // TODO Auto-generated catch block
	            e.printStackTrace();
	       
			}
			// Passes the user's from the file to the initSkillSetUsages method to find each users skillset.
			//ApplicationHelper.initSkillsetUsages(usersFromFile);
		}
		
		this.users = usersFromFile;
		ApplicationHelper.initSkillsetUsages(this.users);
		
		
		 
	}

	@Override
	public void add(LinkedInUser user) throws LinkedInException {
		
		// creating 2 messages to put into the LinkedInException.
		String invalidInfoMessage ="Invalid user type. Valid types are P or S.\n ";
		String alreadyExistsMessage = "A user already exists with that user name.";
		
		if (user.getUsername() != null && user.getPassword() != null)
		{
			
			if(user.getType().equals("P") || user.getType().equals("S")) {
			
				// Add new user to users 
				this.users.add(user);
				// Persist users
				saveAll();
			
			}
			else {
				throw new LinkedInException(invalidInfoMessage);
			}
		
		}
		
		else if (user.getUsername() == null || user.getPassword() == null ||
				(!(user.getType().equals("P")) || (!(user.getType().equals("S"))))) {
			
			
			throw new LinkedInException(invalidInfoMessage);
		
		}
		
		else {
			throw new LinkedInException(alreadyExistsMessage);

		}
		
	}

	@Override
	public void saveAll(){
		File file = new File(filePath + fileName);
		if (file.exists()) {
			file.delete();
		}
		// Create directory
		new File(filePath).mkdirs();
		try(FileOutputStream fout = new FileOutputStream(filePath + fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fout);) 
		{
			//System.out.println(users.get(0).getPassWord());
			oos.writeObject(users);
//			for (int i = 0 ;i < users.size();i++) {
//				System.out.println(oos + " "  + users.get(i).getPassWord());
//			}
		}catch (FileNotFoundException e) {
            // Output unexpected Exceptions.
            System.out.println("File not found");
        }catch (IOException e) {
            // Output unexpected Exceptions.
            System.out.println("IO Exception");
            e.printStackTrace();
        }
		
	}

	@Override
	public void delete(LinkedInUser user) {
		users.remove(user);
		saveAll();
	}

	@Override
	public LinkedInUser retrieve(String username) {
		userAccount = null;
		
		for(LinkedInUser linkedInUser : this.users) 
		{
		    if(linkedInUser.getUsername().equals(username)){
		    	
		    	int index = users.indexOf(linkedInUser);
				
				userAccount = users.get(index);
		
				break;
		    }
		  
		  

		}

		return userAccount;
	}

	@Override
	public List<LinkedInUser> retrieveAll() {
		if(users != null && users.isEmpty() == false)
			return users;
		else {
			List <LinkedInUser> user = new ArrayList<LinkedInUser>();
			return user;

		}
	}

}
	
	