package boj;

import java.io.*;
import java.util.*;

public class MainB_14501 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_14501.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] counsel = new int[N][2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int days = Integer.parseInt(st.nextToken());
			int income = Integer.parseInt(st.nextToken());
			
			counsel[i][0] = days;
			counsel[i][1] = income;
		}
		
		int ans = solve(counsel);
		System.out.println(ans);
	}
	
	public static int solve(int[][] counsel) {
		int ret = 0, N = counsel.length;
		
		ret = get_max_income(counsel, 0, N, 0, ret);
		
		return ret;
	}
	
	public static int get_max_income(int[][] counsel, int idx, int N, int income, int max) {
		if(idx >= N) return income;
		int cnt = 0;
		if(idx+counsel[idx][0] <= N)
			cnt = get_max_income(counsel, idx+counsel[idx][0], N, income+counsel[idx][1], max);

		cnt = Math.max(cnt, get_max_income(counsel, idx+1, N, income, max));
		if(cnt > max) max = cnt;
		
		return max;
	}
}
