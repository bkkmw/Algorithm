package boj;

import java.util.*;
import java.io.*;

public class MainB_07795 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_07795.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<TC+1; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			long[] as = new long[N];
			long[] bs = new long[M];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++)
				as[i] = Long.parseLong(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<M;i ++) 
				bs[i] = Long.parseLong(st.nextToken());
				
			int ans = solve(as, bs);
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static int solve(long[] as, long[] bs) {
		int ret = 0;
		int N = as.length, M = bs.length;
		
		Arrays.sort(as);
		Arrays.sort(bs);
		
		int b_idx = -1;
		for(int i=0; i<N; i++) {
			for(int j=b_idx; j<M-1; j++) {
				if(as[i] <= bs[j+1])
					break;
				else
					b_idx = j+1;
			}
			
			ret += (b_idx+1);
		}
				
		return ret;
	}
}
