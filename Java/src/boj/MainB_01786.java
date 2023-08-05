package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainB_01786 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01786.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		String pattern = br.readLine();
		
		solve(str, pattern, sb);
		System.out.println(sb.toString());
	}
	
	static void solve(String str, String pattern, StringBuilder sb) {
		int tlen = str.length();
		int plen = pattern.length();
		if(tlen < plen) {
			sb.append(0);
			return;
		}
		
		int ans = 0;
		List<Integer> list = new ArrayList<Integer>();
		
		int[] pi = generate_table(pattern, plen);
		
		int j = 0;
		for(int i=0; i<tlen; i++) {
			if(str.charAt(i) == pattern.charAt(j)) {
				j++;
				if(j==plen) {
					ans++; list.add(i+2-plen);
					j=pi[j-1];
				}
			}
			else {
				while(j>0 && str.charAt(i) != pattern.charAt(j)) 
					j = pi[j-1];
				if(str.charAt(i) == pattern.charAt(j)) j++;
			}
		}
		
		sb.append(ans).append("\n");
		for(int i=0; i<ans; i++) 
			sb.append(list.get(i)).append(" ");
	}
	
	static int[] generate_table(String pattern, int len) {
		int[] ret = new int[len];
		int j = 0;
		for(int i=1; i<len; i++) {
			if(pattern.charAt(i) == pattern.charAt(j)) 
				ret[i] = ++j;
			else {
				while(j>0 && pattern.charAt(i) != pattern.charAt(j)) 
					j = ret[j-1];
				if(pattern.charAt(i) == pattern.charAt(j)) j++;
				ret[i] = j;
			}
		}
		return ret;
	}
}
