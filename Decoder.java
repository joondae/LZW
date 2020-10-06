/*
* Praise be to Ms. Kaufman and Computer Science A teachers.
* They spoke the truth when they spoke of handwritten code and BlueJ.
*/

//Imported all io and util assets rather than having several lines importing one at a time
import java.util.*;
import java.io.*;

/**
 * <p>
 * This class decodes a given LZW compressed file whose name is denoted by
 * <code>encodedFile</code> and contains a codestream of compressed characters.
 * </p>
 * 
 * <p>
 * 1 work of ASCII Art is at the end of Encoded.java.
 * </p>
 * 
 * @author Max
 */
public class Decoder {
	// Dictionary containing all relevant characters & strings from original text
	// Size = 512 to be compatible with 9 bit encoding
	private HashMap<Integer, String> dictionary = new HashMap<Integer, String>(512);
	// ArrayList<Integer> that contains the encoded codestream
	private ArrayList<Integer> codes = new ArrayList<Integer>();

	/**
	 * This method contains all helper methods used to decode given file denoted by
	 * <code>encodedFile</code>.
	 * 
	 * @param encodedFile the name of the file to be decoded
	 */
	public void decodeFile(String encodedFile) {
		initializeDictionary();
		catalogueCodestream(encodedFile);
		recreateDictionary();
		printDecodedCodestream(encodedFile);
	}

	/**
	 * This method initializes the dictionary by adding a String containing each
	 * char of value 0-255 in the ASCII table.
	 */
	private void initializeDictionary() {
		for (int i = 0; i < 256; i++) {
			dictionary.put(i, "" + (char) i);
		}
	}

	/**
	 * This method parses through the encoded file and reads the codestream into
	 * <code>codes</code>.
	 * 
	 * @param encodedFile the name of the encoded file that contains the codestream
	 */
	private void catalogueCodestream(String encodedFile) {
		// Reads the file of codes from the encoder
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(new File(encodedFile)));

			// Goes through each character in the input file until it reaches the end/null,
			// puts each code in the input file into an ArrayList<Integer>
			while (bufferedReader.ready()) {
				// Keeps track of the current code in the file we are on, add the chars to this
				// until you reach a space
				String currentString = "";
				// Keeps track of the current char in the file we are on
				int currentChar = bufferedReader.read();

				// Goes through the file while current character is not a space or the end of
				// the file
				while (currentChar != -1 && currentChar != (char) ' ') {
					currentString += (char) currentChar;
					currentChar = bufferedReader.read();
				}

				// Adds the current code to the dictionary
				codes.add(Integer.parseInt(currentString));

				bufferedReader.close();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * This method recreates the dictionary used to encode the original file.
	 * 
	 */
	private void recreateDictionary() {
		// Goes through each code except the last one
		// (because I deal with two codes at the same time) and recreates the dictionary
		// with those codes
		for (int i = 0; i < codes.size() - 1; i++) {
			// Add the dictionary String of the first code + the dictionary String of the
			// second code
			// as the next entry in the dictionary
			if (dictionary.containsKey(codes.get(i + 1))) {
				dictionary.put(256 + i, "" + dictionary.get(codes.get(i))
						+ dictionary.get(codes.get(i + 1)).charAt(0));
			}
			// Handles the special case when there are 3+ consecutive of the same char in
			// the original text
			// [Ex: 97 256 --> aaa]
			else {
				dictionary.put(256 + i,
						"" + dictionary.get(codes.get(i)) + dictionary.get(codes.get(i)).charAt(0));
			}
		}
	}

	/**
	 * This method prints the decoded codestream to a new file with the name
	 * [OriginalFile]-decoded.txt
	 * 
	 * @param encodedFile
	 */
	private void printDecodedCodestream(String encodedFile) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(
					new File(encodedFile.substring(0, encodedFile.length() - 8) + "-decoded.txt")));

			// Iterate through each code in input file & output its dictionary String
			for (Integer codeValue : codes) {
				bufferedWriter.write(dictionary.get(codeValue));
			}

			bufferedWriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}