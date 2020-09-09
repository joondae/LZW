import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//HUB DESCRIPTION:
//created repo first, then changed project properties: properties>project nature>add>java
	//is this dangerous?
	//(rather than creating java project first then creating repo w/ java project selected as repo folder)

//NOTE: Your table of codes should have a MAXIMUM size,
//after which you no longer add any more codes to the table;
//what size that is is up to you.

//How you output the codes into the encoded file: also up to you.
//What you initialize the table of codes with: up to you.
//What data structures you use: up to you.
//Lots of things: up to you!

public class Encoder {
	public ArrayList<Integer> encodeFile(String fileName) {
		//not sure what we're supposed to do after making the codes...
		return generateCodes(fileName, initializeDictionary());
	}

	private HashMap<String, Integer> initializeDictionary() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		//adds ASCII table (all characters w/ decimal values from 0-255)
		for(int i = 0; i < 256; i++) {
			//alternative code for key: (char) i + ""
			map.put(Character.toString((char) i), i);
		}
		
		return map;
	}
	
	private ArrayList<Integer> generateCodes(String fileToEncode, HashMap<String, Integer> map) {
		//not sure what size should be (help)
		ArrayList<Integer> codes = new ArrayList<Integer>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileToEncode)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("encodedFile.txt")));
			
			while(br.ready()) {
				//String because p can be multiple letters
				String p = null;
				char c = '\0';
				
				if(map.containsKey(p + c)) {
					//P = concat(P,C)
					p += c;
				}
				else {
					//output the code word which denotes P to the codestream
					codes.add(Integer.parseInt(p));
					bw.write(Integer.parseInt(p) + " ");
					
					//add the string concat(P,C) to the dictionary
					map.put(p + c, Integer.parseInt(p + c));
					
					//P = C
					p = Character.toString(c);
				}
			}
			
			br.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return codes;
	}
}
