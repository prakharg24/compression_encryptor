import java.util.*;
import java.io.*;

class node{
	int ch;
	int freq;
	node left = null;
	node right = null;
}

public class huffman{
	int[] fre = new int[256];
	String[] table = new String[256];
	String ans = "";
	boolean[] vis = new boolean[256];
	node root = new node();
	String main;
	void add(String file, String key){
		int a = file.length()/key.length();
		this.createfreq(file, 1);
		this.maketree();
		this.createcode(file);
		this.createfreq(key, a);
	}
	void createfreq(String a, int rep){
		for(int i=0;i<a.length();i++){
			fre[(int)a.charAt(i)] = fre[(int)a.charAt(i)] + rep;
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
		int sum = 0;
		for(int i=0;i<256;i++){
			fre[i] = Integer.parseInt(reader.readLine());
			sum = sum + fre[i];
		}
		int tem = sum/a.length();
		for(int i=0;i<a.length();i++){
			fre[(int)a.charAt(i)] = fre[(int)a.charAt(i)] - tem/2;
		}
		this.maketree();
	}
	String decode(String a){
		node curr = root;
		int i=0;
		String ans = "";
		while(i<a.length()-1){
			if(curr.left==null && curr.right==null){
				ans = ans + (char)curr.ch;
				curr = root;
			}
			else if(a.charAt(i)=='1'){
				curr = curr.right;
				i++;
			}
			else{
				ans = ans + (char)curr.left.ch;
				curr = root;
				i++;
			}
		}
		if(a.charAt(i)=='0'){
			ans = ans + (char)curr.left.ch;
		}
		else{
			ans = ans + (char)curr.right.ch;
		}
		return ans;
	}
}