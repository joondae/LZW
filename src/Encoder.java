//imported all io and util assets rather than having several lines importing one at a time
import java.io.*;
import java.util.*;

public class Encoder {
	
	//made dictionary a public static variable within the Encoder class so that it can be accessed in the Decoder class to make decoding easier and more efficient
	public static HashMap<String, Integer> dictionary = new HashMap<String, Integer>(512);

	public void encodeFile(String fileName) {
		generateCodestream(fileName, initializeDictionary());
	}

	private HashMap<String, Integer> initializeDictionary() {
		//512 for 9 bit encoding
		
		
		//adds ASCII table (all characters w/ decimal values from 0-255)
		for(int i = 0; i < 256; i++) {
			dictionary.put(Character.toString((char) i), i);
		}
		
		return dictionary;
	}
	
	private void generateCodestream(String fileToEncode, HashMap<String, Integer> dictionary) {
		String p = "";
		//change to 257 for EOF marker
		int dictionarySize = 256;
		
		try {
			File file = new File(fileToEncode);
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file.getAbsolutePath() + ".lzw")));
			int current = 0;
			String code = "";
			while((current = br.read()) != -1) {
				char c = (char) current;
				
				//if present in dictionary:
				if(dictionary.containsKey(p + c)) {
					//P = concat(P,C)
					p += c;
				}
				//if not present in dictionary:
				else {
					//output the code word which denotes P to the codestream
					code += dictionary.get(p) + " ";

					//add the string concat(P,C) to the dictionary
					dictionary.put(p + c, dictionarySize);
					dictionarySize++;
					
					//P = C
					p = "" + c;
				}
				//ends the encoded values with indicated z, starts the dictionary,separates them with spaces
				//writes dictionary into file, with : to separate the index from the string
				
			}
			
			code += dictionary.get(p);
			
			bw.write (code);

			br.close();
			bw.close();

		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
