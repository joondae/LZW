/*
* Praise be to Ms. Kaufman and Computer Science A teachers.
* They spoke the truth when they spoke of handwritten code and BlueJ.
*/

//Imported all io and util assets rather than having several lines importing one at a time
import java.io.*;
import java.util.*;

/**
 * <p>
 * This class encodes a given file whose name is denoted by
 * <code>fileName</code> and prints the resulting codestream to a file whose
 * name is <code>fileName</code>.txt.lzw.
 * </p>
 * 
 * <p>
 * 1 work of ASCII Art is at the end of this file.
 * </p>
 * 
 * @author Max
 *
 */
public class Encoder {
	// Initializes a dictionary HashMap that will store ASCII characters and other
	// character combinations, and pair them with codes
	// Size = 512 for 9 bit encoding
	public static HashMap<String, Integer> dictionary = new HashMap<String, Integer>(512);

	/**
	 * This method contains & executes all helper methods used to encode given file,
	 * denoted by <code>fileName</code>, according to the LZW compression scheme.
	 * 
	 * @param fileName the name of the file to be encoded
	 */
	public void encodeFile(String fileName) {
		generateCodestream(fileName, initializeDictionary());
	}

	/**
	 * This method adds all characters with decimal values from 0-255 to
	 * <code>dictionary</code>.
	 * 
	 * @return the now initialized dictionary
	 */
	private HashMap<String, Integer> initializeDictionary() {
		for (int i = 0; i < 256; i++) {
			dictionary.put(Character.toString((char) i), i);
		}
		return dictionary;
	}

	/**
	 * This method parses through <code>fileToEncode</code> and writes the encoded
	 * codestream to a new, encoded file whose name is
	 * <code>file.getAbsolutePath() + ".lzw"</code>.
	 * 
	 * @param fileToEncode name of the file to be encoded
	 * @param dictionary   HashMap that contains ASCII table and other character
	 *                     combinations as keys mapped to Integer code values
	 */
	private void generateCodestream(String fileToEncode, HashMap<String, Integer> dictionary) {
		// Previous character read/combination of characters stored
		String previous = "";
		// Dictionary size is initially 256 due to storing ASCII characters 0-255
		int dictionarySize = 256;

		try {
			File file = new File(fileToEncode);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			BufferedWriter bufferedWriter = new BufferedWriter(
					new FileWriter(new File(file.getAbsolutePath() + ".lzw")));
			int currentChar = 0;

			// Reads file character by character until it reaches the end
			while ((currentChar = bufferedReader.read()) != -1) {
				char currentTempVariable = (char) currentChar;
				// If the current character is present in dictionary:
				if (dictionary.containsKey(previous + currentTempVariable)) {
					// P = concat(P,C)
					previous += currentTempVariable;
				}
				// If the current character is not present in dictionary:
				else {
					// Writes the code word which denotes P to the file
					bufferedWriter.write("" + dictionary.get(previous) + " ");
					// Add the string concat(P,C) to the dictionary
					dictionary.put(previous + currentTempVariable, dictionarySize);
					// Increases the number of elements in the dictionary map by one
					dictionarySize++;
					// P = C
					previous = "" + currentTempVariable;
				}
			}

			// Writes the code word correlating with String p to the file
			bufferedWriter.write("" + dictionary.get(previous));

			// Closes reading & writing streams, releases any associated system resources
			bufferedReader.close();
			bufferedWriter.close();

			// Informs the user if they pass the size limit for the dictionary
			if (dictionarySize > 512) {
				System.out.println("FYI, you passed the dictionary size limit of 512!");
			}
		}

		catch (IOException exception) {
			exception.printStackTrace();
		}

	}
}

/*
 * ASCII Art (dude getting blasted by his computer screen???):
 *        							 )
                            )      ((     (
                           (        ))     )
                    )       )      //     (
               _   (        __    (     ~->>
        ,-----' |__,_~~___<'__`)-~__--__-~->> <
        | //  : | -__   ~__ o)____)),__ - '> >-  >
        | //  : |- \_ \ -\_\ -\ \ \ ~\_  \ ->> - ,  >>
        | //  : |_~_\ -\__\ \~'\ \ \, \__ . -<-  >>
        `-----._| `  -__`-- - ~~ -- ` --~> >
         _/___\_    //)_`//  | ||]
   _____[_______]_[~~-_ (.L_/  ||
  [____________________]' `\_,/'/
    ||| /          |||  ,___,'./
    ||| \          |||,'______|
    ||| /          /|| I==||
    ||| \       __/_||  __||__
-----||-/------`-._/||-o--o---o---
  ~~~~~'
 */