package boj;

import java.io.*;
import java.util.*;

public class MainB_10799 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_10799.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		int ans = solve(input);
		System.out.println(ans);
	}
	
	public static int solve(String input) {
		int ret = 0, len = input.length(), cnt = 0;
		Stack<Character> st = new Stack<Character>();
		
		for(int i=0; i<len; i++) {
			char cur = input.charAt(i);
			if(cur == '(') {
				if(input.charAt(i+1) == ')') {
					ret += cnt;
					i++;
				}
				else {
					cnt ++;
					ret ++;
					st.add(cur);
				}
			}
			else {
				st.pop();
				cnt--;
			}
			
		}
		
		return ret;
	}

}
