import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Encoder {
	//512 for 9-bit encoding
	private int maxDictionarySize = 512;
	
	public ArrayList<Integer> encodeFile(String fileName) {
		return generateCodestream(fileName, initializeDictionary());
	}

	private HashMap<String, Integer> initializeDictionary() {
		HashMap<String, Integer> dictionary = new HashMap<String, Integer>(maxDictionarySize);
		
		//adds ASCII table (all characters w/ decimal values from 0-255)
		for(int i = 0; i < 256; i++) {
			dictionary.put(Character.toString((char) i), i);
		}
		
		return dictionary;
	}
	
	//NOTE: current implementation does NOT cover resetting the dictionary
	private ArrayList<Integer> generateCodestream(String fileToEncode, HashMap<String, Integer> dictionary) {
		ArrayList<Integer> codestream = new ArrayList<Integer>();
		String p = "";
		//change to 257 for EOF marker
		int dictionarySize = 256;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileToEncode)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("encodedFile.txt")));
			
			while(br.ready() && dictionary.size() < maxDictionarySize) {
				char c = (char) br.read();
				
				//if present in dictionary:
				if(dictionary.containsKey(p + c)) {
					//P = concat(P,C)
					p += c;
					
					//to cover last read
					if(br.ready() == false) {
						codestream.add(dictionary.get(p));
						bw.write(dictionary.get(p) + " ");
					}
				}
				//if not present in dictionary:
				else {
					//output the code word which denotes P to the codestream
					codestream.add(dictionary.get(p));
					bw.write(dictionary.get(p) + " ");

					//add the string concat(P,C) to the dictionary
					dictionary.put(p + c, dictionarySize++);
					//alternative dictionary reset
					//dictionary.replace(p + c, dictionarySize++);
					
					//P = C
					p = Character.toString(c);
				}
			}
			
			System.out.println("Maximum dictionary size reached - stopping compression");
			//if dictionary size reaches chosen bit limit
			while(br.ready() && dictionary.size() >= maxDictionarySize) {
				int c = br.read();
				codestream.add(c);
				bw.write(c + " ");
				
				//standard? dictionary reset
				//implement "flush character" feature?
				//calculate compression ratio?
				//dictionary = initializeDictionary();
				//dictionarySize = 256;
				
				//alternative dictionary reset (replace already mapped values starting w/ 1st element)
				//implement "flush character" feature here as well?
				//calculate compression ratio?
				//dictionary = new HashMap<String, Integer>(maxDictionarySize);
				//dictionarySize = 0;
			}
			
			br.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return codestream;
	}
}
