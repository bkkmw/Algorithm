package boj;

import java.io.*;
import java.util.*;

public class MainB_10828 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_10828.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] cmds = new String[N];
		
		for(int i=0; i<N; i++)
			cmds[i] = br.readLine();
		
		solve(sb, cmds);
		System.out.println(sb.toString());
	}
	
	public static void solve(StringBuilder sb, String[] cmds) {
		int N = cmds.length;
		Stack<Integer> stack = new Stack<Integer>();
		StringTokenizer st;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(cmds[i]);
			String cmd = st.nextToken();
			
			if("push".equals(cmd)) {
				int num = Integer.parseInt(st.nextToken());
				stack.add(num);
			} else if("pop".equals(cmd)) {
				sb.append(stack.isEmpty()? -1 : stack.pop()).append("\n");
			} else if("size".equals(cmd)) {
				sb.append(stack.size()).append("\n");
			} else if("empty".equals(cmd)) {
				sb.append(stack.isEmpty() ? 1 : 0).append("\n");
			} else {
				sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
			}
		}
	}
}
