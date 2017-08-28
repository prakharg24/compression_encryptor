import java.util.*;
import java.io.*;

public class encode{
	public static void main(String args[]) throws Exception{
		Scanner in = new Scanner(System.in);
		FileReader file = new FileReader(args[0]);
		BufferedReader reader = new BufferedReader(file);
		FileWriter coded = new FileWriter("coded.txt");

		String data = "";
		String line = reader.readLine();
		while(line!=null){
			data = data + line + '\n';
			line = reader.readLine();
		}
		System.out.println("Enter the key :");
		String key = in.nextLine();
		huffman a = new huffman();
		a.add(data, key);
		String code = a.encode();
		a.print();
		coded.write(code);
		coded.flush();
		coded.close();
	}
}