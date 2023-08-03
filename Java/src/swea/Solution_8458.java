package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_8458 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_8458.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] dists = new int[N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				dists[i] = Math.abs(y) + Math.abs(x);
			}
			
			int ans = solve(dists, N);
			
			sb.append(String.format("#%d %d\n", tc, ans));
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static int solve(int[]dists, int N) {
		int ret = 0;
		long dist = 0;
		int max_dist = 0;
		int[] status = new int[N];
		
		for(int i=0; i<N; i++) if(dists[i] > max_dist) max_dist = dists[i];
		
		while(dist < max_dist) {
			ret ++;
			dist += ret;
		}
		
		for(int i=0; i<N; i++) {
			long temp = dist - (long)dists[i];
			status[i] = (int)(temp % 2);
		}
		
		return check_status(status, N, ret);
	}
	
	static int check_status(int[] status, int N, int ret) {
		int stat = status[0];
		for(int i=1; i<N; i++) {
			if(status[i] != stat) return -1;
		}
		return (stat == 0)? ret : (ret%2==0)? ret+1 : ret+2;
	}
}
