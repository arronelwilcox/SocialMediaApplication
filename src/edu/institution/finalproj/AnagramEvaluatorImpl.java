package edu.institution.finalproj;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AnagramEvaluatorImpl implements AnagramEvaluator {
	private Set<String> anagramSet = new TreeSet<String>();

	@SuppressWarnings("unused")
	@Override
	public List<String> evaluate(String anagram, String option) {
		AnagramDataReader data = new AnagramDataReaderImpl();
		
		Set<String> dataSet = new TreeSet<String>();

		List<String> dataList = new ArrayList<String>();
		
		List<String> listToReturn = new ArrayList<String>();
		
		List<String> anagramList = new ArrayList<String>();
		
		if(anagramSet.size() >= 0) {
			anagramSet.clear();
		}
		// if the data entered is null display a message saying so
		if (option == null || anagram == null) {
			//System.out.println("The option a and anagram is null");
		}
		else {
			int calculation = possibleWords(anagram);
			
			String space = "";

			// Invoke the generate words method.
			display("",anagram);

			// add the anagramSet into a list for looping purposes.
			anagramList.addAll(anagramSet);

			
			if(option == "nf" || option == "no-filter") {
				for(int i =0 ; i < anagramList.size(); i++) {
					listToReturn.add(anagramList.get(i).toUpperCase());

				}

			}
			else if(option == "words" || option == "filter-words") {
				// store the words from the file in a set
				dataSet = data.readData();
				// add that set to a list for sorting purposes
				dataList.addAll(dataSet);
						// loop through the dataList ;
				for(int i =0 ; i < anagramList.size(); i++) {
					if(dataList.contains(anagramList.get(i).toUpperCase())){
						// add all of correct words to the list that is returned
						listToReturn.add(anagramList.get(i).toUpperCase());
					}
					else {
						continue;
					}
				}
			}
		
		}

		return listToReturn;
	}
	
	public int possibleWords(String anagram) {
		int calculation = 1;
		// Calculate the amount of words derived from anagram
		for(int i = 0; i < anagram.length(); i++) {
			calculation += calculation * i;
		}
		return calculation;
	}
	
	// Recursive method that sorts the anagram word into every possibility
	// then adds that word to a TreeSet where the words are sorted
	// Alphabetically automatically.
	// http://www.guideforschool.com/2469083-java-program-to-find-all-the-anagrams-of-a-word/
	// @author : www.guideforschool.com
	// Above is the website and author website that gave me the idea to use this type of recursive method.
	// where substrings of the anagram string are made and re-arranged until all possiblilites 
	// are formed. My first approach was to make a char array of letters from the string , but 
	// I found that to be less efficient because the char would have to be converted back to an array.
	void display(String space, String anagram)
    {

		int calculation = possibleWords(anagram);
		String x= "",y="",z="";
        if(calculation<=1)
        {
           anagramSet.add(space+anagram);
        }
        else
        {
            for(int i=0; i<anagram.length(); i++)
            {
                x = anagram.substring(i, i+1);
                y = anagram.substring(0, i);
                z = anagram.substring(i+1);
                display(space+x, y+z);
            }
        }

    }
	
}

