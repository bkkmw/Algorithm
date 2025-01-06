package boj;

import java.io.*;
import java.util.*;

public class MainB_01654 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_01654.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] length = new int[N];
		for(int i=0; i<N; i++) {
			length[i] = Integer.parseInt(br.readLine());
		}
		
		int ans = solve(length, K);
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[] length, int K) {
		int left = 1, ret = 1;
		
		int right = find_max_len(length);
				
		while(left <= right && left > 0) {
			int unit_len = (int) (((long)left + (long)right)/2L);
			if(determ_fun(length, unit_len, K)) {
				ret = Math.max(ret, unit_len);
				left = unit_len+1;
			} else {
				right = unit_len-1;
			}
			
		}
		
		return ret;
	}
	
	public static boolean determ_fun(int[] length, int unit_len, int K) {
		int N = length.length;
		int cnt = 0;
		
		for(int i=0; i<N; i++) {
			cnt += length[i] / unit_len;
		}
		
		return cnt >= K;
	}
	
	public static int find_max_len(int[] length) {
		int ret = 0, N = length.length;
		
		for(int i=0; i<N; i++) {
			if(length[i] > ret)
				ret = length[i];
		}
		
		return ret;
	}
}
