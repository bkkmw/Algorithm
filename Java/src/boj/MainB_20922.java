package boj;

import java.io.*;
import java.util.*;

public class MainB_20922 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_20922.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] seq = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			seq[i] = Integer.parseInt(st.nextToken());
		
		int ans = solve(seq, N, K);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[] seq, int N, int K) {
		if(N<2) return 1;
		int ret = 0;
		int[] cnt = new int[100001];
		
		cnt[seq[0]] += 1;
		int len = 1;
		int lidx = 0, ridx = 1;
		
		while(lidx < N) {
			while(cnt[seq[ridx]] < K) {
				cnt[seq[ridx]]++;
				len++;
				ridx++;
				if(ridx == N) 
					return ret<len? len : ret;
			}
			
			ret = ret<len? len : ret;
			
			cnt[seq[lidx]] --;
			lidx ++;
			len--;
		}
		
		return ret;
	}
}
