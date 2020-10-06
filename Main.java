import java.util.*;

public class Main {
	public static void main(String [] args) {
		//Initializes Encoder and Decoder
		Encoder e = new Encoder();
		Decoder d = new Decoder();
		
		//Runs encoding and decoding functions on the file in the parameter
		e.encodeFile("lzw-text0.txt");
		d.decodeFile("lzw-text0.txt.lzw");
		
	}
}