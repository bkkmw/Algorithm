package boj;

import java.io.*;
import java.util.*;

public class MainB_01205 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01205.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		int[] scores = new int[N];
		
		if(N > 0) 
			st = new StringTokenizer(br.readLine());
			
		for(int i=0; i<N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = solve(scores, S, P);
		
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[] scores, int S, int P) {
		int N = scores.length;
		int consecutive_cnt = 0;
		int prev_score = 2_000_000_001;
		int i = N-1;
		
		Arrays.sort(scores);
				
		for(; i>=0; i--) {			
			if(scores[i] < S) {
				break;
			}
			
			if(scores[i] == prev_score) {
				consecutive_cnt ++;
			} else {
				prev_score = scores[i];
				consecutive_cnt = 1;
			}
		}
		
		// WA(15%)
		if(N-i > P) return -1;
		
		int ret = N-i - (prev_score==S? consecutive_cnt : 0);
		return ret > P ? -1 : ret;
	}
}
