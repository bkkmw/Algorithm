package boj;

import java.io.*;
import java.util.*;

public class MainB_01406 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01406.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String word = br.readLine();
		
		int M = Integer.parseInt(br.readLine());
		char[][] cmds = new char[M][2];
		
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			cmds[i][0] = cmd;
			if(cmd == 'P')
				cmds[i][1] = st.nextToken().charAt(0);
		}
		
		String ans = solve(word, cmds);
		System.out.println(ans);
		br.close();
	}
	
	public static String solve(String word, char[][] cmds) {
		int len = word.length();
		int M = cmds.length;
		
		Stack<Character> prev = new Stack<Character>();
		Stack<Character> next = new Stack<Character>();
		
		for(int i=0; i<len; i++) {
			prev.push(word.charAt(i));
		}
		
		for(int i=0; i<M; i++) {
			char cmd = cmds[i][0];
			
			if(cmd == 'L') {
				if(prev.isEmpty()) continue;
				next.push(prev.pop());
			} else if(cmd == 'D') {
				if(next.isEmpty()) continue;
				prev.push(next.pop());
			} else if(cmd == 'B') {
				if(prev.isEmpty()) continue;
				prev.pop();
			} else if(cmd == 'P') {
				prev.push(cmds[i][1]);
			}
		}
		
		int prev_size = prev.size();
		int next_size = next.size();
		len = prev_size + next_size;
		char[] ret = new char[len];
		
		for(int i=0; i<prev_size; i++) {
			ret[prev_size-1-i] = prev.pop();
		}
		for(int i=0; i<next_size; i++) {
			ret[prev_size+i] = next.pop();
		}
		
		return char_arr_to_string(ret);
	}
	
	public static String char_arr_to_string(char[] arr) {
		StringBuilder sb = new StringBuilder();
		int len = arr.length;
		
		for(int i=0; i<len; i++) {
			sb.append(arr[i]);
		}
		
		return sb.toString();
	}
}
