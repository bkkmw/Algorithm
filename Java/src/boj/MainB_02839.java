package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainB_02839 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02839.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int ans = solve(N);
		
		sb.append(ans);
		System.out.println(sb);
		
	}
	static int solve(int N) {
		int ret = -1;
		for(int i=N/5; i>=0; i--) {
			int rem = N - 5 * i;
			if(rem % 3 == 0) {
				return i + rem/3;
			}
		}
		return ret;
	}
}
