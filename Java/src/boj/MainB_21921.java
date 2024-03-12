package boj;

import java.io.*;
import java.util.*;

public class MainB_21921 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_21921.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int[] visitors = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			visitors[i] = i==0 ? 0 : visitors[i-1];
			visitors[i] += cnt;
		}
		
		solve(visitors, N, X, sb);
		
		System.out.println(sb.toString());
	}
	
	public static void solve(int[] visitors, int N, int X, StringBuilder sb) {
		int max = 0, cnt = 0;
		
		for(int i=0; i<=N-X; i++) {
			int visitor = visitors[i+X] - visitors[i];
			if(visitor > max) {
				max = visitor;
				cnt = 1;
			}
			else if(visitor == max) {
				cnt++;
			}
		}
		
		if(max == 0) {
			sb.append("SAD");
		}
		else {
			sb.append(max).append("\n");
			sb.append(cnt).append("\n");
		}
	}
}
