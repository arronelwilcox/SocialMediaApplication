package edu.institution.asn9.__MACOSX;

import edu.institution.asn9.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortAlgorithmMetrics {

	public List<MetricData> retrieveMetrics(String filePath){
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

	    // Create 5 arrays of integers out of the list that contains the 8000 integers from the file.
	    //Bubble Sort, Merge Sort, Quick Sort, Heap Sort, and Insertion Sort 
	    // assume this contains the list of 80000 Integers.
	    // tells toArray what type of Array to generate
	    Integer[] bubbleArray = new Integer[listOfIntegers.size()],mergeArray = new Integer[listOfIntegers.size()],
	    		quickArray = new Integer[listOfIntegers.size()]
	    		,heapArray = new Integer[listOfIntegers.size()] ,insertionArray = new Integer[listOfIntegers.size()];
	    
	    // Create 5 instances of Metric Data to store the results of each sort.
	    MetricData bubbleData = new MetricData(SortAlgorithm.BUBBLE_SORT);
	    MetricData mergeData = new MetricData(SortAlgorithm.MERGE_SORT);
	    MetricData quickData = new MetricData(SortAlgorithm.QUICK_SORT);
	    MetricData heapData = new MetricData(SortAlgorithm.HEAP_SORT);
	    MetricData insertionData = new MetricData(SortAlgorithm.INSERTION_SORT);
	    

	    // List that holds the results of each sort method.
	    List <MetricData> listOfData = new ArrayList<MetricData>();
		Collections.shuffle(listOfIntegers);
		Integer[] template = {};
		Integer[] arrayOfIntegers = listOfIntegers.toArray(template);
		
		System.arraycopy(arrayOfIntegers, 0, bubbleArray, 0, listOfIntegers.size());
		System.arraycopy(arrayOfIntegers, 0, mergeArray, 0, listOfIntegers.size());
		System.arraycopy(arrayOfIntegers, 0, quickArray, 0, listOfIntegers.size());
		System.arraycopy(arrayOfIntegers, 0, heapArray, 0, listOfIntegers.size());
		System.arraycopy(arrayOfIntegers, 0, insertionArray, 0, listOfIntegers.size());
		

		// Start the timer
		LocalTime start = LocalTime.now();
		// execute the sort
		BubbleSort.bubbleSort(bubbleArray);
		// Stop timer
		LocalTime end = LocalTime.now();
		// Calculate Timer in milliseconds
		long elapsedMilliseconds = Duration.between(start, end).toMillis(); 
		//Set the appropriate TimeComplexityenumfor a Bubble Sort to the MetricsDatainstance.
		bubbleData.setTimeComplexity(TimeComplexity.QUADRATIC);
		//Set the execution time of the Bubble Sort algorithm to the MetricsData instance 
		bubbleData.setExecutionTime(elapsedMilliseconds);
		listOfData.add(bubbleData);

		start = LocalTime.now();
		// execute the sort
		MergeSort.mergeSort(mergeArray);
		end = LocalTime.now();
		elapsedMilliseconds = Duration.between(start, end).toMillis(); 
		//Set the appropriate TimeComplexityenumfor a Merge Sort to the MetricsDatainstance.
		mergeData.setTimeComplexity(TimeComplexity.LOGARITHMIC);
		//Set the execution time of the Merge Sort algorithm to the MetricsData instance 
		mergeData.setExecutionTime(elapsedMilliseconds);
		listOfData.add(mergeData);

		start = LocalTime.now();
		// execute the sort
		QuickSort.quickSort(quickArray);
		end = LocalTime.now();
		elapsedMilliseconds = Duration.between(start, end).toMillis(); 
		//Set the appropriate TimeComplexityenumfor a QuickSort  to the MetricsDatainstance.
		quickData.setTimeComplexity(TimeComplexity.QUADRATIC);
		//Set the execution time of the QuickSort  algorithm to the MetricsData instance 
		quickData.setExecutionTime(elapsedMilliseconds);
		listOfData.add(quickData);

		start = LocalTime.now();
		// execute the sort
		HeapSort.heapSort(heapArray);
		end = LocalTime.now();
		elapsedMilliseconds = Duration.between(start, end).toMillis(); 
		//Set the appropriate TimeComplexityenumfor a Heap Sort to the MetricsDatainstance.
		heapData.setTimeComplexity(TimeComplexity.LOGARITHMIC);
		//Set the execution time of the Heap Sort algorithm to the MetricsData instance 
		heapData.setExecutionTime(elapsedMilliseconds);
		listOfData.add(heapData);

		start = LocalTime.now();
		// execute the sort
		InsertionSort.insertionSort(insertionArray);
		end = LocalTime.now();
		elapsedMilliseconds = Duration.between(start, end).toMillis(); 
		//Set the appropriate TimeComplexity enum for a Insertion Sort to the MetricsDatainstance.
		insertionData.setTimeComplexity(TimeComplexity.QUADRATIC);
		//Set the execution time of the Insertion Sort algorithm to the MetricsData instance 
		insertionData.setExecutionTime(elapsedMilliseconds);
		listOfData.add(insertionData);
	  
	    Collections.sort(listOfData);

		return listOfData;
		
	}

	

}
