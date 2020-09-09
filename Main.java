import java.util.ArrayList;

public class Main {
	public static void main(String [] args) {
		Encoder e = new Encoder();
		ArrayList<Integer> codes = new ArrayList<Integer>(512);
		Decoder d = new Decoder();
		
		codes = e.encodeFile("fileToEncode.txt");
		d.decodeFile(codes);
	}
}
