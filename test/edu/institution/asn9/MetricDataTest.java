package edu.institution.asn9;



import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.institution.asn9.*;
//import edu.institution.actions.SortAlgorithm;
//import edu.institution.actions.SortAlgorithmMetricsOld;
//import edu.institution.actions.TimeComplexity;
import edu.institution.asn9.__MACOSX.MetricData;
import edu.institution.asn9.__MACOSX.SortAlgorithm;
import edu.institution.asn9.__MACOSX.SortAlgorithmMetrics;
import edu.institution.asn9.__MACOSX.TimeComplexity;

public class MetricDataTest {
		
		public List<Integer> readDataFromFile(String filePath){
			 List<Integer> listOfIntegers = new ArrayList<Integer>();
			 File file = new File(filePath);
			 BufferedReader reader = null;

			    try {
			        reader = new BufferedReader(new FileReader(file));
			        String text = null;

			        while ((text = reader.readLine()) != null) {
			        	listOfIntegers.add(Integer.parseInt(text));
			        }
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    } finally {
			        try {
			            if (reader != null) {
			                reader.close();
			            }
			        } catch (IOException e) {
			        }
			    }
				return listOfIntegers;
		}

		 @Test
		 public void MetricDataGetterSetterTest() {
			 
			 SortAlgorithm bubble = SortAlgorithm.BUBBLE_SORT;
			 SortAlgorithm insertion = SortAlgorithm.INSERTION_SORT;
			 SortAlgorithm merge = SortAlgorithm.MERGE_SORT;
			 SortAlgorithm quick = SortAlgorithm.QUICK_SORT;
			 SortAlgorithm heap = SortAlgorithm.HEAP_SORT;
			 
			 MetricData nullData = null;
			 MetricData testData = new MetricData(bubble);
			 MetricData dataB = new MetricData(bubble);
			 MetricData dataI = new MetricData(insertion);
			 MetricData dataM = new MetricData(merge);
			 MetricData dataQ = new MetricData(quick);
			 MetricData dataH = new MetricData(heap);
			 
			 int expectedHashCode = dataB.hashCode() ;
			 
			 String wrongClass = "this is is not a metricData";
			 // Assert false for the wrong class.
			 assertFalse(dataI.equals(wrongClass));
			 dataB.toString();
			 // Check hashcode
			 assertEquals(expectedHashCode , dataB.hashCode());
			 
			 // check equals method
			 assertTrue(testData.equals(dataB));
			 assertTrue(testData.equals(new MetricData(bubble)));
			 // check the same instance
			 assertTrue(dataB.equals(dataB));
			 // check  false if null
			 assertFalse(dataB.equals(null));
			 // check for a false equals.
			 assertFalse(testData.equals(dataI));
			 assertNull(nullData);
			 			 

			 // Assert that getters are working
			 assertEquals((SortAlgorithm.BUBBLE_SORT),dataB.getSortAlgorithm());
			 assertFalse(dataB.getSortAlgorithm().equals(null));
			 assertFalse(dataB.getSortAlgorithm().equals(wrongClass));

			 assertEquals((SortAlgorithm.INSERTION_SORT),dataI.getSortAlgorithm());
			 assertEquals((SortAlgorithm.MERGE_SORT),dataM.getSortAlgorithm());
			 assertEquals((SortAlgorithm.QUICK_SORT),dataQ.getSortAlgorithm());
			 assertEquals((SortAlgorithm.HEAP_SORT),dataH.getSortAlgorithm());
			 
			 // set the time complexities
			 dataB.setTimeComplexity(TimeComplexity.QUADRATIC);
			 assertEquals((TimeComplexity.QUADRATIC),dataB.getTimeComplexity());
			 assertFalse(dataB.getTimeComplexity().equals(null));
			 
			 // Test compareTo method
			 assertTrue(testData.compareTo(dataB) == 0);

			// set Execution Time for coverage
			 testData.setExecutionTime(1);
			 
			 // set the time complexities
			 dataI.setTimeComplexity(TimeComplexity.LINEAR);
			 assertEquals((TimeComplexity.LINEAR),dataI.getTimeComplexity());
			 assertFalse(dataI.getTimeComplexity().equals(null));
			 
			 
			 // set the time complexities
			 dataM.setTimeComplexity(TimeComplexity.LOGARITHMIC);
			 assertEquals((TimeComplexity.LOGARITHMIC),dataM.getTimeComplexity());
			 assertFalse(dataM.getTimeComplexity().equals(null));

			 // Assert that the TimeComplexity that hasnt been set is null.
			 assertNull(dataQ.getTimeComplexity());
		 			 	
			 // set the time complexities
			 dataQ.setTimeComplexity(TimeComplexity.CUBIC);
			 assertEquals((TimeComplexity.CUBIC),dataQ.getTimeComplexity());
			 assertFalse(dataQ.getTimeComplexity().equals(null));

			 
			 // set the time complexities
			 dataH.setTimeComplexity(TimeComplexity.EXPONENTIAL);
			 assertEquals((TimeComplexity.EXPONENTIAL),dataH.getTimeComplexity());
			 assertFalse(dataH.getTimeComplexity().equals(null));
		 			 	
			 
		 }
		
		@Test
		public void retrieveMetricsTest(){
			
			List <Integer> intList = new ArrayList<Integer>();
			
			List<MetricData> list = new ArrayList<MetricData>();
			
			File file = new File("asn9-numbers.txt");

			String filePath = file.getAbsolutePath();

			SortAlgorithmMetrics data = new SortAlgorithmMetrics();
			
			intList = readDataFromFile(filePath);
			
			// Check to see if the data from the file was transferred to the list.
			assertEquals(80000,intList.size());
			 Integer[] bubbleArray = new Integer[intList.size()];
			 Integer[] mergeArray = new Integer[intList.size()];
			 Integer[] quickArray = new Integer[intList.size()];
		 
			 intList.toArray(bubbleArray);
			 
			 // check to see if the array still contains the same list size.
			 assertEquals(80000,bubbleArray.length);
			 
			 for(int i = 0; i < intList.size();i++) {
				 // check to see if the list is in the same order.
				 assertEquals(intList.get(i),bubbleArray[i]);
			 }
			 
			Collections.shuffle(intList); 
			 intList.toArray(mergeArray);

			
			// check the first 2 of the list to make sure it was shuffled.
			 for(int i = 0; i < 2;i++) {
				 // check to see if the list is Not in the same order.
				 assertNotEquals(intList.get(i),bubbleArray[i]);
			 }
			 
			 // shuffle the list again to make sure its not the same order
			 Collections.shuffle(intList);
			 // add list to another array
			 intList.toArray(quickArray);
			 
			 // check to see if the list is in the same order.
			 for(int i = 0; i < intList.size();i++) {
				 // check to see if the list is in the same order.
				 assertEquals(intList.get(i),quickArray[i]);
			 }
			
			// check the first 2 of the list to make sure it was shuffled.
			 for(int i = 0; i < 2;i++) {
				 // check to see if the list is Not in the same order.
				 assertNotEquals(quickArray[i],mergeArray[i]);
			 }
			list = data.retrieveMetrics(filePath);
			
			
			// Check for the expected number of Metric data in the list (5)
			assertEquals(5,list.size());
			
					
			assertTrue((list.get(0).getExecutionTime()) <= (list.get(1).getExecutionTime()));

			assertTrue((list.get(1).getExecutionTime()) <= (list.get(2).getExecutionTime()));
			
			assertTrue((list.get(2).getExecutionTime()) <= (list.get(3).getExecutionTime()));
			
			assertTrue((list.get(3).getExecutionTime()) <= (list.get(4).getExecutionTime()));
	
		}
			

	}
	