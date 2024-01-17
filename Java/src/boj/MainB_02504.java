package boj;

import java.io.*;
import java.util.*;

public class MainB_02504 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_02504.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		int ans = solve(str);
		System.out.println(ans);
	}
	
	public static int solve(String str) {
		int len = str.length();
		Stack<Character> stack = new Stack<Character>();
		int[] scores = new int[len];
		
		for(int i=0; i<len; i++) {
			char temp = str.charAt(i);
			
			if(stack.isEmpty()) {
				stack.add(temp);
			}
			else if(check(temp, stack.peek())){
				stack.pop();
				int depth = stack.size();
				int val = (int)temp/10 == 9 ? 3 : 2;
				int add = 0;
				
				if(scores[depth+1] != 0) {
					add = val * scores[depth+1];
					scores[depth+1] = 0;
				} else {
					add = val;
				}
				
				scores[depth] += add;
			}
			else {
				stack.add(temp);
			}
		}
		
		if(!stack.isEmpty()) return 0;
		
		return scores[0];
	}
	
	public static boolean check(char cur, char prev) {
		if((int)cur/10 != (int)prev/10) return false;
		else if((cur==']' && prev=='[') || (cur==')' && prev=='('))
			return true;
		else
			return false;
	}
	
}
