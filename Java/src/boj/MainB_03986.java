package boj;

import java.io.*;
import java.util.*;

public class MainB_03986 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_03986.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		for(int i=0; i<N; i++) {
			words[i] = br.readLine();
		}
		
		int ans = solve(words);
		System.out.println(ans);
	}
	
	public static int solve(String[] words) {
		int ret = 0, N = words.length;
		Stack<Character> st = new Stack<Character>();
		
		for(int i=0; i<N; i++) {
			String word = words[i];
			int len = word.length();
			if(len % 2 == 1) continue;
			
			st.add(word.charAt(0));
			for(int j=1; j<len; j++) {
				char cur = word.charAt(j);
				
				if(st.isEmpty()) st.add(cur);
				else if(st.peek() == cur) {
					st.pop();
				}
				else {
					st.add(cur);
				}
			}
			
			if(st.isEmpty()) ret++;
			else st.clear();
		}
		return ret;
	}
	
}
