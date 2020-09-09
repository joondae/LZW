import java.util.ArrayList;

public class Main {
	public static void main(String [] args) {
		Encoder e = new Encoder();
		//put size (help)
		ArrayList<Integer> codes = new ArrayList<Integer>();
		Decoder d = new Decoder();
		
		codes = e.encodeFile("fileToEncode.txt");
		d.decodeFile(codes);
	}
}
