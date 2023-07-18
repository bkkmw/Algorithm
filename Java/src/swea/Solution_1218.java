package swea;

import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution_1218
{
	static HashMap<Character,Character> map = new HashMap<Character,Character>();
	public static void main(String args[]) throws Exception
	{
		System.setIn(new FileInputStream("input/swea/input_1218.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
		map.put('<', '>');
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String line = in.readLine();
			int N = Integer.parseInt(line);
			line = in .readLine();
			
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(solve(N, line));
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int solve(int N, String str) {
		char[] stack = new char[N];
		int top = -1;
		for(int i=0; i<N; i++) {
			char temp = str.charAt(i);
			if(temp == '(' || temp == '[' || temp == '{' || temp =='<') {
				stack[++top] = temp;
			}
			else {
				if(top == -1) return 0;
				if(map.get(stack[top--]) != temp) {
					return 0;
				}
			}
		}
		return (top == -1) ? 1:0;	
	}
}