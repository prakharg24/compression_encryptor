import java.util.*;
import java.io.*;

public class decode{
	public static void main(String args[]) throws Exception{
		Scanner in = new Scanner(System.in);
		FileReader file = new FileReader("coded.txt");
		BufferedReader reader = new BufferedReader(file);
		FileWriter decoded = new FileWriter("decoded.txt");

		String line = reader.readLine();

		System.out.println("Enter the key :");
		String key = in.nextLine();

		huffman a = new huffman();
		a.recreate(key);
		String data = a.decode(line, a.root);
		decoded.write(data);
		decoded.flush();
		decoded.close();
	}
}

class node{
	int ch;
	int freq;
	node left = null;
	node right = null;
}

class huffman{
	int[] fre = new int[256];
	String[] table = new String[256];
	String ans = "";
	boolean[] vis = new boolean[256];
	node root = new node();
	void add(String file, String key){
		this.createfreq(file);
		this.maketree();
		this.createcode(file);
		this.createfreq(key);
	}
	void createfreq(String a){
		for(int i=0;i<a.length();i++){
			fre[(int)a.charAt(i)]++;
		}
	}
	void maketree(){
		int[] sortfre = new int[256];
		for(int i=0;i<256;i++){
			sortfre[i] = fre[i];
		}
		Arrays.sort(sortfre);
		node current = new node();
		current = root;
		String currcode = "";
		node last = new node();
		for(int i=255;i>=0;i--){
			node temp = new node();
			temp.freq = sortfre[i];
			if(temp.freq>0){
				temp.ch = findindex(sortfre[i], fre);
				table[temp.ch] = currcode + "0";
				current.left = temp;
				current.right = new node();
				last = current;
				current = current.right;
				currcode = currcode + "1";
			}
		}
		last.freq = last.left.freq;
		last.ch = last.left.ch;
		last.left = null;
		last.right = null;
		table[last.ch] = currcode.substring(0, currcode.length()-1);
	}
	void createcode(String a){
		for(int i=0;i<a.length();i++){
			ans = ans + table[a.charAt(i)];
		}
	}
	String encode(){
		return ans;
	}
	int findindex(int a, int[] arr){
		for(int i=0;i<arr.length;i++){
			if(arr[i]==a && vis[i]==false){
				vis[i] = true;
				return i;
			}
		}
		return 0;
	}
	void print() throws Exception{
		FileWriter ab = new FileWriter("table.txt");
		for(int i=0;i<256;i++){
			ab.write(fre[i] + "\n");
		}
		ab.flush();
		ab.close();
	}
	void recreate(String a) throws Exception{
		FileReader file = new FileReader("table.txt");
		BufferedReader reader = new BufferedReader(file);
		for(int i=0;i<256;i++){
			fre[i] = Integer.parseInt(reader.readLine());
		}
		for(int i=0;i<a.length();i++){
			fre[(int)a.charAt(i)]--;
		}
		this.maketree();
	}
	String decode(String a, node curr){
		if(a.length()==1){
			if(a.charAt(0)=='0'){
				return (char)curr.left.ch + "";
			}
			else{
				return (char)curr.right.ch + "";
			}
		}
		else{
			if(curr.left==null && curr.right==null){
				return (char)curr.ch  + decode(a.substring(0), root);
			}
			if(a.charAt(0)=='0'){
				//System.out.println((char)curr.left.ch);
				return ((char)curr.left.ch + decode(a.substring(1), root));
			}
			else{
				//System.out.println((char)curr.ch);
				return decode(a.substring(1), curr.right);
			}
		}
	}
}