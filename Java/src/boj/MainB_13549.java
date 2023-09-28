package boj;

import java.util.*;
import java.io.*;

public class MainB_13549 {
	
	public static final int END = 100000;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_13549.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int ans = solve(N, K);
		System.out.println(ans);
	}
	
	public static int solve(int N, int K) {
		if(N==K) return 0;
		int ret = -1;
		Queue<int[]> q = new LinkedList<int[]>();
		int[] check = init_arr(END+1, END+1);
		
		q.add(new int[] {N, 0});
		check[N] = 0;
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			int pos = poll[0], sec = poll[1];
			
			if(K==pos) return sec;
			if(Math.abs(K-pos) < 2) ret = sec+1;
			
			if(pos-1 > 0 && check[pos-1] > sec+1) {
				q.add(new int[] {pos-1, sec+1});
				check[pos-1] = sec+1;
			}
			if(pos+1 < END+1 && check[pos+1] > sec+1) {
				q.add(new int[] {pos+1, sec+1});
				check[pos+1] = sec+1;
			}
			
			pos *= 2;
			while(pos <= END) {
				if(K == pos) return sec;
				if(Math.abs(K-pos) < 2) return sec+1;
				
				if(check[pos] <= sec) break;
				if(pos > K) break;
				
				check[pos] = sec;
				if(pos-1 > -1 && check[pos-1] > sec+1) {
					q.add(new int[] {pos-1, sec+1});
					check[pos-1] = sec+1;
				}
				if(pos+1 < END+1 && check[pos+1] > sec+1) {
					q.add(new int[] {pos+1, sec+1});
					check[pos+1] = sec+1;
				}
				
				pos*=2;
			}
			
			if(ret > -1) return ret;
		}
		
		
		return ret;
	}
	
	public static int[] init_arr(int size, int val) {
		int[] ret = new int[size];
		for(int i=0; i<size; i++)
			ret[i] = val;
		return ret;
	}
}
