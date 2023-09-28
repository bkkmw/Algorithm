package boj;

import java.util.*;
import java.io.*;

public class MainB_12851 {
	
	public static final int END = 100000;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_12851.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] ans = solve(N, K);
		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}
	
	public static int[] solve(int N, int K) {
		if(N==K) return new int[] {0, 1};
		int[] ret = new int[2];
		ret[0] = -1;
		int[] check = init_arr(END+1, -1);
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {N, 0});
		check[N] = 0;
		
		int[] next = new int[3];
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			int pos = poll[0];
			
			if(ret[0] > 0 && ret[0] <= poll[1])
				break;
			
			next_pos(next, pos);
			
			for(int i=0; i<3; i++) {
				if(next[i] < 0 || next[i] > END) continue;
				if(check[next[i]] > -1 && check[next[i]] <= poll[1]) continue;
				
				if(next[i] == K) {
					ret[0] = poll[1]+1;
					ret[1] ++;
				}
				else {
					q.add(new int[] {next[i], poll[1]+1});
					check[next[i]] = poll[1]+1;
				}
			}
		}
		
		return ret;
	}
	
	public static int[] init_arr(int size, int val) {
		int[] ret = new int[size];
		for(int i=0; i<size; i++)
			ret[i] = val;
		return ret;
	}
	
	public static void next_pos(int[] next, int pos) {
		next[0] = pos-1;
		next[1] = pos+1;
		next[2] = pos*2;
	}
}
