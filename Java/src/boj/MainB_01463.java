package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainB_01463 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01463.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int ans = solve(N);
		sb.append(ans);
		System.out.println(sb.toString());
	}
	
	static int solve(int N) {
		int ret = 0;
		int[] count = new int[N+1];
		
		if(N<3) return N-1;
		count[2] = 1;
		count[3] = 1;
		for(int i=4; i<=N; i++) {
			int i1 = count[i/2] + 1 + i%2;
			int i2 = count[i/3] + 1 + i%3;
			count[i] = Math.min(i1, i2);
		}
		return count[N];
	}
}
