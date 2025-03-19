package boj;

import java.io.*;
import java.util.*;

public class MainB_01660 {

	public static int INIT_NUM;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01660.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int ans = solve(N);
		
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int N) {
		INIT_NUM = N;
		int[] mem = init_arr(N+1, INIT_NUM);
		int[] candidates = init_candidates(N);
		
		int len = candidates.length;
		mem[0] = 0;
		
		for(int i=len-1; i>-1; i--) {
			int amount = candidates[i];
			
			for(int j=0; j<N; j++) {
				if(mem[j] == -1) continue;
				if(j+amount > N) continue;
				
				mem[j+amount] = Math.min(mem[j]+1, mem[j+amount]);
			}
		}
		
		return mem[N];
	}
	
	public static int[] init_candidates(int N) {
		int[] ret = new int[600]; // 600 * 601 * 602 = 2.1M
		int idx = 1;
		
		while(true) {
			int count = idx * (idx+1) * (idx+2) / 6;
			if(count > N) break;
			
			ret[idx] = count;
			idx++;
		}
		return Arrays.copyOfRange(ret, 0, idx);
	}
	
	public static int[] init_arr(int size, int val) {
		int[] ret = new int[size];
		
		for(int i=0; i<size; i++) {
			ret[i] = val;
		}
		
		return ret;
	}
}
