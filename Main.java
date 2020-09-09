import java.util.ArrayList;

public class Main {
	public static void main(String [] args) {
		Encoder e = new Encoder();
		ArrayList<Integer> codes = new ArrayList<Integer>(512);
		Decoder d = new Decoder();
		
		/*
		codes = e.encodeFile("fileToEncode.txt");
		System.out.println(codes);
		d.decodeFile(codes);
		*/
		
		codes = e.encodeFile("lzw-file 1");
		System.out.println(codes);
		//d.decodeFile(codes);
		
		/*
		codes = e.encodeFile("lzw-file 2.txt");
		System.out.println(codes);
		d.decodeFile(codes);
		*/
		
		/*
		codes = e.encodeFile("lzw-file 3.txt");
		System.out.println(codes);
		d.decodeFile(codes);
		*/
	}
}
