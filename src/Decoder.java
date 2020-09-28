import java.util.*;
import java.io.*;

public class Decoder {
		
	public static HashMap<Integer, String> dictionary = new HashMap<Integer, String>(512);
	
	public void decodeFile(String encodedFile) {//method to decode file
		try{

			BufferedReader br=new BufferedReader(new FileReader(new File(encodedFile)));//reads the file of codes from the encoder
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(encodedFile.substring(0,encodedFile.length()-8)+"-decoded.txt")));
			ArrayList<Integer> codes=new ArrayList<Integer>();//arraylist for codes
			
			for(int i = 0; i < 256; i++) {
				dictionary.put(i,Character.toString((char) i));
			}

			while(br.ready()){
				String currentString = "";
				int currentChar = br.read();

				while(currentChar!=-1&&currentChar!=(char)' '){
					currentString += (char)currentChar;
					currentChar = br.read();
				}
				codes.add(Integer.parseInt(currentString));

			}
			for (int i = 0; i < codes.size()-1; i++){
				if (dictionary.containsKey(codes.get(i+1))){
					dictionary.put(256+i,""+dictionary.get(codes.get(i))+dictionary.get(codes.get(i+1)).charAt(0));
				}else{
					dictionary.put(256+i,""+dictionary.get(codes.get(i))+dictionary.get(codes.get(i)).charAt(0));
				}
			}
			
			for (Integer codeValue : codes){
				bw.write(dictionary.get(codeValue));
			}
			br.close();
			bw.close();



			/**


				//knowing when the z will pop up so we can begin
				
				
				//while br reads a character
				while ( ( a = br.read () ) != -1 ){
					//now we begin
					if(begin==true){
						//if this string needs to be added to dictionary
						if(newString==true){
							//keep adding chars until we hit the sizeLimit of the string
							add+=(char)a;
							sizeLimit+=1;
							if(sizeLimit==size){
								//add string and index to dictionary, then reset.
								dictionary.put(add, currentCombination);
								sizeLimit=0;
								newString=false;
								add="";
							}
						}else {
							//we finished the index of the string we want
							if(String.valueOf((char)a).equals("$")){
								currentCombination=Integer.parseInt(add);
								add="";
							}
							//we got the length of the string we want
							else if(String.valueOf((char)a).equals("#")){
								size=Integer.parseInt(add);
								newString=true;
							}
							//adding chars to the string
							else{
								add+=((char)a);
							}
						}
					}
				}
			}
			int temp;//temp int for storing decoded values
			int c=0;//int to store the parse of nums
			
			String nums = null;//string of decoded value
			
			while ((temp=br.read())!=-1){
				if (temp==32){
					codes.add(c=Integer.parseInt(nums));
					nums="";		
				}else{
					nums+=(char)temp;
				}
			}
			codes.add(c=Integer.parseInt(nums));
			br.close();
			String output="";
			//adds codes into output
			for (int i=0;i<codes.size();i++){
				output+=dictionary.get(codes.get(i));
			}
			//creates file and adds the output to the file
			bw.append(output);
			bw.close();
			}
			*/
		}catch(Exception exe){
			exe.printStackTrace();
		}
	}
	
	public static void main(String[]args)
	{
		Decoder decoder = new Decoder();
		decoder.decodeFile("practiceCodes.txt");
	}
	
}
