/*
* Praise be to Ms. Kaufman and Computer Science A teachers.
* They spoke the truth when they spoke of handwritten code and BlueJ.
*/

/**
 * <p>
 * This class is the separate tester .java file required by Coding Guidelines.
 * </p>
 * <p>
 * 1 work of ASCII Art is at the end of Encoder.java.
 * </p>
 * 
 * @author Max
 */
public class Main {
	/**
	 * This method is the single static main method required by Coding Guidelines.
	 * 
	 * @param args this parameter is not used for this assignment
	 */
	public static void main(String[] args) {
		// Initializes Encoder and Decoder
		Encoder encoder = new Encoder();
		Decoder decoder = new Decoder();

		// Runs encoding and decoding functions on the file in the parameter
		encoder.encodeFile("lzw-text0.txt");
		decoder.decodeFile("lzw-text0.txt.lzw");
		// Ensures compression/decompression is lossless
		System.out.print("Lossless compression/decompression? " + decoder.checkDecodedFile("lzw-text0.txt", "lzw-text0-decoded.txt"));
	}
}