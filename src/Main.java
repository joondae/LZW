import java.util.ArrayList;

public class Main {
	public static void main(String [] args) {
		Encoder e = new Encoder();
		Decoder d = new Decoder();
		
		
		
		e.encodeFile("lzw-text0.txt");
		//System.out.println(codes.size() + "\n" + codes);
		d.decodeFile("lzw-text0.txt.lzw");
		
		
		/*
		codes = e.encodeFile("lzw-file1.txt");
		System.out.println(codes.size() + "\n" + codes);
		*/
		//d.decodeFile(codes);
		
		
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

//decoder: number and the a space, each character is an int, it skips spaces