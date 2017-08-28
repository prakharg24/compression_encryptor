import java.util.*;
import java.io.*;

public class decode{
	public static void main(String args[]) throws Exception{
		Scanner in = new Scanner(System.in);
		FileReader file = new FileReader("coded.txt");
		BufferedReader reader = new BufferedReader(file);
		FileWriter decoded = new FileWriter(args[0]);

		String line = reader.readLine();

		System.out.println("Enter the key :");
		String key = in.nextLine();

		huffman a = new huffman();
		a.recreate(key);
		String data = a.decode(line);
		decoded.write(data);
		decoded.flush();
		decoded.close();
	}
}