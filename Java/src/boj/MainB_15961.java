package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_15961 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_15961.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken()) - 1;
		
		int[] list = new int[N];
		for(int i=0; i<N; i++)
			list[i] = Integer.parseInt(br.readLine()) - 1;
		
		int ans = solve(list, N, D, K, C);
		System.out.println(ans);
		br.close();
	}
	
	static int solve(int[] list, int N, int D, int K, int C) {
		// list : list, N : length, D : max no, K : consec, C : coupon 
		int ret = 0;
		
		int[] selected = new int[K];
		int[] counts = new int[D];
		int cur_score = init_que(list, selected, counts, C);
		ret = cur_score;
		
		for(int i=1; i<N; i++) {
			int idx = (i-1)%K;
			int rem = selected[idx];
			int add = list[(i-1+K)%N];
			
			cur_score = update_que(selected, counts, C, idx, rem, add, cur_score);
			
			if(cur_score > ret) ret = cur_score;
		}
		
		return ret;
	}
	
	static int init_que(int[] list, int[] selected, int[] counts, int C) {
		int ret = 1;
		int qlen = selected.length;
		counts[C] = Integer.MAX_VALUE;		
		for(int i=0; i<qlen; i++) {
			selected[i] = list[i];
			if(counts[list[i]] == 0) ret++;
			counts[list[i]] ++;
		}
		
		return ret;
	}
	
	static int update_que(int[] selected, int[] counts, int C, int idx, int rem, int add, int cur_score) {
		if(--counts[rem] == 0) cur_score--;
		if(counts[add]++ == 0) cur_score++;
		selected[idx] = add;
		
		return cur_score;
	}
}
