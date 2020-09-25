//UNCOMMENT "blocks" TO RUN EACH CASE
import java.util.ArrayList;

public class Main {
	public static void main(String [] args) {
		Encoder e = new Encoder();
		ArrayList<Integer> codes = new ArrayList<Integer>();
		Decoder d = new Decoder();
		
		//DO NOT RUN LARGE FILES AND PRINT CODESTREAM
		//test for whatever you want
		/*
		codes = e.encodeFile("/Users/Max/desktop/12.5MBFile.txt");
		System.out.println(codes.size());// + "\n" + codes);
		d.decodeFile(codes);
		*/
		
		//test for lzw-file1.txt
		/*
		codes = e.encodeFile("lzw-file1.txt");
		System.out.println(codes.size() + "\n" + codes);
		//d.decodeFile(codes);
		*/
		
		//test for lzw-file2.txt
		/*
		codes = e.encodeFile("lzw-file2.txt");
		System.out.println(codes.size() + "\n" + codes);
		//d.decodeFile(codes);
		*/
		
		//test for lzw-file3.txt
		/*
		codes = e.encodeFile("lzw-file3.txt");
		System.out.println(codes.size() + "\n" + codes);
		//d.decodeFile(codes);
		*/
		
		//test for lzw-text0.txt (the 1.1 MB big file)
		/*
		codes = e.encodeFile("lzw-text0.txt");
		System.out.println(codes.size());
		d.decodeFile(codes);
		*/
	}
}
