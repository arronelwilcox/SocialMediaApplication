package edu.institution.finalproj;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import edu.institution.finalproj.AnagramDataReader;
import edu.institution.finalproj.AnagramDataReaderImpl;
import edu.institution.finalproj.AnagramEvaluatorImpl;
import edu.institution.finalproj.Anagrammer;

public class AnagramTest {

	@Test
	public void AnagramDataReaderTest() {
		
		AnagramDataReader data = new AnagramDataReaderImpl();
		
	    Set<String> listOfWords = new TreeSet<String>();
	    
		listOfWords = data.readData();
		
		// check to see if the Set contains all of the data.
		assertEquals(373295,listOfWords.size());
	}
	@Test
	public void AnagramEvaluatorTest() {
		List<String> anagramList = new ArrayList<String>();

		AnagramEvaluatorImpl data = new AnagramEvaluatorImpl();

		String option = null,anagram = null;
		int calculation = 0;
		
		assertNull(option);
		assertNull(anagram);
		assertEquals(0,calculation);
		
		anagram = "DOG";
		option = "nf";

		calculation = data.possibleWords(anagram);
		
		// Tet that the calulation method is working .
		assertEquals(6,calculation);
		
        anagramList = data.evaluate(anagram,option);
        
        // Check the Size of anagramList
        assertEquals(6,anagramList.size());
        
        // Change option value
        option = "words";
        
        anagramList = data.evaluate(anagram,option);

        // Check the Size of anagramList againg
        assertEquals(2,anagramList.size());
	}
	@SuppressWarnings("unused")
	@Test
	public void AnagrammerTest() {
		
		AnagramEvaluatorImpl data = new AnagramEvaluatorImpl();
		
		List<String> anagramList = new ArrayList<String>();

		String option = null,anagram = null;
		
		Options options = new Options();
		
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();

		
		assertNotNull(options);
		assertNull(option);
		assertNull(anagram);
		
		options.addOption("a","anagram <word>",true,"supplies the anagram to evaluate");
		options.addOption("h","help",false,"displays this help textdisplays the help for this program");
		options.addOption("nf", "no-filter",false,"output all anagram values with no filter ");
		options.addOption("words","filter-words",false,"output only values that are known words");		
		
		
        // parse the command line arguments
		try {
	        String[] args = null;
			CommandLine line = parser.parse( options, args );
			option = "nf";	
			anagram = "word";
			
			assertEquals(null,line.getOptionValue("a"));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		assertNotNull(option);
		assertNotNull(anagram);	
		assertEquals("word",anagram);
		assertEquals("nf",option);		
		option = "h";	
		assertEquals("h",option);

	}
}
