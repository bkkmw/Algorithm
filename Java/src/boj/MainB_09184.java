package boj;

import java.io.*;
import java.util.*;

public class MainB_09184 {
	
	public static int[][][] mem = new int[21][21][21];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_09184.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
				
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == -1 && b == -1 && c == -1) break;
			int ans = solve(a, b, c);
			
			sb.append(String.format("w(%d, %d, %d) = %d\n", a,b,c,ans));
		}
		
		System.out.println(sb.toString().trim());
	}
	
	public static int solve(int a, int b, int c) {
		if(a<=0 || b<=0 || c<=0) return 1;
		if(a>20 || b>20 || c>20) return 1048576;
		if(mem[a][b][c] != 0) return mem[a][b][c];
		if(a<b && b<c)
			return mem[a][b][c] = (solve(a,b,c-1) + solve(a,b-1,c-1) - solve(a,b-1,c));
		return mem[a][b][c] = (solve(a-1,b,c) + solve(a-1,b-1,c) + solve(a-1,b,c-1) - solve(a-1,b-1,c-1));
	}
	
}
