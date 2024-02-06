package boj;

import java.io.*;
import java.util.*;

public class MainB_11052 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_11052.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] costs = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = solve(costs);
		System.out.println(ans);
	}
	
	public static int solve(int[] costs) {
		int N = costs.length;
		int[] ret = new int[N+1];
		
		for(int i=0; i<N; i++) {
			int card_cnt = i+1, price = costs[i];
			for(int j=card_cnt; j<N+1; j++) {
				if(ret[j] < ret[j-card_cnt]+price) 
					ret[j] = ret[j-card_cnt]+price;
			}
		}
		return ret[N];
	}
}
