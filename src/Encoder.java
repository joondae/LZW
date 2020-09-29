//Imported all io and util assets rather than having several lines importing one at a time
import java.io.*;
import java.util.*;

public class Encoder {
	
	//Initializes a dictionary HashMap that will store ASCII characters and other character combinations, and pair them with codes
	public static HashMap<String, Integer> dictionary = new HashMap<String, Integer>(512);

	//The function called in the main. Calls generateCodestream() and initializeDictionary() to encode file
	public void encodeFile(String fileName) {
		generateCodestream(fileName, initializeDictionary());
	}

	//The function that adds ASCII table (all characters w/ decimal values from 0-255) to dictionary HashMap
	private HashMap<String, Integer> initializeDictionary() {
		for(int i = 0; i < 256; i++) {
			dictionary.put(Character.toString((char) i), i);
		}
		return dictionary;
	}
	
	//Function that parses through the file and writes a new, encoded file
	//String fileToEncode is the name of the file that you want to encode
	private void generateCodestream(String fileToEncode, HashMap<String, Integer> dictionary) {
		String p = "";
		//Dictionary size is 256 due to storing ASCII characters 0-255
		int dictionarySize = 256;
		
		//try statement allows you to test the block of code inside for error while it's being executed
		try {
			//Grabs the file that you want to encode
			File file = new File(fileToEncode);
			//Necessary in order to read the file
			BufferedReader br = new BufferedReader(new FileReader(file));
			//Creates a new, writable file named after the original file + ".lzw"
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file.getAbsolutePath() + ".lzw")));
			int current = 0;

			//Reads file character by character until it reaches the end
			while((current = br.read()) != -1) {
				char c = (char) current;
				//If the current character is present in dictionary:
				if(dictionary.containsKey(p + c)) {
					//P = concat(P,C)
					p += c;
				}
				//If the current character is not present in dictionary:
				else {
					//Writes the code word which denotes P to the file
					bw.write("" + dictionary.get(p) + " ");
					//Add the string concat(P,C) to the dictionary
					dictionary.put(p + c, dictionarySize);
					//Increases the number of elements in the dictionary map by one
					dictionarySize++;
					//P = C
					p = "" + c;
				}				
			}

			//Writes the code word correlating with String p to the file
			bw.write("" + dictionary.get(p));
			//Closes the reading and writing streams and releases any system resources associated with them 
			br.close();
			bw.close();
			//Informs the user if they pass the size limit for the dictionary
			if (dictionarySize > 512){
				System.out.println ("FYI, you passed the dictionary size limit of 512!");
			}
		}

		//This block of code will execute if an error occurs in the try block
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
