package edu.institution.actions.asn5;

import java.util.List;

public class Utilities {
	//This method excepts a list of items and removes the duplicates from the list.
		//After this method completes, the supplied list should not contain any duplicate items.
		public<T> void removeDuplicates(List<T> items){
			
			// testing the logic 
//			items.add(1, null);
//			items.add(1, null);
//			items.add(1, null);
//			items.add(2, null);
//
//			System.out.println("Size of original T list: expecting 4" + items.size());
		
			// run through all of the items to check the items equality
			for(int i = 0; i < items.size(); i++) {
				
				// if items contains the same items remove one of the items from the list
				if(items.get(i).equals(items.get(i))) {
					
					// this should remove identical items 
					items.remove(items.get(i));
					
					//System.out.println("Size of original T list: expecting 2" + items.size());

				}
				else {
					items.add(items.get(i));
					System.out.println("There are " + items.size()+ " in this list");
				}
			}
				
			
		}
		
		//Implement a generic method to do a linear search. 
		//Your linear search method should accept a list containing a 
		//generic type and a key record to search for in the generic list.  
		//Your linear search should return the record associated with the supplied
		//key or null if the key does not exist in the supplied list.
		public<E> E linearSearch(List<E> list, E key){
			// initialized new generic type to hold record
			E record = null;
			// search through the list for any list elements that are the same as the key.
			for (int i = 0; i < list.size(); i++) {
				// If an element in the list is equal to a key
	            if (list.get(i).equals(key)) {
	            	// set the element as the record to be returned
	            	record = list.get(i);

	            }
	            
	            // else print out that record is null
	            else {
	            	System.out.println("In else statement,  The record is null ");
	            }
	        }

			return record;
	      
		}

}
