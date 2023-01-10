package edu.institution.asn11;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import edu.institution.asn11.BST;
import org.junit.jupiter.api.Test;

public class BSTTest<E> {

	@SuppressWarnings({ "unchecked", "unused" })
	@Test
	public void breadthFirstTraversalTest() {
	
		@SuppressWarnings("rawtypes")
		BST test = new BST();
		@SuppressWarnings("rawtypes")
		BST testNum = new BST();
		
		List<E> list = new ArrayList<E>();
		
		Integer num1 = 8;
		Integer num2 = 28;
		Integer num3 = 84;
		Integer num4 = 3;
		Integer num5 = 17;
		Integer num6 = 890;
		Integer num7 = 3;
		Integer num8 = 45;
	
		E name1 = (E) "bad";
		E name2 = (E) "good";
		E name3 = (E) "do";	
		E name4 = (E) "free";
		E name5 = (E) "zoo";
		E name6 = (E) "leaf";
		E name7 = (E) "added";
		E name8 = (E) "unified";

		// Test the null fuctions
		assertNull(test.getRoot());
		assertNull(testNum.getRoot());
		assertEquals(0,test.getHeight());
		assertEquals(0,testNum.getHeight());
		assertEquals(0,test.getSize());
		assertEquals(0,testNum.getSize());
	
		test.insert((Comparable<E>) name1);
		test.insert((Comparable<E>) name2);
		test.insert((Comparable<E>) name3);
		
		testNum.insert((Comparable<E>) num1);
		testNum.insert((Comparable<E>) num2);
		testNum.insert((Comparable<E>) num3);

		// Test that the root of the two BST instances are correct
		assertEquals(test.root,test.getRoot());
		assertEquals(testNum.root,testNum.getRoot());
		assertNotEquals(test.root,testNum.getRoot());
		assertNotEquals(testNum.root,test.getRoot());

		// Test the getHeight Method for both BST instances.
		assertEquals(3,test.getHeight());
		assertEquals(3,testNum.getHeight());

		// Insert more values in the instances and test again
		test.insert((Comparable<E>) name4);
		test.insert((Comparable<E>) name5);
		test.insert((Comparable<E>) name6);
		test.insert((Comparable<E>) name7);
		test.insert((Comparable<E>) name8);

		testNum.insert((Comparable<E>) num4);
		testNum.insert((Comparable<E>) num5);
		testNum.insert((Comparable<E>) num6);
		testNum.insert((Comparable<E>) num7);
		testNum.insert((Comparable<E>) num8);
		
		// Test the nonRecursiveInorder for both instances
		//test.nonRecursiveInorder();
		testNum.nonRecursiveInorder();
		// Test the breadthFirst Traversal for both instances.
		list = test.breadthFirstTraversal();
		//testNum.breadthFirstTraversal();
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		// Test that the size of the two BST instances are correct
		assertEquals(8,test.getSize());
		assertEquals(7,testNum.getSize());
		// Test that the height of the two BST instances are correct
		assertEquals(5,test.getHeight());
		assertEquals(4,testNum.getHeight());

	}

}
