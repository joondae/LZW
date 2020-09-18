//UNCOMMENT "blocks" TO RUN EACH CASE
import java.util.ArrayList;

public class Main {
	public static void main(String [] args) {
		Encoder e = new Encoder();
		ArrayList<Integer> codes = new ArrayList<Integer>();
		Decoder d = new Decoder();
		
		//DO NOT RUN LARGE FILES IF YOU DON'T HAVE DICTIONARY RESET IMPLEMENTED OR INFINITELY BIG DICTIONARY
		//test for whatever you want
		/*
		codes = e.encodeFile("fileToEncode.txt");
		System.out.println(codes.size());// + "\n" + codes);
		//d.decodeFile(codes);
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
	}
}
