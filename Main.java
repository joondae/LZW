import java.util.ArrayList;

public class Main {
	public static void main(String [] args) {
		Encoder e = new Encoder();
		ArrayList<Integer> codes = new ArrayList<Integer>();
		Decoder d = new Decoder();
		
		
		codes = e.encodeFile("fileToEncode.txt");
		System.out.println(codes.size());// + "\n" + codes);
		//d.decodeFile(codes);
		
		
		/*
		codes = e.encodeFile("lzw-file1.txt");
		System.out.println(codes.size() + "\n" + codes);
		//d.decodeFile(codes);
		*/
		
		/*
		codes = e.encodeFile("lzw-file2.txt");
		System.out.println(codes.size() + "\n" + codes);
		//d.decodeFile(codes);
		*/
		
		/*
		codes = e.encodeFile("lzw-file3.txt");
		System.out.println(codes.size() + "\n" + codes);
		//d.decodeFile(codes);
		*/
	}
}
