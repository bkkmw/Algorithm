package boj;

import java.util.*;
import java.io.*;

public class MainB_17071 {
	
	public static final int END = 500000;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_17071.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int ans = solve(N, K);
		System.out.println(ans);
	}
	
	public static int solve(int N, int K) {
		int ret = -1;
		int[][] check = init_mat(END+1, 2, -1);
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {N, 0});
		check[N][0] = 0;
		
		int[] next = new int[3];
		int sec = -1;
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			int pos = poll[0];
			
			if(sec != poll[1]) {
				sec = poll[1];
				int moved = (sec+1)*sec/2;
				if(K+moved > END) return -1;
				if(pos == K + moved) return sec;
				
				if(check[K+moved][sec%2] >= 0) return sec;
			}
			
			update_next(next, pos);
			
			for(int i=0; i<3; i++) {
				if(next[i] < 0 || next[i] > END) continue;
				if(check[next[i]][(sec+1)%2] > 0) continue;
				
				q.add(new int[] {next[i], sec+1});
				check[next[i]][(sec+1)%2] = sec+1;
			}
		}
		
		return ret;
	}
	
	public static void update_next(int[] next, int pos) {
		next[0] = pos-1;
		next[1] = pos+1;
		next[2] = 2*pos;
	}
	
	public static int[][] init_mat(int n, int m, int val) {
		int[][] ret = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++)
				ret[i][j] = val;
		}
		return ret;
	}
	
}
