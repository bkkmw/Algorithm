package boj;

import java.io.*;
import java.util.*;
public class MainB_05430 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_05430.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<TC+1; tc++) {
			String cmd = br.readLine();
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int [N];
			
			st = new StringTokenizer(br.readLine().replaceAll("\\[|\\]", ""), ",");
			for(int i=0; i<N; i++) 
				arr[i] = Integer.parseInt(st.nextToken());
			
			int[] ans = solve(cmd, arr);
			append_ans(ans, sb);
		}
		System.out.println(sb.toString());
	}
	
	public static int[] solve(String cmd, int[] arr) {
		int N = arr.length;
		int size = cmd.length();
		Deque<Integer> dq = new LinkedList<Integer>();
		
		for(int i=0; i<N; i++)
			dq.addLast(arr[i]);
		
		int dir = 0;
		
		for(int i=0; i<size; i++) {
			char action = cmd.charAt(i);
			if(action == 'R') 
				dir = (dir+1)%2;
			else if(dq.size() == 0)
				return null;
			else if(dir == 0)
				dq.removeFirst();
			else
				dq.removeLast();
			
		}
		
		return dq_to_arr(dq, dir);
	}
	
	public static int[] dq_to_arr(Deque<Integer> dq, int dir) {
		int size = dq.size();
		int[] ret = new int[size];
		
		if(dir == 0)
			for(int i=0; i<size; i++)
				ret[i] = dq.pollFirst();
		else
			for(int i=0; i<size; i++)
				ret[i] = dq.pollLast();
		
		return ret;
	}
	
	public static void append_ans(int[] ans, StringBuilder sb) {
		if(ans == null) {
			sb.append("error\n");
			return;
		}
		
		int size = ans.length;
		sb.append("[");
		for(int i=0; i<size; i++) {
			sb.append(ans[i]).append(",");
		}
		if(size > 0)
			sb.deleteCharAt(sb.length()-1);
		sb.append("]\n");
	}
}
