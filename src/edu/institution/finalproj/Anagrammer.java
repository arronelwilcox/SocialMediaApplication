package edu.institution.finalproj;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Anagrammer {

	public static void main(String[] args) {
		
		CommandLineParser parser = new DefaultParser();
		
		AnagramEvaluatorImpl data = new AnagramEvaluatorImpl();
		
		List<String> anagramList = new ArrayList<String>();
		
		String option = null,anagram;
		
		Options options = new Options();
		
		options.addOption("a","anagram <word>",true,"supplies the anagram to evaluate");
		options.addOption("h","help",false,"displays this help textdisplays the help for this program");
		options.addOption("nf", "no-filter",false,"output all anagram values with no filter ");
		options.addOption("words","filter-words",false,"output only values that are known words");

		
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		
	    try {
	        // parse the command line arguments
	        CommandLine line = parser.parse( options, args );
	        
	        if(line.hasOption("h")) {
	        	formatter.printHelp( "anagrammer", options );
	        	return;
	        	
	        }
	        else if(line.hasOption("nf") || line.hasOption("no-filter")) {
	        	 option = "nf";
	               
	        }
	        else if(line.hasOption("words") || line.hasOption("filter-words")) {
	        	option = "words";

	        }
	        else if(line.getOptionValue("a") == null || line.getOptionValue("a").isEmpty() && line.hasOption("h") == false) {
	        	System.out.println(" Missing required option: -a ");
	        }
	        else if (line.getOptionValue("a") != null && (!(line.getOptionValue("a").isEmpty()) && line.hasOption("h") == false 
	        		&& line.hasOption("nf") == false && line.hasOption("no-filter") == false && line.hasOption("filter-words") == false
	        		&& line.hasOption("words") == false)) {
	        	option = "words";
	        }
	         anagram = line.getOptionValue("a");
	         anagramList = data.evaluate(anagram,option);
	         for(int i = 0;i < anagramList.size();i++) {
	        	 System.out.println(anagramList.get(i));
	         }
			 System.out.println("-- "+ anagramList.size() + " value(s) found ");
			 return;
	    }
	    catch(ParseException exp ) {
	        System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
	    } 
	}
}
