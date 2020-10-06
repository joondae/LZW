import java.util.*;
import java.io.*;

public class Decoder {
		
	public static HashMap<Integer, String> dictionary = new HashMap<Integer, String>(512);//Dictionary containing all relevant characters and Strings from the original text (N.B. This dictionary is Integer,String but the other was String,Integer)
	
	public void decodeFile(String encodedFile) {//method to decode file
		try{

			BufferedReader br=new BufferedReader(new FileReader(new File(encodedFile)));//reads the file of codes from the encoder
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(encodedFile.substring(0,encodedFile.length()-8)+"-decoded.txt"))); //outputs the text into a file by the name of [OriginalFile]-decoded.txt
			ArrayList<Integer> codes=new ArrayList<Integer>();//arraylist for codes
			
			for(int i = 0; i < 256; i++) {
				dictionary.put(i,""+(char)i); //adds a String containing each char of value 0 through 255 in the ascii table to the dictionary
			}

			while(br.ready()){ //Goes through each character in the input file until it reaches the end/null, puts each code in the input file into an integer arraylist
				
				String currentString = ""; //Keeps track of the current code in the file we are on, add the chars to this until you reach a space
				int currentChar = br.read(); //Keeps track of the current char in the file we are on

				while(currentChar!=-1&&currentChar!=(char)' '){ //Goes through the file while current character is not a space or the end of the file
					currentString += (char)currentChar;
					currentChar = br.read();
				}

				codes.add(Integer.parseInt(currentString)); //adds the current code to the dictionary

			}

			for (int i = 0; i < codes.size()-1; i++){ //Goes through each code except the last one (because I deal with two codes at the same time) and recreates the dictionary with those codes
				
				if (dictionary.containsKey(codes.get(i+1))){
					dictionary.put(256+i,""+dictionary.get(codes.get(i))+dictionary.get(codes.get(i+1)).charAt(0)); //Add the dictionary String of the first code + the dictionary String of the second code as the next entry in the dictionary
				}else{//handles the special case when there are 3+ consecutive of the same char in the original text [Ex: 97 256 --> aaa]
					dictionary.put(256+i,""+dictionary.get(codes.get(i))+dictionary.get(codes.get(i)).charAt(0)); //Handles the special case mentioned above
				}
			}
			
			for (Integer codeValue : codes){ //Goes through each code in the input file
				bw.write(dictionary.get(codeValue)); //Outputs the dictionary String of that code
			}

			br.close();
			bw.close();

		}catch(Exception exe){
			exe.printStackTrace();
		}
	}
	
	public static void main(String[]args)
	{
		Decoder decoder = new Decoder();
		decoder.decodeFile("practiceCodes.txt");
	}
	
}