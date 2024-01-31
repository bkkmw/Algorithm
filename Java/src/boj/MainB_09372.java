package boj;

import java.io.*;
import java.util.*;

public class MainB_09372 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_09372.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<M; i++) {
				br.readLine();
			}
			
			int ans = solve(N);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static int solve(int N) {
		return N-1;
	}
} 
