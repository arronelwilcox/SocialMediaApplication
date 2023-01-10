package edu.institution.finalproj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class AnagramDataReaderImpl implements AnagramDataReader {

	@Override
	public Set<String> readData() {
	    Set<String> listOfWords = new TreeSet<String>();
	    
	    BufferedReader bufReader = null;
		String line = null;

			try {
				bufReader = new BufferedReader(new FileReader("anagram_data.txt"));
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				line = bufReader.readLine();
				while (line != null) {
			      listOfWords.add(line);
			      line = bufReader.readLine();
				 }
				bufReader.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return listOfWords;
		}

}
