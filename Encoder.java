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
	
	//"main" method
	public ArrayList<Integer> encodeFile(String fileName) {
		return generateCodestream(fileName, initializeDictionary());
	}

	//adds ASCII table (all characters w/ decimal values from 0-255) to table/dictionary
	private HashMap<String, Integer> initializeDictionary() {
		HashMap<String, Integer> dictionary = new HashMap<String, Integer>(maxDictionarySize);
		
		for(int i = 0; i < 256; i++) {
			dictionary.put(Character.toString((char) i), i);
		}
		
		return dictionary;
	}
	
	//NOTE: current implementation does NOT cover resetting the dictionary
	//generates codestream using LZW compression formula
	private ArrayList<Integer> generateCodestream(String fileToEncode, HashMap<String, Integer> dictionary) {
		ArrayList<Integer> codestream = new ArrayList<Integer>();
		String previous = "";
		//change to 257 for EOF marker
		int dictionarySize = 256;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileToEncode)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("encodedFile.txt")));
			
			while(br.ready() && dictionary.size() < maxDictionarySize) {
				char current = (char) br.read();
				
				//if present in dictionary:
				if(dictionary.containsKey(previous + current)) {
					//P = concat(P,C)
					previous += current;
				}
				//if not present in dictionary:
				else {
					//output the code word which denotes P to the codestream
					codestream.add(dictionary.get(previous));
					bw.write(dictionary.get(previous) + " ");

					//add the string concat(P,C) to the dictionary
					dictionary.put(previous + current, dictionarySize++);
					
					//alternative dictionary reset
					//dictionary.replace(p + c, dictionarySize++);
					
					//P = C
					previous = Character.toString(current);
				}
			}
			
			//to cover last read
			codestream.add(dictionary.get(previous));
			bw.write(dictionary.get(previous) + " ");
			
			//if dictionary size reaches chosen bit limit
			if(dictionary.size() >= maxDictionarySize) {
				System.out.println("Maximum dictionary size reached - stopping compression");
				
				while(br.ready()) {
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
			}
			
			br.close();
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return codestream;
	}
}
