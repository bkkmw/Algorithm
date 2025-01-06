package boj;

import java.io.*;

public class MainB_01300 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_01300.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		long ans = solve(N, K);
		
		System.out.println(ans);
		br.close();
	}
	
	public static long solve(int N, int K) {
		// WA : type casting
		long ret = (long)N*N+1;
		long left = 1, right = (long)N*N;
		
		while(left <= right) {
			long mid = (left+right)/2;
			
			if(determ_fun(N, K, mid)) {
//				ret = mid;
				ret = Math.min(mid, ret);
				right = mid-1;
			} else {
				left = mid+1;
			}
		}
		return ret;
	}
	
	public static boolean determ_fun(int N, int K, long num) {
		long cnt = 0;
		
		for(int i=1; i<N+1; i++) {
			cnt += Math.min(num/i, N);
		}
		
		return cnt >= K;
	}
}
