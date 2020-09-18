import java.util.*;
import java.io.*;

public class Decoder {
	
	public static HashMap<String, Integer> dictionary = new HashMap<String, Integer>(512);
	
	public void decodeFile(File encoded) throws IOException {//method to decode file
		BufferedReader br=new BufferedReader(new FileReader(encoded));
		ArrayList<Integer> codes=new ArrayList<Integer>();//arraylist for codes
		boolean begin=false;//to make sure we know when to begin
		boolean newString=false;//to make sure we know when we hit a string that needs to be added to dictionary
		int a;//used for while loops as a placeholder
		String add = "";//string we add to dictionary
		int size = 0;//length of the string
		int sizeLimit = 0;//will rise until it hits the size limit of the string then will reset
		int currentCombination = 0;//index of current string we want to add
		while(br.ready())
		{
			//knowing when the z will pop up so we can begin
			
			
			//while br reads a character
			while ( ( a = br.read () ) != -1 )
			{
				//now we begin
				if(begin==true)
				{
					//if this string needs to be added to dictionary
					if(newString==true)
					{
						//keep adding chars until we hit the sizeLimit of the string
						add+=(char)a;
						sizeLimit+=1;
						if(sizeLimit==size)
						{
							//add string and index to dictionary, then reset.
							dictionary.put(add, currentCombination);
							sizeLimit=0;
							newString=false;
							add="";
						}
					}
				else {
					//we finished the index of the string we want
					if(String.valueOf((char)a).equals("$"))
					{
						currentCombination=Integer.parseInt(add);
						add="";
					}
					//we got the length of the string we want
					else if(String.valueOf((char)a).equals("#"))
					{
						size=Integer.parseInt(add);
						newString=true;
					}
					//adding chars to the string
					else
					{
						add+=((char)a);
					}
				}
				}
				//indicates we've hit the dictionary
				else if(String.valueOf((char)a).equals("z"))
				{
					begin=true;
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
		BufferedWriter bw=new BufferedWriter(new FileWriter("decodedFile.txt"));
		bw.append(output);
		bw.close();
		}
	
	public static void main(String[]args)
	{
		
	}
	
}
