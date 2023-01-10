package edu.institution.asn5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.institution.actions.asn5.Utilities;
import edu.institution.asn2.LinkedInUser;

public class UtilitiesTest {

	// test the remove duplicates function
	@org.junit.Test 
	public<T> void removeDuplicatesTest(){
		
		Utilities utilities = new Utilities();
		
		List<Integer> item1 = new ArrayList<Integer>();
		
		List<String> item2	= new ArrayList<String>();
		
		List<LinkedInUser> item3 = new ArrayList<LinkedInUser>();
		
		List<String> item4 =new ArrayList<String>();	
		
		List<Integer> item5 = new ArrayList<Integer>();
		
		// items created for the Integers
		item1.add(1);
		item1.add(1);
		item1.add(1);
		item1.add(2);
		
		// tests ran for the integers
		assertTrue(item1.get(0).equals(item1.get(0)));
		assertTrue(item1.get(0).equals(item1.get(1)));
		assertTrue(item1.get(0).equals(item1.get(2)));
		assertFalse(item1.get(0).equals(item1.get(3)));

		System.out.println(item1);
		System.out.println(item1.size());

		assertEquals(item1.size(), 4);
		utilities.removeDuplicates(item1);
		assertEquals(item1.size(), 2);
		
		System.out.println(item1);
		System.out.println(item1.size());
		// items created for the Integers
		item2.add("hello");
		item2.add("hello");
		item2.add("hello");
		item2.add("awesome");
		
		System.out.println(item2);
		System.out.println(item2.size());
		
		// tests ran for the integers
		assertTrue(item2.get(0).equals(item2.get(0)));
		assertTrue(item2.get(0).equals(item2.get(1)));
		assertTrue(item2.get(0).equals(item2.get(2)));
		assertFalse(item2.get(0).equals(item2.get(3)));

		assertEquals(item2.size(), 4);
		utilities.removeDuplicates(item2);
		assertEquals(item2.size(), 2);
		
		System.out.println(item2);
		System.out.println(item2.size());
		
	
		
		// items created for the Integers
		item3.add(new LinkedInUser("jeff","pwd"));
		item3.add(new LinkedInUser("jeff","pwd"));
		item3.add(new LinkedInUser("jeff","pwd"));
		item3.add(new LinkedInUser("bob","password"));
	
		System.out.println(item3);
		System.out.println(item3.size());
		
		// tests ran for the integers
		assertTrue(item3.get(0).equals(item3.get(0)));
		assertTrue(item3.get(0).equals(item3.get(1)));
		assertTrue(item3.get(0).equals(item3.get(2)));
		assertFalse(item3.get(0).equals(item3.get(3)));

		assertEquals(item3.size(), 4);
		utilities.removeDuplicates(item3);
		assertEquals(item3.size(), 2);

		
		System.out.println(item3);
		System.out.println(item3.size());
		
		item4 = null;

		
		assertNull(item4);
		
	
		// items created for the Integers
		item5.add(1);
		item5.add(1);
		item5.add(1);
		item5.add(1);

		assertEquals(item5.size(), 4);
		utilities.removeDuplicates(item5);
		assertEquals(item5.size(), 2);


	}
	
	// test the linear search function
	@Test 
	public<E> void linearSearchTest() {
		Utilities utilities = new Utilities();
		List<Integer> List = new ArrayList<Integer>();
		
		Integer key = 5;
		Integer falseKey = 7;
		Integer record = null;

		List.add(1);
		List.add(2);
		List.add(3);
		List.add(4);
		List.add(5);
		List.add(6);
		
		assertTrue(List.get(0).equals(List.get(0)));
		assertTrue(List.get(4).equals(key));
		assertFalse(List.get(0).equals(key));
		
		assertNull(record);

		
		record = utilities.linearSearch(List,key);
		assertTrue(record.equals(List.get(4)));
		
		record = utilities.linearSearch(List,falseKey);
		
		assertNull(record);


	}
}
